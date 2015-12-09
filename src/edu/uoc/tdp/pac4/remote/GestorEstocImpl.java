package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.PeticionRecurso;
import edu.uoc.tdp.pac4.beans.PeticionRecursoDetalle;
import edu.uoc.tdp.pac4.beans.Recurso;
import edu.uoc.tdp.pac4.dao.GestorAulas;
import edu.uoc.tdp.pac4.dao.GestorDisco;
import edu.uoc.tdp.pac4.dao.GestorPeticionRecurso;
import edu.uoc.tdp.pac4.dao.GestorRecursos;
import edu.uoc.tdp.pac4.exceptions.DuplicatedRequestException;
import edu.uoc.tdp.pac4.exceptions.TooManyRequestsException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del interface de control remoto de la aplicación (cliente).
 * 
 * @author JavaBeginers
 */
public class GestorEstocImpl extends java.rmi.server.UnicastRemoteObject implements GestorEstoc, Serializable 
{
   /** Serial version UID */
   private static final long serialVersionUID = 1L;

   GestorDisco gestorDisco;	
   // List <PeticionRecurso> peticiones = new ArrayList<PeticionRecurso>();
   // List <Recurso> recursos = new ArrayList<Recurso>(); 
   
   /**
    * Constructor de la clase.
    * 
    * @throws RemoteException
    * @throws Exception 
    */
   public GestorEstocImpl() throws RemoteException, Exception
   {
      super();

      try 
      {
         gestorDisco = new GestorDisco();
         gestorDisco.initConnection();
      } 
      catch (Exception ex) 
      {
         throw ex;
		}
	}
    
   @Override
   public List<Recurso> getRecursos() throws RemoteException, SQLException, Exception 
   {
      List<Recurso> recursos = new ArrayList<Recurso>();

		try 
      {
			GestorRecursos gr = new GestorRecursos(gestorDisco.getConnection());
			recursos = gr.ConsultarTipoRecurso();
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
    
   @Override
   public List<Aula> getAulas() throws RemoteException, SQLException, Exception
   {
      List<Aula> aulas = new ArrayList<Aula>(); 
		
      try 
      {
			GestorAulas ga = new GestorAulas(gestorDisco.getConnection());
			aulas = ga.getAulas();
		}
      catch (SQLException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
		}

		return aulas;
   }
   
       
   @Override
   public boolean setEntradaStock(int idRecursoo, int cantidad, java.sql.Date fecha) throws RemoteException, SQLException, Exception
   {
      boolean bool = false;
      Recurso recurso;
        
      try 
      { 
         GestorRecursos gr = new GestorRecursos(gestorDisco.getConnection());
         
         recurso = new Recurso(idRecursoo, "", 0, fecha, null);
			bool = gr.ActualizarEntradaEstoc(recurso, cantidad);
		} 
      catch (SQLException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
		}
      
      return bool;
   }
 
   /**
    * Registra una nueva petición de recursos.
    * 
    * @param idAula Identificador único de la aula.
    * @param idRecursoo Identificador único del recurso.
    * @param cantidad Cantidad.
    * @param fecha fecha de alta de la petición.
    * @return {@code true} si la operación tiene éxito o {@code false} en cualquier otro caso.
    * 
    * @throws RemoteException
    * @throws SQLException
    * @throws TooManyRequestsException
    * @throws DuplicatedRequestException
    * @throws Exception 
    */
   @Override
   public boolean setPeticionRecursos(int idAula, int idRecursoo, int cantidad, java.sql.Date fecha) throws RemoteException, SQLException, TooManyRequestsException, DuplicatedRequestException, Exception 
   {
      boolean bool = false;
      PeticionRecurso peticion;
        
      try 
      { 
         GestorPeticionRecurso gpr = new GestorPeticionRecurso(gestorDisco.getConnection());
         
			peticion = new PeticionRecurso(idRecursoo, idAula, fecha, cantidad, null);
			bool = gpr.addPeticion(peticion);
		}
      catch (SQLException ex)
      {
         throw ex;
      }
      catch (DuplicatedRequestException ex)
      {
         throw ex;
      }
      catch (TooManyRequestsException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
			throw ex;
		}

      return bool;
   }

   /**
    * Devuelve una lista de las peticiones pendientes.
    * 
    * @throws RemoteException 
    * @throws SQLException 
    * @throws Exception 
    */
   @Override
   public List<PeticionRecursoDetalle> getPeticionesRecursos() throws RemoteException, SQLException, Exception 
   {
      boolean result = false;
        
      try 
      { 
			GestorPeticionRecurso gpr = new GestorPeticionRecurso(gestorDisco.getConnection());
			return gpr.getPeticiones();
		}
      catch (SQLException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
		}
   }

   /**
    * Acepta una petición de recursos.
    * 
    * @throws RemoteException 
    * @throws SQLException
    * @throws Exception 
    */
   @Override
   public boolean aceptarPeticionRecursos(PeticionRecurso peticion) throws RemoteException, SQLException, Exception 
   {
      boolean result = false;
        
      try 
      { 
			GestorPeticionRecurso gpr = new GestorPeticionRecurso(gestorDisco.getConnection());
			return gpr.setAceptacion(peticion);
		} 
      catch (SQLException ex)
      {
         throw ex;
      }
      catch (Exception ex) 
      {
         throw ex;
		}
   }
}
