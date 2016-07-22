MNK Tic Tac Toe Game

An m,n,k-game is an abstract board game in which two players take turns in placing a stone of their color on an m×n board, the winner being the player who first gets k stones of their own color in a row, horizontally, vertically, or diagonally.
m,n,k-game is also called a k-in-a-row game on m×n board.

m,n,k-games are mainly of mathematical interest. One seeks to find the game-theoretic value, which is the result of the game with perfect play. This is known as solving the game.

This, tic-tac-toe is the 4,5,4-game. 



About Heuristic Function

The heuristic function used in this code will is “Math.pow(10,(j-i+1)”
This heuristic function will calculate the score of all the generated child nodes of the tree. This score is then fed as an input to the AlphaBetaPruning function which contains the implementation of the Alpha – Beta Search Algotrithm. The heuristic function will continuously run in a loop calculating the value by increasing the power of 10: when it encounters continuous similar value of either X or 0. The computer is declared winner when the value is greater than 10000 and on the other hand the user is declared winner when the value is less than -10000. If the value is 0, then it would mean a draw. used in this program is 10^n, where n is the number of continuous X’s and O’s.



Design

Execution begins at GameMain class. The user is prompted to play first after which the computer plays. This alternates between player and the computer. For each move of the player, all possible nodes are generated in the GameConfig class. For each node, the heuristic value is calculated in the Heuristic class. The nodes along with the heuristic values are then sent to the AlphaBetaPruning class which prunes the nodes based on the heuristic values. The selected node is saved in the GameState class. 

The programs generates two 2D 4*5 matrix for X’s and O’s, namely xBlocks and oBlocks. Initially both the arrays are set to false. When the computer makes a move(i.e ‘X’), the corresponding location in the Boolean xBlocks array is set to true. Similarly when the player makes a move, the corresponding location in the oBlocks array is set to true. For each of these
Boolean arrays, getRow(), getColumn(),getDiagonal() will generate 4, 5, 4 element arrays
respectively. These arrays are then sent to the heuristic function which calculates the score
depending on the number of consecutive true’s. Two true in a row/column/diagonal will entail a
score of 10^2. Similarly for 3 consecutive true’s will be evaluated to a score of 10^3. Four
consecutive true’s will generate a score of 10^4 which is the winning condition.

These 2 arrays are “OR”ed to get the occupancy matrix. If it is all true, then the game is a draw.



Difficulty Level

There are two level of difficulty in this code. The difficulty level is implemented using the
“maxdepth” value in the code. If we increase the value of the depth, then the number of nodes
generated will increase thus making the computer more accurate about the prediction.