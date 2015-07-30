import java.util.*;

public class MinTree {
	Graph graph; // current roads should be zero at start
	ArrayList<Road> allpossibleRoads = new ArrayList<Road>();
	public MinTree(Graph graph, ArrayList<Road> allpossibleRoads){
		this.graph = graph;
		this.allpossibleRoads = allpossibleRoads;
	}
	//In order to avoid cycles, you can run a Dijkstra's Algorithm that 
	//checks if your village is ever in the "to" parameter of another road other than the road pointing back. 
	
	public void mergesort(){
		
	}
	
	
	public void addsingleRoad(){
		Road road;
		for(Road x: allpossibleRoads){
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
