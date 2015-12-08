package edu.uoc.tdp.pac4;

/**
 * Declaración de constantes globales del proyecto.
 * 
 * @author JavaBeginers
 */
public interface eAcademiaEU 
{
   //==========================================
   // RMI
   //==========================================
   
   public static final String RMI_URI_BASE = "rmi://localhost/eAcademia/*";   
   public static final String RMI_URI_STOCK = "rmi://localhost/eAcademia/stock";
   public static final String RMI_URI_MANTENIMIENTO = "rmi://localhost/eAcademiaa/mant";
   public static final String RMI_URI_ESTADISTICAS = "rmi://localhost/eAcademia/std";
   public static final String RMI_URI_GESTIONACAD = "rmi://localhost/eAcademia/gest";
   public static final String RMI_URI_CONEXION = "rmi://localhost/eAcademia/conex";
   
   public static final int RMI_PORT = 1099;
   
   //===========================================
   // TEXTOS i18n
   //===========================================

   public static final String FORMAT_SHORTDATE = "format.shdate";
   public static final String FORMAT_TIME = "format.time";
   public static final String FORMAT_NUMERIC_ID = "format.numericId";
   
   public static final String APP_TITLE = "app.title";

   public static final String ERROR_GENERIC = "err.generic";
   public static final String ERROR_DETAILS = "err.detail";
   public static final String ERROR_GROUPALREADYCOUNTED = "err.GroupAlreadyCounted";
   public static final String ERROR_GROUPALREADYEXISTS = "err.GroupAlreadyExists";
   public static final String ERROR_NOTAVAILABLEPROFESSOR = "err.NotAvailableProfessor";
   public static final String ERROR_GROUPNOTEMPTY = "err.GroupNotEmpty";

   public static final String MENU_GESTION = "menu.gestion";
   public static final String MENU_GESTION_RECUENTO = "menu.gestion.recuento";
   public static final String MENU_GESTION_GRUPOS = "menu.gestion.grupos";
   public static final String MENU_GESTION_MATRICULAS = "menu.gestion.matriculas";
   
   public static final String FORM_COMMON_EXPORT = "form.common.export";
   public static final String FORM_COMMON_ACCEPT = "form.common.accept";
   public static final String FORM_COMMON_CANCEL = "form.common.cancel";
   public static final String FORM_COMMON_CLOSE = "form.common.close";
   public static final String FORM_COMMON_CLEAN = "form.common.clean";
   public static final String FORM_COMMON_APPLY = "form.common.apply";

   public static final String FORM_PNLASISTENCIA_TITLE = "form.pnlAsistencia.title";
   public static final String FORM_PNLASISTENCIA_GRUPO = "form.pnlAsistencia.grupo";
   public static final String FORM_PNLASISTENCIA_RECUENTO = "form.pnlAsistencia.recuento";
   public static final String FORM_PNLASISTENCIA_ACTIVIDAD = "form.pnlAsistencia.actividad";
   public static final String FORM_PNLASISTENCIA_PROFESOR = "form.pnlAsistencia.profesor";
   public static final String FORM_PNLASISTENCIA_TURNO = "form.pnlAsistencia.turno";
   public static final String FORM_PNLASISTENCIA_FECHA = "form.pnlAsistencia.fecha";
   public static final String FORM_PNLASISTENCIA_HORAINI = "form.pnlAsistencia.horaIni";
   public static final String FORM_PNLASISTENCIA_HORAFIN = "form.pnlAsistencia.horaFin";
   public static final String FORM_PNLASISTENCIA_SELECTGRUPO = "form.pnlAsistencia.selectGrupo";
   public static final String FORM_PNLASISTENCIA_INITRECUENTO = "form.pnlAsistencia.initRecuento";
   public static final String FORM_PNLASISTENCIA_RECUENTOCLOSE = "form.pnlAsistencia.recuentoClose";
   public static final String FORM_PNLASISTENCIA_RECUENTOFIN = "form.pnlAsistencia.recuentoFin";
   public static final String FORM_PNLASISTENCIA_CONTANDO = "form.pnlAsistencia.counting";
   public static final String FORM_PNLASISTENCIA_BTNRECUENTO = "form.pnlAsistencia.btnRecuento";
   
   public static final String FORM_PNLGROUP_TITLE = "form.pnlGroup.title";
   public static final String FORM_PNLGROUP_NOMBRE = "form.pnlGroup.nombre";
   public static final String FORM_PNLGROUP_ACTIVIDAD = "form.pnlGroup.actividad";
   public static final String FORM_PNLGROUP_PROFESOR = "form.pnlGroup.profesor";
   public static final String FORM_PNLGROUP_AULA = "form.pnlGroup.aula";
   public static final String FORM_PNLGROUP_MAXALUMNOS = "form.pnlGroup.maxAlumnos";
   public static final String FORM_PNLGROUP_TURNO = "form.pnlGroup.turno";
   public static final String FORM_PNLGROUP_MANANA = "form.pnlGroup.turnoManana";
   public static final String FORM_PNLGROUP_TARDE = "form.pnlGroup.turnoTarde";
   public static final String FORM_PNLGROUP_FECHAINI = "form.pnlGroup.fechaIni";
   public static final String FORM_PNLGROUP_FECHAFIN = "form.pnlGroup.fechaFin";
   public static final String FORM_PNLGROUP_SELECTACTIVIDAD = "form.pnlGroup.selectActividad";
   public static final String FORM_PNLGROUP_WARN_NOMBRE = "form.pnlGroup.warning.nombre";
   public static final String FORM_PNLGROUP_WARN_ACTIVIDAD = "form.pnlGroup.warning.actividad";
   public static final String FORM_PNLGROUP_WARN_PROFESOR = "form.pnlGroup.warning.profesor";
   public static final String FORM_PNLGROUP_WARN_AULA = "form.pnlGroup.warning.aula";
   public static final String FORM_PNLGROUP_WARN_MAXALUMNOS = "form.pnlGroup.warning.maxAlumnos";
   
   public static final String FORM_PNLGROUPGESTOR_TITLE = "form.pnlGroupGestor.title";
   public static final String FORM_PNLGROUPGESTOR_ADD = "form.pnlGroupGestor.add";
   public static final String FORM_PNLGROUPGESTOR_EDIT = "form.pnlGroupGestor.edit";
   public static final String FORM_PNLGROUPGESTOR_DELETE = "form.pnlGroupGestor.delete";
   public static final String FORM_PNLGROUPGESTOR_FILTER = "form.pnlGroupGestor.filter";
   public static final String FORM_PNLGROUPGESTOR_ACTIVIDAD = "form.pnlGroupGestor.actividad";
   public static final String FORM_PNLGROUPGESTOR_TURNO = "form.pnlGroupGestor.turno";
   public static final String FORM_PNLGROUPGESTOR_NOMBRE = "form.pnlGroupGestor.nombre";
   public static final String FORM_PNLGROUPGESTOR_MAXALUMNOS = "form.pnlGroupGestor.maxAlumnos";
   public static final String FORM_PNLGROUPGESTOR_PROFESOR = "form.pnlGroupGestor.profesor";
   public static final String FORM_PNLGROUPGESTOR_AULA = "form.pnlGroupGestor.aula";
   public static final String FORM_PNLGROUPGESTOR_FECHAINI = "form.pnlGroupGestor.fechaIni";
   public static final String FORM_PNLGROUPGESTOR_FECHAFIN = "form.pnlGroupGestor.fechaFin";
   public static final String FORM_PNLGROUPGESTOR_WARN_EDITGRUPO = "form.pnlGroupGestor.warning.editGrupo";
   public static final String FORM_PNLGROUPGESTOR_WARN_DELETEGRUPO = "form.pnlGroupGestor.warning.deleteGrupo";
   public static final String FORM_PNLGROUPGESTOR_WARN_DELETECONFIRM = "form.pnlGroupGestor.warning.deleteConfirm";
   public static final String FORM_PNLGROUPGESTOR_ERR_LOADFILTER = "form.pnlGroupGestor.error.loadFilter";
   
   public static final String FORM_PNLMATRICULA_TITLE = "form.pnlMatricula.title";
   public static final String FORM_PNLMATRICULA_ALUMNO = "form.pnlMatricula.alumno";
   public static final String FORM_PNLMATRICULA_GRUPO = "form.pnlMatricula.grupo";
   public static final String FORM_PNLMATRICULA_ESTADO = "form.pnlMatricula.estado";
   public static final String FORM_PNLMATRICULA_ACTIVIDAD = "form.pnlMatricula.actividad";
   public static final String FORM_PNLMATRICULA_FECHAS = "form.pnlMatricula.fechas";
   public static final String FORM_PNLMATRICULA_TURNO = "form.pnlMatricula.turno";
   public static final String FORM_PNLMATRICULA_PLAZAS = "form.pnlMatricula.plazas";
   public static final String FORM_PNLMATRICULA_PLAZASMANANA = "form.pnlMatricula.plazasManana";
   public static final String FORM_PNLMATRICULA_PLAZASTARDE = "form.pnlMatricula.plazasTarde";
   public static final String FORM_PNLMATRICULA_SELECTGRUPO = "form.pnlMatricula.selectGrupo";
   public static final String FORM_PNLMATRICULA_NPLAZAS = "form.pnlMatricula.nPlazas";
   public static final String FORM_PNLMATRICULA_NOGROUP = "form.pnlMatricula.noGroup";
   public static final String FORM_PNLMATRICULA_WARN_ALUMNO = "form.pnlMatricula.warning.alumno";
   public static final String FORM_PNLMATRICULA_WARN_GRUPO = "form.pnlMatricula.warning.grupo";
   public static final String FORM_PNLMATRICULA_WARN_NOPLAZAS = "form.pnlMatricula.warning.noPlazas";
   public static final String FORM_PNLMATRICULA_WARN_READONLY = "form.pnlMatricula.warning.readOnly";
   public static final String FORM_PNLMATRICULA_ERR_NOTSTUDENT = "form.pnlMatricula.err.notStudent";
   
   public static final String FORM_PNLMATRICULAGESTOR_TITLE = "form.pnlMatriculaGestor.title";
   public static final String FORM_PNLMATRICULAGESTOR_ACEPTAR = "form.pnlMatriculaGestor.accept";
   public static final String FORM_PNLMATRICULAGESTOR_CANCELAR = "form.pnlMatriculaGestor.cancel";
   public static final String FORM_PNLMATRICULAGESTOR_FILTER = "form.pnlMatriculaGestor.filter";
   public static final String FORM_PNLMATRICULAGESTOR_ALUMNO = "form.pnlMatriculaGestor.alumno";
   public static final String FORM_PNLMATRICULAGESTOR_NIF = "form.pnlMatriculaGestor.nif";
   public static final String FORM_PNLMATRICULAGESTOR_ESTADO = "form.pnlMatriculaGestor.estado";
   public static final String FORM_PNLMATRICULAGESTOR_ACTIVIDAD = "form.pnlMatriculaGestor.actividad";
   public static final String FORM_PNLMATRICULAGESTOR_GRUPO = "form.pnlMatriculaGestor.grupo";
   public static final String FORM_PNLMATRICULAGESTOR_ESTADO_NOFILTER = "form.pnlMatriculaGestor.estadoNoFilter";
   public static final String FORM_PNLMATRICULAGESTOR_WARN_SELECTMATRICULA = "form.pnlMatriculaGestor.warning.selectMatricula";
   public static final String FORM_PNLMATRICULAGESTOR_WARN_ASKDELETE = "form.pnlMatriculaGestor.warning.askDelete";
   
   public static final String GRUPO_TURNO_MANANA = "group.turno.manana";
   public static final String GRUPO_TURNO_TARDE = "group.turno.tarde";
   
   public static final String MATRICULA_STATUS_ACEPTADA = "matricula.status.aceptada";
   public static final String MATRICULA_STATUS_BAJA = "matricula.status.baja";
   public static final String MATRICULA_STATUS_PENDIENTE = "matricula.status.pendiente";
   public static final String MATRICULA_STATUS_ANULADA = "matricula.status.anulada";
   
   public static final String GRUPO_ASISTENCIA_ABIERTO = "group.asistencia.estado.open";
   public static final String GRUPO_ASISTENCIA_CERRADO = "group.asistencia.estado.closed";
   
   public static final String FORM_PNLACTIVIDAD_TIPO_ACTIVIDAD_SELECCIONA = "form.pnlMantenimientoActividad.tipo.actividad.selecciona";
   public static final String ACTIVIDAD_TIPO_CONGRESO = "actividad.tipo.congreso";
   public static final String ACTIVIDAD_TIPO_JORNADA = "actividad.tipo.jornada";
   public static final String ACTIVIDAD_TIPO_MASTER = "actividad.tipo.master";
   public static final String ACTIVIDAD_TIPO_CONFERENCIA = "actividad.tipo.conferencia";
}