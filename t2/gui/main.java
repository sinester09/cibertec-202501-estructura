import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplicacionVentas extends JFrame {
    private RegistroVentas registro;
    private JTextArea area;
    private JTextField txtAgregar;

    public AplicacionVentas() {
        registro = new RegistroVentas();

        setTitle("Registro de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 420);
        setLocationRelativeTo(null);

        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 5, 5));

        JButton btnAgregar = new JButton("Agregar venta");
        JButton btnReemplazar = new JButton("Reemplazar última venta < 1500");
        JButton btnIncrementar = new JButton("Incrementar ventas < 1800");
        JButton btnEliminarUltima = new JButton("Eliminar última venta < 1500");
        JButton btnListarRango = new JButton("Listar ventas entre 3000 y 4000");
        JButton btnListar = new JButton("Listar todas las ventas");
        JButton btnEliminarTodo = new JButton("Eliminar todas");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnReemplazar);
        panelBotones.add(btnIncrementar);
        panelBotones.add(btnEliminarUltima);
        panelBotones.add(btnListarRango);
        panelBotones.add(btnListar);
        panelBotones.add(btnEliminarTodo);
        panelBotones.add(btnSalir);

        JPanel panelAgregar = new JPanel(new BorderLayout());
        txtAgregar = new JTextField();
        panelAgregar.add(new JLabel("Nueva venta:"), BorderLayout.WEST);
        panelAgregar.add(txtAgregar, BorderLayout.CENTER);

        area = new JTextArea(10, 40);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        add(panelAgregar, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);

        // Acción: Agregar venta
        btnAgregar.addActionListener(e -> {
            try {
                double v = Double.parseDouble(txtAgregar.getText());
                registro.adicionar(v);
                area.setText("Venta agregada.\n" + registro.listar());
                txtAgregar.setText("");
            } catch (NumberFormatException ex) {
                area.setText("Por favor, ingresa un número válido.");
            }
        });

        // Acción: Reemplazar última venta < 1500
        btnReemplazar.addActionListener(e -> {
            if (registro.reemplazarUltimaVentaMenorQue1500()) {
                area.setText("Reemplazo realizado.\n" + registro.listar());
            } else {
                area.setText("No existe ninguna venta menor que 1500.");
            }
        });

        // Acción: Incrementar ventas < 1800
        btnIncrementar.addActionListener(e -> {
            int n = registro.incrementarVentasMenoresQue1800();
            area.setText(registro.listar() + "\nVentas incrementadas: " + n);
        });

        // Acción: Eliminar última venta < 1500
        btnEliminarUltima.addActionListener(e -> {
            if (registro.eliminarUltimaVentaMenorQue1500()) {
                area.setText("Eliminación realizada.\n" + registro.listar());
            } else {
                area.setText("No existe ninguna venta menor que 1500.");
            }
        });

        // Acción: Listar ventas entre 3000 y 4000
        btnListarRango.addActionListener(e -> {
            double[] ventas = registro.listarVentasEntre3000y4000();
            if (ventas.length == 0) {
                area.setText("No existe venta entre 3000 y 4000 soles.");
            } else {
                StringBuilder sb = new StringBuilder("Ventas entre 3000 y 4000 soles:\n");
                for (double v : ventas) sb.append(v).append(", ");
                area.setText(sb.toString());
            }
        });

        // Acción: Listar todas las ventas
        btnListar.addActionListener(e -> {
            area.setText("Ventas:\n" + registro.listar());
        });

        // Acción: Eliminar todas las ventas
        btnEliminarTodo.addActionListener(e -> {
            registro.eliminarTodo();
            area.setText("Todas las ventas eliminadas.");
        });

        // Acción: Salir
        btnSalir.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplicacionVentas().setVisible(true);
        });
    }
}
