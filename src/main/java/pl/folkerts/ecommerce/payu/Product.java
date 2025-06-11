package pl.folkerts.ecommerce.payu;

public class Product {
    String name;

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Product setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
    String unitPrice;
    String quantity;
}
