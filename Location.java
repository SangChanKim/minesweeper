public class Location {
	private int row;
	private int col; 

	private int[] directions = {0, 45, 90, 135, 180, 225, 270, 315};
	
	public Location(int row, int col){ 
		this.row = row;
		this.col = col; 
	}
	
	public Location getAdjacentLocation(int dir){
		Location adjacentLoc; 
		if(dir == 0 || dir == 360){
			adjacentLoc = new Location(this.getRow()-1, this.getCol());
		}
		else if(dir == 45){
			adjacentLoc = new Location(this.getRow()-1, this.getCol()+1); 
		}
		else if(dir == 90){
			adjacentLoc = new Location(this.getRow(), this.getCol()+1); 
		}
		else if(dir == 135){
			adjacentLoc = new Location(this.getRow()+1, this.getCol()+1); 
		}
		else if(dir == 180){
			adjacentLoc = new Location(this.getRow()+1, this.getCol()); 
		}
		else if(dir == 225){
			adjacentLoc = new Location(this.getRow()+1, this.getCol()-1);
		}
		else if(dir == 270){
			adjacentLoc = new Location(this.getRow(), this.getCol()-1); 
		}
		else if(dir == 315){
			adjacentLoc = new Location(this.getRow()-1, this.getCol()-1); 
		}
		else{
			throw new IllegalStateException("Not a valid direction!"); 
		}
		return adjacentLoc; 
	}
	
	public int[] getAllDirections(){
		return directions; 
	}
	
	public int getRow(){
		return row; 
	}
	
	public int getCol(){
		return col; 
	}
	

}
