package edu.uoc.tdp.pac4.exceptions;

/**
 * Indica que ya existe una solicitud de matrícula previa para un curso.
 * @author JavaBeginers
 */
public class ApplicationAlreadyExistsException extends Exception
{
   public ApplicationAlreadyExistsException()
   {
      super();
   }
   
   public ApplicationAlreadyExistsException(String msg)
   {
      super(msg);
   }
}
