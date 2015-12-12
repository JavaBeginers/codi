package edu.uoc.tdp.pac4.remote;

import java.rmi.Remote;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Centro;
import edu.uoc.tdp.pac4.beans.Recurso;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eSupport Netbeans
 */
public interface Mantenimiento extends Remote {

    /**
     * Devuelve una lista de Roles del centro.
     *
     * @return {@link HasMap} < descripcion, idrol> que representa los Roles del
     * centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    public java.util.HashMap getRolesByDesc() throws SQLException, Exception;

    /**
     * Devuelve una lista de Roles del centro.
     *
     * @return {@link HasMap} < idrol, descripcion> que representa los Roles del
     * centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    public java.util.HashMap getRolesById() throws SQLException, Exception;

    /**
     * Devuelve una lista de Usuarios del centro.
     *
     * @return Una lista de instancias {@link Usuario} que representan los
     * Usuarios del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Usuario> getUsuarios() throws SQLException, Exception;

    /**
     * Devuelve una lista de Usuarios Inactivos del centro.
     *
     * @return Una lista de instancias {@link Usuario} que representan los
     * Usuarios del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Usuario> getUsuariosInactivos() throws SQLException, Exception;

    /**
     * Devuelve una lista de Usuarios del centro para un rol determinado.
     *
     * @param desc String describiendo el rol
     * @return Una lista de instancias {@link Usuario} que representan los
     * Usuarios del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Usuario> getUsuariosByRolDesc(String desc) throws SQLException, Exception;

    /**
     * Obtiene un determinado Usuario.
     *
     * @param id Identificador único del Usuario.
     * @return Una instancia de {@link Usuario} que representa el Usuario
     * solicitado.
     *
     * @throws SQLException
     * @throws Exception
     */
    public Usuario getUsuario(int id) throws SQLException, Exception;

    /**
     * Agrega un nuevo Usuario al centro.
     *
     * @param usuario Una instancia de {@link Usuario} que contiene los datos
     * del Usuario.
     *
     * @throws SQLException
     * @throws Exception
     */
    public boolean addUsuario(Usuario usuario) throws SQLException, Exception;

    /**
     * Actualiza los datos de un Usuario.
     *
     * @param usuario Una instancia de {@link Usuario} que contiene los datos
     * actualizados del Usuario.
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
     * Devuelve una lista de Actividadess del centro.
     *
     * @return Una lista de instancias {@link Actividad} que representan los
     * Actividades del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Actividad> getActividades() throws SQLException, Exception;

    /**
     * Devuelve una lista de Actividades inactivos del centro.
     *
     * @return Una lista de instancias {@link Actividad} que representan los
     * Actividades del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Actividad> getActividadesInactivas() throws SQLException, Exception;

    /**
     * Obtiene un determinado Actividad.
     *
     * @param id Identificador único del Actividad.
     * @return Una instancia de {@link Actividad} que representa el Actividad
     * solicitado.
     *
     * @throws SQLException
     * @throws Exception
     */
    public Actividad getActividad(int id) throws SQLException, Exception;

    /**
     * Obtiene todos los centros
     *
     * @return Una instancia de {@link Actividad} que representa el Actividad
     * solicitado.
     *
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Centro> getCentros() throws SQLException, Exception;

    /**
     * Obtiene las aulas de un centro
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList<Aula> getAulasByIdCentro(int id) throws SQLException, Exception;

    /**
     * Agrega un nuevo Actividad al centro.
     *
     * @param actividad Una instancia de {@link Actividad} que contiene los
     * datos del Actividad.
     * @return
     *
     * @throws SQLException
     * @throws Exception
     */
    public boolean addActividad(Actividad actividad) throws SQLException, Exception;

    /**
     * Actualiza los datos de un Actividad.
     *
     * @param actividad Una instancia de {@link Actividad} que contiene los
     * datos actualizados del Actividad.
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
    
    /**
     * Devuelve los inscritos en un actividad
     *
     * @param actividadId Identificador del Actividad
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public int getInscritosByActividadId(int actividadId) throws SQLException, Exception;
        
    /**
     * Devuelve la capacidad de un aula
     *
     * @param aulaId Identificador del Actividad
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public int getCapacidadByAulaId(int aulaId) throws SQLException, Exception;
    
    //*********************************RECURSO********************************
    public ArrayList<Recurso> getRecursos() throws SQLException, Exception;

    public ArrayList<Recurso> getRecursosInactivos() throws SQLException, Exception;

    public Recurso getRecurso(int id) throws SQLException, Exception;

    public boolean altaRecurso(Recurso rec) throws SQLException, Exception;

    public boolean actualizarRecurso(Recurso rec) throws SQLException, Exception;

    public boolean eliminarRecurso(int id) throws SQLException, Exception;
    
    //***********************************************************************
    

    //*********************************AULA**********************************
    public ArrayList<Aula> getAulas() throws SQLException, Exception;

    public ArrayList<Aula> getAulasInactivas() throws SQLException, Exception;

    public Aula getAula(int id) throws SQLException, Exception;

    public boolean altaAula(Aula aula) throws SQLException, Exception;

    public boolean actualizarAula(Aula aula) throws SQLException, Exception;

    public boolean eliminarAula(int id) throws SQLException, Exception;
    
    //***********************************************************************

 

    
}
