package clases;

public class TipoRestaurante {
    // Atributos privados
    private String codigo;
    private String nombre;
    private double factorPrecio;
    
    // Variables de clase
    private static int cantidadTipos = 0;
    private static double sumaFactores = 0.0;
    
    // Constante pública
    public static final double FACTOR_BASE = 1.0;
    
    // Constructor principal
    public TipoRestaurante(String codigo, String nombre, double factorPrecio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.factorPrecio = factorPrecio;
        cantidadTipos++;
        sumaFactores += factorPrecio;
    }
    
    // Constructor con parámetros parciales
    public TipoRestaurante(String codigo, String nombre) {
        this(codigo, nombre, FACTOR_BASE);
    }
    
    // Constructor sin parámetros
    public TipoRestaurante() {
        this("T000", "Tipo Genérico");
    }
    
    // Métodos get/set para atributos
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public double getFactorPrecio() { return factorPrecio; }
    public void setFactorPrecio(double factorPrecio) { this.factorPrecio = factorPrecio; }
    
    // Métodos estáticos para variables de clase
    public static int getCantidadTipos() { return cantidadTipos; }
    public static void setCantidadTipos(int cantidadTipos) { TipoRestaurante.cantidadTipos = cantidadTipos; }
    
    public static double getSumaFactores() { return sumaFactores; }
    public static void setSumaFactores(double sumaFactores) { TipoRestaurante.sumaFactores = sumaFactores; }
}
