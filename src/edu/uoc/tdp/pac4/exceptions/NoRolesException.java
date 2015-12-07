package edu.uoc.tdp.pac4.exceptions;

/**
 * Indica que ya existe una solicitud de matrícula previa para un curso.
 * @author eSupport Netbeans
 */
public class NoRolesException extends Exception
{
   public NoRolesException()
   {
      super();
   }
   
   public NoRolesException(String msg)
   {
      super(msg);
   }
}
