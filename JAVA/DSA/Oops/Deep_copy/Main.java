
public class Main {
    
// Main class

    public static void main(String[] args) {
        // Create Student A and set details
        StudentDeep s1 = new StudentDeep();
        s1.name = "Prasen";
        s1.age = 33;
        s1.rollno = "ecb23015";
        s1.marks[0] = 60;
        s1.marks[1] = 50;
        s1.marks[2] = 40;

        // Create Student B using shallow copy
        StudentDeep s2 = new StudentDeep(s1);

        // Display initial marks of both students
        System.out.println("Initial marks[0] of s2: " + s2.marks[0]); // Output: 60

        // Modify the marks array of s1
        s1.marks[0] = 20;

        // Display modified marks of both students
        System.out.println("Modified marks[0] of s1: " + s1.marks[0]); // Output: 20
        System.out.println("Marks[0] of s2 after modifying s1: " + s2.marks[0]); // Output: 20
    }
}
