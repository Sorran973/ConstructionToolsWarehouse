<%--
  Created by IntelliJ IDEA.
  User: artembulkhak
  Date: 31/05/2020
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit Supply</title>
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
<h1>Изменение Поставки</h1>
<br>
<p><a href="supplies">Назад</a></p>
<br>
<form action="editSupply" method="post">
    <input type="hidden" value="${supply.supId}" name="supId" />
    <label>Дата</label><br>
    <input name="supDate" value="${supply.supDate}" /><br><br>
    <label> Адрес </label><br>
    <select size="1" name="supObjId">
            <option disabled>Выберите адрес</option>
            <c:forEach items="${objectList}" var="object">
            <option value="${object.objAddress}">${object.objAddress}</option>
            </c:forEach>
    </select><br><br>
    <label> Инструменты </label><br>
    <c:forEach items="${instrumentList}" var="instrument">
            <input type="checkbox" name="instIdList" value="${instrument.instId}" />${instrument.instId}: ${instrument.instType}, ${instrument.instName}
            <br>
        </c:forEach><br>
    <input type="submit" value="Изменить" />
</form>
</body>
</html>

