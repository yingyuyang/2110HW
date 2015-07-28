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
}
