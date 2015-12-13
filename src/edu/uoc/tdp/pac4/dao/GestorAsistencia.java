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
public class GestorAsistencia extends GestorDisco {

    private Connection conn = null;
    private LanguageUtils txt = null;

    /**
     * Constructor de la clase.
     */
    public GestorAsistencia(Connection conn) {
        this.conn = conn;
    }

    //===========================================
    // Propiedades
    //===========================================
    /**
     * Devuelve la conexión a base de datos.
     */
    @Override
    public Connection getConnection() {
        return conn;
    }

    //===========================================
    // Métodos
    //===========================================
    /**
     * Actualiza un recuento de asistencia.
     *
     * @param asistencia Una instancia de {@link Asistencia} que contiene los
     * datos del recuento.
     *
     * @throws SQLException
     * @throws Exception
     */
    public void update(Asistencia asistencia) throws SQLException, Exception {
        String sql;
        SimpleDateFormat dfd = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");

        try {
            // Agrega la petición
            sql = "UPDATE asistencia "
                    + "SET    asistencia    = '" + asistencia.isAsistencia()
                    + " WHERE  asistencia.id = " + asistencia.getId();

            execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Agrega una nueva entrada en AsistenciaAlumno
     *
     * @param actividadId
     * @param usuarioid
     *
     * @throws SQLException
     * @throws edu.uoc.tdp.pac4.exceptions.StudentAssistanceAlreadyCountedException
     * @throws GroupAlreadyCountedException
     * @throws Exception
     */
    public void addAsistencia(int actividadId, int usuarioid, boolean haAsistit) throws SQLException, StudentAssistanceAlreadyCountedException, Exception {
        String sql;

        try {
            // Comprueba que no exista otro registro de asistencia para dicha asistencia
            sql = "SELECT Count(*) As nItems "
                    + "FROM asistencia "
                    + "WHERE actividad_id =  " + actividadId + " And "
                    + "      usuari_id = " + usuarioid;
            if (executeScalar(sql) > 0) {
                throw new StudentAssistanceAlreadyCountedException();
            }

            // Agrega el registro de asistencia
            sql = "INSERT INTO asistencia (activitat_id, usuari_id, asistencia) "
                    + "VALUES "
                    + "(" + actividadId + ","
                    + "  " + usuarioid 
                    + "asistencia = " + haAsistit +")";
            execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (StudentAssistanceAlreadyCountedException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    //===========================================
    // Private members
    //===========================================
    private void readAsistencia(Asistencia asistencia, ResultSet rs) throws SQLException {
        // Propiedades nativas de la entidad
//        asistencia.setId(rs.getInt("asistid"));
//        asistencia.setDate(rs.getDate("fechaasist"));
//        asistencia.setHoraInicio(rs.getDate("horainicio"));
//        asistencia.setHoraFin(rs.getDate("horafin"));
//        asistencia.setTotalAssistencia(rs.getInt("totalasisten"));
//        asistencia.setTotalFaltas(rs.getInt("totalnoasisten"));
//        asistencia.setIdGrupo(rs.getInt("grupoid"));

    }
}
