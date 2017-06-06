
package projectecinesexamen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class finestra extends JFrame implements ActionListener {
   
    private finestraCine panelcine;
    private finestraSala panelsala;
    private finestraPrograma panelprograma;
    private JLabel ltitol;
    private JMenuBar barramenu;
    private JMenu menufitxers;
    private JMenuItem itemcines;
    private JMenuItem itemsales;
    private JMenuItem itemprogrames;
    
    private Font gran=new Font("Arial",1,32);
    private Font normal=new Font("Arial",1,20);
    
        
    @Override
    public void actionPerformed(ActionEvent event)
    {
          String opcio=event.getActionCommand();
          switch(opcio) {
              case "Cines":
                  this.remove(panelsala);
                  this.remove(panelprograma);
                  this.add(panelcine,BorderLayout.CENTER);
                  break;
              case "Sales":
                  this.remove(panelcine);
                  this.remove(panelprograma);
                  this.add(panelsala,BorderLayout.CENTER);
                  break;
              case "Programes":
                  this.remove(panelcine);
                  this.remove(panelsala);
                  this.add(panelprograma,BorderLayout.CENTER);
          }
           this.validate();
           this.repaint();
            
    }
   
    public finestra() {
        this.setTitle("GESTIÓ CINES");
        this.initGrafics();
    }
    
    public void initGrafics() {
        this.setLayout(new BorderLayout());
        this.setLocation(150,150);
        ltitol=new JLabel("Cadena EL PUNT");
        ltitol.setFont(gran);
        this.add(ltitol,BorderLayout.NORTH);
        panelcine=new finestraCine();
        panelsala=new finestraSala();
        panelprograma=new finestraPrograma();
        this.add(panelcine,BorderLayout.CENTER);
        barramenu=new JMenuBar();
        menufitxers=new JMenu("Fitxers");menufitxers.setFont(normal);
        itemcines=new JMenuItem("Cines");itemcines.setFont(normal);
        itemsales=new JMenuItem("Sales");itemsales.setFont(normal);
        itemprogrames=new JMenuItem("Programes");itemprogrames.setFont(normal);
        itemcines.addActionListener(this);
        itemsales.addActionListener(this);
        itemprogrames.addActionListener(this);
        menufitxers.add(itemcines);
        menufitxers.add(itemsales);
        menufitxers.add(itemprogrames);
        barramenu.add(menufitxers);
        this.setJMenuBar(barramenu);
        this.setSize(1100,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
