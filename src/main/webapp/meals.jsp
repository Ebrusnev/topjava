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
            <th>Meal Type</th>
            <th>Calories per Meal</th>
        </tr>
        <c:forEach items="${mealsTo}" var="mealTo">
            <tr style="background-color:${mealTo.excess ? 'Tomato' : 'MediumSeaGreen'};">
                <td><javatime:format value="${mealTo.dateTime}" style="MS" /></td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <c:set var="id" value="${mealTo.id}"/>
                <td>
                    <a type="button" href="meals?action=edit&id=${id}">Edit</a>
                </td>
                <td>
                    <a type="button" href="meals?action=delete&id=${id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
