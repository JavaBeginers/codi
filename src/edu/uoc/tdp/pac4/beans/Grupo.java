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
   private boolean grupoVigente;
   private int turno;
   private int idAula;
   private int idProfesor;
   private int idCurso;
   
   // Propiedades de sólo lectura
   private String nombreAula;
   private String nombreCurso;
   private String nombreProfesor;
   private String bibliografiaCurso;
   private String planDocenteCurso;
   private int minimoAsistenciaCurso;
   private Date fechaInicioCurso;
   private Date fechaFinCurso;
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
      this.grupoVigente = false;
      this.turno = 0;
      this.idAula = 0;
      this.idProfesor = 0;
      this.idCurso = 0;
      
      this.nombreAula = "";
      this.nombreCurso = "";
      this.nombreProfesor = "";
      this.minimoAsistenciaCurso = 0;
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
      return grupoVigente;
   }

   public void setGrupoVigente(boolean grupoVigente)
   {
      this.grupoVigente = grupoVigente;
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

   public int getIdCurso()
   {
      return idCurso;
   }

   public void setIdCurso(int idCurso)
   {
      this.idCurso = idCurso;
   }

   public String getNombreAula() 
   {
      return nombreAula;
   }

   public void setNombreAula(String nombreAula)
   {
      this.nombreAula = nombreAula;
   }

   public String getNombreCurso() 
   {
      return nombreCurso;
   }

   public void setNombreCurso(String nombreCurso)
   {
      this.nombreCurso = nombreCurso;
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
   
   public String getBibliografiaCurso() 
   {
      return bibliografiaCurso;
   }

   public void setBibliografiaCurso(String bibliografiaCurso)
   {
      this.bibliografiaCurso = bibliografiaCurso;
   }
   
   public String getPlanDocenteCurso() 
   {
      return planDocenteCurso;
   }

   public void setPlanDocenteCurso(String planDocenteCurso)
   {
      this.planDocenteCurso = planDocenteCurso;
   }
   
   public int getMinimoAsistenciaCurso() 
   {
      return minimoAsistenciaCurso;
   }

   public void setMinimoAsistenciaCurso(int minimoAsistenciaCurso)
   {
      this.minimoAsistenciaCurso = minimoAsistenciaCurso;
   }

   // Read only properties
   
   public Date getFechaInicioCurso() 
   {
      return fechaInicioCurso;
   }

   public void setFechaInicioCurso(Date fechaInicioCurso) 
   {
      this.fechaInicioCurso = fechaInicioCurso;
   }

   public Date getFechaFinCurso() 
   {
      return fechaFinCurso;
   }

   public void setFechaFinCurso(Date fechaFinCurso) 
   {
      this.fechaFinCurso = fechaFinCurso;
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
