
package Backend;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionMySQL {

    private static final String USUARIO = "cancino"; // Usuario de la base de datos
    private static final String CONTRASENA = "root"; // Contraseña de la base de datos
    private static final String BASE_DE_DATOS = "registro_materias"; // Nombre de la base de datos
    private static final String IP = "127.0.0.1"; // Dirección IP del servidor MySQL
    private static final String PUERTO = "3306"; // Puerto de conexión MySQL
    private static final String CADENA_DE_CONEXION = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + BASE_DE_DATOS;

    public Connection establecerConexion() {
        Connection conexion = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            conexion = DriverManager.getConnection(CADENA_DE_CONEXION, USUARIO, CONTRASENA);
            // Mostrar mensaje de conexión exitosa (opcional)
            // JOptionPane.showMessageDialog(null, "Conexión establecida correctamente");
        } catch (Exception e) {
            // Mostrar mensaje de error en caso de fallo en la conexión
            JOptionPane.showMessageDialog(null, "Error al establecer la conexión: " + e.getMessage());
        }
        return conexion;
    }
}
