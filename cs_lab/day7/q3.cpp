#include <iostream>
#include <string>
using namespace std;

class Song {
public:
    string name, artist;
    double duration;
    Song* prev;
    Song* next;
    Song(string n, string a, double d) : name(n), artist(a), duration(d), prev(nullptr), next(nullptr) {}
};

class Playlist {
    Song* head;
public:
    Playlist() : head(nullptr) {}

    void addSong(string name, string artist, double duration) {
        Song* song = new Song(name, artist, duration);
        if (!head) { head = song; return; }
        Song* temp = head;
        while (temp->next) temp = temp->next;
        temp->next = song; song->prev = temp;
    }

    void display() {
        Song* t = head;
        int idx = 1;
        while (t) {
            cout << idx++ << ". ";
            cout << "Song: " << t->name << ", Artist: " << t->artist << ", Duration: " << t->duration << " mins\n";
            t = t->next;
        }
        cout << endl;
    }

    void removeAt(int pos) {
        if (!head) return;
        Song* temp = head;
        int idx = 1;
        while (temp && idx < pos) { temp = temp->next; idx++; }
        if (!temp) return;
        if (temp->prev) temp->prev->next = temp->next;
        else head = temp->next;
        if (temp->next) temp->next->prev = temp->prev;
        delete temp;
    }

    void insertAfter(int pos, string name, string artist, double duration) {
        Song* temp = head;
        int idx = 1;
        while (temp && idx < pos) { temp = temp->next; idx++; }
        if (!temp) return;
        Song* song = new Song(name, artist, duration);
        song->next = temp->next;
        if (temp->next) temp->next->prev = song;
        temp->next = song;
        song->prev = temp;
    }
};

int main() {
    Playlist ply;
    ply.addSong("Tum Hi Ho", "Arijit Singh", 4.5);
    ply.addSong("Kesariya", "Arijit Singh", 4);
    ply.addSong("Naatu Naatu", "Rahul Sipligunj", 3.5);
    ply.addSong("Levitating", "Dua Lipa", 3.2);
    ply.addSong("Faded", "Alan Walker", 3.6);

    cout << "\nInitial Playlist:\n";
    ply.display();

    ply.removeAt(3);
    cout << "After removing 3rd song:\n";
    ply.display();

    ply.insertAfter(2, "Believer", "Imagine Dragons", 3.3);
    ply.insertAfter(2, "Counting Stars", "One Republic", 4);

    cout << "After inserting two songs after 2nd node:\n";
    ply.display();

    return 0;
}
