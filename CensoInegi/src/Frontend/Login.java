package Frontend;

import Backend.ConexionMySQL;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    // Componentes del formulario
    private JLabel labelUsername, labelPassword;
    private JTextField textUsername;
    private JPasswordField textPassword;
    private JButton buttonLogin;

    public Login() {
        // Configuración del formulario
        setTitle("Censo de Poblacion Inegi");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        labelUsername = new JLabel("Username:");
        labelPassword = new JLabel("Password:");
        textUsername = new JTextField(20);
        textPassword = new JPasswordField(20);
        buttonLogin = new JButton("Login");

        // Layout
        setLayout(new GridLayout(3, 2));
        add(labelUsername);
        add(textUsername);
        add(labelPassword);
        add(textPassword);
        add(new JLabel()); // Espacio vacío
        add(buttonLogin);

        // Escuchar eventos del botón
        buttonLogin.addActionListener(this);
    }

    // Manejo del evento de clic en el botón
    public void actionPerformed(ActionEvent e) {
        String username = textUsername.getText();
        String password = new String(textPassword.getPassword());

        // Validación de credenciales consultando la base de datos
        ConexionMySQL conexionMySQL = ConexionMySQL.obtenerInstancia();
        Connection conexion = conexionMySQL.obtenerConexion();

        try {
            // Consulta SQL para verificar las credenciales del usuario
            String consulta = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                // Usuario autenticado
                JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!");
                // Aquí podrías redirigir a otra ventana o realizar alguna acción adicional
            } else {
                // Credenciales incorrectas
                JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos");
            }
        } catch (Exception ex) {
            // Error al ejecutar la consulta
            JOptionPane.showMessageDialog(this, "Error al verificar las credenciales: " + ex.getMessage());
        }
    }
}
