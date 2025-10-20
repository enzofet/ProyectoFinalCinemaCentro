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
 * @author Enzo_2
 */
public class Cliente {
    
    private int id_cliente;
    private int dni;
    private LocalDate fecha_nac;
    private String nombre;
    private String apellido;
    
    public Cliente(){
        this.id_cliente=-1;
    }
    
    public Cliente(int dni, LocalDate fecha_nac, String nombre, String apellido){
        this.id_cliente = -1;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public Cliente(int id_cliente, int dni, LocalDate fecha_nac, String nombre, String apellido){
        this.id_cliente = id_cliente;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    @Override
    public boolean equals(Object a) {
        if(a == this){
            return true;
        }
        if(a.getClass() != this.getClass() || this.dni == 0){
            return false;
        }
        Cliente cliente = (Cliente) a;
        return cliente.dni == this.dni;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id_cliente);
    }

    @Override
    public String toString() {
        return "Cliente: \n" + "\tid_cliente: " + id_cliente + "\n \tDNI: " + dni  + "\n \tNombre: " + nombre + "\n \tApellido: " + apellido + "\n \tFecha de nacimiento: " + fecha_nac;
    }

    
    
    
}
