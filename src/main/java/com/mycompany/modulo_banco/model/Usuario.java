
package com.mycompany.modulo_banco.model;

import java.time.LocalDateTime;

public class Usuario {
    
    private int id;
    private String numeroCuenta;
    private String nombre;
    private String username;
    private String email;
    private String password;
    private String telefono;
    private LocalDateTime fechaRegistro;
            
    public Usuario() {
        
    }
    
    public Usuario(String numeroCuenta, String nombre, String username, String email,
            String password, String telefono) {
        this.numeroCuenta = numeroCuenta;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
    
    public Usuario(int id, String numeroCuenta, String nombre, String username, String email, 
                   String telefono, LocalDateTime fechaRegistro) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }        
}
