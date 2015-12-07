package edu.uoc.tdp.pac4.beans;

/**
 * Implementa un profesor.
 * 
 * @author JavaBeginers
 */
public class Profesor extends Usuario implements java.io.Serializable 
{
   public static final int ROL_PROFESOR = 3;
   
   @Override
   public String toString()
   {
      return this.getApellidos() + ", " + this.getNombre();
   }
}
