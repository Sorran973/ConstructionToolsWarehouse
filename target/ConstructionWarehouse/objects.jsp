<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artembulkhak
  Date: 29/05/2020
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>List of Object</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 16px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 16px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
        input {
            font-family: Arial, sans-serif;
            font-size: 16px;
        }
        label {
            font-family: Arial, sans-serif;
            font-size: 16px;
        }
        p{
            font-family: Arial, sans-serif;
            font-size: 18px;
        }
    </style>
</head>
<body>
<br>
<h1>Список Объектов</h1>
<br>
<p><a href="main">Назад</a></p>
<p><a href='<c:url value="/createObject" />'>Добавить</a></p>
<br>

<table class="tg">
    <tr>
        <th width="30">ID</th>
        <th width="120">Адрес</th>
        <th width="70">Изменение</th>
        <th width="60">Удаление</th>
    </tr>
    <c:forEach items="${objectList}" var="object">
        <tr><td>${object.objId}</td>
            <td>${object.objAddress}</td>
            <td>
                <form action="editObject" method="GET">
                    <input type="hidden" name="objId" value="${object.objId}">
                    <input type="submit" value="Изменить">
                </form></td>
            <td><form action="deletObject" method="POST">
                    <input type="hidden" name="objId" value="${object.objId}">
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
