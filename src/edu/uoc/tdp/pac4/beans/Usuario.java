package edu.uoc.tdp.pac4.beans;

import java.util.Date;

/**
 * Representa un usuario del sistema.
 * 
 * @author JavaBeginers
 */
public class Usuario extends IdentifiableObject implements java.io.Serializable
{
   private int usuari_id;
   private String nom_usuari;
   private String contrasenya;
   private String noms;
   private String cognoms;
   private String nif; 
   private int pais_nif;
   private int pais;
   private String adreca;
   private String CP;
   private String poblacio;
   private int idioma;
   private Date data_alta;
   private String email;
   private String telf;
   private boolean activo;
   private Date data_baixa;
   private int rol;
   private String descrol;
   private int universidadId;

   
   //===========================================
   // Constructors
   //===========================================
   
   /**
    * Constructor de la clase.
    */
   public Usuario()
   {
      this.nom_usuari = "";
      this.contrasenya = "";
      this.data_alta = null;
      this.email = "";
      this.telf = "";
      this.noms = "";
      this.cognoms = "";
      this.activo = false;
      this.data_baixa = null;
      this.nif = "";
      this.rol = 1;
      this.descrol ="Administrador";
      this.universidadId=0;
      this.adreca="";
      this.poblacio="";
      this.CP="";
      this.pais=1;
      this.pais_nif=1;
      this.idioma=1;
         
   }

   //===========================================
   // Properties
   //===========================================
   
   public String getLogin() 
   {
      return nom_usuari;
   }

    public String getDescrol() {
        return descrol;
    }

    public void setDescrol(String descrol) {
        this.descrol = descrol;
    }

   public void setLogin(String login) 
   {
      this.nom_usuari = login;
   }

   public String getPwd() 
   {
      return contrasenya;
   }

   public void setPwd(String pwd) 
   {
      this.contrasenya = pwd;
   }

   public Date getFechaAlta()
   {
      return data_alta;
   }

   public void setFechaAlta(Date fechaAlta) 
   {
      this.data_alta = fechaAlta;
   }

   public String getEmail() 
   {
      return email;
   }

   public void setEmail(String email) 
   {
      this.email = email;
   }

   public String getTelf() 
   {
      return telf;
   }

   public void setTelf(String telf) 
   {
      this.telf = telf;
   }

   public String getNombre() 
   {
      return noms;
   }

   public void setNombre(String nombre) 
   {
      this.noms = nombre;
   }

   public String getApellidos() 
   {
      return cognoms.replace("_", " ");
   }
   
   public String getPrimerApellido(){
        return cognoms.split("_")[0].toString();
   }
   
   public String getSegundoApellido(){
        return cognoms.split("_")[1].toString();
   }

   public void setApellidos(String apellido1, String apellido2) 
   {
      StringBuilder strbff = new StringBuilder();
      strbff.append(apellido1);
      strbff.append("_");
      strbff.append(apellido2);
      this.cognoms = strbff.toString();
   }
   public void setApellidos(String apellidos){
       this.cognoms = apellidos;
   }

   public boolean isActivo() 
   {
      return activo;
   }
   
   public int isActivoBit() {
       if (this.isActivo()) {
           return 1;
       }
       else {
           return 0;
       }
   }

   public void setActivo(boolean activo) 
   {
      this.activo = activo;
   }

   public Date getFechaInactividad() 
   {
      return data_baixa;
   }

   public void setFechaInactividad(Date fechaInactividad) 
   {
      this.data_baixa = fechaInactividad;
   }

   public String getNif() 
   {
      return nif;
   }

   public void setNif(String nif) 
   {
      this.nif = nif;
   }

   public int getIdRol() 
   {
      return rol;
   }

   public void setIdRol(int idRol) 
   {
      this.rol = idRol;
   }
   
    public int getId() {
        return usuari_id;
    }

    public void setId(int id) {
        this.usuari_id = id;
    }

    /**
     * @return the universidadId
     */
    public int getUniversidadId() {
        return universidadId;
    }

    /**
     * @param universidadId the universidadId to set
     */
    public void setUniversidadId(int universidadId) {
        this.universidadId = universidadId;
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
    
   
    public String getCP(){
        return CP;
    }
    
    public void setCP(String cop){
        this.CP=cop;
    }
    
    public int getPais(){
        return pais;
    }
    public void setPais(int ps){
        this.pais=ps;
    }
  
    public int getPaisNIF(){
        return pais_nif;
    }
    public void setPaisNIF(int psnif){
        this.pais_nif=psnif;
    }

    public int getIdioma(){
        return idioma;
    }
    public void setIdioma(int id){
        this.idioma=id;
    }
    
}
