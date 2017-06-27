public class GermanShepard extends Dog{

	public GermanShepard(String name){
		super(name);
	}

	public String attack(){
		return "Your dog " + getName() + " just attacked someone";
	}
}