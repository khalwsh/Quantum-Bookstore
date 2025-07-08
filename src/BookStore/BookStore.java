package BookStore;

import Books.Interfaces.CanSaleProduct;
import Books.Product;
import DeliveryService.Interfaces.DeliveryInfo;
import TransactionHandler.Interfaces.TransactionHandler;
import Utilities.TransactionHandlerFactor;

import java.util.*;

public class BookStore {
    List<Product>  inventory;
    BookStore(){
        inventory = new ArrayList<Product>();
    }
    public void addBook(Product book) {
        inventory.add(book);
        System.out.println("Quantum book store: Added book: " + book.getTitle() + " (" + book.getId() + ")");
    }
    public Product getBook(String isbn) {
        for (Product book : inventory) {
            if (book.getId().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    public double buyBook(String isbn, int quantity, DeliveryInfo customerInfo) {
        Product book = getBook(isbn);
        if (book != null) {
            throw new IllegalArgumentException("Quantum book store: book not exist");
        }
        if(!(book instanceof CanSaleProduct)) throw new IllegalArgumentException("Quantum book store: book is not saleable");
        if(! ((CanSaleProduct)book).canSaleProduct()) throw new IllegalArgumentException("Quantum book store: book is not saleable");

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
