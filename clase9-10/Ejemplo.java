import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Ejemplo extends JFrame implements ActionListener {
    
    // Variable global - objeto de tipo ArregloAlumnos
    private ArregloAlumnos aa;
    
    // Componentes de la interfaz
    private JTextField txtCodigo, txtNombre, txtNota1, txtNota2, txtNota3;
    private JButton btnAdicionar, btnReportar, btnAprobadosDesaprobados, btnGuardarReporte;
    private JTextArea txtResultado;
    
    public Ejemplo() {
        // Crear objeto ArregloAlumnos
        aa = new ArregloAlumnos();
        
        // Configurar ventana
        setTitle("Clase 8-9 - Gestión de Alumnos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicializar componentes
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Panel de entrada de datos
        JPanel panelEntrada = new JPanel(new GridBagLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos del Alumno"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Código
        gbc.gridx = 0; 
        gbc.gridy = 0;
        panelEntrada.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(10);
        panelEntrada.add(txtCodigo, gbc);
        
        // Nombre
        gbc.gridx = 0; 
        gbc.gridy = 1;
        panelEntrada.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        panelEntrada.add(txtNombre, gbc);
        
        // Nota 1
        gbc.gridx = 0; 
        gbc.gridy = 2;
        panelEntrada.add(new JLabel("Nota 1:"), gbc);
        gbc.gridx = 1;
        txtNota1 = new JTextField(10);
        panelEntrada.add(txtNota1, gbc);
        
        // Nota 2
        gbc.gridx = 0; 
        gbc.gridy = 3;
        panelEntrada.add(new JLabel("Nota 2:"), gbc);
        gbc.gridx = 1;
        txtNota2 = new JTextField(10);
        panelEntrada.add(txtNota2, gbc);

        // Nota 3
        gbc.gridx = 0; 
        gbc.gridy = 4;
        panelEntrada.add(new JLabel("Nota 3:"), gbc);
        gbc.gridx = 1;
        txtNota3 = new JTextField(10);
        panelEntrada.add(txtNota3, gbc);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones"));
        
        btnAdicionar = new JButton("Adicionar");
        btnReportar = new JButton("Reportar");
        btnAprobadosDesaprobados = new JButton("Aprobados/Desaprobados");
        btnGuardarReporte = new JButton("Guardar Reporte");
        
        // Agregar ActionListeners
        btnAdicionar.addActionListener(this);
        btnReportar.addActionListener(this);
        btnAprobadosDesaprobados.addActionListener(this);
        btnGuardarReporte.addActionListener(this);
        
        // Agregar botones al panel
        panelBotones.add(btnAdicionar);
        panelBotones.add(btnReportar);
        panelBotones.add(btnAprobadosDesaprobados);
        panelBotones.add(btnGuardarReporte);
        
        // Área de resultados
        txtResultado = new JTextArea(12, 50);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBorder(BorderFactory.createTitledBorder("Resultados"));
        
        // Agregar componentes al panel principal
        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(scrollResultado, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionar) {
            adicionar();
        } else if (e.getSource() == btnReportar) {
            reportar();
        } else if (e.getSource() == btnAprobadosDesaprobados) {
            mostrarAprobadosDesaprobados();
        } else if (e.getSource() == btnGuardarReporte) {
            guardarReporte();
        }
    }
    
    // Método adicionar: envía al método adicionar un nuevo alumno creado
    private void adicionar() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = txtNombre.getText();
            int nota1 = Integer.parseInt(txtNota1.getText());
            int nota2 = Integer.parseInt(txtNota2.getText());
            int nota3 = Integer.parseInt(txtNota3.getText());
            
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del alumno");
                return;
            }
            
            // Crear nuevo alumno y adicionarlo
            Alumno nuevoAlumno = new Alumno(codigo, nombre, nota1, nota2, nota3);
            aa.adicionar(nuevoAlumno);
            
            // Limpiar campos
            limpiarCampos();
            
            JOptionPane.showMessageDialog(this, "Alumno adicionado correctamente");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Verifique que los datos numéricos sean correctos");
        }
    }
    
    // Método reportar: muestra la cantidad de alumnos, promedio general y mayor promedio
    private void reportar() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("REPORTE DE ALUMNOS\n");
        reporte.append("==================\n\n");
        
        // Cantidad de alumnos
        reporte.append("Cantidad de alumnos: ").append(aa.tamaño()).append("\n\n");
        
        // Promedio general de todos los alumnos
        reporte.append("Promedio general: ").append(String.format("%.2f", aa.promedioGeneral())).append("\n\n");
        
        // Mayor promedio
        reporte.append("Mayor promedio: ").append(String.format("%.2f", aa.promedioMayor())).append("\n\n");
        
        // Detalle de todos los alumnos
        reporte.append("DETALLE DE ALUMNOS:\n");
        reporte.append("-------------------\n");
        
        for (int i = 0; i < aa.tamaño(); i++) {
            Alumno alumno = aa.obtener(i);
            reporte.append("Código: ").append(alumno.getCodigo());
            reporte.append(" | Nombre: ").append(alumno.getNombre());
            reporte.append(" | Nota1: ").append(alumno.getNota1());
            reporte.append(" | Nota2: ").append(alumno.getNota2());
            reporte.append(" | Nota3: ").append(alumno.getNota3());
            reporte.append(" | Promedio: ").append(String.format("%.2f", alumno.promedio()));
            reporte.append("\n");
        }
        
        txtResultado.setText(reporte.toString());
    }
    
    // Método para mostrar alumnos aprobados y desaprobados
    private void mostrarAprobadosDesaprobados() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("REPORTE DE APROBADOS Y DESAPROBADOS\n");
        reporte.append("===================================\n");
        reporte.append("Criterio de aprobación: Promedio >= 13\n\n");
        
        // Estadísticas generales
        int totalAprobados = aa.cantidadAprobados();
        int totalDesaprobados = aa.cantidadDesaprobados();
        int total = aa.tamaño();
        
        reporte.append("RESUMEN:\n");
        reporte.append("--------\n");
        reporte.append("Total de alumnos: ").append(total).append("\n");
        reporte.append("Aprobados: ").append(totalAprobados).append(" (")
               .append(String.format("%.1f", (totalAprobados * 100.0 / total))).append("%)\n");
        reporte.append("Desaprobados: ").append(totalDesaprobados).append(" (")
               .append(String.format("%.1f", (totalDesaprobados * 100.0 / total))).append("%)\n\n");
        
        // Lista de aprobados
        ArrayList<Alumno> aprobados = aa.obtenerAprobados();
        reporte.append("ALUMNOS APROBADOS (").append(aprobados.size()).append("):\n");
        reporte.append("------------------------\n");
        
        if (aprobados.isEmpty()) {
            reporte.append("No hay alumnos aprobados.\n");
        } else {
            for (Alumno alumno : aprobados) {
                reporte.append("✓ Código: ").append(alumno.getCodigo());
                reporte.append(" | ").append(alumno.getNombre());
                reporte.append(" | Notas: ").append(alumno.getNota1()).append("-")
                       .append(alumno.getNota2()).append("-").append(alumno.getNota3());
                reporte.append(" | Promedio: ").append(String.format("%.2f", alumno.promedio()));
                reporte.append("\n");
            }
        }
        
        reporte.append("\n");
        
        // Lista de desaprobados
        ArrayList<Alumno> desaprobados = aa.obtenerDesaprobados();
        reporte.append("ALUMNOS DESAPROBADOS (").append(desaprobados.size()).append("):\n");
        reporte.append("----------------------------\n");
        
        if (desaprobados.isEmpty()) {
            reporte.append("No hay alumnos desaprobados.\n");
        } else {
            for (Alumno alumno : desaprobados) {
                reporte.append("✗ Código: ").append(alumno.getCodigo());
                reporte.append(" | ").append(alumno.getNombre());
                reporte.append(" | Notas: ").append(alumno.getNota1()).append("-")
                       .append(alumno.getNota2()).append("-").append(alumno.getNota3());
                reporte.append(" | Promedio: ").append(String.format("%.2f", alumno.promedio()));
                reporte.append("\n");
            }
        }
        
        txtResultado.setText(reporte.toString());
    }

    private void guardarReporte() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("reporte.txt"));
            
            // Obtener datos estadísticos
            int totalAprobados = aa.cantidadAprobados();
            int totalDesaprobados = aa.cantidadDesaprobados();
            int total = aa.tamaño();
            
            ArrayList<Alumno> aprobados = aa.obtenerAprobados();
            ArrayList<Alumno> desaprobados = aa.obtenerDesaprobados();
            
            // Escribir encabezado del reporte
            pw.println("REPORTE DE ALUMNOS - " + new java.util.Date());
            pw.println("==========================================");
            pw.println("Total de alumnos: " + total);
            pw.println("Aprobados: " + totalAprobados + " de " + total);
            pw.println("Desaprobados: " + totalDesaprobados + " de " + total);
            pw.println();
            
            // Sección de aprobados
            pw.println("ALUMNOS APROBADOS:");
            pw.println("------------------");
            
            if (aprobados.isEmpty()) {
                pw.println("No hay alumnos aprobados.");
            } else {
                pw.println("Código;Nombre;Nota1;Nota2;Nota3;Promedio");
                for (Alumno alumno : aprobados) {
                    String linea = alumno.getCodigo() + ";" +
                                  alumno.getNombre() + ";" +
                                  alumno.getNota1() + ";" +
                                  alumno.getNota2() + ";" +
                                  alumno.getNota3() + ";" +
                                  String.format("%.2f", alumno.promedio());
                    pw.println(linea);
                }
            }
            
            pw.println();
            
            // Sección de desaprobados
            pw.println("ALUMNOS DESAPROBADOS:");
            pw.println("---------------------");
            
            if (desaprobados.isEmpty()) {
                pw.println("No hay alumnos desaprobados.");
            } else {
                pw.println("Código;Nombre;Nota1;Nota2;Nota3;Promedio");
                for (Alumno alumno : desaprobados) {
                    String linea = alumno.getCodigo() + ";" +
                                  alumno.getNombre() + ";" +
                                  alumno.getNota1() + ";" +
                                  alumno.getNota2() + ";" +
                                  alumno.getNota3() + ";" +
                                  String.format("%.2f", alumno.promedio());
                    pw.println(linea);
                }
            }
            
            // Mostrar mensaje de éxito al usuario
            JOptionPane.showMessageDialog(this, 
                "Reporte guardado exitosamente en 'reporte.txt'", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar el archivo: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
        txtCodigo.requestFocus();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ejemplo().setVisible(true);
        });
    }
}