import java.io.*;
import java.util.Scanner;

public class blackFileInterface {
    private String filename;
    private boolean get_flag, put_flag;
    private File black_file;
    private Scanner scan;
    private PrintWriter out;

    blackFileInterface(String filename){
        this.filename = filename;
        this.get_flag = false;
        this.put_flag = false;
    }

    public static blackFileInterface Instance(String filename) { return new blackFileInterface(filename); }

    protected void openFile() {
        this.black_file = new File(this.filename);
    }

    protected int getNextInt() throws FileNotFoundException {
        if (!this.get_flag) {
            this.get_flag = true;
            scan = new Scanner(this.black_file);
        }
        if (scan.hasNext())
            return scan.nextInt();
        else
            this.get_flag = false;
        return -1;
    }

    protected void putNextInt(int x) throws IOException {
        if (!this.put_flag) {
            this.put_flag = true;
            out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));
        }
        out.println(x);
    }

    // sends out audit message after operations is done
    protected void endOfFile(){
        System.out.println("BlackFile End Reached");
        //send audit message here
        System.out.println("The Audit message has been sent");
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
