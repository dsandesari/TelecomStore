
/*
 @author T139 Group C 
 */

package com.tcs.ilp.telecomstore.module3.model;

import java.sql.SQLException;

import com.tcs.ilp.telecomstore.module3.dao.RetailerDAO;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;

public class LoginModel {
 public String validateRetailer(int a,String b)throws UserExistsException, SQLException
{
	RetailerDAO ob=null;
	ob=new RetailerDAO();
	String s=ob.validateRetailer(a, b);
	return s;
	

	
}
}
