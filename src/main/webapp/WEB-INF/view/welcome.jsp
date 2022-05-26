<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP</title>
</head>
<body>
IdVe: ${maVeInput }<br/>
dateInput: ${dateInput }<br/>
tuyenDuongInput: ${tuyenDuongInput }<br/>
trangThaiInput: ${trangThaiInput }<br/>
${listVeXe }
<a href="${pageContext.request.contextPath }">Back to Home</a>


</body>
</html>