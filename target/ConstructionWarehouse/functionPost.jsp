<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artembulkhak
  Date: 01/06/2020
  Time: 01:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Function</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
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
            font-size: 14px;
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
        select{
            font-family: Arial, sans-serif;
            font-size: 18px;
        }
        option{
            font-family: Arial, sans-serif;
            font-size: 18px;
        }
    </style>
</head>
<body>
<br>
<h1>Функция</h1>
<br>
<p><a href="main">Назад</a></p>

<form action="function" method="post">
    <label> Инструмент</label><br>
        <select size="1" name="instrument">
            <option disabled>Выберите инструмент</option>
            <c:forEach items="${instrumentList}" var="instrument">
            <option value="${instrument.instType}">${instrument.instType}</option>
            </c:forEach>
        </select><br><br>
    <input type="submit" value="Выполнить" />
</form>
</body>
</html>
