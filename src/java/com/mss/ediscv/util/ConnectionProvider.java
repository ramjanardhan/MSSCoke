/*******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.general
 *
 * Date    : Mar 11, 2013 1:28:58 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : ConnectionProvider.java
 *

 * *****************************************************************************
 */
package com.mss.ediscv.util;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;
import javax.sql.DataSource;

import com.mss.ediscv.util.Properties;
import com.mss.ediscv.util.ServiceLocatorException;

import com.mss.ediscv.util.ConfigProperties;

public class ConnectionProvider {
	
	private static ConnectionProvider _instance;

	private DataSource dataSource;
	private Connection connection;

	private ConnectionProvider() {

	}

	public static ConnectionProvider getInstance() {

		if (_instance == null) {
			_instance = new ConnectionProvider();
		}
		return _instance;
	}
	
    /**
    *
    * @return returns Connection from IntialContext
    */
 /*  public Connection getConnection() throws ServiceLocatorException{
       try{
           
    
    	   //dataSource = DataServiceLocator.getInstance().getDataSource(Properties.getProperty("Db.Datasource.Name"));
           dataSource = DataServiceLocator.getInstance().getDataSource("jndi/mscvp");
           connection = dataSource.getConnection();
       }catch(ServiceLocatorException se) {
           throw new ServiceLocatorException("Exception in Connection Provider");
       }catch(SQLException sqlEx) {
           throw new ServiceLocatorException(sqlEx);
       }
       return connection;
       
        
   }*/
        
        public Connection getConnection() throws ServiceLocatorException{
       try{
         //  String dsnName = ConfigProperties.getProperty("DB.DSNNAME");
          // System.out.println("datasource name"+dsnName);
         System.out.println("in get connection using datasource----"+Properties.getProperty("SQL.DataSource.Name"));
         
           dataSource = DataServiceLocator.getInstance().getDataSource(Properties.getProperty("SQL.DataSource.Name"));
           System.out.println("after get the datasource----"+dataSource+Properties.getProperty("SQL.DataSource.Name"));
           connection = dataSource.getConnection();
           System.out.println("after get the connection!!!"+connection);
       }catch(ServiceLocatorException se) {
           
           throw new ServiceLocatorException("Exception in Connection Provider");
       }catch(SQLException sqlEx) {
           System.err.println("Exception-->"+sqlEx.getMessage());
           throw new ServiceLocatorException(sqlEx);
       }
       return connection;
   }
   
}