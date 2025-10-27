package Modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Naiara
 */
public class Venta {
    private int id_venta;
    private int id_cliente;
    private String medio_pago;
    private int cantidad_entradas;
    private double importe_total;
    private String medio_compra;
    private Integer token;
    private LocalDate fecha_venta;

    public Venta() {
        this.id_venta=-1;
        this.id_cliente=-1;
    }

    public Venta(int id_venta, int id_cliente, String medio_pago, int cantidad_entradas, double importe_total, String medio_compra, LocalDate fecha_venta) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.medio_pago = medio_pago;
        this.cantidad_entradas = cantidad_entradas;
        this.importe_total = importe_total;
        this.medio_compra = medio_compra;
        this.fecha_venta = fecha_venta;
    }
    
    

    public Venta(int id_Venta, int id_Cliente, String Medio_Pago, int Cantidad_Entradas, double Importe_Total, String Medio_Compra, int Token, LocalDate fecha_venta) {
        this.id_venta = id_Venta;
        this.id_cliente = id_Cliente;
        this.medio_pago = Medio_Pago;
        this.cantidad_entradas = Cantidad_Entradas;
        this.importe_total = Importe_Total;
        this.medio_compra = Medio_Compra;
        this.token = Token;
        this.fecha_venta = fecha_venta;
    }

    public Venta(String Medio_Pago, int Cantidad_Entradas, double Importe_Total, String Medio_Compra, int Token, LocalDate fecha_venta) {
        this.medio_pago = Medio_Pago;
        this.cantidad_entradas = Cantidad_Entradas;
        this.importe_total = Importe_Total;
        this.medio_compra = Medio_Compra;
        this.token = Token;
        this.fecha_venta = fecha_venta;
    }

    public int getId_Venta() {
        return id_venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_venta = id_Venta;
    }

    public int getId_Cliente() {
        return id_cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_cliente = id_Cliente;
    }

    public String getMedio_Pago() {
        return medio_pago;
    }

    public void setMedio_Pago(String Medio_Pago) {
        this.medio_pago = Medio_Pago;
    }

    public int getCantidad_Entradas() {
        return cantidad_entradas;
    }

    public void setCantidad_Entradas(int Cantidad_Entradas) {
        this.cantidad_entradas = Cantidad_Entradas;
    }

    public double getImporte_Total() {
        return importe_total;
    }

    public void setImporte_Total(double Importe_Total) {
        this.importe_total = Importe_Total;
    }

    public String getMedio_Compra() {
        return medio_compra;
    }

    public void setMedio_Compra(String Medio_Compra) {
        this.medio_compra = Medio_Compra;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int Token) {
        this.token = Token;
    }

    public LocalDate getFecha_Venta() {
        return fecha_venta;
    }

    public void setFecha_Venta(LocalDate Fecha_Venta) {
        this.fecha_venta = Fecha_Venta;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id_venta);
    }

    public boolean equals(Object a){
        if(a == this){
            return true;
        }
        if(a.getClass() != this.getClass() || this.id_venta == 0){
            return false;
        }
        Venta vent = (Venta) a;
        return vent.id_venta  == this.id_venta;
    }

    @Override
    public String toString() {
        return "Venta{" + "id_Venta=" + id_venta + ", id_Cliente=" + id_cliente + ", Medio_Pago=" + medio_pago + ", Cantidad_Entradas=" + cantidad_entradas + ", Importe_Total=" + importe_total + ", Medio_Compra=" + medio_compra + ", Token=" + token + ", Fecha_Venta=" + fecha_venta + '}';
    }
    
    
    
}