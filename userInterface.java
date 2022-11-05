import java.util.Scanner;

enum Opt{
    ENCRYPT,
    DECRYPT;
}

public class userInterface {
    private String team_name, password;
    private Opt option;
    Scanner scan = new Scanner(System.in);

    protected void getTeamName(){
        System.out.print("Enter Team name: ");
        this.team_name = scan.nextLine();
    }

    protected void getPassword(){
        System.out.print("Enter Password: ");
        this.password = scan.nextLine();
    }

    protected void getOption(){
        System.out.print("Select an Option:\n1. Encrypt\n2. Decrypt\n?: ");
        switch (scan.nextInt()){
            case 1:
                option = Opt.ENCRYPT;
                break;
            case 2:
                option = Opt.DECRYPT;
                break;
            default:
                System.out.println("Invalid Choice!");
                this.getOption();
        }
    }
}
