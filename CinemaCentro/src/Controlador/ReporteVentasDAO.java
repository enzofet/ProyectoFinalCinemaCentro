/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ReporteVentas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author emadupre
 */
public class ReporteVentasDAO {

    public List<ReporteVentas> generarReporteCompleto() {
        List<ReporteVentas> reportes = new ArrayList<>();
        String sql = "SELECT "
                + "p.titulo as pelicula, "
                + "CONCAT(p.titulo, ' - ', DATE_FORMAT(f.fecha_funcion, '%d/%m/%Y %H:%i')) as funcion, "
                + "s.nro_sala as sala, "
                + "s.capacidad, "
                + "COUNT(dt.id_ticket) as tickets_vendidos, "
                + "SUM(CASE WHEN v.medio_pago = 'EFECTIVO' THEN v.importe_total ELSE 0 END) as total_efectivo, "
                + "SUM(CASE WHEN v.medio_pago = 'DEBITO' THEN v.importe_total ELSE 0 END) as total_debito, "
                + "SUM(CASE WHEN v.medio_compra = 'Online' THEN 1 ELSE 0 END) as tickets_online, "
                + "SUM(CASE WHEN v.medio_compra = 'Taquilla' THEN 1 ELSE 0 END) as tickets_taquilla "
                + "FROM detalleticket dt "
                + "INNER JOIN venta v ON dt.id_venta = v.id_venta "
                + "INNER JOIN funcion f ON dt.id_funcion = f.id_funcion "
                + "INNER JOIN pelicula p ON f.id_pelicula = p.id_pelicula "
                + "INNER JOIN sala s ON f.nro_sala = s.nro_sala "
                + "WHERE dt.estado = true "
                + "GROUP BY p.titulo, f.fecha_funcion, s.nro_sala, s.capacidad "
                + "ORDER BY f.fecha_funcion, p.titulo";

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ticketsVendidos = rs.getInt("tickets_vendidos");
                int capacidad = rs.getInt("capacidad");
                double ocupacion = capacidad > 0 ? (ticketsVendidos * 100.0) / capacidad : 0;

                ReporteVentas reporte = new ReporteVentas(
                        rs.getString("pelicula"),
                        rs.getString("funcion"),
                        "Sala " + rs.getInt("sala"),
                        ticketsVendidos,
                        rs.getDouble("total_efectivo"),
                        rs.getDouble("total_debito"),
                        rs.getInt("tickets_online"),
                        rs.getInt("tickets_taquilla"),
                        ocupacion
                );
                reportes.add(reporte);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reportes;
    }

    public Map<String, Object> obtenerTotalesGenerales() {
        Map<String, Object> totales = new HashMap<>();
        String sql = "SELECT "
                + "COUNT(dt.id_ticket) as total_tickets, "
                + "SUM(v.importe_total) as total_general, "
                + "SUM(CASE WHEN v.medio_pago = 'EFECTIVO' THEN v.importe_total ELSE 0 END) as total_efectivo, "
                + "SUM(CASE WHEN v.medio_pago = 'DEBITO' THEN v.importe_total ELSE 0 END) as total_debito, "
                + "SUM(CASE WHEN v.medio_compra = 'Online' THEN 1 ELSE 0 END) as tickets_online, "
                + "SUM(CASE WHEN v.medio_compra = 'Taquilla' THEN 1 ELSE 0 END) as tickets_taquilla "
                + "FROM detalleticket dt "
                + "INNER JOIN venta v ON dt.id_venta = v.id_venta "
                + "WHERE dt.estado = true";

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totales.put("totalTickets", rs.getInt("total_tickets"));
                totales.put("totalGeneral", rs.getDouble("total_general"));
                totales.put("totalEfectivo", rs.getDouble("total_efectivo"));
                totales.put("totalDebito", rs.getDouble("total_debito"));
                totales.put("ticketsOnline", rs.getInt("tickets_online"));
                totales.put("ticketsTaquilla", rs.getInt("tickets_taquilla"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return totales;
    }

    public List<ReporteVentas> generarReportePorFecha(java.time.LocalDate fecha) {
        List<ReporteVentas> reportes = new ArrayList<>();
        String sql = "SELECT "
                + "p.titulo as pelicula, "
                + "CONCAT(p.titulo, ' - ', DATE_FORMAT(f.fecha_funcion, '%d/%m/%Y %H:%i')) as funcion, "
                + "s.nro_sala as sala, "
                + "s.capacidad, "
                + "COUNT(dt.id_ticket) as tickets_vendidos, "
                + "SUM(CASE WHEN v.medio_pago = 'EFECTIVO' THEN v.importe_total ELSE 0 END) as total_efectivo, "
                + "SUM(CASE WHEN v.medio_pago = 'DEBITO' THEN v.importe_total ELSE 0 END) as total_debito, "
                + "SUM(CASE WHEN v.medio_compra = 'Online' THEN 1 ELSE 0 END) as tickets_online, "
                + "SUM(CASE WHEN v.medio_compra = 'Taquilla' THEN 1 ELSE 0 END) as tickets_taquilla "
                + "FROM detalleticket dt "
                + "INNER JOIN venta v ON dt.id_venta = v.id_venta "
                + "INNER JOIN funcion f ON dt.id_funcion = f.id_funcion "
                + "INNER JOIN pelicula p ON f.id_pelicula = p.id_pelicula "
                + "INNER JOIN sala s ON f.nro_sala = s.nro_sala "
                + "WHERE dt.estado = true AND DATE(v.fecha_venta) = ? "
                + "GROUP BY p.titulo, f.fecha_funcion, s.nro_sala, s.capacidad "
                + "ORDER BY f.fecha_funcion, p.titulo";

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ticketsVendidos = rs.getInt("tickets_vendidos");
                int capacidad = rs.getInt("capacidad");
                double ocupacion = capacidad > 0 ? (ticketsVendidos * 100.0) / capacidad : 0;

                ReporteVentas reporte = new ReporteVentas(
                        rs.getString("pelicula"),
                        rs.getString("funcion"),
                        "Sala " + rs.getInt("sala"),
                        ticketsVendidos,
                        rs.getDouble("total_efectivo"),
                        rs.getDouble("total_debito"),
                        rs.getInt("tickets_online"),
                        rs.getInt("tickets_taquilla"),
                        ocupacion
                );
                reportes.add(reporte);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reportes;
    }

    public List<ReporteVentas> generarReporteConFiltros(String peliculaFiltro, String salaFiltro, LocalDate fechaDesde, LocalDate fechaHasta) {
        List<ReporteVentas> reportes = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("p.titulo as pelicula, ")
                .append("CONCAT(p.titulo, ' - ', DATE_FORMAT(f.fecha_funcion, '%d/%m/%Y %H:%i')) as funcion, ")
                .append("s.nro_sala as sala, ")
                .append("s.capacidad, ")
                .append("COUNT(dt.id_ticket) as tickets_vendidos, ")
                .append("SUM(CASE WHEN v.medio_pago = 'EFECTIVO' THEN f.precio_entrada ELSE 0 END) as total_efectivo, ")
                .append("SUM(CASE WHEN v.medio_pago = 'DEBITO' THEN f.precio_entrada ELSE 0 END) as total_debito, ")
                .append("SUM(CASE WHEN v.medio_compra = 'Online' THEN 1 ELSE 0 END) as tickets_online, ")
                .append("SUM(CASE WHEN v.medio_compra = 'Taquilla' THEN 1 ELSE 0 END) as tickets_taquilla ")
                .append("FROM detalleticket dt ")
                .append("INNER JOIN venta v ON dt.id_venta = v.id_venta ")
                .append("INNER JOIN funcion f ON dt.id_funcion = f.id_funcion ")
                .append("INNER JOIN pelicula p ON f.id_pelicula = p.id_pelicula ")
                .append("INNER JOIN sala s ON f.nro_sala = s.nro_sala ")
                .append("WHERE dt.estado = true ");

        List<Object> parametros = new ArrayList<>();

        if (peliculaFiltro != null && !peliculaFiltro.isEmpty()) {
            sql.append("AND p.titulo = ? ");
            parametros.add(peliculaFiltro);
        }

        if (salaFiltro != null && !salaFiltro.isEmpty()) {
            sql.append("AND s.nro_sala = ? ");
            parametros.add(Integer.parseInt(salaFiltro));
        }

        if (fechaDesde != null) {
            sql.append("AND DATE(v.fecha_venta) >= ? ");
            parametros.add(java.sql.Date.valueOf(fechaDesde));
        }

        if (fechaHasta != null) {
            sql.append("AND DATE(v.fecha_venta) <= ? ");
            parametros.add(java.sql.Date.valueOf(fechaHasta));
        }

        sql.append("GROUP BY p.titulo, f.fecha_funcion, s.nro_sala, s.capacidad ")
                .append("ORDER BY f.fecha_funcion, p.titulo");

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(sql.toString());

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ticketsVendidos = rs.getInt("tickets_vendidos");
                int capacidad = rs.getInt("capacidad");
                double ocupacion = capacidad > 0 ? (ticketsVendidos * 100.0) / capacidad : 0;

                ReporteVentas reporte = new ReporteVentas(
                        rs.getString("pelicula"),
                        rs.getString("funcion"),
                        "Sala " + rs.getInt("sala"),
                        ticketsVendidos,
                        rs.getDouble("total_efectivo"),
                        rs.getDouble("total_debito"),
                        rs.getInt("tickets_online"),
                        rs.getInt("tickets_taquilla"),
                        ocupacion
                );
                reportes.add(reporte);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reportes;
    }

    public Map<String, Object> obtenerTotalesConFiltros(String peliculaFiltro, String salaFiltro, LocalDate fechaDesde, LocalDate fechaHasta) {
        Map<String, Object> totales = new HashMap<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("COUNT(dt.id_ticket) as total_tickets, ")
                .append("SUM(f.precio_entrada) as total_general, ")
                .append("SUM(CASE WHEN v.medio_pago = 'EFECTIVO' THEN f.precio_entrada ELSE 0 END) as total_efectivo, ")
                .append("SUM(CASE WHEN v.medio_pago = 'DEBITO' THEN f.precio_entrada ELSE 0 END) as total_debito, ")
                .append("SUM(CASE WHEN v.medio_compra = 'Online' THEN 1 ELSE 0 END) as tickets_online, ")
                .append("SUM(CASE WHEN v.medio_compra = 'Taquilla' THEN 1 ELSE 0 END) as tickets_taquilla ")
                .append("FROM detalleticket dt ")
                .append("INNER JOIN venta v ON dt.id_venta = v.id_venta ")
                .append("INNER JOIN funcion f ON dt.id_funcion = f.id_funcion ")
                .append("INNER JOIN pelicula p ON f.id_pelicula = p.id_pelicula ")
                .append("INNER JOIN sala s ON f.nro_sala = s.nro_sala ")
                .append("WHERE dt.estado = true ");

        List<Object> parametros = new ArrayList<>();

        if (peliculaFiltro != null && !peliculaFiltro.isEmpty()) {
            sql.append("AND p.titulo = ? ");
            parametros.add(peliculaFiltro);
        }

        if (salaFiltro != null && !salaFiltro.isEmpty()) {
            sql.append("AND s.nro_sala = ? ");
            parametros.add(Integer.parseInt(salaFiltro));
        }

        if (fechaDesde != null) {
            sql.append("AND DATE(v.fecha_venta) >= ? ");
            parametros.add(java.sql.Date.valueOf(fechaDesde));
        }

        if (fechaHasta != null) {
            sql.append("AND DATE(v.fecha_venta) <= ? ");
            parametros.add(java.sql.Date.valueOf(fechaHasta));
        }

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(sql.toString());

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totales.put("totalTickets", rs.getInt("total_tickets"));
                totales.put("totalGeneral", rs.getDouble("total_general"));
                totales.put("totalEfectivo", rs.getDouble("total_efectivo"));
                totales.put("totalDebito", rs.getDouble("total_debito"));
                totales.put("ticketsOnline", rs.getInt("tickets_online"));
                totales.put("ticketsTaquilla", rs.getInt("tickets_taquilla"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return totales;
    }

    public List<Map<String, Object>> obtenerPeliculasMasVistas(LocalDate fechaDesde, LocalDate fechaHasta) {
        List<Map<String, Object>> resultados = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("p.titulo as pelicula, ")
                .append("COUNT(dt.id_ticket) as tickets_vendidos, ")
                .append("SUM(CASE WHEN v.medio_pago = 'EFECTIVO' THEN f.precio_entrada ELSE 0 END) as total_efectivo, ")
                .append("SUM(CASE WHEN v.medio_pago = 'DEBITO' THEN f.precio_entrada ELSE 0 END) as total_debito, ")
                .append("SUM(CASE WHEN v.medio_compra = 'Online' THEN 1 ELSE 0 END) as tickets_online, ")
                .append("SUM(CASE WHEN v.medio_compra = 'Taquilla' THEN 1 ELSE 0 END) as tickets_taquilla, ")
                .append("SUM(f.precio_entrada) as total_recaudado ")
                .append("FROM detalleticket dt ")
                .append("INNER JOIN venta v ON dt.id_venta = v.id_venta ")
                .append("INNER JOIN funcion f ON dt.id_funcion = f.id_funcion ")
                .append("INNER JOIN pelicula p ON f.id_pelicula = p.id_pelicula ")
                .append("WHERE dt.estado = true ");

        List<Object> parametros = new ArrayList<>();

        if (fechaDesde != null) {
            sql.append("AND DATE(v.fecha_venta) >= ? ");
            parametros.add(java.sql.Date.valueOf(fechaDesde));
        }

        if (fechaHasta != null) {
            sql.append("AND DATE(v.fecha_venta) <= ? ");
            parametros.add(java.sql.Date.valueOf(fechaHasta));
        }

        sql.append("GROUP BY p.id_pelicula, p.titulo ")
                .append("ORDER BY tickets_vendidos DESC, total_recaudado DESC");

        Connection con = null;
        try {
            con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(sql.toString());

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> pelicula = new HashMap<>();
                pelicula.put("pelicula", rs.getString("pelicula"));
                pelicula.put("tickets_vendidos", rs.getInt("tickets_vendidos"));
                pelicula.put("total_efectivo", rs.getDouble("total_efectivo"));
                pelicula.put("total_debito", rs.getDouble("total_debito"));
                pelicula.put("tickets_online", rs.getInt("tickets_online"));
                pelicula.put("tickets_taquilla", rs.getInt("tickets_taquilla"));
                pelicula.put("total_recaudado", rs.getDouble("total_recaudado"));
                resultados.add(pelicula);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultados;
    }
}
