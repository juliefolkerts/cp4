package pl.folkerts.ecommerce.productcatalog;

import org.junit.jupiter.api.Test;
import pl.folkerts.ecommerce.productcatalog.ArrayListProductStorage;
import pl.folkerts.ecommerce.productcatalog.Product;
import pl.folkerts.ecommerce.productcatalog.ProductRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseProductRepositoryTest {

    @Test
    void itStoresAnsLoadsProduct() {
        Product product = thereIsProduct();
        ProductRepository repository = thereIsProductRepository();

        repository.save(product);
        Product loaded = repository.loadProductById(product.getId());

        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getId(), loaded.getDescription());
    }

    private ProductRepository thereIsProductRepository() {
        return new DbProductRepository();
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID(), "test", "desc");
    }

    @Test
    void itLoadsAllProducts() {
        Product product = thereIsProduct();
        ProductRepository repository = thereIsProductRepository();

        repository.save(product);
        List<Product> products = repository.allProducts();

        assertEquals(1, products.size());
    }
}