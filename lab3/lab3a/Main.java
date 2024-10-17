import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for(int i=0;i<T;i++){
            int n=in.nextInt();
            int k=in.nextInt();
            Node a=new Node(1);
            Node current=a;
            for(int j=1;j<n;j++){
                Node temp=new Node(j+1);
                current.next=temp;
                current=current.next;
            }
            current.next=a;
            //Node current=current;
            while(true){
                for(int j=0;j<k-1;j++){
                    current=current.next;
                }
                out.print(current.next.value+" ");
                //System.out.print(current.next.value+" ");;
                Node temp=current.next.next;
                if(temp==current){
                    break;
                }
                current.next=temp;
            }
            if(current.next!=current){
                out.print(current.value);
            }
            out.println("");
//            for(int j=0;j<k;j++){
//                out.print(current.value+" ");
//                current=current.next;
//            }

        }
        //out.println("Hello World");
        out.close();
    }
}
class Node{
    int value;
    Node next;
    public Node(int value) {
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