#include <iostream>
using namespace std;

int main() {
    int T; 
    cin >> T; // 测试用例数量

    while (T--) {
        long long N, K;
        cin >> N >> K; // 节点数和叉树参数

        long long remaining_nodes = N - 1; // 除根节点外的剩余节点
        long long current_level_nodes = 1; // 当前层节点数

        while (remaining_nodes > 0) {
            if (current_level_nodes * K <= remaining_nodes) {
                // 如果当前层能够完全扩展到下一层
                remaining_nodes -= current_level_nodes * K;
                current_level_nodes *= K;
            } else {
                // 剩余节点不足以扩展到下一层
                long long no_child=(remaining_nodes+K-1)/K;
                cout << remaining_nodes+current_level_nodes-no_child  << endl; // 剩余节点就是叶节点
                break;
            }
        }

        if (remaining_nodes == 0) {
            // 如果刚好分配完，当前层节点就是叶节点
            cout << current_level_nodes << endl;
        }
    }

    return 0;
}