package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gestor de subsistema de Conexión
 * 
 * @author JavaBeginers
 */
public class GestorConexion extends GestorDisco
{
   private Connection conn = null;
   private LanguageUtils txt = null;
   
   /**
    * Constructor de la clase.
    */
   public GestorConexion(Connection conn) 
   {
      this.conn = conn;
   }
   
   //===========================================
   // Propiedades
   //===========================================
   
   /**
    * Devuelve la conexión a base de datos.
    */
   @Override
   public Connection getConnection() 
   {
      return conn;
   }
   
   //===========================================
   // Métodos
   //===========================================
   
    /**
    * Valida la conexión de un usuario al sistema
    * 
    * @return el rol del usuario. Si está vacío se deniega el acceso.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Usuario validaUsuario(String login, String password) throws SQLException, Exception
   {
      Usuario usuario = new Usuario();
      String sql;
      String rol = "";
     /** 
      sql = "SELECT usuari_id as idUsuario, nom_usuari as login, noms, cognoms, activo, rol, rol_desc, contrasenya " +
            "FROM   usuari, rol " +            
            "WHERE  lower(login)  = '" + login.trim().toLowerCase() + "' AND " +
            "       pwd           = ('" + password + "') AND " +
            "       usuario.rol = rol AND " +
            "       activo        = '" + 1 + "'";     
      */
      sql = "SELECT USUARI.usuari_id, USUARI.nom_usuari, USUARI.contrasenya, USUARI.noms, USUARI.cognoms, USUARI.rol, USUARI.activo FROM public.USUARI " +
            "WHERE  lower(USUARI.nom_usuari)= '" + login.trim().toLowerCase() + "' AND " +
            "       USUARI.contrasenya= ('" + password + "') AND " +
            "       USUARI.activo        = '" + 1 + "'";     
     
      try
      {
         Statement statement = getConnection().createStatement();
         ResultSet rs = statement.executeQuery(sql);
         
         while(rs.next())
         {
            usuario.setId(rs.getInt("usuari_id"));
            usuario.setLogin(rs.getString("nom_usuari"));
            usuario.setPwd(rs.getString("contrasenya"));
            usuario.setNombre(rs.getString("noms"));
            usuario.setApellidos(rs.getString("cognoms"));
            usuario.setIdRol(rs.getInt("rol"));
            usuario.setDescrol(rs.getString("rol_desc"));
         }
      }
      catch (SQLException ex) 
      {
         throw ex;
      } 
      catch (Exception ex) 
      {
         throw ex;
      }
            
      return usuario;
   }
}
