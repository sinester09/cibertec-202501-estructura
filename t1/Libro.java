
public class Libro {
    // Atributos de instancia
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private int añoPublicacion;
    private String tipo; // Nuevo atributo

    // Atributos de clase
    private static int cantidadLibros = 0;
    private static double totalPreciosFinales = 0.0;

    // Constante de clase
    public static final double FACTOR_DESCUENTO_ANTIGUO = 0.08;

    // Constructores
    public Libro(String isbn, String titulo, String autor, String editorial, int añoPublicacion, String tipo) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.añoPublicacion = añoPublicacion;
        this.tipo = tipo;
        cantidadLibros++;
        totalPreciosFinales += getPrecioFinal();
    }

    public Libro(String isbn, String titulo) {
        this(isbn, titulo, "Autor Desconocido", "Editorial Genérica", 2020, "Ficción");
    }

    public Libro() {
        this("ISBN-000", "Título Genérico");
    }

    // Métodos set/get de clase
    public static int getCantidadLibros() {
        return cantidadLibros;
    }

    public static double getTotalPreciosFinales() {
        return totalPreciosFinales;
    }

    public static void setCantidadLibros(int cantidad) {
        cantidadLibros = cantidad;
    }

    public static void setTotalPreciosFinales(double total) {
        totalPreciosFinales = total;
    }

    // Métodos set/get de instancia
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getAñoPublicacion() { return añoPublicacion; }
    public void setAñoPublicacion(int añoPublicacion) { this.añoPublicacion = añoPublicacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    // Método para obtener el precio base
    public double getPrecioBase() {
        if (tipo.equalsIgnoreCase("Ficción")) {
            if (editorial.equalsIgnoreCase("Planeta")) return 45.00;
            if (editorial.equalsIgnoreCase("Alfaguara")) return 42.00;
        } else if (tipo.equalsIgnoreCase("Académico")) {
            if (editorial.equalsIgnoreCase("Pearson")) return 85.00;
            if (editorial.equalsIgnoreCase("McGraw-Hill")) return 78.00;
        } else if (tipo.equalsIgnoreCase("Técnico")) {
            if (editorial.equalsIgnoreCase("O'Reilly")) return 120.00;
            if (editorial.equalsIgnoreCase("Addison-Wesley")) return 115.00;
        }
        return 50.00; // Precio por defecto si no coincide
    }

    // Método para calcular el descuento por antigüedad
    public double getDescuentoAntigüedad() {
        return (añoPublicacion < 2018) ? getPrecioBase() * FACTOR_DESCUENTO_ANTIGUO : 0.0;
    }

    // Método para calcular el precio final
    public double getPrecioFinal() {
        return getPrecioBase() - getDescuentoAntigüedad();
    }

    // Método listado
    public void listado() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Editorial: " + editorial);
        System.out.println("Año de Publicación: " + añoPublicacion);
        System.out.println("Precio Base: S/. " + getPrecioBase());
        System.out.println("Descuento por Antigüedad: S/. " + getDescuentoAntigüedad());
        System.out.println("Precio Final: S/. " + getPrecioFinal());
        System.out.println("-----------------------------------");
    }
}
