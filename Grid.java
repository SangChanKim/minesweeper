import java.util.ArrayList;
import java.util.Random;


public class Grid {
	
	private int numRow; 
	private int numCol; 
	private Cell[][] grid; 
	
	/*
	 * Grid constructor
	 * Random mine distribution algorithm 
	 * parameter- numRow & numCol- the number of rows & columns on the grid
	 */
	public Grid(int numRow, int numCol){
		
		this.numRow = numRow;
		this.numCol = numCol;
		
		this.grid = new Cell[this.numRow][this.numCol];
		for(int i = 0; i < this.numRow; i++){
			for(int j = 0; j < this.numCol; j++){
				grid[i][j] = new Cell(false, false, new Location(i, j)); 
			}
		}
		
		Random r = new Random(); 
		int numMines = 10; 
		
		/*
		 *  iterate through the grid array until all mines have been placed 
		 *  There is a 1/8 chance of a mine placement
		 */
		
		while(numMines > 0){
			for(int i = 0; i < this.numRow; i++){
				for(int j = 0; j < this.numCol; j++){
					if(numMines > 0){
						int place_mine = r.nextInt(8); 
						if(place_mine == 0){ 
							if(!grid[i][j].hasMine()){ 
								int mine_count = getNumMinesNeighbor(new Location(i,j));  
								if(mine_count <= 1){
									grid[i][j].setMineState(true); 
									numMines--; 
								}
							}
						}
					}
					else{
						break; 
					}
				}	
			}
		}
	}
	
	/*
	 * Returns the appropriate instance of a Cell object from the specified location
	 * parameter- location that the program should look for
	 * return- Cell object that is located at the specified location
	 */
	public Cell get(Location loc){
		int targetRow = loc.getRow();
		int targetCol = loc.getCol(); 
		return grid[targetRow][targetCol];
	}

	/*
	 * Returns the number of rows on the grid
	 */
	public int getNumRow(){
		return numRow; 
	}
	
	/*
	 * Returns the number of columns on the grid
	 */
	public int getNumCol(){
		return numCol; 
	}
	
	/*
	 * A method to easily set the visibility of all cells
	 * parameter- the visibility of the cells
	 */
	public void setAllVis(boolean vis){
		for(int i = 0; i < this.numRow; i++){
			for(int j = 0; j < this.numCol; j++){
				this.get(new Location(i,j)).setVis(vis); 
			}
		}
	}
	
	/*
	 * checks to whether or not a location is within grid dimensions
	 * parameter- Location loc to check
	 * return- boolean statement that represents a validity of loc
	 */
	public boolean isValid(Location loc){
		boolean isRowValid = loc.getRow() >= 0 && loc.getRow() < this.getNumCol(); 
		boolean isColValid = loc.getCol() >= 0 && loc.getCol() < this.getNumCol(); 
		return isRowValid && isColValid; 
	}
	
	
	/*
	 * 
	 * Gets all 8 valid neighbors around a Location
	 * parameter-  Location loc to look around
	 * return- an ArrayList of valid Cells that neighbor loc
	 * 
	 */
	public ArrayList<Cell> getNeighbors(Location loc){
		ArrayList<Cell> allNeighbors = new ArrayList<Cell>(); 
		int[] allDir = loc.getAllDirections(); 
		
		for(int dir: allDir){
			if(this.isValid(loc.getAdjacentLocation(dir))){
				allNeighbors.add(this.get(loc.getAdjacentLocation(dir))); 
			}
		}
		return allNeighbors; 
	}
	
	/*
	 * Gets all the neighboring cells that are not visible
	 * parameter- the location of the cell
	 * return- an ArrayList containing neighboring cells that are not visible
	 */
	public ArrayList<Cell> getInvisibleNeighbors(Location loc){
		ArrayList<Cell> allNeighbors = this.getNeighbors(loc);
		ArrayList<Cell> coveredNeighbors = new ArrayList<Cell>(); 
		
		for(Cell cell: allNeighbors){
			if(!cell.isVisible()){
				coveredNeighbors.add(cell); 
			}
		}
		return coveredNeighbors; 
	}
	
	
	
	public int getNumMinesNeighbor(Location loc){
		ArrayList<Cell> allNeighbors = getNeighbors(loc); 
		int mineCount = 0; 
		for(Cell cell: allNeighbors){
			if(cell.hasMine()){
				mineCount++; 
			}
		}
		return mineCount; 
	}
	
	public int getNumMinesOnGrid(){
		int mineCount = 0; 
		for(int i = 0; i < this.grid.length; i++){
			for(int j = 0; j < this.grid[0].length; j++){
				Cell cellToCheck = this.get(new Location(i, j));
				if(cellToCheck.hasMine()){
					mineCount++; 
				}
			}
		}
		return mineCount; 
	}
	
	public void revealCells(Location loc){
		this.get(loc).setVis(true);
		if(this.get(loc).hasMine()){	
			return; 
		}
		else{
		    if(this.getNumMinesNeighbor(loc) == 0){
		    	ArrayList<Cell> cellsToReveal = this.getInvisibleNeighbors(loc); 
		    	for(Cell cell: cellsToReveal){
		    		revealCells(cell.getLocation()); 
		    	}
		    }
			
		}
	}
	
	public void setAllNum(){
		for(int i = 0; i < this.numRow; i++){
			for(int j = 0; j < this.numCol; j++){
				if(!this.get(new Location(i,j)).hasMine()){
					int mineCount = this.getNumMinesNeighbor(new Location(i,j)); 
					this.get(new Location(i,j)).setNum(mineCount); 
				}
			}
		}
	}
	
}
