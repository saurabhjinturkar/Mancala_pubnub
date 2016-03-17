/**
 * 
 */
package ai;

import core.GameState;

/**
 * @author Saurabh
 *
 */
public class AIGame {

	private GameTree tree;

	public AIGame(GameState gs) {
		this.tree = new GameTree(new GameState(gs));
	}

	public int getBestMove() {
		return tree.getBestMove();
	}

	public static void main(String[] args) {

	}

}
