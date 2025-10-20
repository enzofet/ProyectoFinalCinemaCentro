/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Naiara
 */
public class Pelicula {
     private int Id_Pelicula;
     private String Titulo;
     private String Director;
     private String Reparto;
     private String Pais_Origen;
     private String Genero;
     private int EnCartelera;
     private Date Estreno;
     private boolean Estado;

    public Pelicula() {
        this.Id_Pelicula = -1;
    }

    public Pelicula(int Id_Pelicula, String Titulo, String Director, String Reparto, String Pais_Origen, String Genero, int EnCartelera, Date Estreno, boolean Estado) {
        this.Id_Pelicula = Id_Pelicula;
        this.Titulo = Titulo;
        this.Director = Director;
        this.Reparto = Reparto;
        this.Pais_Origen = Pais_Origen;
        this.Genero = Genero;
        this.EnCartelera = EnCartelera;
        this.Estreno = Estreno;
        this.Estado = Estado;
    }

    public Pelicula(String Titulo, String Director, String Reparto, String Pais_Origen, String Genero, int EnCartelera, Date Estreno, boolean Estado) {
        this.Titulo = Titulo;
        this.Director = Director;
        this.Reparto = Reparto;
        this.Pais_Origen = Pais_Origen;
        this.Genero = Genero;
        this.EnCartelera = EnCartelera;
        this.Estreno = Estreno;
        this.Estado = Estado;
    }

    public int getId_Pelicula() {
        return Id_Pelicula;
    }

    public void setId_Pelicula(int Id_Pelicula) {
        this.Id_Pelicula = Id_Pelicula;
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

    public int getEnCartelera() {
        return EnCartelera;
    }

    public void setEnCartelera(int EnCartelera) {
        this.EnCartelera = EnCartelera;
    }

    public Date getEstreno() {
        return Estreno;
    }

    public void setEstreno(Date Estreno) {
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
        int hash = 7;
        hash = 29 * hash + this.Id_Pelicula;
        hash = 29 * hash + Objects.hashCode(this.Titulo);
        hash = 29 * hash + Objects.hashCode(this.Director);
        hash = 29 * hash + Objects.hashCode(this.Reparto);
        hash = 29 * hash + Objects.hashCode(this.Pais_Origen);
        hash = 29 * hash + Objects.hashCode(this.Genero);
        hash = 29 * hash + this.EnCartelera;
        hash = 29 * hash + Objects.hashCode(this.Estreno);
        hash = 29 * hash + (this.Estado ? 1 : 0);
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
        final Pelicula other = (Pelicula) obj;
        if (this.Id_Pelicula != other.Id_Pelicula) {
            return false;
        }
        if (this.EnCartelera != other.EnCartelera) {
            return false;
        }
        if (this.Estado != other.Estado) {
            return false;
        }
        if (!Objects.equals(this.Titulo, other.Titulo)) {
            return false;
        }
        if (!Objects.equals(this.Director, other.Director)) {
            return false;
        }
        if (!Objects.equals(this.Reparto, other.Reparto)) {
            return false;
        }
        if (!Objects.equals(this.Pais_Origen, other.Pais_Origen)) {
            return false;
        }
        if (!Objects.equals(this.Genero, other.Genero)) {
            return false;
        }
        if (!Objects.equals(this.Estreno, other.Estreno)) {
            return false;
        }
        return true;
    }
     
     
     
     
}
