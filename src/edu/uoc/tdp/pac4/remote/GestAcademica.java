package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Alumno;
import edu.uoc.tdp.pac4.beans.Asistencia;
import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Grupo;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.beans.Profesor;
import edu.uoc.tdp.pac4.exceptions.GroupAlreadyCountedException;
import edu.uoc.tdp.pac4.exceptions.GroupAlreadyExistsException;
import edu.uoc.tdp.pac4.exceptions.GroupNotEmptyException;
import edu.uoc.tdp.pac4.exceptions.NoGroupFoundException;
import edu.uoc.tdp.pac4.exceptions.NotAvailableProfessorException;
import edu.uoc.tdp.pac4.exceptions.StudentNotExistsException;
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Interface que deben cumplir las implementaciones del subsistema de gestión académica.
 * 
 * @author eSupport Netbeans
 */
public interface GestAcademica extends Remote 
{
   /**
    * Obtiene una lista completa de los actividades del centro.
    * 
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Actividad> getActividades() throws SQLException, Exception;
   
   /**
    * Obtiene una lista completa de los actividades del centro.
    * 
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   Actividad getActividad(int id) throws SQLException, Exception;
   
   /**
    * Devuelve el número de plazas disponibles para un determinado actividad y turno (en función de las matriculas aceptadas).
    * 
    * @param idActividad Identificador único del actividad.
    * @param turno Código del turno.
    * @return El número de plazas disponibles en un grupo y turno.
    */
   int getPlazasDisponibles(int idActividad, int turno) throws NoGroupFoundException, SQLException, Exception;
   
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
   void addGrupo(Grupo grupo) throws SQLException, GroupAlreadyExistsException, NotAvailableProfessorException, Exception;
   
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
   void updateGrupo(Grupo grupo) throws SQLException, GroupAlreadyExistsException, NotAvailableProfessorException, Exception;
   
   /**
    * Elimina un grupo.
    * 
    * @param id Identificador del grupo a eliminar.
    * 
    * @throws SQLException
    * @throws GroupNotEmptyException
    * @throws Exception 
    */
   void deleteGrupo(int id) throws SQLException, GroupNotEmptyException, Exception;
   
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
    * Obtiene una lista de los grupos del centro correspondientes a un determinado actividad.
    * 
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Grupo> getGrupos(int idActividad) throws SQLException, Exception;
   
   /**
    * Obtiene una lista (filtrada) de los grupos del centro.
    * 
    * @param idTurno Identificador del actividad para el que se desea filtrar ({@code -1} para ignorar).
    * @param idTurno Identificador del turno para el que se desea filtrar ({@code -1} para ignorar).
    * @param fechaInicio Fecha de inicio para la que se desea filtrar ({@code null} para ignorar).
    * @param fechaFin Fecha de finalización para la que se desea filtrar ({@code null} para ignorar).
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Grupo> getGrupos(int idActividad, int idTurno, Date fechaInicio, Date fechaFin) throws SQLException, Exception;
   
   /**
    * Obtiene una lista de los grupos del centro correspondientes a un determinado profesor.
    * 
    * @param idUsuario Identificador del profesor (usuario).
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Grupo> getGruposByProfesor(int idUsuario) throws SQLException, Exception;
   
   /**
    * Obtiene un grupo.
    * 
    * @param id Identificador único del grupo.
    * @return Una instancia de {@link Grupo} que contiene los datos del grupo solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   Grupo getGrupo(int id) throws SQLException, Exception;
    
    // void updateGrupo(Grupo grupo);
   
   /**
    * Recupera la lista de aulas.
    * 
    * @return Una lista de instancias de {@link Aula} que representan todas las aulas del centro.
    * 
    * @throws SQLException
    * @throws Exception
    */
   ArrayList<Aula> getAulas() throws SQLException, Exception;
   
   /**
    * Recupera la información de una aula.
    * 
    * @param id Identificador único de la aula.
    * @return Una instancia de {@link Aula} que representa la aula solicitada.
    * 
    * @throws SQLException
    * @throws Exception
    */
   Aula getAula(int id) throws SQLException, Exception;
   
   /**
    * Devuelve una lista de profesores del centro.
    * 
    * @return Una lista de instancias {@link Profesor} que representan los profesores del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Profesor> getProfesores() throws SQLException, Exception;
   
   /**
    * Obtiene un determinado profesor.
    * 
    * @param  id Identificador único del profesor.
    * @return Una instancia de {@link Profesor} que representa el profesor solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   Profesor getProfesor(int id) throws SQLException, Exception;
   
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
    * Agrega una nueva matricula al centro.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   void addMatricula(Matricula matricula) throws SQLException, Exception;
   
   /**
    * Actualiza los datos de una matricula.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos actualizados del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   void updateMatricula(Matricula matricula) throws SQLException, Exception;
   
   /**
    * Elimina un matricula.
    * 
    * @param id Identificador del matricula a eliminar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   void deleteMatricula(int id) throws SQLException, Exception;
   
   /**
    * Acepta un matricula para un alumno y grupo.<br />
    * Este método actualiza el número de plazas disponibles en el grupo.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos actualizados del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   void aceptarMatricula(Matricula matricula) throws SQLException, Exception;
   
   /**
    * Obtiene una lista completa de los grupos del centro.
    * 
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Matricula> getMatriculas() throws SQLException, Exception;
   
   /**
    * Obtiene una lista completa de los grupos del centro.
    * 
    * @param name Una cadena que contiene parte del nombre y/o apellidos para filtrar.
    * @param nif Una cadena que contiene parte del nif para filtrar.
    * @param estado Un estado para filtrar o -1 para ignorar el filtro.
    * @return Una lista de instancia de {@link Actividad}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Matricula> getMatriculas(String name, String nif, int estado) throws SQLException, Exception;
   
   /**
    * Obtiene un determinado usuario.
    * 
    * @param  id Identificador único del usuario.
    * @return Una instancia de {@link Alumno} que representa el usuario solicitado.
    * 
    * @throws SQLException
    * @throws StudentNotExistsException
    * @throws Exception 
    */
   Alumno getAlumno(int id) throws SQLException, StudentNotExistsException, Exception;
   
   /**
    * Devuelve una lista de usuarios del centro.
    * 
    * @return Una lista de instancias {@link Alumno} que representan los usuarios del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   ArrayList<Alumno> getAlumnos() throws SQLException, Exception;

}
