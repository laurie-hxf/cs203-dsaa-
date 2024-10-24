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
            Node head = new Node(0);
            Node current=head;
            for(int j=0;j<n;j++){
                Node temp = new Node(in.nextInt());
                current.next = temp;
                temp.previse=current;
                current=temp;
            }
            current=head.next;
            Node[] arr=new Node[n];
            //Node temp=current.previse;
            int record=0;
            int number=0;
            while(current!=null){
                record=0;
                Node temp=current.previse;
                while(current.next != null && current.value > current.next.value){
                    current = current.next;
                    record++;
                }
                if(record!=0){
                    temp.next=current.next;
                    if(current.next!=null){
                        current.next.previse=temp;
                    }
                    arr[number++]=temp;
                }else{
                    current=current.next;
                }
            }

//            for(int k=0;k<arr.length;k++){
//                if(arr[k]==null){
//                    break;
//                }
//                out.println(arr[k].value+" ");
//            }
//            current=head.next;
//            while(current!=null){
//                out.print(current.value+" ");
//                current=current.next;
//            }
//            out.println("");
            for(int t=0;t<arr.length;t++){

                if(arr[t]==null){
                    break;
                }
                else if(arr[t].previse==null){
                    continue;
                }
                else{
                    Node check=arr[t];
                    if(check.next != null&&check.value>check.next.value){
                        Node temp=check.previse;
                        while(check.next != null&&check.value > check.next.value){
                            check.previse=null;
                            check = check.next;
                        }
                        //temp.next.previse=null;
                        temp.next=check.next;
                        if(check.next!=null){
                            check.next.previse=temp;
                        }
                        check.next=null;
                        arr[number++]=temp;
                    }
                }
            }
            current=head.next;
            while(current!=null){
                out.print(current.value+" ");
                current=current.next;
            }
            out.println("");
//            for(int k=0;k<arr.length;k++){
//                if(arr[k]==null){
//                    break;
//                }
//                out.println(arr[k].value+" ");
//            }
        }
        //out.println("Hello World");
        out.close();
    }
    //public
}
class Node{
    int value;
    Node next;
    Node previse;
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