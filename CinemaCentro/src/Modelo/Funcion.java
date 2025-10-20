/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Naiara
 */
public class Funcion {
    private int id_Funcion;
    private int id_Pelicula;
    private int Nro_sala;
    private String Edioma;
    private boolean Es3D;
    private Time Hora_Inicio;
    private Time Hora_Fin;
    private double Precio_Entrada;
    private Date Fecha_Funcion;
    private boolean Subtitulada;
    private int Estado;

    public Funcion() {
        this.id_Funcion=-1;
        this.id_Pelicula=-1;
    }

    public Funcion(int id_Funcion, int id_Pelicula) {
        this.id_Funcion = id_Funcion;
        this.id_Pelicula = id_Pelicula;
    }

    
    
    public Funcion(int id_Funcion, int id_Pelicula, int Nro_sala, String Edioma, boolean Es3D, Time Hora_Inicio, Time Hora_Fin, double Precio_Entrada, Date Fecha_Funcion, boolean Subtitulada, int Estado) {
        this.id_Funcion = id_Funcion;
        this.id_Pelicula = id_Pelicula;
        this.Nro_sala = Nro_sala;
        this.Edioma = Edioma;
        this.Es3D = Es3D;
        this.Hora_Inicio = Hora_Inicio;
        this.Hora_Fin = Hora_Fin;
        this.Precio_Entrada = Precio_Entrada;
        this.Fecha_Funcion = Fecha_Funcion;
        this.Subtitulada = Subtitulada;
        this.Estado = Estado;
    }

    public int getId_Funcion() {
        return id_Funcion;
    }

    public void setId_Funcion(int id_Funcion) {
        this.id_Funcion = id_Funcion;
    }

    public int getId_Pelicula() {
        return id_Pelicula;
    }

    public void setId_Pelicula(int id_Pelicula) {
        this.id_Pelicula = id_Pelicula;
    }

    public int getNro_sala() {
        return Nro_sala;
    }

    public void setNro_sala(int Nro_sala) {
        this.Nro_sala = Nro_sala;
    }

    public String getEdioma() {
        return Edioma;
    }

    public void setEdioma(String Edioma) {
        this.Edioma = Edioma;
    }

    public boolean isEs3D() {
        return Es3D;
    }

    public void setEs3D(boolean Es3D) {
        this.Es3D = Es3D;
    }

    public Time getHora_Inicio() {
        return Hora_Inicio;
    }

    public void setHora_Inicio(Time Hora_Inicio) {
        this.Hora_Inicio = Hora_Inicio;
    }

    public Time getHora_Fin() {
        return Hora_Fin;
    }

    public void setHora_Fin(Time Hora_Fin) {
        this.Hora_Fin = Hora_Fin;
    }

    public double getPrecio_Entrada() {
        return Precio_Entrada;
    }

    public void setPrecio_Entrada(double Precio_Entrada) {
        this.Precio_Entrada = Precio_Entrada;
    }

    public Date getFecha_Funcion() {
        return Fecha_Funcion;
    }

    public void setFecha_Funcion(Date Fecha_Funcion) {
        this.Fecha_Funcion = Fecha_Funcion;
    }

    public boolean isSubtitulada() {
        return Subtitulada;
    }

    public void setSubtitulada(boolean Subtitulada) {
        this.Subtitulada = Subtitulada;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id_Funcion;
        hash = 41 * hash + this.id_Pelicula;
        hash = 41 * hash + this.Nro_sala;
        hash = 41 * hash + Objects.hashCode(this.Edioma);
        hash = 41 * hash + (this.Es3D ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.Hora_Inicio);
        hash = 41 * hash + Objects.hashCode(this.Hora_Fin);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.Precio_Entrada) ^ (Double.doubleToLongBits(this.Precio_Entrada) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.Fecha_Funcion);
        hash = 41 * hash + (this.Subtitulada ? 1 : 0);
        hash = 41 * hash + this.Estado;
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
        final Funcion other = (Funcion) obj;
        if (this.id_Funcion != other.id_Funcion) {
            return false;
        }
        if (this.id_Pelicula != other.id_Pelicula) {
            return false;
        }
        if (this.Nro_sala != other.Nro_sala) {
            return false;
        }
        if (this.Es3D != other.Es3D) {
            return false;
        }
        if (Double.doubleToLongBits(this.Precio_Entrada) != Double.doubleToLongBits(other.Precio_Entrada)) {
            return false;
        }
        if (this.Subtitulada != other.Subtitulada) {
            return false;
        }
        if (this.Estado != other.Estado) {
            return false;
        }
        if (!Objects.equals(this.Edioma, other.Edioma)) {
            return false;
        }
        if (!Objects.equals(this.Hora_Inicio, other.Hora_Inicio)) {
            return false;
        }
        if (!Objects.equals(this.Hora_Fin, other.Hora_Fin)) {
            return false;
        }
        if (!Objects.equals(this.Fecha_Funcion, other.Fecha_Funcion)) {
            return false;
        }
        return true;
    }

    
    
}
