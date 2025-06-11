package pl.folkerts.ecommerce.payu;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayU {
    RestTemplate http;
    //private PayUConfiguration cfg;
    private final PayUConfiguration cfg;

    public PayU(RestTemplate http, PayUConfiguration cfg) {
        this.http = http;
        this.cfg = cfg;
    }

    public OrderCreateResponse handle(OrderCreateRequest request) {
        request.setMerchantPosId(cfg.posID);
        var url = getUrl("/api/v2_1/orders");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", String.format("Bearer %s", getToken()));

        HttpEntity<OrderCreateRequest> requestHttpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<OrderCreateResponse> orderCreateResponseResponseEntity = http.postForEntity(
                url,
                requestHttpEntity,
                OrderCreateResponse.class

        );
        return orderCreateResponseResponseEntity.getBody();
    }
    private String getToken() {
        var url = getUrl("/pl/standard/user/oauth/authorize");
        String body = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                cfg.clientId, //not getClientId
                cfg.clientSecret //not getClientSecret?
        );
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", String.format("Bearer %s", body));
        HttpEntity<String> requestHttpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<AuthorizationResponse> response = http.postForEntity(
                url,
                requestHttpEntity,
                AuthorizationResponse.class
        );

        return orderCreateResponse.getBody(); //?????????????????????????????????????????????????????
    }


    private String getUrl(String path) {
        if (cfg.sandboxMode) { //???????????????????????????????????????????????????????????????????
            return String.format("https://secure.snd.payu.com%s", path);

        }
        return String.format("https://secure.payu.com%s", path);
    }
}
