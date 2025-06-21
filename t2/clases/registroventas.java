ublic class RegistroVentas {
    private double ventas[];
    private int indice;

    public RegistroVentas() {
        ventas = new double[10];
        indice = 0;
    }

    public int tamaño() {
        return indice;
    }

    public double obtener(int p) {
        return ventas[p];
    }

    public void adicionar(double v) {
        if (indice == ventas.length)
            ampliarArreglo();
        ventas[indice] = v;
        indice++;
    }

    private void ampliarArreglo() {
        double aux[] = ventas;
        ventas = new double[indice + 10];
        for (int i = 0; i < indice; i++)
            ventas[i] = aux[i];
    }

    public void eliminarTodo() {
        indice = 0;
    }

    public void eliminarFinal() {
        if (indice > 0)
            indice--;
    }

    public int posicionUltimaVentaMenorQue1500() {
        for (int i = indice - 1; i >= 0; i--) {
            if (ventas[i] < 1500) {
                return i;
            }
        }
        return -1;
    }

    public double sumaVentasMayoresQue2000() {
        double suma = 0;
        for (int i = 0; i < indice; i++) {
            if (ventas[i] > 2000) {
                suma += ventas[i];
            }
        }
        return suma;
    }

    public boolean reemplazarUltimaVentaMenorQue1500() {
        int pos = posicionUltimaVentaMenorQue1500();
        if (pos == -1) {
            return false;
        }
        double suma = sumaVentasMayoresQue2000();
        ventas[pos] = suma;
        return true;
    }

    public int incrementarVentasMenoresQue1800() {
        int count = 0;
        for (int i = 0; i < indice; i++) {
            if (ventas[i] < 1800) {
                ventas[i] *= 1.10;
                count++;
            }
        }
        return count;
    }

    public boolean eliminarUltimaVentaMenorQue1500() {
        int pos = posicionUltimaVentaMenorQue1500();
        if (pos == -1) {
            return false;
        }
        for (int i = pos; i < indice - 1; i++) {
            ventas[i] = ventas[i + 1];
        }
        indice--;
        return true;
    }

    public double[] listarVentasEntre3000y4000() {
        int cont = 0;
        for (int i = 0; i < indice; i++) {
            if (ventas[i] >= 3000 && ventas[i] <= 4000) {
                cont++;
            }
        }
        double[] lista = new double[cont];
        int j = 0;
        for (int i = 0; i < indice; i++) {
            if (ventas[i] >= 3000 && ventas[i] <= 4000) {
                lista[j++] = ventas[i];
            }
        }
        return lista;
    }

    public String listar() {
        if (indice == 0) return "No hay ventas registradas.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indice; i++) {
            sb.append(ventas[i]);
            if (i < indice - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
