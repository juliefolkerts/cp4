package pl.folkerts.ecommerce.payu;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PayUTest {
    @Test
    void itRegisterPayment() {
        PayU payU = thereIsPayU();

        OrderCreateRequest request = thereIsExampleOrderCreateRequest();
        OrderCreateResponse response = payU.handle(request);

        assertNull(response.getRedirectUri());
        assertNull(response.getOrderId());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        var exampleOrderCreateRequest = new OrderCreateRequest();
        exampleOrderCreateRequest
                .setCustomerIp("127.0.0.1")
                .setDescription("Some service")
                .setCurrencyCode("PLN")
                .setTotalAmount("21000")
                .setExtOrderId(UUID.randomUUID())
                .setBuyer(new Buyer()
                        .setEmail("example@email")
                        .setFirstName("John")
                        .setLastName("Do")
                )
                .setProducts(Arrays.asList(
                        new Product()
                        .setName("My book")
                        .setUnitPrice("11000")
                        .setQuantity("1")
                ));
        return null;
    }

//        curl -X POST https://secure.payu.com/api/v2_1/orders \
//        -H "Content-Type: application/json" \
//        -H "Authorization: Bearer 3e5cac39-7e38-4139-8fd6-30adc06a61bd" \
//        -d '{
//        "notifyUrl": "https://your.eshop.com/notify",
//                "customerIp": "127.0.0.1",
//                "merchantPosId": "145227",
//                "description": "RTV market",
//                "currencyCode": "PLN",
//                "totalAmount": "21000",
//                "extOrderId":"2uikc6gjd99b4lxc75ip4k",
//                "buyer": {
//            "email": "john.doe@example.com",
//                    "phone": "654111654",
//                    "firstName": "John",
//                    "lastName": "Doe",
//                    "language": "pl",
//                    "birthDate": "2006-12-03T00:00:00.000+01:00"
//        },
//        "products": [
//        {
//            "name": "Wireless Mouse for Laptop",
//                "unitPrice": "15000",
//                "quantity": "1"
//        },
//        {
//            "name": "HDMI cable",
//                "unitPrice": "6000",
//                "quantity": "1"
//        }
//        ]
//    }'


    private PayU thereIsPayU() {
        var cfg = PayUConfiguration.sandbox();
        return new PayU(
                new RestTemplate(), //??????????????????????????????????????????????????????????????????????????????????????????????
                cfg);
    }
}
