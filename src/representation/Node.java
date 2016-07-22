
package representation;

import representation.GameState;


public class Node {
	private GameState currentGameState;
	private GameState childGameState;
	private int heuristicValue;
	public Node(GameState currentGameState) {
		super();
		heuristicValue = 0;
		childGameState = null;
		this.currentGameState = currentGameState;
	}

	public GameState getChildGameState() {
		return childGameState;
	}

	public void setChildGameState(GameState childGameState) {
		this.childGameState = childGameState;
	}

	public int getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}

	public GameState getCurrentGameState() {
		return currentGameState;
	}

	public void setCurrentGameState(GameState currentGameState) {
		this.currentGameState = currentGameState;
	}

	@Override
	public String toString() {
		return "Node [currentGameState=" + currentGameState
				+ ", childGameState=" + childGameState + ", heuristicValue="
				+ heuristicValue + "]";
	}
	
}
