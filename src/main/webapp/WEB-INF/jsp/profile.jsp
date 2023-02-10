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
Satistics of player ${user.getUsername()}
<table border="2" width="100%">

<tr>
<td>Total games:
</td>
<td> ${rank.getPlayedGames()}
</td>
<td>Monthly games: 
</td>
<td>${monthlyRank.getMplayedGames()}
</td>
</tr>


<tr>
<td>Total wins:
</td>
<td> ${rank.getWins()}
</td>
<td>Monthly wins: 
</td>
<td>${monthlyRank.getMwins()}
</td>
</tr>


<tr>
<td>Total loses:
</td>
<td> ${rank.getLoses()}
</td>
<td>Monthly games: 
</td>
<td>${monthlyRank.getMloses()}
</td>

<tr>
<td>Total easy games:
</td>
<td> ${rank.getEasyGames()}
</td>
<td>Monthly easy games: 
</td>
<td>${monthlyRank.getMeasyGames()}
</td>


<tr>
<td>Total medium games:
</td>
<td> ${rank.getMediumGames()}
</td>
<td>Monthly medium games: 
</td>
<td>${monthlyRank.getMmediumGames()}
</td>

<tr>
<td>Total hard games:
</td>
<td> ${rank.getHardGames()}
</td>
<td>Monthly hard games: 
</td>
<td>${monthlyRank.getMhardGames()}
</td>
</tr>
</table>
 <input  type="button" id="main page" name="main page" value="main page" onclick="back()">
</div>
 
</body>
</html>