package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.dao.GestorActividad;
import edu.uoc.tdp.pac4.dao.GestorDisco;
import edu.uoc.tdp.pac4.dao.GestorMatricula;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author eSupport Netbeans
 */
public class EstadisticasImpl extends UnicastRemoteObject implements Estadisticas, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;

    GestorDisco gestorDisc;

    public EstadisticasImpl() throws RemoteException, Exception {
        super();

        try {
            gestorDisc = new GestorDisco();
            gestorDisc.initConnection();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Obtiene una lista completa de las matrículas de un alumno.
     *
     * @param id identificador alumno.
     * @param op opcion escogida del raioButton.
     * @param data data minima de matricula actividad.
     * @return Una lista de instancia de {@link Matricula}.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public ArrayList<Matricula> consultarActividadesAlumno(int id, int op, Date data) throws SQLException, Exception {

        GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
        return mc.getMatriculasAlumno(id, op, data);

    }

    /**
     * Obtiene una lista completa de los actividades de un profesor.
     *
     * @param login login del profesor.
     * @return Una lista de instancia de {@link Actividad}.
     *
     * @throws SQLException
     * @throws Exception
     * @throws RemoteException
     */
    @Override
    public ArrayList<Actividad> consultarActividadesProfesor(String login) throws RemoteException, SQLException, Exception {

        GestorActividad mc = new GestorActividad(gestorDisc.getConnection());
        return mc.getActividadesProfesor(login);
    }

    /**
     * Obtiene una lista completa de las alumno que imparte clase un profesor.
     *
     * @param id identificador profesor.
     * @param data data minima de matricula actividad.
     * @param actividades listado de actividades.
     * @return Una lista de instancia de {@link Matricula}.
     *
     * @throws SQLException
     * @throws Exception
     * @throws RemoteException
     */
    @Override
    public ArrayList<Matricula> consultarAlumnosProfesor(int id, Date data, ArrayList<Actividad> actividades) throws RemoteException, SQLException, Exception {

        GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
        return mc.getAlumnosProfesor(id, data, actividades);

    }

    /**
     * Obtiene una lista completa de las estadísticas de asistencia en los
     * actividades de un profesor.
     *
     * @param id identificador profesor.
     * @param data data minima de matricula actividad.
     * @param actividades listado de actividades.
     * @return Una lista de instancia de {@link Matricula}.
     *
     * @throws SQLException
     * @throws Exception
     * @throws RemoteException
     */
    @Override
    public ArrayList<Matricula> consultarEstCursProfesor(int id, Date data, ArrayList<Actividad> actividades) throws RemoteException, SQLException, Exception {

        GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
        return mc.getAsistenciaActividades(id, data, actividades);

    }

    /**
     * Obtiene una lista completa de los actividades del centro.
     *
     * @param login login del profesor.
     * @return Una lista de instancia de {@link Actividad}.
     *
     * @throws SQLException
     * @throws Exception
     * @throws RemoteException
     */
    @Override
    public ArrayList<Actividad> consultarActividades() throws RemoteException, SQLException, Exception {

        GestorActividad mc = new GestorActividad(gestorDisc.getConnection());
        return mc.getActividades();

    }

    /**
     * Obtiene una lista de los profesores con la asistencia a sus actividades.
     *
     * @param data data minima de matricula actividad.
     * @param actividades listado de actividades.
     * @return Una lista de instancia de {@link Matricula}.
     *
     * @throws SQLException
     * @throws Exception
     * @throws RemoteException
     */
    @Override
    public ArrayList<Matricula> consultarEstProfesores(Date data, ArrayList<Actividad> actividades) throws RemoteException, SQLException, Exception {
        GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
        return mc.getAsistenciaProfesores(data, actividades);
    }

    /**
     * Obtiene una lista los actividades impartidos por el centro con la asistencia
     * de cada actividad.
     *
     * @param data data minima de matricula actividad.
     * @param actividades listado de actividades.
     * @return Una lista de instancia de {@link Matricula}.
     *
     * @throws SQLException
     * @throws Exception
     * @throws RemoteException
     */
    @Override
    public ArrayList<Matricula> consultarEstActividadesCentro(Date data, ArrayList<Actividad> actividades) throws RemoteException, SQLException, Exception {
        GestorMatricula mc = new GestorMatricula(gestorDisc.getConnection());
        return mc.getAsistenciaActividadCentro(data, actividades);
    }

    /**
     * Obtiene una lista los actividades solapados.
     *
     * @param data data minima de matricula actividad.
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
        ArrayList<Matricula> matricules = mc.getActividadesGruposAlumnos(data, nif);
        ArrayList<Matricula> solapados = new ArrayList<Matricula>();
        ArrayList<Matricula> matricules2 = mc.getActividadesGruposAlumnos(data, nif);

        for (Matricula x : matricules2) {
            for (Matricula a : matricules) {

                // Se solapa con otra actividad
                if ((x.getFechaInicio().compareTo(a.getFechaFinal()) <= 0)
                        && (a.getFechaFinal().compareTo(x.getFechaInicio()) >= 0)
                        && (x.getTurno() == a.getTurno())
                        && (x.getUsuarioId() == a.getUsuarioId())
                        && (x.getIdMatricula() != a.getIdMatricula())
                        && (a.getSolapado() != 1)) {

                    solapados.add(a);

                }
            }
        }
        return solapados;
    }

    /**
     * Obtiene una lista con la asistencia por horas.
     *
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
