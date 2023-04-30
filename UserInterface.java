import java.util.Scanner;

public class UserInterface{
    private Scanner scan;
    
    public UserInterface(Scanner scan){
        this.scan=scan;
    }
    public void start(){
        while(true){
        System.out.println("\t\tWelcome to Online Market\n->Log In\n->Sing Up\n->Exit");
        String logInSingUp=this.scan.nextLine();
        if(logInSingUp.equalsIgnoreCase("Exit")){
            break;
        }else if(logInSingUp.equals("Log In")){

        }else if(logInSingUp.equals("Sing Up")){

        }
    }
}

}