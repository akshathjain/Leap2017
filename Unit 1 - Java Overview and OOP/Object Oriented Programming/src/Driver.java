/*
 * Name: Akshath Jain
 * Date: 6/26/17
 */

public class Driver {
	public static void main(String[] args){
		//System.out.println("hello world");
		
		//Animal dog = new Animal("Doge");
		//System.out.println(dog.getName());
		
		/*Dog aDog = new Dog("cat");
		System.out.println(aDog.getName());
		
		Animal myAnimal = new Dog("elephant");
		System.out.println(myAnimal.getName());*/
		
		/*Animal otherAnimal = new Dog("fido");
		System.out.println(otherAnimal.makeNoise());*/

		GermanShepard guardDog = new GermanShepard("Buck");
		System.out.println(guardDog.getName());
		System.out.println(guardDog.makeNoise());
		System.out.println(guardDog.attack());
	}
}
