
package ai;

import representation.GameState;


public interface Heuristic {          //This method returns -infinity to +infinity range of values.
	
	
	public int getHeuristicValue(GameState gameState);
	
	
	public boolean isTerminal(GameState gameState);        //Returns true if the gameState is terminal.
}
