package com.tcs.ilp.telecomstore.module3.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.tcs.ilp.telecomstore.module3.connect.ConnectionFactory;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;
import com.tcs.ilp.telecomstore.module3.log4j.Log4j;

/*
@author T139 Group C Pair 3
Employee id:553659 and 552119
*/

public class RetailerDAO
{  
	Logger _appLogger=Log4j.get_appLogger();
	public String validateRetailer(int a,String b)throws UserExistsException, SQLException
	{
	Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	String msg="error";
	cn=ConnectionFactory.getConnection();
	try
	{
		st=cn.createStatement();
		rs=st.executeQuery("select * FROM LOGIN_TBL WHERE USER_ID="+a+" AND PASSWORD='"+b+"' AND ROLE_TAGGED IN('Retailer','RETAILER')");
	while(rs.next())
	{
		msg="success";
	}
	}
	
	finally
	{
		
			cn.close();
			st.close();
	
		
	}
	return msg;

}
	  /*
	  @author T139 Group C Pair 3
	  Employee id:553659 and 552119
	  */
	  
	public int getRetailerID(String a)throws UserExistsException, SQLException		//To get the retailer id for a user id

	{
		Connection cn=null;
		Statement st=null;
		ResultSet rs=null;
		int retid=0;
		cn=ConnectionFactory.getConnection();
		try
		{
			st=cn.createStatement();
			rs=st.executeQuery("select RETAILER_ID from RTL_INFO_TBL WHERE USER_ID='"+a+"'");
		while(rs.next())
		{
			retid=rs.getInt(1);
		}
		}
		
		finally
		{
			cn.close();
				st.close();
			
			
		}
		return retid;
	}
	  /*
	  @author T139 Group C Pair 3
	  Employee id:553659 and 552119
	  */
	public String getRetailerName(int a) throws UserExistsException, SQLException	//To  the retailer name for an retailer id

	{
		Connection cn=null;
		Statement st=null;
		ResultSet rs=null;
		String retname=null;
		cn=ConnectionFactory.getConnection();
		try
		{
			st=cn.createStatement();
			rs=st.executeQuery("select RETAILER_NAME from RTL_INFO_TBL WHERE RETAILER_ID="+a+"");
			while(rs.next())
			{
				retname=rs.getString(1);
			}
		}
		
		finally
		{
			
				cn.close();
				st.close();
			
			
		}
		return retname;
	}
	
}
