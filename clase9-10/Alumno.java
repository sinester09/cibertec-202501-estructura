public class Alumno {
    
    // Atributos privados
    private int codigo, nota1, nota2,nota3;
    private String nombre;
    
    // Constructor
    public Alumno(int codigo, String nombre, int nota1, int nota2,int nota3) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }
    
    // Métodos de acceso público set/get
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }
    
    public int getNota1() {
        return nota1;
    }
    
    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }
    
    public int getNota2() {
        return nota2;
    }
       public void setNota3(int nota3) {
        this.nota3 = nota3;
    }
    
    public int getNota3() {
        return nota3;
    }
    
    // Método promedio
    public double promedio() {
        return ((nota1 *0.20)+(nota2*0.35)+(nota3*0.45));
    }

}