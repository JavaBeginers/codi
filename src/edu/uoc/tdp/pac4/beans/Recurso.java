package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Implementa un recurso.
 * 
 * @author JavaBeginers
 */
public class Recurso implements Serializable 
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;

   private int id_recurso;
   private String nombre_recurso;
   private int cantidad_stock;
   private Date fecha_ultima_entrada_stock;
   private Date fecha_ultima_salida_stock;

   /**
    * Constructor de la clase.
    */
   public Recurso() { }
   
   /**
    * Constructor de la clase.
    */
   public Recurso(int id_recurso, String nombre_recurso, int cantidad_stock, Date fecha_ultima_entrada_stock, Date fecha_ultima_salida_stock) 
   {
      this.id_recurso = id_recurso;
      this.nombre_recurso = nombre_recurso;
      this.cantidad_stock = cantidad_stock;
      this.fecha_ultima_entrada_stock = fecha_ultima_entrada_stock;
      this.fecha_ultima_salida_stock = fecha_ultima_salida_stock;
   }

   public int getIdRecurso() 
   {
      return id_recurso;
   }

   public String getNombreRecurso() 
   {
      return nombre_recurso;
   }

   public int getCantidadStock() 
   {
      return cantidad_stock;
   }

   public Date getFechaUltimaEntradaStock() 
   {
      return fecha_ultima_entrada_stock;
   }

   public Date getFechaUltimaSalidaStock() 
   {
      return fecha_ultima_salida_stock;
   }

   public void setIdRecurso(int id_recurso) 
   {
      this.id_recurso = id_recurso;
   }

   public void setNombreRecurso(String nombre_recurso) 
   {
      this.nombre_recurso = nombre_recurso;
   }

   public void setCantidadStock(int cantidad_stock) 
   {
      this.cantidad_stock = cantidad_stock;
   }

   public void setFechaUltimaEntradaStock(Date fecha_ultima_entrada_stock) 
   {
      this.fecha_ultima_entrada_stock = fecha_ultima_entrada_stock;
   }

   public void setFechaUltimaSalidaStock(Date fecha_ultima_salida_stock) 
   {
      this.fecha_ultima_salida_stock = fecha_ultima_salida_stock;
   }

   @Override
   public String toString() 
   {
      return this.nombre_recurso + " (" + this.cantidad_stock + ")";
   }
}
