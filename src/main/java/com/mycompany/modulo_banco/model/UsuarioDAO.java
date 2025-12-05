package com.mycompany.modulo_banco.model;

import com.mycompany.modulo_banco.utils.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.util.Random;

public class UsuarioDAO {

    private static final String SELECT_USER_BY_ACCOUNT
            = "SELECT id_usuario, nombre_completo, correo, telefono, password_hash, tipo_usuario, estado, fecha_creacion, numero_cuenta "
            + "FROM usuarios WHERE correo = ?";
    private final Random random = new SecureRandom();
    private final int NUMERO_DIGITOS = 16;

    public Usuario validarLogin(String correo, String password) {
        Usuario usuario = null;

        try (Connection connection = ConexionDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ACCOUNT)) {

            preparedStatement.setString(1, correo);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    // 1. Obtiene la contraseña hasheada almacenada en la BD
                    String storedPasswordHash = rs.getString("password_hash");

                    if (password.equals(storedPasswordHash)) {

                        // Si la contraseña es correcta, mapeamos el usuario
                        usuario = new Usuario();
                        usuario.setId(rs.getInt("id_usuario"));
                        usuario.setNombre(rs.getString("nombre_completo"));
                        usuario.setEmail(rs.getString("correo"));
                        usuario.setTelefono(rs.getString("telefono"));
                        usuario.setFechaRegistro(rs.getTimestamp("fecha_creacion").toLocalDateTime());
                        usuario.setPassword(rs.getString("password_hash"));
                        usuario.setTipo(TipoUsuario.fromString(rs.getString("tipo_usuario")));
                        usuario.setEstado(EstadoUsuario.fromString(rs.getString("estado")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean registrarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement pstmtUsuario = null;
        PreparedStatement pstmtCuenta = null;
        ResultSet rs = null;

        CuentaDAO cuentaDAO = new CuentaDAO();

        try {
            // Obtener conexión
            conn = ConexionDB.getConnection();

            conn.setAutoCommit(false);

            String sql = "INSERT INTO usuarios (nombre_completo, correo, telefono, "
                    + "password_hash, tipo_usuario, estado, fecha_creacion) "
                    + "VALUES (?, ?, ?, ?, ?::tipo_usuario_enum, ?::estado_usuario_enum, ?)";

            pstmtUsuario = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmtUsuario.setString(1, usuario.getNombre());
            pstmtUsuario.setString(2, usuario.getEmail());
            pstmtUsuario.setString(3, usuario.getTelefono());
            pstmtUsuario.setString(4, usuario.getPassword());
            pstmtUsuario.setString(5, usuario.getTipo().name());
            pstmtUsuario.setString(6, usuario.getEstado().name());
            pstmtUsuario.setObject(7, usuario.getFechaRegistro());

            // Ejecutar INSERT
            int filasAfectadas = pstmtUsuario.executeUpdate();

            if (filasAfectadas == 0) {
                conn.rollback();
                return false;
            }

            rs = pstmtUsuario.getGeneratedKeys();

            int idGenerado = 0;

            if (rs.next()) {
                idGenerado = rs.getInt(1); // Se usa el ResultSet (rs) para OBTENER el valor del ID
                usuario.setId(idGenerado);
            } else {
                conn.rollback(); // Fallo en obtener el ID (imposible crear cuenta)
                return false;
            }

            String nuevoNumeroCuenta = generarNumeroCuentaUnico();

            boolean cuentaCreada = cuentaDAO.crearCuenta(conn, idGenerado, nuevoNumeroCuenta);

            if (!cuentaCreada) {
                conn.rollback(); // Fallo al crear la cuenta
                return false;
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;

        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmtUsuario != null) {
                    pstmtUsuario.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true); // Restaurar autocommit
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    private String generarNumeroCuentaUnico() {

        long numeroBase = Math.abs(random.nextLong());

        String cuenta = String.valueOf(numeroBase);

        if (cuenta.length() > NUMERO_DIGITOS) {
            cuenta = cuenta.substring(cuenta.length() - NUMERO_DIGITOS);
        } else if (cuenta.length() < NUMERO_DIGITOS) {

            cuenta = String.format("%0" + NUMERO_DIGITOS + "d", numeroBase);
        }

        if (cuenta.length() > NUMERO_DIGITOS) {
            cuenta = cuenta.substring(cuenta.length() - NUMERO_DIGITOS);
        }

        return cuenta;
    }

}
