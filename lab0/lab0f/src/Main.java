import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            int[][] barrel = new int[4][10];
            String line = in.next();
            int total = 0;
            for (int j = 0; j < 28; j += 2) {
                int number = line.charAt(j) - '0';
                char kind = line.charAt(j + 1);
                switch (kind) {
                    case 'b':
                        barrel[0][number]++;
                        total++;
                        break;
                    case 'w':
                        barrel[1][number]++;
                        total++;
                        break;
                    case 's':
                        barrel[2][number]++;
                        total++;
                        break;
                    case 'z':
                        barrel[3][number]++;
                        total++;
                        break;
                }
            }
            if(helper(barrel,total,1)){
                out.println("Blessing of Heaven");
            }else{
                out.println("Bad luck");
            }
        }
        //out.println("Hello World");
        out.close();
    }

    public static boolean helper(int[][] a, int total, int type) {
        if (total == 0) return true;
        if (type == 1) {
            for (int m = 0; m < 4; m++) {
                for (int k = 0; k < 10; k++) {
                    if (a[m][k] >= 2) {
                        a[m][k] -= 2;
                        total -= 2;
                        if(helper(a, total, 2)){
                            return true;
                        };
                        a[m][k] += 2;
                        total += 2;
                    }
                }
            }
        }else if(type == 2){
            for (int m = 0; m < 4; m++) {
                for (int k = 0; k < 10; k++) {
                    if (a[m][k] >= 3) {
                        a[m][k] -= 3;
                        total -= 3;
                        if(helper(a, total, 2)){
                            return true;
                        };
                        a[m][k] += 3;
                        total += 3;
                    }
                }
            }
            if(helper(a, total, 3)) {
                return true;
            }
        }else if(type == 3){
            for (int m = 0; m < 3; m++) {
                for (int k = 0; k < 8; k++) {
                    if(a[m][k]!=0&a[m][k+1]!=0&a[m][k+2]!=0){
                        a[m][k] --;
                        a[m][k+1] --;
                        a[m][k+2] --;
                        total -= 3;
                        if(helper(a, total, 3)){
                            return true;
                        };
                        a[m][k] ++;
                        a[m][k+1] ++;
                        a[m][k+2] ++;
                        total += 3;
                    }
                }
            }
        }
        return false;
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