/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Venta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Gonzalo Achucarro
 */
public class VentaDAO {
    public void registrarVentaTaquilla(Venta venta) throws Exception{
        String sql = "INSERT INTO venta(id_cliente, medio_pago, cantidad_entradas, importe_total"
                + " medio_compra, fecha_venta) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection con =ConexionBD.getConnection();
        
        try(PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, venta.getId_Cliente());
            ps.setString(2, venta.getMedio_Pago());
            ps.setInt(3, venta.getCantidad_Entradas());
            ps.setDouble(4, venta.getImporte_Total());
            ps.setString(5, venta.getMedio_Pago());
            
            int fila = ps.executeUpdate();
            
            if(fila == 0){
                throw new Exception("Error al registrar la venta.");
            } 
            
        } catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    public void registrarVentaOnline(Venta venta) throws Exception{
        String sql = "INSERT INTO venta(id_cliente, medio_pago, cantidad_entradas, importe_total"
                + " medio_compra, token, fecha_venta) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection con =ConexionBD.getConnection();
        
        try(PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, venta.getId_Cliente());
            ps.setString(2, venta.getMedio_Pago());
            ps.setInt(3, venta.getCantidad_Entradas());
            ps.setDouble(4, venta.getImporte_Total());
            ps.setString(5, "Debito");
            ps.setNull(6, venta.getToken());
            
            int fila = ps.executeUpdate();
            
            if(fila == 0){
                throw new Exception("Error al registrar la venta.");
            } 
            
        } catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
        
    public void modificarVenta( Venta venta) throws Exception{
        String sql = "UPDATE venta SET id_cliente = ?, medio_pago = ?, cantidad_entradas = ?, importe_total = ?,"
                + " medio_compra = ?, token = ?, fecha_venta = ? WHERE id_venta = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, venta.getId_Cliente());
            ps.setString(2, venta.getMedio_Pago());
            ps.setInt(3, venta.getCantidad_Entradas());
            ps.setDouble(4, venta.getImporte_Total());
            ps.setString(5, venta.getMedio_Compra());
            ps.setInt(6, venta.getToken());
            ps.setDate(7, Date.valueOf(venta.getFecha_Venta()));
            
            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al modificar la venta.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
          
    
    public void darAlta(int id) throws Exception {
        String sql = "UPDATE pelicula SET estado = false WHERE id_pelicula = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int fila = ps.executeUpdate();

            if (fila == 0) {
                throw new Exception("Error al dar de alta pelicula.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void darBaja(int id) throws Exception {
        String sql = "UPDATE pelicula SET estado = true WHERE id_pelicula = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int fila = ps.executeUpdate();

            if (fila == 0) {
                throw new Exception("Error al dar de baja pelicula.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

  public void eliminarVenta(int id) throws Exception{
        String sql = "DELETE FROM venta WHERE id_Venta = ?";
        Connection conn = ConexionBD.getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if(filas == 0){
                throw new Exception("No se pudo eliminar la venta.");
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        } 
    }
  
public List<Venta> listarVenta() throws Exception{
        String sql = "SELECT * FROM venta";
        Connection conn = ConexionBD.getConnection();
       Venta venta = null;
        
        List<Venta> listaVenta = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                  venta = new Venta();
                  venta.setId_Venta(rs.getInt("id_venta"));
                  venta.setId_Cliente(rs.getInt("id_cliente"));
                  venta.setMedio_Pago(rs.getString("medio_pago"));
                  venta.setCantidad_Entradas(rs.getInt("cantidad_entradas"));
                  venta.setImporte_Total(rs.getDouble("importe_total"));
                  venta.setToken(rs.getInt("token"));
            
                  
                  
                    listaVenta.add(venta);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listaVenta;
    }
}

