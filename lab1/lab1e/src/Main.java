import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        Scanner scanner =new Scanner(System.in);
        while(scanner.hasNext()) {
            int L=in.nextInt();
            int n=in.nextInt();
            int m=in.nextInt();
            int[] distance=new int[n];
            for(int i=0;i<n;i++){
                distance[i]=in.nextInt();
            }
            Arrays.sort(distance);
            int average=L/m;
            int[][] record=new int[m-1][3];
            for(int i=0;i<m-1;i++){
                int average_point=average*(i+1);
                int left=0;
                int right=distance.length-1;
                record[i][0]=distance[left];
                record[i][1]=distance[right];
                if(average_point<distance[left]){
                    record[i][0]=0;
                    record[i][1]=distance[left];
                }else if(average_point>distance[right]){
                    record[i][0]=distance[right];
                    record[i][1]=L;
                }else {
                    while (left < right) {
                        int middle = left + (right - left) / 2;
                        if (distance[middle] < average_point) {
                            left = middle;
                            record[i][0] = distance[left];
                            left++;

                        } else if (distance[middle] > average_point) {
                            right = middle;
                            record[i][1] = distance[right];
                        } else {
                            if(middle-1>=0){
                                record[i][0] = distance[middle - 1];
                            }
                            record[i][2] = distance[middle];
                            if(middle+1<distance.length){
                                record[i][1] = distance[middle+1];
                            }
                            break;
                        }
                    }
                }
            }
            for(int i=0;i<m-1;i++){
                for(int j=0;j<3;j++){
                    System.out.print(record[i][j]+" ");
                }
            }
            int max=0;
            int differ=0;
//            for(int i=0;i<Math.pow(3,m-1);i++){
//                for(int j=1;j<m-1;j++){
//                    int temp2=i%3;
//                    int temp1=i/(int)(Math.pow(3,j));
//                    int temp3=i/(int)(Math.pow(3,j+1));
//                    int a=record[temp1][temp2];
//                    int b=record[temp3][temp2];
//                    differ=b-a;
//                    if(differ>=max){
//                        max=differ;
//                    }
//                }
//            }
        }

    }

    public  int help(int[][] a,int x,int y){
        int max=0;
        if(x==a.length-1){
            y++;
        }
        for(int i=0;i<3;i++){

        }
        return max;
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