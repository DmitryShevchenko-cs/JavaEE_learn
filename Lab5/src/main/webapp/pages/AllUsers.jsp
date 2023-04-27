<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>View All Users</title></head>
  <style>
    h1 {
      color: #333;
      font-size: 24px;
      font-weight: bold;
    }
    a {
      color: #0066cc;
      text-decoration: none;
      font-weight: bold;
      margin-bottom: 20px;
    }

    button {
      background-color: #0066cc;
      color: #fff;
      border-radius: 5px;
      border: none;
      padding: 10px 20px;
      margin-bottom: 20px;
    }

    table {
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    table, th, td {
      border: 1px solid #999;
    }

    th, td {
      padding: 10px;
      text-align: center;
    }

    th {
      font-weight: bold;
    }

    h3 {
      color: #333;
      font-size: 18px;
      font-weight: bold;
      margin-bottom: 10px;
    }
  </style>
<body>
<h1>View All Users</h1>
<c:if test="${whoIs eq 'ADMINISTRATOR'}" >
  You are logged in as administrator<br/>
<h3>You are Admin</h3>
<p>Count of Users <c:out value="${accounts.size()}" />
<table width="100%" border="1">
  <tr><th>Name</th>
    <th>Email</th>
    <th>Role</th>
    <th>Last login date</th>
    <th>Incomes and taxes</th>
    <th>Total taxes</th>
  </tr>
  <c:forEach items="${accounts}" var="user">
  <tr><td>${user.name}</td>
    <td>${user.email}</td>
    <td>${user.role}</td>
    <td>${user.loginDate}</td>
    <td>${user.getTaxes().toString()}</td>
    <td>${user.getTaxes().calculateTaxes()}</td></tr>
  </c:forEach>
</c:if>
</body>
</html>
