package DeliveryService;

import Books.PaperBook;
import Books.Product;
import DeliveryService.Interfaces.DeliveryInfo;
import DeliveryService.Interfaces.DeliveryService;

public class ShippingService implements DeliveryService {

    @Override
    public void deliver(Product product, int quantity, DeliveryInfo deliveryInfo) {
        if(!(product instanceof PaperBook) || !(deliveryInfo instanceof ShippingInfo)) {
            throw new IllegalArgumentException("Quantum book store : Invalid product type or Invalid delivery info for shipping");
        }
        PaperBook book = (PaperBook) product;
        ShippingInfo shippingInfo = (ShippingInfo) deliveryInfo;
        System.out.printf(
                "Quantum Book Store: Sending \"%s\" (quantity: %d) to address: %s%n",
                book.getTitle(),
                quantity,
                shippingInfo.getAddress()
        );
    }
}
