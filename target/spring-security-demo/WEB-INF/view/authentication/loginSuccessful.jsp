<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<sec:authorize access="hasRole('MANAGER')">
						 	<% response.sendRedirect(request.getContextPath()+"/quanly/?isShowList=true"); %>
		</sec:authorize>
	<sec:authorize access="hasRole('USER')">
						 	<% response.sendRedirect(request.getContextPath()); %>
		</sec:authorize>	
		<sec:authorize access="hasRole('EMPLOYEE')">
						 	<% response.sendRedirect(request.getContextPath()); %>
		</sec:authorize>

</body>
</html>