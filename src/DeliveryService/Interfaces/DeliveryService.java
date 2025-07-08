package DeliveryService.Interfaces;

import Books.Product;

public interface DeliveryService {
    void deliver(Product product, int quantity, DeliveryInfo deliveryInfo);
}
