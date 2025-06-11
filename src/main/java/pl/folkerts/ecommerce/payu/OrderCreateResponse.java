package pl.folkerts.ecommerce.payu;

public class OrderCreateResponse {
    Status status;
    String redirectUri;
    String orderId;
    String extOrderId;

    public Status getStatus() {
        return status;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public OrderCreateResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public OrderCreateResponse setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public OrderCreateResponse setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderCreateResponse setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
        return this;
    }
}
