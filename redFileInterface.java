import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class redFileInterface {

    // declarations
    private String filename;
    private File red_file;
    private Scanner scan;
    private Boolean get_flag, put_flag;
    private PrintWriter out;

    redFileInterface(String filename) {
        this.filename = filename;
        this.get_flag = false;
        this.put_flag = false;
    }

    public static redFileInterface Instance(String filename) {
        return new redFileInterface(filename);
    }

    protected void openFile() {
        this.red_file = new File(this.filename);
    }

    protected int getNextInt() throws FileNotFoundException {
        if (!this.get_flag) {
            this.get_flag = true;
            scan = new Scanner(this.red_file);
            scan.useDelimiter("");
        }
        if (scan.hasNext())
            return (int) scan.next().charAt(0);
        else
            this.get_flag = false;
        return -1;
    }

    protected void putNextChar(int x) throws IOException {
        if (!this.put_flag) {
            this.put_flag = true;
            out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));
        }
        out.print((char) (x + 32));
    }
}
