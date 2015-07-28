import java.util.*;

public class DAlg {
	ArrayList<Village>Villages;
	ArrayList<Road> Roads;
	
	HashSet<Village> checkedV;
	HashSet<Village> frontierV;
	HashMap<Village, Integer> distances;
	HashMap<Village, Village> savepath;
	
	//Constructor
	public DAlg(Graph x){
		this.Villages = x.getVillages();
		this.Roads = x.getRoads();
		
		checkedV = new HashSet<Village>();
		frontierV = new HashSet<Village>();
		distances = new HashMap<Village, Integer>();
		savepath = new HashMap<Village, Village>();
	}
	
	//Getters and setters
	public HashMap<Village, Integer> getdistances(){
		return distances;
	}
	public HashSet<Village> getfrontierV(){
		return frontierV;
	}
	public HashSet<Village> getcheckedV(){
		return checkedV;
	}
	public HashMap<Village, Village>getsavepath(){
		return savepath;
	}
	//Print statements for debugging
	public void printfrontierV(){
		for (Village x: frontierV){
			x.printVillage();
		}
	}
	public void printcheckedV(){
		for (Village x: checkedV){
			x.printVillage();
		}
	}
	public static void printpath(LinkedList<Village> Villages){
		for (Village x: Villages){
			x.printVillage();
		}
	}

	//Methods for DAlg
	public void start(Village startV, Village endV){
		checkedV = new HashSet<Village>();
		frontierV = new HashSet<Village>();
		distances = new HashMap<Village, Integer>();
		savepath = new HashMap<Village, Village>();
		
		checkedV.add(startV); //initializes frontier
		frontierV.add(startV);
		distances.put(startV, 0);
		
		while (!checkedV.contains(endV)){//as long as the destination is not met, keep on appending to checked and change distance
			Village loopedV = closest();//picks closest Village
			shortestpathtoeachV(loopedV); //updates distance to surrounding, adds to frontier
			checkedV.add(loopedV);//adds to checkedV;
		}
	}
		
	//adds to distances the shortest path to each Village
	public void shortestpathtoeachV(Village fromV){
		ArrayList<Village> surroundingV = fromV.getoutgoing();
		for (Village x: surroundingV){//Village1 is surrounding
			if (shortest(x) > shortest(fromV) + getroaddistance(fromV, x)){ //for X in surroundingV, if getDistance is smaller than the current Distance, replace it.
				distances.put(x, shortest(fromV) + getroaddistance(fromV, x));
				savepath.put(x, fromV);
				frontierV.add(x);
				frontierV.remove(fromV);
			}
		}
	}
	//returns distance to village
	public int shortest(Village village){
		int shortest;
		if (!distances.containsKey(village)){
			shortest = 999999999;
		}
		else{
			shortest = distances.get(village);
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
	
	//returns the next closest village
	public Village closest(){//checks from frontier villages and returns closest village;
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
		start(start, end);
		Village previous = new Village();
		previous = end;
		path.add(previous);
		while (savepath.get(previous) != null){
			path.add(savepath.get(previous));
			previous = savepath.get(previous);
		}
		return path;	
	}
	public static LinkedList<Village> reverse(LinkedList<Village> Villages){//why won't it override saved?  Nvrmind; it works! 
		if(Villages.isEmpty()){
			return Villages;
		}
		else{
			Village nextstep = Villages.remove();
			LinkedList x = reverse(Villages);
			Villages.add(nextstep);
			return Villages;
		}
	}
}