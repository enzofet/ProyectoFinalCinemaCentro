/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Venta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Gonzalo Achucarro
 */
public class VentaDAO {
    public void agregarVenta(Venta venta) throws Exception{
        String sql= "INSERT INTO venta (id_cliente, medio_pago, cantidad_entradas,"
                    + "importe_total, medio_compra, token, fecha_venta)VALUES(?,?,?,?,?,?,?)";
        Connection con = ConexionBD.getConnection();
    try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS )){
        ps.setInt(1,venta.getId_Cliente());
        ps.setString(2, venta.getMedio_Pago());
        ps.setInt(3,venta.getCantidad_Entradas());
        ps.setDouble(4, venta.getImporte_Total());
        ps.setString(5, venta.getMedio_Compra());
        ps.setInt(6, venta.getToken());
        ps.setDate(7, Date.valueOf(venta.getFecha_Venta()));
          ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                venta.setId_Venta(1);
                System.out.println("Venta cargada con éxito.");
            } else {
                System.out.println("Error al intentar cargar la venta.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
          
    }


