import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for(int t=0;t<T;t++){
            String str = in.next();
            int n = str.length();
            int[] next = new int[n];
            int i=1,j=0;
            while(i<n){
                if(str.charAt(i)==str.charAt(j)){
                    next[i]=j+1;
                    i++;j++;
                }else if(j!=0){
                    j=next[j-1];
                }else{
                    next[i]=0;
                    i++;
                }
            }//find next array
            int answer=0;
            if(n%(n-next[n-1])==0&&next[n-1]!=0){

            }else{
                int yu=n%(n-next[n-1]);
                if(yu!=0){
                    answer=(n-next[n-1])-yu;
                }else if(yu==0){
                    answer=n;
                }
            }
            out.println(answer);
        }

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