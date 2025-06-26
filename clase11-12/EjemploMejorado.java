import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class EjemploMejorado extends JFrame implements ActionListener {
    
    // Variable global - objeto de tipo ArregloAlumnos
    private ArregloAlumnos aa;
    
    // Componentes de la interfaz
    private JTextField txtCodigo, txtNombre, txtNota1, txtNota2, txtNota3;
    private JButton btnAdicionar, btnReportar, btnAprobadosDesaprobados, btnGuardarReporte;
    private JButton btnEditar, btnCargarCSV, btnActualizar, btnCancelar, btnEliminar;
    private JTextArea txtResultado;
    private JTable tablaAlumnos;
    private DefaultTableModel modeloTabla;
    private boolean modoEdicion = false;
    private int alumnoEditando = -1;
    
    public EjemploMejorado() {
        // Crear objeto ArregloAlumnos
        aa = new ArregloAlumnos();
        
        // Configurar ventana
        setTitle("Sistema de Gestión de Alumnos - Versión Mejorada");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicializar componentes
        inicializarComponentes();
        actualizarTabla();
    }
    
    private void inicializarComponentes() {
        // Panel principal con pestañas
        JTabbedPane pestanas = new JTabbedPane();
        
        // PESTAÑA 1: GESTIÓN DE DATOS
        JPanel panelGestion = crearPanelGestion();
        pestanas.addTab("Gestión de Datos", panelGestion);
        
        // PESTAÑA 2: REPORTES
        JPanel panelReportes = crearPanelReportes();
        pestanas.addTab("Reportes", panelReportes);
        
        add(pestanas);
    }
    
    private JPanel crearPanelGestion() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel superior: Formulario de entrada
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Alumno"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Código
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(10);
        panelFormulario.add(txtCodigo, gbc);
        
        // Nombre
        gbc.gridx = 2; 
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 3;
        txtNombre = new JTextField(20);
        panelFormulario.add(txtNombre, gbc);
        
        // Nota 1
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nota 1:"), gbc);
        gbc.gridx = 1;
        txtNota1 = new JTextField(10);
        panelFormulario.add(txtNota1, gbc);
        
        // Nota 2
        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Nota 2:"), gbc);
        gbc.gridx = 3;
        txtNota2 = new JTextField(10);
        panelFormulario.add(txtNota2, gbc);
        
        // Nota 3
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Nota 3:"), gbc);
        gbc.gridx = 1;
        txtNota3 = new JTextField(10);
        panelFormulario.add(txtNota3, gbc);
        
        // Panel de botones del formulario
        JPanel panelBotonesForm = new JPanel(new FlowLayout());
        btnAdicionar = new JButton("Adicionar");
        btnActualizar = new JButton("Actualizar");
        btnCancelar = new JButton("Cancelar");
        btnCargarCSV = new JButton("📁 Cargar CSV");
        
        btnAdicionar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnCargarCSV.addActionListener(this);
        
        // Inicialmente solo mostrar botón adicionar
        btnActualizar.setVisible(false);
        btnCancelar.setVisible(false);
        
        panelBotonesForm.add(btnAdicionar);
        panelBotonesForm.add(btnActualizar);
        panelBotonesForm.add(btnCancelar);
        panelBotonesForm.add(btnCargarCSV);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 4;
        panelFormulario.add(panelBotonesForm, gbc);
        
        // Panel central: Tabla de alumnos
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Alumnos"));
        
        // Crear modelo de tabla
        String[] columnas = {"Código", "Nombre", "Nota 1", "Nota 2", "Nota 3", "Promedio"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable directamente en la tabla
            }
        };
        
        tablaAlumnos = new JTable(modeloTabla);
        tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollTabla = new JScrollPane(tablaAlumnos);
        scrollTabla.setPreferredSize(new Dimension(0, 250));
        
        // Botones para la tabla
        JPanel panelBotonesTabla = new JPanel(new FlowLayout());
        btnEditar = new JButton("✏️ Editar Seleccionado");
        btnEliminar = new JButton("🗑️ Eliminar Seleccionado");
        
        btnEditar.addActionListener(this);
        btnEliminar.addActionListener(this);
        
        panelBotonesTabla.add(btnEditar);
        panelBotonesTabla.add(btnEliminar);
        
        panelTabla.add(scrollTabla, BorderLayout.CENTER);
        panelTabla.add(panelBotonesTabla, BorderLayout.SOUTH);
        
        // Agregar todo al panel principal
        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(panelTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de botones de reportes
        JPanel panelBotonesReporte = new JPanel(new FlowLayout());
        panelBotonesReporte.setBorder(BorderFactory.createTitledBorder("Generar Reportes"));
        
        btnReportar = new JButton("📊 Reporte General");
        btnAprobadosDesaprobados = new JButton("📈 Aprobados/Desaprobados");
        btnGuardarReporte = new JButton("💾 Guardar Reporte");
        
        btnReportar.addActionListener(this);
        btnAprobadosDesaprobados.addActionListener(this);
        btnGuardarReporte.addActionListener(this);
        
        panelBotonesReporte.add(btnReportar);
        panelBotonesReporte.add(btnAprobadosDesaprobados);
        panelBotonesReporte.add(btnGuardarReporte);
        
        // Área de resultados
        txtResultado = new JTextArea(25, 60);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBorder(BorderFactory.createTitledBorder("Resultados"));
        
        panel.add(panelBotonesReporte, BorderLayout.NORTH);
        panel.add(scrollResultado, BorderLayout.CENTER);
        
        return panel;
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
        } else if (e.getSource() == btnEditar) {
            editarSeleccionado();
        } else if (e.getSource() == btnCargarCSV) {
            cargarCSV();
        } else if (e.getSource() == btnActualizar) {
            actualizarAlumno();
        } else if (e.getSource() == btnCancelar) {
            cancelarEdicion();
        } else if (e.getSource() == btnEliminar) {
            eliminarSeleccionado();
        }
    }
    
    // NUEVOS MÉTODOS PARA LA FUNCIONALIDAD MEJORADA
    
    private void editarSeleccionado() {
        int filaSeleccionada = tablaAlumnos.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un alumno de la tabla para editar.",
                "Seleccionar Alumno", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener el alumno seleccionado
        Alumno alumno = aa.obtener(filaSeleccionada);
        
        // Llenar los campos con los datos del alumno
        txtCodigo.setText(String.valueOf(alumno.getCodigo()));
        txtNombre.setText(alumno.getNombre());
        txtNota1.setText(String.valueOf(alumno.getNota1()));
        txtNota2.setText(String.valueOf(alumno.getNota2()));
        txtNota3.setText(String.valueOf(alumno.getNota3()));
        
        // Cambiar a modo edición
        modoEdicion = true;
        alumnoEditando = filaSeleccionada;
        
        // Cambiar visibilidad de botones
        btnAdicionar.setVisible(false);
        btnActualizar.setVisible(true);
        btnCancelar.setVisible(true);
        
        // Deshabilitar botones de tabla
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        JOptionPane.showMessageDialog(this, 
            "Modo edición activado. Modifique los datos y presione 'Actualizar'.",
            "Modo Edición", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void actualizarAlumno() {
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
            
            // Actualizar el alumno
            Alumno alumnoActualizado = new Alumno(codigo, nombre, nota1, nota2, nota3);
            aa.actualizar(alumnoEditando, alumnoActualizado);
            
            // Actualizar tabla
            actualizarTabla();
            
            // Salir del modo edición
            cancelarEdicion();
            
            JOptionPane.showMessageDialog(this, "Alumno actualizado correctamente");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Verifique que los datos numéricos sean correctos");
        }
    }
    
    private void cancelarEdicion() {
        // Salir del modo edición
        modoEdicion = false;
        alumnoEditando = -1;
        
        // Limpiar campos
        limpiarCampos();
        
        // Restaurar visibilidad de botones
        btnAdicionar.setVisible(true);
        btnActualizar.setVisible(false);
        btnCancelar.setVisible(false);
        
        // Habilitar botones de tabla
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    
    private void eliminarSeleccionado() {
        int filaSeleccionada = tablaAlumnos.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un alumno de la tabla para eliminar.",
                "Seleccionar Alumno", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Confirmar eliminación
        Alumno alumno = aa.obtener(filaSeleccionada);
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar al alumno: " + alumno.getNombre() + "?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            aa.eliminar(filaSeleccionada);
            actualizarTabla();
            JOptionPane.showMessageDialog(this, "Alumno eliminado correctamente");
        }
    }
    
    private void cargarCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos CSV", "csv"));
        fileChooser.setDialogTitle("Seleccionar archivo CSV");
        
        int resultado = fileChooser.showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            cargarDatosCSV(archivo);
        }
    }
    
    private void cargarDatosCSV(File archivo) {
        BufferedReader br = null;
        int alumnosAgregados = 0;
        int errores = 0;
        StringBuilder erroresDetalle = new StringBuilder();
        
        try {
            br = new BufferedReader(new FileReader(archivo));
            String linea;
            int numeroLinea = 0;
            
            // Saltar la primera línea si contiene encabezados
            String primeraLinea = br.readLine();
            numeroLinea++;
            
            // Verificar si la primera línea son encabezados
            boolean tieneEncabezados = primeraLinea.toLowerCase().contains("codigo") || 
                                     primeraLinea.toLowerCase().contains("nombre");
            
            if (!tieneEncabezados) {
                // Procesar la primera línea como datos
                if (procesarLineaCSV(primeraLinea, numeroLinea, erroresDetalle)) {
                    alumnosAgregados++;
                } else {
                    errores++;
                }
            }
            
            // Procesar el resto del archivo
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                if (procesarLineaCSV(linea, numeroLinea, erroresDetalle)) {
                    alumnosAgregados++;
                } else {
                    errores++;
                }
            }
            
            // Actualizar la tabla
            actualizarTabla();
            
            // Mostrar resultado
            String mensaje = "Carga completada:\n" +
                           "- Alumnos agregados: " + alumnosAgregados + "\n" +
                           "- Errores: " + errores;
            
            if (errores > 0) {
                mensaje += "\n\nDetalles de errores:\n" + erroresDetalle.toString();
            }
            
            JOptionPane.showMessageDialog(this, mensaje, "Resultado de Carga", 
                errores > 0 ? JOptionPane.WARNING_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al leer el archivo: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // Ignorar error al cerrar
                }
            }
        }
    }
    
    private boolean procesarLineaCSV(String linea, int numeroLinea, StringBuilder erroresDetalle) {
        try {
            // Dividir por comas o punto y coma
            String[] datos = linea.split("[,;]");
            
            if (datos.length < 5) {
                erroresDetalle.append("Línea ").append(numeroLinea)
                             .append(": Faltan datos (se requieren 5 campos)\n");
                return false;
            }
            
            // Limpiar espacios en blanco
            for (int i = 0; i < datos.length; i++) {
                datos[i] = datos[i].trim();
            }
            
            int codigo = Integer.parseInt(datos[0]);
            String nombre = datos[1];
            int nota1 = Integer.parseInt(datos[2]);
            int nota2 = Integer.parseInt(datos[3]);
            int nota3 = Integer.parseInt(datos[4]);
            
            // Validaciones básicas
            if (nombre.isEmpty()) {
                erroresDetalle.append("Línea ").append(numeroLinea)
                             .append(": Nombre vacío\n");
                return false;
            }
            
            if (nota1 < 0 || nota1 > 20 || nota2 < 0 || nota2 > 20 || nota3 < 0 || nota3 > 20) {
                erroresDetalle.append("Línea ").append(numeroLinea)
                             .append(": Notas fuera del rango 0-20\n");
                return false;
            }
            
            // Crear y agregar alumno
            Alumno nuevoAlumno = new Alumno(codigo, nombre, nota1, nota2, nota3);
            aa.adicionar(nuevoAlumno);
            
            return true;
            
        } catch (NumberFormatException e) {
            erroresDetalle.append("Línea ").append(numeroLinea)
                         .append(": Error en formato numérico\n");
            return false;
        } catch (Exception e) {
            erroresDetalle.append("Línea ").append(numeroLinea)
                         .append(": Error inesperado - ").append(e.getMessage()).append("\n");
            return false;
        }
    }
    
    private void actualizarTabla() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        // Agregar filas
        for (int i = 0; i < aa.tamaño(); i++) {
            Alumno alumno = aa.obtener(i);
            Object[] fila = {
                alumno.getCodigo(),
                alumno.getNombre(),
                alumno.getNota1(),
                alumno.getNota2(),
                alumno.getNota3(),
                String.format("%.2f", alumno.promedio())
            };
            modeloTabla.addRow(fila);
        }
    }
    
    // MÉTODOS ORIGINALES (mantenidos)
    
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
            
            // Actualizar tabla
            actualizarTabla();
            
            // Limpiar campos
            limpiarCampos();
            
            JOptionPane.showMessageDialog(this, "Alumno adicionado correctamente");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Verifique que los datos numéricos sean correctos");
        }
    }
    
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
    
    private void mostrarAprobadosDesaprobados() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("REPORTE DE APROBADOS Y DESAPROBADOS\n");
        reporte.append("===================================\n");
        reporte.append("Criterio de aprobación: Promedio >= 13\n\n");
        
        // Estadísticas generales
        int totalAprobados = aa.cantidadAprobados();
        int totalDesaprobados = aa.cantidadDesaprobados();
        int total = aa.tamaño();
        
        if (total == 0) {
            reporte.append("No hay alumnos registrados.");
            txtResultado.setText(reporte.toString());
            return;
        }
        
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
        if (txtResultado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay reporte para guardar. Genere un reporte primero.",
                "Sin Datos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de texto", "txt"));
        
        int resultado = fileChooser.showSaveDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            
            // Agregar extensión si no la tiene
            if (!archivo.getName().toLowerCase().endsWith(".txt")) {
                archivo = new File(archivo.getAbsolutePath() + ".txt");
            }
            
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileWriter(archivo));
                pw.print(txtResultado.getText());
                
                JOptionPane.showMessageDialog(this, 
                    "Reporte guardado exitosamente en:\n" + archivo.getAbsolutePath(), 
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
        // Configurar Look and Feel del sistema
        
        SwingUtilities.invokeLater(() -> {
            new EjemploMejorado().setVisible(true);
        });
    }
}
