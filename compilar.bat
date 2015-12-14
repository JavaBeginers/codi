@echo off
REM BAT que compila els fitxers java de la PAC4
REM (c) Joan Antoni Dominguez, Cristian Ceballos, Ivan Margarit  i Marc Pérez
@ echo on

set classpath=.;
set classpath=C:\UOC\15-16\TDP\PAC4\PAC4_TDP_2015\codi\src\*;

javac src\edu\uoc\tdp\pac4\eAcademiaEU.java
javac src\edu\uoc\tdp\pac4\beans\IdentifiableObject.java
javac src\edu\uoc\tdp\pac4\util\LanguageUtils.java
javac src\edu\uoc\tdp\pac4\util\StringUtils.java
javac src\edu\uoc\tdp\pac4\util\DateTimeUtils.java
javac src\edu\uoc\tdp\pac4\util\FieldLimit.java
javac src\edu\uoc\tdp\pac4\util\NumericUtils.java

javac src\edu\uoc\tdp\pac4\util\ComboItem.java

javac src\edu\uoc\tdp\pac4\beans\Actividad.java
javac src\edu\uoc\tdp\pac4\beans\Administrador.java
javac src\edu\uoc\tdp\pac4\beans\Alumno.java
javac src\edu\uoc\tdp\pac4\beans\Asistencia.java
javac src\edu\uoc\tdp\pac4\beans\Aula.java
javac src\edu\uoc\tdp\pac4\beans\AuxiliarCombo.java
javac src\edu\uoc\tdp\pac4\beans\Centro.java
javac src\edu\uoc\tdp\pac4\beans\Grupo.java



javac src\edu\uoc\tdp\pac4\dao\GestorMatricula.java
javac src\edu\uoc\tdp\pac4\dao\GestionPeticionRecurso.java
javac src\edu\uoc\tdp\pac4\dao\GestorProfesor.java
javac src\edu\uoc\tdp\pac4\dao\GestorRecursos.java
javac src\edu\uoc\tdp\pac4\dao\GestorUsuario.java
javac src\edu\uoc\tdp\pac4\beans\Usuario.java
javac src\edu\uoc\tdp\pac4\beans\Profesor.java
javac src\edu\uoc\tdp\pac4\beans\Recurso.java
javac src\edu\uoc\tdp\pac4\beans\Matricula.java
javac src\edu\uoc\tdp\pac4\beans\PersonalAcademico.java
javac src\edu\uoc\tdp\pac4\beans\PeticionRecurso.java
javac src\edu\uoc\tdp\pac4\beans\PeticionRecursoDetalle.java




javac src\edu\uoc\tdp\pac4\client\conexion\PnlConsultaMatricula.java
javac src\edu\uoc\tdp\pac4\client\conexion\PnlConsultaLogin.java
javac src\edu\uoc\tdp\pac4\client\conexion\PnlMarcaAsistencia.java
javac src\edu\uoc\tdp\pac4\client\conexion\PnlMuestraBibliografia.java
javac src\edu\uoc\tdp\pac4\client\conexion\PnlMuestraPlanDocente.java
javac src\edu\uoc\tdp\pac4\client\conexion\PnlSolicitaMatricula.java
javac src\edu\uoc\tdp\pac4\client\estadisticas\PnlFiltroActividades.java
javac src\edu\uoc\tdp\pac4\client\estadisticas\PnlFiltroPersonalAcademico.java
javac src\edu\uoc\tdp\pac4\client\estadisticas\PnlFiltroProfesor.java
javac src\edu\uoc\tdp\pac4\client\estadisticas\PnlListadoAlumno.java
javac src\edu\uoc\tdp\pac4\client\estadisticas\PnlListadoPersonalAcademico.java
javac src\edu\uoc\tdp\pac4\client\estadisticas\PnlListadoProfesor.java
javac src\edu\uoc\tdp\pac4\client\gestion\PnlAsistencia.java
javac src\edu\uoc\tdp\pac4\client\gestion\PnlGroup.java
javac src\edu\uoc\tdp\pac4\client\gestion\PnlGroupGestor.java
javac src\edu\uoc\tdp\pac4\client\gestion\PnlMatricula.java
javac src\edu\uoc\tdp\pac4\client\gestion\PnlMatriculaGestor.java
javac src\edu\uoc\tdp\pac4\client\mantenimiento\PnlMantenimientoActividades.java
javac src\edu\uoc\tdp\pac4\client\mantenimiento\PnlMantenimientoActividadGestor.java
javac src\edu\uoc\tdp\pac4\client\mantenimiento\PnlMantenimientoAulaGestor.java
javac src\edu\uoc\tdp\pac4\client\mantenimiento\PnlMantenimientoAulas.java
javac src\edu\uoc\tdp\pac4\client\mantenimiento\PnlMantenimientoRecursoGestor.java
javac src\edu\uoc\tdp\pac4\client\mantenimiento\PnlMantenimientoRecursos.java
javac src\edu\uoc\tdp\pac4\client\mantenimiento\PnlMantenimientoUsuarioGestor.java
javac src\edu\uoc\tdp\pac4\client\mantenimiento\PnlMantenimientoUsuarios.java
javac src\edu\uoc\tdp\pac4\client\PnlMain.java
javac src\edu\uoc\tdp\pac4\dao\GestorActividad.java
javac src\edu\uoc\tdp\pac4\dao\GestorAlumno.java
javac src\edu\uoc\tdp\pac4\dao\GestorAsistencia.java
javac src\edu\uoc\tdp\pac4\dao\GestorAulas.java
javac src\edu\uoc\tdp\pac4\dao\GestorCentro.java
javac src\edu\uoc\tdp\pac4\dao\GestorConexion.java
javac src\edu\uoc\tdp\pac4\dao\GestorDisco.java
javac src\edu\uoc\tdp\pac4\dao\GestorGrupo.java

javac src\edu\uoc\tdp\pac4\exceptions\ApplicationAlreadyExistsException.java
javac src\edu\uoc\tdp\pac4\exceptions\AssistanceAlreadyCountedException.java
javac src\edu\uoc\tdp\pac4\exceptions\DuplicatedRequestException.java
javac src\edu\uoc\tdp\pac4\exceptions\eAssistenciaEsception.java
javac src\edu\uoc\tdp\pac4\exceptions\GroupAlreadyCountedException.java
javac src\edu\uoc\tdp\pac4\exceptions\GroupAlreadyExistsException.java
javac src\edu\uoc\tdp\pac4\exceptions\GroupNotEmptyException.java
javac src\edu\uoc\tdp\pac4\exceptions\NoGroupFountException.java
javac src\edu\uoc\tdp\pac4\exceptions\NoRolesException.java
javac src\edu\uoc\tdp\pac4\exceptions\NotAvailableProfessorException.java
javac src\edu\uoc\tdp\pac4\exceptions\StudentAssistanceAlreadyCountedException.java
javac src\edu\uoc\tdp\pac4\exceptions\StudentNotExistsException.java
javac src\edu\uoc\tdp\pac4\exceptions\TooManyRequestException.java
javac src\edu\uoc\tdp\pac4\exceptions\GestioDeFacturasException.java
javac src\edu\uoc\tdp\pac4\remote\Conexion.java
javac src\edu\uoc\tdp\pac4\remote\ConexionImpl.java
javac src\edu\uoc\tdp\pac4\remote\Estadisticas.java
javac src\edu\uoc\tdp\pac4\remote\EstadisticasImpl.java
javac src\edu\uoc\tdp\pac4\remote\GestAcademica.java
javac src\edu\uoc\tdp\pac4\remote\GestAcademicaImpl.java
javac src\edu\uoc\tdp\pac4\remote\Mantenimiento.java
javac src\edu\uoc\tdp\pac4\remote\MantenimientoImpl.java
javac src\edu\uoc\tdp\pac4\remote\tdpLanguageUtils.java

javac src\edu\uoc\tdp\pac4\server\Servidor.java

rmic uoc.tdp.pac4.server.impl.RemotoImpl