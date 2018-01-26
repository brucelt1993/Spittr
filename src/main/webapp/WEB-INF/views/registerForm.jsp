<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bruce
  Date: 2018/1/26
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />" >
</head>
<body>
    <h1>Register</h1>
    <form method="POST">
        First Name: <input type="text" name="firstName"/><br/>
        Last Name: <input type="text" name="lastName"/><br/>
        Email: <input type="email" name="email" /><br/>
        UserName: <input type="text" name="username"/><br/>
        Password: <input type="password" name="password"/><br/>
        <input type="submit" value="Register"/>
    </form>
</body>
</html>
