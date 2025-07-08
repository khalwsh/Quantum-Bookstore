package TestingModule;

import BookStore.BookStore;
import Books.EBook;
import Books.PaperBook;
import Books.Product;
import Books.ShowCaseBook;
import DeliveryService.EmailInfo;
import DeliveryService.ShippingInfo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QuantumBookstoreFullTest {

    static private BookStore bookStore;

    // set up the test environment before each test
    static private void setUp() {
        bookStore = new BookStore();
        System.out.println("Quantum book store: Setting up new test scenario.");
    }

    static public void testAddBook() {
        setUp();
        System.out.println("Quantum book store: Running testAddBook...");
        PaperBook paperBook = new PaperBook("978-0321765723", "The Lord of the Rings", 25.00, new Date(), 10, "J.R.R. Tolkien");
        bookStore.addBook(paperBook);
        Product retrievedBook = bookStore.getBook("978-0321765723");

        if (retrievedBook != null && "The Lord of the Rings".equals(retrievedBook.getTitle())) {
            System.out.println("Quantum book store: testAddBook completed successfully.");
        } else {
            System.out.println("Quantum book store: testAddBook FAILED. Book not found or title mismatch.");
        }
    }

    static public void testBuyPaperBookSuccessfully() {
        setUp();
        System.out.println("Quantum book store: Running testBuyPaperBookSuccessfully...");
        PaperBook paperBook = new PaperBook("978-0321765723", "The Hobbit", 15.00, new Date(), 5, "J.R.R. Tolkien");
        bookStore.addBook(paperBook);

        ShippingInfo shippingInfo = new ShippingInfo("123 Main St, Anytown, USA");
        double finalPrice = 0.0;
        boolean success = false;
        try {
            finalPrice = bookStore.buyBook("978-0321765723", 2, shippingInfo);
            success = true;
        } catch (IllegalArgumentException e) {
            System.out.println("Quantum book store: Exception during testBuyPaperBookSuccessfully: " + e.getMessage());
        }

        if (success && Math.abs(finalPrice - 30.00) < 0.001 && paperBook.getStock() == 3) {
            System.out.println("Quantum book store: testBuyPaperBookSuccessfully completed successfully. Remaining stock: " + paperBook.getStock());
        } else {
            System.out.println("Quantum book store: testBuyPaperBookSuccessfully FAILED. Final price or stock incorrect. Price: " + finalPrice + ", Stock: " + paperBook.getStock());
        }
    }

    static public void testBuyEBookSuccessfully() {
        setUp();
        System.out.println("Quantum book store: Running testBuyEBookSuccessfully...");
        EBook eBook = new EBook("978-1234567890", "Clean Code", 30.00, new Date(), "PDF", "Robert C. Martin");
        bookStore.addBook(eBook);

        EmailInfo emailInfo = new EmailInfo("test@example.com");
        double finalPrice = 0.0;
        boolean success = false;
        try {
            finalPrice = bookStore.buyBook("978-1234567890", 1, emailInfo);
            success = true;
        } catch (IllegalArgumentException e) {
            System.out.println("Quantum book store: Exception during testBuyEBookSuccessfully: " + e.getMessage());
        }

        if (success && Math.abs(finalPrice - 30.00) < 0.001) {
            System.out.println("Quantum book store: testBuyEBookSuccessfully completed successfully.");
        } else {
            System.out.println("Quantum book store: testBuyEBookSuccessfully FAILED. Final price incorrect. Price: " + finalPrice);
        }
    }

    static public void testBuyBookInsufficientStock() {
        setUp();
        System.out.println("Quantum book store: Running testBuyBookInsufficientStock...");
        PaperBook paperBook = new PaperBook("978-0553103546", "Dune", 12.50, new Date(), 1, "Frank Herbert");
        bookStore.addBook(paperBook);

        ShippingInfo shippingInfo = new ShippingInfo("456 Oak Ave, Othertown, USA");
        boolean exceptionThrown = false;
        String exceptionMessage = "";
        try {
            bookStore.buyBook("978-0553103546", 5, shippingInfo);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
            exceptionMessage = e.getMessage();
        }

        if (exceptionThrown && exceptionMessage.contains("The requested quantity is greater than the stock you request 5 and in stock only 1") && paperBook.getStock() == 1) {
            System.out.println("Quantum book store: testBuyBookInsufficientStock completed successfully. Stock remains: " + paperBook.getStock());
        } else {
            System.out.println("Quantum book store: testBuyBookInsufficientStock FAILED. No exception or wrong message, or stock changed. Message: " + exceptionMessage + ", Stock: " + paperBook.getStock());
        }
    }

    static public void testBuyNonExistentBook() {
        setUp();
        System.out.println("Quantum book store: Running testBuyNonExistentBook...");
        ShippingInfo shippingInfo = new ShippingInfo("789 Pine Ln, Somewhere, USA");
        boolean exceptionThrown = false;
        String exceptionMessage = "";
        try {
            bookStore.buyBook("999-9999999999", 1, shippingInfo);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
            exceptionMessage = e.getMessage();
        }

        if (exceptionThrown && exceptionMessage.contains("book not exist")) {
            System.out.println("Quantum book store: testBuyNonExistentBook completed successfully.");
        } else {
            System.out.println("Quantum book store: testBuyNonExistentBook FAILED. No exception or wrong message. Message: " + exceptionMessage);
        }
    }

    static public void testBuyShowCaseBook() {
        setUp();
        System.out.println("Quantum book store: Running testBuyShowCaseBook...");
        ShowCaseBook showCaseBook = new ShowCaseBook("978-0123456789", "Art of War (Showcase)", 50.00, new Date(), "Sun Tzu");
        bookStore.addBook(showCaseBook);

        ShippingInfo shippingInfo = new ShippingInfo("101 Elm St, Villagetown, USA");
        boolean exceptionThrown = false;
        String exceptionMessage = "";
        try {
            bookStore.buyBook("978-0123456789", 1, shippingInfo);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
            exceptionMessage = e.getMessage();
        }

        if (exceptionThrown && exceptionMessage.contains("book is not saleable")) {
            System.out.println("Quantum book store: testBuyShowCaseBook completed successfully.");
        } else {
            System.out.println("Quantum book store: testBuyShowCaseBook FAILED. No exception or wrong message. Message: " + exceptionMessage);
        }
    }

    static public void testRemoveAndReturnOutDatedBooks() {
        setUp();
        System.out.println("Quantum book store: Running testRemoveAndReturnOutDatedBooks...");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -2);
        PaperBook oldBook1 = new PaperBook("OLD-001", "Ancient History", 10.00, cal.getTime(), 5, "Historian A");
        bookStore.addBook(oldBook1);

        cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -5);
        EBook oldBook2 = new EBook("OLD-002", "Forgotten Lore", 20.00, cal.getTime(), "EPUB", "Lore Master B");
        bookStore.addBook(oldBook2);

        PaperBook currentBook = new PaperBook("NEW-001", "Modern Marvels", 30.00, new Date(), 10, "Innovator C");
        bookStore.addBook(currentBook);

        List<Product> removedBooks = bookStore.removeAndReturnOutDatedBooks(1);

        boolean testPassed = true;
        if (removedBooks.size() != 2) {
            System.out.println("Quantum book store: testRemoveAndReturnOutDatedBooks FAILED. Incorrect number of removed books: " + removedBooks.size());
            testPassed = false;
        }
        if (!removedBooks.contains(oldBook1) || !removedBooks.contains(oldBook2)) {
            System.out.println("Quantum book store: testRemoveAndReturnOutDatedBooks FAILED. Removed list does not contain expected books.");
            testPassed = false;
        }
        if (bookStore.getBook("OLD-001") != null || bookStore.getBook("OLD-002") != null) {
            System.out.println("Quantum book store: testRemoveAndReturnOutDatedBooks FAILED. Outdated books still in inventory.");
            testPassed = false;
        }
        if (bookStore.getBook("NEW-001") == null) {
            System.out.println("Quantum book store: testRemoveAndReturnOutDatedBooks FAILED. Current book was removed.");
            testPassed = false;
        }

        if (testPassed) {
            System.out.println("Quantum book store: testRemoveAndReturnOutDatedBooks completed successfully. Removed books count: " + removedBooks.size());
        } else {
            System.out.println("Quantum book store: testRemoveAndReturnOutDatedBooks FAILED.");
        }
    }

    static public void testDecreaseAndIncreaseStock() {
        setUp();
        System.out.println("Quantum book store: Running testDecreaseAndIncreaseStock...");
        PaperBook paperBook = new PaperBook("STOCK-001", "Stock Management Guide", 10.00, new Date(), 10, "Stock Expert");
        bookStore.addBook(paperBook);

        paperBook.decreaseStock(3);
        if (paperBook.getStock() == 7) {
            System.out.println("Quantum book store: Stock decreased successfully to 7.");
        } else {
            System.out.println("Quantum book store: Stock decrease FAILED. Expected 7, got " + paperBook.getStock());
        }

        paperBook.increaseStock(5);
        if (paperBook.getStock() == 12) {
            System.out.println("Quantum book store: Stock increased successfully to 12.");
        } else {
            System.out.println("Quantum book store: Stock increase FAILED. Expected 12, got " + paperBook.getStock());
        }

        boolean exceptionThrown = false;
        String exceptionMessage = "";
        try {
            paperBook.decreaseStock(15);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
            exceptionMessage = e.getMessage();
        }

        if (exceptionThrown && exceptionMessage.contains("The requested quantity is greater than the stock you request 15 and in stock only 12") && paperBook.getStock() == 12) {
            System.out.println("Quantum book store: testDecreaseAndIncreaseStock (insufficient decrease) completed successfully. Stock remains: " + paperBook.getStock());
        } else {
            System.out.println("Quantum book store: testDecreaseAndIncreaseStock (insufficient decrease) FAILED. No exception or wrong message, or stock changed. Message: " + exceptionMessage + ", Stock: " + paperBook.getStock());
        }
        System.out.println("Quantum book store: testDecreaseAndIncreaseStock completed. Final stock: " + paperBook.getStock());
    }

    static public void testGetBookNotFound() {
        setUp(); // Setup for this test
        System.out.println("Quantum book store: Running testGetBookNotFound...");
        Product book = bookStore.getBook("NON-EXISTENT-ISBN");
        if (book == null) {
            System.out.println("Quantum book store: testGetBookNotFound completed successfully.");
        } else {
            System.out.println("Quantum book store: testGetBookNotFound FAILED. Book found when it should not be.");
        }
    }

    static public void runAllTests() {
        System.out.println("Quantum book store: Starting all tests...");
        testAddBook();
        testBuyPaperBookSuccessfully();
        testBuyEBookSuccessfully();
        testBuyBookInsufficientStock();
        testBuyNonExistentBook();
        testBuyShowCaseBook();
        testRemoveAndReturnOutDatedBooks();
        testDecreaseAndIncreaseStock();
        testGetBookNotFound();
        System.out.println("Quantum book store: All tests finished.");
    }
}
