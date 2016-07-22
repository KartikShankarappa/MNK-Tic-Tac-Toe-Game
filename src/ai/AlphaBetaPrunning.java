
package ai;

import java.util.List;
import java.util.Set;

import representation.GameConfig;
import representation.GameState;
import representation.Node;

public class AlphaBetaPrunning {
	private int counter = 0;
	private int maxDepth;
	private GameConfig gameConfig;
	private Heuristic heuristic;
	private int nodeCounter =0;
	private int alphaPruningCounter = 0;
	private int betaPruningCounter = 0;
	
	
	
	public AlphaBetaPrunning(int maxDepth, GameConfig gameConfig,
			Heuristic heuristic) {
		super();
		this.maxDepth = maxDepth;
		this.gameConfig = gameConfig;
		this.heuristic = heuristic;
		this.nodeCounter =0; 
		this.alphaPruningCounter =0;
		this.betaPruningCounter =0;
	}

	
	public GameState alphaBeta(GameState gameState) throws CloneNotSupportedException{
		Node rootNode = new Node(gameState);
		alphaBeta(rootNode, maxDepth, -2147483646,  2147483646, true);
		return rootNode.getChildGameState();		
	}
	
	
	//We call this to depth limit the search and check if things are in our favour.
	private int alphaBeta(Node node, int depth, int alpha, int beta, boolean aiTurn) throws CloneNotSupportedException {

		nodeCounter++;
		if (heuristic.isTerminal(node.getCurrentGameState())) {
			return heuristic.getHeuristicValue(node.getCurrentGameState());
        } else if (depth < 0) {
        	return heuristic.getHeuristicValue(node.getCurrentGameState());
        }

        List<GameState> children = generateChildren(node.getCurrentGameState(), aiTurn); // generates children. also rates them and applies move to copy of field. 

        if (aiTurn) { // aiTurn tries to maximize the score
            
        	for (GameState child : children) {
        		int score = alphaBeta(new Node(child), depth - 1, alpha, beta, false);
        		
        		if(score>alpha){
        			node.setChildGameState(child);
        			alpha = score;
        		}
        		
                if (beta <= alpha) {
                    betaPruningCounter++;
                    break;   // beta cutoff
                }
                
            }
            return alpha;
        } else {  // enemy tries to minimize the score
        	
        	for (GameState child : children) {
            	int score = alphaBeta(new Node(child), depth - 1, alpha, beta, true);
        		if(score<beta){
        			node.setChildGameState(child);
        			beta = score;
        		}
            	
        		if (beta <= alpha) {
                    alphaPruningCounter++;
        	
                    break;   // alpha cutoff
                }
            }
            return beta;
        }
    }	

	private List<GameState> generateChildren(GameState gameState, boolean aiTurn) throws CloneNotSupportedException {
		return gameConfig.generateChildStates(gameState, aiTurn);
	}
}
