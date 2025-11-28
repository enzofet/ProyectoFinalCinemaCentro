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
    private Pelicula pelicula;
    private Sala sala;
    private String idioma;
    private boolean es3D;
    private Time Hora_Inicio;
    private Time Hora_Fin;
    private double Precio_Entrada;
    private LocalDate Fecha_Funcion;
    private boolean Subtitulada;
    private boolean Estado;

    public Funcion() {
        this.id_Funcion = -1;
    }

    public Funcion(int id_Funcion, Pelicula pelicula, Sala sala, String idioma, boolean es3D, Time Hora_Inicio, Time Hora_Fin, double Precio_Entrada, LocalDate Fecha_Funcion, boolean Subtitulada, boolean Estado) {
        this.id_Funcion = id_Funcion;
        this.pelicula = pelicula;
        this.sala = sala;
        this.idioma = idioma;
        this.es3D = es3D;
        this.Hora_Inicio = Hora_Inicio;
        this.Hora_Fin = Hora_Fin;
        this.Precio_Entrada = Precio_Entrada;
        this.Fecha_Funcion = Fecha_Funcion;
        this.Subtitulada = Subtitulada;
        this.Estado = Estado;
        //aca borré lo de descripcion que estaba al pedo.
    }

    public Funcion(Pelicula pelicula, Sala sala, String idioma, boolean es3D, Time Hora_Inicio, Time Hora_Fin, double Precio_Entrada, LocalDate Fecha_Funcion, boolean Subtitulada, boolean Estado, String descripcion) {
        this.pelicula = pelicula;
        this.sala = sala;
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

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isEs3D() {
        return es3D;
    }

    public void setEs3D(boolean es3D) {
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

    public double getPrecio_Entrada() {
        return Precio_Entrada;
    }

    public void setPrecio_Entrada(double Precio_Entrada) {
        this.Precio_Entrada = Precio_Entrada;
    }

    public LocalDate getFecha_Funcion() {
        return Fecha_Funcion;
    }

    public void setFecha_Funcion(LocalDate Fecha_Funcion) {
        this.Fecha_Funcion = Fecha_Funcion;
    }

    public boolean isSubtitulada() {
        return Subtitulada;
    }

    public void setSubtitulada(boolean Subtitulada) {
        this.Subtitulada = Subtitulada;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id_Funcion);
    }

    @Override
    public boolean equals(Object a) {
        if (a == this) {
            return true;
        }
        if (a.getClass() != this.getClass() || this.id_Funcion == 0) {
            return false;
        }
        Funcion funci = (Funcion) a;
        return funci.id_Funcion == this.id_Funcion;
    }

    @Override
    public String toString() {
        return "Funcion{" + "id_Funcion=" + id_Funcion + ", id_pelicula=" + pelicula + ", nro_Sala=" + sala + ", idioma=" + idioma + ", es3D=" + es3D + ", Hora_Inicio=" + Hora_Inicio + ", Hora_Fin=" + Hora_Fin + ", Precio_Entrada=" + Precio_Entrada + ", Fecha_Funcion=" + Fecha_Funcion + ", Subtitulada=" + Subtitulada + ", Estado=" + Estado + '}';
    }

}
