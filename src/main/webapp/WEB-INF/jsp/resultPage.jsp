<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="hangman.business.repo.game.GameServiceImpl" %>
     <%@ page import="hangman.business.repo.game.Game" %>
         <%@page import="java.util.UUID" %>
         <%@page import="java.util.List" %>
         <%@page import="hangman.business.repo.ranks.Ranks" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <script type="text/javascript">
function go(){
window.location =  "/HangmanGame/index1";
}

function exit(){
	window.location =  "/HangmanGame/login";
	}
</script>
<head>
<meta charset="ISO-8859-1">
<title>ResultPage</title>
</head>
<body>
<%
Game myGame=(Game)request.getAttribute("game");
String stringId=(String)request.getAttribute("id");
UUID id=UUID.fromString(stringId);
int ctr=0;
%>
 <%if (myGame.getResult().equals("W")){ %>
            You win!
            <div align="center">
            <div>
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
            </div>
            <br>
            
            <br>
             <input  type="button" id="new game" name="new game" value="new game" onclick="go()">
             <br>
             <br>
              <input  type="button" id="exit" name="exit" value="exit" onclick="exit()">
            <br>
            </div>
            <%}else if (myGame.getResult().equals("L")){ %>
            <div align="center">
            You lose!
<br>
The word was: <%=myGame.getWord()%>
            <div>
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
            </div>
            <br>
            
            <br>
            <input  type="button" id="new game" name="new game" value="new game" onclick="go()">
            <br>
            <br>
             <input  type="button" id="exit" name="exit" value="exit" onclick="exit()">
            <br>
            </div>
            <%} %>
            
</body>
</html>