/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectecinesexamen;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

class cineModel extends AbstractTableModel {
    
    ArrayList<Cines> llistacines=new ArrayList<Cines>();
    
    cineModel(ArrayList<Cines> l) {
           this.llistacines=l;
    }
     
    public int getRowCount() {
        return llistacines.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    public String getColumnName(int columna) {
        String titol="";
        switch(columna) {
            case 0: titol="Codi"; break;
            case 1: titol="Nom"; break;
            case 2: titol="Població"; break;
        }
        return titol;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cines c=llistacines.get(rowIndex);
        String torna="";
        switch(columnIndex) {
            case 0:torna=""+c.getIdCine(); break;
            case 1:torna=c.getNomCine(); break;
            case 2:torna=c.getPobCine(); break;
        }
        return torna;
    }
    
}

class salaModel extends AbstractTableModel {
    
    ArrayList<Salas> llistasalas=new ArrayList<Salas>();
    
    salaModel(ArrayList<Salas> l) {
           this.llistasalas=l;
    }
     
    public int getRowCount() {
        return llistasalas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    public String getColumnName(int columna) {
        String titol="";
        switch(columna) {
            case 0: titol="Cine"; break;
            case 1: titol="Sala"; break;
            case 2: titol="Butaques"; break;
        }
        return titol;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Salas c=llistasalas.get(rowIndex);
        String torna="";
        switch(columnIndex) {
            case 0:torna=""+c.getIdCine(); break;
            case 1:torna=""+c.getIdSala(); break;
            case 2:torna=""+c.getButaques(); break;
        }
        return torna;
    }
    
}

class programaModel extends AbstractTableModel {
    
    ArrayList<Programas> llistaProgrames=new ArrayList<Programas>();
    
    programaModel(ArrayList<Programas> l) {
           this.llistaProgrames=l;
    }
     
    public int getRowCount() {
        return llistaProgrames.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    public String getColumnName(int columna) {
        String titol="";
        switch(columna) {
            case 0: titol="Cine"; break;
            case 1: titol="Sala"; break;
            case 2: titol="Pel·lícula"; break;
            case 3: titol="Inici"; break;
            case 4: titol="Duració"; break;
        }
        return titol;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Programas c=llistaProgrames.get(rowIndex);
        String torna="";
        switch(columnIndex) {
            case 0:torna=""+c.getIdCine(); break;
            case 1:torna=""+c.getIdSala(); break;
            case 2:torna=c.getNompelicula(); break;
            case 3:torna=c.getHinici(); break;
            case 4:torna=""+c.getDuracio(); break;
        }
        return torna;
    }
    
}