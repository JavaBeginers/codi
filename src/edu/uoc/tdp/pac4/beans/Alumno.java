package edu.uoc.tdp.pac4.beans;

/**
 * Implementa un alumno.
 * 
 * @author JavaBeginers
 */
public class Alumno extends Usuario implements java.io.Serializable 
{
   public static final int ROL_ALUMNO = 4;
   
   @Override
   public String toString()
   {
      return this.getApellidos() + ", " + this.getNombre();
   }
}
