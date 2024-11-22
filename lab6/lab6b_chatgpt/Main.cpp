#include <iostream>
#include <vector>
//#include <algorithm>
using namespace std;

// 自定义队列实现
class SimpleQueue {
private:
    int *data;     // 存储队列元素的数组
    int head, tail; // 队列头和尾指针
    int capacity;  // 队列容量

public:
    SimpleQueue(int size) {
        data = new int[size];
        head = 0;
        tail = 0;
        capacity = size;
    }
    ~SimpleQueue() {
        delete[] data;
    }
    void push(int x) {
        data[tail++] = x; // 将元素插入尾部
    }
    int front() {
        return data[head]; // 返回队首元素
    }
    void pop() {
        ++head; // 移动头指针
    }
    bool empty() {
        return head == tail; // 判断队列是否为空
    }
};

int main() {
    int T;
    cin >> T; // 读取测试用例数量

    while (T--) {
        int N;
        cin >> N; // 读取节点数量
        vector<vector<int>> tree(N + 1); // 用于存储树，1 到 N 的节点
        for (int i = 2; i <= N; ++i) {
            int parent;
            cin >> parent;
            tree[parent].push_back(i); // 将子节点加入对应父节点的列表
        }
        // 确保每个父节点的子节点按升序排序
        for (int i = 1; i <= N; ++i) {
            sort(tree[i].begin(), tree[i].end());
        }
        // 广度优先遍历（BFS）
        SimpleQueue q(N);   // 使用自定义队列
        vector<int> result; // 存储遍历结果
        q.push(1); // 从根节点 1 开始
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            result.push_back(node); // 当前节点加入结果
            // 将子节点加入队列
            for (int child : tree[node]) {
                q.push(child);
            }
        }
        // 输出结果
        for (size_t i = 0; i < result.size(); ++i) {
            if (i > 0) cout << " ";
            cout << result[i];
        }
        cout << endl;
    }
    return 0;
}