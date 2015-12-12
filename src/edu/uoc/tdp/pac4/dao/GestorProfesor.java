package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Profesor;
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
public class GestorProfesor extends GestorDisco
{
   private Connection conn = null;
   private LanguageUtils txt = null;
   
   /**
    * Constructor de la clase.
    */
   public GestorProfesor(Connection conn) 
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
    * Obtiene un determinado profesor.
    * 
    * @param  id Identificador único del profesor.
    * @return Una instancia de {@link Profesor} que representa el profesor solicitado.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public Profesor get(int id) throws SQLException, Exception
   {
      String sql;
      Profesor profesor = null;
      
      sql = "SELECT * " +
            "FROM usuario " +
            "WHERE id    = " + id + " And " +
                  "idrol = " + Profesor.ROL_PROFESOR + " " +
                  "AND activo = '" + 1 + "'";

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         if (rs.next()) 
         {
            profesor = new Profesor();
            profesor.setId(rs.getInt("id"));
            profesor.setLogin(rs.getString("login"));
            profesor.setLogin(rs.getString("pwd"));
            profesor.setFechaAlta(rs.getDate("fechaalta"));
            profesor.setEmail(rs.getString("email"));
            profesor.setTelf(rs.getString("telf"));
            profesor.setNombre(rs.getString("nombre"));
            profesor.setApellidos(rs.getString("apellidos"));
            profesor.setActivo(rs.getBoolean("activo"));
            profesor.setFechaInactividad(rs.getDate("fechainactividad"));
            profesor.setNif(rs.getString("nif"));
            profesor.setIdRol(rs.getInt("idrol"));
            profesor.setUniversidadId(rs.getInt("universitat_id"));


            return profesor;
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
      
      return profesor;
   }
   
   /**
    * Devuelve una lista de profesores del centro.
    * 
    * @return Una lista de instancias {@link Profesor} que representan los profesores del centro.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public ArrayList<Profesor> getProfesores() throws SQLException, Exception
   {
      String sql;
      Profesor profesor;
      ArrayList<Profesor> profesores = new ArrayList<Profesor>();
      
      sql = "SELECT * " +
            "FROM usuario " +
            "WHERE idrol = " + Profesor.ROL_PROFESOR + " " +
            "AND activo = '" + 1 + "' " +
            "ORDER BY apellidos, nombre";

      try 
      {
         Statement objStt = (Statement) getConnection().createStatement();
         ResultSet rs = objStt.executeQuery(sql);
         while (rs.next()) 
         {
            profesor = new Profesor();
            profesor.setId(rs.getInt("id"));
            profesor.setLogin(rs.getString("login"));
            profesor.setLogin(rs.getString("pwd"));
            profesor.setFechaAlta(rs.getDate("fechaalta"));
            profesor.setEmail(rs.getString("email"));
            profesor.setTelf(rs.getString("telf"));
            profesor.setNombre(rs.getString("nombre"));
            profesor.setApellidos(rs.getString("apellidos"));
            profesor.setActivo(rs.getBoolean("activo"));
            profesor.setFechaInactividad(rs.getDate("fechainactividad"));
            profesor.setNif(rs.getString("nif"));
            profesor.setIdRol(rs.getInt("idrol"));
            profesor.setUniversidadId(rs.getInt("universitat_id"));

            profesores.add(profesor);
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
      
      return profesores;
   }
}