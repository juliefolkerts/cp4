package pl.folkerts.ecommerce.payu;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        exampleOrderCreateRequest.setCustomerIp("127.0.0.1");
        exampleOrderCreateRequest.setDescription("Some service");
        exampleOrderCreateRequest.setCurrencyCode("PLN");
        exampleOrderCreateRequest.setTotalAmount("21000");
        exampleOrderCreateRequest.setExtOrderId(UUID.randomUUID().toString());

        Buyer buyer = new Buyer();
        buyer.setEmail("example@email");
        buyer.setFirstName("John");
        buyer.setLastName("Doe");
        exampleOrderCreateRequest.setBuyer(buyer);

        Product product = new Product();
        product.setName("Julie");
        product.setUnitPrice("100");
        product.setQuantity("5");

        exampleOrderCreateRequest.setProducts(Arrays.asList(product));

        return exampleOrderCreateRequest;
    }



    private PayU thereIsPayU() {
        var cfg = PayUConfiguration.sandbox();

        return new PayU(new RestTemplate(), cfg);
    }
}


