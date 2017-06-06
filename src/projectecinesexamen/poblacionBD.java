/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectecinesexamen;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dani_
 */
public class poblacionBD extends BD{
    
     public int alta(poblacion p) {
      String cadenaSQL="INSERT INTO sala VALUES("+p.getCpostal()+","+p.getNompob()+")";
      JOptionPane.showMessageDialog(null,cadenaSQL);
      try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
	}
   }
   
   public int baixa(int cpostal) {
       
     String cadenaSQL="DELETE FROM poblacion WHERE cpostal="+cpostal;
     try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
     }
   }
   
   
   
    public ArrayList<poblacion> consultaTots() {
   int cpostal;
   String nompob;
  
   poblacion p=null;
   ArrayList<poblacion> llistapoblacion=new ArrayList<>();
    try{
       resultat=instruccions.executeQuery("SELECT * FROM poblacion");
       while(resultat.next()) {
          cpostal=resultat.getInt(1);
	  nompob=resultat.getString(2);
	  
          
	  p=new poblacion();
          p.setCpostal(cpostal);
          p.setNompob(nompob);
         
          llistapoblacion.add(p);
          }
       return llistapoblacion;
      }
      catch(SQLException e) {
	return null;
      } 
   }
    
}
