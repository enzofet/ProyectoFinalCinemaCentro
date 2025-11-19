/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
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
public class ClienteDAO {
    public void agregarCliente(Cliente cliente) throws Exception{
        String sql= "INSERT INTO cliente (dni, fecha_nacimiento, nombre, apellido, estado, password) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = ConexionBD.getConnection();
        
        try(PreparedStatement ps= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, cliente.getDni());
            ps.setDate(2, Date.valueOf(cliente.getFecha_nacimiento()));
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setBoolean(5, cliente.isEstado());
            ps.setString(6, cliente.getPassword());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                cliente.setId_cliente(rs.getInt(1));
            } else {
                System.out.println("No se pudo agregar el cliente");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    public void eliminarCliente(int id) throws Exception{
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        Connection conn = ConexionBD.getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            if(filas == 0){
                throw new Exception("No se pudo registrar el cliente.");
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        } 
    }
    
    public void actualizarCliente(int id, Cliente cliente) throws Exception{
        String sql = "UPDATE cliente SET dni= ?, fecha_nacimiento = ?, nombre = ?, apellido = ?,"
                + " estado = ?, password = ? WHERE id_cliente=?";
        Connection conn = ConexionBD.getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, cliente.getDni());
            ps.setDate(2, Date.valueOf(cliente.getFecha_nacimiento()));
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setBoolean(5, cliente.isEstado());
            ps.setString(6, cliente.getPassword());
            ps.setInt(7, cliente.getId_cliente());
            
            int filas = ps.executeUpdate();
            if(filas == 0){
                throw new Exception("No se pudo registrar el cliente.");
            }  
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    
    public Cliente buscarClientePorId(int id) throws Exception{
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        Connection conn = ConexionBD.getConnection();
        
        Cliente cliente = null;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    cliente = new Cliente();
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setDni(rs.getInt("dni"));
                    cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setEstado(rs.getBoolean("estado"));
                    cliente.setPassword(rs.getString("password"));
                } else {
                    throw new Exception("No se ha encontrado el cliente.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return cliente;
    }
    
    
    public Cliente buscarClientePorDNI(int dni) throws Exception{
         String sql = "SELECT * FROM cliente WHERE dni = ?";
        Connection conn = ConexionBD.getConnection();
        
        Cliente cliente = null;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, dni);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    cliente = new Cliente();
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setDni(rs.getInt("dni"));
                    cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setEstado(rs.getBoolean("estado"));
                    cliente.setPassword(rs.getString("password"));
                } else {
                    throw new Exception("No se ha encontrado el cliente.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return cliente;
    }
    
    public List<Cliente> listarClientes() throws Exception{
        String sql = "SELECT * FROM cliente";
        Connection conn = ConexionBD.getConnection();
        Cliente client = null;
        
        List<Cliente> listaCliente = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    client = new Cliente();
                    client.setId_cliente(rs.getInt("id_cliente"));
                    client.setDni(rs.getInt("dni"));
                    client.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                    client.setNombre(rs.getString("nombre"));
                    client.setApellido(rs.getString("apellido"));
                    client.setEstado(rs.getBoolean("estado"));
                    client.setPassword(rs.getString("password"));
                    listaCliente.add(client);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listaCliente;
    }
    
    public void darAltaCliente(int id) throws Exception{
        String sql = "UPDATE cliente SET estado = true WHERE id_cliente = ?";
        Connection conn = ConexionBD.getConnection();

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 0){
                throw new Exception("Error al dar de alta al cliente.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    public void darBajaCliente(int id)throws Exception{
        String sql = "UPDATE cliente SET estado = false WHERE id_cliente = ?";
        Connection conn = ConexionBD.getConnection();

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 0){
                throw new Exception("Error al dar de baja al cliente.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }
    
    public Cliente validarCredenciales(int dni, String password) throws Exception{
        String sql = "SELECT * FROM cliente WHERE dni = ? AND password = ? AND estado = true";
        Connection conn = ConexionBD.getConnection();
        
        Cliente cliente = null;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, dni);
            ps.setString(2, password);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    cliente = new Cliente();
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setDni(rs.getInt("dni"));
                    cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setEstado(rs.getBoolean("estado"));
                    cliente.setPassword(rs.getString("password"));
                } else {
                    throw new Exception("No se ha podido validar sus credenciales, reviselas o verifique que este "
                            + "registrado.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return cliente;
    }
}
