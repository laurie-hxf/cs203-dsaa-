#include <iostream>
#include <vector>
using namespace std;

int main() {
    int N;
    cin >> N;
    vector<int> tree(N + 1);
    for (int i = 1; i <= N; i++) {
        cin >> tree[i];
    }


    bool isMaxHeap = true, isMinHeap = true;

    for (int i = 2; i <= N; i++) {
        if (tree[i] > tree[i / 2]) {
            isMaxHeap = false;
        }
        if (tree[i] < tree[i / 2]) {
            isMinHeap = false;
        }

        if (!isMaxHeap && !isMinHeap) {
            break;
        }
    }


    if (isMaxHeap) {
        cout << "Max" << endl;
    } else if (isMinHeap) {
        cout << "Min" << endl;
    } else {
        cout << "Neither" << endl;
    }

    return 0;
}