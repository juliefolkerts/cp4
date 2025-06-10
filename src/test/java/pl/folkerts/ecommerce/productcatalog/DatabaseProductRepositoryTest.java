package pl.folkerts.ecommerce.productcatalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseProductRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupDatabase() {
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` IF EXISTS");

        var sql = """
                  CREATE table `product_catalog__products` (
                      id VARCHAR(100) NOT NULL,
                      name VARCHAR(50) NOT NULL,
                      description VARCHAR(144) NOT NULL,
                      cover VARCHAR(50),
                      price DECIMAL(12,2),
                      PRIMARY KEY(id)
               );
        """;
        jdbcTemplate.execute(sql);
    }

    @Test
    void itQueryDb() {
        var sql = "select now() curr_time";
        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2025");
    }

    @Test
    void initialProductTablesIsEmpty() {
        var result = jdbcTemplate.queryForObject(
                "select count(*) from `product_catalog__products`",
                Integer.class);

        assert result == 0;
    }

    @Test
    void insertSomeProductV1() {
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description)
                VALUES
                    ('ea0665d0-7b07-46bb-9a07-0911ef5a68de', 'Nice product 1', 'nice one'),
                    ('678753c3-69d9-4f8e-bc41-6f0d4d960cac', 'Nice product 2', 'even nicer');
                """;

        jdbcTemplate.execute(sql);

        var result = jdbcTemplate.queryForObject(
                "select count(*) from `product_catalog__products`",
                Integer.class);

        assert result == 2;
    }

    @Test
    void insertSomeProductV2DynamicValues() {
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description)
                VALUES
                    (?, ?, ?);
                """;

        jdbcTemplate.update(sql, "814309e3-eba9-4e72-884e-afe3d7ee1d33", "product X", "Nice product X");

        var result = jdbcTemplate.queryForObject(
                "select count(*) from `product_catalog__products`",
                Integer.class);

        assert result == 1;
    }

    @Test
    void insertSomeProductV3DynamicValuesNamedParameters() {
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description)
                VALUES
                    (:id, :name, :desc);
                """;

        Map<String, Object> params = new HashMap<>();
        params.put("id", "4c33cfee-b968-4223-b1c9-3f203c439d2c");
        params.put("name", "product Y");
        params.put("desc", "Nice product Y");

        var namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedJdbcTemplate.update(sql, params);

        var result = jdbcTemplate.queryForObject(
                "select count(*) from `product_catalog__products`",
                Integer.class);

        assert result == 1;
    }


    @Test
    void itStoresAnsLoadsProduct() {
        Product product = thereIsProduct();
        ProductRepository repository = thereIsProductRepository();

        repository.save(product);
        Product loaded = repository.loadProductById(product.getId());

        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getName(), loaded.getName());
        assertEquals(product.getDescription(), loaded.getDescription());
    }

    private ProductRepository thereIsProductRepository() {
        return new DbProductRepository(jdbcTemplate);
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