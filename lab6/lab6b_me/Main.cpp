//
// Created by purple on 24-11-20.
//
//
// Created by purple on 24-11-20.
//
#include <iostream>
#include <vector>
using namespace std;
struct Node {
    int val;
    vector<int> children;
    Node() {
        val = 0;
        children.clear();
    }
    Node(int val) : val(val) {}
};
int main() {
    int testcase;
    scanf("%d",&testcase);
    for (int k = 0; k < testcase; k++) {
        int N;
        scanf("%d",&N);
        vector<Node> nodes(N + 1);
        for(int i=0;i<N+1;i++) {
            nodes[i]= Node(i);
        }
        for(int i=1;i<N;i++) {
            int a_i;
            scanf("%d",&a_i);
            nodes[a_i].children.push_back(i+1);
        }
        vector<int> queue(N + 1);
        int front =0;
        int rear =0;
        queue[rear++] = 1;
        while (front != rear) {
            Node h=nodes[queue[front++]];
            printf("%d ",h.val);
            for (int i=0;i<h.children.size();i++) {
                queue[rear++] = h.children[i];
            }
        }
        printf("\n");
    }
    return 0;
}


