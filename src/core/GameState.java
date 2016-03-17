package core;

import java.util.Arrays;
import java.util.UUID;

import com.google.gson.Gson;

public class GameState {
	// private transient Session player1;
	// private transient Session player2;
	private int playerTurn;
	private int player1Score;
	private int player2Score;
	private int[] deck;
	private int lastMove;
	private int winner;
	private boolean freeTurn;
	private final long uid = UUID.randomUUID().getLeastSignificantBits();

	public GameState() {
		initialize();
	}

	public GameState(GameState gs) {
		this.playerTurn = gs.playerTurn;
		this.player1Score = gs.player1Score;
		this.player2Score = gs.player2Score;
		this.deck = Arrays.copyOf(gs.deck, gs.deck.length);
		this.lastMove = gs.lastMove;
		this.winner = gs.winner;
		this.freeTurn = gs.freeTurn;
	}

	private void initialize() {
		this.playerTurn = 1;
		this.player1Score = 0;
		this.player2Score = 0;
		this.winner = -1;
		this.deck = new int[12];
		for (int i = 0; i < 12; i++) {
			this.deck[i] = 4;
		}
	}

	public void playHole(int hole) {
		int marbles = deck[hole];
		lastMove = hole;
		deck[hole] = 0;
		freeTurn = false;
		play(hole, marbles);
		if (checkWin() >= 0) {
			winner = checkWin();
		}
	}

	private int checkWin() {
		boolean player1SideEmpty = true;
		boolean player2SideEmpty = true;
		for (int i = 0; i < 6; i++) {
			if (deck[i] > 0) {
				player1SideEmpty = false;
				break;
			}
		}

		for (int i = 6; i < 12; i++) {
			if (deck[i] > 0) {
				player2SideEmpty = false;
				break;
			}

		}

		if (player1SideEmpty || player2SideEmpty) {
			if (player1Score > player2Score) {
				return 1;
			} else if (player2Score > player1Score) {
				return 2;
			} else {
				return 0;
			}
		}
		return -1;
	}

	private void play(int hole, int marbles) {
		if (marbles <= 0) {

			if (freeTurn)
				return;

			if (playerTurn == 1) {
				playerTurn = 2;
			} else {
				playerTurn = 1;
			}
			return;
		}

		if (marbles > 0) {
			if (hole == 5 && playerTurn == 1) {
				player1Score++;
				marbles--;
			}

			if (hole == 11 && playerTurn == 2) {
				player2Score++;
				marbles--;
			}

			if (marbles == 0) {
				freeTurn = true;
			}
		}

		hole++;
		if (hole < 12 && marbles == 1 && deck[hole] == 0
		    && deck[11 - hole] > 0) {
			if (playerTurn == 1 && hole > -1 && hole < 6) {
				player1Score += deck[11 - hole] + 1;
				deck[11 - hole] = 0;
				marbles--;
				play(hole, marbles);
				return;
			} else if (playerTurn == 2 && hole > 5 && hole < 12) {
				player2Score += deck[11 - hole] + 1;
				deck[11 - hole] = 0;
				marbles--;
				play(hole, marbles);
				return;
			}
		}

		if (hole == 12) {
			hole = 0;
		}

		if (marbles > 0) {
			deck[hole]++;
			marbles--;
		}
		play(hole, marbles);
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	// public void setPlayer2(Session player2) {
	// this.player2 = player2;
	// }
	//
	// public Session getPlayer1() {
	// return player1;
	// }
	//
	// public void setPlayer1(Session player1) {
	// this.player1 = player1;
	// }

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	// public Session getPlayer2() {
	// return player2;
	// }

	public int getPlayer1Score() {
		return player1Score;
	}

	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}

	public int[] getDeck() {
		return deck;
	}

	public void setDeck(int[] deck) {
		this.deck = deck;
	}

	public int getLastMove() {
		return lastMove;
	}

	public void setLastMove(int lastMove) {
		this.lastMove = lastMove;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public boolean isFreeTurn() {
		return freeTurn;
	}

	public void setFreeTurn(boolean freeTurn) {
		this.freeTurn = freeTurn;
	}

	public int heuristic(int forPlayer) {
		int output = 0;
		if (forPlayer == 1) {
			if (winner == 1) {
				output = Integer.MAX_VALUE;
			} else {
				output += 10 * (player1Score - player2Score);
				if (playerTurn == 1) {
					output += 5;
				}
			}
		} else {
			if (winner == 2) {
				output = Integer.MAX_VALUE;
			} else {
				output += 10 * (player2Score - player1Score);
				if (playerTurn == 2) {
					output += 5;
				}
			}
		}
		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (uid ^ (uid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameState other = (GameState) obj;
		if (uid != other.uid)
			return false;
		return true;
	}

	@Override
	public String toString() {

		String output = "GameState [player1=";
		// if (player1 != null) {
		// output += player1.getId();
		// }
		// output += ", player2=";
		// if (player2 != null) {
		// output += player2.getId();
		// }
		// return output + ", playerTurn=" + playerTurn + ", gamestate="
		// + Arrays.toString(deck) + "]";

		return " player1: " + player1Score + " player2: " + player2Score
		    + " heuristic:" + heuristic(1) + " " + heuristic(2) + " ";
	}
}
