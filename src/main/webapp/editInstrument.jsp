<%--
  Created by IntelliJ IDEA.
  User: artembulkhak
  Date: 30/05/2020
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit Instrument</title>
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
    </style>
</head>
<body>
<h1>Изменение Инструмента</h1>
<br>
<p><a href="instruments">Назад</a></p>
<br>
<form action="editInstrument" method="post" enctype="multipart/form-data">
    <input type="hidden" value="${instrument.instId}" name="instId" />
    <label>Тип</label><br>
    <input name="instType" value="${instrument.instType}" /><br><br>
    <label>Название</label><br>
    <input name="instName" value="${instrument.instName}" /><br><br>
    <label>Цена</label><br>
    <input name="instPrice" value="${instrument.instPrice}" /><br><br>
    <label>Состояние</label><br>
    <input name="instCondition" value="${instrument.instCondition}" /><br><br>
    <input type="hidden" value="${instrument.instLocation}" name="instLocation" />
    <label>Описание</label><br>
    <input name="instDescription" value="${instrument.instDescription}" /><br><br>
    <label>Картинка</label><br>
    <input type="file" name="image"/><br><br>
    <input type="submit" value="Изменить" />
</form>
</body>
</html>
