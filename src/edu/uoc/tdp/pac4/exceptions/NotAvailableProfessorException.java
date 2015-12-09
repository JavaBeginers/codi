package edu.uoc.tdp.pac4.exceptions;

/**
 * Excepción que indica que el profesor ya tiene asignado otro curso para las mismas fechas y turno.
 * 
 * @author JavaBeginers
 */
public class NotAvailableProfessorException extends Exception
{
   public NotAvailableProfessorException()
   {
      super();
   }
   
   public NotAvailableProfessorException(String msg)
   {
      super(msg);
   }
}
