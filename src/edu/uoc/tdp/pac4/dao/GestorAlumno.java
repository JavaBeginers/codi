package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Alumno;
import edu.uoc.tdp.pac4.exceptions.StudentNotExistsException;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author JavaBeginers
 */
public class GestorAlumno extends GestorDisco
{
   private Connection conn = null;
   private LanguageUtils txt = null;
   
   /**
    * Constructor de la clase.
    */
   public GestorAlumno(Connection conn) 
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
    * Obtiene un determinado usuario.
    * 
    * @param  id Identificador único del usuario.
    * @return Una instancia de {@link Alumno} que representa el usuario solicitado.
    * 
    * @throws SQLException
    * @throws StudentNotExistsException
    * @throws Exception 
    */
   public Alumno get(int id) throws SQLException, StudentNotExistsException, Exception
   {
      String sql;
      Alumno alumno;
      
      sql = "SELECT * " +
            "FROM usuari " +
            "WHERE usuari_id    = " + id + " And " +
                  "rol = " + Alumno.ROL_ALUMNO +
                  "AND actiu = '" + 1 + "'";

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         if (rs.next()) 
         {
            alumno = new Alumno();
            alumno.setId(rs.getInt("usuari_id"));
            alumno.setLogin(rs.getString("nom_usuari"));
            alumno.setLogin(rs.getString("contrasenya"));
            alumno.setFechaAlta(rs.getDate("data_alta"));
            alumno.setEmail(rs.getString("email"));
            alumno.setTelf(rs.getString("telefono"));
            alumno.setNombre(rs.getString("noms"));
            alumno.setApellidos(rs.getString("cognoms"));
            alumno.setActivo(rs.getBoolean("actiu"));
            alumno.setFechaInactividad(rs.getDate("data_baixa"));
            alumno.setNif(rs.getString("nombre_doc_identif"));
            alumno.setIdRol(rs.getInt("rol"));
            alumno.setUniversidadId(rs.getInt("universitat_id"));


            return alumno;
         }
         else
         {
            throw new StudentNotExistsException();
         }
      } 
      catch (SQLException ex) 
      {
         throw ex;
      } 
      catch (StudentNotExistsException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
   }
   
   /**
    * Devuelve una lista de usuarios del centro.
    * 
    * @return Una lista de instancias {@link Alumno} que representan los usuarios del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Alumno> getAlumnos() throws SQLException, Exception
   {
      String sql;
      Alumno alumno;
      ArrayList<Alumno> users = new ArrayList<Alumno>();
      
      sql = "SELECT * " +
            "FROM usuari " +
            "WHERE rol = " + Alumno.ROL_ALUMNO + " " +
            "AND actiu = '" + 1 + "' " +
            "ORDER BY cognoms, noms";

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         while (rs.next()) 
         {
            alumno = new Alumno();
            alumno.setId(rs.getInt("usuari_id"));
            alumno.setLogin(rs.getString("nom_usuari"));
            alumno.setLogin(rs.getString("contrasenya"));
            alumno.setFechaAlta(rs.getDate("data_alta"));
            alumno.setEmail(rs.getString("email"));
            alumno.setTelf(rs.getString("telefono"));
            alumno.setNombre(rs.getString("noms"));
            alumno.setApellidos(rs.getString("cognoms"));
            alumno.setActivo(rs.getBoolean("actiu"));
            alumno.setFechaInactividad(rs.getDate("data_baixa"));
            alumno.setNif(rs.getString("nombre_doc_identif"));
            alumno.setIdRol(rs.getInt("rol"));

            users.add(alumno);
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
      
      return users;
   }
}
