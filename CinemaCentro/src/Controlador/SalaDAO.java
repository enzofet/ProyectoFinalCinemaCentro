/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Achucarro
 */
public class SalaDAO {

    public void agregarSala(Sala sala) throws Exception {
        String sql = "INSERT INTO sala (nro_sala,capacidad,estado,apta3D) VALUES (?,?,?,?)";
        Connection conn = ConexionBD.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, sala.getNro_Sala());
            ps.setInt(2, sala.getCapacidad());
            ps.setBoolean(3, sala.isEstado());
            ps.setBoolean(4, sala.isApta3D());

            int filas = ps.executeUpdate();
             if (filas == 0) {
                throw new Exception("No se pudo agregar la sala");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void eliminarSala(int nro_Sala) throws Exception {
        String sql = "DELETE FROM sala WHERE nro_sala = ? ";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nro_Sala);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new Exception("No se pudo encontrar la sala.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }

    }

    public void actualizarSala(int nro_sala, Sala sala) throws Exception {
        String sql = "UPDATE sala SET capacidad=? , estado=?,apta3D=? WHERE nro_sala = ?";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sala.getCapacidad());
            ps.setBoolean(2, sala.isEstado());
            ps.setBoolean(3, sala.isApta3D());
            ps.setInt(4, sala.getNro_Sala());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new Exception("No se pudo actualizar la sala");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");

        }
    }

    public Sala buscarSala(int nro_sala) throws Exception {
        String sql = "SELECT * FROM sala WHERE nro_sala = ?";
        Connection conn = ConexionBD.getConnection();

        Sala sala = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nro_sala);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    sala = new Sala();
                    sala.setNro_Sala(rs.getInt("nro_sala"));
                    sala.setCapacidad(rs.getInt("capacidad"));
                    sala.setEstado(rs.getBoolean("estado"));
                    sala.setApta3D(rs.getBoolean("apta3D"));
                } else {
                    throw new Exception("No se a encontrado la sala");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return sala;
    }

    public List<Sala> listarsalas() throws Exception {
        String sql = "SELECT * FROM sala";
        Connection conn = ConexionBD.getConnection();
        Sala sala = null;

        List<Sala> listarsalas = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sala = new Sala();
                    sala.setNro_Sala(rs.getInt("nro_sala"));
                    sala.setCapacidad(rs.getInt("capacidad"));
                    sala.setEstado(rs.getBoolean("estado"));
                    sala.setApta3D(rs.getBoolean("apta3D"));
                    listarsalas.add(sala);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listarsalas;
    }

    public void darAltaSala(int nro_sala) throws Exception {
        String sql = "UPDATE sala SET estado = true WHERE nro_sala = ?";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nro_sala);
            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al dar de alta al cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void darBajaSala(int nro_sala) throws Exception {
        String sql = "UPDATE sala SET estado = false WHERE nro_sala = ?";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nro_sala);
            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("no se pudo dar de baja la sala");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

}
