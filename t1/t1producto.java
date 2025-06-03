

    private String codigo;
    private String nombre;
    private double precio;
    private int cantidadStock;
    private int categoria;
    private double descuento;

    public Producto(String codigo, String nombre, double precio, int cantidadStock, int categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
        this.descuento = 0.0;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidadStock() { return cantidadStock; }
    public int getCategoria() { return categoria; }
    public double getDescuento() { return descuento; }

    // Setters
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }
    public void setCategoria(int categoria) { this.categoria = categoria; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    // Método para asignar descuento según categoría
    public void setDescuentoPorCategoria(int categoria) {
        switch (categoria) {
            case 1: this.descuento = 0.10; break; // 10%
            case 2: this.descuento = 0.15; break; // 15%
            case 3: this.descuento = 0.20; break; // 20%
            default: this.descuento = 0.0; break;
        }
    }

    // Método para calcular el precio final con descuento
    public double getPrecioFinalVenta() {
        return precio * (1 - descuento);
    }

    // Método para imprimir
    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    // Método main para probar
    public static void main(String[] args) {
        Producto prod = new Producto("P001", "Laptop HP", 1500.0, 25, 1);
        prod.setDescuentoPorCategoria(prod.getCategoria());
        prod.setCantidadStock(prod.getCantidadStock() + 30);
        prod.setCategoria(2);
        double precioFinal = prod.getPrecioFinalVenta();
        imprimir("Precio final es………" + precioFinal);
    }
}
