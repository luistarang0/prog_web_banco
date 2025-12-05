
package com.mycompany.modulo_banco.model;

public enum EstadoUsuario {
    activo,
    inactivo,
    bloqueado;
    
    public static EstadoUsuario fromString(String estado) {
        try {
            return EstadoUsuario.valueOf(estado.toUpperCase());
        } catch (Exception e) {
            return activo; // valor por defecto
        }
    }
}
