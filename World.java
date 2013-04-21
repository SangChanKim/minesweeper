import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class World extends JPanel implements ActionListener{
	
	private Grid grid; 
	private static JButton squares[][];
	
	public World(int numRow, int numCol){
		grid = new Grid(numRow, numCol); 
	    this.setSize(1000,1000);
	    this.setLayout(new GridLayout(numRow,numCol));
	    squares = new JButton[numRow][numCol];
	    buildCells();
	}
	
	private void buildCells(){
	     for(int i = 0; i < grid.getNumRow(); i++){
	          for(int j = 0;j < grid.getNumCol(); j++){
	               squares[i][j] = new JButton();
	               squares[i][j].setSize(400,400);
	               squares[i][j].addActionListener(this); 
	               this.add(squares[i][j]);
	          }
	     }
	}
	
	public Grid getGrid(){
		return this.grid; 
	}
	
	
	public static void main(String[] args){
		
		World world = new World(9,9);
		JFrame frame = new JFrame("Minesweeper");
		frame.add(world);
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		//find the button that was clicked
		
		int row = -1;
		int col = -1; 
		JButton sourceButton = (JButton) arg0.getSource(); 
		for(int i = 0; i < this.getGrid().getNumRow(); i++){
			for(int j = 0; j < this.getGrid().getNumCol(); j++){
				if(sourceButton.equals(squares[i][j])){
					row = i;
					col = j; 
					break; 
				}
			}
		}
		
		Location sourceButtonLoc = new Location(row, col); 
		
		//perform game logic on it
		this.getGrid().setAllNum(); 
		this.grid.revealCells(new Location(row, col)); 
		
		//check the current button for mine
		if(this.grid.get(sourceButtonLoc).hasMine()){
			squares[row][col].setEnabled(false); 	
			for(int i = 0; i < squares.length; i++){
				for(int j = 0; j < squares[0].length; j++){
					squares[i][j].removeActionListener(this); 
					if(squares[i][j].isEnabled()){
						if(this.grid.get(new Location(i,j)).hasMine()){
							this.grid.get(new Location(i,j)).setVis(true); 
						}
					}
					
				}
		    }
		}
		
		//draw all the cells
		for(int i = 0; i < this.getGrid().getNumRow(); i++){
			for(int j = 0; j < this.getGrid().getNumCol(); j++){
				Location currentLoc = new Location(i, j);
				
				if(this.grid.get(currentLoc).hasMine()){
					if(this.grid.get(currentLoc).isVisible()){
						squares[i][j].setEnabled(false); 
						squares[i][j].setText("+"); 
					}
				}
				else{
					if(this.grid.get(currentLoc).isVisible()){
						squares[i][j].setEnabled(false);
						if(this.grid.get(currentLoc).getNum() != 0){
							squares[i][j].setText("" + grid.get(currentLoc).getNum()); 
						}
					}
				}
			}
		}
	}
}
