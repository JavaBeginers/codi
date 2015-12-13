package edu.uoc.tdp.pac4.beans;

import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.util.Date;
import edu.uoc.tdp.pac4.eAcademiaEU;

/**
 * Representa un grupo de alumnos del centro.
 * 
 * @author JavaBeginers
 */
public class Grupo extends IdentifiableObject implements java.io.Serializable 
{
   private String nombre;
   private int maxAlumnos;
   private int plazasDisponibles;
   private boolean grupovigente;
   private int turno;
   private int idAula;
   private int idProfesor;
   private int idcurso;
   
   // Propiedades de sólo lectura
   private String nombreAula;
   private String nombreActividad;
   private String nombreProfesor;
   private String bibliografiaActividad;
   private String planDocenteActividad;
   private int minimoAsistenciaActividad;
   private Date fechaInicioActividad;
   private Date fechaFinActividad;
   private boolean asistenciaAbierta;
   
   // Definición de turnos
   public static final int TURNO_MANANA = 1;
   public static final int TURNO_TARDE = 2;
   
    // Definición de estados del periodo de marcaje de asistencia
   public static final boolean PERIODO_ABIERTO = true;
   public static final boolean PERIODO_CERRADO = false;
   
   //===========================================
   // Constructors
   //===========================================
   
   /**
    * Constructor de la clase.
    */
   public Grupo()
   {
      this.nombre = "";
      this.maxAlumnos = 0;
      this.plazasDisponibles = 0;
      //this.fechaInicio = null;
      //this.fechaFin = null;
      this.grupovigente = false;
      this.turno = 0;
      this.idAula = 0;
      this.idProfesor = 0;
      this.idcurso = 0;
      
      this.nombreAula = "";
      this.nombreActividad = "";
      this.nombreProfesor = "";
      this.minimoAsistenciaActividad = 0;
   }

   //===========================================
   // Properties
   //===========================================
   
   public String getNombre() 
   {
      return nombre;
   }

   public void setNombre(String nombre) 
   {
      this.nombre = nombre;
   }

   public int getMaxAlumnos() 
   {
      return maxAlumnos;
   }

   public void setMaxAlumnos(int maxAlumnos) 
   {
      this.maxAlumnos = maxAlumnos;
   }

   public int getPlazasDisponibles() 
   {
      return plazasDisponibles;
   }

   public void setPlazasDisponibles(int plazasDisponibles) 
   {
      this.plazasDisponibles = plazasDisponibles;
   }

   public boolean isGrupoVigente() 
   {
      return grupovigente;
   }

   public void setGrupoVigente(boolean grupoVigente)
   {
      this.grupovigente = grupoVigente;
   }

   public int getTurno()
   {
      return turno;
   }

   public void setTurno(int turno)
   {
      this.turno = turno;
   }

   public int getIdAula()
   {
      return idAula;
   }

   public void setIdAula(int idAula) 
   {
      this.idAula = idAula;
   }

   public int getIdProfesor()
   {
      return idProfesor;
   }

   public void setIdProfesor(int idProfesor) 
   {
      this.idProfesor = idProfesor;
   }

   public int getIdActividad()
   {
      return idcurso;
   }

   public void setIdActividad(int idActividad)
   {
      this.idcurso = idActividad;
   }

   public String getNombreAula() 
   {
      return nombreAula;
   }

   public void setNombreAula(String nombreAula)
   {
      this.nombreAula = nombreAula;
   }

   public String getNombreActividad() 
   {
      return nombreActividad;
   }

   public void setNombreActividad(String nombreActividad)
   {
      this.nombreActividad = nombreActividad;
   }

   public String getNombreProfesor() 
   {
      return nombreProfesor.replace("_", " ");
   }

   public void setNombreProfesor(String nombreProfesor)
   {
      this.nombreProfesor = nombreProfesor;
   }
   
   public void setNombreProfesor(String nombre, String apellidos)
   {
      if (nombre == null && apellidos == null)
      {
         this.nombreProfesor = "- no asignado -";
      }
      else
      {
         this.nombreProfesor = apellidos + ", " + nombre;
      }
   }
   
   public String getBibliografiaActividad() 
   {
      return bibliografiaActividad;
   }

   public void setBibliografiaActividad(String bibliografiaActividad)
   {
      this.bibliografiaActividad = bibliografiaActividad;
   }
   
   public String getPlanDocenteActividad() 
   {
      return planDocenteActividad;
   }

   public void setPlanDocenteActividad(String planDocenteActividad)
   {
      this.planDocenteActividad = planDocenteActividad;
   }
   
   public int getMinimoAsistenciaActividad() 
   {
      return minimoAsistenciaActividad;
   }

   public void setMinimoAsistenciaActividad(int minimoAsistenciaActividad)
   {
      this.minimoAsistenciaActividad = minimoAsistenciaActividad;
   }

   // Read only properties
   
   public Date getFechaInicioActividad() 
   {
      return fechaInicioActividad;
   }

   public void setFechaInicioActividad(Date fechaInicioActividad) 
   {
      this.fechaInicioActividad = fechaInicioActividad;
   }

   public Date getFechaFinActividad() 
   {
      return fechaFinActividad;
   }

   public void setFechaFinActividad(Date fechaFinActividad) 
   {
      this.fechaFinActividad = fechaFinActividad;
   }
   
   public boolean getAsistenciaAbierta() 
   {
      return asistenciaAbierta;
   }

   public void setAsistenciaAbierta(boolean asistenciaAbierta) 
   {
      this.asistenciaAbierta = asistenciaAbierta;
   }
   
   //===========================================
   // Methods
   //===========================================
   
   /**
    * Convierte la instancia en una cadena de texto.
    */
   @Override
   public String toString()
   {
      return this.getNombre();
   }
   
   //===========================================
   // Static methods
   //===========================================
   
   /**
    * Devuelve el nombre asociado a un turno.
    * 
    * @param turno Identificador del turno.
    * @return Una cadena que representa el turno.
    */
   public static String getTurnoName(int turno, LanguageUtils language)
   {
      if (turno == Grupo.TURNO_MANANA)
      {
         return language.getProperty(eAcademiaEU.GRUPO_TURNO_MANANA);
      }
      else
      {
         return language.getProperty(eAcademiaEU.GRUPO_TURNO_TARDE);
      }
   }
   
   /**
    * Devuelve el nombre asociado al estado de marcaje de asistencia
    * 
    * @param estado Identificador del estado
    * @return Una cadena que representa el estado (abierto o cerrado)
    */
   public static String getEstadoAsistenciaName(boolean estado, LanguageUtils language)
   {
      if (estado == Grupo.PERIODO_ABIERTO)
      {
         return language.getProperty(eAcademiaEU.GRUPO_ASISTENCIA_ABIERTO);
      }
      else
      {
         return language.getProperty(eAcademiaEU.GRUPO_ASISTENCIA_CERRADO);
      }
   }
   
}
