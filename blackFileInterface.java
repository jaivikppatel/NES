import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class blackFileInterface {
    private final String filename;
    private final Opt opt;
    private BufferedReader reader;
    private PrintWriter printer;
    private int itr;
    private ArrayList<Integer> int_list = new ArrayList<>();

    blackFileInterface(String filename, Opt opt){
        this.filename = filename;
        this.opt = opt;
        this.itr = 0;
    }

    public static blackFileInterface Instance(String filename, Opt opt) { return new blackFileInterface(filename, opt); }

    protected void openFile() throws IOException {
        if (this.opt == Opt.ENCRYPT){
            int temp;
            this.reader = new BufferedReader(new FileReader(this.filename));
            while ((temp = Integer.parseInt(reader.readLine())) != -1){
                int_list.add(temp);
            }
            Collections.reverse(int_list);
        }
        if (this.opt == Opt.DECRYPT)
            this.printer = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
    }

    protected int getNextInt() throws IOException {
        int ret = int_list.get(itr);
        itr++;
        return ret;
    }

    protected void putNextInt(int x) throws IOException {
        printer.println(x);
    }

    // check if EOF
    protected boolean endOfFile(){
        return (itr >= int_list.size());
    }

    // closes the instance
    protected void close() throws IOException {
        this.reader.close();
        this.printer.close();
    }
}
