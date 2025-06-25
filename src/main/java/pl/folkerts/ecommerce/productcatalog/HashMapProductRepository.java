package pl.folkerts.ecommerce.productcatalog;

import java.util.*;

public class HashMapProductRepository implements ProductRepository {

    private final Map<String, Product> storage = new HashMap<>();

    @Override
    public List<Product> allProducts() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void save(Product newProduct) {
        storage.put(newProduct.getId(), newProduct);
    }

    @Override
    public Product loadProductById(String productId) {
        return storage.get(productId);
    }
}
