package BookStore;
import Books.Interfaces.CanSaleProduct;
import Books.Product;
import DeliveryService.Interfaces.DeliveryInfo;
import TransactionHandler.Interfaces.TransactionHandler;
import Utilities.TransactionHandlerFactor;

import java.util.*;

public class BookStore {
    // why not hashmap , I did that to handle multiple product of same product , also good for being extendable as I am not bound to key of certain type
    List<Product>  inventory;

    public BookStore(){
        inventory = new ArrayList<Product>();
    }

    public void addBook(Product book) {
        inventory.add(book);
        System.out.println("Added book: " + book.getTitle() + " (" + book.getId() + ")");
    }

    public Product getBook(String isbn) {
        return inventory.stream()
                .filter(book -> book.getId().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public double buyBook(String isbn, int quantity, DeliveryInfo customerInfo) {
        Product book = getBook(isbn);
        if (book == null) {
            throw new IllegalArgumentException("book not exist");
        }
        if(!(book instanceof CanSaleProduct)) throw new IllegalArgumentException("book is not saleable");
        if(! ((CanSaleProduct)book).canSaleProduct()) throw new IllegalArgumentException("book is not saleable");

        // Now we can buy the item
        TransactionHandler handler = TransactionHandlerFactor.createHandler(book);
        return handler.handleTransaction(book , quantity, customerInfo);
    }

    public List<Product> removeAndReturnOutDatedBooks(int yearsGap) {
        List<Product> res = new ArrayList<>();
        Iterator<Product> iterator = inventory.iterator();
        Calendar current = Calendar.getInstance();
        current.add(Calendar.YEAR, -yearsGap);

        while (iterator.hasNext()) {
            Product book = iterator.next();
            Date bookDate = book.getDate();
            if (bookDate.before(current.getTime())) {
                res.add(book);
                iterator.remove();
            }
        }
        return res;
    }
}
