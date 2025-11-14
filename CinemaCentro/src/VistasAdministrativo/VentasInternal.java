package VistasAdministrativo;

import Controlador.PeliculaDAO;
import Controlador.ReporteVentasDAO;
import Controlador.SalaDAO;
import Modelo.Pelicula;
import Modelo.ReporteVentas;
import Modelo.Sala;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import java.io.PrintWriter;
import javax.swing.table.TableModel;

public class VentasInternal extends javax.swing.JInternalFrame {

    private ReporteVentasDAO reporteDAO;
    private javax.swing.JLabel lblBarraEstado;

    private static final String[] columnas = {
        "Película", "Función", "Sala", "Tickets",
        "Efectivo", "Débito", "Online", "Taquilla", "Ocupación %"
    };
    
    public VentasInternal() {
        initComponents();

        lblBarraEstado = new javax.swing.JLabel();
        lblBarraEstado.setText("Listo");
        pnlTickets.add(lblBarraEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 500, 20));
        inicializarComponentes();
        configurarTabla();
        cargarCombobox();
        establecerFechaHoy();
        cargarDatosHoy();
    }

    private void inicializarComponentes() {
        reporteDAO = new ReporteVentasDAO();
        agregarListeners();
    }

    private void configurarTabla() {

        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabla.setModel(model);
        modeloTabla.getColumnModel().getColumn(0).setPreferredWidth(150);
        modeloTabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        modeloTabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        modeloTabla.getColumnModel().getColumn(3).setPreferredWidth(60);
        modeloTabla.getColumnModel().getColumn(4).setPreferredWidth(80);
        modeloTabla.getColumnModel().getColumn(5).setPreferredWidth(80);
        modeloTabla.getColumnModel().getColumn(6).setPreferredWidth(60);
        modeloTabla.getColumnModel().getColumn(7).setPreferredWidth(70);
        modeloTabla.getColumnModel().getColumn(8).setPreferredWidth(80);
    }

    private void agregarListeners() {
        btnActualizar.addActionListener(e -> aplicarFiltros());
        btnHoy.addActionListener(e -> cargarDatosHoy());
        btnTodos.addActionListener(e -> limpiarFiltros());
        btnExportar.addActionListener(e -> exportarReporte());
        btnAplicarFiltroFecha.addActionListener(e -> aplicarFiltroFecha());
        cmbPelicula.addActionListener(e -> aplicarFiltroPelicula());
        cmbSala.addActionListener(e -> aplicarFiltroSala());
    }

    private Object[] crearFilaReporte(ReporteVentas reporte) {
        return new Object[]{
            reporte.getPelicula(),
            reporte.getFuncion(),
            reporte.getSala(),
            reporte.getTicketsVendidos(),
            String.format("$%,.2f", reporte.getTotalEfectivo()),
            String.format("$%,.2f", reporte.getTotalDebito()),
            reporte.getTicketsOnline(),
            reporte.getTicketsTaquilla(),
            String.format("%.1f%%", reporte.getOcupacion())
        };
    }

    private void cargarCombobox() {
        cargarComboboxPeliculas();
        cargarComboboxSalas();
    }

    private void establecerFechaHoy() {
        String fechaHoy = obtenerFechaHoy();
        txtFechaDesde.setText(fechaHoy);
        txtFechaHasta.setText(fechaHoy);
    }

    private void cargarDatosCompletos() {
        List<ReporteVentas> reportes = reporteDAO.generarReporteConFiltros(null, null, null, null);
        Map<String, Object> totales = reporteDAO.obtenerTotalesConFiltros(null, null, null, null);
        cargarDatosConTotales(reportes, totales, "REPORTE COMPLETO");
    }

    private void cargarDatosHoy() {
        establecerFechaHoy();

        // aca uso el método de filtros unificado para hoy
        String pelicula = obtenerPeliculaSeleccionada();
        String sala = obtenerSalaSeleccionada();
        LocalDate hoy = LocalDate.now();

        List<ReporteVentas> reportes = reporteDAO.generarReporteConFiltros(pelicula, sala, hoy, hoy);
        Map<String, Object> totales = reporteDAO.obtenerTotalesConFiltros(pelicula, sala, hoy, hoy);

        String titulo = "REPORTE DE HOY";
        if (pelicula != null) {
            titulo += " - " + pelicula;
        }
        if (sala != null) {
            titulo += " - Sala " + sala;
        }

        cargarDatosConTotales(reportes, totales, titulo);
    }

    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) modeloTabla.getModel();
        model.setRowCount(0);
    }

    private void agregarFilaVacia() {
        DefaultTableModel model = (DefaultTableModel) modeloTabla.getModel();
        model.addRow(new Object[]{"No hay datos disponibles", "", "", "", "", "", "", "", ""});
    }

    private void agregarFilaTotales(Map<String, Object> totales) {
        if (totales == null || totales.isEmpty()) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) modeloTabla.getModel();
        model.addRow(new Object[]{
            "=== TOTALES GENERALES ===",
            "",
            "",
            totales.get("totalTickets"),
            String.format("$%,.2f", totales.get("totalEfectivo")),
            String.format("$%,.2f", totales.get("totalDebito")),
            totales.get("ticketsOnline"),
            totales.get("ticketsTaquilla"),
            "100%"
        });
    }

    private void aplicarFiltros() {
        if (!validarFechas()) {
            return;
        }

        String pelicula = obtenerPeliculaSeleccionada();
        String sala = obtenerSalaSeleccionada();
        LocalDate fechaDesde = obtenerFechaDesde();
        LocalDate fechaHasta = obtenerFechaHasta();

        String titulo = generarTituloReporte(pelicula, sala, fechaDesde, fechaHasta);
        List<ReporteVentas> reportes = reporteDAO.generarReporteConFiltros(pelicula, sala, fechaDesde, fechaHasta);
        Map<String, Object> totales = reporteDAO.obtenerTotalesConFiltros(pelicula, sala, fechaDesde, fechaHasta);

        cargarDatosConTotales(reportes, totales, titulo);
    }

    private String generarTituloReporte(String pelicula, String sala, LocalDate fechaDesde, LocalDate fechaHasta) {
        StringBuilder titulo = new StringBuilder("REPORTE");

        if (pelicula != null) {
            titulo.append(" - ").append(pelicula);
        }

        if (sala != null) {
            titulo.append(" - Sala ").append(sala);
        }

        if (fechaDesde != null && fechaHasta != null) {
            if (fechaDesde.equals(fechaHasta)) {
                titulo.append(" - ").append(fechaDesde);
            } else {
                titulo.append(" - ").append(fechaDesde).append(" a ").append(fechaHasta);
            }
        } else if (fechaDesde != null) {
            titulo.append(" - Desde ").append(fechaDesde);
        } else if (fechaHasta != null) {
            titulo.append(" - Hasta ").append(fechaHasta);
        }

        if (pelicula == null && sala == null && fechaDesde == null && fechaHasta == null) {
            titulo = new StringBuilder("REPORTE COMPLETO");
        }

        return titulo.toString();
    }

    private void cargarDatosConTotales(List<ReporteVentas> reportes, Map<String, Object> totales, String titulo) {
        limpiarTabla();

        if (reportes == null || reportes.isEmpty()) {
            agregarFilaVacia();
            return;
        }

        DefaultTableModel model = (DefaultTableModel) modeloTabla.getModel();

        for (ReporteVentas reporte : reportes) {
            model.addRow(crearFilaReporte(reporte));
        }

        // agregar totales y mostrar resumen
        agregarFilaTotales(totales);
        mostrarResumen(totales);

        actualizarLabelReporte(titulo);
    }

    private void aplicarFiltroFecha() {
        aplicarFiltros();
    }

    private void aplicarFiltroPelicula() {
        aplicarFiltros();
    }

    private void aplicarFiltroSala() {
        aplicarFiltros();
    }

    private void limpiarFiltros() {
        cmbPelicula.setSelectedIndex(0);
        cmbSala.setSelectedIndex(0);
        establecerFechaHoy();
        cargarDatosCompletos();
    }

    private boolean validarFormatoFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private LocalDate parsearFecha(String fechaStr) {
        try {
            return LocalDate.parse(fechaStr);
        } catch (Exception e) {
            return null;
        }
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void actualizarBarraEstado(String mensaje) {
        if (lblBarraEstado != null) {
            lblBarraEstado.setText(mensaje);
        } else {
            // si no hay barra de estado, muestra en consola
            System.out.println("BARRA ESTADO: " + mensaje);
        }
    }

    private String obtenerFechaHoy() {
        return LocalDate.now().toString();
    }

    private LocalDate obtenerFechaDesde() {
        String strDesde = txtFechaDesde.getText().trim();
        return strDesde.isEmpty() ? null : parsearFecha(strDesde);
    }

    private LocalDate obtenerFechaHasta() {
        String strHasta = txtFechaHasta.getText().trim();
        return strHasta.isEmpty() ? null : parsearFecha(strHasta);
    }

    private void exportarReporte() {
        try {
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte como CSV");
            fileChooser.setSelectedFile(new java.io.File("reporte_ventas.csv"));

            if (fileChooser.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File file = fileChooser.getSelectedFile();
                exportarACSV(file);
                JOptionPane.showMessageDialog(this,
                        "Reporte exportado exitosamente a: " + file.getAbsolutePath(),
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al exportar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportarACSV(java.io.File file) {
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            // escribir bom para Excel
            writer.write('\uFEFF');

            // encabezado
            writer.println("Película,Función,Sala,Tickets Vendidos,Efectivo,Débito,Online,Taquilla,Ocupación %");

            // datos
            TableModel model = modeloTabla.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    if (j > 0) {
                        sb.append(",");
                    }
                    Object value = model.getValueAt(i, j);
                    String cellValue = value != null
                            ? value.toString().replace("\"", "\"\"") : "";
                    if (cellValue.contains(",") || cellValue.contains("\"")) {
                        sb.append("\"").append(cellValue).append("\"");
                    } else {
                        sb.append(cellValue);
                    }
                }
                writer.println(sb.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al exportar CSV: " + e.getMessage(), e);
        }
    }

    private void cargarComboboxPeliculas() {
        // remueve temporalmente el listener
        java.awt.event.ActionListener[] listeners = cmbPelicula.getActionListeners();
        for (java.awt.event.ActionListener listener : listeners) {
            cmbPelicula.removeActionListener(listener);
        }

        try {
            cmbPelicula.removeAllItems();
            cmbPelicula.addItem("Todas las películas");

            PeliculaDAO peliculaDAO = new PeliculaDAO();
            List<Pelicula> peliculas = peliculaDAO.listarPeliculasEnCartelera();

            for (Pelicula pelicula : peliculas) {
                if (pelicula.isEstado()) {
                    cmbPelicula.addItem(pelicula.getTitulo());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar películas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // restaura los listeners
            for (java.awt.event.ActionListener listener : listeners) {
                cmbPelicula.addActionListener(listener);
            }
        }
    }

    private void cargarComboboxSalas() {
        // remueve temporalmente el listener
        java.awt.event.ActionListener[] listeners = cmbSala.getActionListeners();
        for (java.awt.event.ActionListener listener : listeners) {
            cmbSala.removeActionListener(listener);
        }

        try {
            cmbSala.removeAllItems();
            cmbSala.addItem("Todas las salas");

            SalaDAO salaDAO = new SalaDAO();
            List<Sala> salas = salaDAO.listarsalas();

            for (Sala sala : salas) {
                if (sala.isEstado()) {
                    cmbSala.addItem("Sala " + sala.getNro_Sala());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar salas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // restauro los listeners
            for (java.awt.event.ActionListener listener : listeners) {
                cmbSala.addActionListener(listener);
            }
        }
    }

    private String obtenerPeliculaSeleccionada() {
        return cmbPelicula.getSelectedIndex() == 0 ? null : cmbPelicula.getSelectedItem().toString();
    }

    private String obtenerSalaSeleccionada() {
        if (cmbSala.getSelectedIndex() == 0) {
            return null;
        }

        String salaSeleccionada = cmbSala.getSelectedItem().toString();
        // acá solo traigo el numero de la sala
        return salaSeleccionada.replace("Sala ", "").trim();
    }

    private void mostrarResumen(Map<String, Object> totales) {
        if (totales != null && !totales.isEmpty()) {
            double totalEfectivo = (double) totales.get("totalEfectivo");
            double totalDebito = (double) totales.get("totalDebito");
            double totalGeneral = totalEfectivo + totalDebito;
            int totalTickets = (int) totales.get("totalTickets");
            int ticketsOnline = (int) totales.get("ticketsOnline");
            int ticketsTaquilla = (int) totales.get("ticketsTaquilla");

            String resumen = String.format(
                    "RESUMEN: Total: $%,.2f | Efectivo: $%,.2f | Débito: $%,.2f | Tickets: %d (Online: %d, Taquilla: %d)",
                    totalGeneral, totalEfectivo, totalDebito, totalTickets, ticketsOnline, ticketsTaquilla
            );

            // muestra una barra de estado en lugar de dialog
            actualizarBarraEstado(resumen);
        }
    }

    private void actualizarLabelReporte(String titulo) {
        lblReporte.setText(titulo + " - " + LocalDate.now());
    }

    private boolean validarFechas() {
        String strDesde = txtFechaDesde.getText().trim();
        String strHasta = txtFechaHasta.getText().trim();

        if (!strDesde.isEmpty() && !validarFormatoFecha(strDesde)) {
            mostrarError("Formato de fecha 'Desde' inválido. Use YYYY-MM-DD");
            return false;
        }

        if (!strHasta.isEmpty() && !validarFormatoFecha(strHasta)) {
            mostrarError("Formato de fecha 'Hasta' inválido. Use YYYY-MM-DD");
            return false;
        }

        LocalDate fechaDesde = obtenerFechaDesde();
        LocalDate fechaHasta = obtenerFechaHasta();

        if (fechaDesde != null && fechaHasta != null && fechaDesde.isAfter(fechaHasta)) {
            mostrarError("La fecha 'Desde' no puede ser posterior a la fecha 'Hasta'");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTickets = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        modeloTabla = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        lblReporte = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnHoy = new javax.swing.JButton();
        btnTodos = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        cmbPelicula = new javax.swing.JComboBox<>();
        cmbSala = new javax.swing.JComboBox<>();
        txtFechaDesde = new javax.swing.JTextField();
        txtFechaHasta = new javax.swing.JTextField();
        lblFechaDesde = new javax.swing.JLabel();
        lblFechaHasta = new javax.swing.JLabel();
        btnAplicarFiltroFecha = new javax.swing.JButton();
        lblPelicula = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblPorSala = new javax.swing.JLabel();
        lblPorFecha1 = new javax.swing.JLabel();
        lblPorSala1 = new javax.swing.JLabel();

        modeloTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(modeloTabla);

        lblTitulo.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        lblTitulo.setText("CIERRE DE CAJA");

        lblReporte.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        lblReporte.setText("REPORTE DEL DIA:");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnHoy.setText("Hoy");
        btnHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoyActionPerformed(evt);
            }
        });

        btnTodos.setText("Todos");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        cmbPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPeliculaActionPerformed(evt);
            }
        });

        cmbSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSalaActionPerformed(evt);
            }
        });

        lblFechaDesde.setText("Desde:");

        lblFechaHasta.setText("Hasta:");

        btnAplicarFiltroFecha.setText("Buscar");
        btnAplicarFiltroFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarFiltroFechaActionPerformed(evt);
            }
        });

        lblPelicula.setText("Pelicula:");

        jLabel1.setText("Sala:");

        lblPorSala.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        lblPorSala.setText("Por Pelicula");

        lblPorFecha1.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        lblPorFecha1.setText("Por Fecha");

        lblPorSala1.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        lblPorSala1.setText("Por Sala");

        javax.swing.GroupLayout pnlTicketsLayout = new javax.swing.GroupLayout(pnlTickets);
        pnlTickets.setLayout(pnlTicketsLayout);
        pnlTicketsLayout.setHorizontalGroup(
            pnlTicketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTicketsLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(lblTitulo))
            .addGroup(pnlTicketsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblReporte))
            .addGroup(pnlTicketsLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lblPorSala)
                .addGap(94, 94, 94)
                .addComponent(lblPorSala1)
                .addGap(201, 201, 201)
                .addComponent(lblPorFecha1))
            .addGroup(pnlTicketsLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblPelicula)
                .addGap(21, 21, 21)
                .addComponent(cmbPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(cmbSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(lblFechaDesde)
                .addGap(16, 16, 16)
                .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(lblFechaHasta)
                .addGap(18, 18, 18)
                .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnAplicarFiltroFecha))
            .addGroup(pnlTicketsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlTicketsLayout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(btnActualizar)
                .addGap(21, 21, 21)
                .addComponent(btnHoy)
                .addGap(29, 29, 29)
                .addComponent(btnTodos)
                .addGap(19, 19, 19)
                .addComponent(btnExportar))
        );
        pnlTicketsLayout.setVerticalGroup(
            pnlTicketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTicketsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTitulo)
                .addGap(38, 38, 38)
                .addComponent(lblReporte)
                .addGap(6, 6, 6)
                .addGroup(pnlTicketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPorSala)
                    .addComponent(lblPorSala1)
                    .addComponent(lblPorFecha1))
                .addGap(16, 16, 16)
                .addGroup(pnlTicketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTicketsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblPelicula))
                    .addComponent(cmbPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmbSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaDesde)
                    .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaHasta)
                    .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAplicarFiltroFecha))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnlTicketsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizar)
                    .addComponent(btnHoy)
                    .addComponent(btnTodos)
                    .addComponent(btnExportar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        aplicarFiltros();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoyActionPerformed
        establecerFechaHoy();
        cargarDatosHoy();
    }//GEN-LAST:event_btnHoyActionPerformed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        limpiarFiltros();
        cargarDatosCompletos();
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        exportarReporte();
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnAplicarFiltroFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarFiltroFechaActionPerformed
        aplicarFiltroFecha();
    }//GEN-LAST:event_btnAplicarFiltroFechaActionPerformed

    private void cmbSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSalaActionPerformed
        aplicarFiltroSala();
    }//GEN-LAST:event_cmbSalaActionPerformed

    private void cmbPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPeliculaActionPerformed
        aplicarFiltroPelicula();
    }//GEN-LAST:event_cmbPeliculaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAplicarFiltroFecha;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnHoy;
    private javax.swing.JButton btnTodos;
    private javax.swing.JComboBox<String> cmbPelicula;
    private javax.swing.JComboBox<String> cmbSala;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaDesde;
    private javax.swing.JLabel lblFechaHasta;
    private javax.swing.JLabel lblPelicula;
    private javax.swing.JLabel lblPorFecha1;
    private javax.swing.JLabel lblPorSala;
    private javax.swing.JLabel lblPorSala1;
    private javax.swing.JLabel lblReporte;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable modeloTabla;
    private javax.swing.JPanel pnlTickets;
    private javax.swing.JTextField txtFechaDesde;
    private javax.swing.JTextField txtFechaHasta;
    // End of variables declaration//GEN-END:variables

}
