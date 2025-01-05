
 //Created by purple on 24-12-21.

 #include <vector>
 #include <iostream>
 #include <algorithm>
 using namespace std;
 using i64 = long long;

 int dirX[8] = {0, 0, 1, -1, 1, -1, 1, -1};
 int dirY[8] = {1, -1, 0, 0, 1, -1, -1, 1};
 int n, m;
 i64 maxSum = 0;
 vector<vector<int>> map;
 vector<vector<int>> visited;

 void dfs(int x, int y, i64 sum)
 {
     if (x == n)
     {
         maxSum = max(maxSum, sum);
         return;
     }

     if (!visited[x][y])
     {
         visited[x][y]++;
         i64 nextsum = sum + map[x][y];


         for (int d = 0; d < 8; ++d)
         {
             int ni = x + dirX[d];
             int nj = y + dirY[d];
             if (ni >= 0 && ni < n && nj >= 0 && nj < m)
             {
                 visited[ni][nj]++;
             }
         }

         if (y + 1 < m)
         {
             dfs(x, y + 1, nextsum);
         }
         else
         {
             dfs(x + 1, 0, nextsum);
         }

         for (int d = 0; d < 8; ++d)
         {
             int ni = x + dirX[d];
             int nj = y + dirY[d];
             if (ni >= 0 && ni < n && nj >= 0 && nj < m)
             {
                 visited[ni][nj]--;
             }
         }
         visited[x][y] --;
     }

     if (y + 1 < m)
     {
         dfs(x, y + 1, sum);
     }
     else
     {
         dfs(x + 1, 0, sum);
     }
 }

 void solve()
 {
     cin >> n >> m;
     map.assign(n, vector<int>(m));
     visited.assign(n, vector<int>(m,0));
     for (int i = 0; i < n; i++)
     {
         for (int j = 0; j < m; j++)
         {
             cin >> map[i][j];
         }
     }

     maxSum = 0;
     dfs(0, 0, 0);

     cout << maxSum << endl;
 }

 int main()
 {
     ios::sync_with_stdio(false);
     cin.tie(nullptr);
     int t;
     cin >> t;
     while (t--)
     {
         solve();
     }
     return 0;
 }

