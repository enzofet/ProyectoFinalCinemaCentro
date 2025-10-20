/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

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
    
    
}
