<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Вариант 18</title>
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
<button><a href="home">Home</a></button>
</body>
</html>
