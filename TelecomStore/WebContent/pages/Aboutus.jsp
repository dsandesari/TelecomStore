<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% ResourceBundle bundle=(ResourceBundle)session.getAttribute("bundle");%>
<link href="<%=request.getContextPath() %>/pages/templatemo_style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body margin=10px>
<h3><%=bundle.getString("global.aboutus")%></h3>
<p><%=bundle.getString("global.aboutusdes1")%></p>
<p>The Global Communications caters to the Indian consumer's choice of the widest and most comprehensive range of mobile phones with special offers from all the key brands available across the globe.</p> 
<p>The Global Communication offers complete telecom solutions right from handset purchase to the choice of service operator and miscellaneous services like monthly bill collections etc., 
the stores also offer connections (pre paid and post paid), accessories and VAS including the latest ring tones, wallpapers and gaming and prompt after sales service, available not only in the city of purchase but in all across globe.</p>
<p>All major handset brands like Nokia, Sony Ericsson, LG, Samsung, Motorola, Fly, Sagem, HP, iMate, Dopod, HTC and Blackberry are available at the store. The Global Communication has also tied up with all leading operators including Airtel, Vodafone, BPL, Idea, MTNL/BSNL and Reliance, Tata Indicom. 
</p>
<div id="templatemo_bottom_panel"><br><br>
    	<marquee direction="right" onmouseover="this.stop()" onmouseout="this.start()" scrollamount="5">
<img src="<%=request.getContextPath() %>/images/1.jpg" height="150" width="90"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abc.jpg"  height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt1.jpg" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt2.jpg" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt6.JPG" height="150" width="150"></img>&nbsp&nbsp
<img src="<%=request.getContextPath() %>/images/abt4.jpg" height="150" width="150"></img>&nbsp&nbsp
</marquee><br><br><br><br><br>
<center><h6>Copyright @ Group-C T139 Batch. All rights reserved.</h6></center>
</div>

</body>
</html>