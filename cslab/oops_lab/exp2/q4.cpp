#include <iostream>
using namespace std;

class Student {
protected:
    string name;
    int age;

public:
    Student(string n, int a) {
        name = n;
        age = a;
    }
};

class Sports : virtual public Student {
protected:
    int sportsScore;

public:
    Sports(string n, int a, int s) : Student(n, a) {
        sportsScore = s;
    }
};

class Academics : virtual public Student {
protected:
    int examScore;

public:
    Academics(string n, int a, int e) : Student(n, a) {
        examScore = e;
    }
};

class Result : public Sports, public Academics {
public:
    Result(string n, int a, int s, int e) : Student(n, a), Sports(n, a, s), Academics(n, a, e) {}

    void display() {
        cout << "Name: " << name << endl;
        cout << "Age: " << age << endl;
        cout << "Sports Score: " << sportsScore << endl;
        cout << "Exam Score: " << examScore << endl;
        cout << "Total Score: " << sportsScore + examScore << endl;
    }
};

int main() {
    Result r("Amit", 20, 40, 55);
    r.display();
    return 0;
}
