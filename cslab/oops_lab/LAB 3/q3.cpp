#include <iostream>
using namespace std;

class Shape {
public:
    virtual void area() = 0;
};

class Triangle : public Shape {
    double *b, *h;
public:
    Triangle(double* base, double* height) {
        b = base;
        h = height;
    }

    void area() {
        cout << "Area of Triangle: " << 0.5 * (*b) * (*h) << endl;
    }
};

class Square : public Shape {
    double *s;
public:
    Square(double* side) {
        s = side;
    }

    void area() {
        cout << "Area of Square: " << (*s) * (*s) << endl;
    }
};

class Rectangle : public Shape {
    double *l, *b;
public:
    Rectangle(double* length, double* breadth) {
        l = length;
        b = breadth;
    }

    void area() {
        cout << "Area of Rectangle: " << (*l) * (*b) << endl;
    }
};

class Circle : public Shape {
    double *r;
public:
    Circle(double* radius) {
        r = radius;
    }

    void area() {
        cout << "Area of Circle: " << 3.14 * (*r) * (*r) << endl;
    }
};

int main() {
    double b = 10, h = 5, s = 4, l = 6, br = 3, r = 7;

    Shape* sh;

    Triangle t(&b, &h);
    Square sq(&s);
    Rectangle rec(&l, &br);
    Circle c(&r);

    sh = &t;
    sh->area();

    sh = &sq;
    sh->area();

    sh = &rec;
    sh->area();

    sh = &c;
    sh->area();

    return 0;
}
