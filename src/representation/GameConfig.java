
package representation;

import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.text.GapContent;


public class GameConfig {
	private int blockSizeToWin = 4;
	private int columnSize = 4;
	private int rowSize = 3;

	
	public GameConfig(int blockSizeToWin, int rowSize, int columnSize) {
		super();
		this.blockSizeToWin = blockSizeToWin;
		this.columnSize = columnSize;
		this.rowSize = rowSize;
	}
	
	//check whether all the places are filled or not
	public int findTopIndex(boolean[][] occupancyMatrix,int columnNumber){
		for(int i = 0; i < occupancyMatrix.length; i++){
			for(int j=0; j < occupancyMatrix[0].length;j++)
				if(!occupancyMatrix[i][j])
				return i;
		}
		return -1;
	}
	
	public List<GameState> generateChildStates(GameState gameState, boolean aiTurn) throws CloneNotSupportedException{
		List<GameState> childList = new LinkedList<GameState>();
		GameState tempState;
		int index;
		boolean[][] bitSets = gameState.getOccupancyMatrix();
		
		for(int i=0; i<columnSize;i++){
			for(int j=0; j<rowSize;j++)
			{
		
			try {
				tempState = (GameState) gameState.clone();
				if(bitSets[j][i])
				{
					continue;
				}
				index = j;
				if(index>-1 && index<rowSize){
					if(aiTurn)
						tempState.setWhite(index, i);
					else
						tempState.setBlack(index, i);
					childList.add(tempState);
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			}
			
		}
		
		
		return childList;
		
	}
	
	//return the blockSizeToWin
	public int getBlockSizeToWin() {
		return blockSizeToWin;
	}

	//set the blockSizeToWin
	public void setBlockSizeToWin(int blockSizeToWin) {
		this.blockSizeToWin = blockSizeToWin;
	}

	// get the column size
	public int getColumnSize() {
		return columnSize;
	}

	// set the column size
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	// get the row size
	public int getRowSize() {
		return rowSize;
	}

	// set the row size
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public GameConfig() {
		super();
	}
}
