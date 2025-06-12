package pl.folkerts.ecommerce.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.folkerts.ecommerce.productcatalog.ArrayListProductStorage;
import pl.folkerts.ecommerce.productcatalog.ProductCatalog;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SalesTest {

    ProductCatalog catalog;

    @BeforeEach
    void setup() {
        catalog = new ProductCatalog(new ArrayListProductStorage());
    }

    @Test
    void itShowsEmptyOffer() {
        SalesFacade sales = thereIsSalesModuleUnderTest();
        String customerId = thereIsCustomer("Julie");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }

    @Test
    void itAllowsToCollectProducts() {
        //Arrange
        SalesFacade sales = thereIsSalesModuleUnderTest();
        String customerId = thereIsCustomer("Julie");
        String procuctId = thereIsProduct("Produxt X", BigDecimal.valueOf(10));

        //Act
        sales.addToCart(customerId, procuctId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(BigDecimal.valueOf(10), offer.getTotal());
    }

    @Test
    void itAllowsToCollectProductsByCustomersSeparately() {
        //Arrange
        SalesFacade sales = thereIsSalesModuleUnderTest();
        String customer1 = thereIsCustomer("Julie");
        String customer2 = thereIsCustomer("Nikola");
        String procuctId = thereIsProduct("Produxt X", BigDecimal.valueOf(10));

        //Act
        sales.addToCart(customer1, procuctId);
        sales.addToCart(customer2, procuctId);
        sales.addToCart(customer2, procuctId);

        Offer offer1 = sales.getCurrentOffer(customer1);
        Offer offer2 = sales.getCurrentOffer(customer1);

        assertEquals(BigDecimal.valueOf(10), offer1.getTotal());
        assertEquals(BigDecimal.valueOf(10), offer2.getTotal());
    }

    @Test
    void offerAcceptance() {
        //Arrange
        SalesFacade sales = thereIsSalesModuleUnderTest();
        String customerId = thereIsCustomer("Julie");
        String productId = thereIsProduct("Product X", BigDecimal.valueOf(10));

        //Act
        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

       ReservationDetails details = sales.acceptOffer(
                new AcceptOfferCommand()
                        .setFname("Julie")
                        .setLname("Folkerts")
                        .setEmail("JulieF@gmail.com")
                );

    }

    private String thereIsProduct(String name, BigDecimal price) {
        var id = catalog.createProduct(name, "desc");
        catalog.changePrice(id, price);

        return id;
    }

    private String thereIsCustomer(String customerName) {
        return String.format("customer__%s", customerName);
    }

    private SalesFacade thereIsSalesModuleUnderTest() {
        return new SalesFacade();
    }
}
