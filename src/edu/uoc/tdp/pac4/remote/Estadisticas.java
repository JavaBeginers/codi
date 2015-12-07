package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Curso;
import edu.uoc.tdp.pac4.beans.Matricula;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Interfaces que deben cumplir las implementaciones del subsistema de estadísticas
 * 
 * @author eSupport Netbeans
 */  
public interface Estadisticas extends Remote 
{
    
   /**
    * Obtiene una lista completa de las matrículas de un alumno.
    * @param id identificador alumno.
    * @param op opcion escogida del raioButton.
    * @param data data minima de matricula curso.
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    */
ArrayList<Matricula> consultarCursosAlumno(int id,int op, Date data) throws SQLException, Exception;

   /**
    * Obtiene una lista completa de los cursos de un profesor.
    * @param login login del profesor.
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */

ArrayList<Curso> consultarCursosProfesor(String login) throws RemoteException, SQLException, Exception;

  /**
    * Obtiene una lista completa de los cursos del centro.
    * @param login login del profesor.
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */



ArrayList<Curso> consultarCursos() throws RemoteException, SQLException, Exception;


  /**
    * Obtiene una lista completa de las alumno que imparte clase un profesor.
    * @param id identificador profesor.
    * @param data data minima de matricula curso.
    * @param cursos listado de cursos.
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */

ArrayList<Matricula> consultarAlumnosProfesor(int id, Date data, ArrayList<Curso> cursos) throws RemoteException, SQLException, Exception;

 /**
    * Obtiene una lista completa de las estadísticas de asistencia en los cursos de un profesor.
    * @param id identificador profesor.
    * @param data data minima de matricula curso.
    * @param cursos listado de cursos.
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */

ArrayList<Matricula> consultarEstCursProfesor(int id, Date data, ArrayList<Curso> cursos) throws RemoteException, SQLException, Exception;


 /**
    * Obtiene una lista de los profesores con la asistencia a sus cursos.
    * @param data data minima de matricula curso.
    * @param cursos listado de cursos.
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */

ArrayList<Matricula> consultarEstProfesores(Date data, ArrayList<Curso> cursos) throws RemoteException, SQLException, Exception;

/**
    * Obtiene una lista los cursos impartidos por el centro con la asistencia de cada curso.
    * @param data data minima de matricula curso.
    * @param cursos listado de cursos.
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */

ArrayList<Matricula> consultarEstCursosCentro(Date data, ArrayList<Curso> cursos) throws RemoteException, SQLException, Exception;

/**
    * Obtiene una lista los cursos solapados.
    * @param data data minima de matricula curso.
    * @param nif nif del alumno.
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */

ArrayList<Matricula> consultarSolapados(Date data, String nif) throws RemoteException, SQLException, Exception;

/**
    * Obtiene una lista con la asistencia por horas.
    * @param inici hora de inicio.
    * @param fi hora de fin.
    * @return Una lista de instancia de {@link Matricula}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */

ArrayList<Matricula> consultarHoras(java.sql.Time inici, java.sql.Time fi) throws RemoteException, SQLException, Exception;
}
