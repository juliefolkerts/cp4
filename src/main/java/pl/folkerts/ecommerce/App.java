package pl.folkerts.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("It is working :)");
        SpringApplication.run(App.class, args);
    }
}
