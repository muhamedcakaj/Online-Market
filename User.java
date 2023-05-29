import java.util.ArrayList;
import java.time.LocalDate;

public class User {
    private String name;
    private int password;
    private ArrayList<Products> shoppingCart;
    private ArrayList<String> notification;
    private ArrayList<String> paymentHistory;
    private ArrayList<String> inbox;
    private ArrayList<String> InboxStatus;

    public User(String name, int password) {
        this.name = name;
        this.password = password;
        this.shoppingCart = new ArrayList<>();
        this.notification = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
        this.inbox = new ArrayList<>();
        this.InboxStatus = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getPassword() {
        return this.password;
    }

    public ArrayList<Products> getShoppingCartList() {
        return this.shoppingCart;
    }

    public ArrayList<String> getInboxList() {
        return this.inbox;
    }

    public void addProductsToShoppingCart(Products products) {
        this.shoppingCart.add(products);
    }

    public void removeProductsFromShoppingCart(Products products) {
        this.shoppingCart.remove(products);
    }

    public int calculateCartProductsPrice() {
        int sum = 0;
        for (Products productsPrice : this.shoppingCart) {
            sum += productsPrice.getProductPrice();
        }

        return sum;
    }

    public void addNotifications(String notification) {
        this.notification.add(notification);
    }

    public void printNotification() {
        if (this.notification.isEmpty()) {
            System.out.println("There are no notifications available");
        } else {
            for (int i = 0; i < this.notification.size(); i++) {
                System.out.println(i + 1 + ". " + this.notification.get(i));
            }
        }
    }

    public void addHistoryPayment() {
        LocalDate date = LocalDate.now();
        String history = date + "\n-----------------------------------------------";
        for (Products products : this.shoppingCart) {
            history += "\nProduct name: " + products.getProductName() + "\nProduct Price: "
                    + products.getProductPrice();
        }
        history += "\nTotal Price :" + calculateCartProductsPrice()
                + "\n-----------------------------------------------";
        this.paymentHistory.add(history);
        this.shoppingCart.clear();
    }

    public void printPaymentHistory() {
        for (String paymentHistory : this.paymentHistory) {
            System.out.println(paymentHistory);
        }
    }

    public void addInbox(String name) {
        if (!(this.inbox.contains(name))) {
            this.inbox.add(name);
            this.InboxStatus.add("default");
        }
    }

    public void printInboxRequest() {
        for (int i = 0; i < this.inbox.size(); i++) {
            System.out.println(i + 1 + ". " + this.inbox.get(i) + " (" + this.InboxStatus.get(i) + ")");
        }
    }

    public String inboxFindUserName(int index) {
        for (int i = 0; i < this.inbox.size(); i++) {
            if (i == index) {
                return this.inbox.get(i);
            }
        }
        return null;
    }

    public void setInboxStatusToNewChat(String name) {
        int index = 0;
        for (int i = 0; i < this.inbox.size(); i++) {
            if (this.inbox.get(i).equals(name)) {
                index = i;
                break;
            }
        }
        this.InboxStatus.set(index, "New Chat");
    }

    public void setInboxStatusToDelivered(String name) {
        int index = 0;
        for (int i = 0; i < this.inbox.size(); i++) {
            if (this.inbox.get(i).equals(name)) {
                index = i;
                break;
            }
        }
        this.InboxStatus.set(index, "Delivered");
    }

    public String toString() {
        String productsOnCart = "";
        int productNumber = 1;
        for (Products products : this.shoppingCart) {
            productsOnCart += productNumber + ".\n" + products + "\n";
            productNumber++;
        }
        return productsOnCart
                + "\nTotal Price: " + calculateCartProductsPrice();
    }
}