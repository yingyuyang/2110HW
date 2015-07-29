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
	//Add Villages
	TestDAlg testDAlg = new TestDAlg();
	Village Village0 = new Village(0);
	Village Village1 = new Village(1);
	Village Village2 = new Village(2);
	
	//Add Roads
	Road Road01 = new Road("21", Village2, Village1, 5);
	Road Road12 = new Road("10", Village1, Village0, 7);
	
	//Add Graph
	ArrayList<Village> Villages = new ArrayList<Village>();
	Villages.add(Village0);
	Villages.add(Village1);
	Villages.add(Village2);
	
	ArrayList<Road> Roads = new ArrayList<Road>();
	Roads.add(Road01);
	Roads.add(Road12);
	
	Graph graph = new Graph(Villages, Roads);
	
	//Adds graph to TopoSort
	TopoSort TopoSort = new TopoSort(graph);

	System.out.println("TopologicalSort: Correct (Should be 2,1,0)");
	printSorted(TopoSort.Sort());
	}

}
