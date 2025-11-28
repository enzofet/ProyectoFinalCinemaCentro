/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DetalleTicket;
import Modelo.TicketDato;
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
public class DetalleTicketDAO {

    FuncionDAO maniFuncion = new FuncionDAO();
    VentaDAO maniVenta = new VentaDAO();
    

    public void generarTicket(DetalleTicket ticket) throws Exception {
        String sql = "INSERT INTO detalleticket(id_funcion, asiento, id_venta, fecha_emision, estado)"
                + " VALUES (?, ?, ?, ?, ?)";

        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ticket.getFuncion().getId_Funcion());
            ps.setString(2, ticket.getAsiento());
            ps.setInt(3, ticket.getVenta().getId_venta());
            ps.setDate(4, Date.valueOf(ticket.getFecha_emision()));
            ps.setBoolean(5, ticket.isEstado());

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al generar ticket.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public Integer generarToken() throws Exception {
        String sql = "SELECT token FROM venta WHERE medio_compra = 'Online'";
        Connection con = ConexionBD.getConnection();
        int token = 0;
        ArrayList<Integer> tokensCreados = new ArrayList();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                int tokenPrev = (int) (Math.random() * 90000000);
                token = tokenPrev;
                while (rs.next()) {
                    Integer tokenBD = rs.getInt("token");
                    tokensCreados.add(tokenBD);
                    if (tokensCreados.contains(tokenBD)) {
                        tokenPrev = (int) (Math.random() * 90000000);
                        token = tokenPrev;
                    }
                }

            }
        } catch (SQLException e) {
            throw new Exception("Error relacionado a la base de datos.");
        }
        return token;
    }

    public DetalleTicket buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM detalleticket WHERE id_ticket = ?";
        Connection con = ConexionBD.getConnection();
        DetalleTicket ticket = new DetalleTicket();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ticket.setId_ticket(rs.getInt("id_ticket"));
                    ticket.setFuncion(maniFuncion.buscarFuncionPorId(rs.getInt("id_funcion")));
                    ticket.setAsiento(rs.getString("asiento"));
                    ticket.setVenta(maniVenta.buscarPorId(rs.getInt("id_venta")));
                    ticket.setFecha_emision(rs.getDate("fecha_emision").toLocalDate());
                    ticket.setEstado(rs.getBoolean("estado"));
                } else {
                    ticket = null;
                    throw new Exception("No se pudo encontrar el ticket");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return ticket;
    }

    public ArrayList<DetalleTicket> listarTicketsPorVenta(int id_venta) throws Exception {
        String sql = "SELECT * FROM detalleticket WHERE id_venta = ?";
        Connection con = ConexionBD.getConnection();
        ArrayList<DetalleTicket> listaTickets = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_venta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleTicket ticket = new DetalleTicket();
                    ticket.setId_ticket(rs.getInt("id_ticket"));
                    ticket.setFuncion(maniFuncion.buscarFuncionPorId(rs.getInt("id_funcion")));
                    ticket.setAsiento(rs.getString("asiento"));
                    ticket.setVenta(maniVenta.buscarPorId(rs.getInt("id_venta")));
                    ticket.setFecha_emision(rs.getDate("fecha_emision").toLocalDate());
                    ticket.setEstado(rs.getBoolean("estado"));
                    listaTickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listaTickets;
    }
    
    public List<DetalleTicket> listarTicketsPorFuncion(int id_funcion) throws Exception{
        String sql = "SELECT * FROM detalleticket WHERE id_funcion = ?";
        Connection con = ConexionBD.getConnection();
        ArrayList<DetalleTicket> listaTickets = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id_funcion);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    DetalleTicket ticket = new DetalleTicket();
                    ticket.setId_ticket(rs.getInt("id_ticket"));
                    ticket.setFuncion(maniFuncion.buscarFuncionPorId(rs.getInt("id_funcion")));
                    ticket.setAsiento(rs.getString("asiento"));
                    ticket.setVenta(maniVenta.buscarPorId(rs.getInt("id_venta")));
                    ticket.setFecha_emision(rs.getDate("fecha_emision").toLocalDate());
                    ticket.setEstado(rs.getBoolean("estado"));
                    listaTickets.add(ticket);
                    }
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        
        return listaTickets;
    }

    public void modificarTicket(int id_ticket, DetalleTicket ticket) throws Exception {
        String sql = "UPDATE detalleticket SET id_funcion = ?, asiento = ?, id_venta = ? WHERE id_ticket = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ticket.getFuncion().getId_Funcion());
            ps.setString(2, ticket.getAsiento());
            ps.setInt(3, ticket.getVenta().getId_venta());
            ps.setInt(4, id_ticket);
            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al actualizar el ticket");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void darAlta(int id_ticket) throws Exception {
        String sql = "UPDATE detalleticket SET estado = true WHERE id_ticket = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_ticket);

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al dar de alta el ticket");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public void darBaja(int id_ticket) throws Exception {
        String sql = "UPDATE detalleticket SET estado = false WHERE id_ticket = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_ticket);

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al dar de baja el ticket");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTicket(int id_ticket) throws Exception {
        String sql = "DELETE FROM detalleticket WHERE id_ticket = ?";
        Connection con = ConexionBD.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_ticket);

            int fila = ps.executeUpdate();
            if (fila == 0) {
                throw new Exception("Error al eliminar el ticket");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
    }

    public List<DetalleTicket> listarTickets() throws Exception {
        String sql = "SELECT * FROM detalleticket";
        Connection con = ConexionBD.getConnection();
        List<DetalleTicket> listaTickets = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleTicket ticket = new DetalleTicket();
                    ticket.setId_ticket(rs.getInt("id_ticket"));
                    ticket.setFuncion(maniFuncion.buscarFuncionPorId(rs.getInt("id_funcion")));
                    ticket.setAsiento(rs.getString("asiento"));
                    ticket.setVenta(maniVenta.buscarPorId(rs.getInt("id_venta")));
                    ticket.setFecha_emision(rs.getDate("fecha_emision").toLocalDate());
                    ticket.setEstado(rs.getBoolean("estado"));
                    listaTickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listaTickets;
    }

    public List<TicketDato> listarDatosTickets() throws Exception {
        String sql = "SELECT dt.id_ticket id_ticket, p.titulo tituloPelicula, s.nro_sala nroSala, f.fecha_funcion fechaFuncion, "
                + "f.hora_inicio horaInicio, f.hora_fin horaFin, dt.asiento asiento, "
                + "c.dni DNI, c.nombre nombre, c.apellido apellido, v.medio_compra medioCompra, dt.fecha_emision fechaEmision, "
                + "dt.estado estado FROM detalleticket dt\n"
                + "LEFT JOIN funcion f ON dt.id_funcion = f.id_funcion\n"
                + "LEFT JOIN pelicula p ON f.id_pelicula = p.id_pelicula\n"
                + "LEFT JOIN sala s ON f.nro_sala = s.nro_sala\n"
                + "LEFT JOIN venta v ON dt.id_venta = v.id_venta\n"
                + "LEFT JOIN cliente c ON v.id_cliente = c.id_cliente WHERE 1=1\n"
                + "ORDER BY dt.fecha_emision";

        Connection con = ConexionBD.getConnection();
        List<TicketDato> listaDatos = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TicketDato datoTicket = new TicketDato();
                    datoTicket.setIdTicket(rs.getInt("id_ticket"));

                    String pelicula = rs.getString("tituloPelicula");

                    if (pelicula != null) {
                        datoTicket.setPeliculaTitulo(rs.getString("tituloPelicula"));
                        datoTicket.setNroSala(rs.getInt("nroSala"));
                        datoTicket.setFechaFuncion(rs.getString("fechaFuncion"));
                        datoTicket.setHoraInicio(rs.getString("horaInicio"));
                        datoTicket.setHoraFin(rs.getString("horaFin"));
                        datoTicket.setAsiento(rs.getString("asiento"));
                    } else {
                        datoTicket.setPeliculaTitulo("Pelicula o función eliminada.");
                        datoTicket.setNroSala(0);
                        datoTicket.setFechaFuncion(rs.getString("fechaFuncion"));
                        datoTicket.setHoraInicio(rs.getString("horaInicio"));
                        datoTicket.setHoraFin(rs.getString("horaFin"));
                        datoTicket.setAsiento(rs.getString("numeroAsiento") + " - " + rs.getString("filaAsiento"));
                    }

                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");

                    if (nombre != null && apellido != null) {
                        datoTicket.setDNI(rs.getInt("DNI"));
                        datoTicket.setNombreApellidoCliente(nombre + " " + apellido);
                    } else {
                        datoTicket.setNombreApellidoCliente("Eliminado o anonimo.");
                        datoTicket.setDNI(0);
                    }
                    datoTicket.setMedioCompra(rs.getString("medioCompra"));
                    datoTicket.setFechaEmision(rs.getString("fechaEmision"));
                    datoTicket.setEstado(rs.getBoolean("estado"));

                    listaDatos.add(datoTicket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error relacionado a la base de datos.");
        }
        return listaDatos;
    }

}
