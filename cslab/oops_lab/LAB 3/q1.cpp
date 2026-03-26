#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

// ---------------- FRACTION CLASS ----------------
class Fraction {
public:
    int num, den;

    Fraction(int n = 0, int d = 1) {
        num = n;
        den = d;
    }

    Fraction operator+(Fraction f) {
        return Fraction(num * f.den + f.num * den, den * f.den);
    }

    bool operator==(Fraction f) {
        return num * f.den == f.num * den;
    }

    void display() {
        cout << num << "/" << den;
    }
};

// ---------------- MATHPROBLEM CLASS ----------------
class MathProblem {
protected:
    Fraction f1, f2;
    Fraction userAnswer, correctAnswer;
    char op;
    bool isAnswerCorrect;

public:
    virtual void setProblem(Fraction a, Fraction b, char oper) {
        f1 = a;
        f2 = b;
        op = oper;
        correctAnswer = f1 + f2;
        userAnswer = Fraction(0, 1);
        isAnswerCorrect = false;
    }

    void displayProblem() {
        f1.display();
        cout << " " << op << " ";
        f2.display();
        cout << " = ?\n";
    }

    void askUserForAnswer() {
        int n, d;
        cout << "Enter answer (num den): ";
        cin >> n >> d;
        userAnswer = Fraction(n, d);

        if (userAnswer == correctAnswer)
            isAnswerCorrect = true;
    }

    void displayResult() {
        cout << "Your Answer: ";
        userAnswer.display();
        cout << "\nCorrect Answer: ";
        correctAnswer.display();

        if (isAnswerCorrect)
            cout << "\nResult: Correct \n";
        else
            cout << "\nResult: Wrong \n";
    }

    bool isCorrect() {
        return isAnswerCorrect;
    }
};

// ---------------- DOUBLINGMATHPROBLEM CLASS ----------------
class DoublingMathProblem : public MathProblem {
public:
    void setProblem(Fraction a) {
        f1 = a;
        f2 = a;
        op = '+';
        correctAnswer = f1 + f2;
        userAnswer = Fraction(0, 1);
        isAnswerCorrect = false;
    }
};

// ---------------- MAIN (PART f IMPLEMENTED) ----------------
int main() {
    srand(time(0));
    DoublingMathProblem problems[5];
    int score = 0;

    cout << "---- Doubling Fraction Test ----\n\n";

    for (int i = 0; i < 5; i++) {
        Fraction f(rand() % 5 + 1, rand() % 5 + 1);

        problems[i].setProblem(f);
        problems[i].displayProblem();
        problems[i].askUserForAnswer();
        problems[i].displayResult();

        if (problems[i].isCorrect())
            score++;

        cout << "-------------------------\n";
    }

    cout << "\nFinal Score: " << (score * 100) / 5 << "%\n";

    return 0;
}
