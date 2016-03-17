/**
 * 
 */
package servlets;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import core.GameState;

/**
 * @author Saurabh
 *
 */
@ApplicationScoped
public class PubnubCommunicationHandler {
	private Pubnub pubnub = new Pubnub(
	    "pub-c-fe117c7b-0c2b-44bd-8ac0-e70cdae0c6db",
	    "sub-c-09b6a55e-eb4b-11e5-bf9d-02ee2ddab7fe");

	private GameState gs;
	private final Gson gson = new Gson();
	private final Type type = new TypeToken<Map<String, String>>() {
	}.getType();

	private static final Logger logger = Logger.getAnonymousLogger();

	public PubnubCommunicationHandler() {
		gs = new GameState();
	}

	public void function() {
		try {
			pubnub.subscribe("mancala", new Callback() {
				@Override
				public void connectCallback(String channel, Object message) {
				}

				@Override
				public void disconnectCallback(String channel, Object message) {
					System.out.println(
			            "SUBSCRIBE : DISCONNECT on channel:" + channel + " : "
			                + message.getClass() + " : " + message.toString());
				}

				public void reconnectCallback(String channel, Object message) {
					System.out.println(
			            "SUBSCRIBE : RECONNECT on channel:" + channel + " : "
			                + message.getClass() + " : " + message.toString());
				}

				@Override
				public void successCallback(String channel, Object message) {
					System.out.println("SUBSCRIBE : " + channel + " : "
			            + message.getClass() + " : " + message.toString());

					Map<String, String> map = gson.fromJson(message.toString(),
			            type);
					String event = map.get("event");

					switch (event) {
					case "start":
						System.out.println("START EVENT");
						break;

					case "registration":
						System.out.println("REGISTRATION EVENT");
						break;

					case "serverplay":
						try {
							gs.playHole(Integer.parseInt(map.get("hole")));
							Map<String, String> tempMap = new HashMap<>();
							tempMap.put("event", "play");
							tempMap.put("hole", map.get("hole"));
							tempMap.put("gamestate", gs.toJson());
							pubnub.publish("mancala", gson.toJson(tempMap),
			                    new Callback() {
			                    });
						} catch (Exception e) {
							logger.log(Level.SEVERE, e.getMessage(), e);
						}
						break;

					case "close":
						int sessionId = Integer
			                .parseInt(map.get("activeSession"));
						break;
					default:
						logger.info("Custom event occurred!");
					}
				}

				@Override
				public void errorCallback(String channel, PubnubError error) {
					System.out.println("SUBSCRIBE : ERROR on channel " + channel
			            + " : " + error.toString());
				}
			});
		} catch (PubnubException e) {
			System.out.println(e.toString());
		}
	}
}
