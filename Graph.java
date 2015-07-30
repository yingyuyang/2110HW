import java.util.*;

public class Graph<E> {
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
	
	//prints statements for debugging
	public void printGraph(){
		for(Village x:Villages){
			x.printVillage();
		}
		for (Road x: Roads){
			x.printRoad();
		}
	}
	public void printArrayListVillage(ArrayList<Village> Villages){
		System.out.println("The Villages in the ArrayList are: ");
		for (Village x: Villages){
			x.printVillage();
		}
	}
	public void printArrayListRoad(ArrayList<Road> Roads){
		System.out.println("The Roads in the ArrayList are: ");
		for (Road x: Roads){
			x.printRoad();
		}
	}
	
	//deletes the village and the roads pointing to and from that village
	public void deleteVillageandRoads(Village Village){
		ArrayList<Road> savetodeleteRoads = new ArrayList<Road>();
		Villages.remove(Village);
		for (Road x: Roads){
			if(Village == x.getfrom()){
				savetodeleteRoads.add(x);
			}
			if (Village == x.getto()){
				savetodeleteRoads.add(x);
			};
		}
		for (Road y: savetodeleteRoads){
			Roads.remove(y);
		}
	}
	
	//deletes the village and creates roads pointing from villages that point to it 
	//and villages that point away form it
	public void deleteVillageOnly(Village Village){
		ArrayList<Village> saveto = Village.getoutgoing();
		System.out.println("The villages after this village are: ");
		printArrayListVillage(saveto);
		ArrayList<Village> savefrom = Village.getincoming();
		System.out.println("The villages before this village are: ");
		printArrayListVillage(savefrom);
		
		deleteVillageandRoads(Village);
		//initialize roads
		for (Village x: savefrom){
			for (Village y: saveto){
				Road Road = new Road("Road " + x.getID() + "-" + y.getID(), x, y, 0);
				Road.printRoad();
				this.setRoad(Road);//deletes the road and adds new road
				Roads.add(Road);
				printArrayListRoad(Roads);
			}
		}
	}
}
