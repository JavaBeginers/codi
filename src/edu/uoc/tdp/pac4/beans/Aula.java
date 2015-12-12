package edu.uoc.tdp.pac4.beans;
import java.io.Serializable;
import java.util.Date;

   /*************************************
   *****************AULA*****************
   ***@AUTHOR: Cristian - JavaBeginers***
   **************************************/

public class Aula extends IdentifiableObject implements Serializable
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;
   
   private int idAula;
   private int centroAula;
   private String codigoAula;
   private String nombreAula;
   private int capacidadAula;
   private String ubicacionAula;
   Date fechaAlta;
   Date fechaBaja;


   /*************************************
   **********CONSTRUCTOR*****************
   **************************************/
   
   public Aula() 
   {
       this.idAula=0;
       this.centroAula=0;
       this.codigoAula="";
       this.nombreAula="";
       this.capacidadAula=0;
       this.ubicacionAula="";
       this.fechaAlta=null;
       this.fechaBaja=null;
   }

   /*************************************
   **********PROPERTIES*****************
   **************************************/
   
   public int getId(){
       return idAula;
   }
   
   public void setId(int id){
       this.idAula=id;
   }
   
   public int getCentro(){
       return centroAula;
   }
   
   public void setCentro(int centro){
       this.centroAula = centro;
   }
   
   public String getCodigo(){
       return codigoAula;
   }
   
   public void setCodigo(String codigo){
       this.codigoAula=codigo;
   }
   
   public String getNombre(){
      return nombreAula;
   }

   public void setNombre(String nombre){
      this.nombreAula = nombre;
   }

   public int getCapacidad(){
      return capacidadAula;
   }

   public void setCapacidad(int capacidad){
      this.capacidadAula = capacidad;
   }

   public String getUbicacion(){
      return ubicacionAula;
   }

   public void setUbicacion(String ubicacion){
      this.ubicacionAula = ubicacion;
   }

   public Date getFechaAlta(){
      return fechaAlta;
   }

   public void setFechaAlta(Date fechaAlta){
      this.fechaAlta = fechaAlta;
   }


  public Date getFechaBaja(){
      return fechaBaja;
   }

   public void setFechaBaja(Date fechabaja) 
   {
      this.fechaBaja = fechabaja;
   }
   
}
   
  