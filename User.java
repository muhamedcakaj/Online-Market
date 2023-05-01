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

    public String toString() {
        String productsOnCart = "";
        for (Products products : this.shoppingCart) {
            productsOnCart += products + "\n";
        }
        return productsOnCart
                + "\n-----------------------------------------------------------------"
                + "\nTotal Price: " + calculateCartProductsPrice();
    }
}