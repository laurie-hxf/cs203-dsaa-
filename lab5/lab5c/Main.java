import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        String str= in.next();
        int n=str.length();
        int[][] arr=new int[n][26];
        int[] next=new int[n];
        for(int j=0;j<26;j++){
            if(j+'a'==str.charAt(0)){
                arr[0][j]++;
            }
        }
//        for(int i=0;i<n;i++){
//            for(int j=0;j<26;j++){
//                out.print(arr[i][j]+" ");
//            }
//            out.println("");
//        }
//        out.println("");
//        out.println("");
        for(int i=1;i<n;i++){
            next[i]=arr[next[i-1]][str.charAt(i)-'a'];
            for(int j=0;j<26;j++){
                if(j+'a'==str.charAt(i)){
                    arr[i][j]=i+1;
                }else{
                    arr[i][j]=arr[next[i-1]][j];
                }
            }
        }


//        for(int i=0;i<n;i++){
//            out.print(next[i]+" ");
//        }
//        out.println("");
//        out.println("");
        for(int i=0;i<n;i++){
            for(int j=0;j<26;j++){
                out.print(arr[i][j]+" ");
            }
            out.println("");
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