package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Grupo;
import edu.uoc.tdp.pac4.exceptions.GroupAlreadyExistsException;
import edu.uoc.tdp.pac4.exceptions.GroupNotEmptyException;
import edu.uoc.tdp.pac4.exceptions.NotAvailableProfessorException;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import edu.uoc.tdp.pac4.util.StringUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Gestor de grupos del centro.
 * 
 * @author eSupport Netbeans
 */
public class GestorGrupo extends GestorDisco
{
   private Connection conn = null;
   private LanguageUtils txt = null;
   
   //===========================================
   // Constructors
   //===========================================
   
   /**
    * Constructor de la clase.
    */
   public GestorGrupo(Connection conn) 
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
    * Obtiene un grupo.
    * 
    * @param id Identificador único del grupo.
    * @return Una instancia de {@link Grupo} que contiene los datos del grupo solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Grupo get(int id) throws SQLException, Exception
   {
      String sql;
      Grupo grupo = null;
      
      sql = "SELECT grupo.*, usuari.noms As pnombre, usuari.cognoms As papellidos, aula.nom As aula, " +
            "       curso.nombre As curso, curso.plandocente AS plandocente, curso.bibliografia AS bibliografia, " +
            "       curso.minasistencia as minasistencia, curso.fecha_inicio, curso.fecha_finalizacion " +
            "FROM   grupo Inner Join curso On (grupo.idcurso = curso.id) " +
            "             Left Join aula On (grupo.idaula = aula.aula_id) " +
            "             Left Join usuari On (grupo.idprofesor = usuari.usuari_id) " +
            "WHERE  grupo.grupoid = " + id;
      
      try 
      {
         ResultSet rs = executeSql(sql);
         if (rs.next()) 
         {
            grupo = new Grupo();
            readGrupo(grupo, rs, false);
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
      
      return grupo;
   }
   
   /**
    * Agrega un nuevo grupo al centro.
    * 
    * @param grupo Una instancia de {@link Grupo} que contiene los datos del grupo.
    * 
    * @throws SQLException
    * @throws GroupAlreadyExistsException
    * @throws NotAvailalbleProfessorException
    * @throws Exception 
    */
   public void add(Grupo grupo) throws SQLException, GroupAlreadyExistsException, NotAvailableProfessorException, Exception
   {
      String sql;
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
      try 
      {
         // Comprueba que no exista otro grupo para el mismo actividad y turno
         sql = "SELECT Count(*) As nItems " +
               "FROM   grupo " +
               "WHERE  grupo.idactividad = " + grupo.getIdActividad() + " And " +
               "       grupo.turno   = " + grupo.getTurno();
         if (executeScalar(sql) > 0)
         {
            throw new GroupAlreadyExistsException();
         }
         
         // Comprueba que el profesor no tenga otro grupo en en las mismas fechas y turno
         // Comprobación datos SQL: grupo.idprofesor, grupo.turno, curso.id, curso.nombre, curso.fecha_inicio, curso.fecha_finalizacion
         sql = "SELECT Count(*) As nItems " +
               "FROM   grupo, curso " +
               "WHERE  curso.id                  = grupo.idcurso And " +
               "       curso.activo              = '1' And " +
               "       grupo.idprofesor          = " + grupo.getIdProfesor() + " And " +
               "       grupo.turno               = " + grupo.getTurno() + " And " +
               "       curso.fecha_inicio       <= (SELECT fecha_inicio       FROM curso c WHERE c.id = 1) And " +
               "       curso.fecha_finalizacion >= (SELECT fecha_finalizacion FROM curso c WHERE c.id = 1)";
         if (executeScalar(sql) > 0)
         {
            throw new NotAvailableProfessorException();
         }

         // Agrega la petición
         sql = "INSERT INTO grupo (nombre, maxalumnos, plazasdisponibles, grupovigente, turno, idaula, idprofesor, idcurso) " +
               "VALUES " +
               "('" + StringUtils.formatToSql(grupo.getNombre()) + "', " +
               "  " + grupo.getMaxAlumnos() + ", " +
               "  " + grupo.getPlazasDisponibles() + ", " +
               " '" + (grupo.isGrupoVigente() ? "1" : "0") + "', " +
               "  " + grupo.getTurno() + ", " +
               "  " + grupo.getIdAula() + ", " +
               "  " + grupo.getIdProfesor() + ", " +
               "  " + grupo.getIdActividad() + ")";
         execute(sql);
      } 
      catch (SQLException ex) 
      {
         throw ex;
      }
      catch (NotAvailableProfessorException ex)
      {
         throw ex;
      }   
      catch (GroupAlreadyExistsException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
   }
   
   /**
    * Actualiza los datos de un grupo.
    * 
    * @param grupo Una instancia de {@link Grupo} que contiene los datos actualizados del grupo.
    * 
    * @throws SQLException
    * @throws GroupAlreadyExistsException
    * @throws NotAvailableProfessorException
    * @throws Exception 
    */
   public void update(Grupo grupo) throws SQLException, GroupAlreadyExistsException, NotAvailableProfessorException, Exception
   {
      String sql;
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
      try 
      {
         // Comprueba que no exista otro grupo para el mismo curso y turno
         sql = "SELECT Count(*) As nItems " +
               "FROM   grupo " +
               "WHERE  grupo.idcurso = " + grupo.getIdActividad() + " And " +
               "       grupo.turno   = " + grupo.getTurno() + " And " +
               "       grupo.grupoid <>" + grupo.getId();
         if (executeScalar(sql) > 0)
         {
            throw new GroupAlreadyExistsException();
         }
         
         // Comprueba que el profesor no tenga otro grupo en en las mismas fechas y turno
         // Comprobación datos SQL: grupo.idprofesor, grupo.turno, curso.id, curso.nombre, curso.fecha_inicio, curso.fecha_finalizacion
         sql = "SELECT Count(*) As nItems " +
               "FROM   grupo, curso " +
               "WHERE  curso.id                  = grupo.idcurso And " +
               "       curso.activo              = '1' And " +
               "       grupo.idprofesor          = " + grupo.getIdProfesor() + " And " +
               "       grupo.turno               = " + grupo.getTurno() + " And " +
               "       curso.fecha_inicio       <= (SELECT fecha_inicio       FROM curso c WHERE c.id = 1) And " +
               "       curso.fecha_finalizacion >= (SELECT fecha_finalizacion FROM curso c WHERE c.id = 1) And " +
               "       grupo.grupoid            <> " + grupo.getId();
         if (executeScalar(sql) > 0)
         {
            throw new NotAvailableProfessorException();
         }
         
         // Agrega la petición
         sql = "UPDATE grupo " +
               "SET nombre            = '" + StringUtils.formatToSql(grupo.getNombre()) + "', " +
                   "maxalumnos        =  " + grupo.getMaxAlumnos() + ", " +
                   "plazasdisponibles =  " + grupo.getPlazasDisponibles() + ", " +
                   "grupovigente      = '" + (grupo.isGrupoVigente() ? "1" : "0") + "', " +
                   "turno             =  " + grupo.getTurno() + ", " +
                   "idaula            =  " + grupo.getIdAula() + ", " +
                   "idprofesor        =  " + grupo.getIdProfesor() + ", " +
                   "idcurso           =  " + grupo.getIdActividad() + " " +
               "WHERE grupoid = " + grupo.getId();

         execute(sql);
      } 
      catch (SQLException ex) 
      {
         throw ex;
      }
      catch (NotAvailableProfessorException ex)
      {
         throw ex;
      }
      catch (GroupAlreadyExistsException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
   }
   
   /**
    * Elimina un grupo.
    * 
    * @param id Identificador del grupo a eliminar.
    * 
    * @throws SQLException
    * @throws GroupNotEmptyException
    * @throws Exception 
    */
   public void delete(int id) throws SQLException, GroupNotEmptyException, Exception
   {
      String sql;
      
      try 
      {
         // Comprueba que el grupo no tenga ninguna matricula asignada
         sql = "SELECT Count(*) As nItems " +
               "FROM matriculas " +
               "WHERE matriculas.grupoid = " + id;
         if (executeScalar(sql) > 0)
         {
            throw new GroupNotEmptyException();
         }
         
         // Agrega la petición
         sql = "DELETE FROM grupo " +
               "WHERE grupoid = " + id;
         execute(sql);
      } 
      catch (SQLException ex) 
      {
         throw ex;
      }
      catch (GroupNotEmptyException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
   }
   
   /**
    * Obtiene una lista completa de los grupos del centro.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Grupo> getGrupos() throws SQLException, Exception
   {
      return getGrupos(0);
   }
   
   /**
    * Obtiene una lista de los grupos del centro correspondientes a un determinado curso.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Grupo> getGrupos(int idCurso) throws SQLException, Exception
   {
      Grupo grupo;
      String sql;
      ArrayList<Grupo> list = new ArrayList<Grupo>();
      
      sql = "SELECT grupo.*, usuari.noms As pnombre, usuari.cognoms As papellidos, aula.nom As aula, " +
            "       curso.nombre As curso, curso.plandocente AS plandocente, curso.bibliografia AS bibliografia, " +
            "       curso.minasistencia as minasistencia, curso.fecha_inicio, curso.fecha_finalizacion " +
            "FROM   grupo Inner Join curso On (grupo.idcurso = curso.id) " +
            "             Left Join aula On (grupo.idaula = aula.aula_id) " +
            "             Left Join usuari On (grupo.idprofesor = usuari.usuari_id) " +
            (idCurso > 0 ? "WHERE curso.id = " + idCurso + " " : "") +
            "ORDER BY grupo.nombre Asc";

      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            grupo = new Grupo();
            readGrupo(grupo, rs, false);
            list.add(grupo);
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
    * Obtiene una lista de los grupos del centro correspondientes a un determinado profesor.
    * 
    * @param idUsuario Identificador del profesor (usuario).
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Grupo> getGruposByProfesor(int idUsuario) throws SQLException, Exception
   {
      Grupo grupo;
      String sql;
      ArrayList<Grupo> list = new ArrayList<Grupo>();
      
      sql = "SELECT grupo.*, usuari.noms As pnombre, usuari.cognoms As papellidos, aula.nom As aula, " +
            "       curso.nombre As curso, curso.plandocente AS plandocente, curso.bibliografia AS bibliografia, " +
            "       curso.minasistencia as minasistencia, curso.fecha_inicio, curso.fecha_finalizacion " +
            "FROM   grupo, usuari, aula, curso " +
            "WHERE  grupo.idprofesor = usuari.usuari_id And " +
            "       grupo.idaula = aula.aula_id And " +
            "       grupo.idcurso = curso.id And " +
            "       grupo.idprofesor = " + idUsuario + " " +
            "ORDER BY grupo.nombre Asc";
     
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            grupo = new Grupo();
            readGrupo(grupo, rs, false);
            list.add(grupo);
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
    * Obtiene los instancias de @link Grupo en función de la @link Matricula de un alumno. 
    * Para obtener el nombre del profesor, es necesaria una subquery ya que ambos datos
    * están en la misma tabla (nombre de alumno y nombre de profesor)
    * @param idUsuario
    * @return
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Grupo> getGruposByMatricula(int idUsuario, int idCurso, int idTurno) throws  SQLException, Exception
   {
    
      Grupo grupo;
      String sql;
      ArrayList<Grupo> list = new ArrayList<Grupo>();
                  
     sql = "SELECT grupo.*, (SELECT usuari.noms || '#' || usuari.cognoms " +
                             "FROM grupo, usuari where grupo.idprofesor = usuari.usuari_id " + 
       "                     AND grupo.grupoid = matriculas.grupoid) AS nombreProfesor, " +
       "aula.nom As aula, curso.nombre As curso, curso.plandocente AS plandocente, curso.bibliografia AS bibliografia, " +
       "curso.minasistencia as minasistencia, curso.fecha_inicio, curso.fecha_finalizacion " +
       "FROM   grupo, usuari, aula, curso, matriculas " +
       "WHERE  grupo.idaula = aula.aula_id And " +
       "       grupo.idcurso = curso.id And " +
       "       grupo.grupoid = matriculas.grupoid And  " + 
       "       matricula.usuari_id = usuari.usuari_id And" +
       "       matricula.estat = 1 And usuari.usuari_id =  " + idUsuario + " " +
       (idCurso > 0 ? " And curso.id = " + idCurso + " " : " ") +
       (idTurno == Grupo.TURNO_MANANA || idTurno == Grupo.TURNO_TARDE ? " And grupo.turno = " + idTurno + " " : " ") +
       "And curso.fecha_inicio <= now() " +
       "ORDER BY grupo.nombre Asc;";
     
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            grupo = new Grupo();
            readGrupo(grupo, rs, true);
            list.add(grupo);
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
    * Obtiene una lista (filtrada) de los grupos del centro.
    * 
    * @param idTurno Identificador del curso para el que se desea filtrar ({@code -1} para ignorar).
    * @param idTurno Identificador del turno para el que se desea filtrar ({@code -1} para ignorar).
    * @param fechaInicio Fecha de inicio para la que se desea filtrar ({@code null} para ignorar).
    * @param fechaFin Fecha de finalización para la que se desea filtrar ({@code null} para ignorar).
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Grupo> getGrupos(int idCurso, int idTurno, Date fechaInicio, Date fechaFin) throws SQLException, Exception
   {
      Grupo grupo;
      String sql;
      ArrayList<Grupo> list = new ArrayList<Grupo>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      
      sql = "SELECT grupo.*, usuari.noms As pnombre, usuari.cognoms As papellidos, aula.nom As aula, " +
            "       curso.nombre As curso, curso.plandocente AS plandocente, curso.bibliografia AS bibliografia, " +
            "       curso.minasistencia as minasistencia, curso.fecha_inicio, curso.fecha_finalizacion " +
            "FROM   grupo Inner Join curso On (grupo.idcurso = curso.id) " +
            "             Left Join aula On (grupo.idaula = aula.aula_id) " +
            "             Left Join usuari On (grupo.idprofesor = usuari.usuari_id) " +
            "WHERE  grupo.grupoid > 0 " +
            (idCurso > 0 ? " And curso.id = " + idCurso + " " : "") +
            (idTurno == Grupo.TURNO_MANANA || idTurno == Grupo.TURNO_TARDE ? " And grupo.turno = " + idTurno + " " : "") +
            (fechaInicio != null ? " And curso.fecha_inicio >= '" + sdf.format(fechaInicio) + "' " : "") +
            (fechaFin != null ? " And curso.fecha_finalizacion <= '" + sdf.format(fechaFin) + "' " : "") +
            "ORDER BY grupo.nombre Asc";
     
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            grupo = new Grupo();
            readGrupo(grupo, rs, false);
            list.add(grupo);
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
   
   //===========================================
   // Private members
   //===========================================
   
   private void readGrupo(Grupo grupo, ResultSet rs, boolean matricula) throws SQLException
   {
      String name;
      String surname;
      
      // Propiedades nativas de la entidad
      grupo.setId(rs.getInt("grupoid"));
      grupo.setNombre(rs.getString("nombre"));
      grupo.setMaxAlumnos(rs.getInt("maxalumnos"));
      grupo.setPlazasDisponibles(rs.getInt("plazasdisponibles"));
      grupo.setGrupoVigente(rs.getBoolean("grupovigente"));
      grupo.setTurno(rs.getInt("turno"));
      grupo.setIdAula(rs.getInt("idaula"));
      grupo.setIdProfesor(rs.getInt("idprofesor"));
      grupo.setIdActividad(rs.getInt("idactividad"));
      grupo.setAsistenciaAbierta(rs.getBoolean("asistenciaabierta"));
            
      // Propiedades de sólo lectura
      grupo.setNombreAula((rs.getString("aula") == null ? "- no asignada -" : rs.getString("aula")));
      grupo.setNombreActividad(rs.getString("curso"));
      if (!matricula) 
      {
         grupo.setNombreProfesor(rs.getString("pnombre"), rs.getString("papellidos"));
      }
      else
      {
        grupo.setNombreProfesor(rs.getString("nombreProfesor").split("#")[1].toString() + ", " +
                                rs.getString("nombreProfesor").split("#")[0].toString());
      }
      grupo.setPlanDocenteActividad(rs.getString("plandocente"));
      grupo.setBibliografiaActividad(rs.getString("bibliografia"));
      grupo.setMinimoAsistenciaActividad(rs.getInt("minasistencia"));
      grupo.setFechaInicioActividad(rs.getDate("fecha_inicio"));
      grupo.setFechaFinActividad(rs.getDate("fecha_finalizacion"));
   }
}
