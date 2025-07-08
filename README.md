# Quantum Bookstore Application

This project implements a simple command-line based bookstore application in Java, demonstrating basic object-oriented principles, inheritance, interfaces, and transaction handling for different types of books.

## Features

The Quantum Bookstore application provides the following core functionalities:

* **Book Management:**
    * Add various types of books (Paper Books, E-Books, Showcase Books) to the inventory.
    * Retrieve books by their ISBN.
    * Remove outdated books from the inventory based on a specified age gap.

* **Book Sales & Transactions:**
    * **Buy Paper Books:** Handle purchases of physical books, including stock management (decreasing stock upon sale).
    * **Buy E-Books:** Facilitate purchases of digital books, simulating email delivery.
    * **Stock Management:** Increase and decrease stock for paper books, with checks to prevent selling more than available.
    * **Saleability Checks:** Ensures only saleable products can be purchased. Showcase books are explicitly not for sale.
    * **Error Handling:** Robust error handling for scenarios like:
        * Attempting to buy a non-existent book.
        * Attempting to buy a non-saleable product.
        * Attempting to buy a paper book with insufficient stock.

* **Delivery Services:**
    * **Shipping:** For physical books, a `ShippingService` is simulated.
    * **Email Delivery:** For e-books, an `EmailService` is simulated.

## Project Structure

The project is organized into several packages to maintain modularity and separation of concerns:

```
.
├── BookStore
│   └── BookStore.java             # Main class managing inventory and sales logic
├── Books
│   ├── Book.java                  # Abstract base class for all book types
│   ├── EBook.java                 # Represents an electronic book
│   ├── PaperBook.java             # Represents a physical book with stock
│   ├── Product.java               # Abstract base class for all products
│   └── ShowCaseBook.java          # Represents a book for display only (not for sale)
│   └── Interfaces
│       ├── CanEmailProduct.java   # Interface for products that can be emailed
│       ├── CanSaleProduct.java    # Interface for products that can be sold
│       └── CanShipProduct.java    # Interface for products that can be shipped
├── DeliveryService
│   ├── EmailInfo.java             # Delivery information for email
│   ├── EmailService.java          # Service for simulating email delivery
│   ├── ShippingInfo.java          # Delivery information for shipping
│   └── Interfaces
│       ├── DeliveryInfo.java      # Interface for all delivery information types
│       └── DeliveryService.java   # Interface for all delivery services
├── TestingModule
│   └── QuantumBookstoreTestRunner.java # Class to run all custom tests
├── TransactionHandler
│   ├── EmailTransactionHandler.java # Handles transactions for EBooks
│   ├── PaperBookTransactionHandler.java # Handles transactions for PaperBooks
│   └── Interfaces
│       └── TransactionHandler.java # Interface for all transaction handlers
└── Utilities
└── TransactionHandlerFactor.java # Factory for creating appropriate transaction handlers
```

## How to Run

To compile and run this project:

1.  **Save the files:** Ensure all `.java` files are placed in their respective package directories (e.g., `BookStore.java` in a `BookStore` folder, `Book.java` in a `Books` folder, etc.). The root directory should contain the `BookStore`, `Books`, `DeliveryService`, `TestingModule`, `TransactionHandler`, and `Utilities` folders.

2.  **Compile:** Open a terminal or command prompt, navigate to the root directory of your project, and compile the Java files. You might need to compile all files at once or compile them package by package.

    ```bash
    javac -d . BookStore/*.java Books/*.java Books/Interfaces/*.java DeliveryService/*.java DeliveryService/Interfaces/*.java TransactionHandler/*.java TransactionHandler/Interfaces/*.java Utilities/*.java TestingModule/*.java
    ```
3.  **Run the tests:** Execute the `QuantumBookstoreTestRunner` class to see the application's features in action and verify their correctness.

    ```bash
    java TestingModule.QuantumBookstoreTestRunner
    ```

The output will be printed to the console, detailing the execution of each test case and its outcome.

## Running app
