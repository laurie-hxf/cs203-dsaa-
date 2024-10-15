import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int test_number = in.nextInt();
        for(int i=0; i<test_number; i++) {
            int  n=in.nextInt();
            long[][] arr=new long[n][3];
            for(int j=0; j<n; j++) {
                long B=in.nextInt();
                long R=in.nextInt();
                long value;
                if(B<=R){
                    value=-B;
                }
                else{
                    value=R-10000;
                }
                arr[j][0]=B;
                arr[j][1]=R;
                arr[j][2]=value;
            }
            mergeSort(arr,0,n-1);
//            for(int  k=0; k<n; k++) {
//                out.println(arr[k][0]+" "+arr[k][1]+" "+arr[k][2]);
//            }
            long answer=0;
            for(int k=0; k<n-1; k++) {
                if(arr[k][1]-arr[k+1][0]>=0){
                    arr[k+1][1]+=(arr[k][1]-arr[k+1][0]);
                    answer+=arr[k+1][0];
                }else{
                    answer+=arr[k][1];
                }
            }
            out.println(answer);
        }

        out.close();
    }
    public static void mergeSort(long[][] arr, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }


    public static void merge(long[][] arr, int left, int middle, int right) {

        int n1 = middle - left + 1;
        int n2 = right - middle;


        long[][] L = new long[n1][3];
        long[][] R = new long[n2][3];


        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[middle + 1 + j];
        }


        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (-L[i][2] <= -R[j][2]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}