#include <iostream>
#include <fstream>
using namespace std;

struct Employee {
    char name[30];
    int id;
    float salary;
};

int main() {

    Employee e;
    fstream file("employees.dat", ios::in | ios::out | ios::binary | ios::trunc);

    int n;
    cout<<"Enter number of employees: ";
    cin>>n;

    for(int i=0;i<n;i++){
        cout<<"Enter name id salary\n";
        cin>>e.name>>e.id>>e.salary;
        file.write((char*)&e,sizeof(e));
    }

    file.seekg(0);

    cout<<"\nEmployee Records\n";
    while(file.read((char*)&e,sizeof(e))){
        cout<<e.name<<" "<<e.id<<" "<<e.salary<<endl;
    }

    int pos;
    cout<<"\nEnter employee record number to modify: ";
    cin>>pos;

    file.seekg((pos-1)*sizeof(e));
    file.read((char*)&e,sizeof(e));

    cout<<"Enter new salary: ";
    cin>>e.salary;

    file.seekp((pos-1)*sizeof(e));
    file.write((char*)&e,sizeof(e));

    file.close();
}