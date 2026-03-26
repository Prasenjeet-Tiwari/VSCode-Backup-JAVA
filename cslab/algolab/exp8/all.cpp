#include <bits/stdc++.h>
using namespace std;

#define V 5
#define INF 1000000000

// Print matrix
void printMatrix(vector<vector<int>> &dist) {
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            if (dist[i][j] == INF)
                cout << "INF ";
            else
                cout << dist[i][j] << " ";
        }
        cout << endl;
    }
}

// Print path using predecessor matrix
void printPath(vector<vector<int>> &pred, int i, int j) {
    if (i == j) {
        cout << i;
        return;
    }
    if (pred[i][j] == -1) {
        cout << "No path";
        return;
    }
    printPath(pred, i, pred[i][j]);
    cout << " -> " << j;
}

int main() {
    // Initial graph (from assignment)
    vector<vector<int>> dist = {
        {0,   3,   8,  -4, INF},
        {INF, 0,   4,   1,   7},
        {INF, INF, 0, INF, INF},
        {INF, INF, -5,  0, INF},
        {2, INF, INF,   6,   0}
    };

    // Predecessor matrix
    vector<vector<int>> pred(V, vector<int>(V, -1));

    // Initialize predecessor
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            if (i != j && dist[i][j] != INF)
                pred[i][j] = i;
        }
    }

    // Floyd–Warshall Algorithm
    for (int k = 0; k < V; k++) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][k] != INF && dist[k][j] != INF &&
                    dist[i][k] + dist[k][j] < dist[i][j]) {

                    dist[i][j] = dist[i][k] + dist[k][j];
                    pred[i][j] = pred[k][j];
                }
            }
        }
    }

    // Print shortest distance matrix
    cout << "Shortest Distance Matrix:\n";
    printMatrix(dist);

    // Check negative cycle
    cout << "\nChecking for Negative Cycle:\n";
    bool hasNegativeCycle = false;
    for (int i = 0; i < V; i++) {
        if (dist[i][i] < 0) {
            hasNegativeCycle = true;
            break;
        }
    }

    if (hasNegativeCycle)
        cout << "Negative Cycle Detected!\n";
    else
        cout << "No Negative Cycle\n";

    // Print some sample paths
    cout << "\nSample Shortest Paths:\n";
    cout << "Path 0 -> 2: ";
    printPath(pred, 0, 2);
    cout << endl;

    cout << "Path 0 -> 4: ";
    printPath(pred, 0, 4);
    cout << endl;

    cout << "Path 3 -> 1: ";
    printPath(pred, 3, 1);
    cout << endl;

    // Transitive Closure
    vector<vector<int>> reach(V, vector<int>(V, 0));

    // Initialize
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            if (dist[i][j] != INF)
                reach[i][j] = 1;
        }
        reach[i][i] = 1;
    }

    // Warshall Algorithm
    for (int k = 0; k < V; k++) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                reach[i][j] = reach[i][j] || (reach[i][k] && reach[k][j]);
            }
        }
    }

    cout << "\nTransitive Closure Matrix:\n";
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            cout << reach[i][j] << " ";
        }
        cout << endl;
    }

    return 0;
}