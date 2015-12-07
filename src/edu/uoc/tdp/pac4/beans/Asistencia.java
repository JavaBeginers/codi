package edu.uoc.tdp.pac4.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa una contabilización de asistencia.
 * 
 * @author JavaBeginers
 */
public class Asistencia implements java.io.Serializable 
{
   private int id;
   private int idGrupo;
   private Date date;
   private Date horaInicio;
   private Date horaFin;
   private Integer totalAssistencia;
   private Integer totalFaltas;
   
   //==========================================
   // Constructors
   //==========================================
   
   /**
    * Constructor de la clase.
    */
   public Asistencia()
   {
      initialize();
   }

   //==========================================
   // Properties
   //==========================================
   
   public int getId() 
   {
      return id;
   }

   public void setId(int id) 
   {
      this.id = id;
   }

   public int getIdGrupo() 
   {
      return idGrupo;
   }

   public void setIdGrupo(int idGrupo) 
   {
      this.idGrupo = idGrupo;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date) 
   {
      this.date = date;
   }

   public Date getHoraInicio()
   {
      return horaInicio;
   }

   public void setHoraInicio(Date horaInicio)
   {
      this.horaInicio = horaInicio;
   }

   public Date getHoraFin()
   {
      return horaFin;
   }

   public void setHoraFin(Date horaFin) 
   {
      this.horaFin = horaFin;
   }

   public Integer getTotalAssistencia() 
   {
      return totalAssistencia;
   }

   public void setTotalAssistencia(Integer totalAssistencia)
   {
      this.totalAssistencia = totalAssistencia;
   }

   public Integer getTotalFaltas() 
   {
      return totalFaltas;
   }

   public void setTotalFaltas(Integer totalFaltas) 
   {
      this.totalFaltas = totalFaltas;
   }
   
   //==========================================
   // Methods
   //==========================================
   
   /**
    * Convierte la instancia en una cadena que representa el objeto.
    */
   @Override
   public String toString()
   {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      
      return sdf.format(this.date);
   }
   
   //==========================================
   // Private members
   //==========================================
   
   /**
    * Inicializa la instancia
    */
   private void initialize()
   {
      this.id = 0;
      this.idGrupo = 0;
      this.date = null;
      this.horaInicio = null;
      this.horaFin = null;
      this.totalAssistencia = 0;
      this.totalFaltas = 0;
   }
}
