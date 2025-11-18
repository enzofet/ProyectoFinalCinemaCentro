package VistasAdministrativo;

import Controlador.FuncionDAO;
import Controlador.PeliculaDAO;
import Controlador.SalaDAO;
import Modelo.Funcion;
import Modelo.Pelicula;
import Modelo.Sala;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FuncionesInternal extends javax.swing.JInternalFrame {

    String[] cabeceras = {"ID", "Titulo"};
    private PeliculaDAO peliculaDAO = new PeliculaDAO();

    String[] cabecerass = {"Sala", "Capacidad", "Estado", "Es 3D"};
    private SalaDAO salaDAO = new SalaDAO();

    String[] cabeceraDescripcion = {"Película", "Sala", "Hora Inicio", "Hora Fin", "Idioma", "Subtitulada", "Tipo", "Fecha", "Valor de Entrada", "Estado"};
    DefaultTableModel modeloDescripcion = new DefaultTableModel(cabeceraDescripcion, 0);

    private FuncionDAO funcionDAO = new FuncionDAO();

    private String peliculaSeleccionada = null;
    private String salaSeleccionada = null;
    private int idPeliculaSeleccionada = -1;
    private Funcion funcionSeleccionada = null;

    public FuncionesInternal() {
        initComponents();
        cargarTablaPeliculas();
        cargarSalas();
        configurarTablaDescripcion();
        ocultarID();
        inicializarClickTablas();
        cargarFunciones();
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
        btndarAlta.setEnabled(false);
        btndarbaja.setEnabled(false);
        btnAgregar.setEnabled(true);
    }

    private void inicializarClickTablas() {
        tblPeliculas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblPeliculas.getSelectedRow() != -1) {
                int fila = tblPeliculas.getSelectedRow();
                try {
                    peliculaSeleccionada = tblPeliculas.getValueAt(fila, 1).toString();
                    idPeliculaSeleccionada = Integer.parseInt(tblPeliculas.getValueAt(fila, 0).toString());
                } catch (NumberFormatException ex) {
                    idPeliculaSeleccionada = -1;
                }
            }
        });

        tblSala.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblSala.getSelectedRow() != -1) {
                int fila = tblSala.getSelectedRow();
                salaSeleccionada = tblSala.getValueAt(fila, 0).toString();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFunciones = new javax.swing.JPanel();
        lblCinemaCentro = new javax.swing.JLabel();
        lblFunciones = new javax.swing.JLabel();
        lblSala = new javax.swing.JLabel();
        scrollPanePeliculas = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        scrollPaneSala = new javax.swing.JScrollPane();
        tblSala = new javax.swing.JTable();
        lblDescripcionFuncion = new javax.swing.JLabel();
        scrollPaneDesc = new javax.swing.JScrollPane();
        tblDescripcion = new javax.swing.JTable();
        lblListaPeliculas = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btndarbaja = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btndarAlta = new javax.swing.JButton();

        pnlFunciones.setBackground(new java.awt.Color(102, 0, 0));

        lblCinemaCentro.setFont(new java.awt.Font("Roboto Black", 1, 36)); // NOI18N
        lblCinemaCentro.setForeground(new java.awt.Color(255, 255, 255));
        lblCinemaCentro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCinemaCentro.setText("CinemaCentro");

        lblFunciones.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        lblFunciones.setForeground(new java.awt.Color(255, 255, 255));
        lblFunciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFunciones.setText("REGISTRAR FUNCIONES");

        lblSala.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        lblSala.setForeground(new java.awt.Color(255, 255, 255));
        lblSala.setText("Sala:");

        tblPeliculas.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollPanePeliculas.setViewportView(tblPeliculas);

        tblSala.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        tblSala.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollPaneSala.setViewportView(tblSala);

        lblDescripcionFuncion.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        lblDescripcionFuncion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionFuncion.setText("Descripción de la Función:");

        tblDescripcion.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        tblDescripcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDescripcionMouseClicked(evt);
            }
        });
        scrollPaneDesc.setViewportView(tblDescripcion);

        lblListaPeliculas.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        lblListaPeliculas.setForeground(new java.awt.Color(255, 255, 255));
        lblListaPeliculas.setText("Películas:");

        btnAgregar.setBackground(new java.awt.Color(70, 73, 75));
        btnAgregar.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(70, 73, 75));
        btneliminar.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(255, 255, 255));
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnmodificar.setBackground(new java.awt.Color(70, 73, 75));
        btnmodificar.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        btnmodificar.setForeground(new java.awt.Color(255, 255, 255));
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar.png"))); // NOI18N
        btnmodificar.setText("Modificar");
        btnmodificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btndarbaja.setBackground(new java.awt.Color(70, 73, 75));
        btndarbaja.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        btndarbaja.setForeground(new java.awt.Color(255, 255, 255));
        btndarbaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/dar-baja.png"))); // NOI18N
        btndarbaja.setText("Dar de Baja");
        btndarbaja.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btndarbaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndarbajaActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(70, 73, 75));
        btnSalir.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btndarAlta.setBackground(new java.awt.Color(70, 73, 75));
        btndarAlta.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        btndarAlta.setForeground(new java.awt.Color(255, 255, 255));
        btndarAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/dar-alta.png"))); // NOI18N
        btndarAlta.setText("Dar de Alta");
        btndarAlta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btndarAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndarAltaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFuncionesLayout = new javax.swing.GroupLayout(pnlFunciones);
        pnlFunciones.setLayout(pnlFuncionesLayout);
        pnlFuncionesLayout.setHorizontalGroup(
            pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalir)
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblFunciones)
                                .addGap(18, 18, 18)
                                .addComponent(lblCinemaCentro))
                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDescripcionFuncion)
                                    .addComponent(scrollPaneDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                                .addComponent(lblListaPeliculas)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(scrollPanePeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                                .addComponent(scrollPaneSala, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btndarAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btndarbaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(lblSala))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFuncionesLayout.setVerticalGroup(
            pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFunciones, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCinemaCentro))
                .addGap(29, 29, 29)
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblListaPeliculas)
                    .addComponent(lblSala))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionesLayout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btndarAlta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btndarbaja)
                        .addGap(42, 42, 42))
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollPanePeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPaneSala, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(lblDescripcionFuncion)
                .addGap(6, 6, 6)
                .addComponent(scrollPaneDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFunciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFunciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        abrirDialogoNuevaFuncion();
        limpiarSeleccionFuncion();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btndarbajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndarbajaActionPerformed
        if (funcionSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una función para dar de baja.");
            return;
        }

        try {
            funcionDAO.darBajaFuncion(funcionSeleccionada.getId_Funcion());
            JOptionPane.showMessageDialog(this, "Función dada de baja correctamente.");
            cargarFunciones();
            funcionSeleccionada = funcionDAO.buscarFuncionPorId(funcionSeleccionada.getId_Funcion());
            limpiarSeleccionFuncion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja función: " + e.getMessage());
        }
    }//GEN-LAST:event_btndarbajaActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if (funcionSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una función para eliminar.");
            return;
        }

        try {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que quieres eliminar esta función?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                funcionDAO.eliminarFuncion(funcionSeleccionada.getId_Funcion());
                JOptionPane.showMessageDialog(this, "Función eliminada correctamente.");
                cargarFunciones();
                limpiarSeleccionFuncion();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar función: " + e.getMessage());
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        abrirDialogoModificarFuncion();
        limpiarSeleccionFuncion();
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btndarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndarAltaActionPerformed
        if (funcionSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una función para darla de alta.");
            return;
        }

        try {
            funcionDAO.darAltaFuncion(funcionSeleccionada.getId_Funcion());
            JOptionPane.showMessageDialog(this, "Función dada de alta correctamente.");
            cargarFunciones();
            funcionSeleccionada = funcionDAO.buscarFuncionPorId(funcionSeleccionada.getId_Funcion());
            limpiarSeleccionFuncion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de alta función: " + e.getMessage());
        }
    }//GEN-LAST:event_btndarAltaActionPerformed

    private void tblDescripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDescripcionMouseClicked
        int fila = tblDescripcion.getSelectedRow();
        if (fila != -1) {
            try {
                String nombrePelicula = tblDescripcion.getValueAt(fila, 0).toString();
                int nroSala = Integer.parseInt(tblDescripcion.getValueAt(fila, 1).toString());
                String horaInicio = tblDescripcion.getValueAt(fila, 2).toString();
                String fecha = tblDescripcion.getValueAt(fila, 7).toString();
                int idFuncion = obtenerIdFuncionCompleto(nombrePelicula, nroSala, horaInicio, fecha);
                if (idFuncion != -1) {
                    Funcion funcion = funcionDAO.buscarFuncionPorId(idFuncion);
                    if (funcion != null) {
                        funcionSeleccionada = funcion;
                        btnmodificar.setEnabled(true);
                        btneliminar.setEnabled(true);
                        btndarAlta.setEnabled(true);
                        btndarbaja.setEnabled(true);
                        btnAgregar.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo cargar la función seleccionada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo encontrar la función seleccionada.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la función seleccionada: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_tblDescripcionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btndarAlta;
    private javax.swing.JButton btndarbaja;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JLabel lblCinemaCentro;
    private javax.swing.JLabel lblDescripcionFuncion;
    private javax.swing.JLabel lblFunciones;
    private javax.swing.JLabel lblListaPeliculas;
    private javax.swing.JLabel lblSala;
    private javax.swing.JPanel pnlFunciones;
    private javax.swing.JScrollPane scrollPaneDesc;
    private javax.swing.JScrollPane scrollPanePeliculas;
    private javax.swing.JScrollPane scrollPaneSala;
    private javax.swing.JTable tblDescripcion;
    private javax.swing.JTable tblPeliculas;
    private javax.swing.JTable tblSala;
    // End of variables declaration//GEN-END:variables

    private void abrirDialogoNuevaFuncion() {
        if (peliculaSeleccionada == null || idPeliculaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una película.");
            return;
        }

        if (salaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una sala.");
            return;
        }

        DialogCrearFuncion dialog = new DialogCrearFuncion(
                (java.awt.Frame) this.getTopLevelAncestor(),
                true,
                this
        );

        dialog.setPeliculaSeleccionada(peliculaSeleccionada, idPeliculaSeleccionada);
        dialog.setSalaSeleccionada(salaSeleccionada, Integer.parseInt(salaSeleccionada));

        dialog.setVisible(true);
    }

    private void abrirDialogoModificarFuncion() {
        if (funcionSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una función para modificar.");
            return;
        }

        DialogCrearFuncion dialog = new DialogCrearFuncion(
                (java.awt.Frame) this.getTopLevelAncestor(),
                true,
                this,
                funcionSeleccionada
        );
        dialog.setVisible(true);
    }

    private void configurarTablaDescripcion() {
        modeloDescripcion = new DefaultTableModel(
                new Object[]{"Película", "Sala", "Hora Inicio", "Hora Fin", "Idioma", "Subtitulada", "Tipo", "Fecha", "Valor Entrada", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDescripcion.setModel(modeloDescripcion);
    }

    private void cargarTablaPeliculas() {
        try {
            List<Pelicula> peliculas = peliculaDAO.listarTodasPeliculas();
            DefaultTableModel model = VentanaAdministrativo.armarCabeceras(cabeceras);
            for (Pelicula p : peliculas) {
                model.addRow(new Object[]{
                    p.getId_Pelicula(),
                    p.getTitulo(),
                    p.getDirector(),
                    p.isEnCartelera() ? "Sí" : "No",
                    p.getEstreno(),
                    p.isEstado() ? "Activo" : "Inactivo"
                });
            }
            tblPeliculas.setModel(model);
            ocultarID();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla: " + e.getMessage());
        }
    }

    private void cargarSalas() {
        try {
            List<Sala> listaSalas = salaDAO.listarsalas();
            DefaultTableModel modelo = VentanaAdministrativo.armarCabeceras(cabecerass);
            for (Sala s : listaSalas) {
                modelo.addRow(new Object[]{
                    s.getNro_Sala(),
                    s.getCapacidad(),
                    parsearBoolean(s.isEstado()),
                    parsearBoolean(s.isApta3D())
                });
            }
            tblSala.setModel(modelo);
            ocultarID();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar salas: " + e.getMessage());
        }
    }

    public void cargarFunciones() {
        try {
            modeloDescripcion.setRowCount(0);

            List<Funcion> funciones = funcionDAO.listarFunciones();

            for (Funcion funcion : funciones) {
                String nombrePelicula = obtenerNombrePelicula(funcion.getId_pelicula());
                String datosSala = String.valueOf(funcion.getNro_Sala());
                String subtituladaStr = funcion.isSubtitulada()
                        ? (funcion.getIdioma().equals("Español") ? "Inglés" : "Español") : "No";
                String tipo = funcion.isEs3D() ? "3D" : "2D";
                String estado = funcion.isEstado() ? "Activa" : "Inactiva";
                String horaInicio = funcion.getHora_Inicio().toString().substring(0, 5);
                String horaFin = funcion.getHora_Fin().toString().substring(0, 5);

                modeloDescripcion.addRow(new Object[]{
                    nombrePelicula,
                    datosSala,
                    horaInicio,
                    horaFin,
                    funcion.getIdioma(),
                    subtituladaStr,
                    tipo,
                    funcion.getFecha_Funcion().toString(),
                    String.valueOf(funcion.getPrecio_Entrada()),
                    estado
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar funciones: " + e.getMessage());
        }
    }

    private String obtenerNombrePelicula(int idPelicula) {
        try {
            List<Pelicula> peliculas = peliculaDAO.listarTodasPeliculas();
            for (Pelicula pelicula : peliculas) {
                if (pelicula.getId_Pelicula() == idPelicula) {
                    return pelicula.getTitulo();
                }
            }
        } catch (Exception e) {
        }
        return "desconocida";
    }

    private int obtenerIdFuncionCompleto(String nombrePelicula, int nroSala, String horaInicio, String fecha) {
        try {
            List<Funcion> funciones = funcionDAO.listarFunciones();
            for (Funcion funcion : funciones) {
                if (funcion.getNro_Sala() == nroSala
                        && funcion.getFecha_Funcion().toString().equals(fecha)) {
                    String horaFuncion = funcion.getHora_Inicio().toString().substring(0, 5);
                    if (horaFuncion.equals(horaInicio)) {
                        String nombreFuncionPelicula = obtenerNombrePelicula(funcion.getId_pelicula());
                        if (nombreFuncionPelicula.equals(nombrePelicula)) {
                            return funcion.getId_Funcion();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public String parsearBoolean(boolean estado) {
        if (estado) {
            return "Activo";
        }
        return "Inactivo";
    }

    private void ocultarID() {
        if (tblPeliculas.getColumnCount() > 0) {
            tblPeliculas.getColumnModel().getColumn(0).setMinWidth(0);
            tblPeliculas.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPeliculas.getColumnModel().getColumn(0).setWidth(0);
            tblPeliculas.getColumnModel().getColumn(0).setPreferredWidth(0);
        }
    }
    
    private void limpiarSeleccionFuncion() {
        funcionSeleccionada = null;
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
        btndarAlta.setEnabled(false);
        btndarbaja.setEnabled(false);
        btnAgregar.setEnabled(true);
        tblDescripcion.clearSelection();
    }
}
