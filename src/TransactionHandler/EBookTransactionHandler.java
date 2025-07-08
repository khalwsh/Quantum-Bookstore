package TransactionHandler;

import Books.EBook;
import Books.Product;
import DeliveryService.EmailService;
import DeliveryService.Interfaces.DeliveryInfo;
import TransactionHandler.Interfaces.TransactionHandler;

public class EBookTransactionHandler implements TransactionHandler {
    private EmailService emailService;

    public EBookTransactionHandler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public double handleTransaction(Product product, int quantity, DeliveryInfo customerInfo) {
        if (!(product instanceof EBook)) {
            throw new IllegalArgumentException("Quantum book store : Expected EBook product");
        }

        EBook eBook = (EBook) product;

        if (!eBook.canSaleProduct()) {
            throw new IllegalStateException("Quantum book store : Book is not for sale: " + eBook.getTitle());
        }

        double totalPrice = eBook.getPrice() * quantity;
        emailService.deliver(eBook, quantity, customerInfo);
        return totalPrice;
    }
}
