
public abstract class Animal {
	//this is instance data - every time you create an object of animal, you "instantiate" this data
	private double weight;
	private String color;
	private String sound;
	private String name;
	private int age;
	
	public Animal(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public String makeNoise(){
		return "some noise";
	}
	
}
