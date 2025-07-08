package DeliveryService;

import Books.EBook;
import Books.Product;
import DeliveryService.Interfaces.DeliveryInfo;
import DeliveryService.Interfaces.DeliveryService;

public class EmailService implements DeliveryService {
    @Override
    public void deliver(Product product, int quantity, DeliveryInfo deliveryInfo) {
        if(!(product instanceof EBook book) || !(deliveryInfo instanceof EmailInfo emailInfo)) {
            throw new IllegalArgumentException("Invalid product type or Invalid delivery info for emailing");
        }
        System.out.printf(
                "Sending \"%s\" (File Type: %s) to email: %s%n",
                book.getTitle(),
                book.getFileType(),
                emailInfo.getEmail()
        );
    }
}
