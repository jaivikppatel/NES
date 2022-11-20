import java.io.IOException;


// CS490 Fall 2022
// Team Members:
//    Hardi Patel - hp489@njit.edu (Team Lead)
//    Jaivik Patel - jpp25@njit.edu
//    Meghan Borad - mpb44@njit.edu
//    Aayushi Shah - as542@njit.edu
//
public class driver {
    public static void main(String[] args) throws IOException {

        //userinterfaceTest
        userInterface user = new userInterface();
        String tName = user.getTeamName();
        String pass = user.getPassword();
        Opt opt = user.getOption();

        //encrypt
        if (opt == Opt.ENCRYPT) {
            redFileInterface rf = redFileInterface.Instance("D:\\F22\\CS490\\NES\\test.txt", opt);
            blackFileInterface bf = blackFileInterface.Instance("D:\\F22\\CS490\\NES\\test1.txt", opt);
            rf.openFile();
            bf.openFile();
            while (!rf.endOfFile()) {
                bf.putNextInt(rf.getNextInt());
            }
            user.displayWheelEndPosition(25);   //get value from facade
            rf.close();
            bf.close();
        }
        //decrypt
        if (opt == Opt.DECRYPT) {
            int start_pos = user.getWheelStartPositon();
            System.out.println("Wheel start position is: " + start_pos);
            redFileInterface rfd = redFileInterface.Instance("D:\\F22\\CS490\\NES\\test3.txt", opt);
            blackFileInterface bfd = blackFileInterface.Instance("D:\\F22\\CS490\\NES\\test2.txt", opt);
            rfd.openFile();
            bfd.openFile();
            while (!bfd.endOfFile()) {
                rfd.putNextChar(bfd.getNextInt());
            }
            rfd.close();
            bfd.close();
        }

        //if success
        user.displayResult(true);

        //if fail
        user.displayResult(false);
    }
}
