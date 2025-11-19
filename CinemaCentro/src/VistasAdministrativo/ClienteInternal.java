/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasAdministrativo;

import Controlador.ClienteDAO;
import Modelo.Cliente;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Naiara
 */
public class ClienteInternal extends javax.swing.JInternalFrame {

    /**
     * Creates new form ClientesInternal
     */
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    int selecId = 0;
    LocalDate fecha = null;
    private ClienteDAO maniCliente = new ClienteDAO();
    static String[] columnas = {"id_cliente", "DNI", "Nombre", "Apellido", "Fecha Nac.", "Contraseña", "Estado"};
    static DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
        @Override
        public boolean isCellEditable(int a, int b) {
            return false;
        }
    };

    public ClienteInternal() {
        initComponents();
        this.setResizable(false);
        this.setSize(1048, 600);
        armarCabecerayLlenar(tablaCliente);
        placeholderTxtFBuscarDNI();
        habilitarModificacion(cbHabilitarM);
        grupoBoton.add(rbTodo);
        grupoBoton.add(rbActivo);
        grupoBoton.add(rbInactivo);
        rbTodo.setSelected(true);
        txtDNI.setEditable(false);

    }

    //Método Clear
    private void clear() {
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtFechaNac.setText("");
        txtPassword.setText("");
    }

    //MétodoParsear
    private String parsearBoolean(boolean estado) {
        if (estado == true) {
            return "Activo";
        } else {
            return "Inactivo";
        }
    }

    //Método refresh
    private void refreshTabla() {

        try {
            List<Cliente> lista = maniCliente.listarClientes();
            modelo.setRowCount(0);
            List<Cliente> listaAct = new ArrayList<>();
            if (!lista.isEmpty()) {
                for (Cliente clien : lista) {
                    if (rbTodo.isSelected()) {
                        listaAct.add(clien);
                    } else if (rbActivo.isSelected() && clien.isEstado()) {
                        listaAct.add(clien);
                    } else if (rbInactivo.isSelected() && !clien.isEstado()) {
                        listaAct.add(clien);
                    }
                }
                for (Cliente clien : listaAct) {
                    modelo.addRow(new Object[]{
                        clien.getId_cliente(),
                        clien.getDni(),
                        clien.getNombre(),
                        clien.getApellido(),
                        clien.getFecha_nacimiento(),
                        clien.getPassword(),
                        parsearBoolean(clien.isEstado())
                    });
                }
            }
            cbHabilitarM.setSelected(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //armado de cabecera, relleno de tabla
    private void armarCabecerayLlenar(JTable tabla) {
        tabla.setModel(modelo);
        TableColumnModel modelColumn = tabla.getColumnModel();
        TableColumn columnID = modelColumn.getColumn(0);
        columnID.setMinWidth(0);
        columnID.setPreferredWidth(0);
        columnID.setMaxWidth(0);
        columnID.setResizable(false);

        //Relleno        
        refreshTabla();
    }

    //Placeholder 
    private void placeholderTxtFBuscarDNI() {
        txtFBuscarDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtFBuscarDNI.getText().equals("Ingrese DNI sin puntos")) {
                    txtFBuscarDNI.setText("");
                    txtFBuscarDNI.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtFBuscarDNI.getText().equals("")) {
                    txtFBuscarDNI.setText("Ingrese DNI sin puntos");
                    txtFBuscarDNI.setForeground(Color.GRAY);
                }
            }
        });
    }

    //HabilitarModificacion
    private void habilitarModificacion(JCheckBox jcb) {

        jcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                boolean selec = jcb.isSelected();
                if (selec != false) {
                    txtNombre.setEnabled(true);
                    txtApellido.setEnabled(true);
                    txtFechaNac.setEnabled(true);
                    txtPassword.setEnabled(true);
                    btnDarAlta.setEnabled(true);
                    btnDarBaja.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    btnModificar.setEnabled(true);
                    btnAgregar.setEnabled(false);
                } else {
                    txtNombre.setEnabled(false);
                    txtApellido.setEnabled(false);
                    txtPassword.setEnabled(false);
                    btnDarAlta.setEnabled(false);
                    btnDarBaja.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnModificar.setEnabled(false);
                    btnAgregar.setEnabled(true);
                    txtFechaNac.setEnabled(false);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBoton = new javax.swing.ButtonGroup();
        Panel = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtFBuscarDNI = new javax.swing.JTextField();
        lblBuscarDNI = new javax.swing.JLabel();
        PanelTabla = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        Salir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblDNI = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtFechaNac = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbHabilitarM = new javax.swing.JCheckBox();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDarAlta = new javax.swing.JButton();
        btnDarBaja = new javax.swing.JButton();
        rbActivo = new javax.swing.JRadioButton();
        rbInactivo = new javax.swing.JRadioButton();
        txtFiltrar = new javax.swing.JLabel();
        rbTodo = new javax.swing.JRadioButton();

        Panel.setBackground(new java.awt.Color(102, 0, 0));
        Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Panel.setForeground(new java.awt.Color(255, 255, 255));

        lblTitulo.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("GESTION DE CLIENTES");
        lblTitulo.setToolTipText("");
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtFBuscarDNI.setForeground(new java.awt.Color(161, 154, 147));
        txtFBuscarDNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFBuscarDNI.setText("Ingrese DNI sin puntos");
        txtFBuscarDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFBuscarDNIKeyReleased(evt);
            }
        });

        lblBuscarDNI.setFont(new java.awt.Font("URW Gothic", 1, 14)); // NOI18N
        lblBuscarDNI.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscarDNI.setText("Buscar por DNI:");

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        PanelTabla.setViewportView(tablaCliente);

        Salir.setBackground(new java.awt.Color(70, 73, 75));
        Salir.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        Salir.setForeground(new java.awt.Color(255, 255, 255));
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));

        lblNombre.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre:");

        lblApellido.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(255, 255, 255));
        lblApellido.setText("Apellido:");

        lblFechaNacimiento.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lblFechaNacimiento.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaNacimiento.setText("Fecha N. :");

        lblPassword.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password:");

        lblDNI.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lblDNI.setForeground(new java.awt.Color(255, 255, 255));
        lblDNI.setText("DNI:");

        txtDNI.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtDNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDNI.setEnabled(false);

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setEnabled(false);

        txtApellido.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtApellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellido.setEnabled(false);

        txtFechaNac.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtFechaNac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaNac.setEnabled(false);

        txtPassword.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPassword.setEnabled(false);

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(266, 208));

        cbHabilitarM.setFont(new java.awt.Font("Roboto", 2, 13)); // NOI18N
        cbHabilitarM.setForeground(new java.awt.Color(255, 255, 255));
        cbHabilitarM.setText("Habilitar modificación");
        cbHabilitarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHabilitarMActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(70, 73, 75));
        btnAgregar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar Cliente");
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(70, 73, 75));
        btnEliminar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar Cliente");
        btnEliminar.setEnabled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(70, 73, 75));
        btnModificar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar Cliente");
        btnModificar.setEnabled(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnDarAlta.setBackground(new java.awt.Color(70, 73, 75));
        btnDarAlta.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnDarAlta.setForeground(new java.awt.Color(255, 255, 255));
        btnDarAlta.setText("Dar de Alta");
        btnDarAlta.setEnabled(false);
        btnDarAlta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDarAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarAltaActionPerformed(evt);
            }
        });

        btnDarBaja.setBackground(new java.awt.Color(70, 73, 75));
        btnDarBaja.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnDarBaja.setForeground(new java.awt.Color(255, 255, 255));
        btnDarBaja.setText("Dar de Baja");
        btnDarBaja.setEnabled(false);
        btnDarBaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDarBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbHabilitarM)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDarBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDarAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbHabilitarM)
                .addGap(17, 17, 17)
                .addComponent(btnAgregar)
                .addGap(11, 11, 11)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addGap(11, 11, 11)
                .addComponent(btnDarAlta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDarBaja)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDNI)
                            .addComponent(lblNombre)
                            .addComponent(lblApellido)
                            .addComponent(lblFechaNacimiento)
                            .addComponent(lblPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDNI)
                            .addComponent(txtPassword)
                            .addComponent(txtFechaNac, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtApellido)
                            .addComponent(txtNombre))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNI)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGap(39, 39, 39)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        rbActivo.setBackground(new java.awt.Color(102, 0, 0));
        rbActivo.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        rbActivo.setForeground(new java.awt.Color(255, 255, 255));
        rbActivo.setText("Activo");
        rbActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbActivoActionPerformed(evt);
            }
        });

        rbInactivo.setBackground(new java.awt.Color(102, 0, 0));
        rbInactivo.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        rbInactivo.setForeground(new java.awt.Color(255, 255, 255));
        rbInactivo.setText("Inactivo");
        rbInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInactivoActionPerformed(evt);
            }
        });

        txtFiltrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        txtFiltrar.setText("Filtrar por:");

        rbTodo.setBackground(new java.awt.Color(102, 0, 0));
        rbTodo.setFont(new java.awt.Font("Roboto", 2, 12)); // NOI18N
        rbTodo.setForeground(new java.awt.Color(255, 255, 255));
        rbTodo.setSelected(true);
        rbTodo.setText("Todo");
        rbTodo.setToolTipText("rbTodo");
        rbTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addComponent(lblBuscarDNI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFBuscarDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFiltrar)
                        .addGap(18, 18, 18)
                        .addComponent(rbTodo)
                        .addGap(6, 6, 6)
                        .addComponent(rbActivo)
                        .addGap(18, 18, 18)
                        .addComponent(rbInactivo))
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Salir))))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscarDNI)
                            .addComponent(txtFBuscarDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbActivo)
                            .addComponent(rbInactivo)
                            .addComponent(txtFiltrar)
                            .addComponent(rbTodo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Salir))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFBuscarDNIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFBuscarDNIKeyReleased
        String caracter = txtFBuscarDNI.getText();
        modelo.setRowCount(0);
        try {
            List<Cliente> lista = maniCliente.listarClientes();
            for (Cliente a : lista) {
                if (Integer.toString(a.getDni()).startsWith(caracter)) {
                    modelo.addRow(new Object[]{
                        a.getId_cliente(),
                        a.getDni(),
                        a.getNombre(),
                        a.getApellido(),
                        a.getFecha_nacimiento(),
                        a.getPassword(),
                        parsearBoolean(a.isEstado())
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtFBuscarDNIKeyReleased

    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
        int filaSelec = tablaCliente.getSelectedRow();
        if (filaSelec != -1) {
            fecha = (LocalDate) tablaCliente.getValueAt(filaSelec, 4);

            selecId = (int) tablaCliente.getValueAt(filaSelec, 0);
            int dni = (int) tablaCliente.getValueAt(filaSelec, 1);
            String nombre = (String) tablaCliente.getValueAt(filaSelec, 2);
            String apellido = (String) tablaCliente.getValueAt(filaSelec, 3);
            String fechA = fecha.format(dtf);
            String pass = (String) tablaCliente.getValueAt(filaSelec, 5);

            txtDNI.setText(dni + "");
            txtNombre.setText(nombre);
            txtApellido.setText(apellido);
            txtFechaNac.setText(fechA);
            txtPassword.setText(pass);
        }
    }//GEN-LAST:event_tablaClienteMouseClicked

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        JFrame padre = (JFrame) SwingUtilities.getWindowAncestor(this);
        DialogAgregarCliente ventana = new DialogAgregarCliente(padre, true, false, null);
        ventana.setVisible(true);
        refreshTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        Object[] opciones = {"Si", "No"};
        int eleccion = JOptionPane.showOptionDialog(
                null,
                "¿Está seguro de eliminar? \n" + txtApellido.getText() + " " + txtNombre.getText(),
                "",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                null
        );

        if (eleccion == 0) {
            try {
                maniCliente.eliminarCliente(selecId);
                clear();
                refreshTabla();
                JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        try {
            
            if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()
                    || txtFechaNac.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.");

            } else {
                int dni =Integer.parseInt( txtDNI.getText());
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String fecha = txtFechaNac.getText();
                String pass = txtPassword.getText();

                LocalDate fechaNac = null;
                LocalDate fechaMin = LocalDate.of(1900, 1, 1);
                LocalDate fechaMax = LocalDate.now().minusYears(18);

                try {
                    fechaNac = LocalDate.parse(fecha, dtf);

                    if (fechaNac.isBefore(fechaMin) || fechaNac.isAfter(fechaMax)) {
                        JOptionPane.showMessageDialog(this, "La fecha debe ser entre '01-01-1900' y '" + fechaMax.format(dtf) + "'.");
                        txtFechaNac.setText("");
                        return;
                    }
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(this,
                            "La fecha debe estar en formato dd-MM-yyyy y ser válida.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    txtFechaNac.setText("");
                    return;
                }

                Cliente cliente = maniCliente.buscarClientePorDNI(dni);

                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setFecha_nacimiento(fechaNac);
                cliente.setPassword(pass);

                maniCliente.actualizarCliente(selecId, cliente);
                clear();
                refreshTabla();
                JOptionPane.showMessageDialog(this, "Cliente modificado con éxito.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnDarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarAltaActionPerformed
        try {
            Cliente cliente = maniCliente.buscarClientePorId(selecId);

            cliente.setEstado(true);
            maniCliente.actualizarCliente(selecId, cliente);
            refreshTabla();
            JOptionPane.showMessageDialog(this, "Se ha dado de alta al cliente: \n" + cliente.getApellido() + " " + cliente.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDarAltaActionPerformed

    private void btnDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarBajaActionPerformed
        try {
            Cliente cliente = maniCliente.buscarClientePorId(selecId);

            cliente.setEstado(false);
            maniCliente.actualizarCliente(selecId, cliente);
            refreshTabla();
            JOptionPane.showMessageDialog(this, "Se ha dado de alta al cliente: \n" + cliente.getApellido() + " " + cliente.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDarBajaActionPerformed

    private void rbActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbActivoActionPerformed
        // TODO add your handling code here:
        refreshTabla();
    }//GEN-LAST:event_rbActivoActionPerformed

    private void rbInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInactivoActionPerformed
        // TODO add your handling code here:
        refreshTabla();
    }//GEN-LAST:event_rbInactivoActionPerformed

    private void rbTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodoActionPerformed
        // TODO add your handling code here:
        refreshTabla();
    }//GEN-LAST:event_rbTodoActionPerformed

    private void cbHabilitarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHabilitarMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHabilitarMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JScrollPane PanelTabla;
    private javax.swing.JButton Salir;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnDarAlta;
    private javax.swing.JButton btnDarBaja;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JCheckBox cbHabilitarM;
    private javax.swing.ButtonGroup grupoBoton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblBuscarDNI;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton rbActivo;
    private javax.swing.JRadioButton rbInactivo;
    private javax.swing.JRadioButton rbTodo;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtFBuscarDNI;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JLabel txtFiltrar;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
