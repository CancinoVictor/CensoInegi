
package Frontend;

/**
 *
 * @author Cecyl 
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConsultaCantidadHabitantesPorMaterialFrame extends JFrame {

    private JTable table;

    public ConsultaCantidadHabitantesPorMaterialFrame() {
        setTitle("Cantidad de Habitantes por Tipo de Vivienda");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tipo de Vivienda");
        model.addColumn("Cantidad de Habitantes");

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

            // Consulta SQL para obtener la cantidad de habitantes por tipo de vivienda
            String consulta = "SELECT MATERIAL.MATERIAL, COUNT(HABITANTES.ID_VIVIENDA) AS cantidad_habitantes "
                            + "FROM VIVIENDA "
                            + "INNER JOIN MATERIAL ON VIVIENDA.ID_MATERIAL = MATERIAL.ID_MATERIAL "
                            + "LEFT JOIN HABITANTES ON VIVIENDA.ID_VIVIENDA = HABITANTES.ID_VIVIENDA "
                            + "GROUP BY MATERIAL.MATERIAL";

            // Crear objeto Statement
            Statement stmt = con.createStatement();

            // Ejecutar consulta
            ResultSet rs = stmt.executeQuery(consulta);

            // Iterar sobre los resultados y agregarlos a la tabla
            while (rs.next()) {
                Object[] row = {
                        rs.getString("MATERIAL"),
                        rs.getInt("cantidad_habitantes")
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
            ConsultaCantidadHabitantesPorMaterialFrame frame = new ConsultaCantidadHabitantesPorMaterialFrame();
            frame.setVisible(true);
        });
    }
}
