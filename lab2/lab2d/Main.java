import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        //注意点考虑k_location之前有和他相同的元素
        //样例
        //3
        //1 1 2
        //结果应该1 1 2
        //6
        //1 2 2 3 4 5
        //结果应该1 2 2 3 4 5
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int j = 0; j < n; j++) {
            arr[j] = in.nextInt();
        }
        mergeSort(arr, 0, arr.length - 1);

        int k_location=n/3;
        long k=arr[k_location];
        out.println(k);
        long[] answer=new long[n];
        int record=0;
        while((k_location-1>=0)&&arr[k_location]==arr[k_location-1]){
            k_location--;
        }

        for(int i=0;i<k_location;i++){
            if(record<arr.length){
                answer[record]=arr[i];
            }
            record+=3;
        }
        record=0;
        for(int i=k_location;i<n;i++){
           if(record<arr.length&&answer[record]==0){
               answer[record]=arr[i];
           }else{
               record++;
               if(record<arr.length){
                   answer[record]=arr[i];
               }
           }
           record++;
        }
        for(long i:answer){
            out.print(i+" ");
        }
        out.close();
    }
    public static void mergeSort(long[] arr, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }


    public static void merge(long[] arr, int left, int middle, int right) {

        int n1 = middle - left + 1;
        int n2 = right - middle;


        long[] L = new long[n1];
        long[] R = new long[n2];


        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[middle + 1 + j];
        }


        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
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