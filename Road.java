import java.util.*;

public class Road {
	private String roadID; //not sure if exactly needed;
	private Village from;
	private Village to;
	private int length = 0;
	private int cost = 0;
	
	//Constructors
	
	public Road(){
	}
	public Road(String a, Village b, Village c, int d){
		roadID = a;
		from = b;
		to = c;
		length = d;
	}
	public Road(String a, Village b, Village c, int d, int e){
		roadID = a;
		from = b;
		to = c;
		length = d;
		cost = e;
	}
	
	//getters and setter for the ID, from, to, and weight
	public void setfrom(Village x){
		from = x;
	}
	public Village getfrom(){
		return from;
	}
	public void setto(Village x){
		to = x;
	}
	public Village getto(){
		return to;
	}
	public void setLength(int x){
		length = x;
	}
	public int getLength(){
		return length;
	}
	public void setCost(int x){
		cost = x;
	}
	public int getCost(){
		return cost;
	}
	public void printRoad(){
		System.out.println("The road is between nodes: " + roadID);
	}
}
