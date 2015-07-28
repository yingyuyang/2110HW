import java.util.*;

public class Gnome {
	private Village finalV;
	private Village currentV;
	private int Urgency = 0;
	
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
	public void setCurrency(int x){
		Urgency = 0;
	}
	public int getCurrency(){
		return Urgency;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
