/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectecinesexamen;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
/**
 *
 * @author fidel1rdaw
 */
public class SalasBD extends BD {
     
   public int alta(Salas s) {
      String cadenaSQL="INSERT INTO sala VALUES("+s.getIdCine()+","+s.getIdSala()+","+s.getButaques()+")";
      JOptionPane.showMessageDialog(null,cadenaSQL);
      try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
	}
   }
   
   public int baixa(int idCine, int idSala) {
       
     String cadenaSQL="DELETE FROM sala WHERE idCine="+idCine+" AND idSala="+idSala;
     try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
     }
   }
   
   public int modificacio(Salas s) {
    String cadenaSQL="UPDATE sala SET idSala="+s.getIdSala()+
		  ", butaques="+s.getButaques()
  		+ " WHERE idCine="+s.getIdCine()+" AND idSala="+s.getIdSala();
    JOptionPane.showMessageDialog(null,cadenaSQL);
    try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
	}   
   }
   
   public Salas consultaSimple(int idCine, int idSala) {
   Salas s=null;
   int butaques;
   try{
    resultat=instruccions.executeQuery("SELECT * FROM sala"
            + "WHERE idCine = "+idCine+" AND isSala="+idSala);
    while(resultat.next()) {
	  idCine=resultat.getInt(1);
	  idSala=resultat.getInt(2);
	  butaques=resultat.getInt(3);
	  s=new Salas();
          s.setIdCine(idCine);
          s.setIdSala(idSala);
          s.setButaques(butaques);
          }
       return s;
      }
      catch(SQLException e) {
	return null;
      }     
   }
   
   public ArrayList<Salas> consultaTots() {
   int idCine, idSala, butaques;
   Salas s=null;
   ArrayList<Salas> llistaSalas=new ArrayList<>();
    try{
       resultat=instruccions.executeQuery("SELECT * FROM sala");
       while(resultat.next()) {
	  idCine=resultat.getInt(1);
	  idSala=resultat.getInt(2);
	  butaques=resultat.getInt(3);
	  s=new Salas();
          s.setIdCine(idCine);
          s.setIdSala(idSala);
          s.setButaques(butaques);
          llistaSalas.add(s);
          }
       return llistaSalas;
      }
      catch(SQLException e) {
	return null;
      } 
   }
   
   
   public ArrayList<Salas> consultaMultiple(String condicio) {
   int idCine, idSala, butaques;
   Salas s=null;
   ArrayList<Salas> llistaSalas=new ArrayList<>();
   try{
       String cadenaSQL="SELECT * FROM sala WHERE "+condicio;
       resultat=instruccions.executeQuery(cadenaSQL);
       while(resultat.next()) {
	  idCine=resultat.getInt(1);
	  idSala=resultat.getInt(2);
	  butaques=resultat.getInt(3);
	  s=new Salas();
          s.setIdCine(idCine);
          s.setIdSala(idSala);
          s.setButaques(butaques);
          llistaSalas.add(s);
          }
       return llistaSalas;
      }
      catch(SQLException e) {
	return null;
      }    
       
   }
   
}
