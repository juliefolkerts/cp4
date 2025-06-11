package pl.folkerts.ecommerce.payu;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class PayU {

    private final PayUConfiguration cfg;

    public PayU(RestTemplate http, PayUConfiguration cfg) {
        this.cfg = cfg;
        this.http = http;
    }

    public OrderCreateResponse handle(OrderCreateRequest request) {

        request.setMerchantPosId(cfg.posID);
        var url = getUrl("/api/v2_1/orders");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorisation", String.format("Bearer %s", getToken()));

        HttpEntity<OrderCreateRequest> requestHttpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<OrderCreateResponse> orderCreateResponseResponseEntity = http.postForEntity(
                url,
                requestHttpRequest,
                OrderCreateRequest.class

        );
        return orderCreateResponseResponseEntity.getBody();
    }
    private String getToken() {
        var url = getUrl("/pl/standard/user/oauth/authorize");
        String body = String.format("grant_type=client_credentials&client_secret=%s",
                cfg.clientId,
                cfg.clientSecret);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", String.format("Bearer %s", body));
        HttpEntity<String> requestHttpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<AuthorizationResponse> response = http.postForEntity(
                url,
                requestHttpEntity,
                AuthorizationResponse.class
        );

        return orderCreateResponseEntity.getBody();
    }


    private String getUrl(String path) {
        if (cfg.sandboxMode) {
            return String.format("https://secure.snd.payu.com%s", path);

        }
        return String.format("http://secure.payu.com%s", path);
    }
}
