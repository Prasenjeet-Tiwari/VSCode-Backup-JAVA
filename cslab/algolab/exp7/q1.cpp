#include <bits/stdc++.h>
using namespace std;

#define INF 1e9

int main() {
    int n = 7;

    // adjacency matrix
    int graph[7][7];

    // initialize with INF
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            graph[i][j] = INF;

    // edges
    graph[2][0] = 8; // C->A
    graph[2][1] = 1; // C->B
    graph[1][0] = 2; // B->A
    graph[1][3] = 3; // B->D
    graph[0][3] = 4; // A->D
    graph[0][4] = 6; // A->E
    graph[0][5] = 10; // A->F
    graph[3][5] = 8; // D->F
    graph[4][5] = 7; // E->F
    graph[6][5] = 6; // G->F

    int dist[7], parent[7];
    bool visited[7];

    // initialize
    for (int i = 0; i < n; i++) {
        dist[i] = INF;
        parent[i] = -1;
        visited[i] = false;
    }

    int src = 2; // C
    dist[src] = 0;

    // Dijkstra
    for (int i = 0; i < n; i++) {
        int u = -1, minDist = INF;

        // find min distance unvisited node
        for (int j = 0; j < n; j++) {
            if (!visited[j] && dist[j] < minDist) {
                minDist = dist[j];
                u = j;
            }
        }

        if (u == -1) break;
        visited[u] = true;

        for (int v = 0; v < n; v++) {
            if (!visited[v] && graph[u][v] != INF) {
                if (dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        //each loop print
        cout << "Node  Cost  Parent\n";
        for (int i = 0; i < n; i++) {
            cout << char('A' + i) << "     ";
            if (dist[i] == INF) cout << "INF   ";
            else cout << dist[i] << "     ";

            if (parent[i] == -1) cout << "-";
            else cout << char('A' + parent[i]);
            cout << "\n";
        }
    }

    // cout << "Node  Cost  Parent\n";
    // for (int i = 0; i < n; i++) {
    //     cout << char('A' + i) << "     ";
    //     if (dist[i] == INF) cout << "INF   ";
    //     else cout << dist[i] << "     ";

    //     if (parent[i] == -1) cout << "-";
    //     else cout << char('A' + parent[i]);
    //     cout << "\n";
    // }

    return 0;
}