import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int  testcases_number = in.nextInt();
        for(int i=0;i<testcases_number;i++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int j=0;j<n;j++){
                arr[j] = in.nextInt();
            }for(int j=0;j<n-1;j++){
                for(int k=0;k<n-1;k++){
                    if(arr[k]>arr[k+1]){
                        int temp = arr[k];
                        arr[k] = arr[k+1];
                        arr[k+1] = temp;
                    }
                }
            }
//            for(int l:arr){
//                System.out.print(l+" ");
//            }

            if(n-4>0&&arr[n-4]==arr[n-3]){
                System.out.println("wa");
            } else if (arr[n-3]==arr[n-2]) {
                System.out.println("wa");
            }else{
                System.out.println(arr[n-3]);
            }
        }
//        out.println("Hello World");
        out.close();
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