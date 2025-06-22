
import java.util.ArrayList;

public class ArregloAlumnos {
    
    // Atributo privado ArrayList alu de tipo Alumno
    private ArrayList<Alumno> alu;
    
    // Constructor que crea el ArrayList alu de tipo Alumno y autogenera cuatro ingresos
    public ArregloAlumnos() {
        alu = new ArrayList<Alumno>();
        
        // Autogenerar cuatro ingresos
        alu.add(new Alumno(1001, "Cristopher", 12, 15,12));
        alu.add(new Alumno(1002, "Michael", 05, 10,13));
        alu.add(new Alumno(1003, "Victor", 13, 20,12));
        alu.add(new Alumno(1004, "Yuyos", 05, 03,13));
    }
    
    // OPERACIONES PÚBLICAS BÁSICAS
    
    // Método adicionar que recibe la dirección de memoria de un nuevo alumno y lo adiciona al ArrayList
    public void adicionar(Alumno alumno) {
        alu.add(alumno);
    }
    
    // Método tamaño que retorna la cantidad de alumnos registrados hasta ese momento
    public int tamaño() {
        return alu.size();
    }
    
    // Método obtener que recibe la posición y retorna la dirección de memoria del alumno respectivo
    public Alumno obtener(int posicion) {
        return alu.get(posicion);
    }
    
    // OPERACIONES PÚBLICAS COMPLEMENTARIAS
    
    // Método promedioGeneral que retorna el promedio de promedios
    public double promedioGeneral() {
        if (alu.isEmpty()) {
            return 0.0;
        }
        
        double suma = 0.0;
        for (Alumno alumno : alu) {
            suma += alumno.promedio();
        }
        return suma / alu.size();
    }
    
    // Método promedioMayor que retorna el mayor promedio
    public double promedioMayor() {
        if (alu.isEmpty()) {
            return 0.0;
        }
        
        double mayor = alu.get(0).promedio();
        for (Alumno alumno : alu) {
            if (alumno.promedio() > mayor) {
                mayor = alumno.promedio();
            }
        }
        return mayor;
    }
    
    // Método que retorna la cantidad de alumnos aprobados (promedio >= 13)
    public int cantidadAprobados() {
        int contador = 0;
        for (Alumno alumno : alu) {
            if (alumno.promedio() >= 13.0) {
                contador++;
            }
        }
        return contador;
    }
    
    // Método que retorna la cantidad de alumnos desaprobados (promedio < 13)
    public int cantidadDesaprobados() {
        int contador = 0;
        for (Alumno alumno : alu) {
            if (alumno.promedio() < 13.0) {
                contador++;
            }
        }
        return contador;
    }
    
    // Método que retorna ArrayList con alumnos aprobados
    public ArrayList<Alumno> obtenerAprobados() {
        ArrayList<Alumno> aprobados = new ArrayList<Alumno>();
        for (Alumno alumno : alu) {
            if (alumno.promedio() >= 13.0) {
                aprobados.add(alumno);
            }
        }
        return aprobados;
    }
    
    // Método que retorna ArrayList con alumnos desaprobados
    public ArrayList<Alumno> obtenerDesaprobados() {
        ArrayList<Alumno> desaprobados = new ArrayList<Alumno>();
        for (Alumno alumno : alu) {
            if (alumno.promedio() < 13.0) {
                desaprobados.add(alumno);
            }
        }
        return desaprobados;
    }
}