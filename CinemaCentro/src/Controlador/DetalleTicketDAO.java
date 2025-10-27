/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DetalleTicket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Gonzalo Achucarro
 */
public class DetalleTicketDAO {
    
    public void generarTicket(DetalleTicket ticket) throws Exception{
        String sql = "INSERT INTO detalleticket(id_funcion, id_asiento, id_venta, fecha_emision, estado)"
                + " VALUES (?, ?, ?, ?, ?)";
        
        Connection con = ConexionBD.getConnection();
        
        try(PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, ticket.getId_funcion());
            ps.setInt(2, ticket.getId_asiento());
            ps.setInt(3, ticket.getId_venta());
            ps.setDate(4, Date.valueOf(ticket.getFecha_emision()));
            ps.setBoolean(5, ticket.isEstado());
            
            int fila = ps.executeUpdate();
            if(fila == 0){
                throw new Exception("Error al generar ticket.");
            }
            
        } catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    public Integer generarToken(){
        String sql = "SELECT token FROM venta WHERE medio_compra = 'Online'";
        Connection con = ConexionBD.getConnection();
        Integer token = null;
        ArrayList<Integer> tokensCreados = null;
        try(PreparedStatement ps = con.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                int tokenPrev = (int) (Math.random()*90000000);
                token = tokenPrev;
                while(rs.next()){
                    Integer tokenBD = rs.getInt("token");
                    tokensCreados.add(tokenBD);
                    if(tokensCreados.contains(tokenBD)){
                        tokenPrev = (int) (Math.random()*90000000);
                        token = tokenPrev;
                    }
                }
                
            }
        } catch (SQLException e){
        }
        return token;
    }
    
    public ArrayList<DetalleTicket> listarTicketsPorVenta(int id_venta) throws Exception{
        String sql = "SELECT * FROM detalleticket WHERE id_venta = ?";
        Connection con = ConexionBD.getConnection();
        ArrayList<DetalleTicket> listaTickets = null;
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_venta);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    DetalleTicket ticket = new DetalleTicket();
                    ticket.setId_ticket(rs.getInt("id_ticket"));
                    ticket.setId_funcion(rs.getInt("id_funcion"));
                    ticket.setId_asiento(rs.getInt("id_asiento"));
                    ticket.setId_venta(rs.getInt("id_venta"));
                    ticket.setFecha_emision(rs.getDate("fecha_emision").toLocalDate());
                    ticket.setEstado(rs.getBoolean("estado"));
                    listaTickets.add(ticket);
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listaTickets;
    }
}
