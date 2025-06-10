package pl.folkerts.ecommerce.productcatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.folkerts.ecommerce.productcatalog.ArrayListProductStorage;
import pl.folkerts.ecommerce.productcatalog.ProductCatalog;

@Configuration
public class ProductCatalogConfiguration {
    @Bean
    ProductCatalog createProductCatalog(ProductRepository productRepository) {
        ProductCatalog catalog = new ProductCatalog(productRepository);

        catalog.createProduct("nice one 1", "desc");
        catalog.createProduct("nice one 2", "desc");
        catalog.createProduct("nice one 3", "desc");
        catalog.createProduct("nice one 4", "desc");

        return catalog;
    }

    //@Autowired
    //dbcTemplate jdbcTemplate;

    @Bean
    ProductRepository createMyProductRepository(JdbcTemplate jdbcTemplate) {
        return new DbProductRepository(jdbcTemplate);
    }
}
