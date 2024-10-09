import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int size = in.nextInt();
        int test=in.nextInt();
        int[] a=new int[size];
        int[] b=new int[size];
        int result=0;
        for(int j=0;j<size;j++){
            a[j]=in.nextInt();
        }
        for(int j=0;j<size;j++){
            b[j]=in.nextInt();
        }
        for(int i=0;i<test;i++){
            int a_left=in.nextInt()-1;
            int a_right=in.nextInt()-1;
            int b_left=a_left;
            int mid=(a_left+a_right)/2;
            int a_length=mid-a_left+1;
            int k=a_right-a_left+1;
            int b_location=b_left+k-a_length;
            if(a_left==a_right){
                //result=min(a[mid],b[b_location]);
                result=a[mid]<b[b_location]?a[mid]:b[b_location];
                System.out.println(result);
            }else {
                while (a_left < a_right) {
                    mid = (a_left + a_right) / 2;
                    a_length = mid - b_left + 1;
                    b_location = b_left + k - a_length-1;
                    if (a[mid] >= b[b_location]) {
                        a_right = mid;
                    } else {
                        a_left = mid + 1;
                    }

                }
                if(a[mid]>b[b_location]){
                    if(b[b_location+1]<a[mid]){
                        result=b[b_location+1];
                    }else{
                        result=a[mid];
                    }
                }else{
                    if(a[mid+1]<b[b_location]){
                        result=a[mid+1];
                    }else{
                        result=b[b_location];
                    }
                }
                System.out.println(result);
            }
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