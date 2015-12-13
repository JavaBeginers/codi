package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.AuxiliarCombo;
import edu.uoc.tdp.pac4.beans.Universitat;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import edu.uoc.tdp.pac4.exceptions.NoRolesException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author JavaBeginers
 */
public class GestorUsuario extends GestorDisco {

    //Tabla usuario
    private static final String USERTABLE = "USUARI";
    private static final String USERTABLE_ID = "usuari_id";
    private static final String USERTABLE_LOGIN = "nom_usuari";
    private static final String USERTABLE_PASSWORD = "contrasenya";
    private static final String USERTABLE_PAISNIF = "pais_doc_identif";
    private static final String USERTABLE_ADRESS = "adreca";
    private static final String USERTABLE_TOWN = "poblacio";
    private static final String USERTABLE_CP = "codi_postal";
    private static final String USERTABLE_DATE = "data_alta";
    private static final String USERTABLE_MAIL = "email";
    private static final String USERTABLE_PHONE = "telefono";
    private static final String USERTABLE_NAME = "noms";
    private static final String USERTABLE_SURNAME = "cognoms";
    private static final String USERTABLE_ACTIVE = "actiu";
    private static final String USERTABLE_INACTIVEDATE = "data_baixa";
    private static final String USERTABLE_NIF = "nombre_doc_identif";
    private static final String USERTABLE_ROLID = "rol";
    private static final String USERTABLE_IDIOMA = "idioma_id";
    private static final String USERTABLE_UNI = "universitat_id";
    private static final String USERTABLE_PAIS = "pais_residencia";

    //Tabla Rol
    private static final String ROLTABLE               = "rol";
    private static final String ROLTABLE_ID            = "id";
    private static final String ROLTABLE_DESCRIPTION   = "descripcio";
    
    
    //Tabla Matricula
    private static final String MATRICTABLE = "matriculas";
    private static final String MATRICTABLE_USER = "usuarioid";

    //Variables
    private Connection conn = null;
    private LanguageUtils txt = null;
    private java.util.HashMap roleByDesc = null;
    private java.util.HashMap roleById = null;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    //Constructor
    public GestorUsuario(Connection conn) throws SQLException, Exception {
        this.conn = conn;
    }

    //Properties
    /**
     * Devuelve la conexión a base de datos.
     */
    @Override
    public Connection getConnection() {
        return conn;
    }

    //Construir un Usuario
    private Usuario buildUsuario(ResultSet rs) throws SQLException, Exception {
        Usuario usuario = new Usuario();
        
        usuario.setId               (rs.getInt     (USERTABLE_ID));
        usuario.setLogin            (rs.getString  (USERTABLE_LOGIN));
        usuario.setPwd              (rs.getString  (USERTABLE_PASSWORD));
        usuario.setFechaAlta        (rs.getDate    (USERTABLE_DATE));
        usuario.setEmail            (rs.getString  (USERTABLE_MAIL));
        usuario.setTelf             (rs.getString  (USERTABLE_PHONE));
        usuario.setNombre           (rs.getString  (USERTABLE_NAME));
        usuario.setApellidos        (rs.getString  (USERTABLE_SURNAME));
        usuario.setActivo           (rs.getBoolean (USERTABLE_ACTIVE));
        usuario.setFechaInactividad (rs.getDate    (USERTABLE_INACTIVEDATE));
        usuario.setNif              (rs.getString  (USERTABLE_NIF));
        usuario.setIdRol            (rs.getInt     (USERTABLE_ROLID));
        usuario.setPais             (rs.getInt     (USERTABLE_PAIS));
        usuario.setPaisNIF          (rs.getInt     (USERTABLE_PAISNIF));
        usuario.setUniversidadId    (rs.getInt     (USERTABLE_UNI));
        usuario.setAdreca           (rs.getString    (USERTABLE_ADRESS));
        usuario.setPoblacio         (rs.getString     (USERTABLE_TOWN));
        usuario.setIdioma           (rs.getInt     (USERTABLE_IDIOMA));
        usuario.setCP               (rs.getString     (USERTABLE_CP));
        
        usuario.setDescrol(getNombreRol(usuario.getIdRol()));
        
        return usuario;
    }

    //Obtener Usuario
    public Usuario get(int id) throws SQLException, Exception {
        String sql;
        Usuario usuario = null;

        sql = "SELECT * FROM " + USERTABLE + " "
                + "WHERE " + USERTABLE_ID + " = " + id;

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            if (rs.next()) {
                usuario = buildUsuario(rs);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return usuario;
    }
   
        //Obtener Usuario por nombre
    public int existeUsuario(String username) throws SQLException, Exception {
        String sql;
        int i =0;
      
        sql = "SELECT * FROM " + USERTABLE        + " "    +
              "WHERE "         + USERTABLE_LOGIN    + " = '"  + username+"'";

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                i++;
            }
        } 
        catch (SQLException ex) {throw ex;} 
        catch (Exception ex)    {throw ex;}

        return i;
    }

    /*
    public Usuario getByRolDesc(int id, String desc) throws SQLException, NoRolesException, Exception {
        String sql;
        Usuario usuario = null;
        
        try {
            this.roleByDesc = this.getRolesOptionsByDescription();
        }
        catch (SQLException ex)     {throw ex;} 
        catch (NoRolesException ex) {throw ex;}
        catch (Exception ex)        {throw ex;}
      
        sql = "SELECT * FROM " + USERTABLE        + " "    +
              "WHERE "         + USERTABLE_ID     + " = "  + id + " AND "      +
              USERTABLE_ROLID  + " = "            + this.roleByDesc.get(desc) +
              " AND "          + USERTABLE_ACTIVE + " = '" + 1 + "'";
        
        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            if (rs.next()) {
                usuario = buildUsuario(rs);
            }
        } 
        catch (SQLException ex) {throw ex;} 
        catch (Exception ex)    {throw ex;}
        
        return usuario;
    }
    

    public Usuario getByRolId(int id, int rolid) throws SQLException, NoRolesException, Exception {
        String sql;
        Usuario usuario = null;
      
        sql = "SELECT * FROM " + USERTABLE        + " "    +
              "WHERE "         + USERTABLE_ID     + " = "  + id + " AND " +
              USERTABLE_ROLID  + " = "            + rolid  +
              " AND "          + USERTABLE_ACTIVE + " = '" + 1 + "'";
        try {
            this.roleById = this.getRolesOptionsById();
        }
        catch (SQLException ex)     {throw ex;} 
        catch (NoRolesException ex) {throw ex;}
        catch (Exception ex)        {throw ex;}

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            if (rs.next()) {
                usuario = buildUsuario(rs);
            }
        } 
        catch (SQLException ex) {throw ex;} 
        catch (Exception ex)    {throw ex;}
      
        return usuario;
    }
  
     */
    public ArrayList<Usuario> getUsuarios() throws SQLException, Exception {
        String sql;
        Usuario usuario;
        ArrayList<Usuario> users = new ArrayList<Usuario>();

        sql = "SELECT * FROM " + USERTABLE + " "
                + "WHERE " + USERTABLE_ACTIVE + " = '" + 1 + "' "
                + "ORDER BY " + USERTABLE_SURNAME + "," + USERTABLE_NAME;

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                usuario = buildUsuario(rs);
                users.add(usuario);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return users;
    }

    public ArrayList<Usuario> getUsuariosInactivos() throws SQLException, Exception {
        String sql;
        Usuario usuario;
        ArrayList<Usuario> users = new ArrayList<Usuario>();
      
        sql = "SELECT * FROM " + USERTABLE         + " "    +
              "WHERE "         + USERTABLE_INACTIVEDATE + " IS NOT NULL " +
              "ORDER BY "      + USERTABLE_SURNAME + ","    + USERTABLE_NAME;

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                usuario = buildUsuario(rs);
                users.add(usuario);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return users;
    }

    /**
     * Devuelve una lista de usuarios activos del centro con un rol específico.
     *
     * @param desc Descripción del rol de interés.
     * @return Una lista de instancias {@link Usuario} que representan los
     * usuarios del centro.
     *
     * @throws SQLException
     * @throws Exception      *
     * public ArrayList<Usuario> getUsuariosByRolDesc(String desc) throws
     * SQLException, NoRolesException, Exception { String sql; Usuario usuario;
     * ArrayList<Usuario> users = new ArrayList<Usuario>(); try {
     * this.roleByDesc = this.getRolesOptionsByDescription(); } catch
     * (SQLException ex) {throw ex;} catch (NoRolesException ex) {throw ex;}
     * catch (Exception ex) {throw ex;}
     *
     * sql = "SELECT * FROM " + USERTABLE + " " + "WHERE " + USERTABLE_ROLID + "
     * = " + this.roleByDesc.get(desc) + " " + "AND " + USERTABLE_ACTIVE + " =
     * '" + 1 + "' " + "ORDER BY " + USERTABLE_SURNAME + "," + USERTABLE_NAME;
     *
     * try { Statement objStt = (Statement) getConnection().createStatement();
     * ResultSet rs = objStt.executeQuery(sql); while (rs.next()) { usuario =
     * buildUsuario(rs); users.add(usuario); } } catch (SQLException ex) {throw
     * ex;} catch (Exception ex) {throw ex;}      *
     * return users; }
     *
     *
     *
     * public ArrayList<Usuario> getUsuariosByRolId(int rolid) throws
     * SQLException, NoRolesException, Exception { String sql; Usuario usuario;
     * ArrayList<Usuario> users = new ArrayList<Usuario>();
     *
     * try { this.roleById = this.getRolesOptionsById(); } catch (SQLException
     * ex) {throw ex;} catch (NoRolesException ex) {throw ex;} catch (Exception
     * ex) {throw ex;}
     *
     * sql = "SELECT * FROM " + USERTABLE + " " + "WHERE " + USERTABLE_ROLID + "
     * = " + rolid + " " + "AND " + USERTABLE_ACTIVE + " = '" + 1 + "' " +
     * "ORDER BY " + USERTABLE_SURNAME + "," + USERTABLE_NAME;
     *
     * try { Statement objStt = (Statement) getConnection().createStatement();
     * ResultSet rs = objStt.executeQuery(sql); while (rs.next()) { usuario =
     * buildUsuario(rs); users.add(usuario); } } catch (SQLException ex) {throw
     * ex;} catch (Exception ex) {throw ex;}      *
     * return users; }
     */
//Alta de Usuario
    public void add(Usuario usuario) throws SQLException, Exception {
        String sql;
        Statement statement;
        ResultSet rs;

        usuario.setActivo(true);

        try {
            sql = "INSERT INTO " + USERTABLE + " "
                    + "(" + USERTABLE_LOGIN + ","
                    + " " + USERTABLE_PASSWORD + ","
                    + " " + USERTABLE_DATE + ","
                    + " " + USERTABLE_MAIL + ","
                    + " " + USERTABLE_PHONE + ","
                    + " " + USERTABLE_NAME + ","
                    + " " + USERTABLE_SURNAME + ","
                    + " " + USERTABLE_ACTIVE + ","
                    + " " + USERTABLE_NIF + ","
                    + " " + USERTABLE_ROLID + ","
                    + " " + USERTABLE_PAIS + ","
                    + " " + USERTABLE_PAISNIF + ","
                    + " " + USERTABLE_UNI + ","
                    + " " + USERTABLE_ADRESS + ","
                    + " " + USERTABLE_TOWN + ","
                    + " " + USERTABLE_IDIOMA + ","
                    + " " + USERTABLE_CP + ") "
                    + "VALUES "
                    + "('" + usuario.getLogin() + "', "
                    + "md5('" + usuario.getPwd() + "'), "
                    + " '" + df.format(usuario.getFechaAlta()) + "', "
                    + " '" + usuario.getEmail() + "', "
                    + " '" + usuario.getTelf() + "', "
                    + " '" + usuario.getNombre() + "', "
                    + " '" + usuario.getPrimerApellido() + "_"
                    + usuario.getSegundoApellido() + "', "
                    + " '" + usuario.isActivoBit() + "', "
                    + " '" + usuario.getNif() + "', "
                    + "  " + usuario.getIdRol() + ", "
                    + "  " + usuario.getPais() + ", "
                    + "  " + usuario.getPaisNIF() + ", "
                    + "  " + usuario.getUniversidadId() + ", "
                    + " '" + usuario.getAdreca() + "', "
                    + " '" + usuario.getPoblacio() + "', "
                    + "  " + usuario.getIdioma() + ", "
                    + " '" + usuario.getCP() + "')";

            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Actualiza los datos de un Usuario.
     *
     * @param usuario Una instancia de {@link Usuario} que contiene los datos
     * actualizados del usuario.
     *
     * @throws SQLException
     * @throws Exception
     */
    public void update(Usuario usuario) throws SQLException, Exception {
        String sql;
        Statement statement;

        try {

            sql = "UPDATE " + USERTABLE + " "
                    + "SET " + USERTABLE_LOGIN + " = '" + usuario.getLogin() + "', "
                    + USERTABLE_PASSWORD + " = '" + usuario.getPwd() + "', "
                    + USERTABLE_DATE + " = '" + df.format(usuario.getFechaAlta()) + "', "
                    + USERTABLE_MAIL + " = '" + usuario.getEmail() + "', "
                    + USERTABLE_PHONE + " = '" + usuario.getTelf() + "', "
                    + USERTABLE_NAME + " = '" + usuario.getNombre() + "', "
                    + USERTABLE_SURNAME + " = '" + usuario.getPrimerApellido() + "_"
                    + usuario.getSegundoApellido() + "', "
                    + USERTABLE_NIF + " = '" + usuario.getNif() + "', "
                    + USERTABLE_ROLID + " = " + usuario.getIdRol() + ", "
                    + USERTABLE_PAIS + " = " + usuario.getPais() + ", "
                    + USERTABLE_PAISNIF + " = " + usuario.getPaisNIF() + ", "
                    + USERTABLE_UNI + " = " + usuario.getUniversidadId() + ", "
                    + USERTABLE_IDIOMA + " = " + usuario.getIdioma() + ", "
                    + USERTABLE_TOWN + " = '" + usuario.getPoblacio() + "', "
                    + USERTABLE_CP + " = '" + usuario.getCP() + "', "
                    + USERTABLE_ADRESS + " = '" + usuario.getAdreca() + "', "
                    + USERTABLE_ACTIVE + " = '" + usuario.isActivoBit() + "' "
                    + "WHERE " + USERTABLE_ID + " = " + usuario.getId();

            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Elimina (borrado lógico) un Usuario del centro.
     *
     * El usuario se inactiva asignando el bit '0' a USERTABLE_ACTIVE
     *
     * @param id Identificador del Usuario a eliminar.
     *
     * @throws SQLException
     * @throws Exception
     */
    public void delete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;

        Date now = new Date();

        try {
            sql = "UPDATE " + USERTABLE + " "
                    + "SET " + USERTABLE_ACTIVE + " = '" + 0 + "', "
                    + USERTABLE_INACTIVEDATE + " = '" + df.format(now) + "' "
                    + "WHERE " + USERTABLE_ID + " = " + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Recupera (de un borrado lógico) un Usuario del centro.
     *
     * El usuario se activa asignando el bit '1' a USERTABLE_ACTIVE
     *
     * @param id Identificador del Usuario a eliminar.
     *
     * @throws SQLException
     * @throws Exception
     */
    public void undelete(int id) throws SQLException, Exception {
        String sql;
        Statement statement;

        Date now = new Date();

        try {
            sql = "UPDATE " + USERTABLE + " "
                    + "SET " + USERTABLE_ACTIVE + " = '" + 1 + "' "
                    + "WHERE " + USERTABLE_ID + " = " + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Genera un diccionario de relaciones descripción rol -> identificador rol
     *
     * @return Un diccionario {@link java.util.HashMap} donde las key() son
     * descripciones de rol y los value() son identificadores
     * @throws SQLException
     * @throws Exception
     */
    public java.util.HashMap getRolesOptionsByDescription() throws SQLException, NoRolesException, Exception {
        String sql;
        Statement statement;
        java.util.HashMap roleMap = null;

        //Si ya se consulto previamente se ha guardado y no es necesario volver a hacer la query SQL
        if (this.roleByDesc != null) {
            return this.roleByDesc;
        }

        //Si la version "inversa" no está llena tendremos que hacer la query SQL
        if (this.roleById == null) {
            try {
                sql = "SELECT * FROM " + ROLTABLE;

                roleMap = new java.util.HashMap();
                statement = (Statement) getConnection().createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    roleMap.put(rs.getString(ROLTABLE_DESCRIPTION), rs.getInt(ROLTABLE_ID));
                }
            } catch (SQLException ex) {
                throw ex;
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            java.util.Set roles;
            roles = this.roleById.entrySet();
            java.util.Iterator iter = roles.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry role = (java.util.Map.Entry) iter.next();
                roleMap.put(role.getValue(), role.getKey());
            }
        }

        this.roleByDesc = roleMap;

        if (roleMap == null) {
            throw new NoRolesException();
        }
        return roleMap;
    }

    /**
     * Genera un diccionario de relaciones identificador rol -> descripcion rol
     *
     * @return Un diccionario {@link java.util.HashMap} donde las key() son
     * identificadores de rol y los value() son descripciones
     * @throws SQLException
     * @throws Exception
     */
    public java.util.HashMap getRolesOptionsById() throws SQLException, NoRolesException, Exception {
        String sql;
        Statement statement;
        java.util.HashMap roleMap = null;

        //Si ya se consulto previamente se ha guardado y no es necesario volver a hacer la query SQL
        if (this.roleById != null) {
            return this.roleById;
        }

        //Si la version "inversa" no está llena tendremos que hacer la query SQL
        if (this.roleByDesc == null) {
            try {
                sql = "SELECT * FROM rol";

                roleMap = new java.util.HashMap();
                statement = (Statement) getConnection().createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    roleMap.put(rs.getInt("id"), rs.getString("descripcion"));
                }
            } catch (SQLException ex) {
                throw ex;
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            java.util.Set roles;
            roles = this.roleByDesc.entrySet();
            java.util.Iterator iter = roles.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry role = (java.util.Map.Entry) iter.next();
                roleMap.put(role.getValue(), role.getKey());
            }
        }

        this.roleById = roleMap;
        if (roleMap == null) {
            throw new NoRolesException();
        }
        return roleMap;
    }

    /**
     * Elimina las matriculas del usuario (Estudiante)
     *
     * @param id Identificador del Usuario a checkear
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public int cleanMatriculasAssigned(int id) throws SQLException, Exception {
        String sql;
        Statement statement;

        int grupos = 0;
        try {
            sql = "DELETE FROM " + MATRICTABLE + " "
                    + "WHERE " + MATRICTABLE_USER + " = " + id;
            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return grupos;
    }

    public ArrayList<AuxiliarCombo> getPaises() throws SQLException, Exception {
        String sql;

        ArrayList<AuxiliarCombo> paises = new ArrayList<AuxiliarCombo>();

        sql = "SELECT * FROM paisos";

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                AuxiliarCombo pais = new AuxiliarCombo();
                pais.setId(rs.getInt("codi_pais"));
                pais.setNombre(rs.getString("nom"));
                paises.add(pais);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return paises;
    }

    public ArrayList<AuxiliarCombo> getRoles() throws SQLException, Exception {
        String sql;

        ArrayList<AuxiliarCombo> roles = new ArrayList<AuxiliarCombo>();

        sql = "SELECT * FROM rol";

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                AuxiliarCombo rol = new AuxiliarCombo();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("descripcio"));
                roles.add(rol);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return roles;
    }

    public ArrayList<Universitat> getUniversidadesRaw() throws SQLException, Exception {
        String sql;

        ArrayList<Universitat> universidades = new ArrayList<Universitat>();

        sql = "SELECT * FROM universitat";

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                Universitat universidad = new Universitat();
                universidad.setUniversitat_id(rs.getInt("universitat_id"));
                universidad.setNom(rs.getString("nom"));
                universidades.add(universidad);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return universidades;
    }

    public ArrayList<AuxiliarCombo> getUniversidades() throws SQLException, Exception {
        String sql;

        ArrayList<AuxiliarCombo> universidades = new ArrayList<AuxiliarCombo>();

        sql = "SELECT * FROM universitat";

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                AuxiliarCombo universidad = new AuxiliarCombo();
                universidad.setId(rs.getInt("universitat_id"));
                universidad.setNombre(rs.getString("nom"));
                universidades.add(universidad);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return universidades;
    }

    public ArrayList<AuxiliarCombo> getIdiomas() throws SQLException, Exception {
        String sql;

        ArrayList<AuxiliarCombo> idiomas = new ArrayList<AuxiliarCombo>();

        sql = "SELECT * FROM idioma";

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                AuxiliarCombo idioma = new AuxiliarCombo();
                idioma.setId(rs.getInt("idioma_id"));
                idioma.setNombre(rs.getString("idioma_nom"));
                idiomas.add(idioma);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return idiomas;
    }
       public String getNombreRol(int idrol) throws SQLException, Exception {
        String sql;
        String nombrerol="";
      
        sql = "SELECT descripcio FROM rol where id= " + idrol;

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {

                        nombrerol=         (rs.getString  ("descripcio"));
            }
        } 
        catch (SQLException ex) {throw ex;} 
        catch (Exception ex)    {throw ex;}
      
        return nombrerol;
    }
}
