

/*
 @author T139 Group C 
 */
package com.tcs.ilp.telecomstore.module3.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.telecomstore.module3.dao.OrderDAO;
import com.tcs.ilp.telecomstore.module3.dao.OrderShipmentDao;
import com.tcs.ilp.telecomstore.module3.dao.ProductDao;
import com.tcs.ilp.telecomstore.module3.dao.RetailerDAO;
import com.tcs.ilp.telecomstore.module3.dao.RewardsDao;
import com.tcs.ilp.telecomstore.module3.dto.OrderDetailsBean;
import com.tcs.ilp.telecomstore.module3.dto.OrderProductBean;
import com.tcs.ilp.telecomstore.module3.dto.OrderShipmentBean;
import com.tcs.ilp.telecomstore.module3.dto.ProductBean;
import com.tcs.ilp.telecomstore.module3.dto.RewardsBean;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;

public class RetailerSystem {
	public int placeOrder(ArrayList<OrderProductBean> a,OrderShipmentBean cus,int b) throws UserExistsException, SQLException
	{
		OrderDAO ob=null;
		ob=new OrderDAO();
		int orderid=ob.placeOrder(a,cus,b);
		return orderid;
	}
	public int updateInventoryOnOrder(int orderid) throws UserExistsException, SQLException
	{
		OrderDAO ob=null;
		int update=0;
		ob=new OrderDAO();
		update=ob.updateInventoryOnOrder(orderid);
		return update;
		
		
	}
	public int calReward(int retid) throws UserExistsException, SQLException
	{RewardsDao ob=null;
	int reward=0;
	ob=new RewardsDao();
	reward=ob.calReward(retid);
	return reward;
	}
	public String getStatus(int orderid) throws UserExistsException, SQLException
	{
		OrderDAO ob=null;
		String status=null;
		ob=new OrderDAO();
		status=ob.getStatus(orderid);
		return status;
	}
	public boolean checkOrder(int a,int b) throws UserExistsException, SQLException
	{
		boolean check;
		OrderDAO ob=null;
		ob=new OrderDAO();
		check=ob.checkOrder(a,b);
		
		return check;
	}
	public int UpdateCancelInventoryDao(int a) throws UserExistsException, SQLException
	{
		int s=0;
		OrderDAO ob=null;
		ob=new OrderDAO();
		s=ob.UpdateCancelInventoryDao(a);
		return s;
		}
	
	public String cancelorder(int a) throws UserExistsException, SQLException
	{String msg=null;
	OrderDAO ob=null;
	ob=new OrderDAO();
	msg=ob.cancelorder(a);
	return msg;
	}
	public  ArrayList<OrderDetailsBean> viewallorder(String a,String b) throws UserExistsException, SQLException
	{OrderDAO ob=null;
	ob=new OrderDAO();
		
		ArrayList<OrderDetailsBean> list=null;
		list=new ArrayList<OrderDetailsBean>();
	list=ob.viewallorder(a,b);
	return list;
		
	}
	public  ArrayList<OrderDetailsBean> viewprocessingorder(String a,String b) throws UserExistsException, SQLException
	{OrderDAO ob=null;
	ob=new OrderDAO();
		
		ArrayList<OrderDetailsBean> list=null;
		list=new ArrayList<OrderDetailsBean>();
	list=ob.viewprocessingorder(a,b);
	return list;
		
	}
	public  ArrayList<OrderDetailsBean> viewdispatchedorder(String a,String b) throws UserExistsException, SQLException
	{OrderDAO ob=null;
	ob=new OrderDAO();
		
		ArrayList<OrderDetailsBean> list=null;
		list=new ArrayList<OrderDetailsBean>();
	list=ob.viewdispatchedorder(a,b);
	return list;
		
	}
	public int setAcknowledgement(int or) throws UserExistsException, SQLException
	{
		int s=0;
		OrderDAO ob=null;
		ob=new OrderDAO();
		s=ob.setAcknowledgement(or);
		return s;
	}
public boolean update(OrderShipmentBean cust,int or)throws UserExistsException, SQLException
{   boolean up=false;
    
	OrderShipmentDao ob=null;
	ob=new OrderShipmentDao();
	up=ob.update(cust, or);
	
	return up;
	
	
}

public int getRetailerID(String s)throws UserExistsException, SQLException
{
	int ret=0;
	RewardsDao obj=new RewardsDao();
	ret=obj.getRetailerID(s); 
	return ret;
}
public  ArrayList<RewardsBean> getRewards(int retid)throws UserExistsException, SQLException
{
	ArrayList<RewardsBean>al=null;
	RewardsDao ob=null;
	ob=new RewardsDao();
	al=new ArrayList<RewardsBean>();
	al=ob.getRewards(retid);
	return al;
	
}
public int placeReorder(String prod[],int retid)throws UserExistsException, SQLException
{
	int oid=0;
	OrderDAO ob=null;
	ob=new OrderDAO();
	oid=ob.placeReorder(prod, retid);
	return oid;
}
public float getPrice(int a)throws UserExistsException, SQLException
{
	OrderDAO ob=null;
	ob=new OrderDAO();
	float price=0;
	price=ob.getPrice(a);
	return price;
}
public ArrayList<ProductBean> getTaggedProduct(int retid)throws UserExistsException, SQLException
{
	ProductDao objt=null;
	objt=new ProductDao();
	ArrayList <ProductBean> al=null;
	al=new ArrayList <ProductBean>();
	al=objt.getTaggedProduct(retid);
	return al;
	
}
public ArrayList<ProductBean> getAvailableProduct(int retid)throws UserExistsException, SQLException
{
	ProductDao objt=null;
	objt=new ProductDao();
	ArrayList <ProductBean> al=null;
	al=new ArrayList <ProductBean>();
	al=objt.getAvailableProduct(retid);
	return al;
}

}