<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isErrorPage="true" %>
<%@page import="java.io.StringWriter,java.io.PrintWriter" %>
<html>
<body>
<h1>
<center>An Error Has Occured</center>
</h1>
<hr>
<h2>
Received the exception:<br>
<font size='3' color=red>
<%= exception.toString() %>

</font>
</h2>

<h3>
<font size='3' color=red>

<%
out.println("<p>");
StringWriter sw = new StringWriter();
PrintWriter pw = new PrintWriter(sw);
exception.printStackTrace(pw);
out.print(sw);
sw.close();
pw.close();
out.println("</p>");
%> 
</font>
</h3>
</body>
</html>
