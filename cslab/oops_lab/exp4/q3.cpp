#include <iostream>
#include <fstream>
using namespace std;

struct Student {
    char name[30];
    int roll_no;
    float marks;
};

int main() {
    Student s;
    fstream file("students.dat", ios::in | ios::out | ios::binary | ios::trunc);

    int n;
    cout << "Enter number of students: ";
    cin >> n;

    for(int i=0;i<n;i++){
        cout<<"Enter name roll marks:\n";
        cin>>s.name>>s.roll_no>>s.marks;
        file.write((char*)&s,sizeof(s));
    }

    file.seekg(0);

    cout<<"\nStudent Records\n";
    while(file.read((char*)&s,sizeof(s))){
        cout<<s.name<<" "<<s.roll_no<<" "<<s.marks<<endl;
    }

    int pos;
    cout<<"\nEnter record number to modify: ";
    cin>>pos;

    file.seekg((pos-1)*sizeof(s));
    file.read((char*)&s,sizeof(s));

    cout<<"Enter new marks: ";
    cin>>s.marks;

    file.seekp((pos-1)*sizeof(s));
    file.write((char*)&s,sizeof(s));

    file.close();
}