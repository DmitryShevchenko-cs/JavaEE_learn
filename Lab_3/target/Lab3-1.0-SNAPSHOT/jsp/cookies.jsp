<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
  <tr><th>Name</th>
    <th>Value</th></tr>
  <c:forEach items="${EmailsFromCookies}" var="cookies">
    <tr>
      <td>${cookies.getName()}</td>
      <td>${cookies.getValue()}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
