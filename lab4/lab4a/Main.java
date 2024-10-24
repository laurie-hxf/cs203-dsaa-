import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for(int i=0; i<T; i++) {
            int n = in.nextInt();
            char[] stack = new char[n];
            String str=in.next();
            int top=0;
            boolean flag=true;
            for(int j=0; j<n; j++) {
                char ch=str.charAt(j);
                switch (ch){
                    case '(':
                    case '[':
                    case '{':
                        stack[top] = str.charAt(j);
                        top++;
                        break;
                    case ')':
                        if(top-1>=0&&stack[top-1]=='('){
                            top--;
                        }
                        else{
                            stack[top] = str.charAt(j);
                            top++;
                        }
                        break;
                    case ']':
                        if(top-1>=0&&stack[top-1]=='['){
                            top--;
                        }
                        else{
                            stack[top] = str.charAt(j);
                            top++;
                        }
                        break;
                    case '}':
                        if(top-1>=0&&stack[top-1]=='{'){
                            top--;
                        }
                        else{
                            stack[top] = str.charAt(j);
                            top++;
                        }
                        break;
                }
            }
            if(top==0){
                out.println("YES");
            }else{
                out.println("NO");
            }
        }
        //out.println("Hello World");
        out.close();
    }
}
//string str =in.next();
//str.charAt(i)

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