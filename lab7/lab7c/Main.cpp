//
// Created by purple on 24-12-4.
//
#include <iostream>
#include <vector>
using namespace std;
struct Player {
    int power;
    int time;
    Player() {
        power = 0;
        time = 0;
    }
    Player(int power, int time):power(power),time(time) {}
};
class heap {
    private :
        vector<Player> Players;
        int size;
    public:
        heap():size(0){}
        void insert(Player) {
            Players.push_back(Player());
            for(int i=Players.size()-1; i>=0; i--) {

            }
        }
        void delet() {

        }
};
void merge(vector<Player>& players, int left, int mid, int right);
void mergeSort(vector<Player>& players, int left, int right);
int main() {
    int temp_case;
    scanf("%d",&temp_case);
    for(int i=0;i<temp_case;i++) {
        int number;
        scanf("%d",&number);
        vector<Player> players(number+1);
        for(int j=1;j<number+1;j++) {
            int power;
            scanf("%d",&power);
            players.push_back(Player(power,0));
        }
        for(int j=1;j<number+1;j++) {
            int time;
            scanf("%d",&time);
            players[j].time = time;
        }
        mergeSort(players, 0, players.size() - 1);
        int size=0;
        int t=0;
        vector<Player> heap(number+1);
        for(int j=1;j<number+1;j++) {
            t=players[j].time;
            if(size<t) {

            }
        }
    }
    return 0;
}

// 合并两个有序子数组
void merge(vector<Player>& players, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    // 创建临时数组
    vector<Player> leftArr(n1);
    vector<Player> rightArr(n2);

    // 拷贝数据到临时数组
    for (int i = 0; i < n1; ++i) {
        leftArr[i] = players[left + i];
    }
    for (int j = 0; j < n2; ++j) {
        rightArr[j] = players[mid + 1 + j];
    }

    // 合并两个临时数组
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (leftArr[i].time <= rightArr[j].time) {
            players[k] = leftArr[i];
            i++;
        } else {
            players[k] = rightArr[j];
            j++;
        }
        k++;
    }

    // 拷贝剩余的元素
    while (i < n1) {
        players[k] = leftArr[i];
        i++;
        k++;
    }
    while (j < n2) {
        players[k] = rightArr[j];
        j++;
        k++;
    }
}

// 递归的 Merge Sort
void mergeSort(vector<Player>& players, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2; // 计算中点

        // 排序左半部分
        mergeSort(players, left, mid);

        // 排序右半部分
        mergeSort(players, mid + 1, right);

        // 合并两个已排序的子数组
        merge(players, left, mid, right);
    }
}
