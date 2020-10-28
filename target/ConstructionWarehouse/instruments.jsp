<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artembulkhak
  Date: 29/05/2020
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>List of Instrument</title>
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
<h1>Список Инструментов</h1>
<p><a href="main">Назад</a></p>
<p><a href='<c:url value="/createInstrument" />'>Добавить</a></p>
<br>

<table class="tg">
    <tr>
        <th width="40">ID</th>
        <th width="120">Тип</th>
        <th width="120">Название</th>
        <th width="80">Цена</th>
        <th width="80">Состояние</th>
        <th width="300">Описание</th>
        <th width="80">Показ картинки</th>
        <th width="80">Изменение</th>
        <th width="70">Удаление</th>
    </tr>
    <c:forEach items="${instrumentList}" var="instrument">
        <tr>
            <td>${instrument.instId}</td>
            <td>${instrument.instType}</td>
            <td>${instrument.instName}</td>
            <td>${instrument.instPrice}руб</td>
            <td><c:choose>
                <c:when test="${instrument.instCondition==1}">
                    В работе
                </c:when>
                <c:when test="${instrument.instCondition==0}">
                    Сломан
                </c:when>
            </c:choose></td>
            <td><details>${instrument.instDescription}</details></td>
            <td><form action="instrumentImage" method="GET" target="_blank">
                    <input type="hidden" name="instId" value="${instrument.instId}">
                    <input type="submit" value="Картинка">
            </form></td>
            <td> <form action="editInstrument" method="GET">
                    <input type="hidden" name="instId" value="${instrument.instId}">
                    <input type="submit" value="Изменить">
            </form></td>
            <td><form action="deleteInstrument" method="POST">
                    <input type="hidden" name="instId" value="${instrument.instId}">
                    <input type="submit" value="Удалить">
            </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
