//
// Created by purple on 25-1-5.
//
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <climits>

using namespace std;

const int INF = INT_MAX;

void bfs(int n, const vector<vector<int>> &graph, const vector<int> &startNodes, vector<int> &dist,vector<int> &stoneColor) {
    queue<int> q;
    vector<bool> visited(n + 1, false);

    for (int node : startNodes) {
        q.push(node);
        dist[node] = 0;
        visited[node] = true;
    }

    while (!q.empty()) {
        int u = q.front();
        q.pop();

        for (int v : graph[u]) {
            if (!visited[v]) {
                visited[v] = true;
                dist[v] = dist[u] + 1;
                q.push(v);
            }
        }
    }
}

int main() {
    int n, m, k, c;
    //cin >> n >> m >> k >> c;
    scanf("%d %d %d %d",&n,&m,&k,&c);

    vector<vector<int>> graph(n + 1);
    vector<int> stoneColor(n + 1);

    // 读取石头颜色
    for (int i = 1; i <= n; ++i) {
        int color;
        scanf("%d",&color);
        stoneColor[i] = color;
        // cin >> stoneColor[i];
    }

    // 读取图的边
    for (int i = 0; i < m; ++i) {
        int u, v;
        scanf("%d %d",&u,&v);
        // cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }


    // 按颜色存储起始点
    vector<vector<int>> colorNodes(k + 1);
    for (int i = 1; i <= n; ++i) {
        colorNodes[stoneColor[i]].push_back(i);
    }

    // 计算每种颜色到所有点的最短距离
    vector<vector<int>> distances(k + 1, vector<int>(n + 1, INF));
    for (int col = 1; col <= k; ++col) {
        bfs(n, graph, colorNodes[col], distances[col],stoneColor);
    }

    // 计算每个顶点 T 的最小代价
    for (int t = 1; t <= n; ++t) {
        vector<int> costs;
        for (int col = 1; col <= k; ++col) {
            costs.push_back(distances[col][t]);
        }
        sort(costs.begin(), costs.end()); // 按距离从小到大排序

        // 累加前 c 个最小值
        int minCost = 0;
        for (int i = 0; i < c; ++i) {
            minCost += costs[i];
        }
        printf("%d ", minCost);

    }

    return 0;
}
