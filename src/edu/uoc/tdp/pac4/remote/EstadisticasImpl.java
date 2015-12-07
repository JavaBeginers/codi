package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Curso;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.dao.GestorCurso;
import edu.uoc.tdp.pac4.dao.GestorDisco;
import edu.uoc.tdp.pac4.dao.GestorMatricula;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author eSupport Netbeans
 */
public class EstadisticasImpl extends UnicastRemoteObject implements Estadisticas, Serializable 
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;
   
   GestorDisco gestorDisc;
   
   public EstadisticasImpl() throws RemoteException, Exception
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

    @Override
    
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
    
    public ArrayList<Matricula> consultarCursosAlumno(int id,int op, Date data ) throws SQLException, Exception {
        
        
      GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
      return mc.getMatriculasAlumno(id,op,data);
      
    }
    
     /**
    * Obtiene una lista completa de los cursos de un profesor.
    * @param login login del profesor.
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */

    @Override
    public ArrayList<Curso> consultarCursosProfesor(String login) throws RemoteException, SQLException, Exception {
        
        GestorCurso mc = new GestorCurso(gestorDisc.getConnection());
        return mc.getCursosProfesor(login);
    }

    
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
    
    @Override
    public ArrayList<Matricula> consultarAlumnosProfesor(int id, Date data, ArrayList<Curso> cursos) throws RemoteException, SQLException, Exception {
        
         GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
         return mc.getAlumnosProfesor(id , data, cursos);
        
    }

    
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
    
    @Override
    public ArrayList<Matricula> consultarEstCursProfesor(int id, Date data, ArrayList<Curso> cursos) throws RemoteException, SQLException, Exception {
        
        GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
      return mc.getAsistenciaCursos(id , data, cursos);
        
    }
    
    /**
    * Obtiene una lista completa de los cursos del centro.
    * @param login login del profesor.
    * @return Una lista de instancia de {@link Curso}.
    * 
    * @throws SQLException
    * @throws Exception 
    * @throws RemoteException
    */


    @Override
    public ArrayList<Curso> consultarCursos() throws RemoteException, SQLException, Exception {
    
    GestorCurso mc = new GestorCurso(gestorDisc.getConnection());
        return mc.getCursos();
        
    }
    
    
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

    @Override
    public ArrayList<Matricula> consultarEstProfesores(Date data, ArrayList<Curso> cursos) throws RemoteException, SQLException, Exception {
      GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
      return mc.getAsistenciaProfesores(data, cursos);
    }

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
    
    @Override
    public ArrayList<Matricula> consultarEstCursosCentro(Date data, ArrayList<Curso> cursos) throws RemoteException, SQLException, Exception {
        GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
      return mc.getAsistenciaCursoCentro(data, cursos);
    }

    
    
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
    
    @Override
    public ArrayList<Matricula> consultarSolapados(Date data, String nif) throws RemoteException, SQLException, Exception {
        
          
         GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
         ArrayList<Matricula>matricules=mc.getCursosGruposAlumnos(data,nif);
         ArrayList<Matricula>solapados= new ArrayList<Matricula>();
         ArrayList<Matricula>matricules2=mc.getCursosGruposAlumnos(data,nif);
         
         for (Matricula x : matricules2)
         {
            for (Matricula a : matricules) {

                // Se solapa con otra actividad
                if ((x.getFechaInicio().compareTo(a.getFechaFinal()) <= 0)
                        && (a.getFechaFinal().compareTo(x.getFechaInicio()) >= 0)
                        && (x.getTurno()==a.getTurno())
                        && (x.getUsuarioId()==a.getUsuarioId())
                        && (x.getIdMatricula()!=a.getIdMatricula())
                        && (a.getSolapado()!=1))
                {
                    
                    solapados.add(a);
          
                    
                    
                }
            }
         }
         return solapados;
        }

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
    
    @Override
    public ArrayList<Matricula> consultarHoras(Time inici, Time fi) throws RemoteException, SQLException, Exception {
         GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
      return mc.getAsistenciaHoras(inici, fi);
    }
}
