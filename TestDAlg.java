import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TestDAlg {

	public static void main(String[]Args){
		
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
	System.out.println("All villages and roads");
	graph.printGraph();
	
	//Tests which villages are incoming and outgoing
	System.out.println("_________________________________");
	System.out.println("Outcoming and incoming villages for village1: correct");
	System.out.println("Outcoming villages");
	Village0.printoutgoing();
	System.out.println("Incoming villages");
	Village0.printincoming();

	//Tests getroaddistance function
	System.out.println("_________________________________");
	System.out.println("Getroaddistance function: correct (should return 5 and 5");
	DAlg DAlgTest = new DAlg(graph);
	System.out.println(DAlgTest.getroaddistance(Village0, Village1));
	System.out.println(DAlgTest.getroaddistance(Village0, Village2));
	//System.out.println(DAlgTest.getroaddistance(Village1, Village0));
	
	//Tests the shortest fuction
	System.out.println("_________________________________");
	System.out.println("shortest function: correct (should return 999999, 0, and 999999)");
	System.out.println(DAlgTest.shortest(Village0));
	DAlgTest.getfrontierV().add(Village0);
	DAlgTest.getdistances().put(Village0, 0);
	DAlgTest.getcheckedV().add(Village0);
	System.out.println(DAlgTest.shortest(Village0));
	System.out.println(DAlgTest.shortest(Village1));

	//Tests the closest function
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
	
	//Tests the start method
	System.out.println("_________________________________");
	System.out.println("start function: correct (should display Village0,1,3),4");
	DAlgTest.start(Village0, Village4);
	System.out.print("The Checked Villages are: "); DAlgTest.printcheckedV();
	
	//Tests path method
	System.out.println("_________________________________");
	System.out.println("path: correct (should dispaly Village0,1,3,4)");
	DAlg.printpath(DAlgTest.path(Village0, Village4));
	
	//Tests reversing by recursion method
	System.out.println("_________________________________"); 
	System.out.println("reversebyrecursion: correct (should dispaly Village4,3,2,1)");
	LinkedList<Village> LinkedList = DAlg.reverse(DAlgTest.path(Village0, Village4));
	System.out.println("Reversed Path:");
	DAlg.printpath(LinkedList);
	
	//Tests DAlgCost
	System.out.println("_________________________________"); 
	System.out.println("DAlgCost: correct (should dispaly Village0,1,5)");
	DAlgCost DAlgCost = new DAlgCost(graph);
	DAlg.printpath(DAlgCost.path(Village0, Village5));
	
  }
} 