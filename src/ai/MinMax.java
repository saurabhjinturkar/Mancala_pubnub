/**
 * 
 */
package ai;

import java.util.LinkedList;
import java.util.List;

import core.GameState;

/**
 * @author Saurabh
 *
 */
public class MinMax {

	private GameState gs;

	private List<MinMax> list;

	private int player;

	public MinMax(GameState gs) {
		this.gs = gs;
		this.list = new LinkedList<>();
		this.player = 1;
	}

	public GameState getGs() {
		return gs;
	}

	public void generatePossibleMoves(int level) {

		if (gs.getWinner() > -1 || level > 2) {
			return;
		}

		System.out.println(level);
		generateMovesForPlayer(gs,level + player);

		int maxScore = Integer.MIN_VALUE;
		List<MinMax> goodMoves = new LinkedList<>();

		if (gs.getPlayerTurn() == 1) {
			for (MinMax m2 : list) {
				if (m2.getGs().heuristic(1) > maxScore) {
					maxScore = m2.getGs().heuristic(1);
				}
			}

			for (MinMax m2 : list) {
				if (m2.getGs().heuristic(1) >= (maxScore / 2)) {
					goodMoves.add(m2);
				}
			}
		} else {
			for (MinMax m2 : list) {
				if (m2.getGs().heuristic(2) > maxScore) {
					maxScore = m2.getGs().heuristic(2);
				}
			}

			for (MinMax m2 : list) {
				if (m2.getGs().heuristic(2) >= (maxScore / 2)) {
					goodMoves.add(m2);
				}
			}
		}
		System.out.println(goodMoves + ">>MAX_SCORE=> " + maxScore);
		level++;
		for (MinMax minMax : goodMoves) {
			minMax.generatePossibleMoves(level);
		}

	}

	/**
	 * @param player 
	 * 
	 */
	private void generateMovesForPlayer(GameState gs, int player) {
		if (gs.getPlayerTurn() != player) {
			return;
		}
		for (int i = 0; i < 6; i++) {
			int move = (gs.getPlayerTurn() - 1) * 6 + i;
			GameState gs1 = new GameState(this.gs);
			MinMax m = new MinMax(gs1);
			m.getGs().playHole(move);
			list.add(m);
		}
	}

	public List<MinMax> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "MinMax [gs=" + gs + "list=" + list + "]";
	}

	static int count = 0;

	public void print(MinMax m) {
		if (m.list.isEmpty()) {
			System.out.println(++count + " " + m.getGs());
			return;
		}

		for (MinMax minMax : m.list) {
			print(minMax);
		}

	}

	public static void main(String[] args) {
		GameState gs = new GameState();
		MinMax m = new MinMax(gs);
		m.generatePossibleMoves(0);
		for (MinMax m2 : m.getList()) {
			System.out.println(m2);
		}
		m.print(m);
	}

}
