/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Funcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Achucarro
 */
public class FuncionDAO {

    public void agregarFuncion(Funcion funcion) throws Exception {
        String sql = "INSERT INTO funcion (id_pelicula, nro_sala, idioma, es3D, hora_inicio, hora_fin, precio_entrada, "
                + "fecha_funcion, subtitulada, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, funcion.getId_pelicula());
            ps.setInt(2, funcion.getNro_Sala());
            ps.setString(3, funcion.getIdioma());
            ps.setBoolean(4, funcion.isEs3D());
            ps.setTime(5, funcion.getHora_Inicio());
            ps.setTime(6, funcion.getHora_Fin());
            ps.setDouble(7, funcion.getPrecio_Entrada());
            ps.setDate(8, Date.valueOf(funcion.getFecha_Funcion()));
            ps.setBoolean(9, funcion.isSubtitulada());
            ps.setBoolean(10, funcion.isEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                funcion.setId_Funcion(rs.getInt(1));
            } else {
                throw new Exception("Error al agregar una función nueva - no se generó ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agregar función: " + e.getMessage()
                    + ". Valores: id_pelicula=" + funcion.getId_pelicula());
        }
    }

    public void eliminarFuncion(int id) throws Exception {
        String sql = "DELETE FROM funcion WHERE id_funcion = ?";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new Exception("No se pudo eliminar la funcion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void actualizarFuncion(int id, Funcion funcion) throws Exception {
        String sql = "UPDATE funcion SET id_pelicula=?, nro_sala=?, idioma=?, es3D=?, hora_inicio=?, hora_fin=?, "
                + "precio_entrada=?, fecha_funcion=?, subtitulada=?, estado=? WHERE id_funcion=?";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, funcion.getId_pelicula());
            ps.setInt(2, funcion.getNro_Sala());
            ps.setString(3, funcion.getIdioma());
            ps.setBoolean(4, funcion.isEs3D());
            ps.setTime(5, funcion.getHora_Inicio());
            ps.setTime(6, funcion.getHora_Fin());
            ps.setDouble(7, funcion.getPrecio_Entrada());
            ps.setDate(8, Date.valueOf(funcion.getFecha_Funcion()));
            ps.setBoolean(9, funcion.isSubtitulada());
            ps.setBoolean(10, funcion.isEstado());
            ps.setInt(11, id);

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("No se pudo actualizar la función. No se encontró el registro.");
            } else {
                System.out.println("Función actualizada correctamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar función: " + e.getMessage());
        }
    }

    public Funcion buscarFuncionPorId(int id) throws Exception {
        String sql = "SELECT * FROM funcion WHERE id_funcion = ?";
        Connection conn = ConexionBD.getConnection();

        Funcion fun = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fun = new Funcion();
                    fun.setId_Funcion(rs.getInt("id_funcion"));
                    fun.setId_pelicula(rs.getInt("id_pelicula"));
                    fun.setNro_Sala(rs.getInt("nro_sala"));
                    fun.setIdioma(rs.getString("idioma"));
                    fun.setEs3D(rs.getBoolean("es3D"));
                    fun.setHora_Inicio(rs.getTime("hora_inicio"));
                    fun.setHora_Fin(rs.getTime("hora_fin"));
                    fun.setPrecio_Entrada(rs.getDouble("precio_entrada"));
                    fun.setFecha_Funcion(rs.getDate("fecha_funcion").toLocalDate());
                    fun.setSubtitulada(rs.getBoolean("subtitulada"));
                    fun.setEstado(rs.getBoolean("estado"));
                } else {
                    throw new Exception("No se ha encontrado la funcion");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return fun;
    }

    public List<Funcion> listarFunciones() throws Exception {
        String sql = "SELECT * FROM funcion";
        Connection conn = ConexionBD.getConnection();
        Funcion fun = null;

        List<Funcion> listafuncion = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fun = new Funcion();
                    fun.setId_Funcion(rs.getInt("id_funcion"));
                    fun.setId_pelicula(rs.getInt("id_pelicula"));
                    fun.setNro_Sala(rs.getInt("nro_sala"));
                    fun.setIdioma(rs.getString("idioma"));
                    fun.setEs3D(rs.getBoolean("es3D"));
                    fun.setHora_Inicio(rs.getTime("hora_inicio"));
                    fun.setHora_Fin(rs.getTime("hora_fin"));
                    fun.setPrecio_Entrada(rs.getDouble("precio_entrada"));
                    fun.setFecha_Funcion(rs.getDate("fecha_funcion").toLocalDate());
                    fun.setSubtitulada(rs.getBoolean("subtitulada"));
                    fun.setEstado(rs.getBoolean("estado"));
                    listafuncion.add(fun);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listafuncion;
    }

    public void darAltaFuncion(int id) throws Exception {
        String sql = "UPDATE funcion SET Estado = true WHERE id_funcion = ?";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al dar de alta la funcion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void darBajaFuncion(int id) throws Exception {
        String sql = "UPDATE funcion SET Estado = false WHERE id_funcion = ?";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al dar de baja la funcion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public ArrayList<Funcion> listadoPorId(int id) throws Exception {
        String sql = "SELECT * FROM funcion WHERE id_pelicula = ?";
        Connection con = ConexionBD.getConnection();
        ArrayList<Funcion> funcionesId = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Funcion fun = new Funcion();
                    fun.setId_Funcion(rs.getInt("id_funcion"));
                    fun.setId_pelicula(rs.getInt("id_pelicula"));
                    fun.setNro_Sala(rs.getInt("nro_sala"));
                    fun.setIdioma(rs.getString("idioma"));
                    fun.setEs3D(rs.getBoolean("es3D"));
                    fun.setHora_Inicio(rs.getTime("hora_inicio"));
                    fun.setHora_Fin(rs.getTime("hora_fin"));
                    fun.setPrecio_Entrada(rs.getDouble("precio_entrada"));
                    fun.setFecha_Funcion(rs.getDate("fecha_funcion").toLocalDate());
                    fun.setSubtitulada(rs.getBoolean("subtitulada"));
                    fun.setEstado(rs.getBoolean("estado"));
                    funcionesId.add(fun);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return funcionesId;
    }

    public Funcion buscarFuncion(int id_Pelicula, int nro_Sala, Time Hora_Inicio) throws Exception {
        for (Funcion f : listarFunciones()) {
            if (f.getId_pelicula() == id_Pelicula
                    && f.getNro_Sala() == nro_Sala
                    && f.getHora_Inicio().equals(Hora_Inicio)) {
                return f;
            }
        }
        return null;
    }
}
