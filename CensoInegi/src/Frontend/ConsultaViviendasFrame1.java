package Frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import Backend.ConexionMySQL;

public class ConsultaViviendasFrame1 extends javax.swing.JFrame {
    private JTable table;

    public ConsultaViviendasFrame1() {
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

    private void mostrarDatos() {
        try {
            // Obtener la conexión desde la clase ConexionMySQL
            Connection con = ConexionMySQL.obtenerInstancia().obtenerConexion();

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

            // Cerrar conexión - Esto ya no es necesario porque la conexión se gestiona internamente en ConexionMySQL
            // con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            ConsultaViviendasFrame1 frame = new ConsultaViviendasFrame1();
            frame.setVisible(true);
        });
    }
}
