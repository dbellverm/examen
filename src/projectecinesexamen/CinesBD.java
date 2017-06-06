/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectecinesexamen;

import java.sql.*;
import java.util.ArrayList;
//import javax.swing.JOptionPane;
/**
 *
 * @author fidel1rdaw
 */
public class CinesBD extends BD {
    
   public int alta(Cines c) {
      String cadenaSQL="INSERT INTO cine VALUES(NULL,'"+c.getNomCine()+"','"+c.getPobCine()+"')";
      try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
	}
   }
   
   public int baixa(int idCine) {
       
     String cadenaSQL="DELETE FROM cine WHERE idCine="+idCine;
     try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
     }
   }
   
   public int modificacio(Cines u) {
    String cadenaSQL="UPDATE cine SET nomCine='"+u.getNomCine()+
		  "', pobCine='"+u.getPobCine()
  		+ "' WHERE idCine="+u.getIdCine();
    //JOptionPane.showMessageDialog(null,cadenaSQL);
    try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
	}   
   }
   
   public Cines consultaSimple(int idCine) {
   Cines u=null;
   String nomCine,pobCine;
   try{
    resultat=instruccions.executeQuery("SELECT * FROM cine"
            + "WHERE idCine = "+idCine);
    while(resultat.next()) {
	  idCine=resultat.getInt(1);
	  nomCine=resultat.getString(2);
	  pobCine=resultat.getString(3);
	  u=new Cines();
          u.setIdCine(idCine);
          u.setNomCine(nomCine);
          u.setPobCine(pobCine);
          }
       return u;
      }
      catch(SQLException e) {
	return null;
      }     
   }
   
   public ArrayList<Cines> consultaTots() {
   int idCine;
   Cines u=null;
   ArrayList<Cines> llistaCines=new ArrayList<Cines>();
   String nomCine,pobCine;
       try{
       resultat=instruccions.executeQuery("SELECT * FROM cine");
       while(resultat.next()) {
	  idCine=resultat.getInt(1);
	  nomCine=resultat.getString(2);
	  pobCine=resultat.getString(3);
	  u=new Cines();
          u.setIdCine(idCine);
          u.setNomCine(nomCine);
          u.setPobCine(pobCine);
          llistaCines.add(u);
          }
       return llistaCines;
      }
      catch(SQLException e) {
	return null;
      } 
   }
   
   
   public ArrayList<Cines> consultaMultiple(String condicio) {
   int idCine;
   Cines u=null;
   ArrayList<Cines> llistaCines=new ArrayList<Cines>();
   String nomCine,pobCine;
       try{
       resultat=instruccions.executeQuery("SELECT * FROM cine "
       + "WHERE "+condicio);
       while(resultat.next()) {
	  idCine=resultat.getInt(1);
	  nomCine=resultat.getString(2);
	  pobCine=resultat.getString(3);
	  u=new Cines();
          u.setIdCine(idCine);
          u.setNomCine(nomCine);
          u.setPobCine(pobCine);
          llistaCines.add(u);
          }
       return llistaCines;
      }
      catch(SQLException e) {
	return null;
      }    
       
   }
   public ArrayList<String> consultaPoblacions() {
       ArrayList<String> llistapoblacions=new ArrayList<>();
       String pobCine="";
       try{
       resultat=instruccions.executeQuery("SELECT DISTINCT pobCine FROM cine ");
       while(resultat.next()) {
	  pobCine=resultat.getString(1);
          llistapoblacions.add(pobCine);
       }
       return llistapoblacions;
      }
      catch(SQLException e) {
	return null;
      }    
   }
}
