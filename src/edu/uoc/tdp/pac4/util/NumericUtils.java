package edu.uoc.tdp.pac4.util;

/**
 * Clase helper que contiene utilidades numéricas.
 * @author eSupport Netbeans
 */
public class NumericUtils 
{
   /**
    * Indica si una cadena es un número entero.
    */
   public static boolean isInteger(String text)
   {
      if (text == null ||  text.trim().equals(""))
      {
         return false;
      }
      
      try
      {
         Integer.parseInt(text);
         return true;
      }
      catch (Exception e)
      {
         return false;
      }
   }
}
