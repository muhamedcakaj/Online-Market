import java.util.ArrayList;
import java.time.LocalDate;

public class User {
    private String name;
    private int password;
    private ArrayList<Products> shoppingCart;
    private ArrayList<String> notification;
    private ArrayList<String> paymentHistory;

    public User(String name, int password) {
        this.name = name;
        this.password = password;
        this.shoppingCart = new ArrayList<>();
        this.notification = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
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
            history += "\nProduct name: " + products.getProductName() + "\nProduct Price: " + products.getProductPrice()
                    + "\n";
        }
        history += calculateCartProductsPrice() + "\n-----------------------------------------------";
        this.paymentHistory.add(history);
        this.shoppingCart.clear();
    }

    public void printPaymentHistory() {
        for (String paymentHistory : this.paymentHistory) {
            System.out.println(paymentHistory);
        }
    }

    public String toString() {
        String productsOnCart = "";
        for (Products products : this.shoppingCart) {
            productsOnCart += products + "\n";
        }
        return productsOnCart
                + "\nTotal Price: " + calculateCartProductsPrice();
    }
}