package com.mycompany.modulo_banco.model;

import java.time.LocalDateTime;

public class Cuenta {

    private int idCuenta;
    private int idUsuario;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private LocalDateTime fechaCreacion;

    public Cuenta() {
    }

    public Cuenta(int idCuenta, int idUsuario, String numeroCuenta, String tipoCuenta, double saldo, LocalDateTime fechaCreacion) {
        this.idCuenta = idCuenta;
        this.idUsuario = idUsuario;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUltimosCuatroDigitos() {
        if (numeroCuenta != null && numeroCuenta.length() >= 4) {
            // Devuelve los últimos 4 caracteres
            return numeroCuenta.substring(numeroCuenta.length() - 4);
        }
        return "N/A";
    }

}
