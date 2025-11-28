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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Gonzalo Achucarro
 */
public class VentaDAO {

    ClienteDAO maniCliente = new ClienteDAO();

    public int registrarVentaTaquilla(Venta venta) throws Exception {
        String sql = "INSERT INTO venta(id_cliente, medio_pago, cantidad_entradas, importe_total,"
                + " medio_compra, fecha_venta) VALUES (?, ?, ?, ?, ?, ?)";

        Connection con = ConexionBD.getConnection();
        int idVenta = -1;
        try (PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            if (venta.getCliente() == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, venta.getCliente().getId_cliente());
            }
            ps.setString(2, venta.getMedio_pago());
            ps.setInt(3, venta.getCantidad_entradas());
            ps.setDouble(4, venta.getImporte_total());
            ps.setString(5, venta.getMedio_compra());
            ps.setDate(6, Date.valueOf(venta.getFecha_venta()));
            int fila = ps.executeUpdate();

            if (fila == 0) {
                throw new Exception("Error al registrar la venta.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idVenta = rs.getInt(1);
                } else {
                    throw new Exception("Error al obtener el ID de la venta registrada.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return idVenta;
    }

    public int registrarVentaOnline(Venta venta) throws Exception {
        String sql = "INSERT INTO venta(id_cliente, medio_pago, cantidad_entradas, importe_total,"
                + " medio_compra, token, fecha_venta) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = ConexionBD.getConnection();
        int idVenta = -1;
        try (PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            if (venta.getCliente().getId_cliente() == -1) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, venta.getCliente().getId_cliente());
            }
            ps.setString(2, venta.getMedio_pago());
            ps.setInt(3, venta.getCantidad_entradas());
            ps.setDouble(4, venta.getImporte_total());
            ps.setString(5, venta.getMedio_compra());

            if (venta.getToken() == null) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, venta.getToken());
            }

            ps.setDate(7, java.sql.Date.valueOf(venta.getFecha_venta()));

            int fila = ps.executeUpdate();

            if (fila == 0) {
                throw new Exception("Error al registrar la venta.");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idVenta = rs.getInt(1);
                } else {
                    throw new Exception("Error al obtener el ID de la venta registrada.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return idVenta;
    }

    public void modificarVenta(Venta venta) throws Exception {
        String sql = "UPDATE venta SET id_cliente = ?, medio_pago = ?, cantidad_entradas = ?, importe_total = ?,"
                + " medio_compra = ?, token = ?, fecha_venta = ? WHERE id_venta = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venta.getCliente().getId_cliente());
            ps.setString(2, venta.getMedio_pago());
            ps.setInt(3, venta.getCantidad_entradas());
            ps.setDouble(4, venta.getImporte_total());
            ps.setString(5, venta.getMedio_compra());
            if (venta.getToken() == null) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, venta.getToken());
            }
            ps.setDate(7, Date.valueOf(venta.getFecha_venta()));

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al modificar la venta.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void darAlta(int id) throws Exception {
        String sql = "UPDATE pelicula SET estado = false WHERE id_pelicula = ?";
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
        String sql = "UPDATE pelicula SET estado = true WHERE id_pelicula = ?";
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

    public void eliminarVenta(int id) throws Exception {
        String sql = "DELETE FROM venta WHERE id_Venta = ?";
        Connection conn = ConexionBD.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new Exception("No se pudo eliminar la venta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public List<Venta> listarVenta() throws Exception {
        String sql = "SELECT * FROM venta";
        Connection conn = ConexionBD.getConnection();
        Venta venta = null;

        List<Venta> listaVenta = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    venta = new Venta();
                    venta.setId_venta(rs.getInt("id_venta"));
                    venta.setCliente(maniCliente.buscarClientePorId(rs.getInt("id_cliente")));
                    venta.setMedio_pago(rs.getString("medio_pago"));
                    venta.setCantidad_entradas(rs.getInt("cantidad_entradas"));
                    venta.setImporte_total(rs.getDouble("importe_total"));
                    venta.setToken(rs.getInt("token"));

                    listaVenta.add(venta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listaVenta;
    }
    
    public Venta buscarPorId(int id) throws Exception{
        String sql = "SELECT * FROM venta WHERE id_venta = ?";
        Connection con = ConexionBD.getConnection();
        Venta venta = null;
        
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                venta = new Venta();
                venta.setId_venta(rs.getInt("id_venta"));
                    venta.setCliente(maniCliente.buscarClientePorId(rs.getInt("id_cliente")));
                    venta.setMedio_pago(rs.getString("medio_pago"));
                    venta.setCantidad_entradas(rs.getInt("cantidad_entradas"));
                    venta.setImporte_total(rs.getDouble("importe_total"));
                    venta.setToken(rs.getInt("token"));
                    }
            }
          }catch(SQLException e){
              throw new Exception("No se ha encontrado la venta asignada.");
          }
        return venta;
    }

}
