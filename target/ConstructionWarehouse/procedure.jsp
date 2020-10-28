<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artembulkhak
  Date: 01/06/2020
  Time: 00:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Procedure</title>
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
<h1>Процедура (Количество Инструментов)</h1>
<br>
<p><a href="main">Назад</a></p>
<br>

<table class="tg">
    <tr>
        <th width="120">Тип</th>
        <th width="80">Количество</th>
    </tr>
        <%
            ArrayList<String> instType = new ArrayList<String>();
            ArrayList<Integer> instAmount = new ArrayList<Integer>();
            instType = (ArrayList<String>)request.getAttribute("instType");
            instAmount = (ArrayList<Integer>)request.getAttribute("instAmount");

            for (int i = 0; i < instType.size(); i++){
            %>
    <tr>
        <td><%out.print(instType.get(i));%></td>
        <td><%out.print(instAmount.get(i));%></td>
        <%}%>
    </tr>
</table>
</body>
</html>
