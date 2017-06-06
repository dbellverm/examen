/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectecinesexamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Fidel_pc
 */
public class BD {
    Connection conexio=null;
    Statement instruccions=null;
    ResultSet resultat=null; 
    
    public boolean connectar() {
    try {
	conexio=DriverManager.getConnection("jdbc:mysql://localhost:3306/cinexamen",
				"root","ercros89");
	instruccions=conexio.createStatement();
        return true;
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	return false;
	}   
   }
     
   public boolean desconnectar() {
      try {
	    conexio.close();
            return true;
	  } catch (SQLException e) {
	    // TODO Auto-generated catch block
	    return false;
          }		
   }    
}
