package edu.uoc.tdp.pac4.exceptions;

/**
 * Implementa una excepción que indica que no se pueden hacer más peticiones por una determinada aula.
 * 
 * @author eSupport Netbeans
 */
public class TooManyRequestsException extends Exception 
{
   /** Serial versuin UID */
   private static final long serialVersionUID = 1L;
   
   /**
    * Constructor de la clase.
    */
   public TooManyRequestsException() 
   {
      super();
   }

   /**
    * Constructor de la clase.
    */
   public TooManyRequestsException(String msg) 
   {
      super(msg);
      System.out.println(msg);
   }
}
