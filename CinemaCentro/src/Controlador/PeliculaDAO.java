/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Pelicula;
import java.sql.Connection;
import java.sql.Date;
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
public class PeliculaDAO {

    public void agregarPelicula(Pelicula peli) throws Exception {
        String sql = "INSERT INTO pelicula(titulo, director, reparto, pais_origen, genero, "
                + "enCartelera,estreno, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, peli.getTitulo());
            ps.setString(2, peli.getDirector());
            ps.setString(3, peli.getReparto());
            ps.setString(4, peli.getPais_Origen());
            ps.setString(5, peli.getGenero());
            ps.setBoolean(6, peli.isEnCartelera());
            ps.setDate(7, Date.valueOf(peli.getEstreno()));
            ps.setBoolean(8, peli.isEstado());

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al agregar pelicula.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void eliminarPelicula(int id) throws Exception {
        String sql = "DELETE FROM pelicula WHERE id_pelicula = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al eliminar la pelicula");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void modificarPelicula(int id, Pelicula peli) throws Exception {
        String sql = "UPDATE pelicula SET titulo = ?, director = ?, reparto = ?, pais_origen = ?,"
                + " genero = ?, enCartelera = ?, estreno = ?, estado = ? WHERE id_pelicula = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, peli.getTitulo());
            ps.setString(2, peli.getDirector());
            ps.setString(3, peli.getReparto());
            ps.setString(4, peli.getPais_Origen());
            ps.setString(5, peli.getGenero());
            ps.setBoolean(6, peli.isEnCartelera());
            ps.setDate(7, Date.valueOf(peli.getEstreno()));
            ps.setBoolean(8, peli.isEstado());
            ps.setInt(9, id);
            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al modificar pelicula.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void darAlta(int id) throws Exception {
        String sql = "UPDATE pelicula SET estado = true WHERE id_pelicula = ?";
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
        String sql = "UPDATE pelicula SET estado = false, enCartelera = false WHERE id_pelicula = ?";
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

    public List<Pelicula> listarTodasPeliculas() throws Exception {
        String sql = "SELECT * FROM pelicula";
        Connection con = ConexionBD.getConnection();
        List<Pelicula> listaPeliculas = new ArrayList<>();
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Pelicula peli = new Pelicula();
                    peli.setId_Pelicula(rs.getInt("id_pelicula"));
                    peli.setTitulo(rs.getString("titulo"));
                    peli.setDirector(rs.getString("director"));
                    peli.setReparto(rs.getString("reparto"));
                    peli.setPais_Origen(rs.getString("pais_origen"));
                    peli.setGenero(rs.getString("genero"));
                    peli.setEnCartelera(rs.getBoolean("enCartelera"));
                    peli.setEstreno(rs.getDate("estreno").toLocalDate());
                    peli.setEstado(rs.getBoolean("estado"));
                    listaPeliculas.add(peli);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        
        return listaPeliculas;
    }
    
    public Pelicula buscarPorId(int id) throws Exception{
        String sql = "SELECT * FROM pelicula WHERE id_pelicula = ?";
        Connection con = ConexionBD.getConnection();
        Pelicula peli = null;
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    peli = new Pelicula();
                    peli.setId_Pelicula(rs.getInt("id_pelicula"));
                    peli.setTitulo(rs.getString("titulo"));
                    peli.setDirector(rs.getString("director"));
                    peli.setReparto(rs.getString("reparto"));
                    peli.setPais_Origen(rs.getString("pais_origen"));
                    peli.setGenero(rs.getString("genero"));
                    peli.setEnCartelera(rs.getBoolean("enCartelera"));
                    peli.setEstreno(rs.getDate("estreno").toLocalDate());
                    peli.setEstado(rs.getBoolean("estado"));
                } else {
                    throw new Exception("No se ha encontrado la pelicula.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return peli;
    }
    
    public List<Pelicula> listarPeliculasEnCartelera(){
        String sql = "SELECT * FROM pelicula WHERE enCartelera = true";
        Connection con = ConexionBD.getConnection();
        List<Pelicula> listaCartelera = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Pelicula peli = new Pelicula();
                    peli.setId_Pelicula(rs.getInt("id_pelicula"));
                    peli.setTitulo(rs.getString("titulo"));
                    peli.setDirector(rs.getString("director"));
                    peli.setReparto(rs.getString("reparto"));
                    peli.setPais_Origen(rs.getString("pais_origen"));
                    peli.setGenero(rs.getString("genero"));
                    peli.setEnCartelera(rs.getBoolean("enCartelera"));
                    peli.setEstreno(rs.getDate("estreno").toLocalDate());
                    peli.setEstado(rs.getBoolean("estado"));
                    listaCartelera.add(peli);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listaCartelera;
    }
    
}