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
    private static final String AULATABLE              = "aula";
    private static final String AULATABLE_ID           = "aula_id";
    private static final String AULATABLE_CENTER       = "centre_id";
    private static final String AULATABLE_CODE         = "codi_aula";
    private static final String AULATABLE_NAME         = "nom";
    private static final String AULATABLE_CAPACITY     = "capacitat";
    private static final String AULATABLE_LOCATION     = "ubicacio";
    private static final String AULATABLE_DATE         = "data_alta";
    private static final String AULATABLE_CANCELLDATE  = "data_baixa";
    
    /*
     * Definimos los campos de la tabla recursosaula
     */
    private static final String REAULTABLE             = "recursosaula";
    private static final String REAULTABLE_AULAID      = "idaula";
   // private static final String REAULTABLE_RECID       = "idrecurso";
    
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
        aula.setCentro           (rs.getInt     (AULATABLE_CENTER));
        aula.setCodigo           (rs.getInt     (AULATABLE_CODE ));
        aula.setNombre           (rs.getString  (AULATABLE_NAME));
        aula.setCapacidad        (rs.getInt     (AULATABLE_CAPACITY));
        aula.setUbicacion        (rs.getString  (AULATABLE_LOCATION));
        aula.setFechaAlta        (rs.getDate    (AULATABLE_DATE));
        aula.setFechaBaja        (rs.getDate    (AULATABLE_CANCELLDATE));
 
        
        
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
                  " "            + aula.getCodigo()                      + ", "  +
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
                              AULATABLE_CODE         + " = "      + aula.getCodigo()                   + ", "  +
                              AULATABLE_CENTER       + " = "      + aula.getCentro()                   + ", "  +
                              AULATABLE_LOCATION     + " = '"     + aula.getUbicacion()                + "' "  +
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
                  "SET "   +  AULATABLE_CANCELLDATE + " = '"     + df.format(now) + "' "  +
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
                  "SET "    + AULATABLE_CANCELLDATE       + " = NULL " +
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
