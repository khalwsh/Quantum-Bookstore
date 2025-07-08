package DeliveryService;

import Books.EBook;
import Books.Product;
import DeliveryService.Interfaces.DeliveryInfo;
import DeliveryService.Interfaces.DeliveryService;

public class EmailService implements DeliveryService {

    @Override
    public void deliver(Product product, int quantity, DeliveryInfo deliveryInfo) {
        if(!(product instanceof EBook) || !(deliveryInfo instanceof EmailInfo)) {
            throw new IllegalArgumentException("Quantum book store : Invalid product type or Invalid delivery info for emailing");
        }
        EBook book = (EBook) product;
        EmailInfo emailInfo = (EmailInfo) deliveryInfo;
        System.out.printf(
                "Quantum Book Store: Sending \"%s\" (File Type: %s) to email: %s%n",
                book.getTitle(),
                book.getFileType(),
                emailInfo.getEmail()
        );
    }
}
