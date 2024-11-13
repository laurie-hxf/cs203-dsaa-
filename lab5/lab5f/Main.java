import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        char[] table =new char[26];
        for(int i=0;i<26;i++){
            table[i]=in.next().charAt(0);
        }
        String given=in.next();
        char[] given_plus=new char[given.length()*2];
        for(int i=0;i<given.length();i++){
            given_plus[i]=given.charAt(i);
            given_plus[i+given.length()]=table[given.charAt(i)-'a'];
        }
//        for(int i=0;i<given_plus.length;i++){
//            out.print(given_plus[i]+" ");
//        }
        String double_string=new String(given_plus);
        int n = double_string.length();
        int[] next = new int[n];
        int i=1,j=0;
        while(i<n){
            if(double_string.charAt(i)==double_string.charAt(j)){
                next[i]=j+1;
                i++;j++;
            }else if(j!=0){
                j=next[j-1];
            }else{
                next[i]=0;
                i++;
            }
        }

        int k=next[n-1];
        while(k>n/4){
            k=next[k-1];
        }
        out.print(n/2-k);
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