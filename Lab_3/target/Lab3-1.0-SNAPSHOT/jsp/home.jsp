<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>
<h1>
    Hello, <%= session.getAttribute("user") %>
    <%= session.getAttribute("isAdmin") != null ? "You are logged in as administrator" : "" %><br>
</h1>
<button><a href="AddUser">Add User</a></button><br/><br/>
<button><a href="indv">Individual task</a></button><br/><br/>
<button><a href="cookies">Write Cookies</a></button><br/><br/>
<form action="logout" method="post">
    <input type="hidden" name="authAction" value="logout" >
    <input type="submit" value="Logout" />
</form>
</body>
</html>