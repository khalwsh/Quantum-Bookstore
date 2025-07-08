package Books;
import Books.Interfaces.CanSaleProduct;
import Books.Interfaces.CanShipProduct;
import java.util.Date;

public class PaperBook extends Book implements CanShipProduct, CanSaleProduct {
    private int stock;
    public PaperBook(String isbn, String title, double price, Date publishDate, int stock) {
        super(isbn, title, price, publishDate);
        this.stock = stock;
    }

    @Override
    public boolean canSaleProduct() {
        return stock > 0;
    }

    @Override
    public boolean canShipProduct() {
        return true;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        }else
            throw new IllegalArgumentException("The requested quantity is greater than the stock you request " + quantity + " and in stock only " + stock);
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }
}
