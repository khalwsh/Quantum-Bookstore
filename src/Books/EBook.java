package Books;
import Books.Interfaces.CanEmailProduct;
import Books.Interfaces.CanSaleProduct;
import java.util.Date;

public class EBook extends Book implements CanEmailProduct, CanSaleProduct {
    private final String fileType;

    public EBook(String isbn, String title, double price, Date publishDate , String fileType) {
        super(isbn, title, price, publishDate );
        this.fileType = fileType;
    }

    @Override
    public boolean canEmailProduct() {
        return true;
    }

    @Override
    public boolean canSaleProduct() {
        return true;
    }

    public String getFileType() {
        return fileType;
    }
}
