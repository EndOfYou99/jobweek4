<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
<form:form modelAttribute="user" action="register" method="post">
Registration page
<br>
<br>
Enter username: <form:input path="username" id="username"/><form:errors path="username"/>
<br>
<br>
Enter password: <form:input path="password" id="password"/><form:errors path="password"/>
<br>
<br>
${error}
<br>
<input type="submit" value="register"/>
</form:form>
</body>
</html>