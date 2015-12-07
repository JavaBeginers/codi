package edu.uoc.tdp.pac4.beans;

/**
 * Implementa una petición de recursos para una aula.
 * 
 * @author JavaBeginers
 */
public class PeticionRecursoDetalle extends PeticionRecurso
{
   private String nomAula;
   private String nomRecurso;
   private int stockReal;

   /**
    * Constructor de la clase.
    */
   public PeticionRecursoDetalle() { }
   
   public int getStockReal() 
   {
      return stockReal;
   }

   public void setStockReal(int stockReal) 
   {
      this.stockReal = stockReal;
   }

   public String getNomAula() 
   {
      return nomAula;
   }

   public void setNomAula(String nomAula) 
   {
      this.nomAula = nomAula;
   }

   public String getNombreRecurso() 
   {
      return nomRecurso;
   }

   public void setNombreRecurso(String nomRecurso) 
   {
      this.nomRecurso = nomRecurso;
   }
   
   
   
}
