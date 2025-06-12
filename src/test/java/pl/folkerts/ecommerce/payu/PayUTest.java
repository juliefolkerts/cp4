package pl.folkerts.ecommerce.payu;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PayUTest {
    @Test
    void itRegisterPayment() {
        PayU payU = thereIsPayU();

        OrderCreateRequest request = thereIsExampleOrderCreateRequest();
        OrderCreateResponse response = payU.handle(request);

        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getOrderId());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        var exampleOrderCreateRequest = new OrderCreateRequest();
        exampleOrderCreateRequest
                .setCustomerIp("127.0.0.1")
                .setDescription("Some service")
                .setCurrencyCode("PLN")
                .setTotalAmount("21000")
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer(new Buyer()
                        .setEmail("example@email")
                        .setFirstName("John")
                        .setLastName("Doe")
                )
                .setProducts(Arrays.asList(
                        new Product()
                                .setName("Julie")
                                .setUnitPrice("100")
                                .setQuantity("5")
                ));

        return exampleOrderCreateRequest;
    }


    private PayU thereIsPayU() {
        var cfg = PayUConfiguration.sandbox();

        return new PayU(new RestTemplate(), cfg);
    }
}
