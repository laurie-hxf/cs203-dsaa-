import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        //out.println("Hello World");
        for(int i=0;i<T;i++){
            int n = in.nextInt();
            int m = in.nextInt();
            Node[] arr = new Node[n+1];
            arr[n]=new Node(-1);
            int record=n;
            for(int j=0;j<n;j++){
                int x = in.nextInt();
                arr[x] = new Node(x);
                arr[record].next = arr[x];
                arr[x].previous=arr[record];
                record=x;
            }
            for(int j=0;j<m;j++){
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                Node x1_prev = arr[x1].previous;
                Node x2_prev = arr[x2].previous;
                arr[x1].previous.next=arr[x2];
                arr[x1].previous=x2_prev;
                arr[x2].previous.next=arr[x1];
                arr[x2].previous=x1_prev;

                Node y1_next = arr[y1].next;
                Node y2_next = arr[y2].next;
                arr[y1].next=y2_next;
                if(y2_next!=null){
                    y2_next.previous=arr[y1];
                }
                arr[y2].next=y1_next;
                if(y1_next!=null){
                    y1_next.previous=arr[y2];
                }
            }
            Node temp=arr[n].next;
            while(temp!=null){
                out.print(temp.value+" ");
                temp=temp.next;
            }
            out.println("");
        }

        out.close();
    }
}
class Node{
    int value;
    Node next=null;
    Node previous=null;
    public Node(int value){
        this.value = value;
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