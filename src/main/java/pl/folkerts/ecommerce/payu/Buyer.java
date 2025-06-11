package pl.folkerts.ecommerce.payu;

public class Buyer {
    String email;
    String phone;
    String firstName;
    String lastName;
    String language;

    //                "email": "john.do@example.com",
    //                "phone": "+31621265815",
    //                "firstName": "John",
    //                "lastName": "Do",
    //                "language": "nl"

    public String getEmail() {
        return email;
    }

    public Buyer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Buyer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Buyer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Buyer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Buyer setLanguage(String language) {
        this.language = language;
        return this;
    }

}