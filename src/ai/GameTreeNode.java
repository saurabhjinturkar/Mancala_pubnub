/**
 * 
 */
package ai;

import java.util.HashSet;
import java.util.Set;

import core.GameState;

/**
 * @author Saurabh
 *
 */
public class GameTreeNode {
	private int level;
	private GameState gs;
	private Set<GameTreeNode> children;

	public GameTreeNode() {
		children = new HashSet<>();
	}

	public GameTreeNode(GameState gs, int level) {
		this.gs = gs;
		this.level = level;
		children = new HashSet<>();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public GameState getGs() {
		return gs;
	}

	public void setGs(GameState gs) {
		this.gs = gs;
	}

	public Set<GameTreeNode> getChildren() {
		return children;
	}

	public void setChildren(Set<GameTreeNode> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gs == null) ? 0 : gs.hashCode());
		result = prime * result + level;
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
		GameTreeNode other = (GameTreeNode) obj;
		if (gs == null) {
			if (other.gs != null)
				return false;
		} else if (!gs.equals(other.gs))
			return false;
		if (level != other.level)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameTreeNode [level=" + level + ", gs=" + gs + "]";
	}

}
