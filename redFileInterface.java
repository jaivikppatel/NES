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
        if (scan.hasNext())
            return ((int) scan.next().charAt(0) - 32);
        else
            this.get_flag = false;
        return -1;
    }

    // put next int with +32 -- used during decryption
    protected void putNextChar(int x) throws IOException {
        if (!this.put_flag) {
            this.put_flag = true;
            out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));
        }
        out.print((char) (x + 32));
    }

    protected void endOfFile(){
        System.out.println("RedFile End Reached");
        //send audit message here
        System.out.println("The Audit message has been sent");
    }

    protected boolean close(){
        try {
            this.filename = null;
            this.red_file = null;
            this.get_flag = false;
            this.put_flag = false;
            return false;
        } catch (Exception e) {
            System.out.println("Failed to close the Interface");
            throw new RuntimeException(e);
        }
    }
}
