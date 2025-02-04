import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] array=new int[n];
        for(int i = 0; i < n; i++) {
            array[i]=in.nextInt();
        }
        int check=in.nextInt();
        int length=array.length;
        for(int i =0;i<check;i++) {
            int left=0;
            int right=length-1;
            boolean flag=true;
            int find = in.nextInt();
            while (left < right) {
                int middle=left+(right-left)/2;
                    if (array[middle] == find) {
                        out.println("YES");
                        flag=false;
                        break;
                    } else if (array[middle] > find) {
                        right = middle;
                    } else {
                        left = middle+1;
                    }

            }
            if(flag) {
                out.println("NO");
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