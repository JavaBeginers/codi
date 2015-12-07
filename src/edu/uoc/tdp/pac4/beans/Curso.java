package edu.uoc.tdp.pac4.beans;

import java.util.Date;

/**
 * Representa un curso del centro.
 * @author JavaBeginers
 */
public class Curso extends IdentifiableObject implements java.io.Serializable 
{
   private String nombre;
   private int minasistencia;
   private String plandocente;
   private String bibliografia;
   private boolean activo;
   private Date fechainicio;
   private Date fechafin;
   private Date fechainactividad;
   private int idprofesor;
   private int idaula;   
  
   public Curso()
   {
      this.nombre = "";
      this.minasistencia = 0;
      this.plandocente = "";
      this.bibliografia = "";
      this.activo = false;
      this.fechainactividad = null;
      this.idprofesor = 0;
      this.idaula = 0;
   }
   
   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public int getMinasistencia() {
      return minasistencia;
   }

   public void setMinasistencia(int minasistencia) {
      this.minasistencia = minasistencia;
   }

   public String getPlandocente() {
      return plandocente;
   }

   public void setPlandocente(String plandocente) {
      this.plandocente = plandocente;
   }

   public String getBibliografia() {
      return bibliografia;
   }

   public void setBibliografia(String bibliografia) {
      this.bibliografia = bibliografia;
   }

   public boolean isActivo() {
      return activo;
   }
   
   public int isActivoBit() {
       if (this.isActivo()) {
           return 1;
       }
       else {
           return 0;
       }
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

   public Date getFechaInicio() {
      return fechainicio;
   }

   public void setFechaInicio(Date fechainicio) {
      this.fechainicio = fechainicio;
   }
   
   public Date getFechaFin() {
      return fechafin;
   }

   public void setFechaFin(Date fechafin) {
      this.fechafin = fechafin;
   }
   
   public Date getFechainactividad() {
      return fechainactividad;
   }

   public void setFechainactividad(Date fechainactividad) {
      this.fechainactividad = fechainactividad;
   }

   public int getIdprofesor() {
      return idprofesor;
   }

   public void setIdprofesor(int idprofesor) {
      this.idprofesor = idprofesor;
   }

   public int getIdaula() {
      return idaula;
   }

   public void setIdaula(int idaula) {
      this.idaula = idaula;
   }
   
   @Override
   public String toString()
   {
      return this.getNombre();
   }
}
