package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Curso;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.exceptions.NoGroupFoundException;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import edu.uoc.tdp.pac4.util.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Gestiona los cursos del centro.
 * 
 * @author eSupport Netbeans
 */
public class GestorCurso extends GestorDisco
{
    /*
     * Definimos los campos de la tabla curso
     */
    private static final String CURSOTABLE              = "curso";
    private static final String CURSOTABLE_ID           = "id";
    private static final String CURSOTABLE_NAME         = "nombre";
    private static final String CURSOTABLE_ASISTENCIA   = "minasistencia";
    private static final String CURSOTABLE_PLAN         = "plandocente";
    private static final String CURSOTABLE_BIBLIO       = "bibliografia";
    private static final String CURSOTABLE_DATEINI      = "fecha_inicio";
    private static final String CURSOTABLE_DATEEND      = "fecha_finalizacion";
    private static final String CURSOTABLE_ACTIVE       = "activo";
    private static final String CURSOTABLE_INACTIVEDATE = "fechainactividad";
    
    /*
     * Definimos los campos de interes de la tabla grupo
     */
    private static final String GRUPOTABLE              = "grupo";
    private static final String GRUPOTABLE_CURSO        = "idcurso";
    
    private Connection conn     = null;
    private LanguageUtils txt   = null;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
   
   //===========================================
   // Constructors
   //===========================================
   
   /**
    * Constructor de la clase.
    */
   public GestorCurso(Connection conn) 
   {
      this.conn = conn;
   }
   
   //===========================================
   // Propiedades
   //===========================================
   
   /**
    * Devuelve la conexión a base de datos.
    */
   @Override
   public Connection getConnection() 
   {
      return conn;
   }
   
   //===========================================
   // Métodos
   //===========================================

   /**
    * Obtiene una lista completa de los cursos del centro.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Curso get(int id) throws SQLException, Exception
   {
      Curso curso = null;
      String sql;
      
      sql = "SELECT * " +
            "FROM curso " +
            "WHERE id = " + id;
      
      try
      {
         ResultSet rs = executeSql(sql);
         if (rs.next()) 
         {
            curso = new Curso();
            readCurso(curso, rs);
            
            return curso;
         }
      }
      catch (SQLException ex) 
      {
         throw ex;
      } 
      catch (Exception ex) 
      {
         throw ex;
      }
      
      return curso;
   }
   
   /**
    * Obtiene una lista completa de los cursos del centro.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Curso> getCursos() throws SQLException, Exception
   {
      Curso curso;
      String sql;
      ArrayList<Curso> list = new ArrayList<Curso>();
      
      sql = "SELECT * FROM " + CURSOTABLE        + " "    + 
            "WHERE "         + CURSOTABLE_ACTIVE + " = '" + 1 + "'" +
            "ORDER BY "      + CURSOTABLE_NAME   + " Asc";
      
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            curso = new Curso();
            readCurso(curso, rs);
            list.add(curso);
         }
      }
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return list;
   }
   
   /**
    * Obtiene una lista completa de los cursos inactivos del centro.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Curso> getCursosInactivos() throws SQLException, Exception
   {
      Curso curso;
      String sql;
      ArrayList<Curso> list = new ArrayList<Curso>();
      
      sql = "SELECT * FROM " + CURSOTABLE        + " "    + 
            "WHERE "         + CURSOTABLE_ACTIVE + " = '" + 0 + "'" +
            "ORDER BY "      + CURSOTABLE_NAME   + " Asc";
      
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            curso = new Curso();
            readCurso(curso, rs);
            list.add(curso);
         }
      }
      catch (SQLException ex) {throw ex;} 
      catch (Exception ex)    {throw ex;}
      
      return list;
   }
   
   /**
    * Obtiene una lista completa de los cursos de un profesor.
    * 
    * @param login Identificador único del profesor.
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Curso> getCursosProfesor(String login) throws SQLException, Exception
   {
      Curso curso;
      String sql;
      ArrayList<Curso> list = new ArrayList<Curso>();
      
      sql = "SELECT curso.nombre,curso.id " +
            "FROM curso,usuario,grupo " +
            "WHERE grupo.idcurso=curso.id and usuario.id=grupo.idprofesor and usuario.login='" + login + "'"+ 
            "group by curso.nombre,curso.id" +
            " ORDER BY curso.nombre Asc";
      
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            curso = new Curso();
            curso.setId(rs.getInt("id"));
            curso.setNombre(rs.getString("nombre"));
          
            
            list.add(curso);
         }
      }
      catch (SQLException ex) 
      {
         throw ex;
      } 
      catch (Exception ex) 
      {
         throw ex;
      }
      
      return list;
   }
   
   /**
    * Devuelve el número de plazas disponibles para un determinado curso y turno.<br />
    * Se calcula en función de las matrículas aceptadas para tener mayor fiabilidad.
    * 
    * @param idCurso Identificador único del curso.
    * @param turno Código del turno.
    * @return El número de plazas disponibles en un grupo y turno.
    */
   public int getPlazasDisponibles(int idCurso, int turno) throws NoGroupFoundException, SQLException, Exception
   {
      int plazas = 0, alumnos = 0, idgrupo = 0;
      String sql;
      Statement objStt;
      ResultSet rs;

      try
      {
         // Obtiene el grupo correspondiente al turno y curso
         sql = "SELECT grupo.grupoid, grupo.maxalumnos " +
               "FROM   curso Inner Join grupo On (grupo.idcurso = curso.id) " +
               "WHERE  curso.id    = " + idCurso + " And " +
               "       grupo.turno = " + turno;
         
         rs = executeSql(sql);
         if (rs.next()) 
         {
            idgrupo = rs.getInt("grupoid");
            plazas = rs.getInt("maxalumnos");
         }
         else
         {
            throw new NoGroupFoundException();
         }
         
         // Obtiene el número de plazas libres
         sql = "SELECT Count(*) As nPlazas " +
               "FROM   curso Inner Join matriculas On (curso.id = matriculas.cursoid) " +
               "             Inner Join grupo      On (grupo.grupoid = matriculas.grupoid) " +
               "WHERE  matriculas.estadoid = " + Matricula.MATRICULA_ESTADO_ACEPTADA + " And " +
               "       grupo.turno         = " + turno + " And " +
               "       curso.id            = " + idCurso;
         
         rs = executeSql(sql);
         if (rs.next()) 
         {
            alumnos = rs.getInt("nPlazas");
         }
      }
      catch (SQLException ex) 
      {
         throw ex;
      } 
      catch (Exception ex) 
      {
         throw ex;
      }
      
      return plazas - alumnos;
   }
   
   /**
     * Agrega un nuevo usuario al centro.
     * 
     * @param usuario Una instancia de {@link Usuario} que contiene los datos del grupo.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void add(Curso curso) throws SQLException, Exception {
        String sql;
        Statement statement;
        ResultSet rs;
        
        curso.setActivo(true);
      
        try {
            sql = "INSERT INTO " + CURSOTABLE                             + " "  +
                  "("            + CURSOTABLE_NAME                        + ","  + 
                  " "            + CURSOTABLE_ASISTENCIA                  + ","  +
                  " "            + CURSOTABLE_PLAN                        + ","  +
                  " "            + CURSOTABLE_BIBLIO                      + ","  +
                  " "            + CURSOTABLE_DATEINI                     + ","  +
                  " "            + CURSOTABLE_DATEEND                     + ","  +
                  " "            + CURSOTABLE_ACTIVE                      + ") " +
                  "VALUES "      +
                  "('"           + curso.getNombre()                                + "', " +
                  " "            + curso.getMinasistencia()                         + ", "  +
                  " '"           + StringUtils.formatToSql(curso.getPlandocente())  + "', " +
                  " '"           + StringUtils.formatToSql(curso.getBibliografia()) + "', " +
                  " '"           + df.format(curso.getFechaInicio())                + "', " +
                  " '"           + df.format(curso.getFechaFin())                   + "', " +
                  " '"           + curso.isActivoBit()                              + "')";

            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
    /**
     * Actualiza los datos de un Curso.
     * 
     * @param usuario Una instancia de {@link Curso} que contiene los datos actualizados del curso.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void update(Curso curso) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        try {
            sql = "UPDATE " + CURSOTABLE            + " "        +
                  "SET "    + CURSOTABLE_NAME       + " = '"     + curso.getNombre()                                + "', " +
                              CURSOTABLE_DATEINI    + " = '"     + df.format(curso.getFechaInicio())                + "', " +
                              CURSOTABLE_DATEEND    + " = '"     + df.format(curso.getFechaFin())                   + "', " +
                              CURSOTABLE_ASISTENCIA + " = "      + curso.getMinasistencia()                         + ", "  +
                              CURSOTABLE_PLAN       + " = '"     + StringUtils.formatToSql(curso.getPlandocente())  + "', " +
                              CURSOTABLE_BIBLIO     + " = '"     + StringUtils.formatToSql(curso.getBibliografia()) + "' "  +
                  "WHERE "  + CURSOTABLE_ID         + " = "      + curso.getId();
            System.err.print(sql);
            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
    /**
     * Elimina (borrado logico) un Curso.
     * 
     * @param id Identificador del Curso a eliminar.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void delete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        Date now = new Date();
        
        try {
            sql = "UPDATE " + CURSOTABLE + " "        +
                  "SET "    + CURSOTABLE_ACTIVE       + " = '"     + 0              + "', " +
                              CURSOTABLE_INACTIVEDATE + " = '"     + df.format(now) + "' "  +
                  "WHERE "  + CURSOTABLE_ID           + " = "      + id;
            
            //sql = "DELETE FROM " + AULATABLE    + " "   + 
            //      "WHERE "       + AULATABLE_ID + " = " + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
    /**
     * Recupera (de un borrado lógico) un Curso del centro.
     * 
     * El curso se activa asignando el bit '1' a CURSOTABLE_ACTIVE
     * 
     * @param id Identificador del Curso a eliminar.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void undelete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        Date now = new Date();
        
        try {
            sql = "UPDATE " + CURSOTABLE              + " "        +
                  "SET "    + CURSOTABLE_ACTIVE       + " = '"     + 1   + "' " +
                  "WHERE "  + CURSOTABLE_ID           + " = "      + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
    
    /**
     * Devuelve el número de Grupos que un Curso tiene asignados
     * 
     * @param id Identificador del Curso a checkear
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public int checkGruposAssigned(int id) throws SQLException, Exception {
        String sql;
        ResultSet rs;
        
        int grupos = 0;
        try {
            sql = "SELECT COUNT(*) as total FROM " + GRUPOTABLE + " " +
                  "WHERE " + GRUPOTABLE_CURSO + " = " + id;
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

   //===========================================
   // Private members
   //===========================================
   
   /**
    * Actualiza los datos de una instancia de {@link Curso} con los datos de una fila de {@link Resultset}.
    */
   private void readCurso(Curso curso, ResultSet rs) throws SQLException
   {
      curso.setId(rs.getInt("id"));
      curso.setNombre(rs.getString("nombre"));
      curso.setMinasistencia(rs.getInt("minasistencia"));
      curso.setPlandocente(rs.getString("plandocente"));
      curso.setBibliografia(rs.getString("bibliografia"));
      curso.setActivo(rs.getBoolean("activo"));
      curso.setFechaInicio(rs.getDate("fecha_inicio"));
      curso.setFechaFin(rs.getDate("fecha_finalizacion"));
      curso.setFechainactividad(rs.getDate("fechainactividad"));
   }
}
