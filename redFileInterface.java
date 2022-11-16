
import java.io.*;
import java.util.Scanner;

public class redFileInterface {

    // declarations
    private final String filename;
    private final Opt opt;
    private BufferedReader reader;
    private PrintWriter printer;
    private boolean EOF_flag;

    // initialize with red filename
    redFileInterface(String filename, Opt opt) {
        this.filename = filename;
        this.opt = opt;
    }

    public static redFileInterface Instance(String filename, Opt opt) {
        return new redFileInterface(filename, opt);
    }

    protected void openFile() throws IOException {
        if (this.opt == Opt.ENCRYPT)
            this.reader = new BufferedReader(new FileReader(this.filename));
        if (this.opt == Opt.DECRYPT)
            this.printer = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
        this.EOF_flag = false;
    }

    // gets next int with -32 -- used during encryption
    protected int getNextInt() throws IOException {
        int ret = reader.read() - 32;
        if (ret < 0){
            this.EOF_flag = true;
        }
        return ret;
    }

    // put next int with +32 -- used during decryption
    protected void putNextChar(int x) throws IOException {
        printer.print((char) (x + 32));
    }

    // checks for EOF
    protected boolean endOfFile(){
        return this.EOF_flag;
    }

    // closes the instance
    protected void close() throws IOException {
        if (this.opt == Opt.ENCRYPT)
            this.reader.close();
        if (this.opt == Opt.DECRYPT)
            this.printer.close();
    }
}
