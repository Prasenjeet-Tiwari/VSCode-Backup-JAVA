

public class StudentShallow {
    
    int age;
    String name;
    String rollno;
    int[] marks;

    // Default constructor
    StudentShallow() {
        marks = new int[3]; // Initialize the marks array
    }

    // Copy constructor for shallow copy
    StudentShallow(StudentShallow s1) {
        this.name = s1.name;
        this.age = s1.age;
        this.rollno = s1.rollno;
        this.marks = s1.marks; // Shallow copy of the marks array
    }
}
