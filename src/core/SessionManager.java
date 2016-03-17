/**
 * 
 */
package core;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SessionManager {

	private Set<Integer> sessionIds;
	private Map<Integer, GameState> sessions;
	private static SessionManager instance;
	private Queue<Integer> random;

	private SessionManager() {
		this.sessionIds = new HashSet<>();
		this.sessions = new HashMap<>();
		this.random = new LinkedList<>();
		for (int i = 0; i < 10000; i++) {
			this.random.add(i);
		}
		Collections.shuffle((List<Integer>) this.random);
	}

	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}

	public int generateSessionId() {
		return this.random.poll();
	}

	public GameState getSession(int sessionId) {
		if (sessions.containsKey(sessionId)) {
			return sessions.get(sessionId);
		}
		return null;
	}

	public boolean startSession(int player1, int player2) {
		this.sessionIds.remove(player1);
		this.sessionIds.remove(player2);
		this.sessions.remove(player2);
		System.out.println(sessionIds);
		System.out.println(sessions);
		return true;
	}

	public boolean createSession(int player1, GameState gs) {
		this.sessions.put(player1, gs);
		return true;
	}

	public boolean stopSession(int sessionId) {
		sessions.remove(sessionId);
		if (!random.contains(sessionId)) {
			random.add(sessionId);
		}
		return true;
	}

	public void removeWaitingSessions(int sessionId) {
		sessionIds.remove((Integer) sessionId);
		if (!random.contains(sessionId)) {
			random.add(sessionId);
		}
	}

	public void updateSession(int sessionId, GameState gs) {
		sessions.put(sessionId, gs);
	}

	public void print() {
		System.out.println(random.size());
		System.out.println(sessions);
	}
}
