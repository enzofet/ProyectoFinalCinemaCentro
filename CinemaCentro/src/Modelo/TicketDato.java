/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Enzo_2
 */
public class TicketDato {
    private int idTicket;
    private String peliculaTitulo;
    private int nroSala;
    private String fechaFuncion;
    private String horaInicio;
    private String horaFin;
    private String asiento;
    private Integer DNI;
    private String nombreApellidoCliente;
    private String medioCompra;
    private String fechaEmision;
    private boolean estado;

    public TicketDato() {
    }
    
    

    public TicketDato(int idTicket, String peliculaTitulo, int nroSala, String fechaFuncion, String horaInicio, String horaFin, String asiento, Integer DNI, String nombreApellidoCliente, String medioCompra, String fechaEmision, boolean estado) {
        this.idTicket = idTicket;
        this.peliculaTitulo = peliculaTitulo;
        this.nroSala = nroSala;
        this.fechaFuncion = fechaFuncion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.asiento = asiento;
        this.DNI = DNI;
        this.nombreApellidoCliente = nombreApellidoCliente;
        this.medioCompra = medioCompra;
        this.fechaEmision = fechaEmision;
        this.estado = estado;
    }
    

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
        this.DNI = DNI;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    
    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getPeliculaTitulo() {
        return peliculaTitulo;
    }

    public void setPeliculaTitulo(String peliculaTitulo) {
        this.peliculaTitulo = peliculaTitulo;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public String getFechaFuncion() {
        return fechaFuncion;
    }

    public void setFechaFuncion(String fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public String getMedioCompra() {
        return medioCompra;
    }

    public void setMedioCompra(String medioCompra) {
        this.medioCompra = medioCompra;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
