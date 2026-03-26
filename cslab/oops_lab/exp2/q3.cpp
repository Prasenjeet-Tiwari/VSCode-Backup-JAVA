#include <iostream>
using namespace std;

class Person {
protected:
    string name;
    int age;

public:
    Person(string n, int a) {
        name = n;
        age = a;
    }
};

class Employee : virtual public Person {
public:
    Employee(string n, int a) : Person(n, a) {}
};

class Student : virtual public Person {
public:
    Student(string n, int a) : Person(n, a) {}
};

class TeachingAssistant : public Employee, public Student {
public:
    TeachingAssistant(string n, int a)
        : Person(n, a), Employee(n, a), Student(n, a) {}

    void display() {
        cout << "Name: " << name << endl;
        cout << "Age: " << age << endl;
    }
};

int main() {
    TeachingAssistant ta("Rahul", 22);
    ta.display();
    return 0;
}
