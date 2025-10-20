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
public class Asiento {
    
    private int id_asiento;
    private int numero_asiento;
    private char fila_asiento;
    private boolean estado;
    
    public Asiento(){
        this.id_asiento = -1;
    }

    public Asiento(int id_asiento, int numero_asiento, char fila_asiento, boolean estado) {
        this.id_asiento = id_asiento;
        this.numero_asiento = numero_asiento;
        this.fila_asiento = fila_asiento;
        this.estado = estado;
    }
    
    public Asiento(int numero_asiento, char fila_asiento, boolean estado){
        this.id_asiento = id_asiento;
        this.numero_asiento = numero_asiento;
        this.fila_asiento = fila_asiento;
        this.estado = estado;
    }

    public int getId_asiento() {
        return id_asiento;
    }

    public void setId_asiento(int id_asiento) {
        this.id_asiento = id_asiento;
    }

    public int getNumero_asiento() {
        return numero_asiento;
    }

    public void setNumero_asiento(int numero_asiento) {
        this.numero_asiento = numero_asiento;
    }

    public char getFila_asiento() {
        return fila_asiento;
    }

    public void setFila_asiento(char fila_asiento) {
        this.fila_asiento = fila_asiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public boolean equals(Object a){
        if(this == a){
            return true;
        }
        if(this.getClass() != a.getClass() || this.id_asiento == 0){
            return false;
        }
        Asiento asiento = (Asiento)a;
        return asiento.id_asiento == this.id_asiento;
    }
    
    public int HashCode(){
        return Objects.hash(id_asiento);
    }

    @Override
    public String toString() {
        return "Asiento: \n" + "Id_asiento: " + id_asiento + "\n \tNúmero Asiento: " + numero_asiento + "\n \tFila Asiento: " + fila_asiento + "\n \tEstado: " + estado;
    }
    
    
}
