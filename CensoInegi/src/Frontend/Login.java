package Frontend;

import Backend.ConexionMySQL;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    private JLabel labelUsername, labelPassword;
    private JTextField textUsername;
    private JPasswordField textPassword;
    private JButton buttonLogin;

    public Login() {
        setTitle("Censo de Poblacion Inegi");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        getContentPane().setBackground(new Color(240, 240, 240));

        labelUsername = new JLabel("Usuario:", SwingConstants.CENTER);
        //labelUsername.setIcon(new ImageIcon(getClass().getResource("/resources/user_icon.png")));
        labelUsername.setFont(new Font("Arial", Font.BOLD, 14));
        labelUsername.setForeground(new Color(50, 50, 50));

        textUsername = new JTextField(15);
        textUsername.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel panelUsername = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelUsername.setBackground(new Color(240, 240, 240));
        panelUsername.add(labelUsername);
        panelUsername.add(textUsername);

        labelPassword = new JLabel("Contraseña:", SwingConstants.CENTER);
        //labelPassword.setIcon(new ImageIcon(getClass().getResource("/resources/password_icon.png")));
        labelPassword.setFont(new Font("Arial", Font.BOLD, 14));
        labelPassword.setForeground(new Color(50, 50, 50));

        textPassword = new JPasswordField(15);
        textPassword.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel panelPassword = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelPassword.setBackground(new Color(240, 240, 240));
        panelPassword.add(labelPassword);
        panelPassword.add(textPassword);

        buttonLogin = new JButton("Iniciar sesión");
        buttonLogin.addActionListener(this);
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 14));
        buttonLogin.setBackground(new Color(50, 150, 250));
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setFocusPainted(false);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.setBackground(new Color(240, 240, 240));
        panelButton.add(buttonLogin);

        add(panelUsername);
        add(panelPassword);
        add(panelButton);
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
                // Obtener el rol del usuario desde el ResultSet
                String rol = resultado.getString("rol");

                // Redireccionar según el rol del usuario
                if ("admin".equals(rol)) {
                    // Abrir el formulario MenuAdmin
                    //MenuAdmin menuAdmin = new MenuAdmin();
                    //menuAdmin.setVisible(true);
                    this.dispose();
                } else if ("cliente".equals(rol)) {
                    // Abrir el formulario HabitantesForm
                    HabitantesForm habitantesForm = new HabitantesForm();
                    habitantesForm.setVisible(true);
                    this.dispose();
                } else {
                    // Rol desconocido
                    JOptionPane.showMessageDialog(this, "Rol de usuario desconocido");
                }
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
