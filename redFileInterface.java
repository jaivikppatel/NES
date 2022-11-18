import java.io.*;
import java.util.ArrayList;

public class redFileInterface {

    // declarations
    private final String filename;
    private final Opt opt;
    private BufferedReader reader;
    private PrintWriter printer;
    private ArrayList<Integer> int_list = new ArrayList<>();
    private int itr;

    // initialize with red filename
    redFileInterface(String filename, Opt opt) {
        this.filename = filename;
        this.opt = opt;
        this.itr = 0;
    }

    public static redFileInterface Instance(String filename, Opt opt) {
        return new redFileInterface(filename, opt);
    }

    protected void openFile() throws IOException {
        if (this.opt == Opt.ENCRYPT) {
            int temp;
            this.reader = new BufferedReader(new FileReader(this.filename));
            while ((temp = reader.read()) != -1){
                int_list.add(temp);
            }
        }
        if (this.opt == Opt.DECRYPT)
            this.printer = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
    }

    // gets next int with -32 -- used during encryption
    protected int getNextInt() throws IOException {
        int ret = int_list.get(itr);
        itr++;
        return ret;
    }

    // put next int with +32 -- used during decryption
    protected void putNextChar(int x) throws IOException {
        printer.print((char) (x + 32));
    }

    // checks for EOF
    protected boolean endOfFile(){
        return (itr >= int_list.size());
    }

    // closes the instance
    protected void close() throws IOException {
        if (this.opt == Opt.ENCRYPT)
            this.reader.close();
        if (this.opt == Opt.DECRYPT)
            this.printer.close();
    }
}
