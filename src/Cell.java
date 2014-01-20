
public class Cell {
	private Location loc; 
	private boolean isVisible = false; 
	private boolean hasMine = false;
	private boolean flagState = false; 
	
	private int num = 0; 
	
	public Cell(boolean vis, boolean hasMine, Location loc){
		this.loc = new Location(loc.getRow(), loc.getCol()); 
		setVis(vis);
		setMineState(hasMine);
	}
	
	public int getNum(){
		return this.num; 
	}
	
	public boolean isFlagPlaced(){
		return this.flagState; 
	}
	
	public Location getLocation(){
		return this.loc; 
	}
	
	public boolean isVisible(){
		return this.isVisible;
	}
	
	public boolean hasMine(){
		return this.hasMine; 
	}
	
	public void setVis(boolean vis){
		this.isVisible = vis; 
	}
	
	public void setMineState(boolean state){
		this.hasMine = state; 
	}
	
	public void setFlagState(boolean flagState){
		this.flagState = flagState; 
	}
	
	public void setNum(int num){
		this.num = num; 
	}
}
