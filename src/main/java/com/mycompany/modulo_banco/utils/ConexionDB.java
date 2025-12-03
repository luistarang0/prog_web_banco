
package com.mycompany.modulo_banco.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConexionDB {
    
    private static DataSource dataSource;
    

    private static DataSource getDataSource() throws SQLException {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();

                dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/halcon"); 


            } catch (NamingException e) {
                // Este catch es el que lanza el error, debemos asegurarnos que la búsqueda funcione.
                throw new SQLException("Error al obtener el DataSource: No se pudo enlazar el nombre jdbc/halcon. " + e.getMessage(), e);
            }
        }
        return dataSource;
    }
    
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    public static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar recurso: " + e.getMessage());
                }
            }
        }
    }
}