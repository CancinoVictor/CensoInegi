
package Frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Cecyl 
 */

public class ConsultaActividadesEconomicasFrame extends JFrame {

    private JTable table;

    public ConsultaActividadesEconomicasFrame() {
        setTitle("Actividades Económicas por Vivienda");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID de Vivienda");
        model.addColumn("Actividades Económicas");

        // Crear tabla con el modelo
        table = new JTable(model);

        // Agregar tabla a un JScrollPane para hacerla desplazable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Obtener y mostrar los datos de la base de datos
        mostrarDatos();
    }

    private void mostrarDatos() {
        try {
            // Conexión a la base de datos
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_datos", "usuario", "contraseña");

            // Consulta SQL para obtener las actividades económicas por vivienda
            String consulta = "SELECT SUSTENTO.ID_VIVIENDA, GROUP_CONCAT(SUSTENTO.SUSTENTO SEPARATOR ', ') AS actividades "
                            + "FROM SUSTENTO "
                            + "GROUP BY SUSTENTO.ID_VIVIENDA";

            // Crear objeto Statement
            Statement stmt = con.createStatement();

            // Ejecutar consulta
            ResultSet rs = stmt.executeQuery(consulta);

            // Iterar sobre los resultados y agregarlos a la tabla
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("ID_VIVIENDA"),
                        rs.getString("actividades")
                };
                ((DefaultTableModel) table.getModel()).addRow(row);
            }

            // Cerrar conexión
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultaActividadesEconomicasFrame frame = new ConsultaActividadesEconomicasFrame();
            frame.setVisible(true);
        });
    }
}
