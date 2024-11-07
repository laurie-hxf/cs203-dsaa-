import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k = in.nextInt();
        int n = in.nextInt();
        element[] queue = new element[n+1];
        //queue[0]=new element(2000000001,2000000001);
        for(int i = 0; i < n; i++) {
            queue[i] = new element(in.nextInt(),i);
        }
        int bottom=0;
        element min=queue[0];
        element x=new element(2000000001,2000000001);
        element min2=x;
        element min3=x;
        int length=1;
        for(int i = 0; i < n; i++) {
            int j=i;
            int temp_length=j-bottom;
            while(j<n&&(queue[j].value-min.value>0?queue[j].value-min.value:min.value-queue[j].value)<=k) {
                if(queue[j].value<min.value){
                    if(j!=0){
                        min3=min2;
                        min2=min;
                    }
                    min=queue[j];
                }else if(queue.length>=2&&queue[j].value<min2.value){
                    if(j!=0){
                        min3=min2;
                        min2=queue[j];
                    }
                }else if(queue.length>=3&&queue[j].value<min3.value){
                    min3=queue[j];
                }
                j++;
                temp_length++;
            }
            bottom=min.index+1;
            i=j-1;
            min=min2;
            min2=min3;
            min3=x;
            if(temp_length>length){
                length=temp_length;
            }
        }
        out.println(length);
        out.close();
    }
}

class element{
    int value;
    int index;
    public element(int value, int index) {
        this.value = value;
        this.index = index;
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