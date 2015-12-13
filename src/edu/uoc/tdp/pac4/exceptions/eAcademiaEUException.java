package edu.uoc.tdp.pac4.exceptions;

import edu.uoc.tdp.pac4.util.LanguageUtils;

/**
 * Clase eAcademiaEUException
 Gestor de excepciones de la aplicación
 * @version 1.0
 * @author JavaBeginers
 *
 */
public class eAcademiaEUException extends Exception 
{

	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor sin par�metros. Delega en la superclase.
     */
    public eAcademiaEUException() 
    {
        super();
    }
    
    public eAcademiaEUException(String msg) 
    {
        super(msg);
        System.out.println(msg);
        
    }    
    public static final String ERR_DRV = LanguageUtils.getMessage("con.db.err.driver");    
    public static final String ERR_RECURSOS = LanguageUtils.getMessage("gestor.request.err");
    public static final String ERR_PETICIO = LanguageUtils.getMessage("recurso.err.solicitudes");
    public static final String ERR_SQL = LanguageUtils.getMessage("services.err.query.sql");
    public static final String ERR_BD_STOP = LanguageUtils.getMessage("con.db.err.close");
    public static final String ERR_PRIMARY_KEY = LanguageUtils.getMessage("recurso.err.pk");
    public static final String ERR_NOT_FILE = LanguageUtils.getMessage("con.db.err.notfound");
    public static final String ERR_PROPERTIES = LanguageUtils.getMessage("con.db.err.notopen");
    public static final String ERR_CONECTION = LanguageUtils.getMessage("con.db.err.open");  
    public static final String ERR_DESC_EXEC = LanguageUtils.getMessage("services.err.query.unknown");
    public static final String ERR_QNT = LanguageUtils.getMessage("estoc.err.qnt");
    public static final String ERR_SELEC_CURSO = LanguageUtils.getMessage("err.curso.noselect");
}
