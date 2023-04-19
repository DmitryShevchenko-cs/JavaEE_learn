<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: gray;
        }

        form {
            margin: 50px auto;
            padding: 20px;
            background-color: #d8dcd6;
            border: 1px solid #cccccc;
            border-radius: 4px;
            width: 400px;
        }

        input[type="text"], input[type="password"] {
            display: block;
            width: 200px;
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 20px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #3e8e41;
        }
        div{
            text-align: center;
        }
        button {
            background-color: red;
            border: none;
            color: white;
            padding: 12px 24px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 8px 4px;
            cursor: pointer;
            border-radius: 8px;
            color: white;
            text-decoration: none;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }

        button:hover{
            background-color: darkred;
        }

    </style>
</head>
<body>
<form action="<c:url value = '/login'/>" method="post">
    <input type="hidden" name="authAction" value="login">
    <p>Please login by<br>
        <label for="email">Email</label>
        <input type="radio" name="authType" value="email" id="email" checked><br>
        <label for="userName">User name</label>
        <input type="radio" name="authType" value="userName" id="userName">
        <input type="text" name="loginValue">
        <br>
        <label for="psw">Password</label>
        <input type="password" name="psw" id="psw">
    </p>

    <p>The characters in a password field are masked (shown as asterisks or circles).</p>
    <input type="submit" value="Submit">
</form>
<div>
    <button onclick="window.location.href='/Lab4_war'">Welcome Page</button>
</div>
</body>
</html>
