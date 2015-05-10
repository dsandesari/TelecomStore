<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <% String ord=(String)request.getAttribute("or"); %> 
<%@ page language="java" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="com.tcs.ilp.telecomstore.module3.dao.RetailerDAO" %>
    <% String str=(String)session.getAttribute("uid"); 
    if(str==null)
    	response.sendRedirect("Login.jsp");
    RetailerDAO ob1=new RetailerDAO();
    int retailer_id=ob1.getRetailerID(str);
    session.setAttribute("retailer",retailer_id);
    String retname=ob1.getRetailerName(retailer_id);
//session.setAttribute("uid",str);

%>

<html>
<head>
<script type="text/javascript" language="javascript">
window.history.forward(1);

</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GLOBAL COMMUNICATION</title>

<link href="<%=request.getContextPath() %>/pages/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/pages/contentslider.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/pages/contentslider.js">

</script>
</head>
<body>
	<div id="templatemo_header_panel">
    	<div id="templatemo_header_section">
            <div id="templatemo_title_section">
                <h1 style="font-size:45;bold;">GLOBAL TELECOM STORE </h1><br>
                <center><h3 style="font-family: 'comic sans ms', fantasy;font-style: italic;font-weight: 900;margin-left: 4cm">The one stop mobile solution</h3></center>
             <!--   <p><br /> 
                TELECOM
			    </p>    -->
            </div>
        </div>
    </div> <!-- end of templatemo header panel -->
    
    <div id="templatemo_menu_panel">
    	<div id="templatemo_menu_section">
            <ul>
                <li><a href="<%=request.getContextPath()%>/pages/Menu.jsp" style="border-right: 1px solid #660e4c;">Home</a></li>  
                <li><a href="<%=request.getContextPath()%>/pages/Aboutus.jsp" target="_blank" style="border-right: 1px solid #660e4c;">About </a></li> 
                <li><a href="<%=request.getContextPath()%>/pages/Contact us.jsp" target="_blank" style="border-right: 1px solid #660e4c;">Contact Us</a></li>
                <li><a></a></li>
                <li><a style="padding: 5px 100px;border-right: 1px solid #660e4c;">Welcome&nbsp<i><% out.println(retname); %></i></a></li>
                <li><a href="<%=request.getContextPath()%>/LoginController">Sign Out</a></li>
            </ul>
         </div>
         
           
    </div> <!-- end of menu -->
    
	<div id="templatemo_content_container">
        <div id="templatemo_content">
            <div id="templatemo_content_left">
				<h2>Latest Launches</h2>
                <div class="templatemo_post" style="font-color:white;">
                	<br>
<br>
</br><center>
<h3 align="center">The Acknowledgement for the Order with order id <%=ord%> is failed</h3>
<br><br>
</br>
<a href="<%=request.getContextPath() %>/pages/Menu.jsp">Back to Menu</a></center></body>
                </div>
                </div> <!-- end of one post -->
                
                

                
           
            <div id="templatemo_content_right">
          
               <div class="templatemo_right_section">
                	<h2>Categories</h2>
					<ul>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=order">Place Order</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=checkstatus">Check status of order</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=updateorder">Update order shipment details</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=cancelorder">Cancel order</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=avail">View available products</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=tag">View tagged products</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=orderdetails">View order details</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=reward">View rewards</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=defective">Replacement of defective products</a></li>
                        <li><a href="<%=request.getContextPath() %>/RetailerController?key=acknowledgement">Acknowledgment of received products</a></li>
                    </ul>    
                </div>
              
                
               
                
            </div> <!-- end of right content -->
	    </div> <!-- end of content -->
    </div> <!-- end of content container -->

	
    <div id="templatemo_bottom_panel">
    	<marquee direction="right" onmouseover="this.stop()" onmouseout="this.start()" scrollamount="10">
<img src="<%=request.getContextPath() %>/images/1.jpg" height="150" width="90"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abc.jpg"  height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt1.jpg" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt2.jpg" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt6.JPG" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt4.jpg" height="150" width="150"></img>&nbsp&nbsp
</marquee>
<center><h6>Copyright @ Group-C T139 Batch. All rights reserved.</h6></center>
</div>
        
        <!-- end of templatemo bottom section -->
        
    
</body>
</html>