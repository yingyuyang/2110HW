import java.util.*;

public class Graph {
	ArrayList<Village> Villages;
	ArrayList<Road>Roads;
	
	//Constructors
	public Graph(){
	}
	//primary constructor
	public Graph(ArrayList<Village> Villages, ArrayList<Road> Roads){
		this.Villages = Villages;
		this.Roads = Roads;
		for (Road x: Roads){
			this.setRoad(x);
		}
	}
	

	//getters and setters for villages
	public void setVillage(Village village){
		Villages.add(village);
	}
	public ArrayList<Village> getVillages(){
		return Villages;
	}
	
	//getters and setters for roads
	//Every time a road is added to the graph, it updates the incoming and outgoing connections of the villages
	public void setRoad(Road road){
		Village fromvillage = road.getfrom();
		Village tovillage = road.getto();
		tovillage.getincoming().add(fromvillage);
		fromvillage.getoutgoing().add(tovillage);
	}
	//Every time a road is deleted, it also updates the incoming and outgoing connections of the villages
	public void deleteRoad(Road road){
		Village fromvillage = road.getfrom();
		Village tovillage = road.getto();
		Roads.remove(road);
		tovillage.getincoming().remove(fromvillage);
		fromvillage.getoutgoing().remove(tovillage);
	}
	public ArrayList<Road> getRoads(){
		return Roads;
	}
	
	public void printGraph(){
		for(Village x:Villages){
			x.printVillage();
		}
		for (Road x: Roads){
			x.printRoad();
		}
	}
	//Doesn't work for some reason... Have fun initializing everything on your own
	public void createGraph(){
		//Test Villages
		Village Village0 = new Village(0);
		Village Village1 = new Village(1);
		Village Village2 = new Village(2);
		Village Village3 = new Village(3);
		Village Village4 = new Village(4);

		
		//Test Roads
		Road Road01 = new Road("Road 0-1", Village0, Village1, 5);
		Road Road10 = new Road("Road 1-0", Village1, Village0, 5);
		Road Road12 = new Road("Road 1-2", Village1, Village2, 5);
		Road Road21 = new Road("Road 2-1", Village2, Village1, 5);
		Road Road02 = new Road("Road 0-2", Village0, Village2, 5);
		Road Road20 = new Road("Road 1-0", Village2, Village0, 5);
		Road Road13 = new Road("Road 1-3", Village1, Village3, 5);
		Road Road31 = new Road("Road 3-1", Village3, Village1, 5);
		Road Road34 = new Road("Road 3-4", Village3, Village4, 5);
		Road Road43 = new Road("Road 4-3", Village4, Village3, 5);
		
		//Test Graph
		Villages = new ArrayList<Village>();
		Villages.add(Village0);
		Villages.add(Village1);
		Villages.add(Village2);
		Villages.add(Village3);
		Villages.add(Village4);
		
		
		Roads = new ArrayList<Road>();
		Roads.add(Road01);
		//Roads.add(Road10);
		//Roads.add(Road12);
		//Roads.add(Road21);
		Roads.add(Road02);
		//Roads.add(Road20);
		Roads.add(Road13);
		//Roads.add(Road31);
		Roads.add(Road34);
		//Roads.add(Road43);
		
		
	}
}
