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
            int m = in.nextInt();
            Node root = new Node(-1);
            Node current = root;
            for(int j=0; j<n; j++) {
                Node temp = new Node(in.nextInt());
                current.next = temp;
                //temp.before = current;
                current = current.next;
            }

            for(int j=0; j<m; j++) {
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                //out.print(current);
                Node x1_before =null;
                Node x1_node=null;
                Node y1_node=null;
                Node y1_after=null;
                Node x2_before=null;
                Node x2_node=null;
                Node y2_node=null;
                Node y2_after=null;
                current=root;
                for(int k=0; k<=n; k++) {
                    if(current.next!=null&&current.next.value==x1){
                        x1_before = current;
                        x1_node = current.next;
                    }if(current.next!=null&&current.next.value==x2){
                        x2_before = current;
                        x2_node = current.next;
                    }if(current.value==y1){
                        y1_node = current;
                        y1_after = current.next;
                    }if(current.value==y2){
                        y2_node = current;
                        y2_after = current.next;
                    }
                    current=current.next;
                }
                boolean judge=false;
                if(y1_node==x2_before){
                    judge=true;
                }
                y1_node.next=null;
                y2_node.next=null;
                x1_before.next=x2_node;
                x2_before.next=x1_node;
                y1_node.next=y2_after;
                y2_node.next=y1_after;
                if(judge){
                    y2_node.next=x1_node;
                }
            }
            Node temp=root.next;
            while(temp!=null){
                out.print(temp.value+" ");
                temp=temp.next;
            }
        }
        //out.println("Hello World");
        out.close();
    }
}
class Node{
    int value;
    Node next;
    //Node before;
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