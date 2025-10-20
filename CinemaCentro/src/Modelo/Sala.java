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
    private int Capacidad;
    private int Estado;
    private int Apta3D;

    public Sala() {
    }

    
    
    public Sala(int nro_Sala, int Capacidad, int Estado, int Apta3D) {
        this.nro_Sala = nro_Sala;
        this.Capacidad = Capacidad;
        this.Estado = Estado;
        this.Apta3D = Apta3D;
    }

    public int getNro_Sala() {
        return nro_Sala;
    }

    public void setNro_Sala(int nro_Sala) {
        this.nro_Sala = nro_Sala;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public int getApta3D() {
        return Apta3D;
    }

    public void setApta3D(int Apta3D) {
        this.Apta3D = Apta3D;
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
        return "Sala{" + "nro_Sala=" + nro_Sala + ", Capacidad=" + Capacidad + ", Estado=" + Estado + ", Apta3D=" + Apta3D + '}';
    }
    
    
}
