package Books;
import java.util.Date;

public class Book extends Product {
    private final String isbn;
    private final String title;
    private final double price;
    private final Date publishDate;
    private final String author;

    public Book(String isbn, String title, double price, Date publishDate , String author) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
        this.author = author;
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public Date getDate() {
        return publishDate;
    }

    @Override
    public String getId() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }
}
