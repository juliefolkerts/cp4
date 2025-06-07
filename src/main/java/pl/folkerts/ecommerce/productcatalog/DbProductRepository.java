package pl.folkerts.ecommerce.productcatalog;

import java.util.List;

public class DbProductRepository implements ProductRepository {
    @Override
    public List<Product> allProducts() {
        return List.of();
    }

    @Override
    public void save(Product newProduct) {

    }

    @Override
    public Product loadProductById(String productId) {
        return null;
    }
}
