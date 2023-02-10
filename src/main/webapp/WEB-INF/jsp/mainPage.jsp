<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
           <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <script type="text/javascript">
function play(){
window.location =  "/HangmanGame/index1";
}
function rank(){
	window.location =  "/HangmanGame/rankList";
	}
function profile(){
	window.location =  "/HangmanGame/profile";
	}
</script>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
Welcome ${user.getUsername()}
<br>
<input  type="button" id="new game" name="new game" value="new game" onclick="play()">
<br>
<input  type="button" id="RankList" name="Rank List" value="Rank List" onclick="rank()">
<br>
<input  type="button" id="Profile" name="Profile" value="Profile" onclick="profile()">
<br>
</div>
</body>
</html>