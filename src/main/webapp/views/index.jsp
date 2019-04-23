<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="jmCrud.model.User" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список пользователей</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<table class="table">
    <thead class="thead-light">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Имя пользователя</th>
        <th scope="col">Логин</th>
        <th scope="col">Редактировать</th>
        <th scope="col">Удалить</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("Users");
        for (User user : users) {
    %>
    <tr>
        <th><%= user.getId() %>
        </th>
        <th><%= user.getUsername() %>
        </th>
        <th><%= user.getLogin() %>
        </th>
        <th><a href="edit?id=<%= user.getId() %>">Редакт.</a></th>
        <th><a href="?delete=<%= user.getId() %>">Удалить</a></th>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<a class="btn btn-primary" href="add">Добавить</a>

</body>
</html>