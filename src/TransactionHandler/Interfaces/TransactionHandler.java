package TransactionHandler.Interfaces;
import Books.Product;
import DeliveryService.Interfaces.DeliveryInfo;

public interface TransactionHandler {
    double handleTransaction(Product product, int quantity, DeliveryInfo customerInfo);
}
