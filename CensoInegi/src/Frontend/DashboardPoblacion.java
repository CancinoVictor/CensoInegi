
package Frontend;

import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Cecyl 
 */

public class DashboardPoblacion extends JFrame {

    public DashboardPoblacion() {
        setTitle("Dashboard de Población");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Conectar a la base de datos y obtener datos de población
        String[][] datosPoblacion = obtenerDatosPoblacion();

        // Crear tabla para mostrar los datos
        String[] columnas = {"Entidad", "Municipio", "Localidad", "Población"};
        JTable tablaPoblacion = new JTable(datosPoblacion, columnas);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaPoblacion);
        add(scrollPane);
    }

    private String[][] obtenerDatosPoblacion() {
        // Declarar matriz para almacenar los datos
        String[][] datosPoblacion = null;

        try {
            // Conexión a la base de datos
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_datos", "usuario", "contraseña");

            // Consulta SQL para obtener los datos de población
            String consulta = "SELECT ENTIDAD, MUNICIPIO, LOCALIDAD, COUNT(*) AS Población "
                            + "FROM HABITANTES "
                            + "GROUP BY ENTIDAD, MUNICIPIO, LOCALIDAD";

            // Crear objeto Statement
            Statement stmt = con.createStatement();

            // Ejecutar consulta
            ResultSet rs = stmt.executeQuery(consulta);

            // Contar filas en el resultado
            rs.last();
            int filas = rs.getRow();
            rs.beforeFirst();

            // Inicializar matriz con el tamaño correcto
            datosPoblacion = new String[filas][4];

            // Iterar sobre los resultados y agregarlos a la matriz
            int i = 0;
            while (rs.next()) {
                datosPoblacion[i][0] = rs.getString("ENTIDAD");
                datosPoblacion[i][1] = rs.getString("MUNICIPIO");
                datosPoblacion[i][2] = rs.getString("LOCALIDAD");
                datosPoblacion[i][3] = rs.getString("Población");
                i++;
            }

            // Cerrar conexión
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datosPoblacion;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardPoblacion dashboard = new DashboardPoblacion();
            dashboard.setVisible(true);
        });
    }
}
