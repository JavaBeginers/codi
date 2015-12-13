package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Asistencia;
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
            sql = "UPDATE assistencia "
                    + "SET assistencia    = " + asistencia.isAsistencia()
                    + " WHERE id = " + asistencia.getId();

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
                    + "FROM assistencia "
                    + "WHERE activitat_id =  " + actividadId + " And "
                    + "      usuari_id = " + usuarioid;
            if (executeScalar(sql) > 0) {
                throw new StudentAssistanceAlreadyCountedException();
            }

            // Agrega el registro de asistencia
            sql = "INSERT INTO assistencia (activitat_id, usuari_id, assistencia) "
                    + "VALUES "
                    + "(" + actividadId + ","
                    + "  " + usuarioid 
                    + ", " + haAsistit +")";
            execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (StudentAssistanceAlreadyCountedException ex) {
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
     * @param haAsistit
     *
     * @throws SQLException
     * @throws edu.uoc.tdp.pac4.exceptions.StudentAssistanceAlreadyCountedException
     * @throws GroupAlreadyCountedException
     * @throws Exception
     */
    public void setAsistencia(int actividadId, int usuarioid, boolean haAsistit) throws SQLException, StudentAssistanceAlreadyCountedException, Exception {
        String sql;
        Asistencia asistencia;
        try {
            // Comprueba que no exista otro registro de asistencia para dicha asistencia
            sql = "SELECT * "
                    + "FROM assistencia "
                    + "WHERE activitat_id =  " + actividadId + " And "
                    + "      usuari_id = " + usuarioid;
            ResultSet rs = executeSql(sql);
            if (rs.next()) {
                asistencia = new Asistencia();
                readAsistencia(asistencia, rs);
                asistencia.setAsistencia(haAsistit);
                update(asistencia);
            } else {
                addAsistencia(actividadId, usuarioid, haAsistit);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (StudentAssistanceAlreadyCountedException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public ArrayList<Asistencia> getAsistenciasByActividadId(int idActivitat) throws SQLException, Exception {
        Asistencia asistencia;
        String sql;
        ArrayList<Asistencia> list = new ArrayList<Asistencia>();

        sql = "SELECT m.*, a.* FROM MATRICULA M " +
                "LEFT JOIN ASSISTENCIA A ON A.ACTIVITAT_ID = M.ACTIVITAT_ID AND A.USUARI_ID=M.USUARI_ID " +
                "WHERE M.ACTIVITAT_ID="+idActivitat +" ORDER BY M.USUARI_ID";

        try {
            ResultSet rs = executeSql(sql);
            while (rs.next()) {
                asistencia = new Asistencia();
                readAsistencia(asistencia, rs);
                list.add(asistencia);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return list;

    }
        
    //===========================================
    // Private members
    //===========================================
    private void readAsistencia(Asistencia asistencia, ResultSet rs) throws SQLException, Exception {
        // Propiedades nativas de la entidad
        asistencia.setId(rs.getInt("id"));
        asistencia.setIdActivitat(rs.getInt("activitat_id"));
        asistencia.setIdUsuari(rs.getInt("usuari_id"));
        asistencia.setAsistencia(rs.getBoolean("assistencia"));
    }
}
