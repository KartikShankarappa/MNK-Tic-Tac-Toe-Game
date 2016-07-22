
package representation;

import java.util.Arrays;
import java.util.BitSet;

import ai.AlphaBetaPrunning;
import ai.CustomHeuristic;
import ai.Heuristic;
import cli.GameMain;

public class GameState implements Cloneable {
	private boolean[][] blackBlocks;
	private boolean[][] whiteBlocks;
	
	/**
	 * Constructor Initiates a state of n*m. Each row on the board is
	 * represented as a BitSet. There are m (number of rows) then we initialize
	 * an array of size m. Each BitSet in this array is of size 'n'
	 * (numberOfColumns)
	 */

	public GameState(int numberOfRows, int numberOfColumns) {
		super();

		// We use this representation as we can then exploit bitSet.get
		this.blackBlocks = new boolean[numberOfRows][numberOfColumns];
		this.whiteBlocks = new boolean[numberOfRows][numberOfColumns];

	}

	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		for(int i=0;i<blackBlocks.length;i++){
			for(int j=0;j<blackBlocks[0].length;j++){
				if(blackBlocks[i][j])
					{buffer.append(" X ");}
				else if(whiteBlocks[i][j])
					{buffer.append(" O ");
					GameMain.setw(i, j);
					}
				else
					buffer.append(" = ");
			}
			buffer.append("\n");
		}
		buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		return buffer.toString();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(blackBlocks);
		result = prime * result + Arrays.deepHashCode(whiteBlocks);
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
		if (!Arrays.equals(blackBlocks, other.blackBlocks))
			return false;
		if (!Arrays.equals(whiteBlocks, other.whiteBlocks))
			return false;
		return true;
	}

	// set the black co-ordinate
	public void setBlack(int rowCoordinate, int columnCoordinate){
		blackBlocks[rowCoordinate][columnCoordinate] = true;
	}
	
	// set the white co-ordinate
	public void setWhite(int rowCoordinate, int columnCoordinate){
		whiteBlocks[rowCoordinate][columnCoordinate] = true;
	}
	
	// get BlackBlocks
	public boolean[][] getBlackBlocks() {
		return blackBlocks;
	}

	// set BlackBlocks
	public void setBlackBlocks(boolean[][] blackBlocks) {
		this.blackBlocks = blackBlocks;
	}

	// get Whiteblocks
	public boolean[][] getWhiteBlocks() {
		return whiteBlocks;
	}

	// set WhiteBlocks
	public void setWhiteBlocks(boolean[][] whiteBlocks) {
		this.whiteBlocks = whiteBlocks;
	}
	
	// send the WhiteBlocks
	public boolean isWhite(int rowCordinate, int columnCordinate) {
		return this.whiteBlocks[rowCordinate][columnCordinate];
	}
	
	// send the BlackBlocks
	public boolean isBlack(int rowCordinate, int columnCordinate) {
		return this.blackBlocks[rowCordinate][columnCordinate];
	}

	public boolean isEmpty(int rowCordinate, int columnCordinate) {
		return !(isBlack(rowCordinate, columnCordinate) || isWhite(
				rowCordinate, columnCordinate));
	}


	@Override
	public Object clone() throws CloneNotSupportedException {
		GameState gameState = new GameState(this.blackBlocks.length, this.blackBlocks[0].length);
		boolean[][] newBlackBlocks = gameState.getBlackBlocks();
		boolean[][] newWhiteBlocks = gameState.getWhiteBlocks();
		for(int i=0;i<this.blackBlocks.length;i++){
			newBlackBlocks[i] = Arrays.copyOf(blackBlocks[i], blackBlocks[i].length);
			newWhiteBlocks[i] = Arrays.copyOf(whiteBlocks[i], whiteBlocks[i].length);
		}
		gameState.setBlackBlocks(newBlackBlocks);
		gameState.setWhiteBlocks(newWhiteBlocks);
		return gameState;
	}
	
	// get the occupancy matrix
	public boolean[][] getOccupancyMatrix() {
		boolean[][] occupancyMatrix = new boolean[whiteBlocks.length][whiteBlocks[0].length];
		for (int i = 0; i < whiteBlocks.length; i++)
			for (int j = 0; j < whiteBlocks[0].length; j++)
				occupancyMatrix[i][j] = (whiteBlocks[i][j] || blackBlocks[i][j]);

		return occupancyMatrix;

	}

	public static void main(String[] args) throws CloneNotSupportedException {
		GameConfig config = new GameConfig(3, 3,3);
		Heuristic heuristic = new CustomHeuristic(config);

		GameState gameState = new GameState(config.getRowSize(),
				config.getColumnSize());
		
		gameState.setBlack(0,0);
		gameState.setBlack(0,1);
		gameState.setWhite(0,2);
		
		gameState.setWhite(1,0);
		gameState.setWhite(1,1);
		gameState.setBlack(1,2);
		
		gameState.setBlack(2,0);
		gameState.setWhite(2,1);
		
		
		AlphaBetaPrunning alphaBetaPrunning = new AlphaBetaPrunning(1, config,
				heuristic);

		GameState childGameState = alphaBetaPrunning.alphaBeta(gameState);

		
	}
}
