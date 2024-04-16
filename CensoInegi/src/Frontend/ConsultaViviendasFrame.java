
package Frontend;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
/**
 *
 * @author Cecyl 
 */
public class ConsultaViviendasFrame extends javax.swing.JFrame {
    private JTable table;

    public ConsultaViviendasFrame() {
        setTitle("Consulta de Habitantes por Vivienda");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID de Vivienda");
        model.addColumn("Cantidad de Habitantes");
        model.addColumn("Habitantes");

        // Crear tabla con el modelo
        table = new JTable(model);

        // Agregar tabla a un JScrollPane para hacerla desplazable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Obtener y mostrar los datos de la base de datos
        mostrarDatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarDatos() {
        try {
            // Conexión a la base de datos
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_datos", "usuario", "contraseña");

            // Consulta SQL
            String consulta = "SELECT V.ID_VIVIENDA, COUNT(H.ID_VIVIENDA) AS cantidad_habitantes, GROUP_CONCAT(CONCAT(H.NOMBRE, ' ', H.APELIIDO) SEPARATOR ', ') AS habitantes "
                            + "FROM VIVIENDA V "
                            + "LEFT JOIN HABITANTES H ON V.ID_VIVIENDA = H.ID_VIVIENDA "
                            + "GROUP BY V.ID_VIVIENDA";

            // Crear objeto Statement
            Statement stmt = con.createStatement();

            // Ejecutar consulta
            ResultSet rs = stmt.executeQuery(consulta);

            // Iterar sobre los resultados y agregarlos a la tabla
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("ID_VIVIENDA"),
                        rs.getInt("cantidad_habitantes"),
                        rs.getString("habitantes")
                };
                ((DefaultTableModel) table.getModel()).addRow(row);
            }

            // Cerrar conexión
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            ConsultaViviendasFrame frame = new ConsultaViviendasFrame();
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
