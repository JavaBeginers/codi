package edu.uoc.tdp.pac4.exceptions;

/**
 * Excepción que indica que el grupo ya existe y se puede dar en los siguientes casos:
 * 
 * - Per cada curs, es podrà obrir un màxim de dos grups: un de matí i un de tarda.
 * - Sólo puede haber un grupo por turno y curso.
 * 
 * @author JavaBeginers
 */
public class GroupAlreadyExistsException extends Exception
{
   public GroupAlreadyExistsException()
   {
      super();
   }
   
   public GroupAlreadyExistsException(String msg)
   {
      super(msg);
   }
}
