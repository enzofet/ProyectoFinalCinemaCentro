package Modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Naiara
 */
public class Venta {
    private int id_venta;
    private Cliente cliente;
    private String medio_pago;
    private int cantidad_entradas;
    private double importe_total;
    private String medio_compra;
    private Integer token;
    private LocalDate fecha_venta;

    public Venta() {
        this.id_venta=-1;
        this.token = null;
        
    }

    public Venta(int id_venta, Cliente cliente, String medio_pago, int cantidad_entradas, double importe_total, String medio_compra, LocalDate fecha_venta) {
        this.id_venta = id_venta;
        this.cliente = cliente;
        this.medio_pago = medio_pago;
        this.cantidad_entradas = cantidad_entradas;
        this.importe_total = importe_total;
        this.medio_compra = medio_compra;
        this.fecha_venta = fecha_venta;
    }
    
    

    public Venta(int id_Venta, Cliente Cliente, String Medio_Pago, int Cantidad_Entradas, double Importe_Total, String Medio_Compra, int Token, LocalDate fecha_venta) {
        this.id_venta = id_Venta;
        this.cliente = Cliente;
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

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public int getCantidad_entradas() {
        return cantidad_entradas;
    }

    public void setCantidad_entradas(int cantidad_entradas) {
        this.cantidad_entradas = cantidad_entradas;
    }

    public double getImporte_total() {
        return importe_total;
    }

    public void setImporte_total(double importe_total) {
        this.importe_total = importe_total;
    }

    public String getMedio_compra() {
        return medio_compra;
    }

    public void setMedio_compra(String medio_compra) {
        this.medio_compra = medio_compra;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
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
        return "Venta{" + "id_Venta=" + id_venta + ", id_Cliente=" + cliente + ", Medio_Pago=" + medio_pago + ", Cantidad_Entradas=" + cantidad_entradas + ", Importe_Total=" + importe_total + ", Medio_Compra=" + medio_compra + ", Token=" + token + ", Fecha_Venta=" + fecha_venta + '}';
    }
    
    
    
}