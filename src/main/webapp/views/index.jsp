<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Вход в систему</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<h2><c:out value="${error}"/></h2>

<form action="<c:out value="${action}"/>" method="post">

    <div class="form-group">
        <label for="login">Имя пользователя:</label>
        <input type="text" class="form-control" id="login" name="login" value="">
    </div>
    <div class="form-group">
        <label for="password">Пароль:</label>
        <input type="password" class="form-control" id="password" name="password" value="">
    </div>

    <button type="submit" class="btn btn-primary">Продолжить</button>
</form>

</body>
</html>
