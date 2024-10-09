import java.io.*;
import java.util.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //sample
        Scanner scanner =new Scanner(System.in);
         while(scanner.hasNext()) {
            int L = scanner.nextInt();//total length
            int n = scanner.nextInt();//total possible places
            int m = scanner.nextInt();//racers
            int[] arr = new int[n+1];
            for(int i=0;i<n;i++){
                arr[i] = scanner.nextInt();
            }
            arr[n]=L;
            Arrays.sort(arr);
            int left=0;
            int right=L;
            int mid =left+(right-left)/2;
            //System.out.println(judge(10,arr,L,m));
            while(left<right){
                mid=left+(right-left)/2;
                if(judge(mid,arr,L,m)){
                    right=mid;
                }
                else{
                    left=mid+1;
                }
            }
            System.out.println(right);
        }
    }
    public static boolean judge(int mid,int[] arr,int L,int racers){
        int length=arr.length;
        int count=0;
        int base=0;
        if(mid<arr[0]){
            return false;
        }else if(mid>=L){
            return true;
        }else {
            for (int i = 0; i < length - 1; i++) {
                if (base+mid >= arr[i]&&base+mid < arr[i+1]) {
                    base=arr[i];
                    count++;
                }
            }
            return (count <= racers-1)&&(base+mid >= arr[length-1]);
        }
    }
}

