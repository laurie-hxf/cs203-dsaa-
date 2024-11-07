import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for(int i=0;i<T;i++){
            out.println("Case "+(i+1)+": ");
            int n = in.nextInt();
            int[] arr = new int[n+1];
            for(int j=1;j<n+1;j++){
                arr[j] = in.nextInt();
            }
            for(int j=1;j<n+1;j++){
                int left=0,right=0;
                int k=j-1;
                while(k>=0&&arr[k]<arr[j]){
                    if(arr[k]>arr[left]){
                        left=k;
                    }
                    k--;
                }
                k=j+1;
                while(k<n+1&&arr[k]<arr[j]){
                    if(arr[k]>arr[right]){
                        right=k;
                    }
                    k++;
                }
                out.println(left+" "+right);
            }

        }
        //out.println("Hello World");
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