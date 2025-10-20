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
    private LocalDate fecha_emision;
    private boolean estado;

    public DetalleTicket() {
        this.id_ticket = -1;
    }

    public DetalleTicket(LocalDate fecha_emision, boolean estado) {
        this.id_ticket = -1;
        this.fecha_emision = fecha_emision;
        this.estado = estado;
    }

    public DetalleTicket(int id_ticket, LocalDate fecha_emision, boolean estado) {
        this.id_ticket = id_ticket;
        this.fecha_emision = fecha_emision;
        this.estado = estado;
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
