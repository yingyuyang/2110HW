import java.util.LinkedList;

public class Gnome implements Runnable {
	private static final int STARTING_ID = 10000;
	private static int numMade = 0;
	private int id;
	private String name;
	private boolean Urgent = false; 
	//False means the gnome is in a lazy mode. True means in an urgent(desperate) mode. 

	private Village finalV; //the final destination for this gnome
	private Village currentV;//the current village the the gnome resides 
	private LinkedList<Village> visitedV; //a history of the places the gnome has visited
	private Graph graph;
	private DAlg d = new DAlg();
	
	/**Create a unique ID for each Gnome, starting from 10000.*/
	private int createID() {
		return STARTING_ID + numMade++;
	}
	
	//setters and getters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name; 
	}
	
	public void setUrgency(boolean x) {
		this.Urgent = x;
	}
	public boolean getUrgency() {
		return this.Urgent;
	}
	
	public void setfinalV(Village x) {
		this.finalV = x;
	}
	public Village getfinalV() {
		return this.finalV;
	}
	
	public void setcurrentV(Village x) {
		this.currentV = x;
	}
	public Village getcurrentV() {
		return this.currentV;
	}
	
	//Constructors
	/**Constr: An instance of Gnome. */
	public Gnome() {
	}
	
	/**Constr: An instance of Gnome with a name and a unique ID.*/
	public Gnome(String name) {
		this.name = name;
		setId(createID());
	}
	
	/**Constr: An instance of Gnome with name, ID, final Village and current Village.*/
	public Gnome(String name, Village fv, Village cv) {
		this.name = name;
		this.finalV = fv;
		this.currentV = cv;
	}

	/**Constr: An instance of Gnome with name, ID, final/current Village and a mode.*/
	public Gnome(String name, Village fv, Village cv, boolean mode, Graph graph) {
		this.name = name;
		this.finalV = fv;
		this.currentV = cv;
		this.Urgent = mode;
		this.graph = graph;
		this.d = new DAlg(graph);
	}
	
	@Override
	public void run() {
		if (this.Urgent) {
			//choose the shortest path
			visitedV = d.path(currentV, finalV);
			for (int i = 0; i < visitedV.size(); i++) {
				visitedV.get(i).setData(this);
				System.out.println("The village that gnome " + this.name + " has past is "
						+ visitedV.get(i));
			}
		} else {
			//choose a random path
			System.out.println("Here is a random path. ");
			
		}
	}
}
