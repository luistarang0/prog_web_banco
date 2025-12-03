
package com.mycompany.modulo_banco.model;

import com.mycompany.modulo_banco.utils.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    private static final String SELECT_USER_BY_ACCOUNT = 
            "SELECT id, numero_cuenta, nombre, username, email, password, telefono, fecha_registro " +
            "FROM usuarios WHERE username = ?";
    
    public Usuario validarLogin(String username, String password) {
        Usuario usuario = null;
        
        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ACCOUNT)) {

            preparedStatement.setString(1, username);
            
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    // 1. Obtiene la contraseña hasheada almacenada en la BD
                    String storedPasswordHash = rs.getString("password");

                    // 2. *** SIMULACIÓN DE VERIFICACIÓN DE CONTRASEÑA (CRÍTICO) ***
                    // **Aquí deberías usar una librería como jBCrypt o Argon2 para comparar el hash**
                    // **Por ahora, para fines de prueba, comparamos el texto plano:**
                    if (password.equals(storedPasswordHash)) { 
                        
                        // Si la contraseña es correcta, mapeamos el usuario
                        usuario = new Usuario();
                        usuario.setId(rs.getInt("id"));
                        usuario.setNumeroCuenta(rs.getString("numero_cuenta"));
                        usuario.setNombre(rs.getString("nombre"));
                        usuario.setUsername(rs.getString("username"));
                        usuario.setEmail(rs.getString("email"));
                        usuario.setTelefono(rs.getString("telefono"));
                        usuario.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime()); 
                        
                        // Nota: NO devuelvas la contraseña ni el hash
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    
}
