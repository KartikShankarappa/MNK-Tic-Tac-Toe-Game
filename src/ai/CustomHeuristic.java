
package ai;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

import representation.GameConfig;
import representation.GameState;

import com.google.common.primitives.Booleans;

//This class will generate the heuristic value
public class CustomHeuristic implements Heuristic {
	private GameConfig gameConfig = new GameConfig();

	
	public CustomHeuristic(GameConfig gameConfig) {
		super();
		this.gameConfig = gameConfig;
	}

	@Override
	public int getHeuristicValue(GameState gameState) {
		int score = 0;
		//Check if the state is a draw state.
				//Draw state occurs when the board is filled completely and we have no other valid move possible.
		
		boolean[][] bitSets = gameState.getOccupancyMatrix();
		boolean isDraw = true;
		for(int i=0; i<bitSets[0].length;i++){
			if(gameConfig.findTopIndex(bitSets, i)!=-1)
				isDraw = false;
		}
		if(isDraw)
			return 0;
		
		// Checks for n-sized black-connects in the column.
		List<boolean[]> list = new LinkedList<boolean[]>();
		list.addAll(getRowBitSets(gameState.getBlackBlocks()));
		list.addAll(getColumnBitSets(gameState.getBlackBlocks()));
		list.addAll(getDiagonalBitSets(gameState.getBlackBlocks()));
		for(boolean[] tempArray : list)
			score -= getHeuristicValue(tempArray);
		
		//check weather given function is terminal node or not
		list = new LinkedList<boolean[]>();
		list.addAll(getRowBitSets(gameState.getWhiteBlocks()));
		list.addAll(getColumnBitSets(gameState.getWhiteBlocks()));
		list.addAll(getDiagonalBitSets(gameState.getWhiteBlocks()));
		for(boolean[] tempArray : list)
			score += getHeuristicValue(tempArray);
		return score;
	}

	@Override
	public boolean isTerminal(GameState gameState) {
		boolean[][] bitSets = gameState.getOccupancyMatrix();
		boolean isDraw = true;
		int i =0;
		
			if(gameConfig.findTopIndex(bitSets, i)!=-1)
				isDraw = false;
		
		if(isDraw)
			return true;
		
		List<boolean[]> list = new LinkedList<boolean[]>();
		list.addAll(getRowBitSets(gameState.getBlackBlocks()));
		list.addAll(getColumnBitSets(gameState.getBlackBlocks()));
		list.addAll(getDiagonalBitSets(gameState.getBlackBlocks()));
		for(boolean[] tempArray : list)
			if(checkForContiguousBlock(tempArray))
				return true;
		
		list = new LinkedList<boolean[]>();
		list.addAll(getRowBitSets(gameState.getWhiteBlocks()));
		list.addAll(getColumnBitSets(gameState.getWhiteBlocks()));
		list.addAll(getDiagonalBitSets(gameState.getWhiteBlocks()));
		for(boolean[] tempArray : list)
			if(checkForContiguousBlock(tempArray))
				return true;
		
		return false;
		
	}
	
	private List<boolean[]> getColumnBitSets(boolean[][] bs){
		List<boolean[]> bitSets = new LinkedList<boolean[]>();
		for(int j = 0; j<gameConfig.getColumnSize();j++){
			boolean[] tempArray = new boolean[gameConfig.getRowSize()];
			for(int i=0;i<gameConfig.getRowSize();i++)
				tempArray[i] = bs[i][j];
			bitSets.add(tempArray);
		}
		return bitSets;
	}
	
	private List<boolean[]> getDiagonalBitSets(boolean[][] bs){
		List<boolean[]> bitSets = new LinkedList<boolean[]>();
		int m = gameConfig.getRowSize();
		int n = gameConfig.getColumnSize();
		for(int slice=0;slice<m+n-1;++slice){
			int z1 = slice < n ? 0 : slice - n + 1;
			int z2 = slice < m ? 0 : slice - m + 1;
			List<Boolean> bitList1 = new LinkedList<Boolean>();
			List<Boolean> bitList2 = new LinkedList<Boolean>();
			for (int j = slice - z2; j >= z1; --j) {
				bitList1.add( bs[j][(slice - j)]);
				bitList2.add( bs[(m-j-1)][(slice - j)]);
			}
			
			bitSets.add(Booleans.toArray(bitList1));
			bitSets.add(Booleans.toArray(bitList2));
		}
		return bitSets;
	}
	
	
	private List<boolean[]> getRowBitSets(boolean[][] bs) {
		List<boolean[]> bitSets = new LinkedList<boolean[]>();
		for(boolean[] tempArray: bs){
			bitSets.add(tempArray);
		}
		return bitSets;
	}

	
	//gives the heuristic value
	private int getHeuristicValue(boolean[] bs) {
		
		int score = 0;
		for (int i = 0; i < bs.length; ) {
			int j = i + 1;
			if (bs[i]) {
				for (; j < bs.length; j++)
					if (!bs[j])
						break;
				
					score += Math.pow(10, (j - i + 1) );
				
			}
			i++;
		}
		return score;
	}

	//check for continuous 4 block
	private boolean checkForContiguousBlock(boolean[] bs) {
		for (int i = 0; i < bs.length; i++) {
			if (bs[i]) {
				int j = i + 1;
				for (; j < bs.length; j++)
					if (!bs[j])
						break;
				if ((j - i + 1) > gameConfig.getBlockSizeToWin())
					return true;
			}
		}
		return false;
	}
}
