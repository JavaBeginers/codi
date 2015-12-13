package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Asistencia;
import edu.uoc.tdp.pac4.exceptions.AssistanceAlreadyCountedException;
import edu.uoc.tdp.pac4.exceptions.GroupAlreadyCountedException;
import edu.uoc.tdp.pac4.exceptions.StudentAssistanceAlreadyCountedException;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Implementa el gestor para el control de asistencia.
 * 
 * @author JavaBeginers
 */
public class GestorAsistencia extends GestorDisco
{
   private Connection conn = null;
   private LanguageUtils txt = null;
   
   /**
    * Constructor de la clase.
    */
   public GestorAsistencia(Connection conn) 
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
    * Obtiene la última asistencia registrada para el conjunto del @link @Grupo
    * @param idGrupo
    * @return la última entrada de asistencia para el grupo. Null si no se encuentra.
    * @throws SQLException
    * @throws Exception 
    */
   public Asistencia getLastAssistance(int idGrupo) throws SQLException, Exception
   {
       Asistencia asistencia = null;
       String sql;
       ArrayList<Asistencia> list = new ArrayList<Asistencia>();
       
      try 
      {
          
         sql = "SELECT * " +
         "FROM assistencia " +
         "WHERE assistencia.grupoid = " + idGrupo + " " +
         "ORDER BY asistid DESC LIMIT 1";
                  
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            asistencia = new Asistencia();
            readAsistencia(asistencia, rs);
            list.add(asistencia);
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
       
       return (list.isEmpty() ? null : list.get(0));
    }
    
   
   /**
    * Inicia el recuento.
    * 
    * @param asistencia Una instancia de {@link Asistencia} que contiene los datos del recuento.
    * @return El identificador del recuento.
    * 
    * @throws SQLException
    * @throws GroupAlreadyCountedException
    * @throws Exception 
    */
   public int startRecuento(Asistencia asistencia) throws SQLException, GroupAlreadyCountedException, Exception
   {
      String sql;
      SimpleDateFormat dfd = new SimpleDateFormat("yyyy/MM/dd");
      SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");
      
      try 
      {
         // Comprueba que no exista otro recuento para la misma fecha y grupo.
         sql = "SELECT Count(*) As nItems " +
               "FROM   assistencia " +
               "WHERE  assistencia.fechaasist = '" + dfd.format(asistencia.getDate()) + "' And " +
               "       assistencia.grupoid = " + asistencia.getIdGrupo();
         if (executeScalar(sql) > 0)
         {
            throw new GroupAlreadyCountedException();
         }
         
         // Agrega la petición y actualiza la instancia con el nuevo ID
         sql = "INSERT INTO assistencia (asistid, fechaasist, horainicio, horafin, totalasisten, totalnoasisten, grupoid) " +
               "VALUES " +
               "(     DEFAULT, " +
               " '" + dfd.format(asistencia.getDate()) + "', " +
               " '" + dft.format(asistencia.getHoraInicio()) + "', " +
               "      null, " +
               "  " + asistencia.getTotalAssistencia() + ", " +
               "  " + asistencia.getTotalFaltas() + ", " +
               "  " + asistencia.getIdGrupo() + ") " +
               "RETURNING asistid";
         asistencia.setId(executeScalar(sql));
         
         // Marca el grupo con el flag Recuento Abierto
         sql = "UPDATE grupo " +
               "SET    asistenciaabierta = '1' " +
               "WHERE  grupo.grupoid     = " + asistencia.getIdGrupo();
         execute(sql);
         
         // Devuelve el ID del recuento
         return asistencia.getId();
      } 
      catch (SQLException ex) 
      {
         throw ex;
      }
      catch (GroupAlreadyCountedException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
   }
   
   /**
    * Detiene el recuento.
    * 
    * @param grupo Una instancia de {@link Asistencia} que contiene los datos del recuento.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public void stopRecuento(Asistencia asistencia) throws SQLException, Exception
   {
      int nAlumnos;
      int nPresentes;
      String sql;
      SimpleDateFormat dfd = new SimpleDateFormat("yyyy/MM/dd");
      SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");
      
      try 
      {
         // Marca el grupo con el flag Recuento Cerrado
         sql = "UPDATE grupo " +
               "SET    asistenciaabierta = '0' " +
               "WHERE  grupo.grupoid     = " + asistencia.getIdGrupo();
         execute(sql);
         
         // Obtiene el número de alumnos en total para el grupo
         sql = "SELECT Count(*) As nAlumnos " +
               "FROM   matriculas " +
               "WHERE  matriculas.grupoid = " + asistencia.getIdGrupo();
         nAlumnos = executeScalar(sql);
         
         // Obtiene el recuento de asistencia
         sql = "SELECT Count(*) As nAlumnos " +
               "FROM   asistenciaalumno " +
               "WHERE  asistid = " + asistencia.getId();
         nPresentes = executeScalar(sql);
         
         // Actualiza el recuento
         sql = "UPDATE assistencia " +
               "SET    horafin            = '" + dft.format(asistencia.getHoraFin()) + "', " +
               "       totalasisten       =  " + nPresentes + ", " +
               "       totalnoasisten      =  " + (nAlumnos - nPresentes) + " " +
               "WHERE  assistencia.asistid = " + asistencia.getId();
         execute(sql);
      } 
      catch (SQLException ex) 
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
   }
   
   /**
    * Actualiza un recuento de asistencia.
    * 
    * @param grupo Una instancia de {@link Asistencia} que contiene los datos del recuento.
    * 
    * @throws SQLException
    * @throws GroupAlreadyCountedException
    * @throws Exception 
    */
   public void update(Asistencia asistencia) throws SQLException, Exception
   {
      String sql;
      SimpleDateFormat dfd = new SimpleDateFormat("yyyy/MM/dd");
      SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");
      
      try 
      {
         // Agrega la petición
         sql = "UPDATE assistencia " +
               "SET    fechaasist    = '" + dfd.format(asistencia.getDate()) + "', " +
               "       horainicio    = '" + dft.format(asistencia.getHoraInicio()) + "', " +
               "       horafin       = '" + dft.format(asistencia.getHoraFin()) + "', " +
               "       totalasisten  =  " + asistencia.getTotalAssistencia() + ", " +
               "       totalnoasisten =  " + asistencia.getTotalFaltas() + ", " +
               "       grupoid       =  " + asistencia.getIdGrupo() + " " +
               "WHERE  assistencia.asistid = " + asistencia.getId();

         execute(sql);
      } 
      catch (SQLException ex) 
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
   }
   
  /**
    * Agrega una nueva entrada en AsistenciaAlumno
    * 
    * @param idasistid, alumnoid Id de @link Asistencia e id de @link Alumno
    * 
    * @throws SQLException
    * @throws GroupAlreadyCountedException
    * @throws Exception 
    */
    public void addAsistenciaAlumno(int asistid, int alumnoid) throws SQLException, AssistanceAlreadyCountedException, Exception
    {
      String sql;

      try 
      {
         // Comprueba que no exista otro registro de asistencia para dicha asistencia
         sql = "SELECT Count(*) As nItems " +
               "FROM asistenciaalumno " +
               "WHERE asistenciaalumno.asistid =  " + asistid + " And " +
               "      asistenciaalumno.alumnoid = " + alumnoid;
         if (executeScalar(sql) > 0)
         {
            throw new StudentAssistanceAlreadyCountedException();
         }
         
         // Agrega el registro de asistencia
         sql = "INSERT INTO asistenciaalumno (asistid, alumnoid) " +
               "VALUES " +
               "("  + asistid  + "," +
               "  " + alumnoid + ")";               
         execute(sql);
      } 
      catch (SQLException ex) 
      {
         throw ex;
      }
      catch (StudentAssistanceAlreadyCountedException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
   }
    
     //===========================================
   // Private members
   //===========================================
   
   private void readAsistencia(Asistencia asistencia, ResultSet rs) throws SQLException
   {
      // Propiedades nativas de la entidad
      asistencia.setId(rs.getInt("asistid"));
      asistencia.setDate(rs.getDate("fechaasist"));
      asistencia.setHoraInicio(rs.getDate("horainicio"));
      asistencia.setHoraFin(rs.getDate("horafin"));
      asistencia.setTotalAssistencia(rs.getInt("totalasisten"));
      asistencia.setTotalFaltas(rs.getInt("totalnoasisten"));
      asistencia.setIdGrupo(rs.getInt("grupoid"));      

   }
}