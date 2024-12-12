//
// Created by purple on 24-12-4.
//


class Player {
int power;
int time;

Player() {
    power = 0;
    time = 0;
}

Player(int power, int time): power(power), time(time) {
        }
        };

class heap {
    private :
    vector<int> Players;
    int size;

    public:
    explicit heap(int size): size(0) ,Players(size) {
    }
    ~heap() = default;
    void insert(int in) {
        size++;
        Players[size] = in;
        int current = size;
        int parent = (current) / 2;
        while (parent > 1 && Players[current] < Players[parent]) {
            int  temp = Players[current];
            Players[current] = Players[parent];
            Players[parent] = temp;
            current = current / 2;
            parent = (current) / 2;
        }
    }

    void delet() {
        int last = Players[size];
        size--;
        Players[1] = last;
        int current = 1;
        while (current * 2 <= size) {
            if (current * 2 + 1 <= size &&( (Players[current] > Players[current*2]) || Players[current] > Players[current*2+1]))  {
                int son_1 = Players[current * 2];
                int son_2 = Players[current * 2 + 1];
                if (son_1 < son_2) {
                    Players[current * 2] = Players[current];
                    Players[current] = son_1;
                    current = current * 2;
                } else {
                    Players[current * 2 + 1] = Players[current];
                    Players[current] = son_2;
                    current = current * 2+1;
                }
            } else if(Players[current] > Players[current*2]) {
                int temp = Players[current * 2];
                Players[current * 2] = Players[current];
                Players[current] = temp;
                current = current * 2;
            }
            else {
                break;
            }
        }
    }

    int  sum() {
        int sum = 0;
        for (int i = 1; i <= size; i++) {
            sum += Players[i];
        }
        return sum;
    }
    int root() {
        int root = Players[1];
        return root;
    }

};

void merge(vector<Player> players, int left, int mid, int right);

void mergeSort(vector<Player> &players, int left, int right);

int main() {
    int temp_case;
    scanf("%d", &temp_case);
    for (int i = 0; i < temp_case; i++) {
        int number;
        scanf("%d", &number);
        vector<Player> players(number + 1);
        for (int j = 1; j < number + 1; j++) {
            int power;
            scanf("%d", &power);
            players[j] = Player(power, 0);
        }
        for (int j = 1; j < number + 1; j++) {
            int time;
            scanf("%d", &time);
            players[j].time = time;
        }
        int length = players.size();
        mergeSort(players, 0, length - 1);
        int size = 0;
        int t = 0;
        heap heap(number+1);
        for (int j = 1; j < number + 1; j++) {
            Player temp = players[j];
            t = temp.time;
            if (size < t) {
                heap.insert(temp.power);
                size++;
            }else {
                if(temp.power > heap.root()) {
                    heap.delet();
                    heap.insert(temp.power);
                }
            }
        }
        printf("%d\n", heap.sum());
    }
    return 0;
}

// 合并两个有序子数组
void merge(vector<Player> &players, int left, int mid, int right) {
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
void mergeSort(vector<Player> &players, int left, int right) {
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
