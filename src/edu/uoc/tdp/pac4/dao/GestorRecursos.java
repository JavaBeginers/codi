package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.beans.Recurso;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el gestor de recursos.
 * @author JavaBeginers
 */
public class GestorRecursos extends GestorDisco
{
   private Connection conn = null;
   private LanguageUtils txt = null;

   /**
    * Constructor de la clase.
    */
   public GestorRecursos(Connection conn) 
   {
      this.conn = conn;
   }

   //===========================================
   // Propiedades
   //===========================================
   
   /**
    * Devuelve la conexión a base de datos.
    */
   public Connection getConnection() 
   {
      return conn;
   }
   
   //===========================================
   // Métodos
   //===========================================
   
   /**
    * Devuelve una lista de los recursos disponibles.
    * 
    * @return Una instancia de {@link List} que contiene los recursos.
    * @throws Exception 
    */
   public List<Recurso> ConsultarTipoRecurso() throws SQLException, Exception 
   {
      String sSQL;
      Recurso recurso;
      List<Recurso> recursos = new ArrayList<Recurso>();
      
      sSQL = "SELECT id_recurso, nombre_recurso, cantidad_stock, fecha_ultima_entrada_stock, fecha_ultima_salida_stock " +
             "FROM recurso " +
             "ORDER BY nombre_recurso Asc";
      
      try 
      {
         java.sql.Statement objStt = getConnection().createStatement();
         ResultSet objRS = objStt.executeQuery(sSQL);
         while (objRS.next()) 
         {
            recurso = new Recurso();
            recurso.setIdRecurso(objRS.getInt("id_recurso"));
            recurso.setNombreRecurso(objRS.getString("nombre_recurso"));
            recurso.setCantidadStock(objRS.getInt("cantidad_stock"));
            recurso.setFechaUltimaEntradaStock(objRS.getDate("fecha_ultima_entrada_stock"));
            recurso.setFechaUltimaSalidaStock(objRS.getDate("fecha_ultima_salida_stock"));
            
            recursos.add(recurso);
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
      
      return recursos;
   }

   /**
    * Actualiza el stock de un recurso.
    * 
    * @param recur Una instancia de {@link Recurso} que contiene la información básica del recurso.
    * @param  entradaStock Número de unidades que entran como estoc.
    * @return {@code true} si la operación tiene éxito o {@code false} en cualquier otro caso.
    * 
    * @throws Exception 
    */
   public boolean ActualizarEntradaEstoc(Recurso recurso, int entradaStock) throws SQLException, Exception 
   {
      boolean result = false;
      String sSQL;
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
      sSQL = "UPDATE recurso " +
             "SET cantidad_stock = cantidad_stock + " + entradaStock + ", " +
             "    fecha_ultima_entrada_stock = '" + df.format(recurso.getFechaUltimaEntradaStock()) + "' " +
             "WHERE id_recurso = " + recurso.getIdRecurso();

      try 
      {
         Statement objStt = getConnection().createStatement();
         objStt.execute(sSQL);

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
