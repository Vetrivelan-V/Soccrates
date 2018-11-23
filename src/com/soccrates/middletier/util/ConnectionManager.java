package com.soccrates.middletier.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// TODO: Auto-generated Javadoc
/**
 * Wrapper class for holding the DataSource used to get connections.
 * 
 * @author Keifer
 *
 */
public class ConnectionManager {
	
	/** The t ds. */
	private static DataSource t_ds;
	
	/**
	 * Sets the DataSource from which connections are obtained. Use this method during server
	 * startup.
	 * 
	 * @param dataSource the DataSource used to get connections
	 */
	public static void setDataSource(DataSource dataSource) {
		t_ds = dataSource;
	}
	
	/**
	 * Get a connection from the DataSource.
	 *
	 * @return a new connection
	 * @throws SQLException if a connection cannot be gotten
	 */
	public static Connection getConnection() throws SQLException {
		return t_ds.getConnection();
	}
	
	/**
	 * Creates the data source.
	 */
	public static void createDataSource()  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			InitialContext context = new InitialContext();
			t_ds = (DataSource) context.lookup( "java:/comp/env/jdbc/soccrates" );
		} catch (NamingException e) {
			System.err.println("Couldn't find inital context can't get the data source!"
					+ "\nQueries to database will not work!");
		}catch(ClassNotFoundException e) {
			System.err.println("Cannot find the driver!");
		}
	}
	
	
	
}
