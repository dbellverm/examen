
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public class finestraCine extends JPanel {
    private JButton botoalta,botobaixa,botomodificar, botoconsulta,bototots;
    private JLabel lid,lnom,lpob, ltitol;
    private JTextField tid,tnom,tpob;
    private JComboBox boxpob;
    private JPanel panel1,panel2,panel3,panel4;
    private Font gran=new Font("Arial",1,32);
    private Font normal=new Font("Arial",1,20);
    
    private Cines c=null;
    private CinesBD cbd=null;
    private int files=0;
    
    private ArrayList<Cines> llistaCines=new ArrayList<Cines>();
    private JScrollPane panelscroll;
    private JTable taula;
    private cineModel modelcine;
    
    class EscoltarBoto implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getActionCommand().equals("Alta")) {
                    cbd.connectar();
                    c=new Cines();
                    c.setNomCine(tnom.getText());
                    c.setPobCine(tpob.getText());
                    files=cbd.alta(c);
                    if(files==1) {
                        JOptionPane.showMessageDialog(null,"Alta realitzada");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No s'ha pogut fer l'alta");
                    }
                    cbd.desconnectar();
                    recargaDatos();
            }
            else if(event.getActionCommand().equals("Baixa")) {
                    cbd.connectar();
                    int id=Integer.parseInt(tid.getText());
                    files=cbd.baixa(id);
                     if(files==1) {
                        JOptionPane.showMessageDialog(null,"Baixa realitzada");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No s'ha pogut fer la baixa");
                    }
                    cbd.desconnectar();
                    recargaDatos();
            }
             else if(event.getActionCommand().equals("Modificar")) {
                    cbd.connectar();
                    c=new Cines();
                    c.setIdCine(Integer.parseInt(tid.getText()));
                    c.setNomCine(tnom.getText());
                    c.setPobCine(tpob.getText());
                    files=cbd.modificacio(c);
                     if(files==1) {
                        JOptionPane.showMessageDialog(null,"Modificació realitzada");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No s'ha pogut fer la modificació");
                    }
                    cbd.desconnectar();
                    recargaDatos();
            }
             else if(event.getActionCommand().equals("Consultar per població")) {
                    //String poblacio=tpob.getText(); // agafa la poblacio del JTextField
                    String poblacio=(String)boxpob.getItemAt(boxpob.getSelectedIndex());
                    String condicio="pobCine = '"+poblacio+"'";
                    recargaDatos(condicio);
            }
             else if(event.getActionCommand().equals("Llistar tots")) {
                    recargaDatos();
            }
            
        }
    }
    public finestraCine() {
        this.initDades();
        this.initGrafics();
    }
    public void initDades() {
        cbd=new CinesBD();
        cargarDatos();
    }
    public void initGrafics() {
        this.setLayout(new BorderLayout());
        this.setLocation(150,150);
        ltitol=new JLabel("Gestió cines");
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
        lid=new JLabel("Codi..........: ");lid.setFont(normal);
        lnom=new JLabel("Nom...........: ");lnom.setFont(normal);
        lpob=new JLabel("Població......: ");lpob.setFont(normal);
        boxpob=new JComboBox();
        cargaComboBox();
        panel1.add(lid);panel1.add(new JLabel(""));
        panel1.add(lnom);panel1.add(new JLabel(""));
        panel1.add(lpob);panel1.add(boxpob);
       
    }
     public void initPanel2() {
        panel2=new JPanel();
        panel2.setLayout(new GridLayout(6,1));
        tid=new JTextField(5);tid.setFont(normal);
        tnom=new JTextField(25);tnom.setFont(normal);
        tpob=new JTextField(20);tpob.setFont(normal);
      
        panel2.add(tid);panel2.add(new JLabel(""));
        panel2.add(tnom);panel2.add(new JLabel(""));
        panel2.add(tpob);panel2.add(new JLabel(""));
       
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
        botoconsulta=new JButton("Consultar per població");
        botoconsulta.setFont(normal);
        botoconsulta.addActionListener(new EscoltarBoto());
        panel3.add(botoconsulta);
        bototots=new JButton("Llistar tots");
        bototots.setFont(normal);
        bototots.addActionListener(new EscoltarBoto());
        panel3.add(bototots);
        
    }
    public void initPanel4() {
        
        panel4=new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(panelscroll);
        
    }
    
    public void cargarDatos() {
        cbd.connectar();
        llistaCines=cbd.consultaTots();
        cbd.desconnectar();
        modelcine=new cineModel(llistaCines);
        taula=new JTable(modelcine);
        taula.setFont(normal);
        taula.setRowHeight(25);
        taula.getTableHeader().setForeground(Color.red);
        taula.getTableHeader().setFont(normal);
        panelscroll = new JScrollPane(taula,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelscroll.setPreferredSize(new Dimension(650,300));
    }
    
    public void recargaDatos() {
        cbd.connectar();
        llistaCines=cbd.consultaTots();
        cbd.desconnectar();
        modelcine=new cineModel(llistaCines);
        taula.setModel(modelcine);
        cargaComboBox();
    }
    public void recargaDatos(String condicio) {
        cbd.connectar();
        llistaCines=cbd.consultaMultiple(condicio);
        cbd.desconnectar();
        modelcine=new cineModel(llistaCines);
        taula.setModel(modelcine);
    }
    
    public void cargaComboBox() {
        cbd.connectar();
        if(boxpob!=null && boxpob.getItemCount()>0) {
            boxpob.removeAllItems();
        }
        for(String pob: cbd.consultaPoblacions()) {
            boxpob.addItem(pob);
        }
        cbd.desconnectar();
    }
}
