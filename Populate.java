import java.util.*;
import java.util.Map.Entry;

public class Populate implements Runnable {
	ArrayList<Gnome> gnomes;
	Graph graph;
	HashMap<Village, ArrayList<Gnome>> wherethegnomesare;
	HashMap<Integer, String> stringforGUI;
	
	public Populate(ArrayList<Gnome> gnomes, Graph graph){
		this.gnomes = gnomes;
		this.graph = graph;
		wherethegnomesare = new HashMap<Village, ArrayList<Gnome>>();
		stringforGUI = new HashMap<Integer, String>();
	}

	public void printstringforGUI(){
		for (Entry<Integer, String> x: stringforGUI.entrySet()){
		System.out.println(x.getKey());
		System.out.println (x.getValue());
		}
	}
	
	public void update(){
		wherethegnomesare = new HashMap<Village, ArrayList<Gnome>>();
		int size = graph.getVillages().size();
		for (Village x: graph.getVillages()){
			ArrayList<Gnome> gnomeArray = new ArrayList<Gnome>();
			wherethegnomesare.put(x, gnomeArray);
		}
		for (Gnome gnome: gnomes){
			Village tempVillage = gnome.getcurrentV(); //grabs the currentV
			ArrayList<Gnome> gnomeArray = wherethegnomesare.get(tempVillage);
			gnomeArray.add(gnome);
		}
		for (Entry<Village, ArrayList<Gnome>> entry: wherethegnomesare.entrySet()){
			Village tempVillage = entry.getKey();
			ArrayList<Gnome> tempArray = entry.getValue();
			String string = "";
			for (Gnome x: tempArray){
				string = string + "-" + x.getname();
			}
			stringforGUI.put(tempVillage.getcoordinate(), string);
		}
	}
	
	public void run() {
		while (true){
			try {
				Thread.sleep(1000);
				update();
				printstringforGUI();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/////////////////////////////////////////////////////////////////////////////////////
		///////////////////INITIALIZES EVERYTHING////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		//Test Villages
		Village Village0 = new Village(0, 0);
		Village Village1 = new Village(1, 1);
		Village Village2 = new Village(2, 2);
		Village Village3 = new Village(3, 3);
		Village Village4 = new Village(4, 4);
		Village Village5 = new Village(5, 5);
		
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
		//Gnome1.setfinalV(Village4);
		Thread Thread1 = new Thread(Gnome1); 
		Thread1.start();
		Gnome1.setfinalV(Village5);

		Gnome Gnome2 = new Gnome("Gnome2", Village0, graph);
		Thread Thread2 = new Thread(Gnome2); 
		Thread2.start();
		
		ArrayList<Gnome> gnomeArray = new ArrayList<Gnome>();
		gnomeArray.add(Gnome1);
		gnomeArray.add(Gnome2);
		
		Populate Populate = new Populate(gnomeArray, graph);
		Thread ThreadPopulate = new Thread(Populate);
		ThreadPopulate.start();
	}
}
