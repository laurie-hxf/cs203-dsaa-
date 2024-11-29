//
// Created by purple on 24-11-27.
//
#include <iostream>
#include <vector>
using namespace std;
struct Node {
  int index;
  long long p_i;
  long long e_i;
  int flag;
  int father;
  vector<int> children;
  Node() : index(0), p_i(0), flag(1),father(0){}
  Node(int index):index(index) {
    flag = 1;//true
    p_i=0;
    e_i=0;
    father=0;
  }
};
void dfs(Node &node,vector<Node> &nodes) ;
void dfs_2(Node &node,vector<Node> &nodes);
int main() {
  int n;
  scanf("%d",&n);
  vector<Node> nodes(n+1);
  for(int i=1;i<n+1;i++) {
    nodes[i]= Node(i);
  }
  for(int i=1;i<n;i++) {
    int node1,node2;
    scanf("%d %d",&node1,&node2);
    nodes[node1].children.push_back(node2);
    nodes[node2].children.push_back(node1);
  }
  int root=0;
  for(int i=1;i<n+1;i++) {
    long long p_i;
    scanf("%lld",&p_i);
    nodes[i].p_i=p_i;
    nodes[i].e_i=p_i;
    if(p_i>nodes[root].p_i) {
      root = i;
    }
  }
  Node &root_node=nodes[root];
  dfs(root_node,nodes);
  int sub_tree=root_node.children.size();

  if(sub_tree>1) {
    long long largest=0;
    long long largest_e_i=0;
    long long second_largest=0;
    long long second_largest_e_i=0;
    for(int i=0;i<sub_tree;i++) {
      Node child=nodes[root_node.children[i]];
      if(child.e_i>largest_e_i) {
        long long temp=largest;
        long long temp_e_i=largest_e_i;
        largest=child.index;
        largest_e_i=child.e_i;
        second_largest=temp;
        second_largest_e_i=temp_e_i;
      }else if(child.e_i>second_largest_e_i) {
        second_largest=child.index;
        second_largest_e_i=child.e_i;
      }
    }
    nodes[largest].e_i=root_node.e_i;
    nodes[second_largest].e_i=root_node.e_i;
    root_node.flag=1;
    for(int i=0;i<sub_tree;i++) {
      dfs_2(nodes[root_node.children[i]],nodes);
    }
  }
  else if(sub_tree==1) {
    Node &child=nodes[root_node.children[0]];
    child.e_i=root_node.e_i;
    root_node.flag=1;
    dfs_2(child,nodes);
  }
  long long sum =0;
  for(int i=0;i<n+1;i++) {
    if(nodes[i].children.size()==1) {
      sum+=nodes[i].e_i;
    }
  }
  printf("%lld",sum);
  return 0;
}


void dfs(Node &node,vector<Node> &nodes) {
  node.flag=0;
  if(node.children.size()==1&&node.father!=0) {
    node.e_i=node.p_i;
    return ;
  }
  for(int i=0;i<node.children.size();i++) {
    Node &temp=nodes[node.children[i]];
    if(nodes[node.children[i]].flag==1) {
      temp.father=node.index;
      dfs(nodes[node.children[i]],nodes);
    }
  }
  for(int i=0;i<node.children.size();i++) {
    Node child=nodes[node.children[i]];
    if(node.e_i<child.e_i&&child.index!=node.father) {
      node.e_i=child.e_i;
    }
  }
}

void dfs_2(Node &node,vector<Node> &nodes) {
  node.flag=1;
  node;
  int max=0;
  for(int i=0;i<node.children.size();i++) {
    if(nodes[node.children[i]].flag==0) {
      Node &temp=nodes[node.children[i]];
      if(temp.e_i>nodes[max].e_i) {
        max=temp.index;
      }
    }
  }
  for(int i=0;i<node.children.size();i++) {
    Node &temp=nodes[node.children[i]];
    if(nodes[node.children[i]].flag==0) {
      if(temp.index==max) {
        nodes[max].e_i=node.e_i;
        dfs_2(nodes[node.children[i]],nodes);
      }else {
        dfs_2(nodes[node.children[i]],nodes);
      }
    }
  }
}


