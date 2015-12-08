/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.beans;

import edu.uoc.tdp.pac4.eAcademiaEU;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ivan
 */
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private long universitatId;
    private long centreId;
    private long aulaId;
    private int tipus;
    private String titol;
    private String area;
    private String especialitat;
    private String decanatura;
    private String investigator;
    private Date dataInici;
    private Date dataFi;
    private Date dataMaxInscripcio;
    private double preu;
    private double minimPercentatge;
    private boolean cancelada;

    // Definición de tipos
    public static final int ACTIVIDAD_TIPO_NO_DEFINIDO_ID = 0;
    public static final int ACTIVIDAD_TIPO_CONGRESO_ID = 1;
    public static final int ACTIVIDAD_TIPO_JORNADA_ID = 2;
    public static final int ACTIVIDAD_TIPO_MASTER_ID = 3;
    public static final int ACTIVIDAD_TIPO_CONFERENCIA_ID = 4;
    
    //==========================================
    // Constructors
    //==========================================
    /**
     * Constructor de la clase.
     */
    public Actividad() {
        initialize();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the universitatId
     */
    public long getUniversitatId() {
        return universitatId;
    }

    /**
     * @param universitatId the universitatId to set
     */
    public void setUniversitatId(long universitatId) {
        this.universitatId = universitatId;
    }

    /**
     * @return the centreId
     */
    public long getCentreId() {
        return centreId;
    }

    /**
     * @param centreId the centreId to set
     */
    public void setCentreId(long centreId) {
        this.centreId = centreId;
    }

    /**
     * @return the aulaId
     */
    public long getAulaId() {
        return aulaId;
    }

    /**
     * @param aulaId the aulaId to set
     */
    public void setAulaId(long aulaId) {
        this.aulaId = aulaId;
    }

    /**
     * @return the tipus
     */
    public int getTipus() {
        return tipus;
    }

    /**
     * @param tipus the tipus to set
     */
    public void setTipus(int tipus) {
        this.tipus = tipus;
    }

    /**
     * @return the titol
     */
    public String getTitol() {
        return titol;
    }

    /**
     * @param titol the titol to set
     */
    public void setTitol(String titol) {
        this.titol = titol;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the especialitat
     */
    public String getEspecialitat() {
        return especialitat;
    }

    /**
     * @param especialitat the especialitat to set
     */
    public void setEspecialitat(String especialitat) {
        this.especialitat = especialitat;
    }

    /**
     * @return the decanatura
     */
    public String getDecanatura() {
        return decanatura;
    }

    /**
     * @param decanatura the decanatura to set
     */
    public void setDecanatura(String decanatura) {
        this.decanatura = decanatura;
    }

    /**
     * @return the investigator
     */
    public String getInvestigator() {
        return investigator;
    }

    /**
     * @param investigator the investigator to set
     */
    public void setInvestigator(String investigator) {
        this.investigator = investigator;
    }

    /**
     * @return the dataInici
     */
    public Date getDataInici() {
        return dataInici;
    }

    /**
     * @param dataInici the dataInici to set
     */
    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    /**
     * @return the dataFi
     */
    public Date getDataFi() {
        return dataFi;
    }

    /**
     * @param dataFi the dataFi to set
     */
    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }

    /**
     * @return the dataMaxInscripcio
     */
    public Date getDataMaxInscripcio() {
        return dataMaxInscripcio;
    }

    /**
     * @param dataMaxInscripcio the dataMaxInscripcio to set
     */
    public void setDataMaxInscripcio(Date dataMaxInscripcio) {
        this.dataMaxInscripcio = dataMaxInscripcio;
    }

    /**
     * @return the preu
     */
    public double getPreu() {
        return preu;
    }

    /**
     * @param preu the preu to set
     */
    public void setPreu(double preu) {
        this.preu = preu;
    }

    /**
     * @return the minimPercentatge
     */
    public double getMinimPercentatge() {
        return minimPercentatge;
    }

    /**
     * @param minimPercentatge the minimPercentatge to set
     */
    public void setMinimPercentatge(double minimPercentatge) {
        this.minimPercentatge = minimPercentatge;
    }

    /**
     * @return the cancelada
     */
    public boolean isCancelada() {
        return cancelada;
    }

    /**
     * @param cancelada the cancelada to set
     */
    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    //==========================================
    // Methods
    //==========================================
    
   /**
    * Convierte un código de estado a un texto descriptivo.
    * 
    * @param status Código de estado. Corresponde a una de las constantes {@code MATRICULA_ESTADO_XXX}.
    * @return Una cadena que corresponde al nombre del estado especificado.
    */
   public static String getTipoActividadName(int status, LanguageUtils language)
   {
      switch (status)
      {
         case ACTIVIDAD_TIPO_CONGRESO_ID:
            return language.getProperty(eAcademiaEU.ACTIVIDAD_TIPO_CONGRESO);
         case ACTIVIDAD_TIPO_JORNADA_ID:
            return language.getProperty(eAcademiaEU.ACTIVIDAD_TIPO_JORNADA);
         case ACTIVIDAD_TIPO_MASTER_ID:
            return language.getProperty(eAcademiaEU.ACTIVIDAD_TIPO_MASTER);
         case ACTIVIDAD_TIPO_CONFERENCIA_ID:
            return language.getProperty(eAcademiaEU.ACTIVIDAD_TIPO_CONFERENCIA);
         default:
            return "";
      }
      
   }
    
    /**
     * Convierte la instancia en una cadena que representa el objeto.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.format(this.dataInici);
    }

    //==========================================
    // Private members
    //==========================================
    /**
     * Inicializa la instancia
     */
    private void initialize() {
        this.id = 0;
        this.universitatId = 0;
        this.centreId = 0;
        this.aulaId = 0;
        this.tipus = 0;
        this.titol = "";
        this.area = "";
        this.especialitat = "";
        this.decanatura = "";
        this.investigator = "";
        this.dataInici = null;
        this.dataFi = null;
        this.dataMaxInscripcio = null;
        this.preu = 0;
        this.minimPercentatge = 0;
        this.cancelada = false;
    }

}
