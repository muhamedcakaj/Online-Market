public class Products {
    private String owner;
    private String productName;
    private int productPrice;
    private String productDescribe;
    private String productStatus;

    public Products(String owner, String productName, int productPrice, String productDescribe) {
        this.owner = owner;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescribe = productDescribe;
        this.productStatus = "For Sale";
    }

    public String getOwner() {
        return this.owner;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getProductPrice() {
        return this.productPrice;
    }

    public String getProductStatus() {
        return this.productStatus;
    }

    public void setProductStatus() {
        this.productStatus = "Sold";
    }

    public String toString() {
        return "Owner: " + this.owner
                + "\nProduct: " + this.productName
                + "\nDescription: " + this.productDescribe
                + "\nPrice: " + this.productPrice
                + "\nStatus: " + this.productStatus
                + "\n---------------------------------------";
    }
}