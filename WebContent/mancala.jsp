<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Mancala</title>
<link rel="stylesheet" type="text/css"
	href="/Mancala/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/Mancala/css/mancala.css">
</head>

<body>
	
	<div id="sessionConnector">
	<%
		if (request.getAttribute("started") == null) {
	%>

	Welcome to Mancala!
	<br> Use this id to connect with your partner:
	<%
		out.print(request.getAttribute("selfid"));
	%>

	<br> If you have id of your partner, enter it here:
	<form method="post" action="/Mancala/app/start">
		<input type="text" value="" name="id" /> <input type="hidden"
			value=<%=request.getAttribute("selfid")%> name="playerSession" /> <input
			type="submit" value="Play!" />
	</form>
	<%
		}
	%>
	</div>
	<div align="center" class="container">
		<div class="white-space"></div>
		<div align="center" id="turn">Waiting for player to join!</div>
		<table class="table-bordered">
			<tr>
				<td rowspan="2" id="player2area"><div class="goalholes"
						id="player2hole">0</div></td>
				<td class="holearea"><div class="holes" id="hole11">4</div></td>
				<td class="holearea"><div class="holes" id="hole10">4</div></td>
				<td class="holearea"><div class="holes" id="hole9">4</div></td>
				<td class="holearea"><div class="holes" id="hole8">4</div></td>
				<td class="holearea"><div class="holes" id="hole7">4</div></td>
				<td class="holearea"><div class="holes" id="hole6">4</div></td>
				<td rowspan="2" id="player1area"><div class="goalholes"
						id="player1hole">0</div></td>
			</tr>
			<tr>
				<td class="holearea"><div class="holes" id="hole0">4</div></td>
				<td class="holearea"><div class="holes" id="hole1">4</div></td>
				<td class="holearea"><div class="holes" id="hole2">4</div></td>
				<td class="holearea"><div class="holes" id="hole3">4</div></td>
				<td class="holearea"><div class="holes" id="hole4">4</div></td>
				<td class="holearea"><div class="holes" id="hole5">4</div></td>
			</tr>
		</table>
	</div>
	<div align="center" id="msg"><p></p></div>
	
	<div class="white-space"></div>
	<div align="center" >
		<h3>How to play</h3>
		<p id="howtoplay">
		1) Find a buddy to play the game! Share your id with them. 
		<br> 2) Player who is entering the id in the box is Player 2.
		<br>3) Goal area for player 1 is on right and for player 2 it's on left.
		<br>4) Player 1 has bottom side to play. Player 2 has top side.
		<br>5) To move, you click on one of the cell in your row. This picks up all the pieces in that cell and moves them counter-clockwise, putting one gem in each cell.
		<br>6) If the last gem of your move lands in your goal area, you will get a free turn.
		<br>7) If the last gem of your move lands in an empty cell on your side and there are gems in the opponents cell right opposite to it, then you win all the gems in both cells.
		<br> You can read nice article about how to play Mancala <a href="http://www.instructables.com/id/How-to-play-mancala-1/?ALLSTEPS" target="BLANK">here.</a>
		</p>
	</div>
	<br><br>&copy; Saurabh Jinturkar
	<script src="https://code.jquery.com/jquery-2.2.0.min.js"
		type="text/javascript"></script>
	<script>
		var sessionId = <%=request.getAttribute("sessionid")%>
		var selfId = <%=request.getAttribute("selfid")%>
		<%boolean b = request.getAttribute("started") != null;%>
		var isGameStarted = <%= b %>
	</script>
	<script src="/Mancala/js/mancala.js" type="text/javascript"></script>
	<script src="/Mancala/js/websocket.js" type="text/javascript"></script>
	
	<script>
  	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  	ga('create', 'UA-47052202-1', 'auto');
  	ga('send', 'pageview');

</script>
</body>
</html>