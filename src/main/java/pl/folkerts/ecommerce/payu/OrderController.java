package pl.folkerts.ecommerce.payu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final PayU payU;

    public OrderController() {
        this.payU = new PayU(new RestTemplate(), PayUConfiguration.sandbox());
    }

    @PostMapping
    public ResponseEntity<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest request) {
        request.calculateTotalFromProducts();
        OrderCreateResponse response = payU.handle(request);
        return ResponseEntity.ok(response);
    }
}

