#include <iostream>
using namespace std;

class Song {
public:
    string name, artist;
    float duration;
    Song *prev, *next;

    Song(string n, string a, float d) : name(n), artist(a), duration(d), prev(nullptr), next(nullptr) {}
};

class Playlist {
    Song* head = nullptr;

public:
    void addSong(string n, string a, float d) {
        Song* node = new Song(n, a, d);
        if (!head) { head = node; return; }
        Song* temp = head;
        while (temp->next) temp = temp->next;
        temp->next = node;
        node->prev = temp;
    }

    void display() {
        cout << "\nPlaylist:\n";
        for (Song* t = head; t; t = t->next)
            cout << "🎵 " << t->name << " | " << t->artist << " | " << t->duration << " min\n";
    }

    void deleteThird() {
        if (!head || !head->next || !head->next->next) return;
        Song* del = head->next->next;  // 3rd node
        del->prev->next = del->next;
        if (del->next) del->next->prev = del->prev;
        delete del;
    }

    void insertAfterSecond(string n1, string a1, float d1,
                           string n2, string a2, float d2) {
        if (!head || !head->next) return;
        Song* s2 = head->next;
        Song* new1 = new Song(n1, a1, d1);
        Song* new2 = new Song(n2, a2, d2);

        new1->next = new2;
        new2->prev = new1;

        new2->next = s2->next;
        if (s2->next) s2->next->prev = new2;

        s2->next = new1;
        new1->prev = s2;
    }
};

int main() {
    Playlist p;

    // Initial 5 songs
    p.addSong("Song1", "Artist1", 3.5);
    p.addSong("Song2", "Artist2", 4.0);
    p.addSong("Song3", "Artist3", 2.8);
    p.addSong("Song4", "Artist4", 3.7);
    p.addSong("Song5", "Artist5", 4.2);

    cout << "Initial playlist:";
    p.display();

    p.deleteThird();
    cout << "\nAfter deleting 3rd song:";
    p.display();

    p.insertAfterSecond("NewSong1", "NewArtist1", 3.9,
                        "NewSong2", "NewArtist2", 4.1);
    cout << "\nAfter inserting 2 new songs after 2nd:";
    p.display();

    return 0;
}
