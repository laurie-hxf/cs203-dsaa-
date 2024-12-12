//
// Created by purple on 24-12-8.
//

#include <iostream>
struct carrots {
    int carrot;
    bool ghost;
    carrots* next;
    carrots* prev;
    int index;
    carrots() {carrot=0;ghost=false;next =nullptr;prev=nullptr;index=0;};
    carrots(int carrot,int index) :carrot(carrot),ghost(false),next(nullptr),prev(nullptr),index(index){};
};
bool compare(carrots *a, carrots *b) {
    if (a->carrot != b->carrot) return a->carrot < b->carrot; // 按值升序
    return a->index < b->index;                      // 按索引升序
}
void root_fix(carrots **heap,int current,int size) {
    while (true) {
        int smallest = current;           // 当前节点
        int left = current*2;    // 左子节点
        int right = current*2+1;  // 右子节点

        if (left <= size && compare(heap[left], heap[smallest])) {
            smallest = left;
        }

        if (right <= size && compare(heap[right], heap[smallest])) {
            smallest = right;
        }

        if (smallest == current) break;

        carrots *temp = heap[current];
        heap[current] = heap[smallest];
        heap[smallest] = temp;
        current = smallest;
    }
}

int main() {
    int N=0;
    scanf("%d", &N);
    carrots *link_list[N+1];
    carrots *heap[N+1];
    int size=N;
    link_list[0] = nullptr;
    for(int i=1;i<N+1;i++) {
        int carrot=0;
        scanf("%d",&carrot);
        carrots *in = new carrots(carrot,i);
        link_list[i] = in;
        heap[i] =in;
        if(i-1!=0) {
            link_list[i-1]->next = link_list[i];
        }
        link_list[i]->prev = link_list[i-1];
    }
    for(int i=N;i>0;i--) {                  //O(n) build heap
        int current =i;
        root_fix(heap,current,size);
    }
    for(int i=1;i<N;i++) {
        while(heap[1]->ghost) {
            int last =size;
            heap[1]=heap[last];
            size--;
            root_fix(heap,1,size);

        }
        int left=-1;
        int right=-1;
        if(heap[1]->prev!=nullptr) {
            left = (heap[1]->prev->carrot ^ heap[1]->carrot) +1; //compute with left neighbour
        }
        if(heap[1]->next!=nullptr) {
            right = (heap[1]->next->carrot ^ heap[1]->carrot) +1;//compute with right neighbour
        }
        if(left>=right) {                                       //merge with left neighbour
            heap[1]->carrot = left;
            heap[1]->prev->ghost=true;
            if(heap[1]->prev->prev!=nullptr) {
                heap[1]->prev->prev->next=heap[1];
                heap[1]->prev=heap[1]->prev->prev;
            }else {
                heap[1]->prev=nullptr;
            }
        }else {                                                 //merge with right neighbour
            heap[1]->carrot = right;
            heap[1]->next->ghost=true;
            if(heap[1]->next->next!=nullptr) {
                heap[1]->next->next->prev=heap[1];
                heap[1]->next=heap[1]->next->next;
            }else {
                heap[1]->next=nullptr;
            }
        }

        int current = 1;
        root_fix(heap,current,size);
    }
    while(heap[1]->ghost) {
        int last =size;
        heap[1]=heap[last];
        size--;
        root_fix(heap,1,size);

    }
    printf("%d\n",heap[1]->carrot);
    return 0;
}
