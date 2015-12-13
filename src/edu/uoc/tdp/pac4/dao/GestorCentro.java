/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Centro;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ivan
 */
public class GestorCentro extends GestorDisco {

  //Tabla Centro
    private static final String CENTROTABLE = "centre";
    private static final String CENTROTABLE_ID = "centre_id";
    private static final String CENTROTABLE_NOMBRE = "nom_centre";
    private static final String CENTROTABLE_ADRESS = "adreca";
    private static final String CENTROTABLE_TOWN = "poblacio";
    private static final String CENTROTABLE_CP = "codi_postal";
    private static final String CENTROTABLE_UNI = "universitat_id";
    private static final String CENTROTABLE_PAIS = "pais";
    private static final String CENTROTABLE_MAIL = "email";
    private static final String CENTROTABLE_PHONE = "telefono";
    private static final String CENTROTABLE_URL = "url";
    private static final String CENTROTABLE_DATE = "data_alta";
    private static final String CENTROTABLE_BAJA = "data_baixa";
    


    //Tabla Grupo
    private static final String GRUPOTABLE = "grupo";
    private static final String GRUPOTABLE_ACTIVIDAD = "idactividad";

    private Connection conn = null;
    private final LanguageUtils txt = null;
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    //===========================================
    // Constructors
    //===========================================
    /**
     * Constructor de la clase.
     *
     * @param conn
     */
    public GestorCentro(Connection conn) {
        this.conn = conn;
    }

    //===========================================
    // Propiedades
    //===========================================
    /**
     * Devuelve la conexión a base de datos.
     *
     * @return
     */
    @Override
    public Connection getConnection() {
        return conn;
    }

    //===========================================
    // Métodos
    //===========================================

    //Obtener listado de centros
    public ArrayList<Centro> getCentros() throws SQLException, Exception {
        Centro centro;
        String sql;
        ArrayList<Centro> list = new ArrayList<Centro>();

        sql = "SELECT * FROM " + CENTROTABLE + " "
                + "ORDER BY " + CENTROTABLE_NOMBRE + " Asc";

        try {
            ResultSet rs = executeSql(sql);
            while (rs.next()) {
                centro = new Centro();
                readCentro(centro, rs);
                list.add(centro);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return list;

    }

    //Obtener listado de centros inactivos
    public ArrayList<Centro> getCentrosInactivos() throws SQLException, Exception {
        Centro centro;
        String sql;
        ArrayList<Centro> list = new ArrayList<Centro>();

        sql = "SELECT * FROM " + CENTROTABLE + " "
                + "WHERE data_baixa IS NOT NULL ORDER BY " + CENTROTABLE_NOMBRE + " Asc";

        try {
            ResultSet rs = executeSql(sql);
            while (rs.next()) {
                centro = new Centro();
                readCentro(centro, rs);
                list.add(centro);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return list;

    }
    
     public Centro get(int id) throws SQLException, Exception 
   {
      String sql;
      Centro cen = null;
      
      sql = "SELECT * FROM " + CENTROTABLE     + " " +
            "WHERE "         + CENTROTABLE_ID     + " = "  + id;

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         if (rs.next()) 
         {
             cen= new Centro();
                readCentro(cen, rs);
         }
      } 
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return cen;
   }
   
    
    //Preparar Centro
    private void readCentro(Centro centro, ResultSet rs) throws SQLException {
        
        centro.setId(rs.getInt(CENTROTABLE_ID));
        centro.setNom(rs.getString(CENTROTABLE_NOMBRE));
        centro.setAdreca(rs.getString(CENTROTABLE_ADRESS));
        centro.setCP(rs.getString(CENTROTABLE_CP));
        centro.setDataAlta(rs.getDate(CENTROTABLE_DATE));
        centro.setDataBaixa(rs.getDate(CENTROTABLE_BAJA));
        centro.setEmail(rs.getString(CENTROTABLE_MAIL));
        centro.setPais(rs.getInt(CENTROTABLE_PAIS));
        centro.setPoblacio(rs.getString(CENTROTABLE_TOWN));
        centro.setTelf(rs.getString(CENTROTABLE_PHONE));
        centro.setURL(rs.getString(CENTROTABLE_URL));
        centro.setUniversitat(rs.getInt(CENTROTABLE_UNI));
        
    }
    
    //Alta de Centro
    public void add(Centro centro) throws SQLException, Exception {
        String sql;
        Statement statement;
        ResultSet rs;

        try {
            sql = "INSERT INTO " + CENTROTABLE + " "
                    + "(" + CENTROTABLE_NOMBRE + ","
                    + " " + CENTROTABLE_ADRESS + ","
                    + " " + CENTROTABLE_TOWN+ ","
                    + " " + CENTROTABLE_CP + ","
                    + " " + CENTROTABLE_UNI + ","
                    + " " + CENTROTABLE_PAIS+ ","
                    + " " + CENTROTABLE_MAIL + ","
                    + " " + CENTROTABLE_PHONE + ","
                    + " " + CENTROTABLE_URL + ","
                    + " " + CENTROTABLE_DATE + ") "
                    + "VALUES "
                    + "('" + centro.getNom() + "', "
                    + " '" + centro.getAdreca() + "', "
                    + " '" + centro.getPoblacio() + "', "
                    + " '" + centro.getCP() + "', "
                     + " " + centro.getUniversitat() + ", "
                     + " " + centro.getPais() + ", "
                    + " '" + centro.getEmail() + "', "
                    + " '" + centro.getTelf() + "', "
                    + " '" + centro.getURL() + "', "
                    + " '" + df.format(centro.getDataAlta()) + "')";

            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    //Modificar Centro
    public void update(Centro centro) throws SQLException, Exception {
        String sql;
        Statement statement;

        try {

            sql = "UPDATE " + CENTROTABLE + " "
                    + "SET " + CENTROTABLE_NOMBRE + " = '" + centro.getNom() + "', "
                    + CENTROTABLE_ADRESS + " = '" + centro.getAdreca() + "', "
                    + CENTROTABLE_TOWN + " = '" + centro.getPoblacio() + "', "
                    + CENTROTABLE_CP  + " = '" + centro.getCP() + "', "
                    + CENTROTABLE_UNI + " = " + centro.getUniversitat() + ", "
                    + CENTROTABLE_PAIS + " = " + centro.getPais() + ", "
                    + CENTROTABLE_MAIL + " = '" + centro.getEmail() + "', "
                    + CENTROTABLE_PHONE + " = '" + centro.getTelf() + "', "
                    + CENTROTABLE_URL + " = '" + centro.getURL() + "', "
                    + CENTROTABLE_DATE + " = '" + df.format(centro.getDataAlta()) + "' "
                    + "WHERE " + CENTROTABLE_ID + " = " + centro.getId();

            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    //Eliminar Centro
    public void delete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;

        Date now = new Date();

        try {
            sql = "UPDATE " +CENTROTABLE + " "
                    + "SET " 
                    + CENTROTABLE_BAJA + " = '" + df.format(now) + "' "
                    + "WHERE " + CENTROTABLE_ID + " = " + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
