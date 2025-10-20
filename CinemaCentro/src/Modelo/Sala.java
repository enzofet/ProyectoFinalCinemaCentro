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
    private int NroSala;
    private int Capacidad;
    private int Estado;
    private int Apta3D;

    public Sala() {
    }

    public Sala(int NroSala, int Capacidad, int Estado, int Apta3D) {
        this.NroSala = NroSala;
        this.Capacidad = Capacidad;
        this.Estado = Estado;
        this.Apta3D = Apta3D;
    }

    public int getNroSala() {
        return NroSala;
    }

    public void setNroSala(int NroSala) {
        this.NroSala = NroSala;
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
        int hash = 3;
        hash = 41 * hash + this.NroSala;
        hash = 41 * hash + this.Capacidad;
        hash = 41 * hash + this.Estado;
        hash = 41 * hash + this.Apta3D;
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
        if (this.NroSala != other.NroSala) {
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
