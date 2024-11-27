//
// Created by purple on 24-11-27.
//
#include <iostream>
#include <vector>
using namespace std;
struct Node {
    int index;
    int gaint;
    int flag;
    int depth;
    //int parent;
    vector<int> children;
    Node(int _index) {
        index = _index;
        gaint = 0;
        flag = 1;
        depth = 0;
        //parent = _parent;
    }
    Node () {
        index = 0;
        gaint = 0;
        flag = 1;
        depth = 0;
        //parent = 0;
    }
};
int main() {
    int n;
    scanf("%d", &n);
    vector<Node> nodes(n+1);
    for(int i=0;i<n+1;i++) {
        nodes[i]= Node(i);
    }
    for(int i=1;i<n;i++) {
        int node1, node2;
        scanf("%d %d",&node1,&node2);
        nodes[node1].children.push_back(node2);
        nodes[node2].children.push_back(node1);
    }

    //确定哪些node一开始有gaint
    int gaint_number;
    scanf("%d",&gaint_number);
    for(int i=0;i<gaint_number;i++) {
        int gaint;
        scanf("%d",&gaint);
        nodes[gaint].gaint=1;
    }

    int sub_tree=nodes[1].children.size();
    int max=0;
    for(int i=0;i<sub_tree;i++) {
        //层序遍历
        //vector<int>arr(gaint_number);
        int* arr = new int[gaint_number];
        vector<int> queue(n+1);
        int front=0;
        int rear=0;
        queue[rear++] = nodes[1].children[i];
        nodes[nodes[1].children[i]].depth=1;
        nodes[1].flag=0;
        int count=0;
        while (front != rear) {
            Node& h=nodes[queue[front++]];
            h.flag=0;
            int depth=h.depth;
            if(h.gaint) {
                arr[count++] = h.depth;
            }
            for (int i=0;i<h.children.size();i++) {
                if(nodes[h.children[i]].flag==1) {
                    queue[rear++] = h.children[i];
                    nodes[h.children[i]].flag=0;
                    nodes[h.children[i]].depth=depth+1;
                }
            }
        }
        int temp=0;
        for(int j=0;j<gaint_number;j++) {
            if(arr[j]!=0) {
                temp++;
                if(temp<arr[j]) {
                    temp=arr[j];
                }
            }
        }
        if(temp>=max) {
            max=temp;
        }
    }
   printf("%d",max);
    return 0;
}