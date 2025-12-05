
package com.mycompany.modulo_banco.model;

import com.mycompany.modulo_banco.utils.ConexionDB;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO {
    public List<Movimiento> obtenerMovimientos(int idCuenta) {
        List<Movimiento> listaMovimientos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // Base de la consulta, incluyendo la ordenación para calcular saldos
        String sql = "SELECT id_transaccion, referencia, fecha_hora, descripcion, tipo, monto, estado "
                   + "FROM transacciones WHERE id_cuenta = ?";
        

        
        // Ordenamos por fecha descendente para obtener los más recientes
        sql += " ORDER BY fecha_hora DESC";

        try {
            conn = ConexionDB.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCuenta);
      

            rs = pstmt.executeQuery();

            // Mapeo de resultados
            while (rs.next()) {
                Movimiento t = new Movimiento();
                t.setId(rs.getInt("id_transaccion"));
                t.setReferencia(rs.getString("referencia"));
                t.setFecha(rs.getTimestamp("fecha_hora").toLocalDateTime());
                t.setDescripcion(rs.getString("descripcion"));
                t.setTipo(rs.getString("tipo"));
                t.setMonto(rs.getDouble("monto"));
                t.setEstado(rs.getString("estado"));
                
                // Nota: El 'saldo' mostrado en tu imagen es el saldo FINAL después de la transacción.
                // Tendrás que calcular el saldo final en el Servlet (Ver Sección 3.2).
                
                listaMovimientos.add(t);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener movimientos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cierre de recursos
        }
        return listaMovimientos;
    }
    
    
}
