package com.tcs.ilp.telecomstore.module3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.ilp.telecomstore.module3.connect.ConnectionFactory;
import com.tcs.ilp.telecomstore.module3.dto.ProductBean;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;
import com.tcs.ilp.telecomstore.module3.log4j.Log4j;


/*
 @author T139 Group C Pair 1
 Employee id:553135 and 557622
 Modified by:Group B, Aparna and Dhupendra
      Reason:For arranging tagged products list in ascending order of product model id
      Date:07-mar,2012
 */  
public class ProductDao {
    private Object obj=new Object();
	Logger _appLogger=Log4j.get_appLogger();
	
	public ArrayList<ProductBean> getTaggedProduct(int a)throws UserExistsException, SQLException		//To get the list of tagged products of a retailer
	{
		Connection cn=null;
		Statement st=null;
		ResultSet rs=null;
		cn=ConnectionFactory.getConnection();
		ArrayList<ProductBean>ar=new ArrayList<ProductBean>();
		try
		{
			st=cn.createStatement();
			rs=st.executeQuery("select * from PRODUCTMODEL_INFO_TBL JOIN RTL_PRODUCTMODEL_TBL ON  PRODUCTMODEL_INFO_TBL.PRODUCTMODEL_ID= RTL_PRODUCTMODEL_TBL.PRODUCTMODEL_ID WHERE RETAILER_ID='"+a+" 'order by PRODUCTMODEL_INFO_TBL.PRODUCTMODEL_ID  ");
			while(rs.next())
			{
				ProductBean ob=new ProductBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getInt(6));
				ar.add(ob);
			}

		}
		
		finally
		{
			
				cn.close();
				st.close();
			
		}
		return ar;                                       //returns the list of tagged products
	}
	 /*
	  @author T139 Group C Pair 2
	  Employee id:560307 and 553082
	  Modified by:Group B, Aparna and Dhupendra
      Reason:For arranging available products list in ascending order of product model id
      Date:07-mar,2012
	  
	  */  

		public ArrayList<ProductBean> getAvailableProduct(int a)throws UserExistsException, SQLException		//To get the list of available  products tagged to that retailer
		{   
			Connection cn=null;
			Statement st=null;
			ResultSet rs=null;
			cn=ConnectionFactory.getConnection();
			ArrayList<ProductBean>ar=new ArrayList<ProductBean>();
			try
			{
				st=cn.createStatement();
				rs=st.executeQuery("select distinct(A.PRODUCTMODEL_ID),C.PRODUCTMODEL_NAME,C.PRODUCTMODEL_DESCRIPTION,C.PRODUCTMODEL_FEATURES,C.PRODUCTMODEL_PRICE,C.PRODUCTMODEL_THRESHOLD from PRODUCT_STOCK_TBL A JOIN RTL_PRODUCTMODEL_TBL B ON  A.PRODUCTMODEL_ID= B.PRODUCTMODEL_ID JOIN PRODUCTMODEL_INFO_TBL C on B.PRODUCTMODEL_ID=C.PRODUCTMODEL_ID where B.RETAILER_ID="+a+" AND A.PRODUCT_STATUS in('Available','AVAILABLE','Cancelled')order by A.PRODUCTMODEL_ID ");
		
				while(rs.next())
				{
					ProductBean ob=new ProductBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getInt(6));
					ar.add(ob);
				}
			}
			
			finally
			{
				
					cn.close();
					st.close();
				
			}
			return ar;
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

