import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class blackFileInterface {
    private final String filename;
    private final Opt opt;
    private Scanner scan;
    private int itr;
    private final ArrayList<Integer> int_list = new ArrayList<>();

    blackFileInterface(String filename, Opt opt){
        this.filename = filename;
        this.opt = opt;
        this.itr = 0;
    }

    public static blackFileInterface Instance(String filename, Opt opt) { return new blackFileInterface(filename, opt); }

    protected void openFile() throws IOException {
        if (this.opt == Opt.DECRYPT){
            scan = new Scanner(new File(this.filename));
            while (scan.hasNext()){
                int_list.add(scan.nextInt());
            }
            Collections.reverse(int_list);
        }
    }

    protected int getNextInt() throws IOException {
        int ret = int_list.get(itr);
        itr++;
        return ret;
    }

    protected void putNextInt(int x) throws IOException {
        Files.write(Paths.get(this.filename), String.valueOf(x).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        Files.write(Paths.get(this.filename), "\n".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    // check if EOF
    protected boolean endOfFile(){
        return (itr >= int_list.size());
    }

    // closes the instance
    protected void close() throws IOException {
        if (opt == Opt.DECRYPT)
            scan.close();
    }
}
