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
public class ProgramasBD extends BD {
     
   public int alta(Programas p) {
      String cadenaSQL="INSERT INTO programa VALUES(NULL,"+p.getIdCine()+","+p.getIdSala()+",'"+p.getNompelicula()+"','"+
              p.getHinici()+"',"+p.getDuracio()+")";
      JOptionPane.showMessageDialog(null,cadenaSQL);
      try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
	}
   }
   
   public int baixa(int idCine, int idSala, String hinici) {
       
     String cadenaSQL="DELETE FROM programa WHERE idCine="+idCine+" AND idSala="+idSala+" AND horaInici='"
             +hinici+"'";
     try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
     }
   }
   
   public int modificacio(Programas p) {
    String cadenaSQL="UPDATE programa SET nomPelicula='"+p.getNompelicula()+
		  "', duracio="+p.getDuracio()
  		+ " WHERE idCine="+p.getIdCine()+" AND idSala="
            +p.getIdSala()+" AND horaInici='"+p.getHinici()+"'";
    JOptionPane.showMessageDialog(null,cadenaSQL);
    try {
	int files=instruccions.executeUpdate(cadenaSQL);
	return files;
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	return -1;
	}   
   }
   
   public Programas consultaSimple(int sessio) {
   Programas p=null;
   int idSessio,idCine,idSala,duracio;
   String nompelicula,hinici;
   try{
    resultat=instruccions.executeQuery("SELECT * FROM programa"
            + "WHERE idSessio = "+sessio);
    while(resultat.next()) {
          idSessio=resultat.getInt(1);
	  idCine=resultat.getInt(2);
	  idSala=resultat.getInt(3);
	  nompelicula=resultat.getString(4);
          hinici=resultat.getString(5);
          duracio=resultat.getInt(6);
	  p=new Programas();
          p.setIdSessio(idSessio);
          p.setIdCine(idCine);
          p.setIdSala(idSala);
          p.setNompelicula(nompelicula);
          p.setHinici(hinici);
          p.setDuracio(duracio);
   
          }
       return p;
      }
      catch(SQLException e) {
	return null;
      }     
   }
   
   public ArrayList<Programas> consultaTots() {
   int idSessio,idCine,idSala,duracio;
   String nompelicula,hinici;
   Programas p=null;
   ArrayList<Programas> llistaProgramas=new ArrayList<>();
    try{
       resultat=instruccions.executeQuery("SELECT * FROM programa");
       while(resultat.next()) {
	  idSessio=resultat.getInt(1);
	  idCine=resultat.getInt(2);
	  idSala=resultat.getInt(3);
	  nompelicula=resultat.getString(4);
          hinici=resultat.getString(5);
          duracio=resultat.getInt(6);
	  p=new Programas();
          p.setIdSessio(idSessio);
          p.setIdCine(idCine);
          p.setIdSala(idSala);
          p.setNompelicula(nompelicula);
          p.setHinici(hinici);
          p.setDuracio(duracio);
          llistaProgramas.add(p);
          }
       return llistaProgramas;
      }
      catch(SQLException e) {
	return null;
      } 
   }
   
   
   public ArrayList<Programas> consultaMultiple(String condicio) {
   int idSessio,idCine,idSala,duracio;
   String nompelicula,hinici;
  Programas p=null;
   ArrayList<Programas> llistaProgramas=new ArrayList<>();
   try{
       resultat=instruccions.executeQuery("SELECT * FROM programa "
       + "WHERE "+condicio);
       while(resultat.next()) {
	  idSessio=resultat.getInt(1);
	  idCine=resultat.getInt(2);
	  idSala=resultat.getInt(3);
	  nompelicula=resultat.getString(4);
          hinici=resultat.getString(5);
          duracio=resultat.getInt(6);
	  p=new Programas();
          p.setIdSessio(idSessio);
          p.setIdCine(idCine);
          p.setIdSala(idSala);
          p.setNompelicula(nompelicula);
          p.setHinici(hinici);
          p.setDuracio(duracio);
          llistaProgramas.add(p);
          }
       return llistaProgramas;
      }
      catch(SQLException e) {
	return null;
      }    
       
   }
   
}
