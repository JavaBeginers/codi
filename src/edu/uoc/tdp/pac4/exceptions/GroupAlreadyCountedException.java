package edu.uoc.tdp.pac4.exceptions;

/**
 * Indica que el recuento para un determinado grupo y dia ya existe.
 * 
 * @author eSupport Netbeans
 */
public class GroupAlreadyCountedException extends Exception
{
   public GroupAlreadyCountedException()
   {
      super();
   }
   
   public GroupAlreadyCountedException(String msg)
   {
      super(msg);
   }
}
