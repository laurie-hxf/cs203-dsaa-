import java.io.*;
import java.util.*;

// 定义图的类
class Node {
    int val;
    int in;
    int out;
    Node father;
    LinkedList<Node> children;

    Node() {
        this.val = 0;
        this.in = 0;
        this.out = 0;
        this.father = null;
        this.children = new LinkedList<>();
    }

    Node(int val) {
        this.val = val;
        this.in = 0;
        this.out = 0;
        this.father = null;
        this.children = new LinkedList<>();
    }
}

class Graph {
    private int V; // 顶点数
    private Node[] adjList; // 邻接表
    private int adder = 0;

    // 构造函数
    Graph(int vertices) {
        V = vertices; // 顶点
        adjList = new Node[V + 1];
        for (int i = 0; i <= V; i++) {
            adjList[i] = new Node(i);
        }
    }

    // 添加边（无向图）
    void addEdge(int u, int v) {
        adjList[v].children.add(adjList[u]); // u -> v
        adjList[u].father = adjList[v];      // v 的父节点是 u
    }

    Node findRoot() {
        for (int i = 1; i <= V; i++) {
            if (adjList[i].father == null) {
                return adjList[i];
            }
        }
        return null;
    }

    void DFS(Node current) {
        current.in = adder++;
        for (Node child : current.children) {
            DFS(child);
        }
        current.out = adder++;
    }

    boolean judge(int x, int y) {
        Node nx = adjList[x];
        Node ny = adjList[y];
        if (ny.in <= nx.in && ny.out >= nx.out) {
            return true;
        }
        else{
            return false;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            Graph g = new Graph(n);

            for (int j = 0; j < n - 1; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                g.addEdge(x, y);
            }
            Node root = g.findRoot();
            g.DFS(root);
            for (int j = 0; j < m; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                String answer= g.judge(x, y)?"Yes":"No";
                out.print(answer);
                out.print("\n");
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