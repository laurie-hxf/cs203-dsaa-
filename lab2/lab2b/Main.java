import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int testcase_number = in.nextInt();
        for(int i=0;i<testcase_number;i++) {
            int a_length = in.nextInt();
            int b_length = in.nextInt();
            int[] a = new int[a_length];
            int[] b = new int[b_length];
            int[] merged = new int[a_length+b_length];
            for(int j=0;j<a_length;j++) {
                a[j] = in.nextInt();
            }
            for(int j=0;j<b_length;j++) {
                b[j] = in.nextInt();
            }
            int record=0;
//            if(a_length<b_length) {
//
//            }
            int k=0;
            //boolean judge=true;
            for(int j=0;j<a_length;j++) {
                for(;k<b_length;k++) {
                    if(a[j]>=b[k]) {
                        merged[record]=b[k];
                        record++;
                    }else{
                        merged[record]=a[j];
                        record++;
                        break;
                    }
                    //record++;
                }
                if(k==b_length){
                    merged[b_length+j]=a[j];
                }
            }
            if(k<b_length) {
                for(int j=k;j<b_length;j++) {
                    merged[a_length+j]=b[j];
                }
            }
            for(int j:merged) {
                out.print(j+" ");

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