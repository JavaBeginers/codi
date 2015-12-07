package edu.uoc.tdp.pac4.util;

import edu.uoc.tdp.pac4.beans.IdentifiableObject;

/**
 * Implementa un elemento de JComboBox.
 * @author eSupport Netbeans
 */
public class ComboItem extends IdentifiableObject
{
   private String name;
   
   public ComboItem(String name, int id)
   {
      this.setId(id);
      this.name = name;
   }

   public String getName() 
   {
      return name;
   }

   public void setName(String name) 
   {
      this.name = name;
   }
   
   @Override
   public String toString()
   {
      return this.name;
   }
}
