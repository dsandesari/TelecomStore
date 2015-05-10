package com.tcs.ilp.telecomstore.module3.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.ilp.telecomstore.module3.connect.ConnectionFactory;
import com.tcs.ilp.telecomstore.module3.dto.RewardsBean;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;
import com.tcs.ilp.telecomstore.module3.log4j.Log4j;

/*
@author T139 Group C Pair 3
Employee id:553659 and 552119
Modified by:Group B, Aparna and Dhupendra
      Reason:For arranging list of rewards in ascending order of reward id.
      Date:07-mar,2012
*/
public class RewardsDao {
	private Object obj=new Object();
	Logger _appLogger=Log4j.get_appLogger();
	public int getRetailerID(String a) throws UserExistsException, SQLException    //For getting the retailer id

	{
		Connection cn=null;
		Statement st=null;
		int retid=0;
		ResultSet rs=null;
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
		return retid;                                   //returns the retailer id
	}
	public ArrayList<RewardsBean> getRewards(int a)throws UserExistsException, SQLException     //For matching a rewards to a particular retailer id
	{   System.out.println(a);
		Connection cn=null;
		Statement st=null;
		cn=ConnectionFactory.getConnection();
		ResultSet rs=null;
		ArrayList<RewardsBean>reward=new ArrayList<RewardsBean>();
		try
		{
			st=cn.createStatement();
			rs=st.executeQuery("SELECT R.REWARD_ID,C.OFFERS,R.REWARD_DATE,R.REWARD_STATUS FROM COMP_PACKAGE_TBL C INNER JOIN RETAILER_REWARDS_TBL R ON C.PACKAGE_ID= R.PACKAGE_ID WHERE R.RETAILER_ID='"+a+"' ORDER BY R.REWARD_ID");
			while(rs.next())
			{
		
				RewardsBean ob=new RewardsBean(rs.getInt(1),rs.getString(4),rs.getDate(3).toString(),rs.getString(2));
		
				reward.add((ob));
			}
		}
		
		finally
		{
			
				cn.close();
				st.close();
			
		
		}
		return reward;                   //returns the list of rewards
	}
	public int calReward(int retid) throws UserExistsException, SQLException    //For calculating rewards
	{
		Connection cn=null;
		Statement ps=null;
		PreparedStatement pt=null;
		float orderamount=0;
		int packageid=0;
		int rewardid=0;
		ResultSet rs=null;
		ResultSet res=null;
		ResultSet rr=null;
		ResultSet rt=null;
		float packagevalue=0;
		cn=ConnectionFactory.getConnection();
		synchronized(obj)
		{
		try
		{
			ps=cn.createStatement();
			rs=ps.executeQuery("SELECT SUM(ORDER_AMOUNT) FROM ORDER_TBL WHERE RETAILER_ID ="+retid+" AND MARKED IS NULL");
			while (rs.next())
			{
				orderamount=rs.getFloat(1);
				System.out.println("Total amt is "+orderamount);
			}
			rt=ps.executeQuery("SELECT MAX(MIN_ORDER_VALUE) FROM COMP_PACKAGE_TBL WHERE MIN_ORDER_VALUE<="+orderamount+"");//checking whether the retailer is eligible for a particular reward
			while(rt.next())
			{packagevalue=rt.getFloat(1);
			
				
			
			pt=cn.prepareStatement("SELECT PACKAGE_ID FROM COMP_PACKAGE_TBL WHERE MIN_ORDER_VALUE=?");//allocating package id
			pt.setFloat(1,packagevalue);
			rs=pt.executeQuery();
			while(rs.next())
			{ 
				packageid=rs.getInt(1);
			
				ps.executeUpdate("UPDATE ORDER_TBL SET Marked='M' where RETAILER_ID="+retid+""); //updating order table
			}
			rr=ps.executeQuery("SELECT MAX(REWARD_ID)FROM RETAILER_REWARDS_TBL");  //reward id is generated
			while(rr.next())
			{
				int t=rr.getInt(1);
				rewardid=t+1;
				
		
				pt=cn.prepareStatement("INSERT INTO RETAILER_REWARDS_TBL VALUES (?,?,?,sysdate,?)");
				pt.setInt(1,rewardid);
				pt.setInt(2,retid);
				pt.setInt(3,packageid);
				pt.setString(4,"Available");
				pt.executeUpdate();
		
			}
		}
			cn.commit();
		}
		
		finally
		{
			
				cn.close();
				pt.close();
			
			
		}
		return rewardid;
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

