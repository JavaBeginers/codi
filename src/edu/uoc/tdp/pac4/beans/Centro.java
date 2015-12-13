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
public class Centro implements Serializable {
    
    private static final long serialVersionUID = 1L;

    
    private int id;
    private String nom;
    private int iduniversitat;
    private String adreca;
    private String poblacio;
    private String telefon;
    private String mail;
    private String url;
    private int pais;
    private String cp;
    Date dataAlta;
    Date dataBaixa;
    

   /*************************************
   **********CONSTRUCTOR*****************
   **************************************/
    
    public Centro(){
       this.id=0;
       this.iduniversitat=0;
       this.adreca="";
       this.nom="";
       this.poblacio="";
       this.telefon="";
       this.mail="";
       this.url="";
       this.pais=0;
       this.cp="";
       this.dataAlta=null;
       this.dataBaixa=null;
   }

   /*************************************
   **********PROPERTIES*****************
   **************************************/
    
    
    public int getId() {
        return id;
   
}
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getUniversitat() {
        return iduniversitat;
    }

    public void setUniversitat(int universidadId) {
        this.iduniversitat= universidadId;
    }
    
    public String getAdreca(){
        return adreca;
    }
    
    public void setAdreca(String adr){
        this.adreca=adr;
    }
    
    public String getPoblacio(){
        return poblacio;
    }
    
    public void setPoblacio(String poble){
        this.poblacio=poble;
    }

    public Date getDataAlta()
   {
      return dataAlta;
   }

   public void setDataAlta(Date fechaAlta) 
   {
      this.dataAlta = fechaAlta;
   }

   public String getEmail() 
   {
      return mail;
   }

   public void setEmail(String email) 
   {
      this.mail = email;
   }

   public String getTelf() 
   {
      return telefon;
   }

   public void setTelf(String telf) 
   {
      this.telefon = telf;
   }

      public String getURL() 
   {
      return url;
   }

   public void setURL(String n_url) 
   {
      this.url = n_url;
   }

   public String getCP(){
        return cp;
    }
    
    public void setCP(String cop){
        this.cp=cop;
    }
    
    public int getPais(){
        return pais;
    }
   
    public void setPais(int ps){
        this.pais=ps;
    }

    public Date getDataBaixa()
   {
      return dataBaixa;
   }

   public void setDataBaixa(Date fechabaja) 
   {
      this.dataBaixa = fechabaja;
   }
}
