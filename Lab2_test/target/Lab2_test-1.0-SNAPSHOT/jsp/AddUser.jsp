<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br/>

    <label for="password">Password:</label>
    <input type="text" id="password" name="password" required><br/>

    <label for="email">Email:</label>
    <input type="text" id="email" name="email" required><br/>

    <label for="birthdate">Birhtdate:</label>
    <input type="text" id="birthdate" name="birthdate" required><br/>

    <button type="submit">Add</button>
</form>
<br/>
<button onclick="history.go(-1)">Назад</button>
</body>
</html>
