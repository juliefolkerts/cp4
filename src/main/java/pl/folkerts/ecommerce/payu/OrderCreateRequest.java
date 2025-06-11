package pl.folkerts.ecommerce.payu;

public class OrderCreateRequest {

    String customerIp;
    String cuurencyCode;
    String totalAmount;
    List<Product> products;
    Buyer buyer;
    //String customerIp;
    //String customerIp;
    //String customerIp;
    String extOrderId;

    public OrderCreateRequest setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
        return this;
    }

    public OrderCreateRequest setCuurencyCode(String cuurencyCode) {
        this.cuurencyCode = cuurencyCode;
        return this;
    }

    public OrderCreateRequest setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderCreateRequest setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public OrderCreateRequest setBuyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }

    public OrderCreateRequest setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
        return this;
    }


}
