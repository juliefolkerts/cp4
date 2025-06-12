package pl.folkerts.ecommerce.sales;

public class SalesFacade {
    public void addToCart(String customerId, String productId) {}
    public void removeFromCart(String customerId, String productId) {}

    public ReservationDetails acceptOffer(AcceptOfferCommand acceptOfferCommand) {return null;}

    //public void acceptOffer(AcceptOfferCommand acceptOfferCommand) {}

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public void makeReservationPaid(String reservationId) {}
}
