package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Implementa una petición de recursos.
 * 
 * @author JavaBeginers
 */
public class PeticionRecurso implements Serializable 
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;

   private int idRecurso;
   private int idAula;
   private Date fechaAltaPeticion;
   private int cantidad;
   private Date fechaAceptacion;

   /**
    * Constructor de la clase.
    */
   public PeticionRecurso() { }
   
   /**
    * Constructor de la clase.
    */
   public PeticionRecurso(int idRecurso, int idAula, Date fechaAltaPeticion, int cantidad, Date fechaAceptacion) 
   {
      this.idRecurso = idRecurso;
      this.idAula = idAula;
      this.fechaAltaPeticion = fechaAltaPeticion;
      this.cantidad = cantidad;
      this.fechaAceptacion = fechaAceptacion;
   }

   public int getIdRecurso() 
   {
      return idRecurso;
   }

   public int getIdAula() 
   {
      return idAula;
   }

   public Date getFechaAltaPeticion() 
   {
      return fechaAltaPeticion;
   }

   public int getCantidad() 
   {
      return cantidad;
   }

   public Date getFechaAceptacion() 
   {
      return fechaAceptacion;
   }

   public void setIdRecurso(int idRecurso) 
   {
      this.idRecurso = idRecurso;
   }

   public void setIdAula(int idAula) 
   {
      this.idAula = idAula;
   }

   public void setFechaAltaPeticion(Date fechaAltaPeticion) 
   {
      this.fechaAltaPeticion = fechaAltaPeticion;
   }

   public void setCantidad(int cantidad) 
   {
      this.cantidad = cantidad;
   }

   public void setFechaAceptacion(Date fechaAceptacion) 
   {
      this.fechaAceptacion = fechaAceptacion;
   }
}
