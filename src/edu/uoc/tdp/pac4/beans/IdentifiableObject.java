package edu.uoc.tdp.pac4.beans;

/**
 * Implementa un objeto identificable mediante ID.
 * 
 * @author JavaBeginers
 */
public class IdentifiableObject implements java.io.Serializable 
{
   private int id;
   
   //===========================================
   // Constructors
   //===========================================
   
   /**
    * Constructor de la clase.
    */
   public IdentifiableObject()
   {
      this.id = 0;
   }

   //===========================================
   // Properties
   //===========================================
   
   public int getId() 
   {
      return id;
   }

   public void setId(int id) 
   {
      this.id = id;
   }
   
   //===========================================
   // Static members
   //===========================================
   
   /**
    * Obtiene una instancia de una lista a partir de su ID.
    * 
    * @param list Lista de instancias identificables.
    * @return Una instancia que implemente {@link IdentifiableObject}.
    */
   public static Object getObjectFromList(Object[] list, int id)
   {
      for (Object obj : list)
      {
         if (obj instanceof IdentifiableObject)
         {
            if (((IdentifiableObject) obj).getId() == id)
            {
               return obj;
            }
         }
      }
      return null;
   }
}
