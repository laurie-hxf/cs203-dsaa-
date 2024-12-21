//
// Created by purple on 24-12-18.
//

#include <vector>
#include <iostream>
using namespace std;
const int dx[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
const int dy[8] = {-1, 0, 1, -1, 1, -1, 0, 1};

// 检查是否在矩阵边界内
bool isValid(int x, int y, int n, int m) {
    return x >= 0 && x < n && y >= 0 && y < m;
}
int maxsum=0;

void dfs(int x, int y, vector<vector<int>>& matrix, vector<vector<bool>>& visited, int n, int m,int sum){
    if (x == n)
    {
        maxsum = max(sum, maxsum);
        return;
    }
    if(!visited[x][y]) {
        visited[x][y] = true;
        int currentValue = matrix[x][y];
        vector<pair<int, int>> neighbors;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(nx, ny, n, m) && !visited[nx][ny]) {
                neighbors.push_back({nx, ny});
                visited[nx][ny] = true; // 暂时标记为访问
            }
        }

        if(y<m-1) {
            dfs(x,y+1,matrix,visited,n,m,sum+currentValue);
        }
        else  {
            dfs(x+1,0,matrix,visited,n,m,sum+currentValue);
        }
        for (auto& neighbor : neighbors) {
            visited[neighbor.first][neighbor.second] = false;
        }
        visited[x][y] = false;
    }

    if(y<m-1) {
        dfs(x,y+1,matrix,visited,n,m,sum);
    }
    else{
        dfs(x+1,0,matrix,visited,n,m,sum);
    }

}
int main() {
    int T=0;
    scanf("%d",&T);
    for(int i=0;i<T;i++) {
        maxsum=0;
        int n,m;
        scanf("%d %d",&n,&m);
        vector<vector<int>> matrix(n, vector<int>(m));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value=0;
                scanf("%d", &value);
                matrix[i][j]=value;
            }
        }

        vector<vector<bool>> visited(n, vector<bool>(m, false));

        dfs(0,0,matrix,visited,n,m,0);
        printf("%d\n",maxsum);
    }
    return 0;
}

