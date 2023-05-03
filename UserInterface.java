import java.util.Scanner;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserInterface {
    private Scanner scan;
    private ArrayList<User> user;
    private ArrayList<Products> products;
    private ArrayList<Inbox> inbox;

    public UserInterface(Scanner scan) {
        this.scan = scan;
        this.user = new ArrayList<>();
        this.products = new ArrayList<>();
        this.inbox = new ArrayList<>();
    }

    public void start() {
        importProductsAndUsersFromFile();
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
                if (logInTrueFalse(name, password)) {
                    int userIndex = findUserIndex(name);
                    logIn(userIndex);
                } else {
                    System.out.println("The user name or password is incorrect");
                }

            } else if (logInSingUp.equalsIgnoreCase("Sing Up")) {
                singUp();
            }
        }
    }

    public void logIn(int userIndex) {
        System.out.println("\t\tWelcome " + this.user.get(userIndex).getName());
        while (true) {
            System.out.println("->1.Products on Sale\n->2.Search For Products\n->3.View Shopping Cart"
                    + "\n->4.Add a product for sale\n->5.Notifications\n->6.Inbox"
                    + "\n->7.Payment History\n->8.Checkout\n->9.Exit");
            int choice = Integer.valueOf(this.scan.nextLine());
            if (choice == 9) {
                break;
            } else if (choice == 1) {
                productsOnSale(userIndex);
            } else if (choice == 2) {
                searchForProducts(userIndex);
            } else if (choice == 3) {
                viewShoppingCart(userIndex);
            } else if (choice == 4) {
                addProductForSale(userIndex);
            } else if (choice == 5) {
                notifications(userIndex);
            } else if (choice == 6) {
                inbox(userIndex);
            } else if (choice == 7) {
                paymentHistory(userIndex);
            } else if (choice == 8) {
                checkout(userIndex);
            }

        }
    }

    public void singUp() {
        try {
            try (FileWriter writer = new FileWriter("user.txt", true)) {
                System.out.println("Write your name");
                String name = this.scan.nextLine();
                System.out.println("Write your password");
                int password = Integer.valueOf(this.scan.nextLine());
                if (userExistTrueFalse(name)) {
                    System.out.println("This name already exist please try another one");
                } else {
                    this.user.add(new User(name, password));

                    String sentToFile = name + "," + password + "\n";
                    writer.write(sentToFile);
                    System.out.println("You have successfully sing up");
                }
            }
        } catch (Exception e) {
            System.out.println("Write the necessary information correctly");
        }
    }

    public void productsOnSale(int userIndex) {
        if (this.products.isEmpty()) {
            System.out.println("There are no products at the moment for sale");
        } else {
            while (true) {
                System.out.println("These are the currently products for sale");
                for (int i = 0; i < this.products.size(); i++) {
                    System.out.println(i + 1 + ".\n" + this.products.get(i));
                }
                addProductToShoppingCartMethod(userIndex);
                break;

            }
        }
    }

    public void searchForProducts(int userIndex) {
        while (true) {
            System.out.println("Search:");
            String search = this.scan.nextLine();
            if (searchProductFoundTrueFalse(search)) {

                for (int i = 0; i < this.products.size(); i++) {
                    if (this.products.get(i).getProductName().contains(search)) {
                        System.out.println(i + 1 + ".\n" + this.products.get(i));
                    }
                }
                addProductToShoppingCartMethod(userIndex);
                break;
            } else {
                System.out.println("Product not found");
                break;
            }
        }
    }

    public void viewShoppingCart(int userIndex) {
        if (this.user.get(userIndex).getShoppingCartList().isEmpty()) {
            System.out.println("There are no products at the moment on your cart");
        } else {
            while (true) {
                System.out.println(this.user.get(userIndex));
                System.out.println("->Press R to remove something from the cart\n->Press enter to get back");
                String choice = this.scan.nextLine();
                if (choice.equals("")) {
                    break;
                } else if (choice.equalsIgnoreCase("R")) {
                    System.out.println("Choose by number which product you want to remove");
                    int removeProductNumber = Integer.valueOf(this.scan.nextLine());
                    if (removeProductNumber <= this.products.size()) {
                        this.user.get(userIndex)
                                .removeProductsFromShoppingCart(this.products.get(removeProductNumber - 1));
                    } else {
                        System.out.println("Choose the correct number please");
                    }
                }
            }
        }
    }

    public void addProductForSale(int userIndex) {
        while (true) {
            try {
                try (FileWriter writer = new FileWriter("products.txt", true)) {
                    System.out.println("Type product name");
                    String productName = this.scan.nextLine();
                    System.out.println("Type product price");
                    int price = Integer.valueOf(this.scan.nextLine());
                    System.out.println("Describe your product");
                    String describe = this.scan.nextLine();
                    String sentToFile = this.user.get(userIndex).getName() + "," + productName + "," + price + ","
                            + describe + "\n";
                    writer.write(sentToFile);
                    this.products.add(new Products(this.user.get(userIndex).getName(), productName, price, describe));
                    System.out.println("You have successfully added a product for sale");
                    System.out.println("->Press C to continue\n->Press enter to get back");
                    String choice = this.scan.nextLine();
                    if (choice.equals("")) {
                        break;
                    } else if (choice.equalsIgnoreCase("C")) {
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println("Write the necessary information correctly");
            }

        }
    }

    public void notifications(int userIndex) {
        this.user.get(userIndex).printNotification();
    }

    public void inbox(int userIndex) {
        this.user.get(userIndex).printInboxRequest();
        System.out.println("Choose one by number to continue conversation");
        int number = Integer.valueOf(this.scan.nextLine());
        if (number <= this.user.get(userIndex).getInboxList().size()) {
            String secondUserName = this.user.get(userIndex).inboxFindUserName(number);
            int secondUserIndex = findUserIndex(secondUserName);
            int inboxIndex = findMessageIndex(this.user.get(userIndex).getName(),
                    this.user.get(secondUserIndex).getName());
            while (true) {
                this.inbox.get(inboxIndex);
                System.out.println("Write a message(Press enter to get back):");
                String message = this.scan.nextLine();
                if (message.equals("")) {
                    break;
                }
                this.inbox.get(inboxIndex).addMessaging(this.user.get(userIndex).getName(), message);
            }
        } else {
            System.out.println("Choose the correct number please");
        }
    }

    public void paymentHistory(int userIndex) {
        this.user.get(userIndex).printPaymentHistory();
    }

    public void checkout(int userIndex) {
        System.out.println(this.user.get(userIndex));
        System.out.println("Do you really want to checkout\n->Yes\n->No");
        String checkout = this.scan.nextLine();
        if (checkout.equalsIgnoreCase("Yes")) {
            for (Products notifications : this.user.get(userIndex).getShoppingCartList()) {
                int findUserIndex = findUserIndex(notifications.getOwner());
                String notification = "Your product " + notifications.getProductName() + " has been sold to "
                        + this.user.get(userIndex).getName();
                System.out.println(findUserIndex);
                this.user.get(findUserIndex).addNotifications(notification);
            }
            this.user.get(userIndex).addHistoryPayment();
            System.out.println("You have sucesfully checkout");
        }
    }

    public void addProductToShoppingCartMethod(int userIndex) {
        while (true) {
            System.out.println(
                    "->Press B to buy one of them\n->Press N to negotiate about the price \n->Press enter to get back");
            String choice = this.scan.nextLine();
            if (choice.equals("")) {
                break;
            } else if (choice.equalsIgnoreCase("B")) {
                System.out.println("Choose a product by number to add to your cart");
                int productChoose = Integer.valueOf(this.scan.nextLine());
                if (productChoose <= this.products.size()) {
                    this.user.get(userIndex).addProductsToShoppingCart(this.products.get(productChoose - 1));
                } else {
                    System.out.println("Choose the correct number please");
                }
            } else if (choice.equalsIgnoreCase("N")) {
                inboxMethodForShoppingCart(userIndex);
            }
        }
    }

    public void inboxMethodForShoppingCart(int userIndex) {
        System.out.println("Choose a product by number to negotiate about the price");
        int productChoose = Integer.valueOf(this.scan.nextLine());
        if (productChoose <= this.products.size()) {
            while (true) {
                if (findMessageHisory(this.user.get(userIndex).getName(),
                        this.products.get(productChoose - 1).getOwner())) {

                    int inboxIndex = findMessageIndex(this.user.get(userIndex).getName(),
                            this.products.get(productChoose - 1).getOwner());
                    this.inbox.get(inboxIndex).printMessage();
                    System.out.println("Write a message(Press enter to get back):");
                    String message = this.scan.nextLine();
                    if (message.equals("")) {
                        break;
                    }
                    this.inbox.get(inboxIndex).addMessaging(this.user.get(userIndex).getName(), message);
                } else {
                    this.inbox.add(
                            new Inbox(this.user.get(userIndex).getName(), this.products.get(productChoose).getOwner()));
                    int secondUserIndex = findUserIndex(this.products.get(productChoose).getOwner());
                    this.user.get(secondUserIndex).addInbox(this.user.get(userIndex).getName());
                }
            }
        } else {
            System.out.println("Choose the correct number please");
        }

    }

    public boolean searchProductFoundTrueFalse(String name) {
        for (Products products : this.products) {
            if (products.getProductName().contains(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean userExistTrueFalse(String name) {
        for (User user : this.user) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean logInTrueFalse(String name, int password) {
        for (User user : this.user) {
            if (user.getName().equals(name) &&
                    user.getPassword() == password) {
                return true;
            }
        }
        return false;
    }

    public int findUserIndex(String name) {
        int index = 0;
        for (int i = 0; i < this.user.size(); i++) {
            if (this.user.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean findMessageHisory(String name, String name2) {
        for (Inbox messaging : this.inbox) {
            if (messaging.conversationHistory(name, name2)) {
                return true;
            }
        }
        return false;
    }

    public int findMessageIndex(String name, String name2) {
        int index = 0;
        for (int i = 0; i < this.inbox.size(); i++) {
            if (this.inbox.get(i).conversationHistory(name, name2)) {
                index = i;
            }
        }
        return index;

    }

    public void importProductsAndUsersFromFile() {
        try (Scanner scan = new Scanner(Paths.get("products.txt"))) {
            while (scan.hasNextLine()) {
                String file = scan.nextLine();

                String[] parts = file.split(",");

                int price = Integer.valueOf(parts[2]);

                this.products.add(new Products(parts[0], parts[1], price, parts[3]));
            }
        } catch (Exception e) {

        }
        try (Scanner scanner = new Scanner(Paths.get("user.txt"))) {
            while (scanner.hasNextLine()) {
                String file = scanner.nextLine();

                String[] parts = file.split(",");
                int password = Integer.valueOf(parts[1]);

                this.user.add(new User(parts[0], password));
            }
        } catch (Exception e) {

        }
    }

}