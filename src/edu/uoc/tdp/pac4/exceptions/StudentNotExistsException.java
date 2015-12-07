package edu.uoc.tdp.pac4.exceptions;

/**
 * Indica que un usuario no existe.
 * @author eSupport Netbeans
 */
public class StudentNotExistsException extends Exception
{
   public StudentNotExistsException()
   {
      super();
   }
   
   public StudentNotExistsException(String msg)
   {
      super(msg);
   }
}
