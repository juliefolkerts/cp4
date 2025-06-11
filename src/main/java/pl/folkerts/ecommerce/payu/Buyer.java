package pl.folkerts.ecommerce.payu;

public class Buyer {
    public Buyer setEmail(String email) {
        this.email = email;
        return this;
    }

    public Buyer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Buyer setLasrName(String lasrName) {
        this.lasrName = lasrName;
        return this;
    }

    String email;
    String firstName;
    String lasrName;
}
