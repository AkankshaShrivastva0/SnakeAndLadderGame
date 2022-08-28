<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-// W3C// DTD HTML 4.01
Transitional// EN" "http:// www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Snake and Ladder Game</title>
</head>
<body>
	<div class="container style="width: 100%; height: 100%;">
		<div style="background-color: #DFF2BF;">
          <i class="fa fa-check"></i>
           <h2 style=" color: #270;">Winner of the game is :  ${playerWinner}</h2>
          <div class="container">
          <h3> Number of Players :  ${NoOfPlayers}</h3>   
          <h4>List of Players in the Game</h4>
            <c:if test="${not empty totalPlayer}">
		      <ol>
		     	<c:forEach var="listPlayer" items="${totalPlayer}">
				   <li><b>Player : ${listPlayer.name} </li>
			   </c:forEach>
		      </ol>
            </c:if>           
            </div>
           <div class="container"> 
           <h3> Number of Snakes as per given board :  ${NoOfSnakes}</h3>   
             <h4>List of Snake with head and tail in the Game</h4>
            <c:if test="${not empty snakeStartAndEnd}">
		      <ol>
		     	<c:forEach var="listValue" items="${snakeStartAndEnd}">
				   <li><b>Snake Head : ${listValue.start}  ,  Snake Tail : ${listValue.end} </b></li>
			   </c:forEach>
		      </ol>
            </c:if>           
            </div>
            <div class="container">  
             <h3> Number of Ladders as per given board :  ${NoOfLadders}</h3>
              <h4>List of ladder with start and End in the Game</h4>
               <c:if test="${not empty LadderStartAndEnd}">
		         <ol>
		     	    <c:forEach var="listLadder" items="${LadderStartAndEnd}">
				       <li><b>Ladder Start : ${listLadder.start}  Ladder End : ${listLadder.end}</li>
			        </c:forEach>
		         </ol>
            </c:if>           
            </div> 
        </div>
    </div>


</body>
</html>