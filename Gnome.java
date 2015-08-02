import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Gnome implements Runnable {
	//private static final int STARTING_ID = 10000;
	//private static int numMade = 0;
	//private int id;
	private String name;

	private Village currentV;
	private Village finalV;
	private ArrayList<Village> history;

	private Graph graph;
	private boolean urgency;
	//create a hashMap for holding all the gnomes. 
	static private HashMap<String, Gnome> hm= new HashMap<String, Gnome>();

	//constructor
	public Gnome(String name, Village currentV, Graph graph){
		this.name = name;
		this.currentV = currentV;
		this.finalV = null;
		this.history = new ArrayList<Village>();
		this.graph = graph;
		this.urgency = false;
		hm.put(name, this);
	}
	public Gnome(String name, Village currentV, Village finalV, Graph graph){
		this.name = name;
		this.currentV = currentV;
		this.finalV = finalV;
		this.history = new ArrayList<Village>();
		this.graph = graph;
		this.urgency = false;
		hm.put(name, this);
	}


	//private int createID() {
	// return STARTING_ID + numMade++;
	//}
	public void setfinalV(Village x){
		finalV = x;
	}
	public Village getfinalV(){
		return finalV;
	}
	public void setcurrentV(Village x){
		currentV = x;
	}
	public Village getcurrentV(){
		return currentV;
	}
	public void setUrgency(boolean x){
		urgency = x;
	}
	public boolean getUrgency(){
		return urgency;
	}
	public String getName() {
		return this.name;
	}
	public ArrayList<Village> getHistory() {
		return this.history;
	}
	/***Moves Gnome directly to final destination via shortest path*/
	public void haulass(){
		DAlg DAlg = new DAlg(graph);
		LinkedList<Village> path = DAlg.path(currentV, finalV);
		try {
			for (Village x: path){
				Thread.sleep(1000);
				currentV = x;
				currentV.printVillage();
				history.add(currentV);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void run() {
		while (finalV == null){
			try {
				Thread.sleep(1000);
				int number = currentV.getoutgoing().size();
				int randomNum = (int) (number*Math.random()+0);
				currentV = currentV.getoutgoing().get(randomNum);     
				history.add(currentV);
				currentV.printVillage();
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		haulass();
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
		Roads.add(Road10);
		Roads.add(Road12);
		Roads.add(Road21);
		Roads.add(Road02);
		Roads.add(Road20);
		Roads.add(Road13);
		Roads.add(Road31);
		Roads.add(Road34);
		Roads.add(Road43);
		Roads.add(Road15);
		Roads.add(Road51);
		Roads.add(Road25);
		Roads.add(Road52);

		Graph graph = new Graph(Villages, Roads);
		/////////////////////////////////////////////////////////////////////////////////////
		///////////////////INITIALIZES EVERYTHING////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////

		Gnome Gnome1 = new Gnome("Gnome1", Village0, graph);
		Gnome1.setfinalV(Village4);
		Thread Thread1 = new Thread(Gnome1); 
		Thread1.start();

//		Gnome Gnome2 = new Gnome("Gnome2", Village5, graph);
//		Gnome2.setfinalV(Village4);
//		Thread Thread2 = new Thread(Gnome2); 
//		Thread2.start();
		
		try {
			Thread.sleep(5000);
			searchGnome(Gnome1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**Search for a given gnome, locating their current position and acquiring a history
	 * of the places that this gnome has visited. Use HashMap. */
	public static void searchGnome(Gnome g) {
		String key = g.getName();
		Gnome findG = hm.get(key);
		System.out.println("The gnome " + g.getName() + " is currently in " + 
				findG.getcurrentV());
		System.out.println("And this gnome has visited: " + g.getHistory());
	}
}
