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
public class Venta {
    private int id_Venta;
    private int id_Cliente;
    private String Medio_Pago;
    private int Cantidad_Entradas;
    private double Importe_Total;
    private String Medio_Compra;
    private int Token;
    private Date Fecha_Venta;

    public Venta() {
        this.id_Venta=-1;
        this.id_Cliente=-1;
    }

    public Venta(int id_Venta, int id_Cliente, String Medio_Pago, int Cantidad_Entradas, double Importe_Total, String Medio_Compra, int Token, Date Fecha_Venta) {
        this.id_Venta = id_Venta;
        this.id_Cliente = id_Cliente;
        this.Medio_Pago = Medio_Pago;
        this.Cantidad_Entradas = Cantidad_Entradas;
        this.Importe_Total = Importe_Total;
        this.Medio_Compra = Medio_Compra;
        this.Token = Token;
        this.Fecha_Venta = Fecha_Venta;
    }

    public Venta(String Medio_Pago, int Cantidad_Entradas, double Importe_Total, String Medio_Compra, int Token, Date Fecha_Venta) {
        this.Medio_Pago = Medio_Pago;
        this.Cantidad_Entradas = Cantidad_Entradas;
        this.Importe_Total = Importe_Total;
        this.Medio_Compra = Medio_Compra;
        this.Token = Token;
        this.Fecha_Venta = Fecha_Venta;
    }

    public int getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_Venta = id_Venta;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getMedio_Pago() {
        return Medio_Pago;
    }

    public void setMedio_Pago(String Medio_Pago) {
        this.Medio_Pago = Medio_Pago;
    }

    public int getCantidad_Entradas() {
        return Cantidad_Entradas;
    }

    public void setCantidad_Entradas(int Cantidad_Entradas) {
        this.Cantidad_Entradas = Cantidad_Entradas;
    }

    public double getImporte_Total() {
        return Importe_Total;
    }

    public void setImporte_Total(double Importe_Total) {
        this.Importe_Total = Importe_Total;
    }

    public String getMedio_Compra() {
        return Medio_Compra;
    }

    public void setMedio_Compra(String Medio_Compra) {
        this.Medio_Compra = Medio_Compra;
    }

    public int getToken() {
        return Token;
    }

    public void setToken(int Token) {
        this.Token = Token;
    }

    public Date getFecha_Venta() {
        return Fecha_Venta;
    }

    public void setFecha_Venta(Date Fecha_Venta) {
        this.Fecha_Venta = Fecha_Venta;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id_Venta);
    }

    public boolean equals(Object a){
        if(a == this){
            return true;
        }
        if(a.getClass() != this.getClass() || this.id_Venta == 0){
            return false;
        }
        Venta vent = (Venta) a;
        return vent.id_Venta  == this.id_Venta;
    }

    @Override
    public String toString() {
        return "Venta{" + "id_Venta=" + id_Venta + ", id_Cliente=" + id_Cliente + ", Medio_Pago=" + Medio_Pago + ", Cantidad_Entradas=" + Cantidad_Entradas + ", Importe_Total=" + Importe_Total + ", Medio_Compra=" + Medio_Compra + ", Token=" + Token + ", Fecha_Venta=" + Fecha_Venta + '}';
    }
    
    
    
}
