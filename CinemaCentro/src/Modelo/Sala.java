/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Objects;



/**
 *
 * @author Naiara
 */
public class Sala {
    
    private int nro_Sala;
    private int capacidad;
    private boolean estado;
    private boolean apta3D;

    public Sala() {
    }

    
    
    public Sala(int nro_Sala, int Capacidad, boolean Estado, boolean Apta3D) {
        this.nro_Sala = nro_Sala;
        this.capacidad = Capacidad;
        this.estado = Estado;
        this.apta3D = Apta3D;
    }

    public Sala(int capacidad, boolean estado, boolean apta3D) {
        this.capacidad = capacidad;
        this.estado = estado;
        this.apta3D = apta3D;
    }

    
    
    public int getNro_Sala() {
        return nro_Sala;
    }

    public void setNro_Sala(int nro_Sala) {
        this.nro_Sala = nro_Sala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.capacidad = Capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean Estado) {
        this.estado = Estado;
    }

    public boolean isApta3D() {
        return apta3D;
    }

    public void setApta3D(boolean Apta3D) {
        this.apta3D = Apta3D;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.nro_Sala);
    }

    
    public boolean equals(Object a){
        if(a == this){
            return true;
        }
        if(a.getClass() != this.getClass() || this.nro_Sala == 0){
            return false;
        }
        Sala sal = (Sala) a;
        return  sal.nro_Sala == this.nro_Sala;
    }

    @Override
    public String toString() {
        return "Sala{" + "nro_Sala=" + nro_Sala + ", Capacidad=" + capacidad + ", Estado=" + estado + ", Apta3D=" + apta3D + '}';
    }
    
    
}
