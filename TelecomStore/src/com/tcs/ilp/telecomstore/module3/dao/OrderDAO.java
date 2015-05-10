package com.tcs.ilp.telecomstore.module3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.ilp.telecomstore.module3.dto.DefectiveProductBean;
import com.tcs.ilp.telecomstore.module3.dto.OrderDetailsBean;
import com.tcs.ilp.telecomstore.module3.dto.OrderProductBean;
import com.tcs.ilp.telecomstore.module3.dto.OrderShipmentBean;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;
import com.tcs.ilp.telecomstore.module3.log4j.Log4j;
import com.tcs.ilp.telecomstore.module3.connect.ConnectionFactory;



public class OrderDAO {

	private static Object obj=new Object();
	  
	
	  Logger _appLogger=Log4j.get_appLogger();
	 
	  
	  /*
	  @author T139 Group C Pair 3
	  Employee id:553659 and 552119
	  Modified by:Group B, Dhupendra And Aparna 
      Reason:For arranging drop down list in ascending order
      Date:07-mar,2012
      
      
      Modified by:Group B ,Deekshith and Priyamvadha
      Reason:For viewing Orders based on processing and dispatched status. 
	  */  
	public ArrayList<OrderProductBean> getProductList(int a)throws UserExistsException, SQLException	//To get the list of products
	{
		ArrayList<OrderProductBean>al=new ArrayList<OrderProductBean>();
		Connection cn=null;
		String pname=null;
		int quan=0;
		ResultSet rs=null;
		cn=ConnectionFactory.getConnection();
		Statement st=null;
	System.out.println("id"+a);
	
		try
		{
			st=cn.createStatement();
			rs=st.executeQuery("select A.productmodel_id,count(A.product_code) as quantity from PRODUCT_STOCK_TBL A join RTL_PRODUCTMODEL_TBL B on A.PRODUCTMODEL_ID=B.PRODUCTMODEL_ID where B.RETAILER_ID="+a+" and A.PRODUCT_STATUS in ('Available','AVAILABLE','Cancelled')  group by A.productmodel_id order by A.productmodel_id ");
			while(rs.next())
			{
				pname=rs.getString(1);
				quan=rs.getInt(2);
				OrderProductBean obj=new OrderProductBean(pname,quan);
				al.add(obj);
		
			}
			
		}
		
		finally
		{
			
				cn.close();
				st.close();
		
			
		}
		return al; //returns the list of available products
	}
	
	 /*
	  @author T139 Group C Pair 3
	  Employee id:553659 and 552119
	  */  
	public int placeOrder(ArrayList<OrderProductBean>ol,OrderShipmentBean cus,int retid)throws UserExistsException, SQLException		//For placing a order
	{

		Connection cn=null;
		cn=ConnectionFactory.getConnection();
		Statement st=null;
		ResultSet rs=null;
		ResultSet ts=null;
		PreparedStatement ps=null;
		int t=0;
		int c=0;
		int orderid=0;
		int pcode=0;
		synchronized(obj)
		{
		try
		{
			st=cn.createStatement();
			rs=st.executeQuery("SELECT MAX(ORDER_ID),MAX(CUSTOMER_ID) FROM ORDER_SHIPMENT_TBL");//generating orderid and customer id
			while(rs.next())
			{
				t=rs.getInt(1);
				c=rs.getInt(2);
		
			}
	
			orderid=t+1; 
			int custid=c+1;
			ps=cn.prepareStatement("insert into ORDER_SHIPMENT_TBL VALUES(?,?,?,?,?,?)"); //inserting order shipment details
			ps.setInt(1,orderid);
			ps.setInt(2,custid);
			ps.setString(3,cus.getCust_name());
			ps.setString(4,cus.getAddress());
			ps.setLong(5,cus.getPhno());
			ps.setString(6,cus.getEmail());
			ps.executeUpdate();
			for(OrderProductBean order:ol)
			{
				ps=cn.prepareStatement("INSERT INTO ORDER_PRODUCTMODEL_TBL VALUES (?,?,?)");
				ps.setInt(1,orderid);
				ps.setString(2,order.getProductname());
				ps.setInt(3,order.getQuantity());
				int up=ps.executeUpdate();
				
			}
	
			float price=0;
			float totalamt=0;
			for(OrderProductBean obj:ol)
			{
				ps=cn.prepareStatement("SELECT PRODUCTMODEL_PRICE FROM PRODUCTMODEL_INFO_TBL WHERE PRODUCTMODEL_ID=?");//retrieving price details 
				ps.setString(1,obj.getProductname());
				ts=ps.executeQuery();
	
				while(ts.next())
				{
					price=ts.getFloat(1);
					totalamt=totalamt+(price*obj.getQuantity());
					
				}
			}
			ps=cn.prepareStatement("insert into ORDER_TBL(ORDER_ID,RETAILER_ID,ORDER_DATE,ORDER_AMOUNT) values(?,?,sysdate,?)");
			ps.setInt(1,orderid);
			ps.setInt(2,retid);
			ps.setFloat(3,totalamt);
			ps.executeUpdate();
		
	
	
			for(OrderProductBean ob:ol)
			{
				for(int j=0;j<ob.getQuantity();j++)
				{    int count=0;
					st=cn.createStatement();
					String pr=ob.getProductname();
					
		     ResultSet rss= st.executeQuery("SELECT PRODUCT_CODE from PRODUCT_STOCK_TBL WHERE PRODUCTMODEL_ID='"+pr+"' and product_status in('Available','AVAILABLE','Cancelled')");
		             while(rss.next() && count==0)
		             {
		            	 pcode=rss.getInt(1);
		            	
		            	 count++;
		             }
					st.executeUpdate("UPDATE PRODUCT_STOCK_TBL set PRODUCT_STATUS='Allocated',ORDER_ID="+orderid+" where PRODUCT_CODE="+pcode+"");
					
				  }
		
			      }
			cn.commit();
	}
	
		finally
		{
			
				cn.close();
			
		}
		return orderid; //returns the orderid
		
		
	}}
	 /*
	  @author T139 Group C Pair 3
	  Employee id:553659 and 552119
	  */  
	public boolean checkOrder(int ret,int orderid)throws UserExistsException, SQLException		//To check the order id with retailer id
	{Connection cn=null;
	
	cn=ConnectionFactory.getConnection();
	PreparedStatement st=null;
	ResultSet rs=null;
	boolean check=false;
	
	try
	{
		st=cn.prepareStatement("Select * from ORDER_TBL WHERE ORDER_ID=? AND RETAILER_ID=?");
	    st.setInt(1,orderid);
	    st.setInt(2,ret);
	    rs=st.executeQuery();
	    while(rs.next())
	    {
	  check=true;
	    }


    }
	
	finally
	{
		
			cn.close();
			st.close();
		
	}
	return check;
		
	}
	 /*
	  @author T139 Group C Pair 3
	  Employee id:553659 and 552119
	  */  
		public ArrayList<DefectiveProductBean> getProductCode(int oid)throws UserExistsException, SQLException		//To get the product codes under a order id
		{ArrayList<DefectiveProductBean>as=new ArrayList<DefectiveProductBean>();

		Connection cn=null;
		
		cn=ConnectionFactory.getConnection();
		PreparedStatement st=null;
		ResultSet rs=null;
		DefectiveProductBean obj=null;
	
	try
	{
		st=cn.prepareStatement("Select PRODUCT_CODE,PRODUCTMODEL_ID FROM PRODUCT_STOCK_TBL WHERE ORDER_ID=? AND PRODUCT_STATUS=? ORDER BY PRODUCT_CODE");
		st.setInt(1, oid);
		st.setString(2, "Received");
		rs=st.executeQuery();
		while(rs.next())
		{
			obj=new DefectiveProductBean(rs.getString(1),rs.getString(2));
			as.add(obj);
		}
	}
	
	finally
	{
		
			cn.close();
			st.close();
		
		
	}
	return as;   //returns the list of product codes and product id of a particular order id
	}
		 /*
		  @author T139 Group C Pair 3
		  Employee id:553659 and 552119
		  */  
		public int placeReorder(String prod[],int ret)throws UserExistsException, SQLException		//For placing reorder of defective product
		{
			Connection cn=null;
		
		    cn=ConnectionFactory.getConnection();
		    PreparedStatement ps=null;
		    Statement st=null;
		    String code=null;
		    String id=null;
		    String newcode=null;
		    int t=0;
		    int c=0;
		    int oldid=0;
		    String msg="error";
		    String name=null;
		    String address=null;
		    String email=null;
		    ResultSet rs=null;
		    ResultSet co=null;
		    ResultSet pp=null;
		    ResultSet rt=null;
		    long ph=0;
		    int orderid=0;
		    int result=0;
	     synchronized(obj){
		   
	      
		    try
		    {
			st=cn.createStatement();
			ResultSet rs1=st.executeQuery("SELECT max(ORDER_ID),max(CUSTOMER_ID) FROM ORDER_SHIPMENT_TBL");//generating new order id and customer id
			while(rs1.next())
			{
				 t=rs1.getInt(1);
				 c=rs1.getInt(2);
				
			}
			
			orderid=t+1;
			int custid=c+1;
			
			for(int i=0;i<prod.length;i++)
			{
				code=prod[i];
		
				ps=cn.prepareStatement("SELECT PRODUCTMODEL_ID,ORDER_ID FROM PRODUCT_STOCK_TBL WHERE PRODUCT_CODE =?");
				ps.setString(1,code);
				rs=ps.executeQuery();
				while (rs.next())
				{
					id=rs.getString(1);
					
					oldid=rs.getInt(2);		
				
				}
				ps=cn.prepareStatement("SELECT * FROM ORDER_SHIPMENT_TBL WHERE ORDER_ID=?");//retrieving shipment details from previous of order id
				ps.setInt(1,oldid);
				pp=ps.executeQuery();
				while(pp.next())
				{
					name=pp.getString(3);
					address=pp.getString(4);
					ph=pp.getLong(5);
					email=pp.getString(6);
				}
		
		
				ps=cn.prepareStatement("INSERT INTO ORDER_SHIPMENT_TBL VALUES(?,?,?,?,?,?)"); //inserting order shipment details
				ps.setInt(1,orderid);
				ps.setInt(2,custid);
				ps.setString(3,name);
				ps.setString(4,address);
				ps.setLong(5,ph);
				ps.setString(6,email);
				ps.executeUpdate();
				ps=cn.prepareStatement("UPDATE PRODUCT_STOCK_TBL SET PRODUCT_STATUS=? WHERE PRODUCT_CODE=? "); //updating the status of product stock
				ps.setString(1,"With Defects");
				ps.setString(2,code);
				ps.executeUpdate();
				ps=cn.prepareStatement("SELECT PRODUCT_CODE FROM PRODUCT_STOCK_TBL WHERE PRODUCTMODEL_ID=? AND ORDER_ID=?"); //getting new product codes
				ps.setString(1,id);
				ps.setInt(2,0);
				rt=ps.executeQuery();
				while (rt.next())
				{
					newcode=rt.getString(1);
				}
				st=cn.createStatement();
				st.executeUpdate("UPDATE PRODUCT_STOCK_TBL set PRODUCT_STATUS='Allocated',ORDER_ID="+orderid+" where ORDER_ID=0 AND  PRODUCT_CODE='"+newcode+"'"); //for allocating new product codes
				ps=cn.prepareStatement("INSERT INTO DEFECT_PRODUCT_ORDER values(?,?,?)");
				ps.setInt(1,orderid);
				ps.setString(2,code);
				ps.setString(3,newcode);
				ps.executeUpdate();
	
				
			}
			ps=cn.prepareStatement("SELECT PRODUCTMODEL_ID,COUNT(PRODUCT_CODE)FROM  PRODUCT_STOCK_TBL WHERE ORDER_ID=? GROUP BY PRODUCTMODEL_ID"); //for getting quantity of each product model id
			ps.setInt(1,orderid);
			co=ps.executeQuery();
		
			while(co.next())
			{
				String newid=co.getString(1);
				int quan=co.getInt(2);
			
	
				ps=cn.prepareStatement("INSERT INTO ORDER_PRODUCTMODEL_TBL VALUES(?,?,?)"); //inserting new orderid,product code and quantity
				ps.setInt(1,orderid);
				ps.setString(2,newid);
				ps.setInt(3,quan);
				ps.executeUpdate();
			}
		
			ps=cn.prepareStatement("INSERT INTO ORDER_TBL(ORDER_ID,RETAILER_ID,ORDER_DATE,ORDER_AMOUNT) VALUES(?,?,sysdate,?)"); //updating the order table
			ps.setInt(1,orderid);
			ps.setInt(2,ret);
		
			ps.setFloat(3,0);
			ps.executeUpdate();
	
			result=orderid;
			cn.commit();
		}
		
		    
		   
		    finally
		    {
		    	
		    		cn.close();
		    		st.close();
		    		ps.close();
		    	
		
		    }
		    return orderid;                          //return the order id
		}}
		 /*
		  @author T139 Group C Pair 3
		  Employee id:553659 and 552119
		  */  
	 public int updateInventoryOnOrder(int a)throws UserExistsException, SQLException		//To update the inventory when an order is placed
	 {
		 Connection cn=null;
		
		cn=ConnectionFactory.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int update=0;
		String modelid=null;
		int quan=0;
		synchronized(obj)
		{
		try
		{
			ps=cn.prepareStatement("SELECT PRODUCTMODEL_ID,COUNT(PRODUCT_CODE) FROM PRODUCT_STOCK_TBL WHERE ORDER_ID=? GROUP BY PRODUCTMODEL_ID");
			ps.setInt(1,a);
			rs=ps.executeQuery();
			while(rs.next())
			{
				modelid=rs.getString(1);
				quan=rs.getInt(2);
				Statement st=cn.createStatement();
				update=st.executeUpdate("UPDATE INVENTORY_TBL SET QUANTITY =QUANTITY-"+quan+" WHERE PRODUCTMODEL_ID='"+modelid+"'");
	             cn.commit();
			}
		}
		
		finally
		{
			
				cn.close();
				ps.close();
			
		}
		return update;
		
	}}
	 /*
	  @author T139 Group C Pair 3
	  Employee id:553659 and 552119
	  */  
	public float getPrice(int a)throws UserExistsException, SQLException		//To get the total price for an order
	{
		Connection cn=null;
		float price=0;
	
		cn=ConnectionFactory.getConnection();
		PreparedStatement st=null;
		ResultSet rs=null;
		try
		{
			st=cn.prepareStatement("SELECT ORDER_AMOUNT FROM ORDER_TBL WHERE ORDER_ID=?");
			st.setInt(1,a);
			rs=st.executeQuery();
			while(rs.next())
			{
				price=rs.getFloat(1);
			}
		}
		
		finally
		{
			
				cn.close();
				st.close();
			
		}
		return price;                         //returns the price of the order
	}
	
	 /*
	  @author T139 Group C Pair 2
	  Employee id:560307 and 553082
	  */  
	public String getStatus(int a)throws UserExistsException, SQLException		//To get status of an order
	{
            
		    String status=null;
		    Connection cn=null;
		    cn=ConnectionFactory.getConnection();
		    Statement st=null;
		    ResultSet rs=null;
		    try
		    {
		    	st=cn.createStatement();
		    	rs=st.executeQuery("select PRODUCT_STATUS from PRODUCT_STOCK_TBL where order_id="+a+"");
		    	while(rs.next())
		    	{
		    		status=rs.getString(1);
		    	}
		    }
		   
		 finally
		 {
			 
				 cn.close();
				 st.close();
				 
			
		 }
		return status;
		    }
	
	 /*
	  @author T139 Group C Pair 3
	  Employee id:553659 and 552119
	  */  
	public String getStatusForDefective(int a)throws UserExistsException, SQLException		//To get status of an order
	{
            
		    String status="notelligible";
		    Connection cn=null;
		    cn=ConnectionFactory.getConnection();
		    Statement st=null;
		    ResultSet rs=null;
		    try
		    {
		    	st=cn.createStatement();
		    	rs=st.executeQuery("select * from PRODUCT_STOCK_TBL where order_id="+a+" and product_status='Received'");
		    	while(rs.next())
		    	{
		    		status="elligible";
		    	}
		    }
		   
		 finally
		 {
			 
				 cn.close();
				 st.close();
				 
			
		 }
		return status;
		    }
	
	
	 /*
	  @author T139 Group C Pair 1
	  Employee id:553135 and 557622
	  */  
	public int setAcknowledgement(int a)throws UserExistsException, SQLException		//To set acknowledgment from "dispatched" to "received"
	{
            
		    int status=0;
		    Connection cn=null;
		    cn=ConnectionFactory.getConnection();
		    Statement st=null;
	
		    try
		    {
		    	st=cn.createStatement();
		    	status=st.executeUpdate("update PRODUCT_STOCK_TBL set PRODUCT_STATUS='Received' where PRODUCT_STATUS='Dispatched' and order_id="+a+"");
		    	cn.commit();
		    	
		    }
		   
		    finally
		    {
		    	
		    		cn.close();
		    		st.close();
		    	
		    }
		    return status;
		   }
	

	 /*
	  @author T139 Group C Pair 1
	  Employee id:553135 and 557622
	  */  
	public ArrayList<OrderDetailsBean> viewallorder(String startd,String endd)throws UserExistsException, SQLException		//for viewing all order between two given date
	{
		ArrayList<OrderDetailsBean> list=new ArrayList<OrderDetailsBean>();
		Connection con=ConnectionFactory.getConnection();
		Statement st=null;
		ResultSet rs=null;
		try
		{
			st=con.createStatement();
			String sql="select distinct o.order_id,o.order_date,o.order_amount,p.product_status from order_tbl o join product_stock_tbl p  on o.order_id=p.order_id where o.order_date > to_date('"+startd+"','yyyy/mm/dd') and (o.order_date< to_date('"+endd+"','yyyy/mm/dd')or o.order_date= to_date('"+endd+"','yyyy/mm/dd'))  order by o.order_id";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				OrderDetailsBean obj=new OrderDetailsBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				list.add(obj);
			}
		}
		
		finally
		 {
			 
				 con.close();
				 st.close();
			
		
		 }
		return list;
	}
	 /*
	 @author T139 Group B Pair 2
	  Employee id:Priyamvadha and Deekshith
	  */  
	public ArrayList<OrderDetailsBean> viewprocessingorder(String startd,String endd)throws UserExistsException, SQLException		//for viewing all order between two given date
	{
		ArrayList<OrderDetailsBean> list=new ArrayList<OrderDetailsBean>();
		Connection con=ConnectionFactory.getConnection();
		Statement st=null;
		ResultSet rs=null;
		try
		{
			st=con.createStatement();
			String sql="select distinct o.order_id,o.order_date,o.order_amount,p.product_status from order_tbl o join product_stock_tbl p  on o.order_id=p.order_id where p.product_status='Allocated' and o.order_date > to_date('"+startd+"','yyyy/mm/dd') and (o.order_date< to_date('"+endd+"','yyyy/mm/dd')or o.order_date= to_date('"+endd+"','yyyy/mm/dd')) order by o.order_id";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				OrderDetailsBean obj=new OrderDetailsBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				list.add(obj);
			}
		}
		
		finally
		 {
			
				 con.close();
				 st.close();
			 
		
		 }
		return list;
	}
	 /*
	  @author T139 Group B Pair 2
	  Employee id:Priyamvadha and Deekshith
	  */  
	public ArrayList<OrderDetailsBean> viewdispatchedorder(String startd,String endd)throws UserExistsException, SQLException		//for viewing all order between two given date
	{
		ArrayList<OrderDetailsBean> list=new ArrayList<OrderDetailsBean>();
		Connection con=ConnectionFactory.getConnection();
		Statement st=null;
		ResultSet rs=null;
		try
		{
			st=con.createStatement();
			String sql="select distinct o.order_id,o.order_date,o.order_amount,p.product_status from order_tbl o join product_stock_tbl p  on o.order_id=p.order_id where p.product_status='Dispatched' and o.order_date > to_date('"+startd+"','yyyy/mm/dd') and (o.order_date< to_date('"+endd+"','yyyy/mm/dd')or o.order_date= to_date('"+endd+"','yyyy/mm/dd')) order by o.order_id";
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				OrderDetailsBean obj=new OrderDetailsBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				list.add(obj);
			}
		}
	
		finally
		 {
			
				 con.close();
				 st.close();
			
		
		 }
		return list;
	}

	 /*
	  @author T139 Group C Pair 2
	  Employee id:560307 and 553082
	  */  
	public String cancelorder(int a)throws UserExistsException, SQLException		//for canceling an order
	{
		    String msg=null;
		    Connection cn=null;
		    cn=ConnectionFactory.getConnection();
		    PreparedStatement ps=null;
		    Statement st=null;
	synchronized(obj)
	{
	try
	{
		
		ps=cn.prepareStatement("delete from ORDER_SHIPMENT_TBL where order_id="+a+""); //deleting order shipment details
		ps.executeUpdate();
		
		st=cn.createStatement();
		st.executeUpdate("update PRODUCT_STOCK_TBL set order_id=0,PRODUCT_STATUS='Cancelled' where order_id='"+a+"'"); //updating the product stock table
		
		cn.commit();
		
	}
	 
	 finally
	 {
		 
			 cn.close();
			 ps.close();
			 st.close();
		
	 }
	 return msg;	
   }}
	
	/*
	  @author T139 Group C Pair 2
	  Employee id:560307 and 553082
	  */  
	public int UpdateCancelInventoryDao(int a)throws UserExistsException, SQLException		//for updating inventory for canceling a order

	{
		    int status=0;
		   
		    Connection cn=null;
		    cn=ConnectionFactory.getConnection();
		    Statement st=null;
		    String pMODEL=null;
		    ResultSet rs=null;
		    int qty=0;
		    synchronized(obj)
		    {
		    try
			{
		    	st=cn.createStatement();
		    	rs=st.executeQuery("select PRODUCTMODEL_ID,COUNT(PRODUCT_CODE) from PRODUCT_STOCK_TBL where order_id="+a+" and product_status='Allocated' group by PRODUCTMODEL_ID" );
		    	while(rs.next())
		    	{  
		    		pMODEL=rs.getString(1);
		    		qty=rs.getInt(2);
		    		status=st.executeUpdate("update INVENTORY_TBL set QUANTITY=QUANTITY+"+qty+" where PRODUCTMODEL_ID='"+pMODEL+"'");
		    	     cn.commit();
		    	}
		    	
			}
		    
		 finally
		 {
			
				 cn.close();
				 st.close();
				 
			
		 }
		 return status;
	   }
	
	}
	public void rollbackTransaction(Connection cn) throws UserExistsException, SQLException//for rolling back of transactions
	{
		try {
			cn.rollback();
		} catch (SQLException e) {
			 _appLogger.error("Exception" +e.getMessage());
			
		}
	}
}
		

	

