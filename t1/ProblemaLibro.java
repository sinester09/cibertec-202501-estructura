
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProblemaLibro extends JFrame {
    private JTextArea areaTexto;
    private JButton botonProcesar;

    public ProblemaLibro() {
        setTitle("Gestión de Libros");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        botonProcesar = new JButton("Procesar");
        botonProcesar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarLibros();
            }
        });

        add(scroll, BorderLayout.CENTER);
        add(botonProcesar, BorderLayout.SOUTH);
    }

    private void procesarLibros() {
        // Crear libros
        Libro libro1 = new Libro("ISBN-123", "Java Avanzado", "Juan Pérez", "Pearson", 2015, "Académico");
        Libro libro2 = new Libro("ISBN-456", "Programación Básica");
        Libro libro3 = new Libro();

        // Mostrar datos
        areaTexto.setText(""); // Limpiar área
        mostrarLibro(libro1);
        mostrarLibro(libro2);
        mostrarLibro(libro3);

        // Mostrar estadísticas
        areaTexto.append("FACTOR DE DESCUENTO ANTIGUO: " + Libro.FACTOR_DESCUENTO_ANTIGUO + "\n");
        areaTexto.append("Cantidad de Libros Creados: " + Libro.getCantidadLibros() + "\n");
        areaTexto.append("Total de Precios Finales: S/. " + Libro.getTotalPreciosFinales() + "\n");
    }

    private void mostrarLibro(Libro libro) {
        areaTexto.append("ISBN: " + libro.getIsbn() + "\n");
        areaTexto.append("Título: " + libro.getTitulo() + "\n");
        areaTexto.append("Autor: " + libro.getAutor() + "\n");
        areaTexto.append("Editorial: " + libro.getEditorial() + "\n");
        areaTexto.append("Año de Publicación: " + libro.getAñoPublicacion() + "\n");
        areaTexto.append("Precio Base: S/. " + libro.getPrecioBase() + "\n");
        areaTexto.append("Descuento por Antigüedad: S/. " + libro.getDescuentoAntigüedad() + "\n");
        areaTexto.append("Precio Final: S/. " + libro.getPrecioFinal() + "\n");
        areaTexto.append("-----------------------------------\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProblemaLibro().setVisible(true);
            }
        });
    }
}
