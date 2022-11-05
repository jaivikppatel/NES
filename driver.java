import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class driver {
    public static void main(String[] args) throws IOException {
        try (FileWriter fw = new FileWriter("test.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.print((char) (93 + 2));
            // more code
            out.print("more123 text");
            // more code
        } catch (IOException e) {
            // exception handling left as an exercise for the reader
        }
    }
}