//package JAVA.DSA.Oops.Abstraction.Interface_;
//********************************************

/*Uses of Interfaces in Java are mentioned below:


It is used to achieve total abstraction.
Since java does not support multiple inheritances in the case of class, by using an interface it can achieve multiple inheritances.
Any class can extend only one class, but can implement multiple interfaces.
It is also used to achieve loose coupling.
Interfaces are used to implement abstraction. 

*/
public class Main {

	public static void main(String[] args) {
		
		Fish fish = new Fish();
		
		fish.hunt();
		fish.flee();
				
	}
}
//********************************************
interface Prey {

	void flee();
}
//********************************************
interface Predator {

	void hunt();
}
//********************************************
class Rabbit implements Prey{


	@Override
	public void flee() {
		System.out.println("*The rabbit is fleeing*");
		
        }
}
//********************************************
class Hawk implements Predator{


	@Override
	public void hunt() {				//this will have to be public
		System.out.println("*The hawk is hunting*");
		
        }
}
//********************************************
class Fish implements Prey,Predator{


	@Override
	public void hunt() {			//this will have to be public
		System.out.println("*The fish is hunting*");
		
	}

	@Override
	public void flee() {			//this will have to be public
		System.out.println("*The fish is fleeing*");
		
	}
}
//********************************************