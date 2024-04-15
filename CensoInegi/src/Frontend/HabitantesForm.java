/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

/**
 *
 * @author Cancino
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HabitantesForm extends JFrame {
    // Componentes del formulario
    private JTextField txtINE, txtNombre, txtApellido, txtTelefono;
    private JComboBox<String> cmbVivienda;
    private JButton btnGuardar, btnEliminar;
    private JTextArea txtAreaHabitantes;

    // Variables de conexión a la base de datos
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public HabitantesForm() {
        // Configuración del formulario
        setTitle("Gestión de Habitantes");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        txtINE = new JTextField(10);
        txtNombre = new JTextField(10);
        txtApellido = new JTextField(10);
        txtTelefono = new JTextField(10);
        cmbVivienda = new JComboBox<>();
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        txtAreaHabitantes = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(txtAreaHabitantes);

        // Layout
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        panelFormulario.add(new JLabel("INE:"));
        panelFormulario.add(txtINE);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellido);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Vivienda:"));
        panelFormulario.add(cmbVivienda);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);

        // Conexión a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_datos", "usuario", "contraseña");
            cargarViviendas();
            cargarHabitantes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
    }

    // Método para cargar las viviendas en el ComboBox
    private void cargarViviendas() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT ID_VIVIENDA FROM VIVIENDA");
            while (rs.next()) {
                cmbVivienda.addItem(rs.getString("ID_VIVIENDA"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar los habitantes en el área de texto
    private void cargarHabitantes() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM HABITANTES");
            StringBuilder habitantes = new StringBuilder();
            while (rs.next()) {
                habitantes.append("INE: ").append(rs.getString("INE")).append(", Nombre: ").append(rs.getString("NOMBRE"))
                        .append(", Apellido: ").append(rs.getString("APELLIDO")).append(", Teléfono: ").append(rs.getInt("TELEFONO"))
                        .append(", Vivienda: ").append(rs.getInt("ID_VIVIENDA")).append("\n");
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
            int idVivienda = Integer.parseInt((String) cmbVivienda.getSelectedItem());
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO HABITANTES (INE, ID_VIVIENDA, NOMBRE, APELLIDO, TELEFONO) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, ine);
            ps.setInt(2, idVivienda);
            ps.setString(3, nombre);
            ps.setString(4, apellido);
            ps.setInt(5, telefono);
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

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HabitantesForm form = new HabitantesForm();
            form.setVisible(true);
        });
    }
}
