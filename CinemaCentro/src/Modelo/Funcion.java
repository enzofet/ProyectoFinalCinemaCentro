/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Time;
import java.time.LocalDate;
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
    private LocalDate Fecha_Funcion;
    private int Subtitulada;
    private int Estado;

    public Funcion() {
        this.id_Funcion=-1;
        this.id_pelicula=-1;
    }

    public Funcion(int nro_Sala, String idioma, int es3D, Time Hora_Inicio, Time Hora_Fin, Double Precio_Entrada, LocalDate Fecha_Funcion, int Subtitulada, int Estado) {
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

    public Funcion(int id_Funcion, int id_pelicula, int nro_Sala, String idioma, int es3D, Time Hora_Inicio, Time Hora_Fin, Double Precio_Entrada, LocalDate Fecha_Funcion, int Subtitulada, int Estado) {
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

    public LocalDate getFecha_Funcion() {
        return Fecha_Funcion;
    }

    public void setFecha_Funcion(LocalDate Fecha_Funcion) {
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
        return Objects.hashCode(this.id_Funcion);
    }

    
    @Override
    public boolean equals(Object a){
        if(a == this){
            return true;
        }
        if(a.getClass() != this.getClass() || this.id_Funcion == 0){
            return false;
        }
        Funcion funci = (Funcion) a;
        return  funci.id_Funcion == this.id_Funcion;
    }

    @Override
    public String toString() {
        return "Funcion{" + "id_Funcion=" + id_Funcion + ", id_pelicula=" + id_pelicula + ", nro_Sala=" + nro_Sala + ", idioma=" + idioma + ", es3D=" + es3D + ", Hora_Inicio=" + Hora_Inicio + ", Hora_Fin=" + Hora_Fin + ", Precio_Entrada=" + Precio_Entrada + ", Fecha_Funcion=" + Fecha_Funcion + ", Subtitulada=" + Subtitulada + ", Estado=" + Estado + '}';
    }
    
    
    
    
}
