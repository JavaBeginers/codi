package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.exceptions.DuplicatedRequestException;
import edu.uoc.tdp.pac4.beans.PeticionRecurso;
import edu.uoc.tdp.pac4.beans.PeticionRecursoDetalle;
import edu.uoc.tdp.pac4.exceptions.TooManyRequestsException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el gestor para las peticiones de recursos (clase {@link PeticionRecurso}).
 * 
 * @author JavaBeginers
 */
public class GestorPeticionRecurso extends GestorDisco
{
	private Connection conn = null;
	
   /**
    * Constructor de la clase.
    * 
    * @param conn Una instancia de {@link Connection} que conecta con la base de datos.
    */
	public GestorPeticionRecurso(Connection conn ) 
   {
		this.conn = conn;
	}
	
   //===========================================
   // Propiedades
   //===========================================
   
   /**
    * Devuelve la conexión de base de datos usada por el gestor.
    */
	public Connection getConnection() 
   {
		return conn;
	}
   
   //===========================================
   // Métodos
   //===========================================
   
   /**
    * Añade una nueva petición de recursos.
    * 
    * @param peticion Una instancia de {@link PeticionRecurso} que contiene los datos de la petición.
    * @return {@code true} si la operación ha tenido exito o {@code false} en cualquier otro caso.
    * 
    * @throws SQLException
    * @throws TooManyRequestsException
    * @throws DuplicatedRequestException
    * @throws Exception 
    */
   public boolean addPeticion(PeticionRecurso peticion) throws SQLException, TooManyRequestsException, DuplicatedRequestException, Exception
   {
      boolean result = false;
      String sSQL;
      Statement statement;
      ResultSet rs;
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
      try 
      {
         // Comprueba si para la aula y recurso ya hay una petición el mismo dia
         sSQL = "SELECT Count(*) " +
                "FROM peticion_recurso " +
                "WHERE id_aula   = " + peticion.getIdAula() + " And " +
                "      id_recurso = " + peticion.getIdRecurso() + " And " +
                "      fecha_alta_peticion = '" + df.format(peticion.getFechaAltaPeticion()) + "'";
         statement = getConnection().createStatement();
         rs = statement.executeQuery(sSQL);
         
         if (rs.next() && rs.getInt(1) > 0)
         {
            throw new DuplicatedRequestException();
         }
         
         // Comprueba que no existan 3 peticiones pendientes
         sSQL = "SELECT Count(*) " +
                "FROM peticion_recurso " +
                "WHERE fecha_aceptacion is null And " +
                "      id_aula = " + peticion.getIdAula();
         statement = getConnection().createStatement();
         rs = statement.executeQuery(sSQL);
         
         if (rs.next() && rs.getInt(1) >= 3)
         {
            throw new TooManyRequestsException();
         }
         
         // Agrega la petición
         sSQL = "INSERT INTO peticion_recurso (id_recurso, id_aula, fecha_alta_peticion, cantidad, fecha_aceptacion) " +
                "VALUES " +
                "(" + peticion.getIdRecurso() + ", " +
                " " + peticion.getIdAula() + ", " +
                "'" + df.format(peticion.getFechaAltaPeticion()) + "', " +
                " " + peticion.getCantidad() + ", " +
                "     null)";
         statement = getConnection().createStatement();
         statement.execute(sSQL);

         result = true;
      } 
      catch (SQLException ex) 
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
      }
      
      return result;
   }
   
   /**
    * Devuelve una lista con todas las peticiones de recursos pendientes.
    * 
    * @throws Exception 
    */
   public List<PeticionRecursoDetalle> getPeticiones() throws SQLException, Exception
   {
      String sSQL;
      PeticionRecursoDetalle peticion;
      List<PeticionRecursoDetalle> peticiones = new ArrayList<PeticionRecursoDetalle>();
      
      sSQL = "SELECT peticion_recurso.id_recurso, nombre_recurso, cantidad_stock, aula.aula_id, descripcion_aula, fecha_alta_peticion, cantidad, fecha_aceptacion " +
             "FROM peticion_recurso " +
             "     Left Join aula On peticion_recurso.id_aula = aula.aula_id " +
             "     Left Join recurso on peticion_recurso.id_recurso = recurso.id_recurso " +
             "WHERE fecha_aceptacion is null " +
             "ORDER BY fecha_alta_peticion Asc";
      
      try 
      {
         Statement objStt = (Statement)getConnection().createStatement();
         ResultSet objRS = objStt.executeQuery(sSQL);
         while (objRS.next()) 
         {
            peticion = new PeticionRecursoDetalle();
            
            peticion.setIdRecurso(objRS.getInt("id_recurso"));
            peticion.setNombreRecurso(objRS.getString("nombre_recurso"));
            peticion.setStockReal(objRS.getInt("cantidad_stock"));
            peticion.setIdAula(objRS.getInt("id_aula"));
            peticion.setNomAula(objRS.getString("descripcion_aula"));
            peticion.setFechaAltaPeticion(objRS.getDate("fecha_alta_peticion"));
            peticion.setCantidad(objRS.getInt("cantidad"));
            peticion.setFechaAceptacion(objRS.getDate("fecha_aceptacion"));
            
            peticiones.add(peticion);
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
      
      return peticiones;
   }
   
   /**
    * Marca una petición como aceptada y servida.
    * 
    * @param peticion Una instancia de {@link PeticionRecurso} que contiene los datos de la petición.
    * @return {@code true} si la operación tiene exito o {@code false} en cualquier otro caso.
    * 
    * @throws SQLException
    * @throws Exception 
    */
   public boolean setAceptacion(PeticionRecurso peticion) throws SQLException, Exception
   {
      boolean result = false;
      Statement objStt;
      String sSQL;
      String uSQL;
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
      sSQL = "UPDATE peticion_recurso " +
             "SET fecha_aceptacion = '" + df.format(peticion.getFechaAceptacion()) + "' " +
             "WHERE id_recurso = " + peticion.getIdRecurso() + " And " +
             "      id_aula = " + peticion.getIdAula() + " And " +
             "      fecha_alta_peticion =  '" + df.format(peticion.getFechaAltaPeticion()) + "'";
      
      uSQL = "UPDATE recurso " +
             "SET cantidad_stock = cantidad_stock - " + peticion.getCantidad() + " " +             
             "WHERE id_recurso = " + peticion.getIdRecurso();
      
      try 
      {
         objStt = getConnection().createStatement();
         objStt.execute(sSQL);
         
         objStt = getConnection().createStatement();
         //Actualiza stock
         objStt.execute(uSQL);

         result = true;
      } 
      catch (SQLException ex) 
      {
         throw ex;
      } 
      catch (Exception ex) 
      {
         throw ex;
      }
      
      return result;
   }
   
}
