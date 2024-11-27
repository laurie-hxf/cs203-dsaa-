//
// Created by purple on 24-11-27.
//
#include <iostream>
#include <vector>
using namespace std;
struct Node {
  int index;
  int p_i;
  int e_i;
  int flag;
  vector<int> children;
  Node() : index(0), p_i(0), flag(1), children(){}
  Node(int index):index(index) {
    flag = 1;//true
    p_i=0;
    e_i=0;
  }
};
int main() {
  int n;
  scanf("%d",&n);
  vector<Node> nodes(n+1);
  for(int i=1;i<n+1;i++) {
    nodes[i]= Node(i);
  }
  for(int i=1;i<n+1;i++) {
    int node1,node2;
    scanf("%d %d",&node1,&node2);
    nodes[node1].children.push_back(node2);
    nodes[node2].children.push_back(node1);
  }
  int root=0;
  for(int i=1;i<n+1;i++) {
    int p_i;
    scanf("%d",&p_i);
    nodes[i].p_i=p_i;
    if(p_i>root) {
      root = i;
    }
  }
  int sub_tree=nodes[root].children.size();
  for(int i=0;i<sub_tree;i++) {
    
  }
  return 0;
}
