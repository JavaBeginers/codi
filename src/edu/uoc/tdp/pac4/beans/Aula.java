package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Implementa una aula del centro.
 * 
 * @author JavaBeginers
 */
public class Aula extends IdentifiableObject implements Serializable
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;
   
   private String nombre;
   private int capacidad;
   private String descripcion;
   private String localizacion;
   Date fechaAlta;
   boolean activa;
   Date fechaInactividad;

   //==========================================
   // Constructors
   //==========================================
   
   /**
    * Constructor de la clase.
    */
   public Aula() 
   {
      this.descripcion = "";
      this.nombre = "";
      this.capacidad = 0;
      this.descripcion = "";
      this.localizacion = "";
      this.fechaAlta = null;
      this.activa = false;
      this.fechaInactividad = null;
   }

   //==========================================
   // Properties
   //==========================================
   
   public String getNombre() 
   {
      return nombre;
   }

   public void setNombre(String nombre) 
   {
      this.nombre = nombre;
   }

   public int getCapacidad() 
   {
      return capacidad;
   }

   public void setCapacidad(int capacidad)
   {
      this.capacidad = capacidad;
   }

   public String getDescripcion() 
   {
      return descripcion;
   }

   public void setDescripcion(String descripcion) 
   {
      this.descripcion = descripcion;
   }

   public String getLocalizacion() 
   {
      return localizacion;
   }

   public void setLocalizacion(String localizacion) 
   {
      this.localizacion = localizacion;
   }

   public Date getFechaAlta() 
   {
      return fechaAlta;
   }

   public void setFechaAlta(Date fechaAlta) 
   {
      this.fechaAlta = fechaAlta;
   }

   public boolean isActiva() 
   {
      return activa;
   }
   
   public int isActivaBit() {
       if (this.isActiva()) {
           return 1;
       }
       else {
           return 0;
       }
   }

   public void setActiva(boolean activa) 
   {
      this.activa = activa;
   }

   public Date getFechaInactividad()
   {
      return fechaInactividad;
   }

   public void setFechaInactividad(Date fechaInactividad) 
   {
      this.fechaInactividad = fechaInactividad;
   }
   
   //==========================================
   // Methods
   //==========================================
   
   /**
    * Compara nombre/localizacion para determinar si es la misma aula
    */
   public Boolean compare(Aula aula) {
       if(!this.getNombre().equalsIgnoreCase(aula.getNombre()))             {return false;}
       if(!this.getLocalizacion().equalsIgnoreCase(aula.getLocalizacion())) {return false;}
       return true;
   }
   
   /**
    * Convierte la instancia en una cadena que representa el objeto.
    */
   @Override
   public String toString()
   {
      return this.nombre;
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
      this.descripcion = "";
   }
}
