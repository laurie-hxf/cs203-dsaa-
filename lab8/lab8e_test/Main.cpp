//
// Created by purple on 24-12-23.
//
// Created by purple on 24-12-23.
#include <iostream>
using namespace std;

constexpr int MAX_N = 150000; // 假设最大节点数

struct Node {
    int val;
    int in;
    int out;
    Node* father;
    Node* children[MAX_N];
    int childCount;

    Node() : val(0), in(0), out(0), father(nullptr), childCount(0) {}
    explicit Node(int val) : val(val), in(0), out(0), father(nullptr), childCount(0) {}
};

class Graph {
private:
    int V; // 顶点数
    Node adjList[MAX_N]; // 邻接表
    int adder;

public:
    Graph(int vertices) : V(vertices), adder(0) {
        for (int i = 0; i <= V; ++i) {
            adjList[i] = Node(i);
        }
    }

    void addEdge(int u, int v) {
        adjList[u].val = u;
        adjList[v].val = v;
        adjList[v].children[adjList[v].childCount++] = &adjList[u]; // u -> v
        adjList[u].father = &adjList[v];          // 设置父节点
    }

    Node* findRoot() {
        for (int i = 1; i <= V; ++i) {
            if (adjList[i].father == nullptr) {
                return &adjList[i];
            }
        }
        return nullptr; // 理论上不会发生
    }

    void DFS(Node* current) {
        current->in = adder++;
        for (int i = 0; i < current->childCount; ++i) {
            DFS(current->children[i]);
        }
        current->out = adder++;
    }

    bool isAncestor(int x, int y) const {
        return adjList[y].in <= adjList[x].in && adjList[y].out >= adjList[x].out;
    }
};

int main() {
    int T;
    scanf("%d", &T);

    while (T--) {
        int n, m;
        scanf("%d %d", &n, &m);

        Graph g(n);

        for (int j = 0; j < n - 1; ++j) {
            int x, y;
            scanf("%d %d", &x, &y);
            g.addEdge(x, y);
        }

        g.DFS(g.findRoot());

        for (int j = 0; j < m; ++j) {
            int x, y;
            scanf("%d %d", &x, &y);
            printf("%s\n", g.isAncestor(x, y) ? "Yes" : "No");
        }
    }

    return 0;
}
