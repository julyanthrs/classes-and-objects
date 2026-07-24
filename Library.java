import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            // Case-insensitive exact match
            if (book.getTitle().equalsIgnoreCase(title.trim())) {
                return book;
            }
        }
        return null;
    }
}
