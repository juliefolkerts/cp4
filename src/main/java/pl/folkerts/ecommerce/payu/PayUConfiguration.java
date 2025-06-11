package pl.folkerts.ecommerce.payu;

public class PayUConfiguration {

    String posID;
    String md5;
    String clientId;
    String clientSecret;
    boolean sandbox;
    //    POS ID (pos_id):                    300746
//    Second key (MD5):                   b6ca15b0d1020e8094d9b5f8d163db54
//    OAuth protocol - client_id:         300746
//    OAuth protocol - client_secret:     2ee86a66e5d97e3fadc400c9f19b065d

    public PayUConfiguration(String posID, String md5, String clientId, String clientSecret, boolean sandbox) {
        this.posID = posID;
        this.md5 = md5;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.sandbox = sandbox;
    }
    public static PayUConfiguration sandbox() {
        return new PayUConfiguration(
                System.getenv("PAYU_POS_ID"),
                System.getenv("PAYU_MD5"),
                System.getenv("PAYU_CLIENT_ID"),
                System.getenv("PAYU_CLIENT_SECRET"),
                false
                );
    }

    public static PayUConfiguration sandbox() {
        return new PayUConfiguration(
            "300746";
            b6ca15b0d1020e8094d9b5f8d163db54;
            "300746";
            "2ee86a66e5d97e3fadc400c9f19b065d";
            true
        );
    }
}
