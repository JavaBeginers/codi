/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.beans;

/**
 *
 * @author JavaBeginers
 */
public class PersonalAcademico extends Usuario implements java.io.Serializable{
    
   public static final int ROL_PERSONALACADEMICO = 2;
   
   @Override
   public String toString()
   {
      return this.getApellidos() + ", " + this.getNombre();
   }
}
