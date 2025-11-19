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
        String sql = "INSERT INTO asiento (numero_asiento, nro_sala, fila_asiento, estado) VALUES (?, ?, ?, ?)";
        Connection con = ConexionBD.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, asiento.getNumero_asiento());
            ps.setInt(2, asiento.getNro_sala());
            ps.setString(3, String.valueOf(asiento.getFila_asiento()));
            ps.setBoolean(4, asiento.isEstado());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                asiento.setId_asiento(1);
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
                while (rs.next()) {
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

    public void darAlta(int id) throws Exception {
        String sql = "UPDATE asiento SET estado = true WHERE id_asiento = ?";
        Connection con = ConexionBD.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al dar de alta el asiento");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }

    }

    public void darBaja(int id) throws Exception {
        String sql = "UPDATE asiento SET estado = false WHERE id_asiento = ?";
        Connection con = ConexionBD.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al dar de baja el asiento");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }

    }

    public void liberarAsientosPorSala(int id) throws Exception {
        String sql = "UPDATE asiento SET estado = true WHERE nro_sala = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filasS = ps.executeUpdate();
            if (filasS == 0) {
                throw new Exception("Error al dar de alta los asientos.");
            }
        } catch (SQLException e) {
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public int cantidadAsientosLibres(int nro_sala) throws Exception {
        String sql = "SELECT COUNT(estado) AS asientoslibres FROM asiento WHERE nro_sala = ? AND estado = true";
        Connection con = ConexionBD.getConnection();
        int cantidad = 0;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nro_sala);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cantidad = rs.getInt("asientoslibres");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return cantidad;
    }

    public boolean agregarListaAsientos(List<Asiento> listaAsientos) {
        String sql = "INSERT INTO asiento (numero_asiento, nro_sala, fila_asiento, estado) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConexionBD.getConnection();
            ps = con.prepareStatement(sql);
            for (Asiento a : listaAsientos) {
                ps.setInt(1, a.getNumero_asiento());
                ps.setInt(2, a.getNro_sala());
                ps.setString(3, String.valueOf(a.getFila_asiento()));
                ps.setBoolean(4, a.isEstado());
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException a) {
                a.printStackTrace();
            }
        }
    }

    public Asiento buscarPorFilaAsiento(int numero_asiento, int nro_sala, char fila_asiento) throws Exception {
        String sql = "SELECT * FROM asiento WHERE nro_sala = ? AND fila_asiento = ? AND numero_asiento = ?";
        Connection con = ConexionBD.getConnection();
        Asiento as = null;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nro_sala);
            ps.setString(2, String.valueOf(fila_asiento));
            ps.setInt(3, numero_asiento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    as = new Asiento();
                    as.setId_asiento(rs.getInt("id_asiento"));
                    as.setNro_sala(rs.getInt("nro_sala"));
                    as.setFila_asiento(rs.getString("fila_asiento").charAt(0));
                    as.setNumero_asiento(rs.getInt("numero_asiento"));
                    as.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error relacionado a la base de datos.");
        }
        return as;
    }

    public Asiento buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM asiento WHERE id_asiento= ?";
        Connection con = ConexionBD.getConnection();
        Asiento asiento = new Asiento();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    asiento = new Asiento();
                    asiento.setNro_sala(rs.getInt("nro_sala"));
                    asiento.setId_asiento(rs.getInt("id_asiento"));
                    asiento.setFila_asiento(rs.getString("fila_asiento").charAt(0));
                    asiento.setNumero_asiento(rs.getInt("numero_asiento"));
                    asiento.setEstado(rs.getBoolean("estado"));
                } else {
                    asiento = null;
                    throw new Exception("No se pudo encontrar el ticket");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return asiento;
    }

    public void liberarAsientos(List<Asiento> asientos) throws Exception {
        String sql = "UPDATE asiento SET estado = true WHERE id_asiento = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (Asiento asiento : asientos) {
                ps.setInt(1, asiento.getId_asiento());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos al liberar asientos.");
        }
    }
}
