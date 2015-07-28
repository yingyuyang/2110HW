import java.util.*;

public class Graph {
	ArrayList<Village> Villages;
	ArrayList<Road>Roads;
	
	//getters and setters for villages
	public void setVillage(Village village){
		Villages.add(village);
	}
	public ArrayList<Village> getVillages(){
		return Villages;
	}
	
	//getters and setters for roads
	//everytime a road is added to the graph, it updates the incoming and outgoing connections of the villages
	public void setRoad(Road road){
		Roads.add(road);
		Village fromvillage = road.getfrom();
		Village tovillage = road.getto();
		tovillage.getincoming().add(fromvillage);
		fromvillage.getoutgoing().add(tovillage);
	}
	//everytime a road is deleted, it also updates the incoming and outgoing connections of the villages
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
	
	//primary constructor
	public void Graph(ArrayList<Village> x, ArrayList<Road> y){
		Villages = x;
		Roads = y;
	}
}
