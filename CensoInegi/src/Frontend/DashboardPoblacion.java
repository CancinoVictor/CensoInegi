import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import Backend.ConexionMySQL;

public class DashboardPoblacion extends JFrame {

    private JTable table;

    public DashboardPoblacion() {
        setTitle("Dashboard de Población");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Entidad");
        model.addColumn("Municipio");
        model.addColumn("Localidad");
        model.addColumn("Población");

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
            // Obtener la conexión desde la clase ConexionMySQL
            Connection con = ConexionMySQL.obtenerInstancia().obtenerConexion();

            // Consulta SQL para obtener la población por entidad, municipio y localidad
            String consulta = "SELECT MUNICIPIO.ATTRIBUTE_MUNICIPIO14 AS Entidad, MUNICIPIO.ATTRIBUTE_MUNICIPIO14 AS Municipio, LOCALIDAD.LOCALIDAD, COUNT(HABITANTES.INE) AS Población " +
                  "FROM HABITANTES " +
                  "INNER JOIN VIVIENDA ON HABITANTES.ID_VIVIENDA = VIVIENDA.ID_VIVIENDA " +
                  "INNER JOIN LOCALIDAD ON VIVIENDA.ID_LOCALIDAD = LOCALIDAD.ID_LOCALIDAD " +
                  "INNER JOIN MUNICIPIO ON LOCALIDAD.ID_MUNICIPIO = MUNICIPIO.ID_MUNICIPIO " +
                  "GROUP BY MUNICIPIO.ATTRIBUTE_MUNICIPIO14, LOCALIDAD.LOCALIDAD";


            // Crear objeto Statement
            Statement stmt = con.createStatement();

            // Ejecutar consulta
            ResultSet rs = stmt.executeQuery(consulta);

            // Iterar sobre los resultados y agregarlos a la tabla
            while (rs.next()) {
                Object[] row = {
                        rs.getString("ENTIDAD"),
                        rs.getString("Municipio"),
                        rs.getString("LOCALIDAD"),
                        rs.getInt("Población")
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
            DashboardPoblacion dashboard = new DashboardPoblacion();
            dashboard.setVisible(true);
        });
    }
}
