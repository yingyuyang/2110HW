import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class TopoSort {
	private ArrayList<Village> Villages;
	private ConcurrentHashMap<Village, Integer> villageIncoming;
	
	//Constructors
	public TopoSort(){
	}
	
	public TopoSort (Graph graph){
		Villages = graph.getVillages();
		villageIncoming = new ConcurrentHashMap<Village, Integer>();
		for (Village x: Villages){
			villageIncoming.put(x, x.getincoming().size());
		}
	}
	
	//Getters and setters
	public ArrayList<Village> getVillages(){
		return Villages;
	}
	public void printVillages(){
		for (Village x: Villages){
			x.printVillage();
		}
	}
	public ConcurrentHashMap<Village, Integer> getvillageIncoming(){
		return villageIncoming;
	}
	public void printvillageIncoming(){
		System.out.println("The key sets are: ");
		for (Village x: villageIncoming.keySet()){
			x.printVillage();;
		}	
		System.out.println("The value sets are: ");
		for (Integer x: villageIncoming.values()){
			System.out.println(x);
		}
	}
	
	//Implements topological sort via HashMap
	//will throw RuntimeException if there is a cycle in the graph
	public ArrayList<Village> Sort(){
		ArrayList<Village> Sorted = new ArrayList<Village>();
		while (villageIncoming.containsValue(0)){
			for (Village x: villageIncoming.keySet()){
				if (villageIncoming.get(x) == 0){
					villageIncoming.remove(x);
					for (Village y: x.getoutgoing()){
						villageIncoming.put(y, villageIncoming.get(y) - 1);
					}
					Sorted.add(x);
				}
			}
		}
		if (!villageIncoming.containsValue(0)){
			if (!villageIncoming.isEmpty()){
				throw new RuntimeException("There is a cycle in the graph");
			}
		}
		return Sorted;
	}
	public static void printSorted(ArrayList<Village> Villages){
		for (Village x: Villages){
			x.printVillage();
		}
	}
	
	public static void main(String[] args) {
		/////////////////////////////////////////////////////////////////////////////////////
		///////////////////INITIALIZES EVERYTHING////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		//Test Villages
		Village Village0 = new Village(0);
		Village Village1 = new Village(1);
		Village Village2 = new Village(2);
		Village Village3 = new Village(3);
		Village Village4 = new Village(4);
		Village Village5 = new Village(5);
		
		//Test Roads
		Road Road01 = new Road("Road 0-1", Village0, Village1, 5, 0);
		Road Road10 = new Road("Road 1-0", Village1, Village0, 5, 0);
		Road Road12 = new Road("Road 1-2", Village1, Village2, 5, 0);
		Road Road21 = new Road("Road 2-1", Village2, Village1, 5, 0);
		Road Road02 = new Road("Road 0-2", Village0, Village2, 5, 0);
		Road Road20 = new Road("Road 1-0", Village2, Village0, 5, 0);
		Road Road13 = new Road("Road 1-3", Village1, Village3, 5, 0);
		Road Road31 = new Road("Road 3-1", Village3, Village1, 5, 0);
		Road Road34 = new Road("Road 3-4", Village3, Village4, 5, 0);
		Road Road43 = new Road("Road 4-3", Village4, Village3, 5, 0);
		Road Road15 = new Road("Road 1-5", Village1, Village5, 2, 0);
		Road Road51 = new Road("Road 5-1", Village5, Village1, 2, 0);
		Road Road25 = new Road("Road 2-5", Village2, Village5, 5, 10);
		Road Road52 = new Road("Road 5-5", Village5, Village2, 5, 10);
		
		//Test Graph
		ArrayList<Village> Villages = new ArrayList<Village>();
		Villages.add(Village0);
		Villages.add(Village1);
		Villages.add(Village2);
		Villages.add(Village3);
		Villages.add(Village4);
		Villages.add(Village5);
		
		ArrayList<Road> Roads = new ArrayList<Road>();
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
		Roads.add(Road15);
		//Roads.addAll(Road51);
		Roads.add(Road25);
		//Roads.add(Road52);
		
	Graph graph = new Graph(Villages, Roads);
	/////////////////////////////////////////////////////////////////////////////////////
	///////////////////INITIALIZES EVERYTHING////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	//Adds graph to TopoSort
	TopoSort TopoSort = new TopoSort(graph);
	System.out.println("_____________________________________________________");
	System.out.println("TopologicalSort: Correct (Should be 2,1,0)");
	printSorted(TopoSort.Sort());
	}

}
