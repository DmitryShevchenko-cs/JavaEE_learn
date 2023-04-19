<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name"><br/>

    <button type="submit">Search</button>
</form>
<br/>
<table>
    <tr><th>User Name</th>
        <th>Birth Date</th></tr>
    <c:forEach items="${usersFromServer}" var="users">
        <tr>
            <td>${users.get_name()}</td>
            <td>${users.get_email()}</td>
            <td>${users.get_birthDate()}</td>
            <td>${users.getRole()}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
