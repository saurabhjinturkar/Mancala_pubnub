/**
 * Created by Saurabh on 17-Feb-16.
 */

$(document).ready(function() {
	$("#msg").hide(1000);
	$(".holearea").click(function(event) {
		if (!gameStarted || !turn) {
			$("#msg").html("Waiting for other player...");
			$("#msg").fadeIn(1000).delay(1000).fadeOut(1000);
			return;
		}
		var pebbleCount = event.target.innerHTML;
		if (pebbleCount == 0) {
			console.log("No pebbles present!")
			$("#msg").html("No pebbles present!");
			$("#msg").fadeIn(1000).delay(1000).fadeOut(1000);
			return;
		} else {
			var hole = ("" + event.target.id).substring(4);
			if (player == 1 && (hole > 5 || hole < 0)) {
				$("#msg").html("Not your area!");
				$("#msg").fadeIn(200).delay(1000).fadeOut(200);
				return;
			} else if (player == 2 && (hole > 11 || hole < 6)) {
				$("#msg").html("Not your area!");
				$("#msg").fadeIn(200).delay(1000).fadeOut(200);
				return;
			}
			var msg = {};
			msg.event = "play";
			msg.hole = ("" + event.target.id).substring(4);
			msg.sessionId = activeSession;
			socket.send(JSON.stringify(msg));
		}
	});
});