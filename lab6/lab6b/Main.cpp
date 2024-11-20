//
// Created by purple on 24-11-20.
//
#include <iostream>
#include <vector>
#include <array>
using namespace std;
struct Node {
    int val;                      // 节点值
    vector<Node*> children;       // 子节点，用指针存储
    // 构造函数，使用初始化列表
    Node(int val) : val(val) {}
};
int main() {
    int N;
    cin >> N;
    Node** nodes = new Node*[N+1];
    for(int i=0;i<N+1;i++) {
        nodes[i]=new Node(i);
    }
    for(int i=1;i<N;i++) {
        int a_i;
        cin >> a_i;
        nodes[a_i]->children.push_back(nodes[i+1]);
    }
    return 0;
}


