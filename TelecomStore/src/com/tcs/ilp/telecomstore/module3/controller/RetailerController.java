/*
 @author T139 Group C
 */


package com.tcs.ilp.telecomstore.module3.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tcs.ilp.telecomstore.module3.dao.OrderShipmentDao;
import com.tcs.ilp.telecomstore.module3.dao.OrderDAO;
import com.tcs.ilp.telecomstore.module3.dao.ProductDao;
import com.tcs.ilp.telecomstore.module3.dao.RetailerDAO;
import com.tcs.ilp.telecomstore.module3.dao.RewardsDao;
import com.tcs.ilp.telecomstore.module3.dto.OrderDetailsBean;
import com.tcs.ilp.telecomstore.module3.dto.OrderProductBean;
import com.tcs.ilp.telecomstore.module3.dto.OrderShipmentBean;
import com.tcs.ilp.telecomstore.module3.dto.ProductBean;
import com.tcs.ilp.telecomstore.module3.dto.RewardsBean;
import com.tcs.ilp.telecomstore.module3.exception.UserExistsException;

import com.tcs.ilp.telecomstore.module3.log4j.Log4j;
import com.tcs.ilp.telecomstore.module3.model.RetailerSystem;


/**
 * Servlet implementation class OrderController
 */
public class RetailerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ArrayList<OrderProductBean> Session = null;
	Logger _appLogger=Log4j.get_appLogger();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetailerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ArrayList<OrderBean>orderlist=(ArrayList<OrderBean>)request.getSession().getAttribute("cart");
		try{
			String user=(String)request.getSession().getAttribute("uid");
		
		String key=request.getParameter("key");
		 RetailerSystem rts=null;
		 rts=new RetailerSystem ();
		
		
		int retailer_id=rts.getRetailerID(user);
		ArrayList<ProductBean> al=new ArrayList<ProductBean>();
		if("tag".equals(key))
		{
			al=rts.getTaggedProduct(retailer_id); //to get tagged products
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/ViewTaggedProduct.jsp");
			request.getSession().setAttribute("productinfo", al);
			rd.forward(request, response);
		}
		if("avail".equals(key))
		{
			al=rts.getAvailableProduct(retailer_id);//to get available products
			request.getSession().setAttribute("productinfo", al);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/AvailableProduct.jsp");
			
			rd.forward(request, response);
		}
	 if("order".equals(key))
		{
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Order.jsp");//forwards to order placing page
			rd.forward(request, response);
		}
	 
	 if("checkstatus".equals(key))
		{
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/CheckStatus.jsp");
		rd.forward(request, response);
			
		}
	 if("updateorder".equals(key))
		{
		 RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/UpdateOrder.jsp");
			rd.forward(request, response);
			
		}
	 if("cancelorder".equals(key))
		{
		 RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/CancelOrder.jsp");
			rd.forward(request, response);
			
		}
	 if("defective".equals(key))
		{
		 RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/DefectiveOrder.jsp");
			rd.forward(request, response);
		
		}
	 if("acknowledgement".equals(key))
		{
		 RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Acknowledge.jsp");
			rd.forward(request, response);
		
		}
	 if("orderdetails".equals(key))
		{
		 RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/ViewAllOrder.jsp");
			rd.forward(request, response);
			
		}
	 if("reward".equals(key))
		{
		 	int retailer=rts.getRetailerID(user);
			ArrayList<RewardsBean>alist=new ArrayList<RewardsBean>();
			RetailerSystem ob1=null;
			ob1=new RetailerSystem();
			alist=ob1.getRewards(retailer);	//retrieves reward details of the retailer
			request.getSession().setAttribute("rewardinfo", alist);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Rewards.jsp");
			
			rd.forward(request, response);
		}
		}catch(UserExistsException e){
			// request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
			_appLogger.error("Exception" +e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
			_appLogger.error("Exception" +e.getMessage());
		}
		}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
		String keyvalue=request.getParameter("check");
		String index=request.getParameter("index");
		RetailerSystem ops=null;
		ops=new RetailerSystem();
	
		if ("first".equals(index)||"next".equals(index))
		{
			ArrayList<OrderProductBean> ord=null;
			ord=new ArrayList<OrderProductBean>();
			String prodname=request.getParameter("s1");
			String quantity=request.getParameter("q1");
			int quan=Integer.parseInt(quantity);
			OrderProductBean obj=new OrderProductBean(prodname,quan);
			ArrayList<OrderProductBean> an=null;
			an=(ArrayList<OrderProductBean>)request.getSession().getAttribute("avail");//ArrayList of available products is retrieved from session
		
			int i=0;
			for(OrderProductBean pr:an)//Checks whether quantity of ordered product is within available quantity
			{
				if(pr.getProductname().equals(prodname)&&(pr.getQuantity()<quan) && "first".equals(index))
				{
					i=1;
					request.getRequestDispatcher("/pages/ErrorOrderFirst.jsp").forward(request, response);			
				}
				if(pr.getProductname().equals(prodname)&&(pr.getQuantity()<quan) && "next".equals(index))
				{
					i=1;
			
					request.getRequestDispatcher("/pages/ErrorOrderNext.jsp").forward(request, response);	
				}
			
			
			}
		if(i==0)
		{
		
		if("first".equals(index))
		{
			
			
			
			ord.add(obj);
			request.getSession().setAttribute("cart", ord); //ArrayList of ordered products is store in session
			if("submit".equals(keyvalue))
		{
				request.getRequestDispatcher("/pages/Customer.jsp").forward(request, response);	
			
		}
		 if("BuyMoreProducts".equals(keyvalue))
		{
			
			 request.getRequestDispatcher("/pages/OrderMore.jsp").forward(request, response);	
		}
			
		}
		
			
		
			if ("next".equals(index))
		{
		try{
		ord=(ArrayList<OrderProductBean>)request.getSession().getAttribute("cart"); //ArrayList of ordered products is retrieved from session
		
		ord.add(obj);
		
		}
		catch(ClassCastException e)
		{
			//System.out.println("Exception" +e.getMessage());
			_appLogger.error("Exception" +e.getMessage());
		}
		request.getSession().setAttribute("cart", ord);
		if("submit".equals(keyvalue))
		{
			request.getRequestDispatcher("/pages/Customer.jsp").forward(request, response);	
		}
		 if("BuyMoreProducts".equals(keyvalue))
		{
			
			 request.getRequestDispatcher("/pages/OrderMore.jsp").forward(request, response);	
		}
		}
		}
}
	
if("customer".equals(index))
{
	String name=request.getParameter("customername");
	String adrs=request.getParameter("shipmentaddress");
	String cno=request.getParameter("contactno");
	String email=request.getParameter("email");
	long cntct=Long.parseLong(cno);
	
	
	OrderShipmentBean cus=null;
	cus=new OrderShipmentBean(name,adrs,cntct,email);
	try
	{
	ArrayList<OrderProductBean>orderlist=(ArrayList<OrderProductBean>)request.getSession().getAttribute("cart");
	
	int ret=(Integer)request.getSession().getAttribute("retailer");
	
	
	int orderid=ops.placeOrder(orderlist,cus,ret);//Order is placed
	
	int update=ops.updateInventoryOnOrder(orderid);//Inventory is updated after placing the order
	
	
	
	int reward=0;
	if (update>0)
	{
		reward=ops.calReward(ret); //Reward id is generated
		
	}
	

	if((orderid>0) && (update>0) && (reward==0))
	{
	request.setAttribute("oid",orderid);
	RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Successfulorder.jsp");
	rd.forward(request, response);
	
	}
	else if((0==orderid)  && (0==update))
{
	 RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Unsuccessfulorder.jsp");
		rd.forward(request, response);
		
}
	else
 
 {
		
		float price=ops.getPrice(orderid);
		request.setAttribute("prc",price);
		request.setAttribute("oid",orderid);
 request.setAttribute("rewardid",reward);
 RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/SuccessfulReward.jsp");
	rd.forward(request, response);
 }
	
}

catch(ClassCastException e)
{
	//System.out.println("Exception" +e.getMessage());
	_appLogger.error("Exception" +e.getMessage());
}
}
if("CancelOrder".equals(keyvalue)&& ("cancel".equals(index)))
		{
	
	String orid=request.getParameter("orderid");
	request.setAttribute("or", orid);
	
	int or=Integer.parseInt(orid);
	int ret=(Integer)request.getSession().getAttribute("retailer");
	String status=ops.getStatus(or);//checks status of order
	
	boolean match=ops.checkOrder(ret,or); //matches the retailerid and order id
	if(!match)
	{
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Mismatch.jsp");
		rd.forward(request, response);
	}
	else if(!"Allocated".equalsIgnoreCase(status))
	{   
		request.setAttribute("status",status);
	    RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/UnsuccessfulCancel.jsp");
	    rd.forward(request, response);
	
		
	}
	else
	{
		
		
		int s=new OrderDAO().UpdateCancelInventoryDao(or);//updates inventory after cancelling the order
		String msg=ops.cancelorder(or);
		
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/SuccessCancel.jsp");
		rd.forward(request, response);
	}
}
   if(("ViewOrders".equals(keyvalue))&& ("view".equals(index)))

   {
	
	  String s0=request.getParameter("D1");
		String s1=request.getParameter("M1");
       String s2=request.getParameter("Y1");
       if("01".equals(s1))
       {
       	s1="jan";
       }else if("02".equals(s1))
       {
       	s1="feb";
       }
       else if("03".equals(s1))
       {
       	s1="mar";
       }
       else if("04".equals(s1))
       {
       	s1="apr";
       }
       
       else if("05".equals(s1))
       {
       	s1="may";
       }
       else if("06".equals(s1))
       {
       	s1="jun";
       }
       else if("07".equals(s1))
       {
       	s1="jul";
       }
       else if("08".equals(s1))
       {
       	s1="aug";
       }
       else if("09".equals(s1))
       {
       	s1="sep";
       }
       else if("10".equals(s1))
       {
       	s1="oct";
       }
       else if("11".equals(s1))
       {
       	s1="nov";
       }
       else if("12".equals(s1))
       {
       	s1="dec";
       }
       String start=s2+"-"+s1+"-"+s0;
       
       String e0=request.getParameter("D2");
       String e1=request.getParameter("M2");
       String e2=request.getParameter("Y2");
       
       if("01".equals(e1))
       {
       	e1="jan";
       }else if("02".equals(e1))
       {
       	e1="feb";
       }
       else if("03".equals(e1))
       {
       	e1="mar";
       }
       else if("04".equals(e1))
       {
       	e1="apr";
       }
       
       else if("05".equals(e1))
       {
       	e1="may";
       }
       else if("06".equals(e1))
       {
       	e1="jun";
       }
       else if("07".equals(e1))
       {
       	e1="jul";
       }
       else if("08".equals(e1))
       {
       	e1="aug";
       }
       else if("09".equals(e1))
       {
       	e1="sep";
       }
       else if("10".equals(e1))
       {
       	e1="oct";
       }
       else if("11".equals(e1))
       {
       	e1="nov";
       }
       else if("12".equals(e1))
       {
       	e1="dec";
       }
       
       String end=e2+"-"+e1+"-"+e0;
       
       
       ArrayList<OrderDetailsBean> list=new ArrayList<OrderDetailsBean>();
	    list=ops.viewallorder(start, end);//retrives order details between the starting and ending dates
	    request.setAttribute("finallist",list);
	    RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/ViewOrderResult.jsp");
	   
	    rd.forward(request, response);
	    
	}
   
   //View orders based on processing
   if(("ViewProcessingOrders".equals(keyvalue))&& ("view".equals(index)))

   {
	
	  String s0=request.getParameter("D1");
		String s1=request.getParameter("M1");
       String s2=request.getParameter("Y1");
       if("01".equals(s1))
       {
       	s1="jan";
       }else if("02".equals(s1))
       {
       	s1="feb";
       }
       else if("03".equals(s1))
       {
       	s1="mar";
       }
       else if("04".equals(s1))
       {
       	s1="apr";
       }
       
       else if("05".equals(s1))
       {
       	s1="may";
       }
       else if("06".equals(s1))
       {
       	s1="jun";
       }
       else if("07".equals(s1))
       {
       	s1="jul";
       }
       else if("08".equals(s1))
       {
       	s1="aug";
       }
       else if("09".equals(s1))
       {
       	s1="sep";
       }
       else if("10".equals(s1))
       {
       	s1="oct";
       }
       else if("11".equals(s1))
       {
       	s1="nov";
       }
       else if("12".equals(s1))
       {
       	s1="dec";
       }
       String start=s2+"-"+s1+"-"+s0;
       
       String e0=request.getParameter("D2");
       String e1=request.getParameter("M2");
       String e2=request.getParameter("Y2");
       
       if("01".equals(e1))
       {
       	e1="jan";
       }else if("02".equals(e1))
       {
       	e1="feb";
       }
       else if("03".equals(e1))
       {
       	e1="mar";
       }
       else if("04".equals(e1))
       {
       	e1="apr";
       }
       
       else if("05".equals(e1))
       {
       	e1="may";
       }
       else if("06".equals(e1))
       {
       	e1="jun";
       }
       else if("07".equals(e1))
       {
       	e1="jul";
       }
       else if("08".equals(e1))
       {
       	e1="aug";
       }
       else if("09".equals(e1))
       {
       	e1="sep";
       }
       else if("10".equals(e1))
       {
       	e1="oct";
       }
       else if("11".equals(e1))
       {
       	e1="nov";
       }
       else if("12".equals(e1))
       {
       	e1="dec";
       }
       
       String end=e2+"-"+e1+"-"+e0;
       
       
       ArrayList<OrderDetailsBean> list=new ArrayList<OrderDetailsBean>();
	    list=ops.viewprocessingorder(start, end);//retrives order details between the starting and ending dates
	    request.setAttribute("processinglist",list);
	    RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/ViewProcessingOrderResult.jsp");
	   
	    rd.forward(request, response);
	    
	}
   
   //view dispatched orders
   
   if(("ViewDispatchedOrders".equals(keyvalue))&& ("view".equals(index)))

   {
	
	  String s0=request.getParameter("D1");
		String s1=request.getParameter("M1");
       String s2=request.getParameter("Y1");
       if("01".equals(s1))
       {
       	s1="jan";
       }else if("02".equals(s1))
       {
       	s1="feb";
       }
       else if("03".equals(s1))
       {
       	s1="mar";
       }
       else if("04".equals(s1))
       {
       	s1="apr";
       }
       
       else if("05".equals(s1))
       {
       	s1="may";
       }
       else if("06".equals(s1))
       {
       	s1="jun";
       }
       else if("07".equals(s1))
       {
       	s1="jul";
       }
       else if("08".equals(s1))
       {
       	s1="aug";
       }
       else if("09".equals(s1))
       {
       	s1="sep";
       }
       else if("10".equals(s1))
       {
       	s1="oct";
       }
       else if("11".equals(s1))
       {
       	s1="nov";
       }
       else if("12".equals(s1))
       {
       	s1="dec";
       }
       String start=s2+"-"+s1+"-"+s0;
       
       String e0=request.getParameter("D2");
       String e1=request.getParameter("M2");
       String e2=request.getParameter("Y2");
       
       if("01".equals(e1))
       {
       	e1="jan";
       }else if("02".equals(e1))
       {
       	e1="feb";
       }
       else if("03".equals(e1))
       {
       	e1="mar";
       }
       else if("04".equals(e1))
       {
       	e1="apr";
       }
       
       else if("05".equals(e1))
       {
       	e1="may";
       }
       else if("06".equals(e1))
       {
       	e1="jun";
       }
       else if("07".equals(e1))
       {
       	e1="jul";
       }
       else if("08".equals(e1))
       {
       	e1="aug";
       }
       else if("09".equals(e1))
       {
       	e1="sep";
       }
       else if("10".equals(e1))
       {
       	e1="oct";
       }
       else if("11".equals(e1))
       {
       	e1="nov";
       }
       else if("12".equals(e1))
       {
       	e1="dec";
       }
       
       String end=e2+"-"+e1+"-"+e0;
       
       
       ArrayList<OrderDetailsBean> list=new ArrayList<OrderDetailsBean>();
	    list=ops.viewdispatchedorder(start, end);//retrives order details between the starting and ending dates
	    request.setAttribute("dispatchedlist",list);
	    RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/ViewDispatchedOrderResult.jsp");
	   
	    rd.forward(request, response);
	    
	}
   
   
   
   
   
   
   
   if(("Acknowledge".equals(keyvalue))&&("ack".equals(index)))
   {   
	   String orid=request.getParameter("orderid");
		
		int or=Integer.parseInt(orid);
		int ret=(Integer)request.getSession().getAttribute("retailer");
		
		
		String status=ops.getStatus(or); //checks status of the order
		boolean match=ops.checkOrder(ret,or); //checks whether the order has been made by that retailer or not
		request.setAttribute("or", orid);
		request.setAttribute("status", status);
		if(!match)
		{
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Mismatch.jsp");
			rd.forward(request, response);
		}
		else if("Received".equalsIgnoreCase(status)|| "with defects".equalsIgnoreCase(status) )
		{
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/InvalidAcknowledgement.jsp");
		    rd.forward(request, response);
			
		}
		else if("Allocated".equalsIgnoreCase(status))
		{
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Unsuccessfulack.jsp");
		    rd.forward(request, response);
			
		}
		else 
		{
			
			
	
			
			int a=ops.setAcknowledgement(or);  //acknowledges the order
			
			if(a==0)
			{
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/AcknowledgementFailure.jsp");
			rd.forward(request, response);
			}
			else
			{RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/AcknowledgementSuccess.jsp");
			rd.forward(request, response);
				
			}
		}
		
   }
   if(("Check Status".equals(keyvalue))&&("statuscheck".equals(index)))
   {
	   String s=request.getParameter("orderid");
	    int oid=Integer.parseInt(s);
	    
		String s1=ops.getStatus(oid); //checks status of the order
		int retid=(Integer)request.getSession().getAttribute("retailer");
		boolean result=ops.checkOrder(retid,oid);//matches the retailer id with order id
		request.setAttribute("or",s);
		if(!result)
		{RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Mismatch.jsp");
		rd.forward(request ,response);
		
			
		}
		else
		{
		
	
		request.setAttribute("status",s1);
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/StatusResult.jsp");
		rd.forward(request ,response);
		
	}

   }

   
	if("Submit".equals(keyvalue) && "updateform".equals(index))
	
	{
	
			
		String ordid=request.getParameter("orderid");
		int order_id=Integer.parseInt(ordid);
	int ret=(Integer)request.getSession().getAttribute("retailer");	
	boolean val=ops.checkOrder(ret,order_id);
	
	
	String status=ops.getStatus(order_id);
	if (!val)
	{
		request.setAttribute("or",ordid);
		RequestDispatcher rd1=getServletContext().getRequestDispatcher("/pages/Mismatch.jsp");
		rd1.forward(request, response);
	}
	 
	else if(!"Allocated".equalsIgnoreCase(status))
	{
		request.setAttribute("or",ordid);
		request.setAttribute("status",status);
		RequestDispatcher rd2=getServletContext().getRequestDispatcher("/pages/StatusMismatch.jsp");
		rd2.forward(request, response);	
	}
	else
	{
	request.setAttribute("or",ordid);
	
	RequestDispatcher rd3=getServletContext().getRequestDispatcher("/pages/ViewCustomer.jsp");
	
	rd3.forward(request, response);
	}
	
   }
	
	if("Update".equals(keyvalue) && "newdetails".equals(index))
	{
		String ord=request.getParameter("hello");
		int ord_id=Integer.parseInt(ord);
		
		String name=request.getParameter("customername");
		String adrs=request.getParameter("shipmentaddress");
		String cno=request.getParameter("contactno");
		long contact=Long.parseLong(cno);
		String email=request.getParameter("email");
		OrderShipmentBean cust=new OrderShipmentBean(name,adrs,contact,email);
	
	
	boolean msg=ops.update(cust,ord_id);//updates order shipment details
		
	if(msg)
	{
		
	request.getRequestDispatcher("/pages/SuccessfulUpdate.jsp").forward(request, response);
	}
	if(!msg)
	{
		
		request.getRequestDispatcher("/pages/UnsuccessfulUpdate.jsp").forward(request, response);
	}
	}
	
	
	if("Submit".equals(keyvalue) && "defectid".equals(index))
	
	{
		String ordr=request.getParameter("orderid");
		int oid=Integer.parseInt(ordr);
		String status=ops.getStatus(oid);
		String statuscheck=new OrderDAO().getStatusForDefective(oid);
		      
		request.setAttribute("or",ordr);
	
			int ret=(Integer)request.getSession().getAttribute("retailer");
		
		boolean val=ops.checkOrder(ret,oid);
		if (!val)
		{       
			
			
            RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/Mismatch.jsp");
			rd.forward(request, response);
		}
		else if("Dispatched".equalsIgnoreCase(status))
		{
			request.setAttribute("status",status);

			RequestDispatcher re=getServletContext().getRequestDispatcher("/pages/ReorderDispatch.jsp");
			
			re.forward(request, response);
			
			
		}
		 
		else if("notelligible".equals(statuscheck))
		{
			request.setAttribute("status",status);

			RequestDispatcher re=getServletContext().getRequestDispatcher("/pages/Reorderstatus.jsp");
			
			re.forward(request, response);
			
			
		}
		 
	
		else
		{
		
		RequestDispatcher re=getServletContext().getRequestDispatcher("/pages/ViewDefective.jsp");
		
		re.forward(request, response);
		
		
	}
		
	}
	if("Request for reorder".equals(keyvalue) && "defectcode".equals(index))
	{
		String defprod[]=request.getParameterValues("code");
		int ret=(Integer)request.getSession().getAttribute("retailer");
		
	
	int neworderid=	ops.placeReorder(defprod,ret);//requesting for defective products
	int update=ops.updateInventoryOnOrder(neworderid);//updates the inventory after placing reorder
	
	if((neworderid>1) && (update>0))
	{     
		request.setAttribute("ord",neworderid);
	    RequestDispatcher rd=getServletContext().getRequestDispatcher("/pages/ReorderSuccess.jsp");
	    rd.forward(request, response);
	}
	else
	{
		request.getRequestDispatcher("pages/ReorderFailure.jsp").forward(request,response);
		
	}

	}
	
	}catch(UserExistsException e)
	{_appLogger.error("Exception" +e.getMessage());
		//request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		//request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
		_appLogger.error("Exception" +e.getMessage());
	}	
		
	
}
}
		




