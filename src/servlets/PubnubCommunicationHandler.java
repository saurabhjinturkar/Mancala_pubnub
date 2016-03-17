/**
 * 
 */
package servlets;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

/**
 * @author Saurabh
 *
 */
public class PubnubCommunicationHandler {
	Pubnub pubnub = new Pubnub("pub-c-fe117c7b-0c2b-44bd-8ac0-e70cdae0c6db", "sub-c-09b6a55e-eb4b-11e5-bf9d-02ee2ddab7fe");

	public void function() {
		try {
			pubnub.subscribe("mancala", new Callback() {
				@Override
				public void connectCallback(String channel, Object message) {
					pubnub.publish("mancala",
			            "Hello from the PubNub Java SDK", new Callback() {
			            });
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
