package edu.uoc.tdp.pac4.remote;

import java.rmi.Remote;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Centro;
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
    * Devuelve una lista de Actividadess del centro.
    * 
    * @return Una lista de instancias {@link Actividad} que representan los Actividades del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Actividad> getActividades() throws SQLException, Exception;
   
   /**
    * Devuelve una lista de Actividades inactivos del centro.
    * 
    * @return Una lista de instancias {@link Actividad} que representan los Actividades del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Actividad> getActividadesInactivas() throws SQLException, Exception;
   
   /**
    * Obtiene un determinado Actividad.
    * 
    * @param  id Identificador único del Actividad.
    * @return Una instancia de {@link Actividad} que representa el Actividad solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Actividad getActividad(int id) throws SQLException, Exception;
   
   public ArrayList<Centro> getCentros() throws SQLException, Exception;
   
   /**
    * Agrega un nuevo Actividad al centro.
    * 
    * @param actividad Una instancia de {@link Actividad} que contiene los datos del Actividad.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean addActividad(Actividad actividad) throws SQLException, Exception;
   
   /**
    * Actualiza los datos de un Actividad.
    * 
    * @param actividad Una instancia de {@link Actividad} que contiene los datos actualizados del Actividad.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean updateActividad(Actividad actividad) throws SQLException, Exception;
   
   /**
    * Elimina un Actividad.
    * 
    * @param id Identificador del Actividad a eliminar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean deleteActividad(int id) throws SQLException, Exception;
   
   /**
    * Recupera un Actividad.
    * 
    * @param id Identificador del Actividad a recuperar.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean undeleteActividad(int id) throws SQLException, Exception;
           
   /**
    * Comprueba si un actividad tiene grupos asignados
    * 
    * @param id Identificador del Actividad
    * @return
    * @throws SQLException
    * @throws Exception 
    */
   public int checkGruposActividad(int id) throws SQLException, Exception;
}
