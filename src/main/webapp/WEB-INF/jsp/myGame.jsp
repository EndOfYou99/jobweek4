<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.UUID" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="java.util.HashMap" %>
        <%@page import="java.util.Set" %>
    <%@ page import="hangman.business.repo.game.GameServiceImpl" %>
     <%@ page import="hangman.business.repo.game.Game" %>
<%@page session="true" %>
<!DOCTYPE html>
<html>
<%
Game myGame=(Game)request.getAttribute("myGame");
String stringId=(String)request.getAttribute("id");
UUID id=UUID.fromString(stringId);
%>

 <script type="text/javascript">
function createUrl(element){
	let id="<%=id%>";
window.location =  "/HangmanGame/Game/"+id+"?letter="+element.value;
}
</script>
<head>
<meta charset="ISO-8859-1">
<title>JSP</title>
</head>
<body>

 <div is="game" align="center">


<%if(myGame.getResult().equals("N")){%>
            <h3>Welcome to hangman game</h3>
            <h2>You have made <%=myGame.getTries()%> mistakes from total of <%=myGame.getMaxTries()%></h2>
            <h3>
            <b id="hiddenword"><%=myGame.getHiddenWord() %></b> 
            <sub>(<%=myGame.getHiddenWord().length()%>)</sub>
            </h3><br>
            <form action="Game" method="GET">
           <!--   <input type="hidden" name="id" value=<%=id%>>-->
            <input type="button" id="a" name="letter" value="a" onclick="createUrl(this)">
            <input type="button" id="b" name="letter" value="b" onclick="createUrl(this)">
            <input type="button" id="c" name="letter" value="c" onclick="createUrl(this)">
            <input type="button" id="d" name="letter" value="d" onclick="createUrl(this)">
            <input type="button" id="e" name="letter" value="e" onclick="createUrl(this)">
            <input type="button" id="f" name="letter" value="f" onclick="createUrl(this)">
            <input type="button" id="g" name="letter" value="g" onclick="createUrl(this)">
            <input type="button" id="h" name="letter" value="h" onclick="createUrl(this)">
            <input type="button" id="i" name="letter" value="i" onclick="createUrl(this)">
            <input type="button" id="j" name="letter" value="j" onclick="createUrl(this)">
            <input type="button" id="k" name="letter" value="k" onclick="createUrl(this)">
            <input type="button" id="l" name="letter" value="l" onclick="createUrl(this)">
            <input type="button" id="m" name="letter" value="m" onclick="createUrl(this)">
            <input type="button" id="n" name="letter" value="n" onclick="createUrl(this)">
            <input type="button" id="o" name="letter" value="o" onclick="createUrl(this)">
            <input type="button" id="p" name="letter" value="p" onclick="createUrl(this)">
            <input type="button" id="q" name="letter" value="q" onclick="createUrl(this)">
            <input type="button" id="r" name="letter" value="r" onclick="createUrl(this)">
            <input type="button" id="s" name="letter" value="s" onclick="createUrl(this)">
            <input type="button" id="t" name="letter" value="t" onclick="createUrl(this)">
            <input type="button" id="u" name="letter" value="u" onclick="createUrl(this)">
            <input type="button" id="v" name="letter" value="v" onclick="createUrl(this)">
            <input type="button" id="w" name="letter" value="w" onclick="createUrl(this)">
            <input type="button" id="x" name="letter" value="x" onclick="createUrl(this)">
            <input type="button" id="y" name="letter" value="y" onclick="createUrl(this)">
            <input type="button" id="z" name="letter" value="z" onclick="createUrl(this)">
            </form>
            <%=myGame.getUsedLetters() %> 
            <%System.out.println(myGame.getUsedLetters());} %>
   
            
 </div>

</body>
</html>