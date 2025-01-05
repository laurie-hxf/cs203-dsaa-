#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

// 定义邻接表存储图
struct Edge {
    int to;         // 城市编号仍用 int 表示
    long long weight; // 边权改为 long long
};

long long dijkstra(int n, vector<vector<Edge>>& graph) {
    // 最短距离数组，初始值为无穷大
    vector<long long> dist(n + 1, LLONG_MAX);
    dist[1] = 0; // 起点城市的距离为 0

    // 最小堆，存储 (当前距离, 当前节点)
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    pq.push({0, 1}); // 起点加入堆

    while (!pq.empty()) {
        auto top = pq.top(); // 取出堆顶
        pq.pop();

        long long d = top.first;
        int u = top.second;

        // 如果距离不是当前最短路径，跳过
        if (d > dist[u]) continue;

        // 遍历邻接节点
        for (const auto& edge : graph[u]) {
            int v = edge.to;
            long long w = edge.weight;
            if (dist[u] + w < dist[v]) { // 更新最短路径
                dist[v] = dist[u] + w;
                pq.push({dist[v], v});
            }
        }
    }

    // 返回城市 n 的最短距离，无法到达返回 -1
    return dist[n] == LLONG_MAX ? -1 : dist[n];
}

int main() {
    int n, m;
    cin >> n >> m;

    // 构建图
    vector<vector<Edge>> graph(n + 1);
    for (int i = 0; i < m; ++i) {
        int u, v;
        long long w; // 边权改为 long long
        cin >> u >> v >> w;
        graph[u].push_back({v, w}); // 添加单向边
    }

    // 求从城市 1 到城市 n 的最短路径
    cout << dijkstra(n, graph) << endl;

    return 0;
}
