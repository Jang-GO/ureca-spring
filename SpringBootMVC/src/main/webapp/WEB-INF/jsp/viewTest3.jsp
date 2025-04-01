<%@ page import="mycom.springbootmvc.dto.CarDto" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
    String seq = (String) request.getAttribute("seq");
    CarDto carDto = (CarDto) request.getAttribute("carDto");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>viewTest3</title>
</head>
<body>
<h1>viewTest3.jsp</h1>
<h4>seq : <%=seq%></h4>

<h1>CarDto</h1>
<h4>name : <%=carDto.getName()%></h4>
<h4>price : <%=carDto.getPrice()%></h4>
<h4>owner : <%=carDto.getOwner()%></h4>
</body>
</html>