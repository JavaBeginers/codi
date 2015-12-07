package edu.uoc.tdp.pac4.exceptions;

/**
 * Implementa una excepción para controlar el caso que no se encuentra grupo para ciertas operaciones.
 * @author eSupport Netbeans
 */
public class NoGroupFoundException extends Exception
{
   public NoGroupFoundException()
   {
      super();
   }
   
   public NoGroupFoundException(String message)
   {
      super(message);
   }
}
