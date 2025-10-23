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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Achucarro
 */
public class FuncionDAO {
    
    public void agregarFuncion(Funcion funcion) throws Exception{
        String sql= "INSERT INTO funcion (nro_Sala, idioma, es3D, hora_inicio, hora_fin, precio_entrada, " 
        + "fecha_funcion, subtitulada, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = ConexionBD.getConnection();
        
        try(PreparedStatement ps= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, funcion.getNro_Sala());
            ps.setString(2, funcion.getIdioma());
            ps.setInt(3, funcion.getEs3D());
            ps.setTime(4, funcion.getHora_Inicio());
            ps.setTime(5, funcion.getHora_Fin());
            ps.setDouble(6, funcion.getPrecio_Entrada());
            ps.setDate(7, Date.valueOf(funcion.getFecha_Funcion()));
            ps.setInt(8, funcion.getSubtitulada());
            ps.setInt(9, funcion.getEstado());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                funcion.setId_Funcion(rs.getInt(1));
            } else {
                throw new Exception("Error al agregar una función nueva.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    public void eliminarFuncion(int id) throws Exception{
        String sql = "DELETE FROM funcion WHERE id_Funcion = ?";
        Connection conn = ConexionBD.getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if(filas == 0){
                throw new Exception("No se pudo eliminar la funcion.");
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        } 
    }
    
    public void actualizarFuncion(int id, Funcion funcion) throws Exception{
        String sql = "UPDATE funcion SET nro_Sala= ?, idioma= ?, es3D = ?, Hora_Inicio = ?, Hora_Fin=?, "
            + " Precio_Entrada=?, Fecha_Funcion =?, Subtitulada =?, Estado =? WHERE id_Funcion=?";
        Connection conn = ConexionBD.getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, funcion.getNro_Sala());
            ps.setString(2, funcion.getIdioma());
            ps.setInt(3, funcion.getEs3D());
            ps.setTime(4, funcion.getHora_Inicio());
            ps.setTime(5, funcion.getHora_Fin());
            ps.setDouble(6, funcion.getPrecio_Entrada());
            ps.setDate(7, Date.valueOf(funcion.getFecha_Funcion()));
            ps.setInt(8, funcion.getSubtitulada());
            ps.setInt(9, funcion.getEstado());
            ps.setInt(10, funcion.getId_Funcion());
            
            int filas = ps.executeUpdate();
            if(filas == 0){
                throw new Exception("No se pudo registrar la funcion.");
            }  
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    
    public Funcion buscarFuncionPorId(int id) throws Exception{
        String sql = "SELECT * FROM funcion WHERE id_Funcion = ?";
        Connection conn = ConexionBD.getConnection();
        
        Funcion fun = null;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    fun = new Funcion();
                    fun.setId_Funcion(rs.getInt("id_Funcion"));
                    fun.setNro_Sala(rs.getInt("nro_Sala"));
                    fun.setIdioma(rs.getString("idioma"));
                    fun.setEs3D(rs.getInt("es3D"));
                    fun.setHora_Inicio(rs.getTime("Hora_Inicio"));
                    fun.setHora_Fin(rs.getTime("Hora_Fin"));
                    fun.setPrecio_Entrada(rs.getDouble("Precio_Entrada"));
                    fun.setFecha_Funcion(rs.getDate("Fecha_Funcion").toLocalDate());
                    fun.setSubtitulada(rs.getInt("Subtitulada"));
                    fun.setEstado(rs.getInt("Estado"));
                }else{
                    throw new Exception("No se ha encontrado la funcion");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return fun;
    }
    
    public List<Funcion> listarFunciones() throws Exception{
        String sql = "SELECT * FROM funcion";
        Connection conn = ConexionBD.getConnection();
        Funcion fun = null;
        
        List<Funcion> listafuncion = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    fun = new Funcion();
                    fun.setId_Funcion(rs.getInt("id_Funcion"));
                    fun.setId_pelicula(rs.getInt("id_pelicula"));
                    fun.setNro_Sala(rs.getInt("nro_Sala"));
                    fun.setIdioma(rs.getString("idioma"));
                    fun.setEs3D(rs.getInt("es3D"));
                    fun.setHora_Inicio(rs.getTime("Hora_Inicio"));
                    fun.setHora_Fin(rs.getTime("Hora_Fin"));
                    fun.setPrecio_Entrada(rs.getDouble("Precio_Entrada"));
                    fun.setFecha_Funcion(rs.getDate("Fecha_Funcion").toLocalDate());
                    fun.setSubtitulada(rs.getInt("Subtitulada"));
                    fun.setEstado(rs.getInt("Estado"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listafuncion;
    }
    
    public void darAltaFuncion(int id) throws Exception{
        String sql = "UPDATE funcion SET Estado = true WHERE id_Funcion = ?";
        Connection conn = ConexionBD.getConnection();

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 0){
                throw new Exception("Error al dar de alta la funcion.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    public void darBajaFuncion(int id)throws Exception{
        String sql = "UPDATE funcion SET Estado = false WHERE id_Funcion = ?";
        Connection conn = ConexionBD.getConnection();

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 0){
                throw new Exception("Error al dar de baja la funcion.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
}
