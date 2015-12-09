package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Asistencia;
import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Grupo;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.exceptions.AssistanceAlreadyCountedException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Interfaces que deben cumplir las implementaciones del subsistema de conexión
 * y Matrícula
 * 
 * @author JavaBeginers
 */  
public interface Conexion extends Remote 
{
   
   /**
    * Obtiene una matricula.
    * 
    * @param id Identificador único de la matricula.
    * @return Una instancia de {@link Grupo} que contiene los datos del matricula solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */    
   Matricula getMatricula(int id) throws SQLException, Exception;
   
   /**
    * Obtiene una lista completa de las matrículas del centro.
    * 
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Matricula> getMatriculas() throws SQLException, Exception;
   
   /**
    * Obtiene una lista completa de las matrículas del centro.
    * 
    * @param name Una cadena que contiene parte del nombre y/o apellidos para filtrar.
    * @param nif Una cadena que contiene parte del nif para filtrar.
    * @param estado Un estado para filtrar o -1 para ignorar el filtro.
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Matricula> getMatriculas(String name, String nif, int estado, Date fechainicio, Date fechafin) throws SQLException, Exception;
   
  /**
    * Obtiene la última asistencia registrada para el conjunto del @link @Grupo
    * @param grupoid
    * @return la última entrada de asistencia para el grupo. Null si no se encuentra.
    * @throws SQLException
    * @throws Exception 
    */  
   Asistencia getLastAssistance(int grupoid) throws SQLException, Exception; 
   
  /**
    * Agrega una nueva matricula al centro.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   void addMatricula(Matricula matricula) throws SQLException, Exception;
   
   /**
    * Añade un registro referente a una @link Asistencia de un alumno
    * @param asistid
    * @param alumnoid
    * @throws SQLException
    * @throws AssistanceAlreadyCountedException
    * @throws Exception 
    */
   void addAsistenciaAlumno(int asistid, int alumnoid) throws SQLException, AssistanceAlreadyCountedException, Exception;
   
   /**
    * Obtiene una lista completa de los grupos del centro.
    * 
    * @return Una lista de instancia de {@link Grupo}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Grupo> getGrupos() throws SQLException, Exception;
   
   /**
    * Obtiene una lista de los grupos del centro correspondientes a un determinado curso.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Grupo> getGrupos(int idCurso) throws SQLException, Exception;
   
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
   ArrayList<Grupo> getGrupos(int idCurso, int idTurno, Date fechaInicio, Date fechaFin) throws SQLException, Exception;
   
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
   ArrayList<Grupo> getGruposByMatricula(int idUsuario, int idCurso, int idTurno) throws SQLException, Exception;
   
    /**
    * Obtiene una lista completa de los cursos del centro.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Actividad> getActividades() throws SQLException, Exception;
    
    /**
    * Valida la conexión de un usuario al sistema
    * 
    * @return Datos básicos de usuario. Si está vacío se deniega el acceso.
    * 
    * @throws SQLException
    * @throws Exception 
    */
    Usuario validaUsuario(String login, String password) throws SQLException, Exception;
}
