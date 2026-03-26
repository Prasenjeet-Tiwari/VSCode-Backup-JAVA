#include <bits/stdc++.h>
using namespace std;

#define INF 1e9

struct Edge {
    int u, v, w;
};

int main() {
    int n = 5;

    Edge edges[] = {
        {0,1,6},   // s->t
        {0,3,7},   // s->y
        {1,2,5},   // t->x
        {2,1,-2},  // x->t
        {1,3,8},   // t->y
        {3,2,-3},  // y->x
        {1,4,-4},  // t->z
        {4,2,7},   // z->x
        {3,4,9}    // y->z
    };

    int m = 9;

    int dist[5], parent[5];

    for (int i = 0; i < n; i++) {
        dist[i] = INF;
        parent[i] = -1;
    }

    int src = 0; // s
    dist[src] = 0;

    // relax edges n-1 times
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < m; j++) {
            int u = edges[j].u;
            int v = edges[j].v;
            int w = edges[j].w;

            if (dist[u] != INF && dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                parent[v] = u;
            }
        }
    }

    // negative cycle check
    for (int j = 0; j < m; j++) {
        int u = edges[j].u;
        int v = edges[j].v;
        int w = edges[j].w;

        if (dist[u] != INF && dist[u] + w < dist[v]) {
            cout << "Negative cycle detected\n";
            return 0;
        }
    }

    cout << "Node  Cost  Parent\n";
    char names[5] = {'s','t','x','y','z'};

    for (int i = 0; i < n; i++) {
        cout << names[i] << "     ";

        if (dist[i] == INF) cout << "INF   ";
        else cout << dist[i] << "     ";

        if (parent[i] == -1) cout << "-";
        else cout << names[parent[i]];

        cout << "\n";
    }

    return 0;
}