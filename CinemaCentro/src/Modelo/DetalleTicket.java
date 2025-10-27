/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Naiara
 */
public class DetalleTicket {
    
    private int id_ticket;
    private int id_funcion;
    private int id_asiento;
    private int id_venta;
    private LocalDate fecha_emision;
    private boolean estado;

    public DetalleTicket() {
        this.id_ticket = -1;
    }

    public DetalleTicket(int id_ticket, int id_funcion, int id_asiento, int id_venta, LocalDate fecha_emision, boolean estado) {
        this.id_ticket = id_ticket;
        this.id_funcion = id_funcion;
        this.id_asiento = id_asiento;
        this.id_venta = id_venta;
        this.fecha_emision = fecha_emision;
        this.estado = estado;
    }

    public DetalleTicket(int id_funcion, int id_asiento, int id_venta, LocalDate fecha_emision, boolean estado) {
        this.id_funcion = id_funcion;
        this.id_asiento = id_asiento;
        this.id_venta = id_venta;
        this.fecha_emision = fecha_emision;
        this.estado = estado;
    }

    public int getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(int id_funcion) {
        this.id_funcion = id_funcion;
    }

    public int getId_asiento() {
        return id_asiento;
    }

    public void setId_asiento(int id_asiento) {
        this.id_asiento = id_asiento;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public boolean equals(Object a) {
        if(a == this){
            return true;
        }
        if(a.getClass() != this.getClass() || this.id_ticket == 0){
            return false;
        }
        DetalleTicket ticket = (DetalleTicket) a;
        return ticket.id_ticket == this.id_ticket;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id_ticket);
    }

    @Override
    public String toString() {
        return "DetalleTicket: \n" + "\tid_ticket: " + id_ticket + "\n \tFecha de emisión: " + fecha_emision + "\n \tEstado: " + estado;
    }
    
    
}
