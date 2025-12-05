
package com.mycompany.modulo_banco.model;

import com.mycompany.modulo_banco.utils.ConexionDB;
import java.sql.*;

public class CuentaDAO {
    public boolean crearCuenta(Connection conn, int idUsuario, String numeroCuenta) throws SQLException {
        // La conexión se pasa desde el método transaccional del UsuarioDAO
        String sql = "INSERT INTO cuentas (id_usuario, numero_cuenta, tipo_cuenta, saldo, fecha_creacion) VALUES (?, ?, 'DEFAULT', 0.00, NOW())";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            pstmt.setString(2, numeroCuenta);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public Cuenta obtenerCuentaPorUsuarioId(int idUsuario) {
        Cuenta cuenta = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // Selecciona todos los campos necesarios de la tabla 'cuentas'
        String sql = "SELECT id_cuenta, numero_cuenta, saldo, tipo_cuenta, fecha_creacion "
                   + "FROM cuentas WHERE id_usuario = ?";

        try {
            conn = ConexionDB.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                cuenta = new Cuenta();
                // Mapeo de la cuenta
                cuenta.setIdCuenta(rs.getInt("id_cuenta"));
                cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setTipoCuenta(rs.getString("tipo_cuenta"));
                cuenta.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
               
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener cuenta por ID de usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return cuenta;
    }
}
