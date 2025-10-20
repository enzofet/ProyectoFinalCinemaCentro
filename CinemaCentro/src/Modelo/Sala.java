/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
        int hash = 5;
        hash = 89 * hash + this.nro_Sala;
        hash = 89 * hash + this.Capacidad;
        hash = 89 * hash + this.Estado;
        hash = 89 * hash + this.Apta3D;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sala other = (Sala) obj;
        if (this.nro_Sala != other.nro_Sala) {
            return false;
        }
        if (this.Capacidad != other.Capacidad) {
            return false;
        }
        if (this.Estado != other.Estado) {
            return false;
        }
        if (this.Apta3D != other.Apta3D) {
            return false;
        }
        return true;
    }
    
    
}
