<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
    <title>LOGIN FORM</title>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GLOBAL COMMUNICATION</title>

<link href="<%=request.getContextPath() %>/pages/templatemo_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"> </script>
<script type="text/javascript" language="javascript">
window.history.forward(1);

</script>

<body onload="Login_firstFocus()">
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
                <li><a href="Login.jsp" style="border-right: 1px solid #660e4c;">Home</a></li>  
                <li><a href="<%=request.getContextPath()%>/pages/Aboutus.jsp" target="_blank"  style="border-right: 1px solid #660e4c;">About </a></li> 
                <li><a href="<%=request.getContextPath()%>/pages/Contact us.jsp" target="_blank" style="border-right: 1px solid #660e4c;">Contact Us</a></li>
                <li><a></a></li>
                
            </ul>
         </div>
         
           
    </div> <!-- end of menu -->
    
	<div id="templatemo_content_container">
        <div id="templatemo_content">
            <div id="templatemo_content_left">
				<h2>Latest Launches</h2>
                <div class="templatemo_post" style="font-color:white;">
                
                
                	<img src="<%=request.getContextPath() %>/images/kshi.jpg" height="120" border="0" alt="" title="" align="left">
                	The wait is over now as Google&rsquo;s second smartphone Nexus S is finally coming to India. The country&rsquo;s online retailer, Flipkart, has announced the availability of Google Nexus S for pre-orderorder.<br />
&nbsp;<br />
The smartphone has been priced at Rs. 27,999- including all taxes, for the Indian ...
</div>

<div class="templatemo_post" style="font-color:white;">
<tr><td><br>
<img src="<%=request.getContextPath() %>/images/kshitiz.jpg" height="120" border="0" alt="" title="" align="left">
							The recently launched HTC Incredible S may soon be available in our store, according to sources. Sources today leaked information regarding the possible red HTC Incredible S.<br />
&nbsp;<br />
The smartphone was originally launched at the Mobile World Congress 2011 in Barcelona. 
</tr></td>







                	
                </div>
                </div> <!-- end of one post -->
                
                

                
           
            <div id="templatemo_content_right">
          
               <div class="templatemo_right_section">
                	<center><h2>RETAILER LOGIN</h2>
					<form name="f1" method="post" action="<%=request.getContextPath()%>/LoginController" target="_parent" onsubmit="return login_validateForm()">
 <table>
    <tr>
       
    </tr>
	<tr>	
		<td>
			<table>
				<tr>
					<td><font color=#dbc1a7 size="2">User Id</font></td>
					<td><input type="text" name="uid" maxlength="5" style="background:white;"></td>
				</tr>
				<tr>
					<td><font color=#dbc1a7 size="2">Password</font></td>
					<td><input type="password" name="pwd" maxlength="10" style="background:white;"></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
				<td><input type="submit" name="b1" value="Sign In" ></td>
				<td><input type="reset" name="b2" value="Reset"></td>
				</tr>
			</table>
			<h5>The User Id or Password is incorrect. Verify that CAPS LOCK is not on, and then retype the correct User Id and Password.</h5>
		</td>
	</tr>
</table>


</form></center> 
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























































