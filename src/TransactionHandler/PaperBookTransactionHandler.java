package TransactionHandler;

import Books.PaperBook;
import Books.Product;
import DeliveryService.Interfaces.DeliveryInfo;
import DeliveryService.ShippingService;
import TransactionHandler.Interfaces.TransactionHandler;

public class PaperBookTransactionHandler implements TransactionHandler {
    private final ShippingService shippingService;

    public PaperBookTransactionHandler(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Override
    public double handleTransaction(Product product, int quantity, DeliveryInfo customerInfo) {
        if (!(product instanceof PaperBook paperBook)) {
            throw new IllegalArgumentException("Expected Paper Book product");
        }

        if (!paperBook.canSaleProduct()) {
            throw new IllegalStateException("Book is not for sale: " + paperBook.getTitle());
        }

        ((PaperBook) product).decreaseStock(quantity); // decrease the product quantity as already it is shipping and this is physical product
        double totalPrice = paperBook.getPrice() * quantity;
        shippingService.deliver(paperBook, quantity, customerInfo);
        return totalPrice;
    }
}
