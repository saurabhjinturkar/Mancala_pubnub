/**
 * 
 */
package ai;

import core.GameState;

/**
 * @author Saurabh
 *
 */
public class GameTree {
	private GameTreeNode root;

	public GameTree(GameState gs) {
		root = new GameTreeNode(gs, 0);
	}

	public int getBestMove() {
		generateTree();
		return 0;
	}

	private void generateTree() {
		
	}

}
