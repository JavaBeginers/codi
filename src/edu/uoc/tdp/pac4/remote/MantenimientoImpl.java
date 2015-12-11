package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Centro;
import edu.uoc.tdp.pac4.dao.GestorDisco;
import edu.uoc.tdp.pac4.dao.GestorUsuario;
import edu.uoc.tdp.pac4.dao.GestorAulas;
import edu.uoc.tdp.pac4.dao.GestorActividad;
import edu.uoc.tdp.pac4.dao.GestorCentro;
import edu.uoc.tdp.pac4.dao.GestorMatricula;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eSupport Netbeans
 */
public class MantenimientoImpl extends UnicastRemoteObject implements Mantenimiento, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;

    GestorDisco gestorDisc;

    public MantenimientoImpl() throws RemoteException, Exception {
        super();

        try {
            gestorDisc = new GestorDisco();
            gestorDisc.initConnection();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Devuelve una lista de Roles del centro.
     *
     * @return {@link HasMap} < descripcion, idrol> que representa los Roles del
     * centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public java.util.HashMap getRolesByDesc() throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        return gu.getRolesOptionsByDescription();
    }

    /**
     * Devuelve una lista de Roles del centro.
     *
     * @return {@link HasMap} < idrol, descripcion> que representa los Roles del
     * centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public java.util.HashMap getRolesById() throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        return gu.getRolesOptionsById();
    }

    /**
     * Devuelve una lista de Usuarios del centro.
     *
     * @return Una lista de instancias {@link Usuario} que representan los
     * Usuarios del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public ArrayList<Usuario> getUsuarios() throws RemoteException, SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        return gu.getUsuarios();
    }

    /**
     * Devuelve una lista de Usuarios Inactivos del centro.
     *
     * @return Una lista de instancias {@link Usuario} que representan los
     * Usuarios del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public ArrayList<Usuario> getUsuariosInactivos() throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        return gu.getUsuariosInactivos();
    }

    @Override
    public ArrayList<Usuario> getUsuariosByRolDesc(String desc) throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        return gu.getUsuariosByRolDesc(desc);
    }

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
    @Override
    public Usuario getUsuario(int id) throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        return gu.get(id);
    }

    /**
     * Agrega un nuevo Usuario al centro.
     *
     * @param usuario Una instancia de {@link Usuario} que contiene los datos
     * del Usuario.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean addUsuario(Usuario usuario) throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        gu.add(usuario);

        return true;
    }

    /**
     * Actualiza los datos de un Usuario.
     *
     * @param usuario Una instancia de {@link Usuario} que contiene los datos
     * actualizados del Usuario.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean updateUsuario(Usuario usuario) throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        gu.update(usuario);

        return true;
    }

    /**
     * Elimina un Usuario.
     *
     * @param id Identificador del Usuario a eliminar.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean deleteUsuario(int id) throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        gu.delete(id);

        return true;
    }

    /**
     * Recupera un Usuario.
     *
     * @param id Identificador del Usuario a eliminar.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean undeleteUsuario(int id) throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        gu.undelete(id);

        return true;
    }

    /**
     * Devuelve una lista de Aulas del centro.
     *
     * @return Una lista de instancias {@link Aula} que representan las Aulas
     * del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public ArrayList<Aula> getAulas() throws SQLException, Exception {
        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
        return ga.getAulas();
    }

    /**
     * Devuelve una lista de Aulas inactivas del centro.
     *
     * @return Una lista de instancias {@link Aula} que representan las Aulas
     * del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public ArrayList<Aula> getAulasInactivas() throws SQLException, Exception {
        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
        return ga.getAulasInactivas();
    }

    /**
     * Obtiene una determinada Aula.
     *
     * @param id Identificador único de la Aula.
     * @return Una instancia de {@link Aula} que representa la Aula
     * seleccionada.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public Aula getAula(int id) throws SQLException, Exception {
        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
        return ga.get(id);
    }

    /**
     * Agrega un nueva Aula al centro.
     *
     * @param aula Una instancia de {@link Aula} que contiene los datos de la
     * Aula.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean altaAula(Aula aula) throws SQLException, Exception {
        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
        ga.alta(aula);

        return true;
    }

    /**
     * Actualiza los datos de una Aula.
     *
     * @param aula Una instancia de {@link Aula} que contiene los datos
     * actualizados de la Aula.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean actualizarAula(Aula aula) throws SQLException, Exception {
        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
        ga.actualizar(aula);

        return true;
    }

    /**
     * Elimina una Aula.
     *
     * @param id Identificador de la Aula a eliminar.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean eliminarAula(int id) throws SQLException, Exception {
        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
        ga.eliminar(id);

        return true;
    }



    /**
     * Recupera una Aula.
     *
     * @param id Identificador de la Aula a recuperar.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean undeleteAula(int id) throws SQLException, Exception {
        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
        ga.undelete(id);

        return true;
    }

    /**
     * Devuelve una lista de Actividades del centro.
     *
     * @return Una lista de instancias {@link Actividad} que representan los
     * Actividades del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public ArrayList<Actividad> getActividades() throws SQLException, Exception {
        GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
        return gc.getActividades();
    }

    /**
     * Devuelve una lista de Actividades inactivos del centro.
     *
     * @return Una lista de instancias {@link Actividad} que representan los
     * Actividades del centro.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public ArrayList<Actividad> getActividadesInactivas() throws SQLException, Exception {
        GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
        return gc.getActividadesInactivas();
    }

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
    @Override
    public Actividad getActividad(int id) throws SQLException, Exception {
        GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
        return gc.get(id);
    }

    /**
     * Agrega un nuevo Actividad al centro.
     *
     * @param actividad Una instancia de {@link Actividad} que contiene los
     * datos del Actividad.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean addActividad(Actividad actividad) throws SQLException, Exception {
        GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
        gc.add(actividad);

        return true;
    }

    /**
     * Actualiza los datos de un Actividad.
     *
     * @param actividad Una instancia de {@link Actividad} que contiene los
     * datos actualizados del Actividad.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean updateActividad(Actividad actividad) throws SQLException, Exception {
        GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
        gc.update(actividad);
        return true;
    }

    /**
     * Elimina un Actividad.
     *
     * @param id Identificador del Actividad a eliminar.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean deleteActividad(int id) throws SQLException, Exception {
        GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
        gc.delete(id);
        return true;
    }

    /**
     * Recupera un Actividad.
     *
     * @param id Identificador del Actividad a recuperar.
     *
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean undeleteActividad(int id) throws SQLException, Exception {
        GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
        gc.undelete(id);

        return true;
    }

    /**
     * Comprueba si un actividad tiene grupos asignados
     *
     * @param id Identificador del Actividad
     * @return
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public int checkGruposActividad(int id) throws SQLException, Exception {
        GestorActividad gc = new GestorActividad(gestorDisc.getConnection());
        return gc.checkGruposAssigned(id);
    }

    /**
     * Comprueba si un usuario tiene grupos asignados
     *
     * @param id Identificador del Usuario
     * @return
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public int checkGruposUsuario(int id) throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        return gu.checkGruposAssigned(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean liberarMatriculasUsuario(int id) throws SQLException, Exception {
        GestorUsuario gu = new GestorUsuario(gestorDisc.getConnection());
        gu.cleanMatriculasAssigned(id);

        return true;
    }

 

    @Override
    public ArrayList<Centro> getCentros() throws SQLException, Exception {
        GestorCentro gc = new GestorCentro(gestorDisc.getConnection());
        return gc.getCentros();
    }

    @Override
    public ArrayList<Aula> getAulasByIdCentro(int id) throws SQLException, Exception {
        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
        return ga.getAulasByCentro(id);
    }

    @Override
    public int getInscritosByActividadId(int actividadId) throws SQLException, Exception {
        GestorMatricula gm = new GestorMatricula(gestorDisc.getConnection());
        return gm.getInscritosByActividad(actividadId);
    }

//    @Override
//    public int getCapacidadByAulaId(int aulaId) throws SQLException, Exception {
//        GestorAulas ga = new GestorAulas(gestorDisc.getConnection());
//        return ga.getCapacidadByAulaId(aulaId);
//    }
    
}
