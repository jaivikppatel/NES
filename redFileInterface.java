import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class redFileInterface {

    // declarations
    private final String filename;
    private File red_file;
    private Scanner scan;
    private Boolean get_flag, put_flag;
    private PrintWriter out;

    // initialize with red filename
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

    // gets next int with -32 -- used during encryption
    protected int getNextInt() throws FileNotFoundException {
        if (!this.get_flag) {
            this.get_flag = true;
            scan = new Scanner(this.red_file);
            scan.useDelimiter("");
        }
        return ((int) scan.next().charAt(0) - 32);
    }

    // put next int with +32 -- used during decryption
    protected void putNextChar(int x) throws IOException {
        if (!this.put_flag) {
            this.put_flag = true;
            out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));
        }
        out.println((char) (x + 32));
    }

    // checks for EOF
    protected boolean endOfFile(){
        return scan.hasNext();
    }

    // closes the instance
    protected boolean close(){
        try {
            if (this.get_flag) scan.close();
            if (this.put_flag) out.close();
            return true;
        } catch (Exception e) {
            System.out.println("Failed to close the Interface");
            return false;
        }
    }
}
