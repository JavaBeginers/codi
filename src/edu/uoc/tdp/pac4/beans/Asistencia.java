package edu.uoc.tdp.pac4.beans;

import java.util.Date;

/**
 * Representa una contabilización de asistencia.
 * 
 * @author JavaBeginers
 */
public class Asistencia implements java.io.Serializable 
{
   private int id;
   private int idActivitat;
   private Date idUsuari;
   private boolean asistencia;
   
   //==========================================
   // Constructors
   //==========================================
   
   /**
    * Constructor de la clase.
    */
   public Asistencia()
   {
      initialize();
   }

   //==========================================
   // Properties
   //==========================================
   
   public int getId() 
   {
      return id;
   }

   public void setId(int id) 
   {
      this.id = id;
   }

   //==========================================
   // Private members
   //==========================================
   
   /**
    * Inicializa la instancia
    */
   private void initialize()
   {
       this.setId(0);
       this.setIdActivitat(0);
       this.setIdUsuari(null);
       this.setAsistencia(false);
   }

    /**
     * @return the idActivitat
     */
    public int getIdActivitat() {
        return idActivitat;
    }

    /**
     * @param idActivitat the idActivitat to set
     */
    public void setIdActivitat(int idActivitat) {
        this.idActivitat = idActivitat;
    }

    /**
     * @return the idUsuari
     */
    public Date getIdUsuari() {
        return idUsuari;
    }

    /**
     * @param idUsuari the idUsuari to set
     */
    public void setIdUsuari(Date idUsuari) {
        this.idUsuari = idUsuari;
    }

    /**
     * @return the asistencia
     */
    public boolean isAsistencia() {
        return asistencia;
    }

    /**
     * @param asistencia the asistencia to set
     */
    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
}
