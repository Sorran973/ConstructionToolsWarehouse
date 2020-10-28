<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artembulkhak
  Date: 31/05/2020
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Data of Supply</title>
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
<h1>Информация о Поставке</h1>
<br>
<p><a href="supplies">Назад</a></p>
<br>

<table class="tg">
    <tr>
        <th width="30">ID</th>
        <th width="120">Тип</th>
        <th width="120">Название</th>
        <th width="120">Состояние</th>
    </tr>
    <c:forEach items="${instrumentList}" var="instrument">
        <tr>
            <td>${instrument.instId}</td>
            <td>${instrument.instType}</td>
            <td>${instrument.instName}</td>
            <td><c:choose>
                <c:when test="${instrument.instCondition==1}">
                    В работе
                </c:when>
                <c:when test="${instrument.instCondition==0}">
                    Сломан
                </c:when>
            </c:choose></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
