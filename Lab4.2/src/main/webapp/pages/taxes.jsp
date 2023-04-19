<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><c:out value="${Tax.calculateTaxes()}"/></h3>
<h3><c:out value="${Tax.toString()}"/></h3>
<h3><c:out value="${Tax.sortIncomesByTaxes()}"/></h3>
<h3><c:out value="${Tax.toString()}"/></h3>
</body>
</html>
