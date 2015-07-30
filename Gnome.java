
public class Gnome {
	private Village finalV;
	private Village currentV;
	private boolean Urgency = true;// true means the gnome is in a lazy mode. false means in an urgent mode. 
	
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
		this.Urgency = x;
	}
	public boolean getUrgency(){
		return Urgency;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
