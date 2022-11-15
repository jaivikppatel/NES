import java.io.*;
import java.util.Scanner;

public class blackFileInterface {
    private final String filename;
    private BufferedReader reader;
    private PrintWriter printer;
    private boolean EOF_flag;

    blackFileInterface(String filename){
        this.filename = filename;
    }

    public static blackFileInterface Instance(String filename) { return new blackFileInterface(filename); }

    protected void openFile() throws IOException {
        this.reader = new BufferedReader(new FileReader(this.filename));
        this.printer = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
        this.EOF_flag = false;
    }

    protected int getNextInt() throws IOException {
        int ret = Integer.parseInt(reader.readLine());
        if (ret < 0){
            this.EOF_flag = true;
        }
        return ret;
    }

    protected void putNextInt(int x) throws IOException {
        printer.println(x);
    }

    // check if EOF
    protected boolean endOfFile(){
        return this.EOF_flag;
    }

    // closes the instance
    protected void close() throws IOException {
        this.reader.close();
        this.printer.close();
    }
}
