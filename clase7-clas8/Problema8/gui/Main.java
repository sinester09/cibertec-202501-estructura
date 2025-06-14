
import clases.Arreglo;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JLabel lblNumero;

    private JTextField txtNumero;

    private JButton btnAdicionar;

    private JButton btnEliminarPares;

    private JButton btnEliminarImpares;

    private JButton btnEliminarRepetidos;

    private JButton btnPrimerParAlFinal;

    private JButton btnBarajar;

    private JScrollPane scrollPane;

    private JTextArea txtS;

    Arreglo a;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        this.a = new Arreglo();
        setTitle("Problema_8_1");
        setDefaultCloseOperation(3);
        setBounds(100, 100, 600, 500);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.lblNumero = new JLabel("N");
        this.lblNumero.setBounds(10, 11, 50, 23);
        this.contentPane.add(this.lblNumero);
        this.txtNumero = new JTextField();
        this.txtNumero.setFont(new Font("Monospaced", 0, 13));
        this.txtNumero.setBounds(66, 11, 50, 23);
        this.contentPane.add(this.txtNumero);
        this.txtNumero.setColumns(10);
        this.btnAdicionar = new JButton("Adicionar");
        this.btnAdicionar.addActionListener(this);
        this.btnAdicionar.setBounds(380, 48, 195, 23);
        this.contentPane.add(this.btnAdicionar);
        this.btnEliminarPares = new JButton("Eliminar npares");
        this.btnEliminarPares.addActionListener(this);
        this.btnEliminarPares.setBounds(380, 78, 195, 23);
        this.contentPane.add(this.btnEliminarPares);
        this.btnEliminarImpares = new JButton("Eliminar nimpares");
        this.btnEliminarImpares.addActionListener(this);
        this.btnEliminarImpares.setBounds(380, 108, 195, 23);
        this.contentPane.add(this.btnEliminarImpares);
        this.btnEliminarRepetidos = new JButton("Eliminar nrepetidos");
        this.btnEliminarRepetidos.addActionListener(this);
        this.btnEliminarRepetidos.setBounds(380, 138, 195, 23);
        this.contentPane.add(this.btnEliminarRepetidos);
        this.btnPrimerParAlFinal = new JButton("Primer npar al final");
        this.btnPrimerParAlFinal.addActionListener(this);
        this.btnPrimerParAlFinal.setBounds(380, 168, 195, 23);
        this.contentPane.add(this.btnPrimerParAlFinal);
        this.btnBarajar = new JButton("Barajar los n");
        this.btnBarajar.addActionListener(this);
        this.btnBarajar.setBounds(380, 198, 195, 23);
        this.contentPane.add(this.btnBarajar);
        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(10, 48, 360, 400);
        this.contentPane.add(this.scrollPane);
        this.txtS = new JTextArea();
        this.txtS.setFont(new Font("Monospaced", 0, 13));
        this.scrollPane.setViewportView(this.txtS);
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.btnBarajar)
            actionPerformedBtnBarajar(arg0);
        if (arg0.getSource() == this.btnPrimerParAlFinal)
            actionPerformedBtnPrimerParAlFinal(arg0);
        if (arg0.getSource() == this.btnEliminarRepetidos)
            actionPerformedBtnEliminarRepetidos(arg0);
        if (arg0.getSource() == this.btnEliminarImpares)
            actionPerformedBtnEliminarImpares(arg0);
        if (arg0.getSource() == this.btnEliminarPares)
            actionPerformedBtnEliminarPares(arg0);
        if (arg0.getSource() == this.btnAdicionar)
            actionPerformedBtnAdicionar(arg0);
        limpieza();
    }

    protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
        try {
            this.a.adicionar(leerNumero());
            listar();
        } catch (Exception e) {
            mensaje("error de ingreso");
        }
    }

    protected void actionPerformedBtnEliminarPares(ActionEvent arg0) {
        this.a.eliminarNumerosPares();
        listar();
    }

    protected void actionPerformedBtnEliminarImpares(ActionEvent arg0) {
        try {
            this.a.eliminarNumerosImpares();
            listar();
        } catch (Exception e) {
            mensaje("error de ingreso");
        }
    }

    protected void actionPerformedBtnEliminarRepetidos(ActionEvent arg0) {
        this.a.eliminarNumerosRepetidos();
        listar();
    }

    protected void actionPerformedBtnPrimerParAlFinal(ActionEvent arg0) {
        this.a.primerNumeroParAlFinal();
        listar();
    }

    protected void actionPerformedBtnBarajar(ActionEvent arg0) {
        this.a.barajarNumeros();
        listar();
    }

    void imprimir() {
        imprimir("");
    }

    void limpieza() {
        this.txtNumero.setText("");
        this.txtNumero.requestFocus();
    }

    void listar() {
        this.txtS.setText("");
        for (int i = 0; i < this.a.largo(); i++)
            imprimir("n[" + i + "] : " + this.a.obtener(i));
    }

    void imprimir(String s) {
        this.txtS.append(s + "\n");
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    int leerNumero() {
        return Integer.parseInt(this.txtNumero.getText().trim());
    }
}
