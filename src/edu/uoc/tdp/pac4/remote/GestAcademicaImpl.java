package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Alumno;
import edu.uoc.tdp.pac4.beans.Asistencia;
import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Grupo;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.beans.Profesor;
import edu.uoc.tdp.pac4.dao.GestorAlumno;
import edu.uoc.tdp.pac4.dao.GestorAsistencia;
import edu.uoc.tdp.pac4.dao.GestorAulas;
import edu.uoc.tdp.pac4.dao.GestorActividad;
import edu.uoc.tdp.pac4.dao.GestorDisco;
import edu.uoc.tdp.pac4.dao.GestorGrupo;
import edu.uoc.tdp.pac4.dao.GestorMatricula;
import edu.uoc.tdp.pac4.dao.GestorProfesor;
import edu.uoc.tdp.pac4.exceptions.GroupAlreadyCountedException;
import edu.uoc.tdp.pac4.exceptions.GroupAlreadyExistsException;
import edu.uoc.tdp.pac4.exceptions.GroupNotEmptyException;
import edu.uoc.tdp.pac4.exceptions.NoGroupFoundException;
import edu.uoc.tdp.pac4.exceptions.NotAvailableProfessorException;
import edu.uoc.tdp.pac4.exceptions.StudentNotExistsException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Implementación del gestor del subsistema de Gestión Académica.
 * 
 * @author JavaBeginers
 */
public class GestAcademicaImpl extends UnicastRemoteObject implements GestAcademica, Serializable
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;

   GestorDisco gestorDisc;
   
   /**
    * Constructor de la clase.
    * 
    * @throws RemoteException
    * @throws Exception 
    */
   public GestAcademicaImpl() throws RemoteException, Exception
   {
      super();

      try 
      {
         gestorDisc = new GestorDisco();
         gestorDisc.initConnection();
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
   @Override
   public ArrayList<Actividad> getActividades() throws RemoteException, SQLException, Exception 
   {
      GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
		return gc.getActividades();
   }

   /**
    * Obtiene una lista completa de los cursos del centro.
    * 
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public Actividad getActividad(int id) throws SQLException, Exception
   {
      GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
      return gc.get(id);
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
      GestorGrupo gg = new GestorGrupo(gestorDisc.getConnection());
		return gg.getGrupos(idCurso);
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
   @Override
   public void addGrupo(Grupo grupo) throws SQLException, GroupAlreadyExistsException, NotAvailableProfessorException, Exception 
   {
      GestorGrupo gg = new GestorGrupo(gestorDisc.getConnection());
      gg.add(grupo);
   }

   /**
    * Obtiene un grupo.
    * 
    * @param id Identificador único del grupo.
    * @return Una instancia de {@link Grupo} que contiene los datos del grupo solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public Grupo getGrupo(int id) throws SQLException, Exception 
   {
      GestorGrupo gg = new GestorGrupo(gestorDisc.getConnection());
		return gg.get(id);
   }

   /**
    * Recupera la lista de aulas.
    * 
    * @return Una lista de instancias de {@link Aula} que representan todas las aulas del centro.
    * 
    * @throws SQLException
    * @throws Exception
    */
   @Override
   public ArrayList<Aula> getAulas() throws SQLException, Exception 
   {
      GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
		return ga.getAulas();
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
   @Override
   public Aula getAula(int id) throws SQLException, Exception 
   {
      GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
		return ga.get(id);
   }
   
   /**
    * Devuelve una lista de profesores del centro.
    * 
    * @return Una lista de instancias {@link Profesor} que representan los profesores del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public ArrayList<Profesor> getProfesores() throws SQLException, Exception 
   {
      GestorProfesor gp = new GestorProfesor(gestorDisc.getConnection());
		return gp.getProfesores();
   }

   /**
    * Obtiene un determinado profesor.
    * 
    * @param  id Identificador único del profesor.
    * @return Una instancia de {@link Profesor} que representa el profesor solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public Profesor getProfesor(int id) throws SQLException, Exception 
   {
      GestorProfesor gp = new GestorProfesor(gestorDisc.getConnection());
      return gp.get(id);
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
   @Override
   public void updateGrupo(Grupo grupo) throws SQLException, GroupAlreadyExistsException, NotAvailableProfessorException, Exception 
   {
      GestorGrupo gg = new GestorGrupo(gestorDisc.getConnection());
      gg.update(grupo);
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
   @Override
   public void deleteGrupo(int id) throws SQLException, GroupNotEmptyException, Exception 
   {
      GestorGrupo gg = new GestorGrupo(gestorDisc.getConnection());
      gg.delete(id);
   }

   /**
    * Elimina un matricula.
    * 
    * @param id Identificador del matricula a eliminar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public void deleteMatricula(int id) throws SQLException, Exception 
   {
      GestorMatricula gm = new GestorMatricula(gestorDisc.getConnection());
      gm.delete(id);
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
      GestorMatricula gm = new GestorMatricula(gestorDisc.getConnection());
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
      GestorMatricula gm = new GestorMatricula(gestorDisc.getConnection());
      return gm.get(id);
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
      GestorMatricula gm = new GestorMatricula(gestorDisc.getConnection());
      gm.add(matricula);
   }

   /**
    * Devuelve una lista de usuarios del centro.
    * 
    * @return Una lista de instancias {@link Alumno} que representan los usuarios del centro.
    * 
    * @throws SQLException
    * @throws StudentNotExistsException
    * @throws Exception 
    */
   @Override
   public ArrayList<Alumno> getAlumnos() throws SQLException, StudentNotExistsException, Exception 
   {
      GestorAlumno ga = new GestorAlumno(gestorDisc.getConnection());
      return ga.getAlumnos();
   }

   /**
    * Obtiene un determinado usuario.
    * 
    * @param  id Identificador único del usuario.
    * @return Una instancia de {@link Alumno} que representa el usuario solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public Alumno getAlumno(int id) throws SQLException, Exception 
   {
      GestorAlumno ga = new GestorAlumno(gestorDisc.getConnection());
      return ga.get(id);
   }

   /**
    * Actualiza los datos de una matricula.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos actualizados del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public void updateMatricula(Matricula matricula) throws SQLException, Exception 
   {
      GestorMatricula gm = new GestorMatricula(gestorDisc.getConnection());
      gm.update(matricula);
   }

   /**
    * Devuelve el número de plazas disponibles para un determinado curso y turno (en función de las matriculas aceptadas).
    * 
    * @param idCurso Identificador único del curso.
    * @param turno Código del turno.
    * @return El número de plazas disponibles en un grupo y turno.
    */
   @Override
   public int getPlazasDisponibles(int idCurso, int turno) throws NoGroupFoundException, SQLException, Exception 
   {
      GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
      return gc.getPlazasDisponibles(idCurso, turno);
   }

   /**
    * Obtiene una lista completa de los grupos del centro.
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
   public ArrayList<Matricula> getMatriculas(String name, String nif, int estado) throws SQLException, Exception 
   {
      GestorMatricula gm = new GestorMatricula(gestorDisc.getConnection());
      return gm.getMatriculas(name, nif, estado, null, null);
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
      GestorGrupo gg = new GestorGrupo(gestorDisc.getConnection());
      return gg.getGrupos(idCurso, idTurno, fechaInicio, fechaFin);
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
   @Override
   public ArrayList<Grupo> getGruposByProfesor(int idUsuario) throws SQLException, Exception 
   {
      GestorGrupo gg = new GestorGrupo(gestorDisc.getConnection());
      return gg.getGruposByProfesor(idUsuario);
   }

   /**
    * Acepta un matricula para un alumno y grupo.<br />
    * Este método actualiza el número de plazas disponibles en el grupo.
    * 
    * @param matricula Una instancia de {@link Grupo} que contiene los datos actualizados del matricula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public void aceptarMatricula(Matricula matricula) throws SQLException, Exception 
   {
      GestorMatricula gm = new GestorMatricula(gestorDisc.getConnection());
      gm.accept(matricula);
   }
}
