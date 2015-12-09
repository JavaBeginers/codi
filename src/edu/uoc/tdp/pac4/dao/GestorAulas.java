package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Aula;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Implementa el gestor de aulas.
 * 
 * @author eSupport Netbeans
 */
public class GestorAulas 
{
    /*
     * Definimos los campos de la tabla aula
     */
    private static final String AULATABLE              = "aula";
    private static final String AULATABLE_ID           = "id_aula";
    private static final String AULATABLE_NAME         = "nombre";
    private static final String AULATABLE_CAPACITY     = "capacidad";
    private static final String AULATABLE_DESCRIPTION  = "descripcion_aula";
    private static final String AULATABLE_LOCATION     = "localizacion";
    private static final String AULATABLE_DATE         = "fechaalta";
    private static final String AULATABLE_ACTIVE       = "activa";
    private static final String AULATABLE_INACTIVEDATE = "fechainactividad";
    
    /*
     * Definimos los campos de la tabla recursosaula
     */
    private static final String REAULTABLE             = "recursosaula";
    private static final String REAULTABLE_AULAID      = "idaula";
    private static final String REAULTABLE_RECID       = "idrecurso";
    
    /*
     * Definimos los campos de la tabla grupo
     */
    private static final String GRUPOTABLE             = "grupo";
    private static final String GRUPOTABLE_AULA        = "idaula";
    
   private Connection conn     = null;
   
   private SimpleDateFormat df = new SimpleDateFormat("yyy/MM/dd");

   /**
    * Constructor de la clase.
    * 
    * @param conn Una instancia de {@link Connection} que contiene la conexión a la base de datos.
    */
   public GestorAulas(Connection conn) 
   {
      this.conn = conn;
   }

   //===========================================
   // Propiedades
   //===========================================
   
   /**
    * Devuelve la conexión a la base de datos usada en la instancia para realizar las operaciones sobre la bbdd.
    */
   public Connection getConnection() 
   {
      return conn;
   }
   
   //===========================================
   // Métodos
   //===========================================
   
   /**
     * Construye un objecto usuario de la información extraída de la consulta SQL
     * 
     * @param  rs {@link ResulsSet} proviniente de una consulta SQL que recoja
     *         TODA la info proviniente de un usuario
     * @return Una instancia de {@link Usuario}
     * 
     * @throws SQLException
     * @throws Exception
     * 
     */
    private Aula buildAula(ResultSet rs) throws SQLException, Exception{
        Aula aula = new Aula();
        
        aula.setId               (rs.getInt     (AULATABLE_ID));
        aula.setNombre           (rs.getString  (AULATABLE_NAME));
        aula.setCapacidad        (rs.getInt     (AULATABLE_CAPACITY));
        aula.setDescripcion      (rs.getString  (AULATABLE_DESCRIPTION));
        aula.setLocalizacion     (rs.getString  (AULATABLE_LOCATION));
        aula.setFechaAlta        (rs.getDate    (AULATABLE_DATE));
        aula.setActiva           (rs.getBoolean (AULATABLE_ACTIVE));
        aula.setFechaInactividad (rs.getDate    (AULATABLE_INACTIVEDATE));
        
        
        return aula;
    }
    
   /**
    * Recupera la información de una aula.
    * 
    * @param id Identificador único de la aula.
    * @return Una instancia de {@link Aula} que representa la aula solicitada.
    * 
    * @throws SQLException
    * @throws Exception
    */
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
   
   /**
    * Recupera la lista de aulas.
    * 
    * @return Una lista de instancias de {@link Aula} que representan todas las aulas del centro.
    * 
    * @throws SQLException
    * @throws Exception
    */
   public ArrayList<Aula> getAulas() throws SQLException, Exception 
   {
      String sql;
      Aula aula;
      ArrayList<Aula> aulas = new ArrayList<Aula>();
      
      sql = "SELECT * FROM " + AULATABLE        + " "    + 
            "WHERE "         + AULATABLE_ACTIVE + " = '" + 1 + "'" +
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
   
   /**
    * Recupera la lista de aulas.
    * 
    * @return Una lista de instancias de {@link Aula} que representan todas las aulas del centro.
    * 
    * @throws SQLException
    * @throws Exception
    */
   public ArrayList<Aula> getAulasInactivas() throws SQLException, Exception 
   {
      String sql;
      Aula aula;
      ArrayList<Aula> aulas = new ArrayList<Aula>();
      
      sql = "SELECT * FROM " + AULATABLE        + " "    + 
            "WHERE "         + AULATABLE_ACTIVE + " = '" + 0 + "'" +
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
   
   /**
     * Agrega un nuevo usuario al centro.
     * 
     * @param usuario Una instancia de {@link Usuario} que contiene los datos del grupo.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void add(Aula aula) throws SQLException, Exception {
        String sql;
        Statement statement;
        ResultSet rs;
        
        aula.setActiva(true);
      
        try {
            sql = "INSERT INTO " + AULATABLE                             + " "  +
                  "("            + AULATABLE_NAME                        + ","  + 
                  " "            + AULATABLE_CAPACITY                    + ","  +
                  " "            + AULATABLE_DESCRIPTION                 + ","  +
                  " "            + AULATABLE_LOCATION                    + ","  +
                  " "            + AULATABLE_DATE                        + ","  +
                  " "            + AULATABLE_ACTIVE                      + ") " +
                  "VALUES "      +
                  "('"           + aula.getNombre()                      + "', " +
                  " "            + aula.getCapacidad()                   + ", "  +
                  " '"           + aula.getDescripcion()                 + "', " +
                  " '"           + aula.getLocalizacion()                + "', " +
                  " '"           + df.format(aula.getFechaAlta())        + "', " +
                  " '"           + aula.isActivaBit()                    + "')";

            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
    /**
     * Actualiza los datos de un Usuario.
     * 
     * @param usuario Una instancia de {@link Usuario} que contiene los datos actualizados del usuario.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void update(Aula aula) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        try {
            sql = "UPDATE " + AULATABLE              + " "        +
                  "SET "    + AULATABLE_NAME         + " = '"     + aula.getNombre()                      + "', " +
                              AULATABLE_CAPACITY     + " = "      + aula.getCapacidad()                   + ", "  +
                              AULATABLE_DESCRIPTION  + " = '"     + aula.getDescripcion()                 + "', " +
                              AULATABLE_LOCATION     + " = '"     + aula.getLocalizacion()                + "' "  +
                  "WHERE "  + AULATABLE_ID           + " = "      + aula.getId();
            
            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
   
    /**
     * Elimina (borrado logico) una Aula.
     * 
     * @param id Identificador de la Aula a eliminar.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void delete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        Date now = new Date();
        
        try {
            sql = "UPDATE " + AULATABLE + " "        +
                  "SET "    + AULATABLE_ACTIVE       + " = '"     + 0              + "', " +
                              AULATABLE_INACTIVEDATE + " = '"     + df.format(now) + "' "  +
                  "WHERE "  + AULATABLE_ID           + " = "      + id;
            //sql = "DELETE FROM " + AULATABLE    + " "   + 
            //      "WHERE "       + AULATABLE_ID + " = " + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
    /**
     * Libera los recursos asignados a una aula
     * 
     * @param id Identificador Aula
     * @throws SQLException
     * @throws Exception 
     */
    public void freeRecursosFromAula(int id) throws SQLException, Exception {
        String sql;
        Statement statement;
        
        try {
            sql = "DELETE FROM " + REAULTABLE        + " "   +
                  "WHERE "       + REAULTABLE_AULAID + " = " + id;
            
            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
    /**
     * Recupera (de un borrado lógico) una Aula del centro.
     * 
     * El aula se activa asignando el bit '1' a AULATABLE_ACTIVE
     * 
     * @param id Identificador de la Aula a eliminar.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void undelete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        Date now = new Date();
        
        try {
            sql = "UPDATE " + AULATABLE              + " "        +
                  "SET "    + AULATABLE_ACTIVE       + " = '"     + 1   + "' " +
                  "WHERE "  + AULATABLE_ID           + " = "      + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
    /**
     * Devuelve el número de Grupos que un Aula (Profesor) tiene asignados
     * 
     * @param id Identificador del Aula a checkear
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public int checkGruposAssigned(int id) throws SQLException, Exception {
        String sql;
        ResultSet rs;
        
        int grupos = 0;
        try {
            sql = "SELECT COUNT(*) as total FROM " + GRUPOTABLE      + " "  +
                  "WHERE "                         + GRUPOTABLE_AULA + " = " + id;
            Statement objStt = (Statement) getConnection().createStatement();
            rs = objStt.executeQuery(sql);
            if(rs.next()) {
                grupos = rs.getInt("total");
            }
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
        
        return grupos;
    }
}