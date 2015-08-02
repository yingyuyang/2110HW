import java.util.ArrayList;


public class MergeSort {
	ArrayList<Integer> array;
	
	//constructor
	public MergeSort(ArrayList<Integer> x){ //
		array = x;
	}
	//works
	public void Sort(){
		array = Merge(array);
	}
	public void print(ArrayList<Integer> array){
		for (Integer x: array){
			System.out.print(x + " ");
		}
	}
	
	public ArrayList<Integer> Merge(ArrayList<Integer> whole){
		if (whole.size() == 1){
			return whole;
		}
		ArrayList<Integer> Left = new ArrayList<Integer>();
		ArrayList<Integer> Right = new ArrayList<Integer>();
		int center = whole.size()/2;
		
		//initialize left
		for (int i = 0; i < center; i++){
			Left.add(whole.get(i));
		}
		//initialize right
		for (int i = center; i < whole.size(); i++){
			Right.add(whole.get(i));
		}
		
		print(Left);
		Left = Merge(Left);
		Right = Merge(Right);
		
		Combine(Left, Right, whole);
		return whole;
	}
	
	public void Combine (ArrayList<Integer> Left, ArrayList<Integer> Right, ArrayList<Integer>Whole){
		int leftint = 0;
		int rightint = 0;
		int wholeint = 0;
		
		while (leftint < Left.size() && rightint < Right.size()){ //while not full
		if ((Left.get(leftint) < Right.get(rightint))){
			Whole.set(wholeint, Left.get(leftint));
			leftint ++;
		}
		else{
			Whole.set(wholeint, Right.get(rightint));
			rightint ++;
		}
		wholeint ++;
		}  
		/*
        ArrayList<Integer>rest;
        int restIndex;
        if (leftint >= Left.size()) {
            // The left arraylist has been use up...
            rest = Right;
            restIndex = rightint;
        }
        else {
            // The right arraylist has been used up...
            rest = Left;
            restIndex = leftint;
        }
 
        // Copy the rest of whichever arraylist (left or right) was
        // not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            Whole.set(wholeint, rest.get(i));
            wholeint++;
        }*/
        
        if (leftint >= Left.size()){
        	for (int i = rightint; i < Right.size(); i++){
        		Whole.set(wholeint, Right.get(i));
        		wholeint++;
        	}
        }
        if (rightint >= Right.size()){
        	for (int i = rightint; i < Left.size(); i++){
        		Whole.set(wholeint, Left.get(i));
        		wholeint++;
        	}
        }
    }

	   public static void main(String[] args) {
	        ArrayList<Integer> input = new ArrayList<Integer>();
	        input.add(8);
	        input.add(7);
	        input.add(6);
	        input.add(5);
	        input.add(4);
	        input.add(3);
	        input.add(2);
	        input.add(1);

	        MergeSort test = new MergeSort(input);
	        test.Sort();	
	   
	        for (Integer x: test.array){
	        	System.out.print(x);
	        }
	   }
}