import java.util.*;

public class Road {
	private String roadID; //not sure if exactly needed;
	private Village from;
	private Village to;
	private int weight;
	
	//Constructors
	
	public Road(){
	}
	public Road(String a, Village b, Village c, int d){
		roadID = a;
		from = b;
		to = c;
		weight = d;
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
	public void setweight(int x){
		weight = x;
	}
	public int getweight(){
		return weight;
	}
	public void printRoad(){
		System.out.println("The road is between nodes: " + roadID);
	}
}
