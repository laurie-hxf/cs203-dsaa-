//
// Created by purple on 24-12-17.
//
#include <iostream>
#include <vector>
using namespace std;
// 定义图的类
struct Node {
    int val;
    int path;
    vector<Node*> children;
    Node() : val(0), path(-1) {}
    explicit Node(int val):val(val),path(-1){}
};

class Graph {
private:
    int V; // 顶点数
    vector<Node> adjList; // 邻接表
    vector<Node*> queue;
    int queueHead=0;
    int queueTail=0;
public:
    // 构造函数
    Graph(int vertices) {
        V = vertices;//顶点
        adjList.resize(V+1);
    }

    // 添加边（无向图）
    void addEdge(int u, int v) {
        adjList[u].val=u;
        adjList[v].val=v;
        adjList[u].children.push_back(&adjList[v]); // u -> v
        adjList[v].children.push_back(&adjList[u]); // v -> u
    }

    void shortestPath(int s) {
        Node& node =adjList[s];
        node.path = 0;
        queue.push_back(&node);
        queueTail++;
        while(queueHead != queueTail) {
            Node* temp = queue[queueHead++];
            for(int i=0;i<temp->children.size();i++) {
                if(temp->children[i]->path == -1) {
                    temp->children[i]->path =temp->path+1;
                    queue.push_back(temp->children[i]);
                    queueTail++;
                }
            }
        }
    }
    void printPath() {
        for(int i=1;i<adjList.size();i++) {
            printf("%d ",adjList[i].path);
        }
    }

};

int main() {
    int T=0;
    scanf("%d",&T);
    for(int i=0;i<T;i++) {
        int n,m,s;
        scanf("%d %d %d",&n,&m,&s);
        Graph g(n);
        for(int j=0;j<m;j++) {
            int u,v;
            scanf("%d %d",&u,&v);
            g.addEdge(u,v);
        }
        g.shortestPath(s);
        g.printPath();
        printf("\n");
    }

    return 0;
}