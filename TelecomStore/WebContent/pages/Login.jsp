<html>
<head>

    <title style="background: #382738;">hello</title>
<%@ page language="java" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.util.*,com.tcs.ilp.telecomstore.module3.dao.RetailerDAO" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>global</title>

<link href="<%=request.getContextPath() %>/pages/templatemo_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"> </script>
<script type="text/javascript" language="javascript">
window.history.forward(1);

</script>

<body onload="Login_firstFocus()">


<%! String language;%>
<%!String country;%>
<%!Locale locale;%>
 <%!ResourceBundle bundle;%>
<% if("french".equals(request.getParameter("value")))
{
	locale = new Locale("fr","FR");
    bundle =ResourceBundle.getBundle("FrenchLang",locale);
   session.setAttribute("bundle",bundle);
}
else 
{
	locale = new Locale("en","UK");
    bundle =ResourceBundle.getBundle("English",locale);
   session.setAttribute("bundle",bundle);
}
%>
	<div id="templatemo_header_panel">
    	<div id="templatemo_header_section">
            <div id="templatemo_title_section">
                <h1 style="font-size:45;bold;"><%=bundle.getString("global.telecom")%> </h1><br>
                <center><h3 style="font-family: 'comic sans ms', fantasy;font-style: italic;font-weight: 900;margin-left: 4cm"><%=bundle.getString("global.stop")%></h3></center>
             <!--   <p><br /> 
                TELECOM
			    </p>    -->
            </div>
        </div>
    </div> <!-- end of templatemo header panel -->
    
    <div id="templatemo_menu_panel">
    	<div id="templatemo_menu_section">
            <ul>
                <li><a href="Login.jsp" style="border-right: 1px solid #660e4c;">	<%=bundle.getString("global.home")%></a></li> 
                <%=bundle.getString("global.chooseyourLang")%> <a href="Login.jsp?value=french" ><%=bundle.getString("global.french")%> </a> <a href="Login.jsp?value=eng" ><%=bundle.getString("global.english")%></a> 
                <li><a href="<%=request.getContextPath()%>/pages/Aboutus.jsp" target="_blank" style="border-right: 1px solid #660e4c;"><%=bundle.getString("global.about")%> </a></li> 
                <li><a href="<%=request.getContextPath()%>/pages/Contact us.jsp" target="_blank" style="border-right: 1px solid #660e4c;"><%=bundle.getString("global.contact")%></a></li>
                <li><a></a></li>
                
            </ul>
         </div>
         
           
    </div> <!-- end of menu -->
    
	<div id="templatemo_content_container">
        <div id="templatemo_content">
            <div id="templatemo_content_left">
				<h2><%=bundle.getString("global.latest")%></h2>
                <div class="templatemo_post" style="font-color:white;">
                
                
                	<img src="<%=request.getContextPath() %>/images/kshi.jpg" height="120" border="0" alt="" title="" align="left">
                	<%=bundle.getString("global.wait")%><br />
&nbsp;<br />
<%=bundle.getString("global.smart")%>.
</div>

<div class="templatemo_post" style="font-color:white;">
<tr><td><br>
<img src="<%=request.getContextPath() %>/images/kshitiz.jpg" height="120" border="0" alt="" title="" align="left">
							<%=bundle.getString("global.htc")%><br />
&nbsp;<br />
<%=bundle.getString("global.barcelona")%> 
</tr></td>







                	
                </div>
                </div> <!-- end of one post -->
                
                

                
           
            <div id="templatemo_content_right">
          
               <div class="templatemo_right_section">
                	<center><h2><%=bundle.getString("global.retailer")%></h2>
					<form name="f1" method="post" action="<%=request.getContextPath()%>/LoginController" target="_parent" onsubmit="return login_validateForm()">
 <table>
    <tr>
       
    </tr>
	<tr>	
		<td>
			<table>
				<tr>
					<td><font color=#dbc1a7 size="2"><%=bundle.getString("global.userid")%></font></td>
					<td><input type="text" name="uid" maxlength="5" style="background:white;"></td>
				</tr>
				<tr>
					<td><font color=#dbc1a7 size="2"><%=bundle.getString("global.password")%></font></td>
					<td><input type="password" name="pwd" maxlength="10" style="background:white;"></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
				<td><input type="submit" name="b1" value="Sign In" ></td>
				<td><input type="reset" name="b2" value="Reset"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>


</form></center> 
                </div>
              
                
               
                
            </div> <!-- end of right content -->
	    </div> <!-- end of content -->
    </div> <!-- end of content container -->

	
    <div id="templatemo_bottom_panel">
    	<marquee direction="right" onmouseover="this.stop()" onmouseout="this.start()" scrollamount="5">
<img src="<%=request.getContextPath() %>/images/1.jpg" height="150" width="90"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abc.jpg"  height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt1.jpg" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt2.jpg" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt6.JPG" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt4.jpg" height="150" width="150"></img>&nbsp&nbsp
</marquee>
<center><h6><%=bundle.getString("global.copyrite")%> .</h6></center>
</div>
        
        <!-- end of template bottom section -->
        
    
</body>
</html>