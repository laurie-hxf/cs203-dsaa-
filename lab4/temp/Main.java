import java.io.*;
import java.util.*;
public class Main {

    public static int longestSubsequenceWithinK(int[] arr, int k) {
        int n = arr.length;
        int left = 0;
        int maxLen = 0;

        int[] minDeque = new int[n];
        int[] maxDeque = new int[n];
        int minFront = 0, minRear = 0;  // minDeque 的头尾指针
        int maxFront = 0, maxRear = 0;  // maxDeque 的头尾指针

        for (int right = 0; right < n; right++) {
            // 更新最小值队列，保持递增顺序
            while (minRear > minFront && arr[minDeque[minRear - 1]] > arr[right]) {
                minRear--;
            }
            minDeque[minRear++] = right;
            // 更新最大值队列，保持递减顺序
            while (maxRear > maxFront && arr[maxDeque[maxRear - 1]] < arr[right]) {
                maxRear--;
            }
            maxDeque[maxRear++] = right;

            // 如果最大值和最小值的差超过 k，则收缩窗口
            while (arr[maxDeque[maxFront]] - arr[minDeque[minFront]] > k) {
                left++;

                // 如果 minDeque 队首的索引小于 left，移除它
                if (minDeque[minFront] < left) {
                    minFront++;
                }
                // 如果 maxDeque 队首的索引小于 left，移除它
                if (maxDeque[maxFront] < left) {
                    maxFront++;
                }
            }

            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k=in.nextInt();
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        out.println(longestSubsequenceWithinK(arr, k));
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