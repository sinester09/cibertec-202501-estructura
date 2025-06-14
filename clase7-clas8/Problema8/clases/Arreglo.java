package clases;


public class Arreglo {
    private int[] n = new int[10];

    private int indice = 0;

    public int largo() {
        return this.indice;
    }

    public int obtener(int i) {
        return this.n[i];
    }

    public void adicionar(int numero) {
        if (this.indice == this.n.length)
            ampliarArreglo();
        this.n[this.indice] = numero;
        this.indice++;
    }

    public void eliminarNumerosPares() {
        for (int i = 0; i < this.indice; i++) {
            if (this.n[i] % 2 == 0)
                eliminarElemento(this.n[i]);
        }
    }

    public void eliminarNumerosImpares() {
        for (int i = 0; i < this.indice; i++) {
            if (this.n[i] % 2 != 0)
                eliminarElemento(this.n[i]);
        }
    }

    public void eliminarNumerosRepetidos() {}

    public void primerNumeroParAlFinal() {}

    public void barajarNumeros() {}

    public void eliminarElemento(int elemento) {
        int pos = buscar(elemento);
        if (pos != -1)
            eliminar(pos);
    }

    private int buscar(int elemento) {
        for (int i = 0; i < this.indice; i++) {
            if (this.n[i] == elemento)
                return i;
        }
        return -1;
    }

    private void eliminar(int pos) {
        if (pos < 0 || pos >= this.indice)
            throw new IndexOutOfBoundsException("Posiciinv" + pos);
        for (int i = pos; i < this.indice - 1; i++)
            this.n[i] = this.n[i + 1];
        this.indice--;
        this.n[this.indice] = 0;
    }

    private void ampliarArreglo() {
        int[] aux = this.n;
        this.n = new int[this.indice + 10];
        for (int i = 0; i < this.indice; i++)
            this.n[i] = aux[i];
    }
}
