/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ivan
 */
public class Universitat implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;

   /*************************************
   **********CONSTRUCTOR*****************
   **************************************/
   public Universitat() { }
   
  
   public Universitat(int universitat_id, String acronim, String nom, String adreca, String poblacio, String codi_postal,
           String pais, String telefon, String email, String url, Date data_alta, Date data_baixa){
       
      this.universitat_id = universitat_id;
      this.acronim = acronim;
      this.nom = nom;
      this.adreca = adreca;
      this.poblacio = poblacio;
      this.codi_postal = codi_postal;
      this.pais = pais;
      this.telefono = telefon;
      this.email = email;
      this.url = url;
      this.data_alta = data_alta;
      this.data_baixa = data_baixa;
      
   }
    
    private int universitat_id;
    private String acronim;
    private String nom;
    private String adreca;
    private String poblacio;
    private String codi_postal;
    private String pais;
    private String telefono;
    private String email;
    private String url;
    private Date data_alta;
    private Date data_baixa;

    /**
     * @return the universitat_id
     */
    public int getUniversitat_id() {
        return universitat_id;
    }

    /**
     * @param universitat_id the universitat_id to set
     */
    public void setUniversitat_id(int universitat_id) {
        this.universitat_id = universitat_id;
    }

    /**
     * @return the acronim
     */
    public String getAcronim() {
        return acronim;
    }

    /**
     * @param acronim the acronim to set
     */
    public void setAcronim(String acronim) {
        this.acronim = acronim;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the adreca
     */
    public String getAdreca() {
        return adreca;
    }

    /**
     * @param adreca the adreca to set
     */
    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    /**
     * @return the poblacio
     */
    public String getPoblacio() {
        return poblacio;
    }

    /**
     * @param poblacio the poblacio to set
     */
    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    /**
     * @return the codi_postal
     */
    public String getCodi_postal() {
        return codi_postal;
    }

    /**
     * @param codi_postal the codi_postal to set
     */
    public void setCodi_postal(String codi_postal) {
        this.codi_postal = codi_postal;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the data_alta
     */
    public Date getData_alta() {
        return data_alta;
    }

    /**
     * @param data_alta the data_alta to set
     */
    public void setData_alta(Date data_alta) {
        this.data_alta = data_alta;
    }

    /**
     * @return the data_baixa
     */
    public Date getData_baixa() {
        return data_baixa;
    }

    /**
     * @param data_baixa the data_baixa to set
     */
    public void setData_baixa(Date data_baixa) {
        this.data_baixa = data_baixa;
    }
}
