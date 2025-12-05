package com.mycompany.modulo_banco.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movimiento {

    private int id;
    private int idCuenta;
    private LocalDateTime fecha;
    private String descripcion;
    private String tipo;
    private String referencia;
    private Double monto;
    private String estado;

    public Movimiento() {
    }

    public Movimiento(int idCuenta, LocalDateTime fecha, String descripcion, String tipo, String referencia, Double monto, String estado) {
        this.idCuenta = idCuenta;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.referencia = referencia;
        this.monto = monto;
        this.estado = estado;
    }

    public Movimiento(int id, int idCuenta, LocalDateTime fecha, String descripcion, String tipo, String referencia, Double monto, String estado) {
        this.id = id;
        this.idCuenta = idCuenta;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.referencia = referencia;
        this.monto = monto;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
