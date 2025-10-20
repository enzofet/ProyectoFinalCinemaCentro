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
    private int Id_Venta;
    private int Id_Cliente;
    private String Medio_Pago;
    private int Cantidad_Entradas;
    private double Importe_Total;
    private String Medio_Compra;
    private int Token;
    private Date Fecha_Venta;

    public Venta() {
        this.Id_Venta=-1;
        this.Id_Cliente = -1;  
    }

    public Venta(int Id_Venta, int Id_Cliente, String Medio_Pago, int Cantidad_Entradas, double Importe_Total, String Medio_Compra, int Token, Date Fecha_Venta) {
        this.Id_Venta = Id_Venta;
        this.Id_Cliente = Id_Cliente;
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
        return Id_Venta;
    }

    public void setId_Venta(int Id_Venta) {
        this.Id_Venta = Id_Venta;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
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
        int hash = 7;
        hash = 89 * hash + this.Id_Venta;
        hash = 89 * hash + this.Id_Cliente;
        hash = 89 * hash + Objects.hashCode(this.Medio_Pago);
        hash = 89 * hash + this.Cantidad_Entradas;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.Importe_Total) ^ (Double.doubleToLongBits(this.Importe_Total) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.Medio_Compra);
        hash = 89 * hash + this.Token;
        hash = 89 * hash + Objects.hashCode(this.Fecha_Venta);
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
        final Venta other = (Venta) obj;
        if (this.Id_Venta != other.Id_Venta) {
            return false;
        }
        if (this.Id_Cliente != other.Id_Cliente) {
            return false;
        }
        if (this.Cantidad_Entradas != other.Cantidad_Entradas) {
            return false;
        }
        if (Double.doubleToLongBits(this.Importe_Total) != Double.doubleToLongBits(other.Importe_Total)) {
            return false;
        }
        if (this.Token != other.Token) {
            return false;
        }
        if (!Objects.equals(this.Medio_Pago, other.Medio_Pago)) {
            return false;
        }
        if (!Objects.equals(this.Medio_Compra, other.Medio_Compra)) {
            return false;
        }
        if (!Objects.equals(this.Fecha_Venta, other.Fecha_Venta)) {
            return false;
        }
        return true;
    }
    
    
    
}
