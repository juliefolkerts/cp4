package pl.folkerts.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.folkerts.productcatalog.ArrayListProductStorage;
import pl.folkerts.productcatalog.ProductCatalog;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("It works");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createProductCatalog() {
        return new ProductCatalog(
                new ArrayListProductStorage()
        );
    }
}
