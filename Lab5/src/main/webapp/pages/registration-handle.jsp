<%@ page import="com.Lab5.profile.AccountType" %>
<%@ page import="com.Lab5.profile.ProfileTools" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML	4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Verifying Registration</title></head>
<body>
<jsp:useBean id="registeredUser" class="com.Lab5.models.User" scope="session"></jsp:useBean>
<jsp:setProperty property="*" name="registeredUser"/>
<c:set var="userName" value="${registeredUser.name}"/>
<c:set var="userEmail" value="${registeredUser.email}"/>
<c:set var="userPsw" value="${registeredUser.password}"/>
<c:choose>
    <c:when test="${empty userName || empty userEmail || empty userPsw}">
        <h1>Not valid Name, Email or Password, please <a href="register">try one more time</a></h1>
    </c:when>
    <c:otherwise>
        <%
            DataSource ds = ProfileTools.getDataSource(
                    request.getServletContext()
                            .getRealPath(ProfileTools.DATASOURCE_PROPERTIES_FILE));

            ProfileTools.getUsersRepository(ds).save(registeredUser);
            ProfileTools.doLogin(registeredUser, session, ds);

            request.getServletContext()
                    .getRequestDispatcher("/pages/home.jsp")
                    .forward(request, response);
        %>

    </c:otherwise>
</c:choose>
</body></html>