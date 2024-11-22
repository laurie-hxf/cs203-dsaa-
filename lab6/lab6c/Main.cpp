//
// Created by purple on 24-11-20.
//
#include <stdio.h>
#include <iostream>
#include <vector>
using namespace std;
struct Node {
  int index;
  int val;
  int flag;
  vector<int> children;
  vector<int> weight;
  Node() : index(0), val(0), flag(1), children(), weight() {}
  Node(int index):index(index) {
    flag = 1;//true
    val=0;
    weight.clear();
  }
};
int main() {
  int n,num;
  scanf("%d %d",&n,&num);
  vector<Node> nodes(n + 1);
  for(int i=0;i<n+1;i++) {
    nodes[i]= Node(i);
    //nodes[i].weight.resize(n);
  }
  for(int i=1;i<n;i++) {
    int node1,node2,weight;
    scanf("%d %d %d",&node1,&node2,&weight);
    nodes[node1].children.push_back(node2);
    nodes[node2].children.push_back(node1);
    nodes[node1].weight.push_back(weight);
    nodes[node2].weight.push_back(weight);
  }
  vector<int> queue(n+1);
  int front=0;
  int rear=0;
  queue[rear++] = 1;
  while (front != rear) {
    Node& h=nodes[queue[front++]];
    h.flag=0;
    //printf("%d ",h.val);
    for (int i=0;i<h.children.size();i++) {
      if(nodes[h.children[i]].flag==1) {
        queue[rear++] = h.children[i];
        nodes[h.children[i]].val+=h.weight[i]+h.val;
        nodes[h.children[i]].flag=0;
      }
    }
  }
  int count=0;
  for(int i=1;i<n+1;i++) {
    if(num==0) {
      count=0;
    }
    else if(nodes[i].val==num&&nodes[i].children.size()==1) {
      count++;
    }
  }
  printf("%d",count);
  return 0;
}