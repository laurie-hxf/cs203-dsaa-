//
// Created by purple on 24-12-11.
//
#include <iostream>
#include <vector>
using namespace std;
int main() {
    int testcases=0;
    scanf("%d",&testcases);
    for(int i=0;i<testcases;i++) {
        int n=0;
        scanf("%d",&n);
        int m=0;
        scanf("%d",&m);
        vector<vector<int> > arr(n+1, vector<int>(n+1));
        for(int j=0;j<m;j++) {
            int x,y;
            scanf("%d %d",&x,&y);
            arr[x][y]=1;
        }
        for(int k=1;k<n+1;k++) {
            for(int j=1;j<n+1;j++) {
                printf("%d ",arr[k][j]);
            }
            printf("\n");
        }
    }

    return 0;
}