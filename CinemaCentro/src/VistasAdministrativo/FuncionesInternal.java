package VistasAdministrativo;

import Controlador.FuncionDAO;
import Controlador.PeliculaDAO;
import Controlador.SalaDAO;
import Modelo.Funcion;
import Modelo.Pelicula;
import Modelo.Sala;
import java.time.LocalDate;
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
    private boolean modoEdicion = false;

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
        btnConfirmar.setEnabled(true);
    }

    private void inicializarClickTablas() {
        tblPeliculas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblPeliculas.getSelectedRow() != -1) {
                int fila = tblPeliculas.getSelectedRow();
                try {
                    peliculaSeleccionada = tblPeliculas.getValueAt(fila, 1).toString();
                    idPeliculaSeleccionada = Integer.parseInt(tblPeliculas.getValueAt(fila, 0).toString());
                    lblPeliculaSeleccionada.setText(peliculaSeleccionada);
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

        btnGtipo3D = new javax.swing.ButtonGroup();
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
        lblTipoPelicula = new javax.swing.JLabel();
        rbtn2D = new javax.swing.JRadioButton();
        rbtn3D = new javax.swing.JRadioButton();
        lblIdioma = new javax.swing.JLabel();
        jComboBoxIdioma = new javax.swing.JComboBox<>();
        lblListaPeliculas = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btndarbaja = new javax.swing.JButton();
        lblSubtitulada = new javax.swing.JLabel();
        jComboBoxSubtitulada = new javax.swing.JComboBox<>();
        txtFechaFuncion = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblEstadoFuncion = new javax.swing.JLabel();
        txtFHoraInicio = new javax.swing.JTextField();
        lblHoraInicio = new javax.swing.JLabel();
        lblHoraFin = new javax.swing.JLabel();
        txtFHoraFin = new javax.swing.JTextField();
        lblValorEntrada = new javax.swing.JLabel();
        txtFValorEntrada = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        lblPelicula = new javax.swing.JLabel();
        lblPeliculaSeleccionada = new javax.swing.JLabel();
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

        lblTipoPelicula.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblTipoPelicula.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoPelicula.setText("Tipo:");

        btnGtipo3D.add(rbtn2D);
        rbtn2D.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        rbtn2D.setForeground(new java.awt.Color(255, 255, 255));
        rbtn2D.setText("2D");

        btnGtipo3D.add(rbtn3D);
        rbtn3D.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        rbtn3D.setForeground(new java.awt.Color(255, 255, 255));
        rbtn3D.setText("3D");

        lblIdioma.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblIdioma.setForeground(new java.awt.Color(255, 255, 255));
        lblIdioma.setText("Idioma:");

        jComboBoxIdioma.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jComboBoxIdioma.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxIdioma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Español", "Inglés" }));
        jComboBoxIdioma.setSelectedIndex(-1);
        jComboBoxIdioma.setToolTipText("Elija idioma");

        lblListaPeliculas.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        lblListaPeliculas.setForeground(new java.awt.Color(255, 255, 255));
        lblListaPeliculas.setText("Peliculas:");

        btnConfirmar.setBackground(new java.awt.Color(70, 73, 75));
        btnConfirmar.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/modificar.png"))); // NOI18N
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btndarbaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/dar-baja.png"))); // NOI18N
        btndarbaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndarbajaActionPerformed(evt);
            }
        });

        lblSubtitulada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblSubtitulada.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtitulada.setText("Subtitulada:");

        jComboBoxSubtitulada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jComboBoxSubtitulada.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxSubtitulada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Español", "Inglés" }));
        jComboBoxSubtitulada.setSelectedIndex(-1);
        jComboBoxSubtitulada.setToolTipText("Elija idioma");

        txtFechaFuncion.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        txtFechaFuncion.setForeground(new java.awt.Color(0, 0, 0));
        txtFechaFuncion.setText("yyyy-MM-dd");

        lblFecha.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha de Función:");

        lblEstado.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(255, 255, 255));
        lblEstado.setText("Estado:");

        lblEstadoFuncion.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblEstadoFuncion.setForeground(new java.awt.Color(255, 255, 255));

        txtFHoraInicio.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        txtFHoraInicio.setForeground(new java.awt.Color(0, 0, 0));
        txtFHoraInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFHoraInicio.setText("HH:ss");
        txtFHoraInicio.setToolTipText("hh:ss");

        lblHoraInicio.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblHoraInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblHoraInicio.setText("Horario Inicio:");

        lblHoraFin.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblHoraFin.setForeground(new java.awt.Color(255, 255, 255));
        lblHoraFin.setText("Horario Fin:");

        txtFHoraFin.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        txtFHoraFin.setForeground(new java.awt.Color(0, 0, 0));
        txtFHoraFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFHoraFin.setText("HH:ss");
        txtFHoraFin.setToolTipText("hh:ss");

        lblValorEntrada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblValorEntrada.setForeground(new java.awt.Color(255, 255, 255));
        lblValorEntrada.setText("Valor Entrada:");

        txtFValorEntrada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N

        btnSalir.setBackground(new java.awt.Color(70, 73, 75));
        btnSalir.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblPelicula.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        lblPelicula.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula.setText("Pelicula:");

        lblPeliculaSeleccionada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblPeliculaSeleccionada.setForeground(new java.awt.Color(255, 255, 255));
        lblPeliculaSeleccionada.setText("pelicula a ver");

        btndarAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/dar-alta.png"))); // NOI18N
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
                .addGap(6, 6, 6)
                .addComponent(scrollPanePeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneSala, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionesLayout.createSequentialGroup()
                                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btndarAlta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btndarbaja)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btneliminar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnmodificar)
                                        .addGap(6, 6, 6))
                                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                                .addComponent(lblHoraInicio)
                                                .addGap(10, 10, 10)
                                                .addComponent(txtFHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                                .addComponent(lblPelicula)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblPeliculaSeleccionada))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionesLayout.createSequentialGroup()
                                                .addComponent(lblHoraFin)
                                                .addGap(10, 10, 10)
                                                .addComponent(txtFHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionesLayout.createSequentialGroup()
                                                .addComponent(lblTipoPelicula)
                                                .addGap(10, 10, 10)
                                                .addComponent(rbtn2D)
                                                .addGap(6, 6, 6)
                                                .addComponent(rbtn3D)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionesLayout.createSequentialGroup()
                                                .addComponent(lblIdioma)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionesLayout.createSequentialGroup()
                                                .addComponent(lblSubtitulada)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFuncionesLayout.createSequentialGroup()
                                        .addComponent(lblFecha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(23, 23, 23))
                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                        .addComponent(lblValorEntrada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFValorEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                        .addComponent(lblEstado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblEstadoFuncion)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionesLayout.createSequentialGroup()
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPaneDesc))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFuncionesLayout.createSequentialGroup()
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addComponent(lblFunciones)
                                .addGap(18, 18, 18)
                                .addComponent(lblCinemaCentro))
                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblListaPeliculas)
                                .addGap(88, 88, 88)
                                .addComponent(lblSala))
                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblDescripcionFuncion)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );
        pnlFuncionesLayout.setVerticalGroup(
            pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblFunciones, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCinemaCentro))
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaPeliculas)
                    .addComponent(lblSala))
                .addGap(19, 19, 19)
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPanePeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPaneSala, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPelicula)
                            .addComponent(lblPeliculaSeleccionada))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHoraInicio)
                            .addComponent(txtFHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIdioma)
                            .addComponent(jComboBoxIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHoraFin)
                            .addComponent(txtFHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSubtitulada)
                            .addComponent(jComboBoxSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtn2D)
                                .addComponent(lblTipoPelicula))
                            .addComponent(rbtn3D))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFecha)
                            .addComponent(txtFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValorEntrada)
                            .addComponent(txtFValorEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstado)
                            .addComponent(lblEstadoFuncion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmar)))
                .addGap(18, 18, 18)
                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFuncionesLayout.createSequentialGroup()
                        .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFuncionesLayout.createSequentialGroup()
                                .addComponent(lblDescripcionFuncion)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFuncionesLayout.createSequentialGroup()
                                .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btndarAlta, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnmodificar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(8, 8, 8)))
                        .addComponent(scrollPaneDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnSalir))
                    .addGroup(pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btneliminar)
                        .addComponent(btndarbaja))))
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

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            if (peliculaSeleccionada == null || idPeliculaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una película.");
                return;
            }

            if (salaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una sala.");
                return;
            }

            String idioma = (String) jComboBoxIdioma.getSelectedItem();
            String subtituladaStr = (String) jComboBoxSubtitulada.getSelectedItem();
            String tipo = rbtn2D.isSelected() ? "2D" : rbtn3D.isSelected() ? "3D" : null;
            String horaInicio = txtFHoraInicio.getText().trim();
            String horaFin = txtFHoraFin.getText().trim();
            String fecha = txtFechaFuncion.getText().trim();
            String valorStr = txtFValorEntrada.getText().trim();

            if (idioma == null || subtituladaStr == null || tipo == null
                    || horaInicio.isEmpty() || horaFin.isEmpty() || fecha.isEmpty() || valorStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
                return;
            }

            int nroSala;
            double valorEntrada;

            try {
                nroSala = Integer.parseInt(salaSeleccionada);
                valorEntrada = Double.parseDouble(valorStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Formato inválido en sala o valor de entrada.");
                return;
            }

            LocalDate fechaFuncion;
            try {
                fechaFuncion = LocalDate.parse(fecha);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use yyyy-MM-dd.");
                return;
            }

            java.sql.Time horaInicioTime, horaFinTime;
            try {
                if (!horaInicio.contains(":")) {
                    throw new Exception("Formato de hora inválido");
                }
                if (!horaFin.contains(":")) {
                    throw new Exception("Formato de hora inválido");
                }
                horaInicioTime = java.sql.Time.valueOf(horaInicio + ":00");
                horaFinTime = java.sql.Time.valueOf(horaFin + ":00");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de hora inválido. Use HH:mm (ej: 14:30)");
                return;
            }

            boolean es3D = tipo.equals("3D");
            boolean subtitulada = !subtituladaStr.equals("No");

            Funcion funcion = new Funcion();
            funcion.setId_pelicula(idPeliculaSeleccionada);
            funcion.setNro_Sala(nroSala);
            funcion.setIdioma(idioma);
            funcion.setEs3D(es3D);
            funcion.setHora_Inicio(horaInicioTime);
            funcion.setHora_Fin(horaFinTime);
            funcion.setPrecio_Entrada(valorEntrada);
            funcion.setFecha_Funcion(fechaFuncion);
            funcion.setSubtitulada(subtitulada);
            funcion.setEstado(true);

            funcionDAO.agregarFuncion(funcion);

            JOptionPane.showMessageDialog(this, "Función registrada correctamente en la base de datos.");

            limpiarCampos();
            cargarFunciones();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar función: " + e.getMessage());
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

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
            cargarDatosFuncionEnFormulario(funcionSeleccionada);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja función: " + e.getMessage());
            e.printStackTrace();
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
                limpiarCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar función: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        if (funcionSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una función para modificar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea modificar esta función?",
                "Confirmar modificación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            guardarCambiosFuncion();
        }
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
            cargarDatosFuncionEnFormulario(funcionSeleccionada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de alta función: " + e.getMessage());
            e.printStackTrace();
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

                        cargarDatosFuncionEnFormulario(funcion);
                        btnmodificar.setEnabled(true);
                        btneliminar.setEnabled(true);
                        btndarAlta.setEnabled(true);
                        btndarbaja.setEnabled(true);
                        btnConfirmar.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo cargar la función seleccionada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo encontrar la función seleccionada.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar la función seleccionada: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_tblDescripcionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.ButtonGroup btnGtipo3D;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btndarAlta;
    private javax.swing.JButton btndarbaja;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JComboBox<String> jComboBoxIdioma;
    private javax.swing.JComboBox<String> jComboBoxSubtitulada;
    private javax.swing.JLabel lblCinemaCentro;
    private javax.swing.JLabel lblDescripcionFuncion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblEstadoFuncion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFunciones;
    private javax.swing.JLabel lblHoraFin;
    private javax.swing.JLabel lblHoraInicio;
    private javax.swing.JLabel lblIdioma;
    private javax.swing.JLabel lblListaPeliculas;
    private javax.swing.JLabel lblPelicula;
    private javax.swing.JLabel lblPeliculaSeleccionada;
    private javax.swing.JLabel lblSala;
    private javax.swing.JLabel lblSubtitulada;
    private javax.swing.JLabel lblTipoPelicula;
    private javax.swing.JLabel lblValorEntrada;
    private javax.swing.JPanel pnlFunciones;
    private javax.swing.JRadioButton rbtn2D;
    private javax.swing.JRadioButton rbtn3D;
    private javax.swing.JScrollPane scrollPaneDesc;
    private javax.swing.JScrollPane scrollPanePeliculas;
    private javax.swing.JScrollPane scrollPaneSala;
    private javax.swing.JTable tblDescripcion;
    private javax.swing.JTable tblPeliculas;
    private javax.swing.JTable tblSala;
    private javax.swing.JTextField txtFHoraFin;
    private javax.swing.JTextField txtFHoraInicio;
    private javax.swing.JTextField txtFValorEntrada;
    private javax.swing.JTextField txtFechaFuncion;
    // End of variables declaration//GEN-END:variables

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

    private void cargarDatosFuncionEnFormulario(Funcion funcion) {
        try {
            funcionSeleccionada = funcion;
            peliculaSeleccionada = obtenerNombrePelicula(funcion.getId_pelicula());
            idPeliculaSeleccionada = funcion.getId_pelicula();
            lblPeliculaSeleccionada.setText(peliculaSeleccionada);
            salaSeleccionada = String.valueOf(funcion.getNro_Sala());

            seleccionarPeliculaEnTabla(funcion.getId_pelicula());
            seleccionarSalaEnTabla(funcion.getNro_Sala());
            jComboBoxIdioma.setSelectedItem(funcion.getIdioma());

            String subtituladaStr = funcion.isSubtitulada()
                    ? (funcion.getIdioma().equals("Español") ? "Inglés" : "Español") : "No";
            jComboBoxSubtitulada.setSelectedItem(subtituladaStr);

            if (funcion.isEs3D()) {
                rbtn3D.setSelected(true);
            } else {
                rbtn2D.setSelected(true);
            }

            String horaInicio = funcion.getHora_Inicio().toString().substring(0, 5);
            String horaFin = funcion.getHora_Fin().toString().substring(0, 5);
            txtFHoraInicio.setText(horaInicio);
            txtFHoraFin.setText(horaFin);
            txtFechaFuncion.setText(funcion.getFecha_Funcion().toString());
            txtFValorEntrada.setText(String.valueOf(funcion.getPrecio_Entrada()));
            lblEstadoFuncion.setText(funcion.isEstado() ? "Activa" : "Inactiva");
            funcionSeleccionada = funcion;

        } catch (Exception e) {
            System.err.println("ERROR en cargarDatosFuncionEnFormulario: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar datos de la función: " + e.getMessage());
        }
    }

    private void seleccionarPeliculaEnTabla(int idPelicula) {
        for (int i = 0; i < tblPeliculas.getRowCount(); i++) {
            int id = Integer.parseInt(tblPeliculas.getValueAt(i, 0).toString());
            if (id == idPelicula) {
                tblPeliculas.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    private void seleccionarSalaEnTabla(int nroSala) {
        for (int i = 0; i < tblSala.getRowCount(); i++) {
            int sala = Integer.parseInt(tblSala.getValueAt(i, 0).toString());
            if (sala == nroSala) {
                tblSala.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    private void guardarCambiosFuncion() {
        try {
            if (funcionSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "No hay función seleccionada para modificar.");
                return;
            }
            String idioma = (String) jComboBoxIdioma.getSelectedItem();
            String subtituladaStr = (String) jComboBoxSubtitulada.getSelectedItem();
            String tipo = rbtn2D.isSelected() ? "2D" : rbtn3D.isSelected() ? "3D" : null;
            String horaInicio = txtFHoraInicio.getText().trim();
            String horaFin = txtFHoraFin.getText().trim();
            String fecha = txtFechaFuncion.getText().trim();
            String valorStr = txtFValorEntrada.getText().trim();
            if (idioma == null || subtituladaStr == null || tipo == null
                    || horaInicio.isEmpty() || horaFin.isEmpty() || fecha.isEmpty() || valorStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
                return;
            }
            int nroSala = Integer.parseInt(salaSeleccionada);
            double valorEntrada = Double.parseDouble(valorStr);
            LocalDate fechaFuncion = LocalDate.parse(fecha);
            java.sql.Time horaInicioTime, horaFinTime;
            try {
                horaInicioTime = java.sql.Time.valueOf(horaInicio + ":00");
                horaFinTime = java.sql.Time.valueOf(horaFin + ":00");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de hora inválido. Use HH:mm");
                return;
            }
            boolean es3D = tipo.equals("3D");
            boolean subtitulada = !subtituladaStr.equals("No");
            funcionSeleccionada.setId_pelicula(idPeliculaSeleccionada);
            funcionSeleccionada.setNro_Sala(nroSala);
            funcionSeleccionada.setIdioma(idioma);
            funcionSeleccionada.setEs3D(es3D);
            funcionSeleccionada.setHora_Inicio(horaInicioTime);
            funcionSeleccionada.setHora_Fin(horaFinTime);
            funcionSeleccionada.setPrecio_Entrada(valorEntrada);
            funcionSeleccionada.setFecha_Funcion(fechaFuncion);
            funcionSeleccionada.setSubtitulada(subtitulada);
            JOptionPane.showMessageDialog(this, "Función modificada correctamente.");

            cargarFunciones();
            limpiarCampos();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al modificar función: " + e.getMessage());
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

    private void cargarFunciones() {
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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

    private void limpiarCampos() {
        btnGtipo3D.clearSelection();
        jComboBoxIdioma.setSelectedIndex(-1);
        jComboBoxSubtitulada.setSelectedIndex(-1);
        txtFHoraInicio.setText("");
        txtFHoraFin.setText("");
        txtFechaFuncion.setText("yyyy-MM-dd");
        txtFValorEntrada.setText("");
        tblPeliculas.clearSelection();
        tblSala.clearSelection();
        peliculaSeleccionada = null;
        salaSeleccionada = null;
        idPeliculaSeleccionada = -1;
        lblPeliculaSeleccionada.setText("");
        lblEstadoFuncion.setText("");
        funcionSeleccionada = null;

        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
        btndarAlta.setEnabled(false);
        btndarbaja.setEnabled(false);
        btnConfirmar.setEnabled(true);

    }
}
