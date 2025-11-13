/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author emadupre
 */
public class ReporteVentas {

    private String pelicula;
    private String funcion;
    private String sala;
    private int ticketsVendidos;
    private double totalEfectivo;
    private double totalDebito;
    private int ticketsOnline;
    private int ticketsTaquilla;
    private double ocupacion;

    public ReporteVentas() {
    }

    public ReporteVentas(String pelicula, String funcion, String sala, int ticketsVendidos,
            double totalEfectivo, double totalDebito,
            int ticketsOnline, int ticketsTaquilla, double ocupacion) {
        this.pelicula = pelicula;
        this.funcion = funcion;
        this.sala = sala;
        this.ticketsVendidos = ticketsVendidos;
        this.totalEfectivo = totalEfectivo;
        this.totalDebito = totalDebito;
        this.ticketsOnline = ticketsOnline;
        this.ticketsTaquilla = ticketsTaquilla;
        this.ocupacion = ocupacion;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getTicketsVendidos() {
        return ticketsVendidos;
    }

    public void setTicketsVendidos(int ticketsVendidos) {
        this.ticketsVendidos = ticketsVendidos;
    }

    public double getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(double totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public double getTotalDebito() {
        return totalDebito;
    }

    public void setTotalDebito(double totalDebito) {
        this.totalDebito = totalDebito;
    }

    public int getTicketsOnline() {
        return ticketsOnline;
    }

    public void setTicketsOnline(int ticketsOnline) {
        this.ticketsOnline = ticketsOnline;
    }

    public int getTicketsTaquilla() {
        return ticketsTaquilla;
    }

    public void setTicketsTaquilla(int ticketsTaquilla) {
        this.ticketsTaquilla = ticketsTaquilla;
    }

    public double getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(double ocupacion) {
        this.ocupacion = ocupacion;
    }

    public double getTotalGeneral() {
        return totalEfectivo + totalDebito;
    }

    @Override
    public String toString() {
        return "ReporteVentas{"
                + "pelicula='" + pelicula + '\''
                + ", funcion='" + funcion + '\''
                + ", sala='" + sala + '\''
                + ", ticketsVendidos=" + ticketsVendidos
                + ", totalEfectivo=" + totalEfectivo
                + ", totalDebito=" + totalDebito
                + ", ticketsOnline=" + ticketsOnline
                + ", ticketsTaquilla=" + ticketsTaquilla
                + ", ocupacion=" + ocupacion
                + '}';
    }
}
