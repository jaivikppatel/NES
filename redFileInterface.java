import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class redFileInterface {

    // declarations
    private final String filename;
    private final Opt opt;
    private BufferedReader reader;
    private final ArrayList<Integer> int_list = new ArrayList<>();
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
    }

    // gets next int with -32 -- used during encryption
    protected int getNextInt() throws IOException {
        int ret = int_list.get(itr);
        itr++;
        System.out.println((ret-32) + " : " + (char)ret);
        return (ret-32);
    }

    // put next int with +32 -- used during decryption
    protected void putNextChar(int x) throws IOException {
        String s = String.valueOf((char) (x + 32));
      //  System.out.println(x + "~" + (x+32) + "~" + s);
        Files.write(Paths.get(this.filename), s.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    // checks for EOF
    protected boolean endOfFile(){
        return (itr >= int_list.size());
    }

    // closes the instance
    protected void close() throws IOException {
        if (this.opt == Opt.ENCRYPT)
            this.reader.close();
    }
}
