<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
           <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <script type="text/javascript">
function back(){
window.location =  "/HangmanGame/mainPage";
}
</script>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<table border="2" width="100%">
            <tr>
            <th><h1>Overall Rankings</h1></th>
            <th><h1>Monthly Rankings</h1></th>
            </tr>
            <tr>
            <th>
            <c:forEach var="x" items="${bestList}">
            <h1><c:out value=" ${x}"/></h1>
            </c:forEach>
            </th>
            <th>
            <c:forEach var="x" items="${bestMonthlyList}">
            <h1><c:out value=" ${x}"/></h1>
            </c:forEach>
            </th>
            </tr>
            </table>
            <br>
            <input  type="button" id="main page" name="main page" value="main page" onclick="back()">
            <br>
            </div>
</body>
</html>