public class Products {
    private String owner;
    private String productName;
    private int productPrice;
    private String productDescribe;

    public Products(String owner, String productName, int productPrice, String productDescribe) {
        this.owner = owner;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescribe = productDescribe;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getProductName() {
        return this.productName;
    }

    public String toString() {
        return "Owner: " + this.owner
                + "\nProduct: " + this.productName
                + "\nDescription: " + this.productDescribe
                + "\nPrice: " + this.productPrice
                + "\n---------------------------------------";
    }
}