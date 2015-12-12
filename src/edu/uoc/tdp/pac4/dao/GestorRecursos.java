package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Recurso;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GestorRecursos extends GestorDisco
{
    private static final String RECURSOTABLE           = "recurs";
    private static final String RECURSOTABLE_ID        = "recurs_id";
    private static final String RECURSOTABLE_AULA       = "aula_id";
    private static final String RECURSOTABLE_CODIGO     = "codi_recurs";
    private static final String RECURSOTABLE_NOMBRE        = "nom";
    private static final String RECURSOTABLE_DESCRIPCION    = "descripcio";
    private static final String RECURSOTABLE_ALTA       = "data_alta";
    private static final String RECURSOTABLE_BAJA  = "data_baixa";
    private Connection conn = null;
    private LanguageUtils txt = null;
    private SimpleDateFormat df = new SimpleDateFormat("yyy/MM/dd");

//Constructor
   public GestorRecursos(Connection conn) 
   {

      this.conn = conn;
   }

//Properties
   public Connection getConnection() 
   {
      return conn;
   }
   
    private Recurso buildRecurso(ResultSet rs) throws SQLException, Exception{
        Recurso rec = new Recurso();
        
        rec.setIdRecurso            (rs.getInt     (RECURSOTABLE_ID ));
        rec.setAulaRecurso          (rs.getInt     (RECURSOTABLE_AULA ));
        rec.setCodigoRecurso        (rs.getString  (RECURSOTABLE_CODIGO ));
        rec.setNombreRecurso        (rs.getString  (RECURSOTABLE_NOMBRE));
        rec.setDescripcionRecurso   (rs.getString  (RECURSOTABLE_DESCRIPCION));
        rec.setFechaAltaRecurso     (rs.getDate    (RECURSOTABLE_ALTA));
        rec.setFechaBajaRecurso     (rs.getDate    (RECURSOTABLE_BAJA));

        return rec;
    }
    
    public Recurso get(int id) throws SQLException, Exception 
   {
      String sql;
      Recurso rec = null;
      
      sql = "SELECT * FROM " + RECURSOTABLE     + " " +
            "WHERE "         + RECURSOTABLE_ID     + " = "  + id;

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         if (rs.next()) 
         {
             rec = buildRecurso(rs);
         }
      } 
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return rec;
   }
   
//Lista de recursos
   public ArrayList<Recurso> getRecursos() throws SQLException, Exception 
   {
      String sql;
      Recurso rec;
      ArrayList<Recurso> recursos = new ArrayList<Recurso>();
      
      sql = "SELECT * FROM " + RECURSOTABLE       + " "    + 
            "WHERE "+ RECURSOTABLE_BAJA + " IS NULL ORDER BY " + RECURSOTABLE_NOMBRE;

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         while (rs.next()) 
         {
            rec = buildRecurso(rs);
            recursos.add(rec);
         }
      } 
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return recursos;
   }
   
   //Recursos eliminados (existe fecha de baja)
   public ArrayList<Recurso> getRecursosInactivos() throws SQLException, Exception 
   {
      String sql;
      Recurso rec;
      ArrayList<Recurso> recursos = new ArrayList<Recurso>();
      
      sql = "SELECT * FROM " + RECURSOTABLE        + " "    + 
            "WHERE "  + RECURSOTABLE_BAJA + " IS NOT NULL " +
            "ORDER BY "      + RECURSOTABLE_NOMBRE;

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         while (rs.next()) 
         {
            rec = buildRecurso(rs);
            recursos.add(rec);
         }
      } 
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return recursos;
   }
   
//Alta de Recurso
    public void alta(Recurso rec) throws SQLException, Exception {
        String sql;
        Statement statement;
        ResultSet rs;
        
       // aula.setActiva(true);
      
        try {
            sql = "INSERT INTO " + RECURSOTABLE                             + " "  +
                  "("            + RECURSOTABLE_NOMBRE                       + ","  + 
                  " "            + RECURSOTABLE_CODIGO                       + ","  +
                  " "            + RECURSOTABLE_AULA                    + ","  +
                  " "            + RECURSOTABLE_DESCRIPCION                   + ","  +
                  " "            + RECURSOTABLE_ALTA                 + ")"  +                 

                  "VALUES "      +
                  "('"           + rec.getNombreRecurso()                     + "', " +
                  " '"           + rec.getCodigoRecurso()                     + "', "  +
                  " "            + rec.getAulaRecurso()                      + ", "  +
                  " '"           + rec.getDescripcionRecurso()                   + "', "  +
                  " '"           + df.format(rec.getFechaAlta())              + "') " ;   

            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
 //Actualizar Recurso
    public void actualizar(Recurso rec) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        try {
            sql = "UPDATE " + RECURSOTABLE             + " "        +
                  "SET "    + RECURSOTABLE_NOMBRE        + " = '"     + rec.getNombreRecurso()                      + "', " +
                              RECURSOTABLE_AULA    + " = "      + rec.getAulaRecurso()+ ", "  +
                              RECURSOTABLE_CODIGO       + " = '"      + rec.getCodigoRecurso()                   + "', "  +
                              RECURSOTABLE_DESCRIPCION      + " = '"      + rec.getDescripcionRecurso()                  + "' "  +
                  "WHERE "  + RECURSOTABLE_ID           + " = "      + rec.getIdRecurso();
            
            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
   
//Elimina Recurso
    public void eliminar(int id) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        Date now = new Date();
        
        try {
            sql = "UPDATE " + RECURSOTABLE + " "        +
                  "SET "   +  RECURSOTABLE_BAJA + " = '"     + df.format(now) + "' "  +
                  "WHERE "  + RECURSOTABLE_ID          + " = "      + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }

    public List<Recurso> ConsultarTipoRecurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
