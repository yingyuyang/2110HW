import java.util.ArrayList;


public class MergeSort {
	ArrayList<Integer> array;
	
	//constructor
	public MergeSort(ArrayList<Integer> x){ //
		array = x;
	}
	public void Sort(){
		array = Merge(array);
	}
	public ArrayList<Integer> Merge(ArrayList<Integer> whole){
		if (whole.size() == 1){
			return whole;
		}
		ArrayList<Integer> Left = new ArrayList<Integer>();
		ArrayList<Integer> Right = new ArrayList<Integer>();
		int center;
		center = whole.size()/2;
		
		//initialize left
		for (int i = 0; i < center; i++){
			Left.add(whole.get(i));
		}
		//initialize right
		for (int i = center; i < whole.size(); i++){
			Right.add(whole.get(i));
		}	
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
		
		if (leftint >= Left.size()){//left is full so add right;
			int wholesize = Right.size() - rightint;//how many left over values are there?	
			for (int i  = 0; i < wholesize; i ++){
				Whole.set(i + wholeint, Right.get(i));
			}
		}
		if (rightint >= Right.size()){//left is full so add right;
			int wholesize = Left.size() - rightint;//how many left over values are there?	
			for (int i  = 0; i < wholesize; i ++){
				Whole.set(i + wholeint, Left.get(i));
			}
		}
	}
	   public static void main(String[] args) {
	        ArrayList<Integer> input = new ArrayList<Integer>();
	        input.add(5);
	        input.add(3);
	        input.add(2);

	        MergeSort test = new MergeSort(input);
	        test.Sort();	
	   
	        for (Integer x: test.array){
	        	System.out.print(x);
	        }
	   }
}