import java.util.*;

public class DAlgorithm {
	ArrayList<Village>Villages;
	ArrayList<Road> Roads;
	
	Set<Village> checkedV;
	Set<Village> frontierV;
	Map<Village, Integer> distances;
	Map<Village, Village> savepath;
	
	public void DAlgorithm(Graph x){
		this.Villages = x.getVillages();
		this.Roads = x.getRoads();
	}
	
	public void putStart(Village startV, Village endV){
		checkedV = new HashSet<Village>();
		frontierV = new HashSet<Village>();
		distances = new HashMap<Village, Integer>();
		savepath = new HashMap<Village, Village>();
		
		checkedV.add(startV); //initializes frontier
		frontierV.add(startV);
		distances.put(startV, 0);
		
		while (!checkedV.contains(endV)){//as long as the destination is not met, keep on appending to checked and change distance
			Village loopedV = closest(frontierV);//picks closest Village
			shortestpathtoeachV(loopedV); //updates distance to surrounding, adds to frontier
			checkedV.add(loopedV);//adds to checkedV;
			frontierV.remove(loopedV);//removes from frontier
		}
	}
		
	//adds to distances the shortest path to each Village
	public void shortestpathtoeachV(Village fromV){
		ArrayList<Village> surroundingV = fromV.getoutgoing();
		for (Village x: surroundingV){
			if (shortest(x) > + shortest(x) + getroaddistance(fromV, x)){ //for X in surroundingV, if getDistance is smaller than the current Distance, replace it.
				distances.put(x, shortest(fromV) + getroaddistance(fromV, x));
				savepath.put(x, fromV);
				frontierV.add(x);
			}
		}
	}
	//returns distance to village
	public int shortest(Village village){
		Integer shortest = distances.get(village);
		if (shortest == null){
			shortest = 2^31-1;
		}
		return shortest;
	}
	//returns the distance of the road given the two villages they connect;
	public int getroaddistance(Village fromV, Village toV){ //why is it that when you throw an exception, you no longer have to meet the specifications
		for (Road x: Roads){
			if (x.getfrom() == fromV && x.getto() == toV){
				return x.getweight();
			}
		}
		throw new RuntimeException("Road does not exist");
	}
	
	//returns next the closest village
	public Village closest(Set<Village> frontierV){//checks from frontier villages and returns closest village;
		Village closestV = new Village();
		for (Village x: frontierV){
			if (distances.get(closestV) == null){
				closestV = x;
			}
			if (distances.get(x) < distances.get(closestV)){//this will throw null;
				closestV = x;
			}
		}
		return closestV;
	}
	
	public LinkedList<Village> path(Village start, Village end){
		LinkedList<Village> path = new LinkedList<Village>();
		putStart(start, end);
		Village previous = new Village();
		previous = end;
		path.add(previous);
		while (savepath.get(previous) != null){
			path.add(savepath.get(previous));
			previous = savepath.get(previous);
		}
		return path;	
	}
}


