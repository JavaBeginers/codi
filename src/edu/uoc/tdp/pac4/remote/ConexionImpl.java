package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Asistencia;
import edu.uoc.tdp.pac4.beans.Curso;
import edu.uoc.tdp.pac4.beans.Grupo;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.dao.GestorAsistencia;
import edu.uoc.tdp.pac4.dao.GestorConexion;
import edu.uoc.tdp.pac4.dao.GestorCurso;
import edu.uoc.tdp.pac4.dao.GestorDisco;
import edu.uoc.tdp.pac4.dao.GestorGrupo;
import edu.uoc.tdp.pac4.dao.GestorMatricula;
import edu.uoc.tdp.pac4.exceptions.AssistanceAlreadyCountedException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Implementación del gestor del subsistema de Conexión y Matrícula
 * 
 * @author JavaBeginers
 */
public class ConexionImpl extends UnicastRemoteObject implements Conexion, Serializable 
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;
   
   GestorDisco gestorDisco;
   
   public ConexionImpl() throws RemoteException, Exception
   {
   
        super();

        try 
        {
            gestorDisco = new GestorDisco();
            gestorDisco.initConnection();
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
   @Override
   public ArrayList<Curso> getCursos() throws RemoteException, SQLException, Exception 
   {
      GestorCurso gc = new GestorCurso(gestorDisco.getConnection());
		return gc.getCursos();
   }
   
   /**
    * Obtiene una lista completa de los grupos del centro.
    * 
    * @return Una lista de instancia de {@link Grupo}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public ArrayList<Grupo> getGrupos() throws RemoteException, SQLException, Exception 
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
   @Override
   public ArrayList<Grupo> getGrupos(int idCurso) throws SQLException, Exception 
   {
      GestorGrupo gg = new GestorGrupo(gestorDisco.getConnection());
		return gg.getGrupos(idCurso);
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
   @Override
   public ArrayList<Grupo> getGrupos(int idCurso, int idTurno, Date fechaInicio, Date fechaFin) throws SQLException, Exception 
   {
      GestorGrupo gg = new GestorGrupo(gestorDisco.getConnection());
      return gg.getGrupos(idCurso, idTurno, fechaInicio, fechaFin);
   }
   
   /**
    * Obtiene una lista de @link Grupo donde un alumno está matriculado, filtrado por
    * curso y/o turno.
    * @param idUsuario
    * @param idCurso
    * @param idTurno
    * @return ArrayList<Grupo> con los grupos en cuestión
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Grupo> getGruposByMatricula(int idUsuario, int idCurso, int idTurno) throws SQLException, Exception
   {
       GestorGrupo gg = new GestorGrupo(gestorDisco.getConnection());
       return gg.getGruposByMatricula(idUsuario, idCurso, idTurno);
   }
   
   /**
    * Obtiene una lista completa de los grupos del centro.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public ArrayList<Matricula> getMatriculas() throws SQLException, Exception 
   {
      GestorMatricula gm = new GestorMatricula(gestorDisco.getConnection());
      return gm.getMatriculas();
   }

   /**
    * Obtiene una matricula.
    * 
    * @param id Identificador único de la matricula.
    * @return Una instancia de {@link Grupo} que contiene los datos del matricula solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public Matricula getMatricula(int id) throws SQLException, Exception 
   {
      GestorMatricula gm = new GestorMatricula(gestorDisco.getConnection());
      return gm.get(id);
   }

  /**
    * Obtiene una lista completa de las matrículas del centro. Filtrada.
    * 
    * @param name Una cadena que contiene parte del nombre y/o apellidos para filtrar.
    * @param nif Una cadena que contiene parte del nif para filtrar.
    * @param estado Un estado para filtrar o -1 para ignorar el filtro.
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public ArrayList<Matricula> getMatriculas(String name, String nif, int estado, Date fechainicio, Date fechafin) throws SQLException, Exception 
   {
      GestorMatricula gm = new GestorMatricula(gestorDisco.getConnection());
      return gm.getMatriculas(name, nif, estado, fechainicio, fechafin);
   }

  /**
    * Obtiene la última asistencia registrada para el conjunto del @link @Grupo
    * @param grupoid
    * @return la última entrada de asistencia para el grupo. Null si no se encuentra.
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public Asistencia getLastAssistance(int grupoid) throws SQLException, Exception 
   {
      GestorAsistencia gm = new GestorAsistencia(gestorDisco.getConnection());
      return gm.getLastAssistance(grupoid);
   }

   
  /**
    * Agrega una nueva matricula al centro.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public void addMatricula(Matricula matricula) throws SQLException, Exception 
   {
      GestorMatricula gm = new GestorMatricula(gestorDisco.getConnection());
      gm.add(matricula);
   }
   
  /**
    * Añade un registro referente a una @link Asistencia de un alumno
    * @param asistid
    * @param alumnoid
    * @throws SQLException
    * @throws AssistanceAlreadyCountedException
    * @throws Exception 
    */
   public void addAsistenciaAlumno(int asistid, int alumnoid) throws SQLException, AssistanceAlreadyCountedException, Exception {
      
      GestorAsistencia gm = new GestorAsistencia(gestorDisco.getConnection());
      gm.addAsistenciaAlumno(asistid, alumnoid);
      
   }
   
   /**
    * Valida la conexión de un usuario al sistema
    * 
    * @return Datos básicos de usuario. Si está vacío se deniega el acceso.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public Usuario validaUsuario(String login, String password) throws RemoteException, SQLException, Exception 
   {
      GestorConexion gc = new GestorConexion(gestorDisco.getConnection());      
      return gc.validaUsuario(login, password);
   }
   
}

