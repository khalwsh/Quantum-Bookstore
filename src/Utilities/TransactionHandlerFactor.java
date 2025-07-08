package Utilities;
import Books.EBook;
import Books.PaperBook;
import Books.Product;
import DeliveryService.EmailService;
import DeliveryService.ShippingService;
import TransactionHandler.Interfaces.TransactionHandler;
import TransactionHandler.PaperBookTransactionHandler;
import TransactionHandler.EBookTransactionHandler;


public class TransactionHandlerFactor {
    public static TransactionHandler createHandler(Product product) {
        if (product instanceof PaperBook) {
            return new PaperBookTransactionHandler(new ShippingService());
        } else if (product instanceof EBook) {
            return new EBookTransactionHandler(new EmailService());
        } else {
            throw new IllegalArgumentException("No Transaction handler available for product type");
        }
    }
}
