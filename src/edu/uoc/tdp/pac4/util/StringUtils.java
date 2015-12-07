package edu.uoc.tdp.pac4.util;

/**
 * Utilidades para cadenas de texto (String).
 * 
 * @author eSupport Netbeans
 */
public class StringUtils 
{
   /**
	 * Permet esbrinar si una cadena és buida o nul·la. 
	 * 
	 * @param text Cadena de caràcters a evaluar.
	 * @return {@code true} si la cadena és buida o nul·la o {@code false} en qualsevol altre cas.
	 */
	public static boolean isNullOrEmpty(String text)
	{
		if (text == null) 
      {
         return true;
      }
		
		return (text.isEmpty());
	}
	
	/**
	 * Permet esbrinar si una cadena és buida o nul·la. 
	 * 
	 * @param text Cadena de caràcters a evaluar.
	 * @return {@code true} si la cadena és buida o nul·la o {@code false} en qualsevol altre cas.
	 */
	public static boolean isNullOrEmptyTrim(String text)
	{
		if (text == null) 
      {
         return true;
      }
		
		text = text.trim();
		
		return (text.isEmpty());
	}
   
   /**
    * Formatea una cadena para ser usada en una sentencia SQL ANSI.
    */
   public static String formatToSql(String text)
   {
      return text.replace("'", "''");
   }
}
