<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавление/редатирование пользователя</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<form action="<c:out value="${action}"/>" method="post">

    <input type="hidden" id="user_id" name="user_id" value="<c:out value="${user.getId()}"/>">

    <div class="form-group">
        <label for="username">Имя пользователя:</label>
        <input type="text" class="form-control" id="username" name="username" value="<c:out value="${user.getUsername()}"/>">
    </div>
    <div class="form-group">
        <label for="login">Логин:</label>
        <input type="text" class="form-control" id="login" name="login" value="<c:out value="${user.getLogin()}"/>">
    </div>
    <div class="form-group">
        <label for="password">Пароль:</label>
        <input type="password" class="form-control" id="password" name="password" value="<c:out value="${user.getPass()}"/>">
    </div>
    <div class="form-group">
        <label for="role">Роль:</label>
        <input type="text" class="form-control" id="role" name="role" value="<c:out value="${user.getRole().getRolename()}"/>">
    </div>

    <button type="submit" class="btn btn-primary">Готово</button>
</form>

</body>
</html>