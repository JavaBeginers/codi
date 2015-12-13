package edu.uoc.tdp.pac4.beans;

import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.sql.Time;
import java.util.Date;
import edu.uoc.tdp.pac4.eAcademiaEU;

/**
 * Representa una matricula de un alumno a un grupo.
 * 
 * @author JavaBeginers
 */
public class Matricula extends IdentifiableObject implements java.io.Serializable 
{
   // peticionid ???
   private int matriculaid;
   private int grupoid;
   private int estadoid;
   private int usuarioid;
   private Date fechaModificacion;
   private Date fechaAlta;
   
   // Propiedades de solo lectura
   private String actividadNombre;
   private String grupoNombre;
   private String usuarioNombre;
   private String usuarioNif;
   private Date fechaInicio;
   private Date fechaFinal;
   private Time horaInicio;
   private int asisRequerida;
   private int asis;
   private int noAsis;
   private int turno;
   private int idMatricula;
   private int solapado;
   
   // Definición de turnos
   public static final int MATRICULA_ESTADO_PENDIENTE = 2;
   public static final int MATRICULA_ESTADO_ACEPTADA = 1;
   public static final int MATRICULA_ESTADO_BAJA = 0;
   public static final int MATRICULA_ESTADO_ANULADA = 3;
   
   //===========================================
   // Constructors
   //===========================================
   
   /**
    * Constructor de la clase.
    */
   public Matricula()
   {
      this.matriculaid = 0;
      this.grupoid = 0;
      this.estadoid = 0;
      this.usuarioid = 0;
      this.solapado = 0;
      this.fechaModificacion = null;
      this.fechaAlta = new Date();
      
      this.actividadNombre = "";
      this.grupoNombre = "";
      this.usuarioNombre = "";
      this.usuarioNif = "";
   }

   //===========================================
   // Properties
   //===========================================

   public int getActividadId() 
   {
      return matriculaid;
   }

   public void setactividadId(int actividadId) 
   {
      this.matriculaid = actividadId;
   }

   public int getGrupoId() 
   {
      return grupoid;
   }

   public void setGrupoId(int grupoId) 
   {
      this.grupoid = grupoId;
   }

   public int getEstado() 
   {
      return estadoid;
   }

   public void setEstado(int estado) 
   {
      this.estadoid = estado;
   }

   public int getUsuarioId() 
   {
      return usuarioid;
   }

   public void setUsuarioId(int usuarioId) 
   {
      this.usuarioid = usuarioId;
   }

   public Date getFechaModificacion() 
   {
      return fechaModificacion;
   }

   public void setFechaModificacion(Date fechaModificacion) 
   {
      this.fechaModificacion = fechaModificacion;
   }

   public Date getFechaAlta() 
   {
      return fechaAlta;
   }

   public void setFechaAlta(Date fechaAlta) 
   {
      this.fechaAlta = fechaAlta;
   }

   public String getActividadNombre() 
   {
      return actividadNombre;
   }

   public void setActividadNombre(String actividadNombre) 
   {
      this.actividadNombre = actividadNombre;
   }

   public String getGrupoNombre() 
   {
      return grupoNombre;
   }

   public void setGrupoNombre(String grupoNombre) 
   {
      this.grupoNombre = grupoNombre;
   }

   public String getUsuarioNombre() 
   {
      return usuarioNombre.replace("_", " ");
   }

   public void setUsuarioNombre(String usuarioNombre) 
   {
      this.usuarioNombre = usuarioNombre;
   }

   public String getUsuarioNif() 
   {
      return usuarioNif;
   }

   public void setUsuarioNif(String nif) 
   {
      this.usuarioNif = nif;
   }
   
    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() 
    {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) 
    {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() 
    {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) 
    {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the asisRequerida
     */
    public int getAsisRequerida() 
    {
        return asisRequerida;
    }

    /**
     * @param asisRequerida the asisRequerida to set
     */
    public void setAsisRequerida(int asisRequerida) 
    {
        this.asisRequerida = asisRequerida;
    }

    /**
     * @return the asis
     */
    public int getAsis() 
    {
        return asis;
    }

    /**
     * @param asis the asis to set
     */
    public void setAsis(int asis) 
    {
        this.asis = asis;
    }

    /**
     * @return the noAsis
     */
    public int getNoAsis() 
    {
        return noAsis;
    }

   /**
    * @param noAsis the noAsis to set
    */
   public void setNoAsis(int noAsis) 
   {
      this.noAsis = noAsis;
   }
   
   
    /**
     * @return the turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }
   
     /**
     * @return the idMatricula
     */
    public int getIdMatricula() {
        return idMatricula;
    }

    /**
     * @param idMatricula the idMatricula to set
     */
    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }
    
       /**
     * @return the solapado
     */
    public int getSolapado() {
        return solapado;
    }

    /**
     * @param solapado the solapado to set
     */
    public void setSolapado(int solapado) {
        this.solapado = solapado;
    }
    
    /**
     * @return the horaInicio
     */
    public Time getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }


   //===========================================
   // Methods
   //===========================================
 
   /**
    * Convierte un código de estado a un texto descriptivo.
    * 
    * @param status Código de estado. Corresponde a una de las constantes {@code MATRICULA_ESTADO_XXX}.
    * @return Una cadena que corresponde al nombre del estado especificado.
    */
   public static String getStatusName(int status, LanguageUtils language)
   {
      switch (status)
      {
         case Matricula.MATRICULA_ESTADO_ACEPTADA:
            return language.getProperty(eAcademiaEU.MATRICULA_STATUS_ACEPTADA);
         case Matricula.MATRICULA_ESTADO_BAJA:
            return language.getProperty(eAcademiaEU.MATRICULA_STATUS_BAJA);
         case Matricula.MATRICULA_ESTADO_PENDIENTE:
            return language.getProperty(eAcademiaEU.MATRICULA_STATUS_PENDIENTE);
         case Matricula.MATRICULA_ESTADO_ANULADA:
            return language.getProperty(eAcademiaEU.MATRICULA_STATUS_ANULADA);
         default:
            return "";
      }
   }

    

 

  
   
    
}
