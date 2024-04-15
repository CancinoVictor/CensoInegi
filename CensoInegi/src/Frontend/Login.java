package Frontend;

/**
 *
 * @author Cancino
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        // Verificación de credenciales (aquí deberías tener tu lógica de autenticación)
        if (username.equals("usuario") && password.equals("contraseña")) {
            JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!");
        } else {
            JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos");
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        Login form = new Login();
        form.setVisible(true);
    }
}
