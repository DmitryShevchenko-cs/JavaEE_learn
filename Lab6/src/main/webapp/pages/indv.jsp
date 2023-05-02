<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вариант 18</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: gray;
        }
        form {
            margin-top: 20px;
            text-align: center;
            background-color: #d8dcd6;
            padding: 10px;
        }
        label {
            font-size: 18px;
            font-weight: bold;
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"] {
            width: 400px;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            margin-bottom: 20px;
        }
        button[type="submit"] {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            font-size: 18px;
            border-radius: 5px;
            cursor: pointer;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }
        h3 {
            text-align: center;
            margin-top: 30px;
            font-size: 24px;
        }
        p {
            font-size: 18px;
        }
        button[type="submit"]:hover {
            background-color: #3e8e41;
        }
        div{
            text-align: center;
        }
        .btn-back {
            background-color: red;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            font-size: 18px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }
        .btn-back:hover{
            background-color: darkred;
        }

    </style>
</head>
<body>
<form>
    <label for="text">Text:</label>
    <input type="text" id="text" name="text"><br/>
    <button type="submit">Change</button>
</form>
<h3><p><c:out value="${text}"/></p></h3>
<h3><p><c:out value="${newtext}"/></p></h3>
<br/>
<div>
    <button class="btn-back" onclick="window.location.href='/Lab6_war'">Назад</button>
</div>
</body>
</html>


