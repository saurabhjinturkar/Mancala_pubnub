
var player = 1
var gameStarted = true;
var turn = true;
var activeSession = -1;

pubnub.subscribe({
	channel : 'mancala',
	message : function(m) {
		console.log(m);

		var msg = JSON.parse(m);
		console.log(msg);
		switch (msg.event) {
		case 'ack':
			console.log(msg.message);
			alert(msg.message);
			gameStarted = true;
			activeSession = msg.sessionId;
			$("#sessionConnector").fadeOut();
			updateTurn();
			break;

		case 'play':
			var gamestate = JSON.parse(msg["gamestate"]);
			if (gamestate.winner >= 0) {
				if (gamestate.winner == 0) {
					$("#msg").html("Game draw!").fadeIn();
				} else if (gamestate.winner == 1) {
					$("#msg").html("Player 1 won!").fadeIn();
				} else if (gamestate.winner == 2) {
					$("#msg").html("Player 2 won!").fadeIn();
				}
				$("#msg").append("<p><a href='http://saurabhjinturkar.in:8080//Mancala/app'>Play again!</p>");
				
			}

			var deck = gamestate["deck"];
			for (var i = 0; i < deck.length; i++) {
				$("#hole" + i).html(deck[i]);
			}
			$("#player1hole").html(gamestate.player1Score);
			$("#player2hole").html(gamestate.player2Score);
			turn = (gamestate.playerTurn == player);
			$(".highlight").removeClass("highlight");
			$("#hole" + gamestate.lastMove).addClass("highlight");
			updateTurn();
			break;

		case "error":
			$("#msg").html(msg.message).fadeIn();
			$("#msg").append("<p><a href='http://saurabhjinturkar.in:8080//Mancala/app'>Try again!</p>");
			alert(msg.message);
		
		default:
			console.log("Other message received!");
		}
	}
});

function updateTurn() {
	if (turn) {
		$("#turn").html("Your turn!");
	} else {
		$("#turn").html("Waiting for other player..");
	}
}