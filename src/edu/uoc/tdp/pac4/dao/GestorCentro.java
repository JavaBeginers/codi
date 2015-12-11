/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Centro;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class GestorCentro extends GestorDisco {

    /*
     * Definimos los campos de la tabla actividad
     */
    private static final String CENTROTABLE = "centre";
    private static final String CENTROTABLE_ID = "centre_id";
    private static final String CENTROTABLE_NOMBRE = "nom_centre";
//    private static final String ACTIVIDADTABLE_CENTRE_ID = "centre_id";
//    private static final String ACTIVIDADTABLE_AULA_ID = "aula_id";
//    private static final String ACTIVIDADTABLE_TIPUS = "tipus";
//    private static final String ACTIVIDADTABLE_TITOL = "titol";
//    private static final String ACTIVIDADTABLE_AREA = "area";
//    private static final String ACTIVIDADTABLE_ESPECIALITAT = "especialitat";
//    private static final String ACTIVIDADTABLE_DECANATURA = "decanatura";
//    private static final String ACTIVIDADTABLE_INVESTIGADOR = "investigador";
//    private static final String ACTIVIDADTABLE_DATA_INICI = "data_inici";
//    private static final String ACTIVIDADTABLE_DATA_FI = "data_fi";
//    private static final String ACTIVIDADTABLE_DATA_MAX_INSCRIPCIO = "data_max_inscripcio";
//    private static final String ACTIVIDADTABLE_PREU = "preu";
//    private static final String ACTIVIDADTABLE_MINIM_PERCENTAGE = "minim_percentatge";
//    private static final String ACTIVIDADTABLE_CANCELADA = "cancelada";

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
     *
     * @param conn
     */
    public GestorCentro(Connection conn) {
        this.conn = conn;
    }

    //===========================================
    // Propiedades
    //===========================================
    /**
     * Devuelve la conexión a base de datos.
     *
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
     * @return Una lista de instancia de {@link Actividad}.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Centro> getCentros() throws SQLException, Exception {
        Centro centro;
        String sql;
        ArrayList<Centro> list = new ArrayList<Centro>();

        sql = "SELECT * FROM " + CENTROTABLE + " "
                + "ORDER BY " + CENTROTABLE_NOMBRE + " Asc";

        try {
            ResultSet rs = executeSql(sql);
            while (rs.next()) {
                centro = new Centro();
                readCentro(centro, rs);
                list.add(centro);
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
    /**
     * Actualiza los datos de una instancia de {@link Actividad} con los datos de
     * una fila de {@link Resultset}.
     */
    private void readCentro(Centro centro, ResultSet rs) throws SQLException {
        
        centro.setId(rs.getInt(CENTROTABLE_ID));
        centro.setNom(rs.getString(CENTROTABLE_NOMBRE));

    }
    
}
