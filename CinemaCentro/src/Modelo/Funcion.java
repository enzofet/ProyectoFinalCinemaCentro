/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Naiara
 */
public class Funcion {
    private int id_Funcion;
    private int id_pelicula;
    private int nro_Sala;
    private String idioma;
    private int es3D;
    private Time Hora_Inicio;
    private Time Hora_Fin;
    private Double Precio_Entrada;
    private Date Fecha_Funcion;
    private int Subtitulada;
    private int Estado;

    public Funcion() {
        this.id_Funcion=-1;
        this.id_pelicula=-1;
    }

    public Funcion(int nro_Sala, String idioma, int es3D, Time Hora_Inicio, Time Hora_Fin, Double Precio_Entrada, Date Fecha_Funcion, int Subtitulada, int Estado) {
        this.nro_Sala = nro_Sala;
        this.idioma = idioma;
        this.es3D = es3D;
        this.Hora_Inicio = Hora_Inicio;
        this.Hora_Fin = Hora_Fin;
        this.Precio_Entrada = Precio_Entrada;
        this.Fecha_Funcion = Fecha_Funcion;
        this.Subtitulada = Subtitulada;
        this.Estado = Estado;
    }

    public Funcion(int id_Funcion, int id_pelicula, int nro_Sala, String idioma, int es3D, Time Hora_Inicio, Time Hora_Fin, Double Precio_Entrada, Date Fecha_Funcion, int Subtitulada, int Estado) {
        this.id_Funcion = id_Funcion;
        this.id_pelicula = id_pelicula;
        this.nro_Sala = nro_Sala;
        this.idioma = idioma;
        this.es3D = es3D;
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

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public int getNro_Sala() {
        return nro_Sala;
    }

    public void setNro_Sala(int nro_Sala) {
        this.nro_Sala = nro_Sala;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getEs3D() {
        return es3D;
    }

    public void setEs3D(int es3D) {
        this.es3D = es3D;
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

    public Double getPrecio_Entrada() {
        return Precio_Entrada;
    }

    public void setPrecio_Entrada(Double Precio_Entrada) {
        this.Precio_Entrada = Precio_Entrada;
    }

    public Date getFecha_Funcion() {
        return Fecha_Funcion;
    }

    public void setFecha_Funcion(Date Fecha_Funcion) {
        this.Fecha_Funcion = Fecha_Funcion;
    }

    public int getSubtitulada() {
        return Subtitulada;
    }

    public void setSubtitulada(int Subtitulada) {
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
        int hash = 7;
        hash = 59 * hash + this.id_Funcion;
        hash = 59 * hash + this.id_pelicula;
        hash = 59 * hash + this.nro_Sala;
        hash = 59 * hash + Objects.hashCode(this.idioma);
        hash = 59 * hash + this.es3D;
        hash = 59 * hash + Objects.hashCode(this.Hora_Inicio);
        hash = 59 * hash + Objects.hashCode(this.Hora_Fin);
        hash = 59 * hash + Objects.hashCode(this.Precio_Entrada);
        hash = 59 * hash + Objects.hashCode(this.Fecha_Funcion);
        hash = 59 * hash + this.Subtitulada;
        hash = 59 * hash + this.Estado;
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
        if (this.id_pelicula != other.id_pelicula) {
            return false;
        }
        if (this.nro_Sala != other.nro_Sala) {
            return false;
        }
        if (this.es3D != other.es3D) {
            return false;
        }
        if (this.Subtitulada != other.Subtitulada) {
            return false;
        }
        if (this.Estado != other.Estado) {
            return false;
        }
        if (!Objects.equals(this.idioma, other.idioma)) {
            return false;
        }
        if (!Objects.equals(this.Hora_Inicio, other.Hora_Inicio)) {
            return false;
        }
        if (!Objects.equals(this.Hora_Fin, other.Hora_Fin)) {
            return false;
        }
        if (!Objects.equals(this.Precio_Entrada, other.Precio_Entrada)) {
            return false;
        }
        if (!Objects.equals(this.Fecha_Funcion, other.Fecha_Funcion)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
