#include <iostream>
#include <vector>
#include <queue>
#include <functional>
#include <limits.h>

using namespace std;

struct Edge {
    pair<int, int>from;
    pair<int, int>to;
    long long weight;
    Edge(pair<int,int> _from, pair<int,int> _to, long long _weight) : from(_from), to(_to), weight(_weight) {}
    bool operator<(const Edge &other) const {
        return weight < other.weight; // 优先队列需要最大堆
    }
};

long long maxPoints(int n, int m, vector<vector<int>> &grid) {
    // 最大堆，用于选择当前最大的边
    priority_queue<Edge> pq;
    vector<vector<bool>> visited(n, vector<bool>(m, false));
    long long maxScore = 0;

    // 初始化，从 (0, 0) 点开始
    // pq.push(Edge(0, 0, 0)); // 虚拟边，权重为 0


    // 四个方向的移动
    vector<int> dx = {-1, 1, 0, 0};
    vector<int> dy = {0, 0, -1, 1};

    for (int d = 0; d < 4; ++d) {
        int nx = 0 + dx[d];
        int ny = 0 + dy[d];
        if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
            long long newWeight = grid[0][0] * grid[nx][ny];
            pq.push(Edge(make_pair(0,0), make_pair(nx,ny), newWeight));
        }
    }
    visited[0][0] = true;


    while (!pq.empty()) {
        auto edge = pq.top();
        pq.pop();
        int x = edge.to.first, y = edge.to.second, weight = edge.weight;

        // 如果已经访问过，跳过
        if (visited[x][y]) continue;

        // 标记为已访问，并累计得分
        visited[x][y] = true;
        maxScore += weight;

        // 遍历四个方向，将未访问过的邻接点加入堆中
        for (int d = 0; d < 4; ++d) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                if(!visited[nx][ny]) {
                    long long newWeight = grid[x][y] * grid[nx][ny];
                    pq.push(Edge(make_pair(0,0), make_pair(nx,ny), newWeight));
                }
            }
        }
    }

    return maxScore;
}

int main() {
    // 读取输入
    int n, m;
    cin >> n >> m;
    vector<vector<int>> grid(n, vector<int>(m));

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> grid[i][j];
        }
    }

    // 计算最大得分
    cout << maxPoints(n, m, grid) << endl;

    return 0;
}
