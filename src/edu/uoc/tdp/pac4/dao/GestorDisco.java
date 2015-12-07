package edu.uoc.tdp.pac4.dao;

import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.imageio.IIOException;

public class GestorDisco 
{

	public static final String SQL_STATE_UNIQUE_VIOLATION = "23505";
	
	private Connection objConn;
	private LanguageUtils txt = null;
	
   //===========================================
   // Constructors
   //===========================================
   
	/**
	 * Constructor
	 */
	public GestorDisco()
	{
		objConn = null;
	}

   //===========================================
   // Methods
   //===========================================
   
   /**
	 * Devuelve la connexión a la base de datos.
	 */
	public Connection getConnection()
	{
		return objConn;
	}
   
	/**
	 * Cierra la conexión la BD
	 * 
	 * @throws RemoteException
	 */
	public void tancaConnexio() throws SQLException
	{
		if (objConn != null)
		{
			try
			{
				objConn.close();
			}
			catch (SQLException eSQL)
			{
            throw new SQLException(LanguageUtils.getMessage("bbdd.err.cerrar") + "\n" + eSQL.getMessage());
			}

			objConn = null;
		}
	}
   
   /**
    * Inicializa la conexión con la base de datos.
    * 
    * @throws SQLException
    * @throws FileNotFoundException
    * @throws IOException
    * @throws ClassNotFoundException
    * @throws Exception 
    */
	public void initConnection() throws SQLException, FileNotFoundException, IOException, ClassNotFoundException, Exception
	{
		String sUrl = null;
		String sUserName = null;
		String sPassword = null;
		Properties objProperties = new Properties();

		// Obtiene las propiedades de la conexión en base al fichero de propiedades 'configutation.properties'
		try 
		{
			FileInputStream objFIS = new FileInputStream("properties/configuration.properties");
			objProperties.load(objFIS);
			sUrl = objProperties.getProperty("url");
			sUserName = objProperties.getProperty("username");
			sPassword = objProperties.getProperty("password");
		}
		catch (FileNotFoundException ex) 
		{
         throw new FileNotFoundException(LanguageUtils.getMessage("bbdd.err.noencontrado") + "\n" + ex.getMessage());
		}
		catch (IOException ex) 
		{
         throw new IIOException(LanguageUtils.getMessage("bbdd.err.io") + "\n" + ex.getMessage());
		}
		catch (Exception ex)
		{
         throw new Exception(LanguageUtils.getMessage("bbdd.err.unknown") + "\n" + ex.getMessage());
		}

		// Carga el Driver de la BD
		try 
		{
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException eCnfe) 
		{
         throw new ClassNotFoundException(LanguageUtils.getMessage("bbdd.err.drvr") + "\n" + eCnfe.getMessage());
		}

		// Obtiene la conexión con la BD
		try 
		{
			objConn = DriverManager.getConnection(sUrl, sUserName, sPassword);
		}
		catch (SQLException eSQL) 
		{
         throw new SQLException(LanguageUtils.getMessage("bbdd.err.open") + "\n" + eSQL.getMessage());
		}
	}
   
   /**
    * Ejecuta una senténcia SQL y devuelve los resultados. 
    * Usualmente se usa este método para senténcias de consulta (SELECT).
    * 
    * @param sql Una cadena que contiene la senténcia SQL.
    * @return Una instancia de {@link ResultSet} con los resultados obtenidos.
    * 
    * @throws SQLException
    * @throws ClassNotFoundException
    * @throws DataException 
    */
   public ResultSet executeSql(String sql) throws SQLException, ClassNotFoundException, Exception
   {
      // Memoritza la sentència SQL
      // this.lastSqlStatement = sql;
      
      // Obliga a tener la conexión abierta
      if (this.objConn == null)
      {
         this.initConnection();
      }
      
      Statement st = objConn.createStatement();
      return st.executeQuery(sql);
   }
   
   /**
    * Executa una sentència SQL de la que no s'espera cap resultat (p. ex. INSERT, UPDATE, DELETE).
    * 
    * @param sql Sentència SQL a executar (consulta).
    * 
    * @return {@code True} si l'execució ha tingut èxit o {@code False} en qualsevol altre cas.
    */
   public boolean execute(String sql) throws SQLException, ClassNotFoundException, Exception
   {
      // Memoritza la sentència SQL
      // this.lastSqlStatement = sql;
      
      // Obliga a tener la conexión abierta
      if (this.objConn == null)
      {
         this.initConnection();
      }
      
      Statement st = objConn.createStatement();
      return st.execute(sql);
   }
   
   /**
    * Executa una consulta SQL i retorna el valor enter ({@link Integer} de la primera fila i primera columna.
    * 
    * @param sql Sentència SQL a executar (consulta).
    * 
    * @return Un valor enter correspòn al valor de la cel·la 0,0.
    */
   public Integer executeScalar(String sql) throws SQLException, ClassNotFoundException, Exception 
   {
      // Memoritza la sentència SQL
      // this.lastSqlStatement = sql;
      
      // Obliga a tener la conexión abierta
      if (this.objConn == null)
      {
         this.initConnection();
      }
      
      Statement st = objConn.createStatement();
      ResultSet rs = st.executeQuery(sql);
      if (!rs.next())
      {
         throw new Exception("No data found.");
      }
      
      return rs.getInt(1);
   }
   
   /**
    * Executa una consulta SQL i retorna la cadena de text (o el valor en format {@link String}) de la primera fila i primera columna.
    * 
    * @param sql Sentència SQL a executar (consulta).
    * 
    * @return Una cadena de text que correspòn al valor de la cel·la 0,0.
    */
   public String executeString(String sql) throws SQLException, ClassNotFoundException, Exception 
   {
      // Memoritza la sentència SQL
      // this.lastSqlStatement = sql;
      
      // Obliga a tener la conexión abierta
      if (this.objConn == null)
      {
         this.initConnection();
      }
      
      Statement st = objConn.createStatement();
      ResultSet rs = st.executeQuery(sql);
      if (!rs.next())
      {
         throw new Exception("No data found.");
      }
      
      return rs.getString(1);
   }
   
   //===========================================
   // Static members
   //===========================================
   
   /**
    * Formata una cadena de text per a poder-la emprar en qualsevòl sentència SQL.
    * 
    * @param text Cadena de text a formatar.
    * @return Una cadena de text que es pot emprar en qualsevol sentència SQL.
    */
   public static String sqlFormatTextValue(String text)
   {
	   String ftext = text.trim().replace("'", "''");
	   return ftext;
   }
}
