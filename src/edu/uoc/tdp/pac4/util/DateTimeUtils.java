package edu.uoc.tdp.pac4.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementa utilidades referentes a fecha/hora.
 * 
 * @author eSupport Netbeans
 */
public class DateTimeUtils
{
   /**
    * Indica si una cadena de texto es convertible al tipo {@link Date}.
    * 
    * @param date Una cadena que contiene una fecha en formato texto.
    * @return {@code true} si la cadena es convertible o {@code false} en cualquier otro caso.
    */
   public static boolean isDate(String date)
   {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      
      try
      {
         sdf.parse(date);
         return true;
      }
      catch (Exception ex)
      {
         return false;
      }
   }
   
   /**
    * Convierte una cadena de texto a una fecha.
    * 
    * @param date Una cadena que contiene una fecha en formato texto.
    * @return Una instancia de {@link Date} si la conversión es correcta o {@code null} en cualquier otro caso.
    */
   public static Date strToDate(String date)
   {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      
      try
      {
         return sdf.parse(date);
      }
      catch (Exception ex)
      {
         return null;
      }
   }
   
   /**
    * Indica si una cadena de texto es convertible al tipo {@link Date}.
    * 
    * @param time Una cadena que contiene una hora en formato texto.
    * @return {@code true} si la cadena es convertible o {@code false} en cualquier otro caso.
    */
   public static boolean isTime(String time)
   {
      SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
      
      try
      {
         sdf.parse(time);
         return true;
      }
      catch (Exception ex)
      {
         return false;
      }
   }
   
   /**
    * Convierte una cadena de texto a una fecha.
    * 
    * @param date Una cadena que contiene una hora en formato texto.
    * @return Una instancia de {@link Date} si la conversión es correcta o {@code null} en cualquier otro caso.
    */
   public static Date strToTime(String time)
   {
      SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
      
      try
      {
         return sdf.parse(time);
      }
      catch (Exception ex)
      {
         return null;
      }
   }
}
