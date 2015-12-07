/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.beans;

/**
 *
 * @author JavaBeginers
 */
public class Administrador extends Usuario implements java.io.Serializable{
    
   public static final int ROL_ADMINSTRADOR = 1;
   
   @Override
   public String toString()
   {
      return this.getApellidos() + ", " + this.getNombre();
   }
}
