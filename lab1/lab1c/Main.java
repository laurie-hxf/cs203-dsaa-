import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int array_length = in.nextInt();
        int query_length = in.nextInt();
        int[] array=new int[array_length];
        for(int i=0;i<array_length;i++){
            array[i]=in.nextInt();
        }
        for(int i=0;i<query_length;i++) {
            int min = in.nextInt();
            int max = in.nextInt();
            int left = 0;
            int right = array.length - 1;
            int index_left = 0;
            int index_right = 0;
            //boolean flag=true;
            while (left < right) {
                int mid = (left + right) / 2 + 1;
                int mid_value = array[mid];
                if (mid_value > min) {
                    right = mid - 1;
                } else if (mid_value == min) {
                    left = mid;
                    //flag=false;
                    //break;
                } else {
                    left = mid + 1;
                }
            }
//            if(flag){
//                index_left=left+1;
//            }else{
//                index_left=left;
//            }
            index_left = left;
            out.println(index_left);
            left = 0;
            right = array.length - 1;
            boolean flag = true;
            while (left < right) {
                int mid = (left + right) / 2;
                int mid_value = array[mid];
                if (mid_value > max) {
                    right = mid - 1;
                } else if (mid_value == max) {
                    right = mid;
                    flag = false;
                } else {
                    left = mid + 1;
                }
            }
            index_right = left;
//            if (flag) {
//                index_right = right + 1;
//            } else {
//                index_right = right;
//            }

            out.println(index_right);
            int answer = index_right - index_left - 1;
            //out.println(answer);
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