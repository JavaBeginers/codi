package edu.uoc.tdp.pac4.exceptions;

/**
 * Implementa la excepción que se produce cuando, el mismo día, se crean dos (o más) peticiones para la misma aula y recurso.
 *
 * En realidad, esta restricción (que no viene en el enunciado) se produce por la restricción que impone el campo DATE
 * dentro de la clave principal (lo mejor hubiera sido un TIMESTAMP, por ejemplo).
 * 
 * @author JavaBeginers
 */
public class DuplicatedRequestException extends Exception 
{
   /** Serial versuin UID */
   private static final long serialVersionUID = 1L;
   
   /**
    * Constructor de la clase.
    */
   public DuplicatedRequestException() 
   {
      super();
   }

   /**
    * Constructor de la clase.
    */
   public DuplicatedRequestException(String msg) 
   {
      super(msg);
      System.out.println(msg);
   }
}
