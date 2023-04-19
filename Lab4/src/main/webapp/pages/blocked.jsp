<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login failed</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: gray;
            color: #333;
            margin: 0;
            padding: 0;
        }
        h1 {
            margin: 0;
            padding: 20px;
            background-color: #dc3545;
            color: #fff;
        }
        p {
            margin: 20px;
        }
        *{
            color:white;
        }
    </style>
</head>
<body>
<h1>Login failed</h1>
<p>You are blocked since you tried to type incorrect login or password 3 times.</p>
<p>Blocked date: ${blockedDate}</p>
<p>Blocked time: ${blockedTime}</p>
</body>
</html>
