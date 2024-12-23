//
// Created by purple on 24-12-23.
//
#include <iostream>
#include <vector>
using namespace std;
struct Node {
    int val;
    int in;
    int out;
    Node* father;
    vector<Node*> children;
    Node() : val(0), in(0),out(0),father(nullptr) {}
    explicit Node(int val):val(val),in(0),out(0),father(nullptr){}
};

class Graph {
private:
    int V; // 顶点数
    vector<Node> adjList; // 邻接表
    int adder = 0;
public:
    // 构造函数
    Graph(int vertices) {
        V = vertices;//顶点
        adjList.resize(V+1);
    }
    void addEdge(int u, int v) {
        adjList[u].val=u;
        adjList[v].val=v;
        adjList[v].children.push_back(&adjList[u]); // u -> v
        adjList[u].father=&adjList[v];              //
    }
    Node* findRoot() {
        Node* temp=&adjList[1];
        while(temp->father!=nullptr) {
            temp=temp->father;
        }
        return temp;
    }

    void DFS(Node *current) {
        current->in=adder++;
        int size = current->children.size();
        for(int i=0;i<size;i++) {
            DFS(current->children[i]);
        }
        current->out=adder++;
    }
    string judge(int x,int y) {
        if(adjList[y].in<=adjList[x].in&&adjList[y].out>=adjList[x].out) {
            return "Yes";
        }
        return "No";
    }
};
int main() {
    int T=0;
    scanf("%d",&T);
    for(int i=0;i<T;i++) {
        int n,m;
        scanf("%d %d",&n,&m);
        Graph g(n);
        for(int j=0;j<n-1;j++) {
            int x,y;
            scanf("%d %d",&x,&y);
            g.addEdge(x,y);
        }
        g.DFS(g.findRoot());
        for(int j=0;j<m;j++) {
            int x,y;
            scanf("%d %d",&x,&y);
            printf("%s\n",g.judge(x,y).c_str());
        }
    }
    return 0;
}