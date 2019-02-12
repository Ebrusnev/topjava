<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals: </h2>
    <table>
        <tr>
            <th>Date</th>
            <th>Type</th>
            <th>Calories</th>
        </tr>
        <c:forEach items="${mealsTo}" var="mealTo">
            <c:set var="color" value="${mealTo.excess == true ? 'Tomato' : 'MediumSeaGreen'}"/>
            <tr style="background-color:${color};">
                <td><javatime:format value="${mealTo.dateTime}" style="MS" /></td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
