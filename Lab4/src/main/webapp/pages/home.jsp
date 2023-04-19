<%@ page import="com.Lab4.profile.ProfileTools" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: gray;
        }
        h1 {
            font-size: 28px;
            font-weight: bold;
            margin-top: 50px;

        }
        button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 10px;
            cursor: pointer;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }
        button:hover{
            background-color: #3e8e41;
        }
        a {
            color: white;
            text-decoration: none;
        }

        input[type="submit"] {
            background-color: red;
            border: none;
            color: white;
            padding: 12px 24px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }
        input[type="submit"]:hover{
            background-color: darkred;
        }
        *{
            color:white;
        }
    </style>
</head>
<body>
<h1>Hello, <%= session.getAttribute(ProfileTools.SESSION_LOGGEDIN_ATTRIBUTE_NAME) %></h1>

<c:if test="${whoIs eq 'ADMINISTRATOR'}" >
    You are logged in as administrator<br/>
    <button onclick="window.location.href='AllUsers'">All Users</button><br/><br/>
    <button onclick="window.location.href='indv'">Individual task</button><br/><br/>
</c:if>
<c:if test="${whoIs eq 'USER'}" >
    You are logged in as User<br/>
    <button onclick="window.location.href='indv'">Individual task</button><br/><br/>
</c:if>
<c:if test="${whoIs eq 'GUEST'}" >
    You are logged in as guest<br/>
</c:if>

<form action="logout" method="post">
    <input type="hidden" name="authAction" value="logout" >
    <input type="submit" value="Logout" />
</form>
</body>
</html>
