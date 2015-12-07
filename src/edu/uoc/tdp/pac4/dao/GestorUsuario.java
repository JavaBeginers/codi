package edu.uoc.tdp.pac4.dao;

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
 * @author J
 */
public class GestorUsuario extends GestorDisco
{
    
    /*
     * Definimos los campos de la tabla usuario
     */
    private static final String USERTABLE              = "USUARI";
    private static final String USERTABLE_ID           = "usuari_id";
    private static final String USERTABLE_LOGIN        = "nom_usuari";
    private static final String USERTABLE_PASSWORD     = "contrasenya";
    private static final String USERTABLE_DATE         = "data_alta";
    private static final String USERTABLE_MAIL         = "email";
    private static final String USERTABLE_PHONE        = "telefono";
    private static final String USERTABLE_NAME         = "noms";
    private static final String USERTABLE_SURNAME      = "cognoms";
    private static final String USERTABLE_ACTIVE       = "activo";
    private static final String USERTABLE_INACTIVEDATE = "data_baixa";
    private static final String USERTABLE_NIF          = "nif";
    private static final String USERTABLE_ROLID        = "rol";
    
    /*
     * Definimos los campos de la tabla rol
     */
    private static final String ROLTABLE               = "rol";
    private static final String ROLTABLE_ID            = "id";
    private static final String ROLTABLE_DESCRIPTION   = "descripcion";
    
    /*
     * Definimos los campos de la tabla grupo
     */
    private static final String GRUPOTABLE             = "grupo";
    private static final String GRUPOTABLE_USER        = "idprofesor";
    
    /*
     * Definimos los campos de la tabla matricula
     */
    private static final String MATRICTABLE            = "matriculas";
    private static final String MATRICTABLE_USER       = "usuarioid";
    
    /*
     * Declaración de variables
     */
    private Connection conn              = null;
    private LanguageUtils txt            = null;
    private java.util.HashMap roleByDesc = null;
    private java.util.HashMap roleById   = null;
    private SimpleDateFormat df          = new SimpleDateFormat("yyyy/MM/dd");
   
    /**
     * Constructor de la clase.
     */
    public GestorUsuario(Connection conn) throws SQLException, Exception {
        this.conn = conn;
    }
   
    //===========================================
    // Propiedades
    //===========================================
   
    /**
     * Devuelve la conexión a base de datos.
     */
    @Override
    public Connection getConnection() {
        return conn;
    }
   
    //===========================================
    // Métodos
    //===========================================
    
    /**
     * Construye un objecto usuario de la información extraída de la consulta SQL
     * 
     * @param  rs {@link ResulsSet} proviniente de una consulta SQL que recoja
     *         TODA la info proviniente de un usuario
     * @return Una instancia de {@link Usuario}
     * 
     * @throws SQLException
     * @throws Exception
     * 
     */
    private Usuario buildUsuario(ResultSet rs) throws SQLException, Exception{
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
        
        return usuario;
    }
     
    /**
     * Obtiene un determinado usuario activo.
     * 
     * @param  id Identificador único del usuario.
     * @return Una instancia de {@link Usuario} que representa el usuario solicitado.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public Usuario get(int id) throws SQLException, Exception {
        String sql;
        Usuario usuario = null;
      
        sql = "SELECT * FROM " + USERTABLE        + " "    +
              "WHERE "         + USERTABLE_ID     + " = "  + id;

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
   
    /**
     * Obtiene un determinado usuario activo de un rol específico.
     * 
     * @param  id   Identificador único del usuario.
     * @param  desc descripción del rol del usuario solicitado
     * @return Una instancia de {@link Usuario} que representa el usuario solicitado.
     * 
     * @throws SQLException
     * @throws Exception 
     */
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
    
    /**
     * Obtiene un determinado usuario activo de un rol específico.
     * 
     * @param  id    Identificador único del usuario.
     * @param  rolid Identificador numérico de un rol.
     * @return Una instancia de {@link Usuario} que representa el usuario solicitado.
     * 
     * @throws SQLException
     * @throws Exception 
     */
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
   
    /**
     * Devuelve una lista de usuarios activos del centro.
     * 
     * @return Una lista de instancias {@link Usuario} que representan los usuarios del centro.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Usuario> getUsuarios() throws SQLException, Exception {
        String sql;
        Usuario usuario;
        ArrayList<Usuario> users = new ArrayList<Usuario>();
      
        sql = "SELECT * FROM " + USERTABLE         + " "    +
              "WHERE "         + USERTABLE_ACTIVE  + " = '" + 1 + "' " +
              "ORDER BY "      + USERTABLE_SURNAME + ","    + USERTABLE_NAME;

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                usuario = buildUsuario(rs);
                users.add(usuario);
            }
        } 
        catch (SQLException ex) {throw ex;} 
        catch (Exception ex)    {throw ex;}
      
        return users;
    }
    
    /**
     * Devuelve una lista de usuarios inactivos del centro.
     * 
     * @return Una lista de instancias {@link Usuario} que representan los usuarios del centro.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Usuario> getUsuariosInactivos() throws SQLException, Exception {
        String sql;
        Usuario usuario;
        ArrayList<Usuario> users = new ArrayList<Usuario>();
      
        sql = "SELECT * FROM " + USERTABLE         + " "    +
              "WHERE "         + USERTABLE_ACTIVE  + " = '" + 0 + "' " +
              "ORDER BY "      + USERTABLE_SURNAME + ","    + USERTABLE_NAME;

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                usuario = buildUsuario(rs);
                users.add(usuario);
            }
        } 
        catch (SQLException ex) {throw ex;} 
        catch (Exception ex)    {throw ex;}
      
        return users;
    }
    
    /**
     * Devuelve una lista de usuarios activos del centro con un rol específico.
     * 
     * @param  desc Descripción del rol de interés.
     * @return Una lista de instancias {@link Usuario} que representan los usuarios del centro.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Usuario> getUsuariosByRolDesc(String desc) throws SQLException, NoRolesException, Exception {
        String sql;
        Usuario usuario;
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        try {
            this.roleByDesc = this.getRolesOptionsByDescription();
        }
        catch (SQLException ex)     {throw ex;} 
        catch (NoRolesException ex) {throw ex;}
        catch (Exception ex)        {throw ex;}
      
        sql = "SELECT * FROM " + USERTABLE         + " "    +
              "WHERE "         + USERTABLE_ROLID   + " = "  + this.roleByDesc.get(desc) + " " +
              "AND "           + USERTABLE_ACTIVE  + " = '" + 1 + "' " +
              "ORDER BY "      + USERTABLE_SURNAME + ","    + USERTABLE_NAME;

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                usuario = buildUsuario(rs);
                users.add(usuario);
            }
        } 
        catch (SQLException ex) {throw ex;} 
        catch (Exception ex)    {throw ex;} 
      
        return users;
    }
    
    /**
     * Devuelve una lista de usuarios activos del centro con un rol específico.
     * 
     * @param  rolid Identificador de un rol específico
     * @return Una lista de instancias {@link Usuario} que representan los usuarios del centro.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Usuario> getUsuariosByRolId(int rolid) throws SQLException, NoRolesException, Exception {
        String sql;
        Usuario usuario;
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        
        try {
            this.roleById = this.getRolesOptionsById();
        }
        catch (SQLException ex)     {throw ex;} 
        catch (NoRolesException ex) {throw ex;}
        catch (Exception ex)        {throw ex;}
      
        sql = "SELECT * FROM " + USERTABLE         + " "    +
              "WHERE "         + USERTABLE_ROLID   + " = "  + rolid + " " +
              "AND "           + USERTABLE_ACTIVE  + " = '" + 1     + "' " +
              "ORDER BY "      + USERTABLE_SURNAME + ","    + USERTABLE_NAME;

        try {
            Statement objStt = (Statement) getConnection().createStatement();
            ResultSet rs = objStt.executeQuery(sql);
            while (rs.next()) {
                usuario = buildUsuario(rs);
                users.add(usuario);
            }
        } 
        catch (SQLException ex) {throw ex;} 
        catch (Exception ex)    {throw ex;} 
      
        return users;
    }
   
    /**
     * Agrega un nuevo usuario activo al centro.
     * 
     * El usuario agregado SIEMPRE es activo.
     * No se asigna fecha de inactividad.
     * 
     * @param usuario Una instancia de {@link Usuario} que contiene los datos del grupo.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void add(Usuario usuario) throws SQLException, Exception {
        String sql;
        Statement statement;
        ResultSet rs;
        
        usuario.setActivo(true);
        
        try {
            sql = "INSERT INTO " + USERTABLE                                + " "  +
                  "("            + USERTABLE_LOGIN                          + ","  + 
                  " "            + USERTABLE_PASSWORD                       + ","  +
                  " "            + USERTABLE_DATE                           + ","  +
                  " "            + USERTABLE_MAIL                           + ","  +
                  " "            + USERTABLE_PHONE                          + ","  +
                  " "            + USERTABLE_NAME                           + ","  +
                  " "            + USERTABLE_SURNAME                        + ","  +
                  " "            + USERTABLE_ACTIVE                         + ","  +
                  " "            + USERTABLE_NIF                            + ","  +
                  " "            + USERTABLE_ROLID                          + ") " +
                  "VALUES "      +
                  "('"           + usuario.getLogin()                       + "', "  +
                  "md5('"        + usuario.getPwd()                         + "'), " +
                  " '"           + df.format(usuario.getFechaAlta())        + "', "  +
                  " '"           + usuario.getEmail()                       + "', "  +
                  " '"           + usuario.getTelf()                        + "', "  +
                  " '"           + usuario.getNombre()                      + "', "  +
                  " '"           + usuario.getPrimerApellido()              + "_"    +
                                   usuario.getSegundoApellido()             + "', "  +
                  " '"           + usuario.isActivoBit()                    + "', "  +
                  " '"           + usuario.getNif()                         + "', "  + 
                  "  "           + usuario.getIdRol()                       + ")";

            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
   
    /**
     * Actualiza los datos de un Usuario.
     * 
     * @param usuario Una instancia de {@link Usuario} que contiene los datos actualizados del usuario.
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void update(Usuario usuario) throws SQLException, Exception {
        String sql;
        Statement statement;
      
        try {
            //Si hay que hacer update del password
            if (!usuario.getPwd().equals("")) {
            sql = "UPDATE " + USERTABLE              + " "        +
                  "SET "    + USERTABLE_LOGIN        + " = '"     + usuario.getLogin()                          + "', "  +
                              USERTABLE_PASSWORD     + " = md5('" + usuario.getPwd()                            + "'), " +
                              USERTABLE_DATE         + " = '"     + df.format(usuario.getFechaAlta())           + "', "  +
                              USERTABLE_MAIL         + " = '"     + usuario.getEmail()                          + "', "  +
                              USERTABLE_PHONE        + " = '"     + usuario.getTelf()                           + "', "  +
                              USERTABLE_NAME         + " = '"     + usuario.getNombre()                         + "', "  +
                              USERTABLE_SURNAME      + " = '"     + usuario.getPrimerApellido()                 +"_"     +
                                                                    usuario.getSegundoApellido()                + "', "  +
                              USERTABLE_NIF          + " = '"     + usuario.getNif()                            + "', "  +
                              USERTABLE_ROLID        + " = "      + usuario.getIdRol()                          + " "    +
                  "WHERE "  + USERTABLE_ID           + " = "      + usuario.getId();
            }
            //Si no hay que hacer update del password
            else {
            sql = "UPDATE " + USERTABLE              + " "        +
                  "SET "    + USERTABLE_LOGIN        + " = '"     + usuario.getLogin()                          + "', "  +
                              USERTABLE_DATE         + " = '"     + df.format(usuario.getFechaAlta())           + "', "  +
                              USERTABLE_MAIL         + " = '"     + usuario.getEmail()                          + "', "  +
                              USERTABLE_PHONE        + " = '"     + usuario.getTelf()                           + "', "  +
                              USERTABLE_NAME         + " = '"     + usuario.getNombre()                         + "', "  +
                              USERTABLE_SURNAME      + " = '"     + usuario.getPrimerApellido()                 +"_"     +
                                                                    usuario.getSegundoApellido()                + "', "  +
                              USERTABLE_NIF          + " = '"     + usuario.getNif()                            + "', "  +
                              USERTABLE_ROLID        + " = "      + usuario.getIdRol()                          + " "    +
                  "WHERE "  + USERTABLE_ID           + " = "      + usuario.getId();
            }

            statement = getConnection().createStatement();
            statement.execute(sql);
        } 
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
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
            sql = "UPDATE " + USERTABLE              + " "        +
                  "SET "    + USERTABLE_ACTIVE       + " = '"     + 0              + "', " +
                              USERTABLE_INACTIVEDATE + " = '"     + df.format(now) + "' "  +
                  "WHERE "  + USERTABLE_ID           + " = "      + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
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
            sql = "UPDATE " + USERTABLE              + " "        +
                  "SET "    + USERTABLE_ACTIVE       + " = '"     + 1   + "' " +
                  "WHERE "  + USERTABLE_ID           + " = "      + id;

            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
    }
   
    /**
     * Genera un diccionario de relaciones descripción rol -> identificador rol
     * 
     * @return Un diccionario {@link java.util.HashMap} donde las key() son descripciones de rol
     *         y los value() son identificadores
     * @throws SQLException
     * @throws Exception 
     */
    public java.util.HashMap getRolesOptionsByDescription() throws SQLException, NoRolesException, Exception {   
        String sql;
        Statement statement;
        java.util.HashMap roleMap = null;
       
        //Si ya se consulto previamente se ha guardado y no es necesario volver a hacer la query SQL
        if (this.roleByDesc != null) {return this.roleByDesc;}
       
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
            }
            catch (SQLException ex) {throw ex;} 
            catch (Exception ex)    {throw ex;}
        }
        else {
            java.util.Set roles; 
            roles = this.roleById.entrySet();
            java.util.Iterator iter  = roles.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry role = (java.util.Map.Entry)iter.next();
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
     * @return Un diccionario {@link java.util.HashMap} donde las key() son identificadores de rol
     *         y los value() son descripciones
     * @throws SQLException
     * @throws Exception 
     */
    public java.util.HashMap getRolesOptionsById() throws SQLException, NoRolesException, Exception {
        String sql;
        Statement statement;
        java.util.HashMap roleMap = null;
        
        //Si ya se consulto previamente se ha guardado y no es necesario volver a hacer la query SQL
        if (this.roleById != null) {return this.roleById;}
       
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
            }
            catch (SQLException ex) {throw ex;} 
            catch (Exception ex)    {throw ex;}
        }
        else {
            java.util.Set roles; 
            roles = this.roleByDesc.entrySet();
            java.util.Iterator iter  = roles.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry role = (java.util.Map.Entry)iter.next();
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
     * Devuelve el número de Grupos que un Usuario (Profesor) tiene asignados
     * 
     * @param id Identificador del Usuario a checkear
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public int checkGruposAssigned(int id) throws SQLException, Exception {
        String sql;
        ResultSet rs;
        
        int grupos = 0;
        try {
            sql = "SELECT COUNT(*) as total FROM " + GRUPOTABLE      + " "  +
                  "WHERE "                         + GRUPOTABLE_USER + " = " + id;
            Statement objStt = (Statement) getConnection().createStatement();
            rs = objStt.executeQuery(sql);
            if(rs.next()) {
                grupos = rs.getInt("total");
            }
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
        
        return grupos;
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
            sql = "DELETE FROM " + MATRICTABLE      + " "  +
                  "WHERE "       + MATRICTABLE_USER + " = " + id;
            statement = getConnection().createStatement();
            statement.execute(sql);
        }
        catch (SQLException ex) {throw ex;}
        catch (Exception ex)    {throw ex;}
        
        return grupos;
    }
   
}
