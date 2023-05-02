import java.util.Scanner;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserInterface {
    private Scanner scan;
    private ArrayList<User> user;
    private ArrayList<Products> products;

    public UserInterface(Scanner scan) {
        this.scan = scan;
        this.user = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public void start() {

        while (true) {
            importProductsAndUsersFromFile();
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
                    + "->\n4.Add a product for sale");
            int choice = Integer.valueOf(this.scan.nextLine());
            if (choice == 7) {
                break;
            } else if (choice == 1) {
                productsOnSale(userIndex);
            } else if (choice == 2) {
                searchForProducts(userIndex);
            } else if (choice == 3) {
                viewShoppingCart(userIndex);
            } else if (choice == 4) {
                addProductForSale(userIndex);
            }

        }
    }

    public void singUp() {
        try {
            try (FileWriter writer = new FileWriter("user.txt")) {
                System.out.println("Write your name");
                String name = this.scan.nextLine();
                System.out.println("Write your password");
                int password = Integer.valueOf(this.scan.nextLine());
                if (userExistTrueFalse(name)) {
                    System.out.println("This name already exist please try another one");
                } else {
                    this.user.add(new User(name, password));
                    String sentToFile = name + "," + password;
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
                System.out.println("->Press B to buy one of them\n->Press enter to get back");
                String choice = this.scan.nextLine();
                if (choice.equals("")) {
                    break;
                } else if (choice.equalsIgnoreCase("B")) {
                    System.out.println("Choose a product by number to add to your cart");
                    int productChoose = Integer.valueOf(this.scan.nextLine());
                    if (productChoose >= this.products.size()) {
                        this.user.get(userIndex).addProductsToShoppingCart(this.products.get(productChoose));
                    } else {
                        System.out.println("Choose the correct number please");
                    }
                }
            }
        }
    }

    public void searchForProducts(int userIndex) {
        while (true) {
            System.out.println("Search:");
            String search = this.scan.nextLine();
            for (int i = 0; i < this.products.size(); i++) {
                if (this.products.get(i).getProductName().contains(search)) {
                    System.out.println(i + 1 + ".\n" + this.products.get(i));
                }
            }
            System.out.println("->Press B to buy one of them\n->Press enter to get back");
            String choice = this.scan.nextLine();
            if (choice.equals("")) {
                break;
            } else if (choice.equalsIgnoreCase("B")) {
                System.out.println("Choose a product by number to add to your cart");
                int productChoose = Integer.valueOf(this.scan.nextLine());
                if (productChoose >= this.products.size()) {
                    this.user.get(userIndex).addProductsToShoppingCart(this.products.get(productChoose));
                } else {
                    System.out.println("Choose the correct number please");
                }
            }
        }
    }

    public void viewShoppingCart(int userIndex) {
        while (true) {
            this.user.get(userIndex);
            System.out.println("->Press R to remove something from the cart\n->Press enter to get back");
            String choice = this.scan.nextLine();
            if (choice.equals("")) {
                break;
            } else if (choice.equalsIgnoreCase("R")) {
                System.out.println("Choose by number which product you want to remove");
                int removeProductNumber = Integer.valueOf(this.scan.nextLine());
                this.user.get(userIndex).removeProductsFromShoppingCart(this.products.get(removeProductNumber));
            }
        }
    }

    public void addProductForSale(int userIndex) {
        while (true) {
            try {
                try (FileWriter writer = new FileWriter("products.txt")) {
                    System.out.println("Type product name");
                    String productName = this.scan.nextLine();
                    System.out.println("Type product price");
                    int price = Integer.valueOf(this.scan.nextLine());
                    System.out.println("Describe your product");
                    String describe = this.scan.nextLine();
                    String sentToFile = this.user.get(userIndex).getName() + "," + productName + "," + price + ","
                            + describe;
                    writer.write(sentToFile);
                    this.products.add(new Products(this.user.get(userIndex).getName(), productName, price, describe));
                    System.out.println("You have successfully added a product for sale");
                    System.out.println("->Press C to continue\nPress enter to get back");
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