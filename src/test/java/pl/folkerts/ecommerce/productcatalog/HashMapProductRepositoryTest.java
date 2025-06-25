package pl.folkerts.ecommerce.productcatalog;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapProductRepositoryTest {

    @Test
    void itStoresAnsLoadsProduct() {
        //Arrange
        Product product = thereIsProduct();
        ProductRepository repository = thereIsProductRepository();
        //Act
        repository.save(product);
        Product loaded = repository.loadProductById(product.getId());
        //Assert
        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getDescription(), loaded.getDescription());
    }

    @Test
    void itLoadsAllProducts() {
        Product product = thereIsProduct();
        ProductRepository repository = thereIsProductRepository();

        repository.save(product);
        List<Product> products = repository.allProducts();

        assertEquals(1, products.size());
    }

    private ProductRepository thereIsProductRepository(){
        return new ArrayListProductStorage();
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID(), "test", "desc");
    }

}