
/*
 @author T139 Group C
 */
package com.tcs.ilp.telecomstore.module3.connect;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
	private static final String USERNAME="PJ01DEVT139";
	private static final String PASSWORD="tcstvm250";
	private static final String URL="jdbc:oracle:thin:@intvmoradb01:1521:ORAJAVADB";
	private static final String DRIVER_NAME="oracle.jdbc.OracleDriver";
	
	public static Connection getConnection()
	{
		Connection con=null;
		try
		{
			Class.forName(DRIVER_NAME);
			con= DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e.getMessage());
		}
		return con;
	}
}
