import java.util.*;

public class Village {//utilizing adjacency matrix will not allow me to change the dimensions of the graph easily;
	int ID;
	int coordinate;
	private ArrayList<Village>outgoing;
	private ArrayList<Village>incoming;
	
	public Village(){
	}
	
	public Village(int ID){
		this.ID = ID;
		outgoing = new ArrayList<Village>();
		incoming = new ArrayList<Village>();
		coordinate = 0;
	}
	
	public Village(int ID, int coordinate){
		this.ID = ID;
		outgoing = new ArrayList<Village>();
		incoming = new ArrayList<Village>();
		this.coordinate = coordinate;
	}
	
	//getters and setters for ID, outgoing connections, and incoming connections
	public void setID(int x){
		ID = x;
	} 
	public int getID(){
		return ID;
	}
	
	public ArrayList<Village> getoutgoing(){
		return outgoing;
	}
	public ArrayList<Village> getincoming(){
		return incoming;
	}
	
	public boolean is(Object x){
		if (this == x){return true;}
		if (x == null){return false;}
		else{ return false; }
	}
	public void printVillage(){
		System.out.println("This Village ID is: " + ID);
	}
	public void printoutgoing(){
		for (Village x: outgoing){
			x.printVillage();
		}
	}
	public void printincoming(){
		for (Village x: incoming){
			x.printVillage();
		}
	}
	public void setcoordinate(int x){
		this.coordinate = x;
	}
	public int getcoordinate(){
		return this.coordinate;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
