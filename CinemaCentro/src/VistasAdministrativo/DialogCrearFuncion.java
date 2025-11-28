package VistasAdministrativo;

import Controlador.FuncionDAO;
import Controlador.PeliculaDAO;
import Controlador.SalaDAO;
import Modelo.Funcion;
import Modelo.Pelicula;
import Modelo.Sala;
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
        validarCompatibilidad3D();
        cargarDatosFuncion();
        setLocationRelativeTo(parent);
    }

    private void validarCompatibilidad3D() {
        try {
            boolean salaEsApta3D = salaDAO.salaEsApta3D(nroSalaSeleccionada);

            if (!salaEsApta3D) {
                // si la sala no es apta para 3D, fuerza el 2D y deshabilita la opción 3D
                rbtn2D.setSelected(true);
                rbtn3D.setEnabled(false);

                // muestra mensaje de advertencia
                rbtn3D.setToolTipText("Esta sala no es apta para proyecciones 3D");
                if (modoEdicion && funcionSeleccionada != null && funcionSeleccionada.isEs3D()) {
                    JOptionPane.showMessageDialog(this,
                            "⚠️ ADVERTENCIA: Esta sala no es apta para 3D.\n"
                            + "La función será convertida a 2D automáticamente.",
                            "Sala no compatible con 3D",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                // si la sala si es apta para 3D, habilitar ambas opciones
                rbtn3D.setEnabled(true);
                rbtn3D.setToolTipText("Proyección en 3D disponible");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al verificar compatibilidad 3D: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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
                // usa getPelicula().getId_Pelicula() en lugar de getId_pelicula()
                peliculaSeleccionada = obtenerNombrePelicula(funcionSeleccionada.getPelicula().getId_Pelicula());
                idPeliculaSeleccionada = funcionSeleccionada.getPelicula().getId_Pelicula();
                lblPeliculaSeleccionada.setText(peliculaSeleccionada);

                // usa getSala().getNro_Sala() en lugar de getNro_Sala()
                salaSeleccionada = String.valueOf(funcionSeleccionada.getSala().getNro_Sala());
                nroSalaSeleccionada = funcionSeleccionada.getSala().getNro_Sala();
                lblSalaSeleccionada.setText("Sala: " + salaSeleccionada);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        }
    }

    private void cargarDatosFuncion() {
        if (funcionSeleccionada != null) {
            try {
                // usa getPelicula().getId_Pelicula()
                peliculaSeleccionada = obtenerNombrePelicula(funcionSeleccionada.getPelicula().getId_Pelicula());
                idPeliculaSeleccionada = funcionSeleccionada.getPelicula().getId_Pelicula();
                lblPeliculaSeleccionada.setText(peliculaSeleccionada);

                // usa getSala().getNro_Sala()
                salaSeleccionada = String.valueOf(funcionSeleccionada.getSala().getNro_Sala());
                nroSalaSeleccionada = funcionSeleccionada.getSala().getNro_Sala();
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

            // obtengo y valido los datos
            String idioma = (String) jComboBoxIdioma.getSelectedItem();
            String subtituladaStr = (String) jComboBoxSubtitulada.getSelectedItem();
            String tipo = rbtn2D.isSelected() ? "2D" : rbtn3D.isSelected() ? "3D" : null;
            String horaInicio = txtFHoraInicio.getText().trim();
            String horaFin = txtFHoraFin.getText().trim();
            String fecha = txtFechaFuncion.getText().trim();
            String valorStr = txtFValorEntrada.getText().trim();

            // valido campos vacíos
            if (idioma == null || subtituladaStr == null || tipo == null
                    || horaInicio.isEmpty() || horaFin.isEmpty() || fecha.isEmpty() || valorStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
                return;
            }

            // valido el formato de horas
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

            // valido que hora fin sea mayor que hora inicio
            if (!horaFinTime.after(horaInicioTime)) {
                JOptionPane.showMessageDialog(this, "La hora de fin debe ser posterior a la hora de inicio.");
                return;
            }

            // valido la fecha
            LocalDate fechaFuncion;
            try {
                fechaFuncion = LocalDate.parse(fecha);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use yyyy-MM-dd.");
                return;
            }

            // valido que la fecha no sea en el pasado
            if (fechaFuncion.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "No se pueden crear funciones en fechas pasadas.");
                return;
            }

            // valido el valor de entrada
            double valorEntrada;
            try {
                valorEntrada = Double.parseDouble(valorStr);
                if (valorEntrada <= 0) {
                    throw new NumberFormatException("Valor debe ser positivo");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El valor de entrada debe ser un número positivo.");
                return;
            }

            boolean es3D = tipo.equals("3D");
            boolean salaEsApta3D = salaDAO.salaEsApta3D(nroSalaSeleccionada);

            if (es3D && !salaEsApta3D) {
                JOptionPane.showMessageDialog(this,
                        "❌ NO SE PUEDE CREAR FUNCIÓN 3D\n\n"
                        + "La sala " + nroSalaSeleccionada + " no es apta para proyecciones 3D.\n"
                        + "Por favor, seleccione:\n"
                        + "• Otra sala que soporte 3D, o\n"
                        + "• Cambie el tipo a 2D",
                        "Sala no compatible con 3D",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean subtitulada = !subtituladaStr.equals("No");

            // ✅ validacion para el conflicto de los horarios
            Integer idFuncionExcluir = modoEdicion ? funcionSeleccionada.getId_Funcion() : null;

            if (funcionDAO.existeConflictoHorario(nroSalaSeleccionada, fechaFuncion, horaInicioTime, horaFinTime, idFuncionExcluir)) {
                List<Funcion> funcionesConflictivas = funcionDAO.obtenerFuncionesConflictivas(
                        nroSalaSeleccionada, fechaFuncion, horaInicioTime, horaFinTime, idFuncionExcluir
                );

                StringBuilder mensajeError = new StringBuilder();
                mensajeError.append("❌CONFLICTO DE HORARIO\n\n");
                mensajeError.append("La sala ").append(nroSalaSeleccionada).append(" ya tiene funciones programadas:\n\n");

                for (Funcion conflicto : funcionesConflictivas) {
                    String pelicula = obtenerNombrePelicula(conflicto.getPelicula().getId_Pelicula());
                    mensajeError.append("• ").append(pelicula)
                            .append(" - ").append(conflicto.getHora_Inicio().toString().substring(0, 5))
                            .append(" a ").append(conflicto.getHora_Fin().toString().substring(0, 5))
                            .append("\n");
                }

                mensajeError.append("\nPor favor, elija otro horario o sala.");
                JOptionPane.showMessageDialog(this, mensajeError.toString(), "Conflicto de Horario", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //esto es paara la modificación, actualizar los objetos Pelicula y Sala
            if (modoEdicion) {
                // crea nuevos objetos de Pelicula y Sala con los ids seleccionados
                Pelicula pelicula = new Pelicula();
                pelicula.setId_Pelicula(idPeliculaSeleccionada);

                Sala sala = new Sala();
                sala.setNro_Sala(nroSalaSeleccionada);

                funcionSeleccionada.setPelicula(pelicula);
                funcionSeleccionada.setSala(sala);
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
                // para nueva función
                Funcion funcion = new Funcion();

                // crear los objetos Pelicula y Sala
                Pelicula pelicula = new Pelicula();
                pelicula.setId_Pelicula(idPeliculaSeleccionada);

                Sala sala = new Sala();
                sala.setNro_Sala(nroSalaSeleccionada);

                funcion.setPelicula(pelicula);
                funcion.setSala(sala);
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
        
        validarCompatibilidad3D();
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
