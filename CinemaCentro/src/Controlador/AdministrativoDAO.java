/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrativo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Enzo_2
 */
public class AdministrativoDAO {
    
    public Administrativo validarCredenciales(String usuario, String password) throws Exception{
        String sql = "SELECT * FROM administrativo WHERE usuario = ? AND password = ?";
        Connection con = ConexionBD.getConnection();
        Administrativo admin = null;
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, usuario);
            ps.setString(2, password);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    admin = new Administrativo();
                    admin.setId_administrativo(rs.getInt("id_administrativo"));
                    admin.setNombre(rs.getString("nombre"));
                    admin.setApellido(rs.getString("apellido"));
                    admin.setUsuario(rs.getString("usuario"));
                    admin.setPassword(rs.getString("password"));
                    admin.setCargo(rs.getString("cargo"));
                } else {
                    throw new Exception("No se ha encontrado el usuario ingresado.");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("Error al conectar a la base de datos.");
        }
        return admin;
    }
    
}
