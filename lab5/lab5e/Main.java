import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        String str1 = in.next();
        String str2 = in.next();
        int min=str1.length()<str2.length()?str1.length():str2.length();
        int left = 0;
        int right = min;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(mid==0){
                result = mid;
                break;
            }
            if (check(mid, str1, str2)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        out.println(result);
        out.close();
    }
    public static boolean check(int answer,String str1,String str2) {
        long[]str1_hash=new long[str1.length()-answer+1];
        long[]str2_hash=new long[str2.length()-answer+1];
        long max=1;
        for(int j=0;j<answer-1;j++) {
            max*=139;
        }
        for(int i=0;i<answer;i++) {
            str1_hash[0]=str1_hash[0]*139+str1.charAt(i);
        }
        for(int i=1;i<str1.length()-answer+1;i++) {
            long a=(str1_hash[i-1]-str1.charAt(i-1)*max);
            str1_hash[i]=(str1_hash[i-1]-str1.charAt(i-1)*max)*139+str1.charAt(i+answer-1);
        }
        for(int i=0;i<answer;i++) {
            str2_hash[0]=str2_hash[0]*139+str2.charAt(i);
        }
        for(int i=1;i<str2.length()-answer+1;i++) {
            str2_hash[i]=(str2_hash[i-1]-str2.charAt(i-1)*max)*139+str2.charAt(i+answer-1);
        }
        mergeSort(str1_hash,0,str1_hash.length-1);
        for(int i=0;i<str2_hash.length;i++) {
            if(binarySearch(str1_hash,str2_hash[i])){
                return true;
            }
        }
        return false;
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

    public static boolean binarySearch(long[] array, long target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return true;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
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