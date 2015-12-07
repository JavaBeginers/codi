package edu.uoc.tdp.pac4.exceptions;

/**
 * Indica que el recuento para un determinado grupo y dia ya existe.
 * 
 * @author eSupport Netbeans
 */
public class AssistanceAlreadyCountedException extends Exception
{
   public AssistanceAlreadyCountedException()
   {
      super();
   }
   
   public AssistanceAlreadyCountedException(String msg)
   {
      super(msg);
   }
}
