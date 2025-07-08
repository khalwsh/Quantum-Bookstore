package DeliveryService;
import Books.PaperBook;
import Books.Product;
import DeliveryService.Interfaces.DeliveryInfo;
import DeliveryService.Interfaces.DeliveryService;

public class ShippingService implements DeliveryService {
    @Override
    public void deliver(Product product, int quantity, DeliveryInfo deliveryInfo) {
        if(!(product instanceof PaperBook book) || !(deliveryInfo instanceof ShippingInfo shippingInfo)) {
            throw new IllegalArgumentException("Invalid product type or Invalid delivery info for shipping");
        }
        System.out.printf(
                "Sending \"%s\" (quantity: %d) to address: %s%n",
                book.getTitle(),
                quantity,
                shippingInfo.getAddress()
        );
    }
}
