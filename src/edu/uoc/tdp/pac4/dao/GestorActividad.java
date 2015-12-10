package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.exceptions.NoGroupFoundException;
import edu.uoc.tdp.pac4.util.LanguageUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Gestiona los actividades del centro.
 *
 * @author eSupport Netbeans
 */
public class GestorActividad extends GestorDisco {

    /*
     * Definimos los campos de la tabla actividad
     */
    private static final String ACTIVIDADTABLE = "actividad";
    private static final String ACTIVIDADTABLE_ID = "id";
    private static final String ACTIVIDADTABLE_UNIVERSITAT_ID = "universitat_id";
    private static final String ACTIVIDADTABLE_CENTRE_ID = "centre_id";
    private static final String ACTIVIDADTABLE_AULA_ID = "aula_id";
    private static final String ACTIVIDADTABLE_TIPUS = "tipus";
    private static final String ACTIVIDADTABLE_TITOL = "titol";
    private static final String ACTIVIDADTABLE_AREA = "area";
    private static final String ACTIVIDADTABLE_ESPECIALITAT = "especialitat";
    private static final String ACTIVIDADTABLE_DECANATURA = "decanatura";
    private static final String ACTIVIDADTABLE_INVESTIGADOR = "investigador";
    private static final String ACTIVIDADTABLE_DATA_INICI = "data_inici";
    private static final String ACTIVIDADTABLE_DATA_FI = "data_fi";
    private static final String ACTIVIDADTABLE_DATA_MAX_INSCRIPCIO = "data_max_inscripcio";    
    private static final String ACTIVIDADTABLE_PREU = "preu";
    private static final String ACTIVIDADTABLE_MINIM_PERCENTAGE = "minim_percentatge";
    private static final String ACTIVIDADTABLE_CANCELADA = "cancelada";

//    /*
//     * Definimos los campos de interes de la tabla grupo
//     */
    private static final String GRUPOTABLE = "grupo";
    private static final String GRUPOTABLE_ACTIVIDAD = "idactividad";

    private Connection conn = null;
    private final LanguageUtils txt = null;
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    //===========================================
    // Constructors
    //===========================================
    /**
     * Constructor de la clase.
     * @param conn
     */
    public GestorActividad(Connection conn) {
        this.conn = conn;
    }

    //===========================================
    // Propiedades
    //===========================================
    /**
     * Devuelve la conexión a base de datos.
     * @return 
     */
    @Override
    public Connection getConnection() {
        return conn;
    }

    //===========================================
    // Métodos
    //===========================================
    /**
     * Obtiene una lista completa de los actividades del centro.
     *
     * @param id
     * @return Una lista de instancia de {@link Actividad}.
     *
     * @throws SQLException
     * @throws Exception
     */
    public Actividad get(int id) throws SQLException, Exception {
        Actividad actividad = null;
        String sql;

        sql = "SELECT * "
                + "FROM actividad "
                + "WHERE id = " + id;

        try {
            ResultSet rs = executeSql(sql);
            if (rs.next()) {
                actividad = new Actividad();
                readActividad(actividad, rs);

                return actividad;
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return actividad;
    }

    /**
     * Obtiene una lista completa de los actividades del centro.
     *
     * @return Una lista de instancia de {@link Actividad}.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Actividad> getActividades() throws SQLException, Exception {
        Actividad actividad;
        String sql;
        ArrayList<Actividad> list = new ArrayList<Actividad>();

        sql = "SELECT * FROM " + ACTIVIDADTABLE + " "
                + "WHERE " + ACTIVIDADTABLE_CANCELADA + " = '" + 0 + "' or " + ACTIVIDADTABLE_CANCELADA + " IS NULL "
                + "ORDER BY " + ACTIVIDADTABLE_TITOL + " Asc";

        try {
            ResultSet rs = executeSql(sql);
            while (rs.next()) {
                actividad = new Actividad();
                readActividad(actividad, rs);
                list.add(actividad);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return list;
    }

    /**
     * Obtiene una lista completa de los actividadess inactivos del centro.
     *
     * @return Una lista de instancia de {@link Actividad}.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Actividad> getActividadesInactivas() throws SQLException, Exception {
        Actividad actividad;
        String sql;
        ArrayList<Actividad> list = new ArrayList<Actividad>();

        sql = "SELECT * FROM " + ACTIVIDADTABLE + " "
                + "WHERE " + ACTIVIDADTABLE_CANCELADA + " = '" + 1 + "'"
                + "ORDER BY " + ACTIVIDADTABLE_TITOL + " Asc";

        try {
            ResultSet rs = executeSql(sql);
            while (rs.next()) {
                actividad = new Actividad();
                readActividad(actividad, rs);
                list.add(actividad);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return list;
    }

    /**
     * Obtiene una lista completa de los actividades de un profesor.
     *
     * @param login Identificador único del profesor.
     * @return Una lista de instancia de {@link Actividad}.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Actividad> getActividadesProfesor(String login) throws SQLException, Exception {
        Actividad actividad;
        String sql;
        ArrayList<Actividad> list = new ArrayList<Actividad>();

        sql = "SELECT actividad.titol,actividad.id "
                + "FROM actividad,usuario,grupo "
                + "WHERE grupo.idactividad=actividad.id and usuario.id=grupo.idprofesor and usuario.login='" + login + "'"
                + "group by actividad.titol,actividad.id"
                + " ORDER BY actividad.titol Asc";

        try {
            ResultSet rs = executeSql(sql);
            while (rs.next()) {
                actividad = new Actividad();
                actividad.setId(rs.getInt("id"));
                actividad.setTitol(rs.getString("titol"));

                list.add(actividad);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return list;
    }

    /**
     * Devuelve el número de plazas disponibles para un determinado actividad y
     * turno.
     * Se calcula en función de las matrículas aceptadas para tener mayor
     * fiabilidad.
     *
     * @param idActividad Identificador único del actividad.
     * @param turno Código del turno.
     * @return El número de plazas disponibles en un grupo y turno.
     * @throws edu.uoc.tdp.pac4.exceptions.NoGroupFoundException
     * @throws java.lang.Exception
     * @throws java.sql.SQLException
     */
    public int getPlazasDisponibles(int idActividad, int turno) throws NoGroupFoundException, SQLException, Exception {
        int plazas = 0, alumnos = 0, idgrupo = 0;
        String sql;
        Statement objStt;
        ResultSet rs;

        try {
            // Obtiene el grupo correspondiente al turno y actividad
            sql = "SELECT grupo.grupoid, grupo.maxalumnos "
                    + "FROM   actividad Inner Join grupo On (grupo.idactividad = actividad.id) "
                    + "WHERE  actividad.id    = " + idActividad + " And "
                    + "       grupo.turno = " + turno;

            rs = executeSql(sql);
            if (rs.next()) {
                idgrupo = rs.getInt("grupoid");
                plazas = rs.getInt("maxalumnos");
            } else {
                throw new NoGroupFoundException();
            }

            // Obtiene el número de plazas libres
            sql = "SELECT Count(*) As nPlazas "
                    + "FROM   actividad Inner Join matriculas On (actividad.id = matriculas.actividadid) "
                    + "             Inner Join grupo      On (grupo.grupoid = matriculas.grupoid) "
                    + "WHERE  matriculas.estadoid = " + Matricula.MATRICULA_ESTADO_ACEPTADA + " And "
                    + "       grupo.turno         = " + turno + " And "
                    + "       actividad.id            = " + idActividad;

            rs = executeSql(sql);
            if (rs.next()) {
                alumnos = rs.getInt("nPlazas");
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return plazas - alumnos;
    }
    
    /**
     * Agrega un nuevo usuario al centro.
     *
     * @param actividad Una instancia de {@link Usuario} que contiene los datos
     * del grupo.
     *
     * @throws SQLException
     * @throws Exception
     */
    public void add(Actividad actividad) throws SQLException, Exception {
        StringBuilder sql = new StringBuilder();
        Statement statement;

        
        try {
            sql.append("INSERT INTO " + ACTIVIDADTABLE + " ");
            sql.append(ACTIVIDADTABLE_ID + ",");
            sql.append(ACTIVIDADTABLE_UNIVERSITAT_ID + ",");
            sql.append(ACTIVIDADTABLE_CENTRE_ID + ",");
            sql.append(ACTIVIDADTABLE_AULA_ID + ",");
            sql.append(ACTIVIDADTABLE_TIPUS + ",");
            sql.append(ACTIVIDADTABLE_TITOL + ",");
            sql.append(ACTIVIDADTABLE_AREA  + ",");
            sql.append(ACTIVIDADTABLE_ESPECIALITAT + ",");
            sql.append(ACTIVIDADTABLE_DECANATURA  + ",");
            sql.append(ACTIVIDADTABLE_INVESTIGADOR  + ",");
            sql.append(ACTIVIDADTABLE_DATA_INICI  + ",");
            sql.append(ACTIVIDADTABLE_DATA_FI  + ",");
            sql.append(ACTIVIDADTABLE_DATA_MAX_INSCRIPCIO  + ",");
            sql.append(ACTIVIDADTABLE_PREU  + ",");
            sql.append(ACTIVIDADTABLE_MINIM_PERCENTAGE  + ",");
            sql.append(ACTIVIDADTABLE_CANCELADA  + ") ");
            sql.append("VALUES ");
            sql.append("('").append(actividad.getId()).append("', ");
            sql.append(actividad.getUniversitatId()).append(", ");
            sql.append(actividad.getCentreId()).append(", ");
            sql.append(actividad.getAulaId()).append(", ");
            sql.append(actividad.getTipus()).append(", ");
            sql.append(actividad.getTitol()).append(", ");
            sql.append(actividad.getArea()).append(", ");
            sql.append(actividad.getEspecialitat()).append(", ");
            sql.append(actividad.getDecanatura()).append(", ");
            sql.append(actividad.getInvestigator()).append(", ");
            sql.append("'").append(df.format(actividad.getDataInici())).append("', ");
            sql.append("'").append(df.format(actividad.getDataFi())).append("', ");
            if(actividad.getDataMaxInscripcio()!=null) {
                sql.append("'").append(df.format(actividad.getDataMaxInscripcio())).append("', ");
            } else {
                sql.append("null");
            }
            sql.append(actividad.getPreu()).append(", ");
            sql.append(actividad.getMinimPercentatge()).append(", ");
            sql.append(actividad.isCancelada()?"1":"0");
            sql.append("')");

            statement = getConnection().createStatement();
            statement.execute(sql.toString());
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Actualiza los datos de un Actividad.
     *
     * @param actividad Una instancia de {@link Actividad} que contiene los datos
     * actualizados del actividad.
     *
     * @throws SQLException
     * @throws Exception
     */
    public void update(Actividad actividad) throws SQLException, Exception {
        String sql;
        Statement statement;

        try {
            sql = "UPDATE " + ACTIVIDADTABLE + " "
                    + "SET " + ACTIVIDADTABLE_TITOL + " = '" + actividad.getTitol() + "', "
                    + ACTIVIDADTABLE_DATA_INICI + " = '" + df.format(actividad.getDataInici()) + "', "
                    + ACTIVIDADTABLE_DATA_FI + " = '" + df.format(actividad.getDataFi()) + "', "
                    + ACTIVIDADTABLE_MINIM_PERCENTAGE + " = " + actividad.getMinimPercentatge() + " "
                    + "WHERE " + ACTIVIDADTABLE_ID + " = " + actividad.getId();
            //System.err.print(sql);
            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Elimina (borrado logico) un Actividad.
     *
     * @param id Identificador del Actividad a eliminar.
     *
     * @throws SQLException
     * @throws Exception
     */
    public void delete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;

        Date now = new Date();

        try {
            sql = "UPDATE " + ACTIVIDADTABLE + " "
                    + "SET " + ACTIVIDADTABLE_CANCELADA + " = '" + 1 + "', "
                    + ACTIVIDADTABLE_DATA_MAX_INSCRIPCIO + " = '" + df.format(now) + "' "
                    + "WHERE " + ACTIVIDADTABLE_ID + " = " + id;
            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Recupera (de un borrado lógico) un Actividad del centro.
     *
     * El actividad se publica asignando el bit '0' a ACTIVIDADTABLE_CANCELADA
     *
     * @param id Identificador del Actividad a eliminar.
     *
     * @throws SQLException
     * @throws Exception
     */
    public void undelete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;

        Date now = new Date();

        try {
            sql = "UPDATE " + ACTIVIDADTABLE + " "
                    + "SET " + ACTIVIDADTABLE_CANCELADA + " = '" + 0 + "' "
                    + "WHERE " + ACTIVIDADTABLE_ID + " = " + id;
            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Devuelve el número de Grupos que un Actividad tiene asignados
     *
     * @param id Identificador del Actividad a checkear
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public int checkGruposAssigned(int id) throws SQLException, Exception {
        String sql;
        ResultSet rs;

        int grupos = 0;
        try {
            sql = "SELECT COUNT(*) as total FROM " + GRUPOTABLE + " "
                    + "WHERE " + GRUPOTABLE_ACTIVIDAD + " = " + id;
            Statement objStt = (Statement) getConnection().createStatement();
            rs = objStt.executeQuery(sql);
            if (rs.next()) {
                grupos = rs.getInt("total");
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return grupos;
    }

    //===========================================
    // Private members
    //===========================================
    /**
     * Actualiza los datos de una instancia de {@link Actividad} con los datos de
     * una fila de {@link Resultset}.
     */
    private void readActividad(Actividad actividad, ResultSet rs) throws SQLException {
        
        actividad.setId(rs.getInt(ACTIVIDADTABLE_ID));
        actividad.setUniversitatId(rs.getLong(ACTIVIDADTABLE_UNIVERSITAT_ID));
        actividad.setCentreId(rs.getLong(ACTIVIDADTABLE_CENTRE_ID));
        actividad.setAulaId(rs.getLong(ACTIVIDADTABLE_AULA_ID));
        actividad.setTipus(rs.getInt(ACTIVIDADTABLE_TIPUS));
        actividad.setTitol(rs.getString(ACTIVIDADTABLE_TITOL));
        actividad.setArea(rs.getString(ACTIVIDADTABLE_AREA));
        actividad.setEspecialitat(rs.getString(ACTIVIDADTABLE_ESPECIALITAT));
        actividad.setDecanatura(rs.getString(ACTIVIDADTABLE_DECANATURA));
        actividad.setInvestigator(rs.getString(ACTIVIDADTABLE_INVESTIGADOR));
        actividad.setDataInici(rs.getDate(ACTIVIDADTABLE_DATA_INICI));
        actividad.setDataFi(rs.getDate(ACTIVIDADTABLE_DATA_FI));
        actividad.setDataMaxInscripcio(rs.getDate(ACTIVIDADTABLE_DATA_MAX_INSCRIPCIO));
        actividad.setPreu(rs.getDouble(ACTIVIDADTABLE_PREU));
        actividad.setMinimPercentatge(rs.getDouble(ACTIVIDADTABLE_MINIM_PERCENTAGE));
        actividad.setCancelada(rs.getBoolean(ACTIVIDADTABLE_CANCELADA));
        
    }
    
}
