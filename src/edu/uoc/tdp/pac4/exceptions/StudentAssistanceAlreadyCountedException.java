package edu.uoc.tdp.pac4.exceptions;

/**
 * Indica que el marcaje de asistencia de un alumno ya se ha realizado.
 * 
 * @author eSupport Netbeans
 */
public class StudentAssistanceAlreadyCountedException extends Exception
{
   public StudentAssistanceAlreadyCountedException()
   {
      super();
   }
   
   public StudentAssistanceAlreadyCountedException(String msg)
   {
      super(msg);
   }
}
