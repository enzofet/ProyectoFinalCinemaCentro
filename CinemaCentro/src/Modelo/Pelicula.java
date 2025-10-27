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
public class Pelicula {
    private int id_Pelicula;
    private String Titulo;
    private String Director;
    private String Reparto;
    private String Pais_Origen;
    private String Genero;
    private boolean enCartelera;
    private LocalDate Estreno;
    private boolean Estado;

    public Pelicula() {
        this.id_Pelicula=-1;
    }


    public Pelicula(int id_Pelicula, String Titulo, String Director, String Reparto, String Pais_Origen, String Genero, boolean enCartelera, LocalDate Estreno, boolean Estado) {
        this.id_Pelicula = id_Pelicula;
        this.Titulo = Titulo;
        this.Director = Director;
        this.Reparto = Reparto;
        this.Pais_Origen = Pais_Origen;
        this.Genero = Genero;
        this.enCartelera = enCartelera;
        this.Estreno = Estreno;
        this.Estado = Estado;
    }

    public Pelicula(String Titulo, String Director, String Reparto, String Pais_Origen, String Genero, boolean enCartelera, LocalDate Estreno, boolean Estado) {
        this.Titulo = Titulo;
        this.Director = Director;
        this.Reparto = Reparto;
        this.Pais_Origen = Pais_Origen;
        this.Genero = Genero;
        this.enCartelera = enCartelera;
        this.Estreno = Estreno;
        this.Estado = Estado;
    }

    public int getId_Pelicula() {
        return id_Pelicula;
    }

    public void setId_Pelicula(int id_Pelicula) {
        this.id_Pelicula = id_Pelicula;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public String getReparto() {
        return Reparto;
    }

    public void setReparto(String Reparto) {
        this.Reparto = Reparto;
    }

    public String getPais_Origen() {
        return Pais_Origen;
    }

    public void setPais_Origen(String Pais_Origen) {
        this.Pais_Origen = Pais_Origen;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public boolean isEnCartelera() {
        return enCartelera;
    }

    public void setEnCartelera(boolean enCartelera) {
        this.enCartelera = enCartelera;
    }

    

    public LocalDate getEstreno() {
        return Estreno;
    }

    public void setEstreno(LocalDate Estreno) {
        this.Estreno = Estreno;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    

   
    @Override
    public int hashCode() {
        
        return Objects.hashCode(this.id_Pelicula);
    }

    @Override
    public boolean equals(Object a){
        if(a == this){
            return true;
        }
        if(a.getClass() != this.getClass() || this.id_Pelicula == 0){
            return false;
        }
        Pelicula peli = (Pelicula) a;
        return  peli.id_Pelicula == this.id_Pelicula;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id_Pelicula=" + id_Pelicula + ", Titulo=" + Titulo + ", Director=" + Director + ", Reparto=" + Reparto + ", Pais_Origen=" + Pais_Origen + ", Genero=" + Genero + ", enCartelera=" + enCartelera + ", Estreno=" + Estreno + ", Estado=" + Estado + '}';
    }

}
