package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;
import java.util.Date;

   /*************************************
   ***************RECURSO****************
   ***@AUTHOR: Cristian - JavaBeginers***
   **************************************/
public class Recurso implements Serializable 
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;

   private int idRecurso;
   private String nombreRecurso;
   private int aulaRecurso;
   private String codigoRecurso;
   private String descripcionRecurso;
   private Date altaRecurso;
   private Date bajaRecurso;

    /*************************************
   **********CONSTRUCTOR*****************
   **************************************/
   public Recurso() { }
   
  
   public Recurso(int id_recurso, String nombre_recurso, int aula_recurso, String codigo_recurso, Date fecha_alta, Date fecha_baja) 
   {
      this.idRecurso = id_recurso;
      this.nombreRecurso = nombre_recurso;
      this.aulaRecurso = aula_recurso;
      this.codigoRecurso=codigo_recurso;
      this.altaRecurso=fecha_alta;
      this.bajaRecurso=fecha_baja;
   }

      /*************************************
   **********PROPERTIES*****************
   **************************************/
   public int getIdRecurso() 
   {
      return idRecurso;
   }

   public String getNombreRecurso() 
   {
      return nombreRecurso;
   }

   public String getCodigoRecurso() 
   {
      return codigoRecurso;
   }
   
   public String getDescripcionRecurso() 
   {
      return descripcionRecurso;
   }
   
   public int getAulaRecurso()
   {
       return aulaRecurso;
   }

   public Date getFechaAlta() 
   {
      return altaRecurso;
   }

   public Date getFechaBaja() 
   {
      return bajaRecurso;
   }

   public void setIdRecurso(int id_recurso) 
   {
      this.idRecurso = id_recurso;
   }

   public void setNombreRecurso(String nombre_recurso) 
   {
      this.nombreRecurso = nombre_recurso;
   }
   
   public void setDescripcionRecurso(String descripcion_recurso) 
   {
      this.descripcionRecurso = descripcion_recurso;
   }

   public void setCodigoRecurso(String codigo_recurso) 
   {
      this.codigoRecurso = codigo_recurso;
   }
   
    public void setAulaRecurso(int aula_recurso) 
   {
      this.aulaRecurso = aula_recurso;
   }

   public void setFechaAltaRecurso(Date fecha_alta) 
   {
      this.altaRecurso=fecha_alta;
   }

   public void setFechaBajaRecurso(Date fecha_baja) 
   {
      this.bajaRecurso=fecha_baja;
   }
}
