<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register new user</title></head>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: gray;
        }

        h1 {
            margin-top: 50px;
        }

        form {
            padding: 10px;
            background-color: #d8dcd6;
            margin-top: 50px;
            display: inline-block;
            text-align: left;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input[type=text],
        input[type=email],
        input[type=password],
        input[type=date] {
            display: block;
            width: 250px;
            padding: 5px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type=submit] {
            display: block;
            margin-top: 20px;
            padding: 10px;
            border-radius: 5px;
            border: none;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

    </style>
<body>
<h1>Register new user</h1>
<form action="doregister" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <p><label for="admin">Admin</label>
        <input type="radio" name="role" value="ADMINISTRATOR" id="admin" />
        <label for="user">User</label>
        <input type="radio" name="role" value="REGISTERED" id="user" checked /></p>
    <input type="submit" value="Register" />
</form>
</body></html>
