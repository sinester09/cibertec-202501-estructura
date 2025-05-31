package clases;

public class Restaurante {
    // Atributos privados
    private String ruc;
    private String nombre;
    private TipoRestaurante tipo;
    private int capacidadPersonas;
    private double calificacion;
    
    // Variables de clase
    private static int cantidadRestaurantes = 0;
    private static double totalIngresos = 0.0;
    
    // Constantes públicas
    public static final double PRECIO_BASE = 30.0;
   public static final double BONO_CALIFICACION = 0.20; // 20% si calificación >= 4.5
    
    // Constructor principal
    public Restaurante(String ruc, String nombre, TipoRestaurante tipo, 
                      int capacidadPersonas, double calificacion) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadPersonas = capacidadPersonas;
        this.calificacion = calificacion;
        cantidadRestaurantes++;
        totalIngresos += getIngresoTotal();
    }
    
    // Constructor con parámetros parciales
    public Restaurante(String ruc, String nombre, TipoRestaurante tipo) {
        this(ruc, nombre, tipo, 50, 3.5);
    }
    
    // Constructor sin parámetros
    public Restaurante() {
        this("00000000000", "Restaurante Genérico", new TipoRestaurante());
    }
    
    // Métodos get/set
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public TipoRestaurante getTipo() { return tipo; }
    public void setTipo(TipoRestaurante tipo) { this.tipo = tipo; }
    
    public int getCapacidadPersonas() { return capacidadPersonas; }
    public void setCapacidadPersonas(int capacidadPersonas) { this.capacidadPersonas = capacidadPersonas; }
    
    public double getCalificacion() { return calificacion; }
    public void setCalificacion(double calificacion) { this.calificacion = calificacion; }
    
    // Métodos estáticos
    public static int getCantidadRestaurantes() { return cantidadRestaurantes; }
    public static void setCantidadRestaurantes(int cantidad) { cantidadRestaurantes = cantidad; }
    
    public static double getTotalIngresos() { return totalIngresos; }
    public static void setTotalIngresos(double total) { totalIngresos = total; }
    
    // Método que retorna el precio por persona según tipo
    public double getPrecioPorPersona() {
        String tipoNombre = tipo.getNombre();
        double factorPrecio = tipo.getFactorPrecio();
        
        if (tipoNombre.equals("Gourmet")) {
            return 80.0 * factorPrecio;
        } else if (tipoNombre.equals("Familiar")) {
            return 45.0 * factorPrecio;
        } else if (tipoNombre.equals("Fast Food")) {
            return 25.0 * factorPrecio;
        } else if (tipoNombre.equals("Pizzería")) {
            return 35.0 * factorPrecio;
        } else if (tipoNombre.equals("Cevicheria")) {
            return 60.0 * factorPrecio;
        } else {
            return PRECIO_BASE * factorPrecio;
        }
    }
    
    // Método que retorna el bono por calificación
    public double getBonoPorCalificacion() {
        if (calificacion >= 4.5) {
            return getPrecioPorPersona() * BONO_CALIFICACION;
        }
        return 0.0;
    }
    
    // Método que retorna el precio final por persona (precio + bono)
    public double getPrecioFinal() {
        return getPrecioPorPersona() + getBonoPorCalificacion();
    }
    
    // Método que retorna el ingreso total (precio final * capacidad)
    public double getIngresoTotal() {
        return getPrecioFinal() * capacidadPersonas;
    }
}
