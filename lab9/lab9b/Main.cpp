#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <set>
using namespace std;

// 定义边结构
struct Edge {
    int to;
    long long weight;
};

long long maximizeEdgeDeletionGain(int n, vector<vector<Edge>>& graph) {
    // 使用 Prim 算法构造最小生成树 (MST)
    vector<bool> inMST(n + 1, false); // 记录节点是否在 MST 中
    priority_queue<pair<long long, pair<int, int>>, vector<pair<long long, pair<int, int>>>, greater<pair<long long, pair<int, int>>>> pq;

    set<pair<pair<int, int>,long long>> mstEdges; // 记录 MST 中的边
    long long mstWeight = 0;     // MST 总权值
    vector<pair<int, int>> allEdges; // 记录所有边
    vector<long long> allWeights;    // 记录所有边的权重

    // 初始化，从节点 1 开始
    inMST[1] = true;
    for (const auto& edge : graph[1]) {
        pq.push(make_pair(edge.weight, make_pair(1, edge.to)));
        allEdges.push_back(make_pair(1, edge.to));
        allWeights.push_back(edge.weight);
    }

    while (!pq.empty()) {
        auto top = pq.top(); // 取出堆顶
        pq.pop();
        long long weight = top.first;
        int u = top.second.first, v = top.second.second;

        if (inMST[v]) continue; // 如果目标节点已在 MST 中，跳过

        // 加入 MST
        inMST[v] = true;
        mstWeight += weight;
        mstEdges.insert(make_pair(make_pair(min(u, v), max(u, v)),weight)); // 存储无向边

        // 遍历新增节点的所有边
        for (const auto& edge : graph[v]) {
            if (!inMST[edge.to]) {
                pq.push(make_pair(edge.weight, make_pair(v, edge.to)));
                allEdges.push_back(make_pair(min(v, edge.to), max(v, edge.to)));
                allWeights.push_back(edge.weight);
            }
            // 所有边（无向边）记录下来

        }
    }

    // Step 2: 遍历所有边，计算可以删除的正权边的总收益
    long long gain = 0;
    for (size_t i = 0; i < allEdges.size(); ++i) {
        const auto& edge = allEdges[i];
        if (mstEdges.find(make_pair(edge,allWeights[i])) == mstEdges.end() && allWeights[i] > 0) {
            // 非 MST 边，且权值为正
            gain += allWeights[i];
        }
    }
    return gain;
}

int main() {
    int n, m;
    cin >> n >> m;
    long long sum=0;
    // 构建图
    vector<vector<Edge>> graph(n + 1);
    for (int i = 0; i < m; ++i) {
        int u, v;
        long long w; // 边权
        cin >> u >> v >> w;
        if(u==v) {
            if(w>0){sum+=w;}
        }else{
        graph[u].push_back({v, w});
        graph[v].push_back({u, w}); // 无向图
        }
    }

    // 求解最大收益
    sum+= maximizeEdgeDeletionGain(n, graph) ;
    cout << sum << endl;

    return 0;
}
