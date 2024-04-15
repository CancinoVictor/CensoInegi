package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import Backend.ConexionMySQL; // Importar la clase de conexión

public class HabitantesForm extends JFrame {
    // Componentes del formulario
    private JTextField txtINE, txtNombre, txtApellido, txtTelefono;
    private JComboBox<String> cmbTipoVivienda; // ComboBox para los tipos de vivienda
    private JButton btnGuardar, btnEliminar;
    private JTextArea txtAreaHabitantes;

    // Objeto de conexión a la base de datos
    private Connection conn;
    
    public HabitantesForm() {
        // Configuración del formulario
        setTitle("Gestión de Habitantes");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes del formulario
        txtINE = new JTextField(10);
        txtNombre = new JTextField(10);
        txtApellido = new JTextField(10);
        txtTelefono = new JTextField(10);
        cmbTipoVivienda = new JComboBox<>(); // ComboBox para los tipos de vivienda
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        txtAreaHabitantes = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(txtAreaHabitantes);

        // Llenar ComboBox con los tipos de vivienda
        String[] tiposVivienda = {
            "Vivienda de concreto",
            "Vivienda de adobe(antiguo)",
            "Vivienda de ladrillo",
            "Vivienda de madera",
            "Vivienda de cartón",
            "Casa de piedra",
            "Vivienda prefabricada",
            "Material Ecológico",
            "Casa de paja, ramas o caña",
            "Material Adobe Moderno"
        };
        cmbTipoVivienda.setModel(new DefaultComboBoxModel<>(tiposVivienda));

        // Layout del formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        panelFormulario.add(new JLabel("INE:"));
        panelFormulario.add(txtINE);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellido);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Tipo de vivienda:")); // Texto para el ComboBox
        panelFormulario.add(cmbTipoVivienda); // ComboBox para los tipos de vivienda

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);

        // Crear instancia de la conexión a la base de datos
        conn = ConexionMySQL.obtenerInstancia().obtenerConexion();

        // Escuchar eventos de los botones
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarHabitante();
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarHabitante();
            }
        });

        // Cargar habitantes al iniciar la aplicación
        cargarHabitantes();
    }

    // Método para cargar los habitantes en el área de texto
    private void cargarHabitantes() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HABITANTES");
            StringBuilder habitantes = new StringBuilder();
            while (rs.next()) {
                habitantes.append("INE: ").append(rs.getString("INE")).append(", Nombre: ").append(rs.getString("NOMBRE"))
                        .append(", Apellido: ").append(rs.getString("APELLIDO")).append(", Teléfono: ").append(rs.getInt("TELEFONO"))
                        .append(", Tipo de vivienda: ").append(rs.getString("TIPO_VIVIENDA")).append("\n");
            }
            txtAreaHabitantes.setText(habitantes.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para guardar un habitante en la base de datos
    private void guardarHabitante() {
        try {
            String ine = txtINE.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int telefono = Integer.parseInt(txtTelefono.getText());
            String tipoVivienda = (String) cmbTipoVivienda.getSelectedItem(); // Obtener el tipo de vivienda seleccionado
            
            // Insertar el habitante en la base de datos
            PreparedStatement ps = conn.prepareStatement("INSERT INTO HABITANTES (INE, NOMBRE, APELLIDO, TELEFONO, TIPO_VIVIENDA) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, ine);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setInt(4, telefono);
            ps.setString(5, tipoVivienda);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Habitante guardado correctamente");
            cargarHabitantes(); // Actualizar la lista de habitantes
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el habitante");
        }
    }
    
    // Método para eliminar un habitante de la base de datos
    private void eliminarHabitante() {
        try {
            String ine = txtINE.getText();
            
            PreparedStatement ps = conn.prepareStatement("DELETE FROM HABITANTES WHERE INE = ?");
            ps.setString(1, ine);
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Habitante eliminado correctamente");
                cargarHabitantes(); // Actualizar la lista de habitantes
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún habitante con el INE especificado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el habitante");
        }
    }   
}