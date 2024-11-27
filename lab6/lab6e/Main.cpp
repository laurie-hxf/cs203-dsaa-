//
// Created by purple on 24-11-27.
//
#include <iostream>
#include <vector>
using namespace std;
struct Node {
    int index;
    int gaint;
    int parent;
    vector<int> children;
    Node(int _index, int _gaint, int _parent) {
        index = _index;
        gaint = _gaint;
        parent = _parent;
    }
    Node () {
        index = 0;
        gaint = 0;
        parent = 0;
    }
};
int main() {
    int n;
    vector<Node> nodes(n+1);
    scanf("%d", &n);
    for(int i=0;i<n+1;i++) {
        nodes[i]= Node(i,0,0);
    }
    for(int i=1;i<n;i++) {

    }
    return 0;
}