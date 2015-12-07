package edu.uoc.tdp.pac4.remote;

import java.rmi.Remote;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.Curso;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eSupport Netbeans
 */
public interface Mantenimiento extends Remote 
{
    
    /**
    * Devuelve una lista de Roles del centro.
    * 
    * @return {@link HasMap} < descripcion, idrol> que representa los Roles del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public java.util.HashMap getRolesByDesc() throws SQLException, Exception;
   
    /**
    * Devuelve una lista de Roles del centro.
    * 
    * @return {@link HasMap} < idrol, descripcion> que representa los Roles del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public java.util.HashMap getRolesById() throws SQLException, Exception;
   
    /**
    * Devuelve una lista de Usuarios del centro.
    * 
    * @return Una lista de instancias {@link Usuario} que representan los Usuarios del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Usuario> getUsuarios() throws SQLException, Exception;
   
   /**
    * Devuelve una lista de Usuarios Inactivos del centro.
    * 
    * @return Una lista de instancias {@link Usuario} que representan los Usuarios del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Usuario> getUsuariosInactivos() throws SQLException, Exception;
   
   /**
    * Devuelve una lista de Usuarios del centro para un rol determinado.
    * 
    * @param  desc String describiendo el rol
    * @return Una lista de instancias {@link Usuario} que representan los Usuarios del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Usuario> getUsuariosByRolDesc(String desc) throws SQLException, Exception;
   
   /**
    * Obtiene un determinado Usuario.
    * 
    * @param  id Identificador único del Usuario.
    * @return Una instancia de {@link Usuario} que representa el Usuario solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Usuario getUsuario(int id) throws SQLException, Exception;
   
   /**
    * Agrega un nuevo Usuario al centro.
    * 
    * @param usuario Una instancia de {@link Usuario} que contiene los datos del Usuario.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean addUsuario(Usuario usuario) throws SQLException, Exception;
   
   /**
    * Actualiza los datos de un Usuario.
    * 
    * @param usuario Una instancia de {@link Usuario} que contiene los datos actualizados del Usuario.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean updateUsuario(Usuario usuario) throws SQLException, Exception;
   
   /**
    * Elimina un Usuario.
    * 
    * @param id Identificador del Usuario a eliminar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean deleteUsuario(int id) throws SQLException, Exception;
    
   /**
    * Recupera un Usuario.
    * 
    * @param id Identificador del Usuario a recuperar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean undeleteUsuario(int id) throws SQLException, Exception;
   
   /**
    * Comprueba si un usuario tiene grupos asignados
    * 
    * @param id Identificador del Usuario
    * @return
    * @throws SQLException
    * @throws Exception 
    */
   public int checkGruposUsuario(int id) throws SQLException, Exception;
   
   /**
    * 
    * @param id
    * @return
    * @throws SQLException
    * @throws Exception 
    */
   public boolean liberarMatriculasUsuario(int id) throws SQLException, Exception;
           
    /**
    * Devuelve una lista de Aulas del centro.
    * 
    * @return Una lista de instancias {@link Aula} que representan las Aulas del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Aula> getAulas() throws SQLException, Exception;
   
   /**
    * Devuelve una lista de Aulas inactivas del centro.
    * 
    * @return Una lista de instancias {@link Aula} que representan las Aulas del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Aula> getAulasInactivas() throws SQLException, Exception;
   
   /**
    * Obtiene una determinada Aula.
    * 
    * @param  id Identificador único de la Aula.
    * @return Una instancia de {@link Aula} que representa la Aula seleccionada.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Aula getAula(int id) throws SQLException, Exception;
   
   /**
    * Agrega un nueva Aula al centro.
    * 
    * @param aula Una instancia de {@link Aula} que contiene los datos de la Aula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean addAula(Aula aula) throws SQLException, Exception;
   
   /**
    * Actualiza los datos de una Aula.
    * 
    * @param aula Una instancia de {@link Aula} que contiene los datos actualizados de la Aula.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean updateAula(Aula aula) throws SQLException, Exception;
   
   /**
    * Elimina una Aula.
    * 
    * @param id Identificador de la Aula a eliminar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean deleteAula(int id) throws SQLException, Exception;
   
   /**
    * Elimina la asignacion de recursos al aula
    * 
    * @param id Identificador de la Aula
    * 
    * @throws SQLException
    * @throws Exception
    */
   public boolean liberarRecursosAula(int id) throws SQLException, Exception;
   
   /**
    * Recupera una Aula.
    * 
    * @param id Identificador de la Aula a recuperar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean undeleteAula(int id) throws SQLException, Exception;
   
   /**
    * Comprueba si un aula tiene grupos asignados
    * 
    * @param id Identificador del Aula
    * @return
    * @throws SQLException
    * @throws Exception 
    */
   public int checkGruposAula(int id) throws SQLException, Exception;
   
   /**
    * Devuelve una lista de Cursos del centro.
    * 
    * @return Una lista de instancias {@link Curso} que representan los Cursos del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Curso> getCursos() throws SQLException, Exception;
   
   /**
    * Devuelve una lista de Cursos inactivos del centro.
    * 
    * @return Una lista de instancias {@link Curso} que representan los Cursos del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Curso> getCursosInactivos() throws SQLException, Exception;
   
   /**
    * Obtiene un determinado Curso.
    * 
    * @param  id Identificador único del Curso.
    * @return Una instancia de {@link Curso} que representa el Curso solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Curso getCurso(int id) throws SQLException, Exception;
   
   /**
    * Agrega un nuevo Curso al centro.
    * 
    * @param curso Una instancia de {@link Curso} que contiene los datos del Curso.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean addCurso(Curso curso) throws SQLException, Exception;
   
   /**
    * Actualiza los datos de un Curso.
    * 
    * @param curso Una instancia de {@link Curso} que contiene los datos actualizados del Curso.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean updateCurso(Curso curso) throws SQLException, Exception;
   
   /**
    * Elimina un Curso.
    * 
    * @param id Identificador del Curso a eliminar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean deleteCurso(int id) throws SQLException, Exception;
   
   /**
    * Recupera un Curso.
    * 
    * @param id Identificador del Curso a recuperar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean undeleteCurso(int id) throws SQLException, Exception;
           
   /**
    * Comprueba si un curso tiene grupos asignados
    * 
    * @param id Identificador del Curso
    * @return
    * @throws SQLException
    * @throws Exception 
    */
   public int checkGruposCurso(int id) throws SQLException, Exception;
}
