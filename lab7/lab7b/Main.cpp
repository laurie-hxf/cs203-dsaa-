//
// Created by purple on 24-12-8.
//

#include <iostream>
#include <vector>
struct node {
    int index;
    int value;
    int x;
    int y;
    node ():index(0), value(0), x(0), y(0) {}
    node (int index, int value):index(index),value(value){}
};
using namespace std;
class MaxHeap {
    vector<node> heap;
    int sum=0;
    void swap() {
        int n = sum;
        while(heap[n/2].value<heap[n].value && n/2>0) {
            int temp_val = heap[n/2].value;
            int temp_index = heap[n/2].index;
            heap[n/2].value = heap[n].value;
            heap[n/2].index = heap[n].index;
            heap[n].value = temp_val;
            heap[n].index = temp_index;
            n=n/2;
        }
    }
public:
    explicit MaxHeap (int size) :heap(size+1) {}//防止函数隐式调用
    ~MaxHeap() = default;
    int level = 0;

    void insert(int index, int value) {
        heap[0]=node(0,0);
        sum++;
        int position = 0;
        int temp =(1<<level)-1;
        if(sum > temp) {
            position =sum - temp;
            level++;
        }else {
            position = sum - (1<<(level-1))+1;
        }
        node n = node(index, value);
        n.x=level;
        n.y=position;
        heap[index]=n;
        swap();
    }
    void find(int index) {
        for(int i=1;i<heap.size();i++) {
            if(heap[i].index == index) {
                printf("%d %d\n",heap[i].x,heap[i].y);
            }
        }
    }
};
int main() {
    int test_case = 0;
    scanf("%d", &test_case);
    for (int i = 0; i < test_case; i++) {
        int n;
        scanf("%d", &n);
        MaxHeap heap(n);
        for (int j = 1; j < n+1; j++) {
            int val=0;
            scanf("%d", &val);
            heap.insert(j, val);
        }
        int index = 0;
        scanf("%d", &index);
        heap.find(index);
    }

    return 0;

}