import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    private Scanner scan;
    private ArrayList<User> user;

    public UserInterface(Scanner scan) {
        this.scan = scan;
        this.user=new ArrayList<>();
    }

    public void start() {
        while (true) {
            System.out.println("\t\tWelcome to Online Market\n->Log In\n->Sing Up\n->Exit");
            String logInSingUp = this.scan.nextLine();
            if (logInSingUp.equalsIgnoreCase("Exit")) {
                break;
            } else if (logInSingUp.equalsIgnoreCase("Log In")) {
                System.out.println("Write your name");
                String name = this.scan.nextLine();
                System.out.println("Write your password");
                int password = Integer.valueOf(this.scan.nextLine());
                if(logInTrueFalse(name,password)){
                    int userIndex= findUserIndex(name);
                    logIn(userIndex);
                }else{
                    System.out.println("The user name or password is incorrect");
                }

            } else if (logInSingUp.equalsIgnoreCase("Sing Up")) {
                singUp();
            }
        }
    }

    public void logIn(int userIndex) {
        while(true){
        System.out.println("\t\tWelcome "+this.user.get(userIndex).getName()+"\n7.Exit");
        int choice=Integer.valueOf(this.scan.nextLine());
        if(choice==7){
            break;
        }

        }
    }
    public void singUp(){
        System.out.println("Write your name");
        String name=this.scan.nextLine();
        System.out.println("Write your password");
        int password=Integer.valueOf(this.scan.nextLine());
        this.user.add(new User(name,password));
        System.out.println("You have successfully sing up");
    }

    public boolean logInTrueFalse(String name, int password) {
        for(User user:this.user){
            if(user.getName().equals(name)&&
            user.getPassword()==password){
                return true;
            }
        }
        return false;
    }
    public int findUserIndex(String name) {
        int index=0;
        for(int i=0;i<this.user.size();i++){
            if(this.user.get(i).getName().equals(name)){
                index=i;
                break;
            }
        }
        return index;
    }

}