package edu.uoc.tdp.pac4.remote;

import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.PeticionRecurso;
import edu.uoc.tdp.pac4.beans.PeticionRecursoDetalle;
import edu.uoc.tdp.pac4.beans.Recurso;
import edu.uoc.tdp.pac4.exceptions.DuplicatedRequestException;
import edu.uoc.tdp.pac4.exceptions.TooManyRequestsException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface GestorEstoc extends java.rmi.Remote 
{
   /**
   * Obtiene la informacion en el combobox del tipo de recurso (Tabla RECURS Campo nombre_recurso)
   * 
   * @return una lista de los tipos de recurso
   * 
   * @throws RemoteException
   * @throws SQLException
   * @throws Exception
   */
   List<Recurso> getRecursos() throws RemoteException, SQLException, Exception;
    
   /**
   * Obtiene la informacion en el combobox de las diferentes aulas (Tabla AULA Campo descripcio_aula)
   * 
   * @return una lista de las diferentes aulas
   * 
   * @throws RemoteException
   * @throws SQLException
   * @throws Exception
   */
   List<Aula> getAulas() throws RemoteException, SQLException, Exception;

    
   /**
   * Dar de alta una entrada de estoc - Se ha aumentara del estoc la cantidad del recurso solicitado 
   * 
   * @param Tipo de recurso
   * @param Cantidad
   * @return true si la entrada de estoc se ha realizado con exito
   * 
   * @throws SQLException
   * @throws Exception
   * @throws RemoteException
   */
   public boolean setEntradaStock(int idRecursoo, int cantidad, java.sql.Date fecha) throws RemoteException, SQLException, Exception;
    
   /**
   * Dar de alta una peticion de recurso - Se realizara un INSERT sobre la tabla PETICIO_RECURS
   * 
   * @param Descripcion Aula
   * @param Tipo de recurso
   * @param Cantidad solicitada
   * @param Fecha de alta de la peticion
   * @return true si la peticion de recursos se ha realizado con exito
   * 
   * @throws RemoteException
   * @throws SQLException
   * @throws TooManyRequestsException
   * @throws DuplicatedRequestException
   * @throws Exception
   */
   public boolean setPeticionRecursos(int idAula, int idRecursoo, int cantidad, java.sql.Date fecha) throws RemoteException, SQLException, TooManyRequestsException, DuplicatedRequestException, Exception;
   
   /**
   * Obtiene la informacion en la lista de las peticiones de recursos aceptadas segun el estoc actual
   * 
   * @return una lista de las diferentes peticiones de recursos aceptadas segun el estoc actual
   * 
   * @throws  RemoteException
   * @throws SQLException
   * @throws Exception
   */
   List<PeticionRecursoDetalle> getPeticionesRecursos() throws RemoteException, SQLException, Exception;
   
   /**
    * Marca una petición como aceptada y servida.
    * 
    * @param peticion Una instancia de {@link PeticionRecurso} que contiene los datos de la petición.
    * @return {@code true} si la operación tiene exito o {@code false} en cualquier otro caso.
    * 
    * @throws RemoteException
    * @throws SQLException
    * @throws Exception 
    */
   public boolean aceptarPeticionRecursos(PeticionRecurso peticion) throws RemoteException, SQLException, Exception;
    
}
