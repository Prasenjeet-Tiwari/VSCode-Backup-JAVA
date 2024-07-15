
public class instance_constructor_rule {

	public static void main(String[] args) {
		
		// abstract =  	abstract classes cannot be instantiated, but they can have a subclass
		//				abstract methods are declared without an implementation
		
		System.out.println("Order of execution: ");
		Car car = new Car();
		
		car.go();
	}
}

abstract class Vehicle {
	
	//constructor can be declared
	Vehicle(){
		System.out.println("Vehicle constructor is called");
	}

	abstract void go();		//abstract method
}

class Car extends Vehicle{
	
	//constructor of car
	Car(){
		System.out.println("Car constructor is called \n");
	}

	@Override	//abstract method instance implementation
	void go() {
		System.out.println("The driver is driving the car \n");
		
	}
}
//**************************************** {
    

