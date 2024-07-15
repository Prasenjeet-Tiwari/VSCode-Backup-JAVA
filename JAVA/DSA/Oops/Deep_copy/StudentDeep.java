
public class StudentDeep {
    
    int age;
    String name;
    String rollno;
    int[] marks;

    // Default constructor
    StudentDeep() {
        marks = new int[3]; // Initialize the marks array
    }

    // Deep constructor for Deep copy
    StudentDeep(StudentDeep s1) {
        marks= new int[3];
        this.name = s1.name;
        this.age = s1.age;
        this.rollno = s1.rollno;
         for(int i=0; i<3; i++){
            this.marks[i]=s1.marks[i];// Deep copy of the marks array
        }
    }
}