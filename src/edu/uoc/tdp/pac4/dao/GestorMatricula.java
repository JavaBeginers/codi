package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Grupo;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.exceptions.ApplicationAlreadyExistsException;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import edu.uoc.tdp.pac4.util.StringUtils;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Implementa el gestor de matriculas del centro.
 * 
 * @author JavaBeginers
 */
public class GestorMatricula extends GestorDisco
{
   private Connection conn = null;
   private LanguageUtils txt = null;
   
   /**
    * Constructor de la clase.
    */
   public GestorMatricula(Connection conn) 
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
    * Obtiene una matricula.
    * 
    * @param id Identificador único de la matricula.
    * @return Una instancia de {@link Grupo} que contiene los datos del matricula solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Matricula get(int id) throws SQLException, Exception
   {
      String sql;
      Matricula matricula = null;
      
      sql = "SELECT matriculas.*, actividad.data_inici As fechaInicio, actividad.data_fi As fechaFin, " +
            "       grupo.nombre As grupoNombre, actividad.titol As actividadNombre, usuario.nombre As usrNombre, " +
            "       usuario.cognoms As usrApellidos, usuario.nif as usrNif " +
            "FROM   matriculas, usuario, grupo, actividad " +
            "WHERE  matriculas.usuarioid = usuario.id And " +
                   "matriculas.grupoid = grupo.grupoid And " + 
                   "grupo.idactividad = actividad.id And " + 
                   "matricula.id = " + id;
      
      try 
      {
         ResultSet rs = executeSql(sql);
         if (rs.next()) 
         {
            matricula = new Matricula();
            getMatriculaValues(matricula, rs);
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
      
      return matricula;
   }
   
   /**
    * Agrega un nuevo matricula al centro.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public void add(Matricula matricula) throws SQLException, Exception
   {
      String sql;
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

         // Comprueba que no exista otra matricula para el mismo grupo y turno
         sql = "SELECT Count(*) As nItems " +
               "FROM matricula " +
               "WHERE matricula.usuari_id = " + matricula.getUsuarioId() + " And " +
               "      matricula.activitat_id = " + matricula.getActividadId()     + " And ";
              
         
         if (executeScalar(sql) > 0)
         {
            throw new ApplicationAlreadyExistsException();
         }                  
      
      try 
      {
         // Agrega la petición (peticionid = 1, alta)
         sql = "INSERT INTO matricula (actividadid, estat, usuari_id, data) " +
               "VALUES " +
               "(1, "                               +
               "  " + matricula.getActividadId() + ", " +  
              
               "  " + matricula.getEstado() + ", " +
               "  " + matricula.getUsuarioId() + ", " +
               "      current_timestamp)";
         
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
    * Actualiza los datos de una matricula.<br />
    * Si la matrícula estaba aceptada se actualizan las plazas libres del grupo.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos actualizados del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public void update(Matricula matricula) throws SQLException, Exception
   {
      String sql;
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
      try 
      {
         // Si la matricula previmanet estaba aceptada en otro grupo debe anular esa plaza
         liberarPlazaEnGroup(matricula);
         
         // Agrega la petición
         sql = "UPDATE matricula " +
               "SET actividad_id           = " + matricula.getActividadId() + ", " +
                   "estat          = " + matricula.getEstado() + ", " +
                   "usuari_id         = " + matricula.getUsuarioId() + ", " +
                   
               "WHERE id = " + matricula.getId();

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
    * Acepta un matricula para un alumno y grupo.<br />
    * Si la matrícula ya estaba aceptada se actualizan las plazas libres del grupo.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos actualizados del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public void accept(Matricula matricula) throws SQLException, Exception
   {
      String sql;
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

      // Actualiz ala instancia
      matricula.setEstado(Matricula.MATRICULA_ESTADO_ACEPTADA);

      try 
      {
         // Si la matricula previmanet estaba aceptada en otro grupo debe anular esa plaza
         liberarPlazaEnGroup(matricula);

         // Formaliza la petición
         sql = "UPDATE matricula " +
               "SET    estadoid          = " + Matricula.MATRICULA_ESTADO_ACEPTADA + ", " +
               "       fechamodificacion =     current_timestamp " +
               "WHERE  id       = " + matricula.getId();
         execute(sql);
         
         // Resta una plaza libre al grupo
         sql = "UPDATE grupo " +
               "SET    plazasdisponibles = plazasdisponibles - 1 " ;
               
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
    * Elimina un matricula.
    * 
    * @param id Identificador del matricula a eliminar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public void delete(int id) throws SQLException, Exception
   {
      String sql;
      
      try 
      {
         // PATCH xxxxx: No elimina ninguna matrícula, sólo la marca como anulada
         // Elimina la matricula
         /*sql = "DELETE FROM matriculas " +
               "WHERE matriculaid = " + id;*/

         // Marca la matrícula como anulada
         sql = "UPDATE matricula " +
               "SET estat = " + Matricula.MATRICULA_ESTADO_ANULADA +
               "WHERE id = " + id;  
         
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
    * Obtiene una lista completa de los grupos del centro.
    * 
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Matricula> getMatriculas() throws SQLException, Exception
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      
      sql = "SELECT   matricula.*, actividad.data_inici As fechaInicio, actividad.data_fi As fechaFin, " +
            "         grupo.nombre As grupoNombre, actividad.titol As actividadNombre, usuari.noms As usrNombre, " +
            "         usuari.cognoms As usrApellidos, usuario.nombre_doc_identif as usrNif " +
            "FROM     matricula, usuari, grupo, actividad " +
            "WHERE    matricula.usuari_id = usuari.usuari_id And " +
                     "matricula.grupoid = grupo.grupoid And " + 
                     "grupo.idactividad = actividad.id " + 
            "ORDER BY usrApellidos, usrNombre";

      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            matricula = new Matricula();
            getMatriculaValues(matricula, rs);
            list.add(matricula);
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
    * Obtiene una lista completa de los grupos del centro.
    * 
    * @param name Una cadena que contiene parte del nombre y/o apellidos para filtrar.
    * @param nif Una cadena que contiene parte del nif para filtrar.
    * @param estado Un estado para filtrar o -1 para ignorar el filtro.
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws RemoteException
    * @throws Exception 
    */
   public ArrayList<Matricula> getMatriculas(String name, String nif, int estado, Date fechainicio, Date fechafin) throws SQLException, Exception, RemoteException
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      
      // Formatea los parámetros proporcionados
      name = StringUtils.formatToSql(name.trim().toLowerCase());
      nif = StringUtils.formatToSql(nif.trim().toLowerCase());
      
      // Genera la senténcia SQL de selección de matriculas
      sql = "SELECT   matricula.*, actividad.data_inici As fechaInicio, actividad.data_fi As fechaFin, " +
            "         grupo.nombre As grupoNombre, actividad.titol As actividadNombre, usuari.noms As usrNombre, " +
            "         usuari.cognoms As usrApellidos, usuari.nombre_doc_identif as usrNif " +
            "FROM     matricula, usuari, grupo, actividad " +
            "WHERE    matricula.usuari_id = usuari.usuari_id And " +
                     "matricula.grupoid = grupo.grupoid And " + 
                     "grupo.idactividad = actividad.id ";

      // Aplica los filtros especificados como parámetro
      if (!name.equals(""))
      {
         sql += " And ";
         sql += "(lower(textcat(textcat(usuari.noms, ' '), usuari.cognoms)) Like '%" + name + "%' Or ";
         sql += " lower(textcat(textcat(usuari.cognoms, ', '), usuari.noms)) Like '%" + name + "%' Or ";
         sql += " lower(usuari.noms) Like '%" + name + "%' Or ";
         sql += " lower(usuaris.cognoms) Like '%" + name + "%') ";
      }
      if (!nif.equals(""))
      {
         sql += " And ";
         sql += "(lower(usuari.nombre_doc_idenfit) Like '%" + nif + "%') ";
      }   
      if (estado >= Matricula.MATRICULA_ESTADO_BAJA && estado <= Matricula.MATRICULA_ESTADO_ANULADA)
      {
         sql += " And ";
         sql += "(matricula.estat = " + estado + ") ";
      }
      if(fechainicio != null && fechafin != null)          
      {
         sql += " And ";
         sql += "(actividad.data_inici >= '" + fechainicio + "') ";
         sql += " And ";
         sql += "(actividad.data_fi <= '" + fechafin + "') ";
      }
              
      // Genera la secuencia de ordenación de los resultados
      sql += "ORDER BY usrApellidos, usrNombre";

      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            matricula = new Matricula();
            getMatriculaValues(matricula, rs);
            list.add(matricula);
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
    * Obtiene una lista completa de los Actividad de un alumno.
    *
    * @param id Identificador del Alumno que se quiere mostrar actividads.
    * @param op Opción escogida de tipo de actividads a mostrar
    * @param inici Fecha de inicio de la busqueda
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
    public ArrayList<Matricula> getMatriculasAlumno(int idAlumno, int op, java.util.Date inici) throws SQLException, Exception
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      Date hoy = new Date(System.currentTimeMillis());
      
         sql= "select actividad.titol, " + 
              "actividad.minasistencia, " + 
              "actividad.data_inici, " +
              "actividad.data_fi, " +
              "sum(asistencia.totalasisten)as totalasisten, " +
              "sum(asistencia.totalnoasisten) as totalnoasisten " +
              "FROM matricula, usuari, grupo, actividad, asistencia, asistenciaalumno " + 
              "where matricula.usuari_id = usuari.usuari_id " +
              "And matricula.grupoid = grupo.grupoid " +
              "AND grupo.idactividad = actividad.id " +
              "AND asistenciaalumno.alumnoid = usuari.usuari_id " +
              "AND asistenciaalumno.asistid = asistencia.asistid " +
              "AND asistencia.grupoid = grupo.grupoid " +
              "And usuari.usuari_id=" + idAlumno;
      
      if(inici!=null){
          sql=sql+" And actividad.data_inici >= '" + df.format(new java.sql.Date(inici.getTime())) + "'";
      }
         
      
         if(op==1){
          sql=sql+" And actividad.data_fi <= '" + hoy + "'";
          
      }
      
      if(op==2){
          
          sql=sql+" And actividad.data_fi > '" + hoy + "'";
          
      }
      
      sql=sql+" group by actividad.titol,actividad.minasistencia,actividad.data_inici,actividad.data_fi";
     
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            matricula = new Matricula();
            matricula.setActividadNombre(rs.getString("nombre"));
            matricula.setFechaInicio(rs.getDate("data_inici"));
            matricula.setFechaFinal(rs.getDate("data_fi"));
            matricula.setAsis(rs.getInt("totalasisten"));
            matricula.setNoAsis(rs.getInt("totalnoasisten"));
            matricula.setAsisRequerida(rs.getInt("minasistencia"));
            

            list.add(matricula);
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
    * Obtiene una lista completa de los alumnos de un profesor con su asistencia
    * @param id codigo identificador del profesor
    * @param inici fecha de inicio
    * @param actividads listado de actividads seleccionados
    * @return Una lista de instancia de {@link Matricula}.
    * @throws SQLException
    * @throws Exception 
    */ 
   public ArrayList<Matricula> getAlumnosProfesor(int id, java.util.Date inici, ArrayList<Actividad> actividades) throws SQLException, Exception
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      Date hoy = new Date(System.currentTimeMillis());
         sql= "select actividad.titol As nombreActividad, " + 
              "actividad.minasistencia, " + 
              "actividad.data_inici, " +
              "actividad.data_fi, " +
              "(usuari.cognoms ||', '|| usuari.noms) as nombreUsuario, " +  
              "sum(asistencia.totalasisten)as totalasisten, " +
              "sum(asistencia.totalnoasisten) as totalnoasisten " +
              "FROM matricula, usuari, grupo, actividad, asistencia, asistenciaalumno " + 
              "where matricula.usuari_id = usuari.usuari_id " +
              "And matricula.grupoid = grupo.grupoid " +
              "AND grupo.idactividad = actividad.id " +
              "AND asistenciaalumno.alumnoid = usuari.usuari_id " +
              "AND asistenciaalumno.asistid = asistencia.asistid " +
              "AND asistencia.grupoid = grupo.grupoid " +
              "And grupo.idprofesor=" + id;
      
      if(inici!=null){
          sql=sql+" And actividad.data_inici >= '" + df.format(new java.sql.Date(inici.getTime())) + "'";
      }
         
      
      if(actividades!=null)
      {
          sql=sql+" And (";
          for( int i = 0 ; i < actividades.size() ; i++ ){
                
          sql = sql + " actividad.titol = '" + actividades.get( i ).getTitol() + "'";
           
            if (i == actividades.size() - 1) 
            {
               sql = sql + " )";
            }
            else 
            {
               sql = sql + " OR ";
            }
         }
      }
      
      sql=sql+"group by nombreUsuario,actividad.titol,actividad.minasistencia,actividad.data_inici,actividad.data_fi order by nombreActividad";
      
     Usuario usr=new Usuario();
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            matricula = new Matricula();
            usr.setApellidos(rs.getString("nombreUsuario"));
            matricula.setActividadNombre(rs.getString("nombreActividad"));
            matricula.setFechaInicio(rs.getDate("data_inici"));
            matricula.setFechaFinal(rs.getDate("data_fi"));
            matricula.setAsis(rs.getInt("totalasisten"));
            matricula.setNoAsis(rs.getInt("totalnoasisten"));
            matricula.setAsisRequerida(rs.getInt("minasistencia"));
            matricula.setUsuarioNombre(usr.getApellidos());

            list.add(matricula);
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
    * Obtiene una lista completa de la asistencia de los actividads que imparte clase un profesor
    * @param id codigo identificador del profesor
    * @param inici fecha de inicio
    * @param actividads listado de actividads seleccionados
    * @return Una lista de instancia de {@link Matricula}.
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Matricula> getAsistenciaActividades(int id, java.util.Date inici, ArrayList<Actividad> actividades) throws SQLException, Exception
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date hoy = new Date(System.currentTimeMillis());
      
      sql = "select actividad.titol As nombreActividad, " + 
                "actividad.minasistencia, " +
                "actividad.data_inici, " +
                "actividad.data_fi, " + 
                "sum(asistencia.totalasisten) as asistenciatotal, " +
                "sum(asistencia.totalnoasisten) as absencia " +
                "FROM matricula, usuari, grupo, actividad, asistencia, asistenciaalumno " +
                "where matricula.usuari_id = usuari.usuari_id " +
                "And matricula.grupoid = grupo.grupoid " +
                "AND grupo.idactividad = actividad.id " +
                "AND asistenciaalumno.alumnoid = usuario.id " +
                "AND asistenciaalumno.asistid = asistencia.asistid " +
                "AND asistencia.grupoid = grupo.grupoid " +
                "And grupo.idprofesor=" + id;
      
      if (inici!=null)
      {
          sql = sql + " And actividad.data_inici >= '" + df.format(new Date(inici.getTime())) + "'";
      }
      
      if (actividades!=null)
      {
         sql = sql + " And (";
         for (int i = 0; i < actividades.size() ; i++ )
         {
            sql = sql + " actividad.titol = '" + actividades.get( i ).getTitol() + "'";

            if (i==actividades.size()-1) 
            {
               sql = sql +  " ) ";
            }
            else
            {
               sql = sql + " OR ";
            }
         }
      }
      
      sql=sql+" group by nombreActividad,actividad.minasistencia,actividad.data_inici,actividad.data_fi " +
              "order by nombreActividad";
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            matricula = new Matricula();
            matricula.setActividadNombre(rs.getString("nombreActividad"));
            matricula.setFechaInicio(rs.getDate("data_inici"));
            matricula.setFechaFinal(rs.getDate("data_fi"));
            matricula.setAsis(rs.getInt("asistenciatotal"));
            matricula.setNoAsis(rs.getInt("absencia"));
            matricula.setAsisRequerida(rs.getInt("minasistencia"));

            list.add(matricula);
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
    * Obtiene una lista completa de la asistencia media de los profesores
    * @param inici fecha de inicio
    * @param actividads listado de actividads seleccionados
    * @return Una lista de instancia de {@link Matricula}.
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Matricula> getAsistenciaProfesores(java.util.Date inici, ArrayList<Actividad> actividades) throws SQLException, Exception
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date hoy = new Date(System.currentTimeMillis());
      
      sql = "select sum(asistencia.totalasisten) as asistenciatotal, " + 
                "sum(asistencia.totalnoasisten) as absencia, " +
                "(usuari.cognoms ||', '|| usuari.noms) as nombreUsuario " +
                "FROM actividad, asistencia, asistenciaalumno, grupo inner join usuari on usuari.usuari_id = grupo.idprofesor " + 
                "where grupo.idactividad = actividad.id  " +
                "AND asistenciaalumno.asistid = asistencia.asistid " +
                "AND asistencia.grupoid = grupo.grupoid ";
      
      if (inici!=null)
      {
          sql = sql + " And actividad.data_inici >= '" + df.format(new Date(inici.getTime())) + "'";
      }
      
      if (actividades!=null)
      {
         sql = sql + " And (";
         for (int i = 0; i < actividades.size() ; i++ )
         {
            sql = sql + " actividad.titol = '" + actividades.get( i ).getTitol() + "'";

            if (i==actividades.size()-1) 
            {
               sql = sql +  " ) ";
            }
            else
            {
               sql = sql + " OR ";
            }
         }
      }
      
      sql=sql+" group by nombreUsuario " +
              "order by nombreUsuario";
     
     Usuario usr=new Usuario();
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            usr.setApellidos(rs.getString("nombreUsuario"));
            matricula = new Matricula();
            matricula.setUsuarioNombre(usr.getApellidos());
            matricula.setAsis(rs.getInt("asistenciatotal"));
            matricula.setNoAsis(rs.getInt("absencia"));
            list.add(matricula);
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
    * Obtiene una lista completa de la asistencia de los actividades que imparte el centro
    * @param inici fecha de inicio
    * @param actividades listado de actividades seleccionados
    * @return Una lista de instancia de {@link Matricula}.
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Matricula> getAsistenciaActividadCentro(java.util.Date inici, ArrayList<Actividad> actividades) throws SQLException, Exception
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date hoy = new Date(System.currentTimeMillis());
      
      sql = "select actividad.minasistencia, " + 
                "sum(asistencia.totalasisten) as totalAsis, " +
                "sum(asistencia.totalnoasisten) as totalAbsencia, " +
                "actividad.titol as nombreActividad, " + 
                "(usuari.cognoms ||', '|| usuari.noms) as nombreUsuario  " +
                "FROM actividad, asistencia, asistenciaalumno, grupo inner join usuari on usuari.usuari_id = grupo.idprofesor " +
                "where grupo.idactividad = actividad.id "+
                "AND asistenciaalumno.asistid = asistencia.asistid "+
                "AND asistencia.grupoid = grupo.grupoid ";
      
      if (inici!=null)
      {
          sql = sql + " And activdad.data_inici >= '" + df.format(new Date(inici.getTime())) + "'";
      }
      
      if (actividades!=null)
      {
         sql = sql + " And (";
         for (int i = 0; i < actividades.size() ; i++ )
         {
            sql = sql + " actividad.titol = '" + actividades.get( i ).getTitol() + "'";

            if (i==actividades.size()-1) 
            {
               sql = sql +  " ) ";
            }
            else
            {
               sql = sql + " OR ";
            }
         }
      }
      
      sql=sql+" group by actividad.titol,nombreUsuario,actividad.minasistencia " +
              "order by actividad.titol";
     
     Usuario usr=new Usuario();
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            
            usr.setApellidos(rs.getString("nombreUsuario"));
            matricula = new Matricula();
            matricula.setActividadNombre(rs.getString("nombreActividad"));
            matricula.setUsuarioNombre(usr.getApellidos());
            matricula.setAsis(rs.getInt("totalAsis"));
            matricula.setNoAsis(rs.getInt("totalAbsencia"));
            list.add(matricula);
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
    * Obtiene una lista completa de actividades i grupos de loa alumnos
    * @param inici fecha de inicio
    * @param nif nif del alumno
    * @return Una lista de instancia de {@link Matricula}.
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Matricula> getActividadesGruposAlumnos(java.util.Date inici, String nif) throws SQLException, Exception
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date hoy = new Date(System.currentTimeMillis());
      
      sql = "select usuari.usuari_id as usuarioId, " +
                "usuari.nombre_doc_identif as nif, " + 
                "matricula.id as matriculaId, " +
                "grupo.turno as turno, " +
                "(usuari.cognoms ||', '|| usuari.noms) as nombreUsuario, " +
                "actividad.titol as nombreActividad, " +
                "grupo.nombre as nombregrupo, " + 
                "actividad.data_inici,  " +
                "actividad.data_fi " +
                "from usuari,actividad,grupo,matricula " +
                "where grupo.idactividad=actividad.id " +
                "AND matricula.grupoid=grupo.grupoid " +
                "AND matricula.usuari_id=usuari.usuari_id ";

      if (nif != null) 
      {
         sql = sql + "AND usuari.nombre_doc_identif='" + nif + "'";
      }
      if (inici != null) 
      {
         sql = sql + " And actividad.data_inici >= '" + df.format(new Date(inici.getTime())) + "'";
      }

     Usuario usr=new Usuario();
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            
            usr.setApellidos(rs.getString("nombreUsuario"));
            matricula = new Matricula();
            matricula.setUsuarioId(rs.getInt("usuari_Id"));
            matricula.setIdMatricula(rs.getInt("matriculaId"));
            matricula.setTurno(rs.getInt("turno"));
            matricula.setFechaInicio(rs.getDate("data_inici"));
            matricula.setFechaFinal(rs.getDate("data_fi"));
            matricula.setActividadNombre(rs.getString("nombreActividad"));
            matricula.setGrupoNombre(rs.getString("nombregrupo"));
            matricula.setUsuarioNif(rs.getString("nif"));
            matricula.setUsuarioNombre(usr.getApellidos());
            list.add(matricula);
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
    * Obtiene una lista completa de asistencia por horas.
    * 
    * @param inici hora de inicio
    * @param fi hora fin
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Matricula> getAsistenciaHoras (java.sql.Time inici, java.sql.Time fi) throws SQLException, Exception
   {
      Matricula matricula;
      String sql;
      ArrayList<Matricula> list = new ArrayList<Matricula>();
      
      sql = "select horainicio, horafin, " +
                "sum(totalasisten) as asisten, " + 
                "sum(totalnoasisten) as noasisten " +
                "from asistencia ";
                
      
      if(inici!=null)sql=sql+"where horainicio>='"+ inici +"'";
      if (fi!=null)sql = sql + " And horainicio <= '" + fi + "'";
      sql=sql+" group by horainicio, horafin";
 
      try
      {
         ResultSet rs = executeSql(sql);
         while (rs.next()) 
         {
            matricula = new Matricula();
            matricula.setHoraInicio(rs.getTime("horainicio"));
            matricula.setAsis(rs.getInt("asisten"));
            matricula.setNoAsis(rs.getInt("noasisten"));
            list.add(matricula);
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
   
   /**
    * Lee la información de un grupo desde una fila de un {@linl ResultSet}.
    * 
    * @param matricula
    * @param rs
    * 
    * @throws SQLException 
    */
   private void getMatriculaValues(Matricula matricula, ResultSet rs) throws SQLException
   {
      matricula.setId(rs.getInt("matriculaid"));
      matricula.setactividadId(rs.getInt("actividadid"));
      
      matricula.setEstado(rs.getInt("estadoid"));
      matricula.setUsuarioId(rs.getInt("usuarioid"));
      matricula.setFechaModificacion(rs.getDate("fechamodificacion"));
      matricula.setFechaAlta(rs.getDate("fechaalta"));
            
      // Propiedades de sólo lectura (para listados)
      matricula.setGrupoNombre(rs.getString("grupoNombre"));
      matricula.setActividadNombre(rs.getString("actividadNombre"));
      matricula.setUsuarioNombre(rs.getString("usrApellidos") + ", " + rs.getString("usrNombre"));
      matricula.setUsuarioNif(rs.getString("usrNif"));
      matricula.setFechaInicio(rs.getDate("fechainicio"));
      matricula.setFechaFinal(rs.getDate("fechafin"));
   }
   
   /**
    * Si la matricula previmanet estaba aceptada en otro grupo, libera la plaza en el grupo antiguo.
    * 
    * @param id Identificador de la matrícula original (antes de ninguna modificación).
    * 
    * @throws SQLException
    * @throws Exception 
    */
   private void liberarPlazaEnGroup(Matricula matricula) throws SQLException, Exception
   {
      String sql;
      Matricula oldreg;

      try 
      {
         oldreg = this.get(matricula.getId());
         if (oldreg.getEstado() == Matricula.MATRICULA_ESTADO_ACEPTADA )
         {
            sql = "UPDATE grupo " +
                  "SET    plazasdisponibles = plazasdisponibles + 1 ";
                  
            execute(sql);
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
   }

    public int getInscritosByActividad(int actividadId) throws SQLException, Exception{

      String sql;
      
      sql = "select count(*) inscritos from matricula where activitat_id =  " + actividadId;
                
      try {
         ResultSet rs = executeSql(sql);
         if(rs.next()) {
            return rs.getInt("inscritos");
         } else {
             return 0;
         }
      }
      catch (SQLException ex) 
      {
          return 0;
      } 
      catch (Exception ex) 
      {
          return 0;
      }
     
    }
}
