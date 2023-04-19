
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Home</title>
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
        </tr>
    </c:forEach>
</table>
<br/>
<button><a href="AddUser">Add User</a></button>
<button><a href="indv">Individual task</a></button>
<button><a href="index.html">Index.html</a></button>
</body>
</html>
