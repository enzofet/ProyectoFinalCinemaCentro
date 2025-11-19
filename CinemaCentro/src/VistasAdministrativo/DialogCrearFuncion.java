package VistasAdministrativo;

import Controlador.FuncionDAO;
import Controlador.PeliculaDAO;
import Controlador.SalaDAO;
import Modelo.Funcion;
import Modelo.Pelicula;
import java.awt.Frame;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author emadupre
 */
public class DialogCrearFuncion extends javax.swing.JDialog {

    private FuncionDAO funcionDAO = new FuncionDAO();
    private PeliculaDAO peliculaDAO = new PeliculaDAO();
    private SalaDAO salaDAO = new SalaDAO();

    private FuncionesInternal parentFrame;
    private Funcion funcionSeleccionada;
    private boolean modoEdicion;

    private String peliculaSeleccionada;
    private String salaSeleccionada;
    private int idPeliculaSeleccionada = -1;
    private int nroSalaSeleccionada = -1;

    public DialogCrearFuncion(Frame parent, boolean modal, FuncionesInternal parentFrame) {
        super(parent, modal);
        this.parentFrame = parentFrame;
        this.modoEdicion = false;
        initComponents();
        configurarDialogo();
        setLocationRelativeTo(parent);
    }

    public DialogCrearFuncion(java.awt.Frame parent, boolean modal, FuncionesInternal parentFrame, Funcion funcion) {
        super(parent, modal);
        this.parentFrame = parentFrame;
        this.funcionSeleccionada = funcion;
        this.modoEdicion = true;
        initComponents();
        configurarDialogo();
        cargarDatosFuncion();
        setLocationRelativeTo(parent);
    }

    private void configurarDialogo() {
        if (modoEdicion) {
            setTitle("Modificar Función");
            btnConfirmar.setText("Guardar Cambios");
            lblTitulo.setText("MODIFICAR FUNCIÓN");
        } else {
            setTitle("Nueva Función");
            btnConfirmar.setText("Confirmar");
            lblTitulo.setText("NUEVA FUNCIÓN");
        }

        cargarDatosPeliculaYSala();
    }

    private void cargarDatosPeliculaYSala() {
        try {
            if (modoEdicion && funcionSeleccionada != null) {
                peliculaSeleccionada = obtenerNombrePelicula(funcionSeleccionada.getId_pelicula());
                idPeliculaSeleccionada = funcionSeleccionada.getId_pelicula();
                lblPeliculaSeleccionada.setText(peliculaSeleccionada);

                salaSeleccionada = String.valueOf(funcionSeleccionada.getNro_Sala());
                nroSalaSeleccionada = funcionSeleccionada.getNro_Sala();
                lblSalaSeleccionada.setText("Sala: " + salaSeleccionada);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        }
    }

    private void cargarDatosFuncion() {
        if (funcionSeleccionada != null) {
            try {
                peliculaSeleccionada = obtenerNombrePelicula(funcionSeleccionada.getId_pelicula());
                idPeliculaSeleccionada = funcionSeleccionada.getId_pelicula();
                lblPeliculaSeleccionada.setText(peliculaSeleccionada);

                salaSeleccionada = String.valueOf(funcionSeleccionada.getNro_Sala());
                nroSalaSeleccionada = funcionSeleccionada.getNro_Sala();
                lblSalaSeleccionada.setText("Sala: " + salaSeleccionada);

                jComboBoxIdioma.setSelectedItem(funcionSeleccionada.getIdioma());

                String subtituladaStr = funcionSeleccionada.isSubtitulada()
                        ? (funcionSeleccionada.getIdioma().equals("Español") ? "Inglés" : "Español") : "No";
                jComboBoxSubtitulada.setSelectedItem(subtituladaStr);

                if (funcionSeleccionada.isEs3D()) {
                    rbtn3D.setSelected(true);
                } else {
                    rbtn2D.setSelected(true);
                }

                String horaInicio = funcionSeleccionada.getHora_Inicio().toString().substring(0, 5);
                String horaFin = funcionSeleccionada.getHora_Fin().toString().substring(0, 5);
                txtFHoraInicio.setText(horaInicio);
                txtFHoraFin.setText(horaFin);
                txtFechaFuncion.setText(funcionSeleccionada.getFecha_Funcion().toString());
                txtFValorEntrada.setText(String.valueOf(funcionSeleccionada.getPrecio_Entrada()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar datos de la función: " + e.getMessage());
            }
        }
    }

    private void guardarFuncion() {
        try {
            if (idPeliculaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una película desde la ventana principal.");
                return;
            }

            if (nroSalaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una sala desde la ventana principal.");
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

            double valorEntrada;
            try {
                valorEntrada = Double.parseDouble(valorStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Formato inválido en valor de entrada.");
                return;
            }

            LocalDate fechaFuncion;
            try {
                fechaFuncion = LocalDate.parse(fecha);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use yyyy-MM-dd.");
                return;
            }

            Time horaInicioTime, horaFinTime;
            try {
                if (!horaInicio.contains(":")) {
                    throw new Exception("Formato de hora inválido");
                }
                if (!horaFin.contains(":")) {
                    throw new Exception("Formato de hora inválido");
                }
                horaInicioTime = Time.valueOf(horaInicio + ":00");
                horaFinTime = Time.valueOf(horaFin + ":00");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de hora inválido. Use HH:mm (ej: 14:30)");
                return;
            }

            boolean es3D = tipo.equals("3D");
            boolean subtitulada = !subtituladaStr.equals("No");

            if (modoEdicion) {
                funcionSeleccionada.setId_pelicula(idPeliculaSeleccionada);
                funcionSeleccionada.setNro_Sala(nroSalaSeleccionada);
                funcionSeleccionada.setIdioma(idioma);
                funcionSeleccionada.setEs3D(es3D);
                funcionSeleccionada.setHora_Inicio(horaInicioTime);
                funcionSeleccionada.setHora_Fin(horaFinTime);
                funcionSeleccionada.setPrecio_Entrada(valorEntrada);
                funcionSeleccionada.setFecha_Funcion(fechaFuncion);
                funcionSeleccionada.setSubtitulada(subtitulada);

                funcionDAO.actualizarFuncion(funcionSeleccionada.getId_Funcion(), funcionSeleccionada);
                JOptionPane.showMessageDialog(this, "Función modificada correctamente.");
            } else {
                Funcion funcion = new Funcion();
                funcion.setId_pelicula(idPeliculaSeleccionada);
                funcion.setNro_Sala(nroSalaSeleccionada);
                funcion.setIdioma(idioma);
                funcion.setEs3D(es3D);
                funcion.setHora_Inicio(horaInicioTime);
                funcion.setHora_Fin(horaFinTime);
                funcion.setPrecio_Entrada(valorEntrada);
                funcion.setFecha_Funcion(fechaFuncion);
                funcion.setSubtitulada(subtitulada);
                funcion.setEstado(true);

                funcionDAO.agregarFuncion(funcion);
                JOptionPane.showMessageDialog(this, "Función creada correctamente.");
            }

            if (parentFrame != null) {
                parentFrame.cargarFunciones();
            }

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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
        return "Desconocida";
    }

    public void setPeliculaSeleccionada(String nombrePelicula, int idPelicula) {
        this.peliculaSeleccionada = nombrePelicula;
        this.idPeliculaSeleccionada = idPelicula;
        lblPeliculaSeleccionada.setText(nombrePelicula);
    }

    public void setSalaSeleccionada(String sala, int nroSala) {
        this.salaSeleccionada = sala;
        this.nroSalaSeleccionada = nroSala;
        lblSalaSeleccionada.setText("Sala: " + sala);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGtipo3D = new javax.swing.ButtonGroup();
        pnlCrearFuncion = new javax.swing.JPanel();
        lblPelicula = new javax.swing.JLabel();
        lblPeliculaSeleccionada = new javax.swing.JLabel();
        lblIdioma = new javax.swing.JLabel();
        jComboBoxIdioma = new javax.swing.JComboBox<>();
        lblSubtitulada = new javax.swing.JLabel();
        jComboBoxSubtitulada = new javax.swing.JComboBox<>();
        lblFecha = new javax.swing.JLabel();
        txtFechaFuncion = new javax.swing.JTextField();
        lblValorEntrada = new javax.swing.JLabel();
        txtFValorEntrada = new javax.swing.JTextField();
        lblHoraInicio = new javax.swing.JLabel();
        txtFHoraInicio = new javax.swing.JTextField();
        lblHoraFin = new javax.swing.JLabel();
        txtFHoraFin = new javax.swing.JTextField();
        lblTipoPelicula = new javax.swing.JLabel();
        rbtn2D = new javax.swing.JRadioButton();
        rbtn3D = new javax.swing.JRadioButton();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblSala = new javax.swing.JLabel();
        lblSalaSeleccionada = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlCrearFuncion.setBackground(new java.awt.Color(102, 0, 0));

        lblPelicula.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        lblPelicula.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula.setText("Película:");

        lblPeliculaSeleccionada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblPeliculaSeleccionada.setForeground(new java.awt.Color(255, 255, 255));
        lblPeliculaSeleccionada.setText("pelicula a ver");

        lblIdioma.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblIdioma.setForeground(new java.awt.Color(255, 255, 255));
        lblIdioma.setText("Idioma:");

        jComboBoxIdioma.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jComboBoxIdioma.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxIdioma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Español", "Inglés", "Japones", "Coreano", "Ruso", "Frances", "Aleman" }));
        jComboBoxIdioma.setToolTipText("Elija idioma");

        lblSubtitulada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblSubtitulada.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtitulada.setText("Subtitulada:");

        jComboBoxSubtitulada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jComboBoxSubtitulada.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxSubtitulada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Español", "Inglés" }));
        jComboBoxSubtitulada.setToolTipText("Elija idioma");

        lblFecha.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha de Función:");

        txtFechaFuncion.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N

        lblValorEntrada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblValorEntrada.setForeground(new java.awt.Color(255, 255, 255));
        lblValorEntrada.setText("Valor Entrada:");

        txtFValorEntrada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N

        lblHoraInicio.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblHoraInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblHoraInicio.setText("Horario Inicio:");

        txtFHoraInicio.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        txtFHoraInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFHoraInicio.setToolTipText("hh:ss");

        lblHoraFin.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblHoraFin.setForeground(new java.awt.Color(255, 255, 255));
        lblHoraFin.setText("Horario Fin:");

        txtFHoraFin.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        txtFHoraFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFHoraFin.setToolTipText("hh:ss");

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

        btnConfirmar.setBackground(new java.awt.Color(70, 73, 75));
        btnConfirmar.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(70, 73, 75));
        btnCancelar.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblSala.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        lblSala.setForeground(new java.awt.Color(255, 255, 255));
        lblSala.setText("Sala:");

        lblSalaSeleccionada.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        lblSalaSeleccionada.setForeground(new java.awt.Color(255, 255, 255));
        lblSalaSeleccionada.setText("sala a ver");

        lblTitulo.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Titulo");

        javax.swing.GroupLayout pnlCrearFuncionLayout = new javax.swing.GroupLayout(pnlCrearFuncion);
        pnlCrearFuncion.setLayout(pnlCrearFuncionLayout);
        pnlCrearFuncionLayout.setHorizontalGroup(
            pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearFuncionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrearFuncionLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPeliculaSeleccionada)
                    .addComponent(lblTipoPelicula)
                    .addGroup(pnlCrearFuncionLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(rbtn2D)
                        .addGap(10, 10, 10)
                        .addComponent(rbtn3D))
                    .addComponent(lblPelicula)
                    .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtFHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlCrearFuncionLayout.createSequentialGroup()
                            .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSubtitulada)
                                .addComponent(jComboBoxSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblIdioma))
                            .addGap(27, 27, 27)
                            .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHoraFin)
                                .addComponent(txtFHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblHoraInicio)
                                .addComponent(lblSalaSeleccionada)
                                .addComponent(lblSala))))
                    .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlCrearFuncionLayout.createSequentialGroup()
                            .addComponent(btnConfirmar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCrearFuncionLayout.createSequentialGroup()
                            .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFecha)
                                .addComponent(txtFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblValorEntrada)
                                .addComponent(txtFValorEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(34, 34, 34))
        );
        pnlCrearFuncionLayout.setVerticalGroup(
            pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrearFuncionLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCrearFuncionLayout.createSequentialGroup()
                        .addComponent(lblSala)
                        .addGap(9, 9, 9)
                        .addComponent(lblSalaSeleccionada))
                    .addGroup(pnlCrearFuncionLayout.createSequentialGroup()
                        .addComponent(lblPelicula)
                        .addGap(9, 9, 9)
                        .addComponent(lblPeliculaSeleccionada)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrearFuncionLayout.createSequentialGroup()
                        .addComponent(lblTipoPelicula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtn2D)
                            .addComponent(rbtn3D))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdioma)
                        .addGap(7, 7, 7)
                        .addComponent(jComboBoxIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSubtitulada)
                        .addGap(7, 7, 7)
                        .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrearFuncionLayout.createSequentialGroup()
                        .addComponent(lblHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblHoraFin)
                        .addGap(29, 29, 29)))
                .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorEntrada)
                    .addGroup(pnlCrearFuncionLayout.createSequentialGroup()
                        .addComponent(lblFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFValorEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(pnlCrearFuncionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnConfirmar))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCrearFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCrearFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        guardarFuncion();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.ButtonGroup btnGtipo3D;
    private javax.swing.JComboBox<String> jComboBoxIdioma;
    private javax.swing.JComboBox<String> jComboBoxSubtitulada;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHoraFin;
    private javax.swing.JLabel lblHoraInicio;
    private javax.swing.JLabel lblIdioma;
    private javax.swing.JLabel lblPelicula;
    private javax.swing.JLabel lblPeliculaSeleccionada;
    private javax.swing.JLabel lblSala;
    private javax.swing.JLabel lblSalaSeleccionada;
    private javax.swing.JLabel lblSubtitulada;
    private javax.swing.JLabel lblTipoPelicula;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValorEntrada;
    private javax.swing.JPanel pnlCrearFuncion;
    private javax.swing.JRadioButton rbtn2D;
    private javax.swing.JRadioButton rbtn3D;
    private javax.swing.JTextField txtFHoraFin;
    private javax.swing.JTextField txtFHoraInicio;
    private javax.swing.JTextField txtFValorEntrada;
    private javax.swing.JTextField txtFechaFuncion;
    // End of variables declaration//GEN-END:variables
}
