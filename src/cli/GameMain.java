package cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import ai.AlphaBetaPrunning;
import ai.CustomHeuristic;
import ai.Heuristic;
import representation.GameConfig;
import representation.GameState;

public class GameMain implements ActionListener {
	public static int k;
	private static JFrame window = new JFrame("Tic-Tac-Toe");
    public static JButton buttons[] = new JButton[21];
   public static int count = 0;
   private String letter = "";
   boolean computerturn;
   GameConfig config;
   Heuristic heuristic;
   GameState gameState;
   AlphaBetaPrunning alphaBetaPrunning;
   int row = 4;
   int column = 5;
   int lookupLevel;
   int connectN = 4;
   
   
   public GameMain(){
	   /*Create Window*/
	   lookupLevel = Integer.parseInt(JOptionPane.showInputDialog("Select the difficulty level \n 0 = Easy 1 = Hard"));  
	   
	   config = new GameConfig(connectN, row, column);
		 
		 gameState = new GameState(config.getRowSize(),
				config.getColumnSize());
		 
   window.setPreferredSize(new Dimension(1000,1000));
   window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   window.setLayout(new GridLayout(4,5));
   
   /*Add Buttons To The Window*/
   for(int i = 1; i<=20; i++){
       buttons[i] = new JButton();
       window.add(buttons[i]);
       buttons[i].addActionListener(this);
       buttons[i].setFont(new Font("Verdana", Font.BOLD, 32));
   }
   
   /*Make The Window Visible*/ 
   window.setVisible(true);
   window.pack();
   }
   
   
   public void actionPerformed(ActionEvent a) {
	   /*Write the letter to the button and deactivate it*/
       for(int i = 1; i<= 20; i++){
           if(a.getSource() == buttons[i]){
               buttons[i].setText("0");
               buttons[i].setForeground(Color.red);
               buttons[i].setEnabled(false);
               k=i;
               break;
               
           }
    
           
       }
       
       count++;
       computerturn=true;
      try{ AI(k,computerturn);}
      catch(Exception e)
      {
    	  System.out.println("error");
      }
   }
   public static int click_index()
   {
   	return k;
   }
   public static void setb(int k, int j)
   {
   	int i=-1;
		if(k==0 && j==0)
			i=1;
		if(k==0 && j==1)
			i=2;
		if(k==0 && j==2)
			i=3;
		if(k==0 && j==3)
			i=4;
		if(k==0 && j==4)
			i=5;
		if(k==1 && j==0)
			i=6;
		if(k==1 && j==1)
			i=7;
		if(k==1 && j==2)
			i=8;
		if(k==1 && j==3)
			i=9;
		if(k==1 && j==4)
			i=10;
		if(k==2 && j==0)
			i=11;
		if(k==2 && j==1)
			i=12;
		if(k==2 && j==2)
			i=13;
		if(k==2 && j==3)
			i=14;
		if(k==2 && j==4)
			i=15;
		if(k==3 && j==0)
			i=16;
		if(k==3 && j==1)
			i=17;
		if(k==3 && j==2)
			i=18;
		if(k==3 && j==3)
			i=19;
		if(k==3 && j==4)
			i=20;
		 buttons[i].setText("x");
       
   }
   public static void setw(int k, int j)
   {
   	int i=-1;
   	if(k==0 && j==0)
		i=1;
	if(k==0 && j==1)
		i=2;
	if(k==0 && j==2)
		i=3;
	if(k==0 && j==3)
		i=4;
	if(k==0 && j==4)
		i=5;
	if(k==1 && j==0)
		i=6;
	if(k==1 && j==1)
		i=7;
	if(k==1 && j==2)
		i=8;
	if(k==1 && j==3)
		i=9;
	if(k==1 && j==4)
		i=10;
	if(k==2 && j==0)
		i=11;
	if(k==2 && j==1)
		i=12;
	if(k==2 && j==2)
		i=13;
	if(k==2 && j==3)
		i=14;
	if(k==2 && j==4)
		i=15;
	if(k==3 && j==0)
		i=16;
	if(k==3 && j==1)
		i=17;
	if(k==3 && j==2)
		i=18;
	if(k==3 && j==3)
		i=19;
	if(k==3 && j==4)
		i=20;
	buttons[i].setForeground(Color.green);
		 buttons[i].setText("X");
		 
        buttons[i].setEnabled(false);
   }
   public static int getr(int j)
   {
   	int a=-1,b=-1;
   	switch (j)
		{
		 case 1:  a=0;
		          b=0;
        break;
case 2:  a=0;
b=1;
        break;
case 3:  a=0;
b=2;
        break;
case 4:  a=0;
b=3;
        break;
case 5:  a=0;
b=4;
        break;
case 6:  a=1;
b=0;
        break;
case 7:  a=1;
b=1;
        break;
case 8:  a=1;
b=2;
        break;
case 9:  a=1;
b=3;
        break;
case 10: a=1;
b=4;
        break;
case 11: a=2;
b=0;
        break;
case 12: a=2;
b=1;
        break;
case 13:  a=2;
b=2;
        break;
case 14:  a=2;
b=3;
        break;
case 15:  a=2;
b=4;
        break;
case 16:  a=3;
b=0;
        break;
case 17:  a=3;
b=1;
        break;
case 18:  a=3;
b=2;
        break;
case 19: a=3;
b=3;
        break;
case 20: a=3;
b=4;
        break;
        }
   	return a;
   }
   public static int getc(int j)
   {
   	int a=-1,b=-1;
   	switch (j)
		{
		case 1:  a=0;
        b=0;
break;
case 2:  a=0;
b=1;
break;
case 3:  a=0;
b=2;
break;
case 4:  a=0;
b=3;
break;
case 5:  a=0;
b=4;
break;
case 6:  a=1;
b=0;
break;
case 7:  a=1;
b=1;
break;
case 8:  a=1;
b=2;
break;
case 9:  a=1;
b=3;
break;
case 10: a=1;
b=4;
break;
case 11: a=2;
b=0;
break;
case 12: a=2;
b=1;
break;
case 13:  a=2;
b=2;
break;
case 14:  a=2;
b=3;
break;
case 15:  a=2;
b=4;
break;
case 16:  a=3;
b=0;
break;
case 17:  a=3;
b=1;
break;
case 18:  a=3;
b=2;
break;
case 19: a=3;
b=3;
break;
case 20: a=3;
b=4;
break;
}
        
   	return b;
   }
   
	public static void main(String[] args) throws IOException,
			CloneNotSupportedException {
		  new GameMain();
		 
	}
		public void AI(int r,boolean computerturn) throws IOException,CloneNotSupportedException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		
				int col=GameMain.getc(r);
		int rowRead=GameMain.getr(r);
		gameState.setBlack(rowRead, col);
		if(count==1){
			heuristic = new CustomHeuristic(config);
			alphaBetaPrunning = new AlphaBetaPrunning(
					lookupLevel, config, heuristic);
		}
		boolean notGameEnd = true;
		notGameEnd=wincheck( gameState, heuristic, connectN);
		
		//computer turn by calling alphabeta function
				if (computerturn && notGameEnd) {
					gameState = alphaBetaPrunning.alphaBeta(gameState);
					System.out.println("Board State after computer's move:");
					System.out.println(gameState);
					notGameEnd=wincheck( gameState, heuristic, connectN);
					
					computerturn = false;
				}
		}
		
		// check ending condition for the current state
		public boolean wincheck(GameState gameState,Heuristic heuristic,int connectN)
		{
			if (heuristic.isTerminal(gameState)) {
				int heuristicValue = heuristic.getHeuristicValue(gameState);
				if (heuristicValue > Math.pow(10, (connectN)))
					
					JOptionPane.showMessageDialog(null, "Computer Wins ");
				
			
				else if (heuristicValue == 0)
					JOptionPane.showMessageDialog(null, "The game is drawn. No more possibe moves.");
					
			
				else if (heuristicValue < -(Math.pow(10, (connectN))))
					JOptionPane.showMessageDialog(null, "You win ");
			
				else
					{
					
			
					}
			            return false;}
			return true;
		}
}
