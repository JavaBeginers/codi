package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Aula;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class GestorAulas 
{
    private static final String AULATABLE              = "aula";
    private static final String AULATABLE_ID           = "aula_id";
    private static final String AULATABLE_CENTER       = "centre_id";
    private static final String AULATABLE_CODE         = "codi_aula";
    private static final String AULATABLE_NAME         = "nom";
    private static final String AULATABLE_CAPACITY     = "capacitat";
    private static final String AULATABLE_LOCATION     = "ubicacio";
    private static final String AULATABLE_DATE         = "data_alta";
    private static final String AULATABLE_CANCELLDATE  = "data_baixa";
    
   private Connection conn     = null;
   
   private SimpleDateFormat df = new SimpleDateFormat("yyy/MM/dd");

//Constructor
   public GestorAulas(Connection conn) 
   {
      this.conn = conn;
   }

//Properties
   public Connection getConnection() 
   {
      return conn;
   }
   
//Métodos
   
    private Aula buildAula(ResultSet rs) throws SQLException, Exception{
        Aula aula = new Aula();
        
        aula.setId               (rs.getInt     (AULATABLE_ID));
        aula.setCentro           (rs.getInt     (AULATABLE_CENTER));
        aula.setCodigo           (rs.getString  (AULATABLE_CODE ));
        aula.setNombre           (rs.getString  (AULATABLE_NAME));
        aula.setCapacidad        (rs.getInt     (AULATABLE_CAPACITY));
        aula.setUbicacion        (rs.getString  (AULATABLE_LOCATION));
        aula.setFechaAlta        (rs.getDate    (AULATABLE_DATE));
        aula.setFechaBaja        (rs.getDate    (AULATABLE_CANCELLDATE));
 
        
        
        return aula;
    }
    
//Obtener aula
   public Aula get(int id) throws SQLException, Exception 
   {
      String sql;
      Aula aula = null;
      
      sql = "SELECT * FROM " + AULATABLE        + " " +
            "WHERE "         + AULATABLE_ID     + " = "  + id;

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         if (rs.next()) 
         {
             aula = buildAula(rs);
         }
      } 
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return aula;
   }
   
//Lista de aulas
   public ArrayList<Aula> getAulas() throws SQLException, Exception 
   {
      String sql;
      Aula aula;
      ArrayList<Aula> aulas = new ArrayList<Aula>();
      
      sql = "SELECT * FROM " + AULATABLE        + " "    + 
            "WHERE "+ AULATABLE_CANCELLDATE + " IS NULL ORDER BY " + AULATABLE_NAME;

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         while (rs.next()) 
         {
            aula = buildAula(rs);
            aulas.add(aula);
         }
      } 
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return aulas;
   }
   
//Aulas de un centro
   public ArrayList<Aula> getAulasByCentro(int idCentro) {
      String sql;
      Aula aula;
      ArrayList<Aula> aulas = new ArrayList<Aula>();
      
      sql = "SELECT * FROM " + AULATABLE        + " "    + 
            "WHERE "+ AULATABLE_CANCELLDATE + " IS NULL and centre_id = " + idCentro + " ORDER BY " + AULATABLE_NAME;

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         while (rs.next()) 
         {
            aula = buildAula(rs);
            aulas.add(aula);
         }
      } 
      catch (SQLException ex) {
          
      } 
      catch (Exception ex)    {
          
      }
      
      return aulas;   
   }
           
//Aulas eliminadas (existe fecha de baja)
   public ArrayList<Aula> getAulasInactivas() throws SQLException, Exception 
   {
      String sql;
      Aula aula;
      ArrayList<Aula> aulas = new ArrayList<Aula>();
      
      sql = "SELECT * FROM " + AULATABLE        + " "    + 
            "WHERE "  + AULATABLE_CANCELLDATE + " IS NOT NULL " +
            "ORDER BY "      + AULATABLE_NAME;

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         while (rs.next()) 
         {
            aula = buildAula(rs);
            aulas.add(aula);
         }
      } 
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return aulas;
   }
   
//Alta de Aula
    public void alta(Aula aula) throws SQLException, Exception {
        String sql;
        Statement statement;
        ResultSet rs;
        
       // aula.setActiva(true);
      
        try {
            sql = "INSERT INTO " + AULATABLE                             + " "  +
                  "("            + AULATABLE_NAME                        + ","  + 
                  " "            + AULATABLE_CODE                        + ","  +
                  " "            + AULATABLE_CENTER                      + ","  +
                  " "            + AULATABLE_CAPACITY                    + ","  +
                  " "            + AULATABLE_LOCATION                    + ","  +
                  " "            + AULATABLE_DATE                        + ")"  +

                  "VALUES "      +
                  "('"           + aula.getNombre()                      + "', " +
                  " '"            + aula.getCodigo()                      + "', "  +
                  " "            + aula.getCentro()                      + ", "  +
                  " "            + aula.getCapacidad()                   + ", "  +
                  " '"           + aula.getUbicacion()                + "', " +
                  " '"           + df.format(aula.getFechaAlta())        + "') " ;

            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
 //Actualizar Aula
    public void actualizar(Aula aula) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        try {
            sql = "UPDATE " + AULATABLE              + " "        +
                  "SET "    + AULATABLE_NAME         + " = '"     + aula.getNombre()                      + "', " +
                              AULATABLE_CAPACITY     + " = "      + aula.getCapacidad()                   + ", "  +
                              AULATABLE_CODE         + " = '"      + aula.getCodigo()                   + "', "  +
                              AULATABLE_CENTER       + " = "      + aula.getCentro()                   + ", "  +
                              AULATABLE_LOCATION     + " = '"     + aula.getUbicacion()                + "' "  +
                  "WHERE "  + AULATABLE_ID           + " = "      + aula.getId();
            
            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
   
//Elimina Aula
    public void eliminar(int id) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        Date now = new Date();
        
        try {
            sql = "UPDATE " + AULATABLE + " "        +
                  "SET "   +  AULATABLE_CANCELLDATE + " = '"     + df.format(now) + "' "  +
                  "WHERE "  + AULATABLE_ID           + " = "      + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
   
}
