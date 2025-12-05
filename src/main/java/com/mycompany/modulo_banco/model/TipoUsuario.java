/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modulo_banco.model;

public enum TipoUsuario {
    cliente,
    admin,
    proveedor;
    
    public static TipoUsuario fromString(String tipo) {
        try {
            return TipoUsuario.valueOf(tipo.toUpperCase());
        } catch (Exception e) {
            return cliente; // valor por defecto
        }
    }
}
