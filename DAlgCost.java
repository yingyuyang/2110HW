import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class DAlgCost {
	ArrayList<Village>Villages;
	ArrayList<Road> Roads;
	
	HashSet<Village> checkedV;
	HashSet<Village> frontierV;
	HashMap<Village, Integer> costs;
	HashMap<Village, Village> savepath;
	
	//Constructor
	public DAlgCost(Graph x){
		this.Villages = x.getVillages();
		this.Roads = x.getRoads();
		
		checkedV = new HashSet<Village>();
		frontierV = new HashSet<Village>();
		costs = new HashMap<Village, Integer>();
		savepath = new HashMap<Village, Village>();
	}
	
	//Getters and setters
	public HashMap<Village, Integer> getcosts(){
		return costs;
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
		costs = new HashMap<Village, Integer>();
		savepath = new HashMap<Village, Village>();
		
		checkedV.add(startV); //initializes frontier
		frontierV.add(startV);
		costs.put(startV, 0);
		
		while (!checkedV.contains(endV)){//as long as the destination is not met, keep on appending to checked and change cost
		//while (!frontierV.isEmpty()){	
			Village nextV = this.closest();//picks closest Village
			shortestpathtoeachV(nextV); //updates costs to surrounding, adds to frontier
			checkedV.add(nextV);//adds to checkedV;
			frontierV.remove(nextV);
		}
	}
		
	//adds to cost the shortest path to each Village
	public void shortestpathtoeachV(Village fromV){
		ArrayList<Village> surroundingV = fromV.getoutgoing();
		for (Village x: surroundingV){//Village1 is surrounding
			if (cheapest(x) > cheapest(fromV) + getroadcost(fromV, x)){ //for X in surroundingV, if getCost is smaller than the current Cost, replace it.
				costs.put(x, cheapest(fromV) + getroadcost(fromV, x));
				savepath.put(x, fromV);
				frontierV.add(x);
			}
		}
	}
	//returns cost to village
	public int cheapest(Village village){
		int shortest;
		if (!costs.containsKey(village)){
			shortest = 999999999;
		}
		else{
			shortest = costs.get(village);
		}
		return shortest;
	}
	
	//need to change this
	//returns the cost of the road given the two villages they connect;
	public int getroadcost(Village fromV, Village toV){ //why is it that when you throw an exception, you no longer have to meet the specifications
		for (Road x: Roads){
			if (x.getfrom() == fromV && x.getto() == toV){
				return x.getCost();
			}
		}
		throw new RuntimeException("Road does not exist");
	}
	
	//returns the next closest village
	public Village closest(){//checks from frontier villages and returns closest village;
		Village closestV = new Village();
		for (Village x: frontierV){
			if (costs.get(closestV) == null){
				closestV = x;
			}
			if (costs.get(x) < costs.get(closestV)){//this will throw null;
				closestV = x;
			}
		}
		return closestV;
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
		return reverse(path);	
	}
	public static void main(String[]Args){
		
	}
}