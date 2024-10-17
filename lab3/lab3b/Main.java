import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int test_number = in.nextInt();
        for(int i=0;i<test_number;i++){
            int n = in.nextInt();
            Node head = new Node(-1,-1);
            Node current =head;
            for(int j=0;j<n;j++){
                Node temp = new Node(in.nextInt(),in.nextInt());
                current.next = temp;
                current = current.next;
            }
            Node tail=new Node(0,1000000001);
            current.next = tail;
            int m=in.nextInt();
            current=head;
            for(int j=0;j<m;j++){
                int coe=in.nextInt();
                int exp=in.nextInt();
                //Node b=new Node(in.nextInt(),in.nextInt());
                while(current.next.exp<=exp){
                    current=current.next;
                }
                if(current.exp==exp){
                    current.coe+=coe;
                }else{
                    Node temp=new Node(coe,exp);
                    temp.next=current.next;
                    current.next=temp;
                }
            }
            int q=in.nextInt();
            current=head.next;
            for(int j=0;j<q;j++){
                int check=in.nextInt();
                while(current.next.exp<=check){
                    current=current.next;
                }
                if(current.exp==check){
                    out.print(current.coe+" ");
                }else{
                    out.print("0 ");
                }
            }
        }
        //out.println("Hello World");
        out.close();
    }
}
 class Node{
     int coe;
     int exp;
    public Node next;
    public Node(int coe, int exp) {
        this.coe = coe;
        this.exp = exp;
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