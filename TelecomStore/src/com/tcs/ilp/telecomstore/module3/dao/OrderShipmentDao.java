package com.tcs.ilp.telecomstore.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.ilp.telecomstore.module3.connect.ConnectionFactory;
import com.tcs.ilp.telecomstore.module3.dto.OrderShipmentBean;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;
import com.tcs.ilp.telecomstore.module3.log4j.Log4j;



/*
 @author T139 Group C Pair 1
 Employee id:553135 and 557622
 */  

public class OrderShipmentDao {

      
 private Object obj=new Object();
	Logger _appLogger=Log4j.get_appLogger();
	public ArrayList<OrderShipmentBean> RetrieveCustomer(int a)	throws UserExistsException, SQLException	// To Retrieve customer Details  
	{
		ArrayList<OrderShipmentBean>al=new ArrayList<OrderShipmentBean>();
		Connection cn=null;
		cn=ConnectionFactory.getConnection();
		Statement st=null;
		ResultSet rs=null;
		OrderShipmentBean obj=null;
		
		try
		{
			st=cn.createStatement();
			rs=st.executeQuery("SELECT * FROM ORDER_SHIPMENT_TBL WHERE ORDER_ID="+a+"");
			while (rs.next())
			{
				obj=new OrderShipmentBean(rs.getString(3),rs.getString(4),rs.getLong(5),rs.getString(6));
				al.add(obj);
			}
		}
		
		finally
		{
			
			cn.close();
			st.close();
			
		}
		return al;
		
	}

	 /*
	  @author T139 Group C Pair 1
	  Employee id:553135 and 557622
	  */  

	public boolean update(OrderShipmentBean cust,int order_id)  throws UserExistsException, SQLException  //To Update customer Details
	{
		Connection cn=null;
		
		cn=ConnectionFactory.getConnection();
		PreparedStatement st=null;
		boolean check=false;
		synchronized(obj)
		{
		try
		{
			st=cn.prepareStatement("UPDATE ORDER_SHIPMENT_TBL SET CUSTOMER_NAME=?,SHIPMENT_ADDRESS=?,PHONE_NO=?,EMAIL_ID=? WHERE ORDER_ID=?");
			st.setString(1,cust.getCust_name());
			st.setString(2,cust.getAddress());
			st.setLong(3,cust.getPhno());
			st.setString(4,cust.getEmail());
			st.setInt(5,order_id);
	        check=st.executeUpdate()>0;
	        cn.commit();
		}
		
		finally
		{
			
				cn.close();
				st.close();
				
			
		}
		return check;
	}


     }
	public void rollbackTransaction(Connection cn)throws UserExistsException, SQLException
	{
		try {
			cn.rollback();
		} catch (SQLException e) {
			 _appLogger.error("Exception" +e.getMessage());
			
		}
	}
	}

