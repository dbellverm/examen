/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectecinesexamen;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dani_
 */
public class reservaBD extends BD {
    
     public int alta(reserva r) {
      String cadenaSQL="INSERT INTO sala VALUES("+r.getIdCine()+","+r.getIdSala()+","+r.getHorainici()+","+r.getEntrades()+")";
      JOptionPane.showMessageDialog(null,cadenaSQL);
      try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
	}
   }
   
   public int baixa(int idReserva) {
       
     String cadenaSQL="DELETE FROM reserva WHERE idReserva="+idReserva;
     try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
     }
   }
   
   
   
    public ArrayList<reserva> consultaTots() {
   int idReserva,idCine, idSala,entrades;
   Time horainici;
   reserva r=null;
   ArrayList<reserva> llistareserva=new ArrayList<>();
    try{
       resultat=instruccions.executeQuery("SELECT * FROM reserva");
       while(resultat.next()) {
          idReserva=resultat.getInt(1);
	  idCine=resultat.getInt(2);
	  idSala=resultat.getInt(3);
	  horainici=resultat.getTime(4);
          entrades=resultat.getInt(5);
          
	  r=new reserva();
          r.setIdReserva(idReserva);
          r.setIdCine(idCine);
          r.setIdSala(idSala);
          r.setHorainici(horainici);
          r.setEntrades(entrades);
          llistareserva.add(r);
          }
       return llistareserva;
      }
      catch(SQLException e) {
	return null;
      } 
   }
    
    public int numentradas(int idCine,int idSala, Time horainici){
        int entradas=0;
         try {
             resultat=instruccions.executeQuery("SELECT SUM(entrades) FROM reserva\n" +
                     "WHERE idCine=xxx AND idSala=xxx AND horaInici='xx:xx'");
             entradas=resultat.getInt(1);
         } catch (SQLException ex) {
             Logger.getLogger(reservaBD.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         

         
         return entradas;       
    }
    
}
