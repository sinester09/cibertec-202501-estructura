package gui;

import clases.Restaurante;
import clases.TipoRestaurante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ProblemaRestaurante extends JFrame implements ActionListener {
    
    // Componentes de la interfaz
    private JLabel lblTitulo;
    private JButton btnProcesar, btnLimpiar, btnSalir;
    private JTextArea txtResultados;
    private JScrollPane scrollPane;
    
    public ProblemaRestaurante() {
        super("Problema - Restaurante");
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        // Título
        lblTitulo = new JLabel("SISTEMA DE RESTAURANTES", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLUE);
        
        // Botones
        btnProcesar = new JButton("Procesar");
        btnLimpiar = new JButton("Limpiar");
        btnSalir = new JButton("Salir");
        
        // Configurar botones
        btnProcesar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnSalir.addActionListener(this);
        
        // Área de texto para resultados
        txtResultados = new JTextArea(25, 60);
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Courier New", Font.PLAIN, 12));
        scrollPane = new JScrollPane(txtResultados);
        
        // Panel para botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnProcesar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnSalir);
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        
        // Layout principal
        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProcesar) {
            procesarRestaurantes();
        } else if (e.getSource() == btnLimpiar) {
            txtResultados.setText("");
        } else if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }
    
    private void procesarRestaurantes() {
        StringBuilder resultado = new StringBuilder();
        
        resultado.append("PROCESAMIENTO DE RESTAURANTES\n");
        resultado.append("=".repeat(50)).append("\n\n");
        
        // Crear tipos de restaurante
        TipoRestaurante tipoGourmet = new TipoRestaurante("T001", "Gourmet", 1.5);
        TipoRestaurante tipoFamiliar = new TipoRestaurante("T002", "Familiar", 1.2);
        TipoRestaurante tipoFastFood = new TipoRestaurante("T003", "Cevicheria");
        
        // Crear restaurantes usando los tres constructores
        
        // 1. Constructor completo
        Restaurante rest1 = new Restaurante("20123456789", "el barrun", 
                                          tipoGourmet, 80, 4.8);
        
        // 2. Constructor parcial
        Restaurante rest2 = new Restaurante("20987654321", "Sabores de Casa", tipoFamiliar);
        
        // 3. Constructor sin parámetros
        Restaurante rest3 = new Restaurante();
        rest3.setRuc("20456789123");
        rest3.setNombre("Burger Express");
        rest3.setTipo(tipoFastFood);
        rest3.setCapacidadPersonas(60);
        rest3.setCalificacion(3.8);
        
        // Mostrar datos de cada restaurante
        resultado.append("RESTAURANTE #1 (Constructor completo):\n");
        resultado.append(listarDatos(rest1)).append("\n");
        
        resultado.append("RESTAURANTE #2 (Constructor parcial):\n");
        resultado.append(listarDatos(rest2)).append("\n");
        
        resultado.append("RESTAURANTE #3 (Constructor sin parámetros):\n");
        resultado.append(listarDatos(rest3)).append("\n");
        
        // Mostrar estadísticas generales
        resultado.append("ESTADÍSTICAS GENERALES:\n");
        resultado.append("-".repeat(30)).append("\n");
        resultado.append("Bono por calificación: ").append(Restaurante.BONO_CALIFICACION * 100).append("%\n");
        resultado.append("Cantidad de restaurantes creados: ").append(Restaurante.getCantidadRestaurantes()).append("\n");
        resultado.append("Total de ingresos estimados: S/ ").append(String.format("%.2f", Restaurante.getTotalIngresos())).append("\n");
        resultado.append("Cantidad de tipos creados: ").append(TipoRestaurante.getCantidadTipos()).append("\n");
        resultado.append("Suma de factores de precio: ").append(String.format("%.2f", TipoRestaurante.getSumaFactores())).append("\n");
        
        txtResultados.setText(resultado.toString());
    }
    
    // Método listado que recibe la dirección de memoria del objeto Restaurante
    private String listarDatos(Restaurante restaurante) {
        StringBuilder datos = new StringBuilder();
        
        datos.append("RUC: ").append(restaurante.getRuc()).append("\n");
        datos.append("Nombre: ").append(restaurante.getNombre()).append("\n");
        datos.append("Tipo: ").append(restaurante.getTipo().getNombre()).append("\n");
        datos.append("Código tipo: ").append(restaurante.getTipo().getCodigo()).append("\n");
        datos.append("Factor precio: ").append(restaurante.getTipo().getFactorPrecio()).append("\n");
        datos.append("Capacidad: ").append(restaurante.getCapacidadPersonas()).append(" personas\n");
        datos.append("Calificación: ").append(restaurante.getCalificacion()).append("\n");
        datos.append("Precio por persona: S/ ").append(String.format("%.2f", restaurante.getPrecioPorPersona())).append("\n");
        datos.append("Bono por calificación: S/ ").append(String.format("%.2f", restaurante.getBonoPorCalificacion())).append("\n");
        datos.append("Precio final por persona: S/ ").append(String.format("%.2f", restaurante.getPrecioFinal())).append("\n");
        datos.append("Ingreso total estimado: S/ ").append(String.format("%.2f", restaurante.getIngresoTotal())).append("\n");
        datos.append("-".repeat(40)).append("\n");
        
        return datos.toString();
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new ProblemaRestaurante().setVisible(true);
        });
    }
}
