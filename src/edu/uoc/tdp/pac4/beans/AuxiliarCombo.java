/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;

   /*************************************
   *****************AULA*****************
   ***@AUTHOR: Cristian - JavaBeginers***
   **************************************/
public class AuxiliarCombo extends IdentifiableObject implements Serializable{

   /** Serial version UID */
   private static final long serialVersionUID = 1L;
   
   private int id;
   private String nombre;

   /*************************************
   **********CONSTRUCTOR*****************
   **************************************/
   
   public AuxiliarCombo() 
   {
       this.id=0;
       this.nombre="";
   }

   /*************************************
   **********PROPERTIES*****************
   **************************************/    
      public int getId(){
       return id;
   }
   
   public void setId(int idn){
       this.id=idn;
   }
   
      public String getNombre(){
       return nombre;
   }
   
   public void setNombre(String nom){
       this.nombre=nom;
   }
   
   
}
