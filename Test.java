import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Test {

	private ArrayList<Village> allvillages;
	private ArrayList<Road> allroads;
	
	//constructor
	public Test(){
	}
	
	public Test(ArrayList<Village> allvillages, ArrayList<Road> allroads){
	this.allvillages = allvillages;
	this.allroads = allroads;
	}

	public static void main(String[]Args){
	
	//Test testcase = new Test();
	//testcase.testthis();
		
	//Test Villages
	Test test = new Test();
	Village Village0 = new Village(0);
	Village Village1 = new Village(1);
	Village Village2 = new Village(2);
	
	//Test Roads
	Road Road01 = new Road("01", Village0, Village1, 5);
	Road Road12 = new Road("12", Village1, Village2, 7);
	
	//Test Graph
	ArrayList<Village> Villages = new ArrayList<Village>();
	Villages.add(Village0);
	Villages.add(Village1);
	Villages.add(Village2);
	
	ArrayList<Road> Roads = new ArrayList<Road>();
	Roads.add(Road01);
	Roads.add(Road12);
	
	Graph graph = new Graph(Villages, Roads);
	System.out.println("All villages and roads");
	graph.printGraph();
	
	System.out.println("_________________________________");
	System.out.println("Outcoming villages");
	Village0.printoutgoing();
	Village1.printoutgoing();
	Village2.printoutgoing();
	System.out.println("Incoming villages");
	Village0.printincoming();
	Village1.printincoming();
	Village2.printincoming();
	
	System.out.println("_________________________________");
	System.out.println("Getroaddistance function: correct (should return 5 and 7");
	DAlg DAlgTest = new DAlg(graph);
	System.out.println(DAlgTest.getroaddistance(Village0, Village1));
	System.out.println(DAlgTest.getroaddistance(Village1, Village2));
	//System.out.println(DAlgTest.getroaddistance(Village1, Village0));
	
	System.out.println("_________________________________");
	System.out.println("shortest function: correct (should return 999999, 0, and 999999)");
	System.out.println(DAlgTest.shortest(Village0));
	DAlgTest.getfrontierV().add(Village0);
	DAlgTest.getdistances().put(Village0, 0);
	DAlgTest.getcheckedV().add(Village0);
	System.out.println(DAlgTest.shortest(Village0));
	System.out.println(DAlgTest.shortest(Village1));

	System.out.println("_________________________________");
	System.out.println("closest function: correct (should return Village0)");
	DAlgTest.closest().printVillage();
	System.out.println(DAlgTest.shortest(Village0));

	
	System.out.println("_________________________________");
	System.out.println("shortestpathtoeachV function: correct (should display Village1 and Village2)");
	//DAlgTest.shortestpathtoeachV(Village0);
	//System.out.print("The frontier is: "); DAlgTest.printfrontierV();
	//DAlgTest.shortestpathtoeachV(Village1);
	//System.out.print("The frontier is: "); DAlgTest.printfrontierV();
	
	System.out.println("_________________________________");
	System.out.println("start function: correct (should display Village0, Village0, and Village1");
	System.out.print("The Checked Villages are: "); DAlgTest.printcheckedV();
	DAlgTest.start(Village0, Village1);
	System.out.print("The Checked Villages are: "); DAlgTest.printcheckedV();
	
	System.out.println("_________________________________");
	System.out.println("path: correct (should dispaly Village2, Village1, Village0");
	DAlg.printpath(DAlgTest.path(Village0, Village2));
	
	System.out.println("_________________________________");
	System.out.println("reversebyrecursion: correct (should dispaly Village2, Village1, Village0");
	LinkedList<Village> LinkedList = new LinkedList<Village>();
	LinkedList.add(Village0);
	LinkedList.add(Village1);
	LinkedList.add(Village2);
	System.out.println("Path:");
	DAlg.printpath(LinkedList);
	DAlg.reverse(LinkedList);
	System.out.println("Reversed Path:");
	DAlg.printpath(LinkedList);
  }
} 