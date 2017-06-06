
package projectecinesexamen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class finestraSala extends JPanel {
    private JButton botoalta,botobaixa,botomodificar;
    private JLabel lid,lsala,lbutaques,ltitol;
    private JTextField tsala,tbutaques;
    private JComboBox boxcine;
    private JPanel panel1,panel2,panel3,panel4;
    private Font gran=new Font("Arial",1,32);
    private Font normal=new Font("Arial",1,20);
    
    private Cines c=null;
    private CinesBD cbd=null;
    private Salas s=null;
    private SalasBD sbd=null;
    private int files=0;
    
    private ArrayList<Cines> llistaCines=new ArrayList<Cines>();
    private ArrayList<Salas> llistaSalas=new ArrayList<Salas>();
    private JScrollPane panelscroll;
    private JTable taula;
    private salaModel modelsala;
    
    class EscoltarBoto implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getActionCommand().equals("Alta")) {
                    sbd.connectar();
                    s=new Salas();
                    s.setIdCine((int)boxcine.getItemAt(boxcine.getSelectedIndex()));
                    s.setIdSala(Integer.parseInt(tsala.getText()));
                    s.setButaques(Integer.parseInt(tbutaques.getText()));
                    files=sbd.alta(s);
                    if(files==1) {
                        JOptionPane.showMessageDialog(null,"Alta realitzada");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No s'ha pogut fer l'alta");
                    }
                    sbd.desconnectar();
                    recargaDatos();
            }
            else if(event.getActionCommand().equals("Baixa")) {
                    sbd.connectar();
                    int idCine=(int)boxcine.getItemAt(boxcine.getSelectedIndex());
                    int idSala=Integer.parseInt(tsala.getText());
                    files=sbd.baixa(idCine,idSala);
                     if(files==1) {
                        JOptionPane.showMessageDialog(null,"Baixa realitzada");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No s'ha pogut fer la baixa");
                    }
                    sbd.desconnectar();
                    recargaDatos();
            }
             else if(event.getActionCommand().equals("Modificar")) {
                    sbd.connectar();
                    s=new Salas();
                    s.setIdCine((int)boxcine.getItemAt(boxcine.getSelectedIndex()));
                    s.setIdSala(Integer.parseInt(tsala.getText()));
                    s.setButaques(Integer.parseInt(tbutaques.getText()));
                    files=sbd.modificacio(s);
                     if(files==1) {
                        JOptionPane.showMessageDialog(null,"Modificació realitzada");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No s'ha pogut fer la modificació");
                    }
                    sbd.desconnectar();
                    recargaDatos();
            }
             
        }
    }
    public finestraSala() {
        this.initDades();
        this.initGrafics();
    }
    public void initDades() {
        cbd=new CinesBD();
        sbd=new SalasBD();
        cargarDatos();
    }
    public void initGrafics() {
        this.setLayout(new BorderLayout());
        ltitol=new JLabel("Gestió sales");
        ltitol.setFont(gran);
        this.add(ltitol,BorderLayout.NORTH);
        initPanel1();
        initPanel2();
        initPanel3();
        initPanel4();
        this.add(panel1,BorderLayout.WEST);
        this.add(panel2,BorderLayout.CENTER);
        this.add(panel3,BorderLayout.SOUTH);
        this.add(panel4,BorderLayout.EAST);
        
    }
    public void initPanel1() {
        panel1=new JPanel();
        panel1.setLayout(new GridLayout(6,1));
        lid=new JLabel("Codi Cine......: ");lid.setFont(normal);
        lsala=new JLabel("Codi Sala......: ");lsala.setFont(normal);
        lbutaques=new JLabel("Butaques.......: ");lbutaques.setFont(normal);
        
        panel1.add(lid);panel1.add(new JLabel(""));
        panel1.add(lsala);panel1.add(new JLabel(""));
        panel1.add(lbutaques);panel1.add(new JLabel(""));
       
    }
     public void initPanel2() {
        panel2=new JPanel();
        panel2.setLayout(new GridLayout(6,1));
        boxcine=new JComboBox();
        cargaCines();
        tsala=new JTextField(10);tsala.setFont(normal);
        tbutaques=new JTextField(10);tbutaques.setFont(normal);
      
        panel2.add(boxcine);panel2.add(new JLabel(""));
        panel2.add(tsala);panel2.add(new JLabel(""));
        panel2.add(tbutaques);panel2.add(new JLabel(""));
       
    }
     
     public void initPanel3() {
        panel3=new JPanel();
        panel3.setLayout(new FlowLayout());
        botoalta=new JButton("Alta");botoalta.setFont(normal);
        botoalta.addActionListener(new EscoltarBoto());
        panel3.add(botoalta);
        botobaixa=new JButton("Baixa");botobaixa.setFont(normal);
        botobaixa.addActionListener(new EscoltarBoto());
        panel3.add(botobaixa);
        botomodificar=new JButton("Modificar");botomodificar.setFont(normal);
        botomodificar.addActionListener(new EscoltarBoto());
        panel3.add(botomodificar);
       
        
    }
   
    public void initPanel4() {
        
        panel4=new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(panelscroll);
        
    }
    public void cargarDatos() {
        sbd.connectar();
        llistaSalas=sbd.consultaTots();
        sbd.desconnectar();
        modelsala=new salaModel(llistaSalas);
        taula=new JTable(modelsala);
        taula.setFont(normal);
        taula.setRowHeight(25);
        taula.getTableHeader().setForeground(Color.red);
        taula.getTableHeader().setFont(normal);
        panelscroll = new JScrollPane(taula,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelscroll.setPreferredSize(new Dimension(650,300));
        
    }
    
    public void recargaDatos() {
        sbd.connectar();
        llistaSalas=sbd.consultaTots();
        sbd.desconnectar();
        modelsala=new salaModel(llistaSalas);
        taula.setModel(modelsala);
        
    }
    
    public void cargaCines() {
        cbd.connectar();
        
        for(Cines c: cbd.consultaTots()) {
            boxcine.addItem(c.getIdCine());
        }
        cbd.desconnectar();
    }
}
