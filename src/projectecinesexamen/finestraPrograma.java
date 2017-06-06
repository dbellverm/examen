
package projectecinesexamen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.table.TableColumnModel;

public class finestraPrograma extends JPanel {
    private JButton botoalta,botobaixa,botomodificar;
    private JLabel lcine,lsala,lpelicula,lhora,lduracio,ltitol;
    private JTextField tpelicula,thora,tduracio;
    private JComboBox boxcine, boxsala;
    private JPanel panel1,panel2,panel3,panel4;
    private Font gran=new Font("Arial",1,32);
    private Font normal=new Font("Arial",1,20);
    
    private Cines c=null;
    private CinesBD cbd=null;
    private Salas s=null;
    private SalasBD sbd=null;
    private Programas p=null;
    private ProgramasBD pbd=null;
    
    private int files=0;
    
    private ArrayList<Cines> llistaCines=new ArrayList<Cines>();
    private ArrayList<Salas> llistaSalas=new ArrayList<Salas>();
    private ArrayList<Programas> llistaProgramas=new ArrayList<Programas>();
    
    private JScrollPane panelscroll;
    private JTable taula;
    private programaModel modelprograma;
    
    class EscoltarBoto implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getActionCommand().equals("Alta")) {
                    pbd.connectar();
                    p=new Programas();
                    p.setIdCine((Integer)boxcine.getItemAt(boxcine.getSelectedIndex()));
                    p.setIdSala((Integer)boxsala.getItemAt(boxsala.getSelectedIndex()));
                    p.setNompelicula(tpelicula.getText());
                    p.setHinici(thora.getText());
                    p.setDuracio(Integer.parseInt(tduracio.getText()));
                    files = pbd.alta(p);
                    if(files==1) {
                        JOptionPane.showMessageDialog(null,"Alta realitzada");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No s'ha pogut fer l'alta");
                    }
                    pbd.desconnectar();
                    recargaDatos();
            }
            else if(event.getActionCommand().equals("Baixa")) {
                    pbd.connectar();
                    int idCine=(int)boxcine.getItemAt(boxcine.getSelectedIndex());
                    int idSala=(int)boxsala.getItemAt(boxsala.getSelectedIndex());
                    String hinici=thora.getText();
                    files=pbd.baixa(idCine,idSala,hinici);
                     if(files==1) {
                        JOptionPane.showMessageDialog(null,"Baixa realitzada");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No s'ha pogut fer la baixa");
                    }
                    pbd.desconnectar();
                    recargaDatos();
            }
             else if(event.getActionCommand().equals("Modificar")) {
                    pbd.connectar();
                    p=new Programas();
                    p.setIdCine((Integer)boxcine.getItemAt(boxcine.getSelectedIndex()));
                    p.setIdSala((Integer)boxsala.getItemAt(boxsala.getSelectedIndex()));
                    p.setNompelicula(tpelicula.getText());
                    p.setHinici(thora.getText());
                    p.setDuracio(Integer.parseInt(tduracio.getText()));
                    pbd.modificacio(p);
                    pbd.desconnectar();
                    recargaDatos();
            }
             
        }
    }
    
     class EscoltarCine implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int idCine=(Integer)boxcine.getItemAt(boxcine.getSelectedIndex());
            cargaSalas(idCine);
        }
    }
    public finestraPrograma() {
        this.initDades();
        this.initGrafics();
    }
    public void initDades() {
        cbd=new CinesBD();
        sbd=new SalasBD();
        pbd=new ProgramasBD();
        cargarDatos();
    }
    public void initGrafics() {
        this.setLayout(new BorderLayout());
        ltitol=new JLabel("Programació");
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
        panel1.setLayout(new GridLayout(10,1));
        lcine=new JLabel("Codi Cine......: ");lcine.setFont(normal);
        lsala=new JLabel("Codi Sala......: ");lsala.setFont(normal);
        lpelicula=new JLabel("Pel·lícula.....: ");lpelicula.setFont(normal);
        lhora=new JLabel("Hora inici.....: ");lhora.setFont(normal);
        lduracio=new JLabel("Duració........: ");lduracio.setFont(normal);
        
        panel1.add(lcine);panel1.add(new JLabel(""));
        panel1.add(lsala);panel1.add(new JLabel(""));
        panel1.add(lpelicula);panel1.add(new JLabel(""));
        panel1.add(lhora);panel1.add(new JLabel(""));
        panel1.add(lduracio);panel1.add(new JLabel(""));
       
    }
     public void initPanel2() {
        panel2=new JPanel();
        panel2.setLayout(new GridLayout(10,1));
        boxcine=new JComboBox();
        boxsala=new JComboBox();
        cargaCines();
        boxcine.addItemListener(new EscoltarCine());
        tpelicula=new JTextField(20);tpelicula.setFont(normal);
        thora=new JTextField(10);thora.setFont(normal);
        tduracio=new JTextField(20);tduracio.setFont(normal);
      
        panel2.add(boxcine);panel2.add(new JLabel(""));
        panel2.add(boxsala);panel2.add(new JLabel(""));
        panel2.add(tpelicula);panel2.add(new JLabel(""));
        panel2.add(thora);panel2.add(new JLabel(""));
        panel2.add(tduracio);panel2.add(new JLabel(""));
       
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
        pbd.connectar();
        llistaProgramas=pbd.consultaTots();
        pbd.desconnectar();
        modelprograma=new programaModel(llistaProgramas);
        taula=new JTable(modelprograma);
        taula.setFont(normal);
        taula.setRowHeight(25);
        taula.getTableHeader().setForeground(Color.red);
        taula.getTableHeader().setFont(normal);
        panelscroll = new JScrollPane(taula,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelscroll.setPreferredSize(new Dimension(650,300));
        TableColumnModel modelcolumnes=taula.getColumnModel();
        modelcolumnes.getColumn(0).setPreferredWidth(70);
        modelcolumnes.getColumn(1).setPreferredWidth(70);
        modelcolumnes.getColumn(2).setPreferredWidth(280);
    }
    
    public void recargaDatos() {
        pbd.connectar();
        llistaProgramas=pbd.consultaTots();
        sbd.desconnectar();
        modelprograma=new programaModel(llistaProgramas);
        taula.setModel(modelprograma);
        TableColumnModel modelcolumnes=taula.getColumnModel();
        modelcolumnes.getColumn(0).setPreferredWidth(70);
        modelcolumnes.getColumn(1).setPreferredWidth(70);
        modelcolumnes.getColumn(2).setPreferredWidth(280);
        
    }
    
    public void cargaCines() {
        cbd.connectar();
        
        for(Cines c: cbd.consultaTots()) {
            boxcine.addItem(c.getIdCine());
        }
        cbd.desconnectar();
        cargaSalas(1);
    }
    
    public void cargaSalas(int idCine) {
        String condicio=" idCine = "+idCine;
        boxsala.removeAllItems();
        sbd.connectar();
        
        for(Salas sala: sbd.consultaMultiple(condicio)) {
            boxsala.addItem(sala.getIdSala());
        }
        sbd.desconnectar();
    }
}
