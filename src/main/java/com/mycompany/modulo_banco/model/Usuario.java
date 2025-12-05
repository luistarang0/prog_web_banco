package com.mycompany.modulo_banco.model;

import java.time.LocalDateTime;
import com.mycompany.modulo_banco.model.TipoUsuario;
import com.mycompany.modulo_banco.model.EstadoUsuario;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private TipoUsuario tipo;
    private EstadoUsuario estado;
    private LocalDateTime fechaRegistro;

    public Usuario() {

    }

    public Usuario(String numeroCuenta, String nombre, String username, String email,
            String password, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }

    public Usuario(int id, String numeroCuenta, String nombre, String username, String email,
            String telefono, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario(int id, String numeroCuenta, String nombre, String email, String password, String telefono, TipoUsuario tipo, EstadoUsuario estado, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

}
