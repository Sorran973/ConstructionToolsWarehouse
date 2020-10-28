<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Database Configuration</title>
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
<br>
<h1>Данные сервера БД</h1>
<br><br>

<form action="databaseConfig" method="POST">
    <label>Драйвер:</label><input name="driver" value="com.microsoft.sqlserver.jdbc.SQLServerDriver"/>
    <br><br>
    <label>Url:</label> <input name="url" value="jdbc:sqlserver://localhost:1433;database=master"/>
    <br><br>
    <label>Пользователь:</label> <input name="user" value="admin1"/>
    <br><br>
    <label>Пароль:</label> <input name="password" value="Password123"/>
    <br><br>
    <input type="submit" value="Подключиться"/>
</form>
</body>
</html>