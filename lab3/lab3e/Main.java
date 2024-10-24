import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] arr=new int[n];
        for(int i = 0; i < n; i++) {
            arr[i]=in.nextInt();
        }
        AVLTree tree = new AVLTree();
        tree.insert(arr[n-1]);
        int[] record=new int[n];
        for(int i = n-2; i >=0; i--) {
            int item=arr[i];
            int closest=tree.findClosest(item);
            int value=(item-closest)>0?(item-closest):(closest-item);
            //out.print(value+" ");
            record[i]=value;
            tree.insert(item);
        }
        for(int i=0;i<n-1;i++) {
            out.print(record[i]+" ");
        }
        //out.println("Hello World");
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
// AVL树中的节点类
class AVLNode {
    int key, height;
    AVLNode left, right;

    AVLNode(int d) {
        key = d;
        height = 1;
    }
}

// AVL树类
class AVLTree {
    AVLNode root;

    // 获取节点高度
    int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // 获取节点的平衡因子
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // 右旋操作
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 执行旋转
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // 返回新的根
        return x;
    }

    // 左旋操作
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 执行旋转
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // 返回新的根
        return y;
    }

    // 插入节点并保持平衡
    AVLNode insert(AVLNode node, int key) {
        // 1. 正常的二叉查找树插入
        if (node == null)
            return (new AVLNode(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // 不允许插入重复的键
            return node;

        // 2. 更新当前节点的高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. 计算当前节点的平衡因子，并进行平衡操作
        int balance = getBalance(node);

        // 4. 平衡因子大于1，进行旋转

        // 左左情况
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // 右右情况
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // 左右情况
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左情况
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // 返回节点指针
        return node;
    }

    // 中序遍历
    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // 调用插入方法
    void insert(int key) {
        root = insert(root, key);
    }

    // 查找与目标值最接近的节点
    int findClosest(int target) {
        return findClosestRec(root, target, root.key);
    }

    // 递归查找与给定数差值最小的节点
    int findClosestRec(AVLNode node, int target, int closest) {
        if (node == null) {
            return closest;
        }

        if (Math.abs(target - node.key) < Math.abs(target - closest)) {
            closest = node.key;
        }

        if (target < node.key) {
            return findClosestRec(node.left, target, closest);
        } else if (target > node.key) {
            return findClosestRec(node.right, target, closest);
        } else {
            return node.key;
        }
    }
}

