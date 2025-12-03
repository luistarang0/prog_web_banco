
package com.mycompany.modulo_banco.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movimiento {
    private int id; 
    private int usuarioId; 
    private LocalDate fecha;     
    private String descripcion;
    private String tipo;     
    private BigDecimal monto; 
    private BigDecimal saldo;     
    private LocalDateTime fechaCreacion;

    public Movimiento() {
    }

    public Movimiento(int usuarioId, LocalDate fecha, String descripcion, String tipo, BigDecimal monto, BigDecimal saldo) {
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.monto = monto;
        this.saldo = saldo;
    }

    public Movimiento(int id, int usuarioId, LocalDate fecha, String descripcion, String tipo, BigDecimal monto, BigDecimal saldo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.monto = monto;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }    
}
