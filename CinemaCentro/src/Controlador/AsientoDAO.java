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
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Gonzalo Achucarro
 */
public class AsientoDAO {

    public void agregarAsiento(Asiento asiento) {
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
            ps.setBoolean(3, asiento.isEstado());
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

    public List<Asiento> listarAsientosPorSala(int nro_sala) {
        String sql = "SELECT * FROM asiento WHERE nro_sala = ?";
        Connection con = ConexionBD.getConnection();
        List<Asiento> listaPorSala = new ArrayList<>();
        Asiento asiento = null;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nro_sala);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                asiento = new Asiento();
                asiento.setId_asiento(rs.getInt("id_asiento"));
                asiento.setFila_asiento(rs.getString("fila_asiento").charAt(0));
                asiento.setNumero_asiento(rs.getInt("numero_asiento"));
                asiento.setEstado(rs.getBoolean("estado"));
                listaPorSala.add(asiento);
                }   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPorSala;
    }

    public void eliminarAsiento(int id) {

        String sql = "DELETE FROM asiento WHERE id_asiento = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Se eliminó con éxito el asiento:" + id);
            } else {
                System.out.println("No se encontró el asiento: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Asiento> BuscarAsientosDisp(int nro_sala) {
        String sql = "SELECT * FROM asiento WHERE nro_sala = ? AND estado = true";
        List<Asiento> listaDisponibles = new ArrayList<>();
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    
                }
            }
        } catch (SQLException e) {

        }

        return listaDisponibles;

    }
}
