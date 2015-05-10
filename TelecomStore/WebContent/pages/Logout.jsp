<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% session.invalidate(); %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
<script type="text/javascript" language="javascript">
window.history.forward(1);

</script>



</head>
<body>

<jsp:forward page="/pages/Login.jsp">
<jsp:param value="" name=""/>
</jsp:forward>
</body>
</html>