import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();

        for(int i = 0; i < n; i++) {
            int length = in.nextInt();
            int width = in.nextInt();
            int height = in.nextInt();
            int draw_width = 2*width;
            int draw_height = 2*(height-width)+1;
            //1  2  3  4  5  6
            //+- /. |. /| .+ |/
            if(height>=width) {
                for (int j = 0; j < draw_width; j++) {
                    String line = "";
                    if (j % 2 != 0) {
                        line += draw_dot(draw_width - j) + draw_line2(length) + draw_line4((j + 1) / 2);
                    } else {
                        line += draw_dot(draw_width - j) + draw_line1(length) + draw_line5(j / 2);
                    }
                    out.println(line);
                }
                for (int j = 0; j < draw_height; j++) {
                    String line = "";
                    if (j % 2 == 0) {
                        line += draw_line1(length) + draw_line5(width);
                    } else {
                        line += draw_line3(length) + "|" + draw_line4(width);
                    }
                    out.println(line);
                }
                for (int j = 0; j < draw_width; j++) {
                    String line = "";
                    if (j % 2 == 0) {
                        line += draw_line3(length) + draw_line6((draw_width - j) / 2) + draw_dot(j + 1);
                    } else {
                        line += draw_line1(length) + draw_line5((draw_width - j - 1) / 2) + draw_dot(j + 1);
                    }
                    out.println(line);
                }
            }else{
                draw_height=2*height;

                for (int j = 0; j < draw_height; j++) {
                    String line = "";
                    if (j % 2 != 0) {
                        line += draw_dot(draw_width - j) + draw_line2(length) + draw_line4((j + 1) / 2);
                    } else {
                        line += draw_dot(draw_width - j) + draw_line1(length) + draw_line5(j / 2);
                    }
                    out.println(line);
                }
                int index=2*(width-height)+1;
                for(int j=0;j<index;j++){
                    String line = "";
                    if (j % 2 == 0) {
                        line+=draw_dot(draw_width - draw_height-j)+draw_line1(length)+draw_line5(height)+draw_dot(j);
                    }else{
                        line+=draw_dot(draw_width - draw_height-j)+draw_line2(length)+"/"+draw_line6(height)+draw_dot(j);
                    }
                    out.println(line);
                }
                 draw_width = 2*width;
                for (int j = 0; j < draw_height; j++) {
                    String line = "";
                    if (j % 2 == 0) {
                        line += draw_line3(length) + draw_line6((draw_height - j) / 2) + draw_dot(draw_width-(draw_height-j)+1);
                    } else {
                        line += draw_line1(length) + draw_line5((draw_height - j - 1) / 2) + draw_dot(draw_width-(draw_height-j)+1);
                    }
                    out.println(line);
                }
            }
        }
        out.close();
    }
    public static String draw_line1(int length){
        String line="";
        for(int i = 0; i < length; i++){
            line+="+-";
        }
        line+="+";
        return line;
    }
    public static String draw_line2(int length){
        String line="";
        for(int i = 0; i < length; i++){
            line+="/.";
        }
        //line+="/";
        return line;
    }
    public static String draw_line3(int length){
        String line="";
        for(int i = 0; i < length; i++){
            line+="|.";
        }
        //line+="|";
        return line;
    }
    public static String draw_line4(int length){
        String line="";
        for(int i = 0; i < length; i++){
            line+="/|";
        }
        //line+="|";
        return line;
    }
    public static String draw_line5(int length){
        String line="";
        for(int i = 0; i < length; i++){
            line+=".+";
        }
        //line+="|";
        return line;
    }
    public static String draw_line6(int length){
        String line="";
        for(int i = 0; i < length; i++){
            line+="|/";
        }
        //line+="|";
        return line;
    }
    public static String draw_dot(int line){
        String dot="";
        for(int i = 0; i < line; i++){
            dot+=".";
        }
        return dot;
    }
}
/*
        ....+-+-+-+-+-+-+
        .../././././././|
        ..+-+-+-+-+-+-+.+
        ./././././././|/|
        +-+-+-+-+-+-+.+.+
        |.|.|.|.|.|.|/|/|
        +-+-+-+-+-+-+.+.+
        |.|.|.|.|.|.|/|/|
        +-+-+-+-+-+-+.+.+
        |.|.|.|.|.|.|/|/.
        +-+-+-+-+-+-+.+..
        |.|.|.|.|.|.|/...
        +-+-+-+-+-+-+....
        */
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