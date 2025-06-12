public class ArregloLineal {
    private int[] n;
    private int indice;
    private int capacidad;
    
    public ArregloLineal(int tamaño) {
        this.capacidad = tamaño;
        this.n = new int[tamaño];
        this.indice = 0;
    }
    
    // Método público para agregar
    public void agregar(int elemento) {
        if (indice < capacidad) {
            n[indice] = elemento;
            indice++;
        }
    }
    
    // MÉTODOS PRIVADOS (operaciones internas)
    
    private int buscar(int elemento) {
        for (int i = 0; i < indice; i++) {
            if (n[i] == elemento) {
                return i;
            }
        }
        return -1;
    }
    
    private void intercambiar(int pos1, int pos2) {
        if (pos1 < 0 || pos1 >= indice || pos2 < 0 || pos2 >= indice) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        
        int temp = n[pos1];
        n[pos1] = n[pos2];
        n[pos2] = temp;
    }
    
    private void eliminar(int pos) {
        if (pos < 0 || pos >= indice) {
            throw new IndexOutOfBoundsException("Posición inválida: " + pos);
        }
        
        for (int i = pos; i < indice - 1; i++) {
            n[i] = n[i + 1];
        }
        
        indice--;
        n[indice] = 0;
    }
    
    // MÉTODOS PÚBLICOS (interfaz hacia el exterior)
    
    public boolean existe(int elemento) {
        return buscar(elemento) != -1;
    }
    
    public void eliminarElemento(int elemento) {
        int pos = buscar(elemento);
        if (pos != -1) {
            eliminar(pos);
        }
    }
    
    public void ordenarBurbuja() {
        for (int i = 0; i < indice - 1; i++) {
            for (int j = 0; j < indice - 1 - i; j++) {
                if (n[j] > n[j + 1]) {
                    intercambiar(j, j + 1);
                }
            }
        }
    }
    
    public int obtener(int pos) {
        if (pos < 0 || pos >= indice) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        return n[pos];
    }
    
    public int tamaño() {
        return indice;
    }
    
    public void mostrar() {
        System.out.print("[");
        for (int i = 0; i < indice; i++) {
            System.out.print(n[i]);
            if (i < indice - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
