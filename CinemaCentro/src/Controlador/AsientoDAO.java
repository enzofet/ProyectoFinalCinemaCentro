/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Asiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Gonzalo Achucarro
 */
public class AsientoDAO {
    public void agregarAsiento ( Asiento asiento) throws SQLException{
        String sql = "INSERT TO asiento ( nro_asiento, fila_asiento, estado) VALUES (?,?,?,?)";
        Connection con = ConexionBD.getConnection();
        
             try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, asiento.getNumero_asiento());
            ps.setInt(2, asiento.getFila_asiento());
            ps.setBoolean(3, asiento.isEstado());
           
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
               asiento.setId_asiento(1);
                System.out.println("Asiento creado con éxito");
            } else {
                System.out.println("Error al intentar crear asiento");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void actualizarAsiento(Asiento asiento) {
        String sql = "UPDATE administrativo SET  nro_asiento=?, fila_asiento = ?, estado = ? WHERE id_asiento= ?";
        Connection con = ConexionBD.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, asiento.getNumero_asiento());
            ps.setInt(2, asiento.getId_asiento());
            ps.setBoolean(3, true);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Actualización realizada con éxito del asiento : " + asiento.getId_asiento());
            } else {
                System.out.println("No se encontró asiento: " + asiento.getId_asiento());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
