import java.util.ArrayList;

public class User {
    private String name;
    private int password;
    private ArrayList<Products> shoppingCart;

    public User(String name, int password) {
        this.name = name;
        this.password = password;
        this.shoppingCart = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getPassword() {
        return this.password;
    }

    public void addProductsToShoppingCart(Products products) {
        this.shoppingCart.add(products);
    }
}