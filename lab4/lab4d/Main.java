import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for(int i=0;i<T;i++){
            int n = in.nextInt();
            boolean[] arr = new boolean[n+1];
            int[] stack = new int[n];
            int top=0;
            for(int j=0;j<n+1;j++){
                arr[j]=true;
            }
            int min=1;
            //int number=0;
            for(int j=0;j<n;j++){
                boolean judge=true;
                int stack_in=in.nextInt();
                if(stack_in==min&&top-1>=0&&stack_in<stack[top-1]){
                    out.print(stack_in+" ");
                    arr[stack_in]=false;
                } else if (stack_in == min && top == 0) {
                    out.print(stack_in+" ");
                    arr[stack_in]=false;
                } else if (top - 1 >= 0 && stack_in > stack[top - 1] && stack[top - 1] < min) {
                    while (top - 1 >= 0 && stack_in > stack[top - 1] && stack[top - 1] < min) {
                        top--;
                        out.print(stack[top] + " ");
                    }
                    stack[top++] = stack_in;
                    arr[stack_in] = false;
                } else if (top - 1 >= 0 && stack[top - 1] == min) {
                    top--;
                    out.print(stack[top] + " ");
                    min++;
                    judge = false;
                } else {
                    stack[top++] = stack_in;
                    arr[stack_in] = false;
                }
                if(judge){
                    for(int k=min;k<arr.length;k++) {
                        if (arr[k]) {
                            min = k;
                            break;
                        }
                    }
                }


//                if(stack_in!=min){
//                    stack[top++]=stack_in;
//                    arr[stack_in]=false;
//                }
//                else {
//                    out.print(stack_in+" ");
//                    arr[stack_in]=false;
//                    min++;
//                    while(top-1>=0&&stack[top-1]==min){
//                        top--;
//                        out.print(stack[top]+" ");
//                        min++;
//                        judge=false;
//                    }if(judge){
//                        for(int k=min;k<arr.length;k++){
//                            if(arr[k]){
//                                min=k;
//                                break;
//                            }
//                        }
//                    }
//                }
            }
            for(int j=top-1;j>=0;j--){
                out.print(stack[j]+" ");
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