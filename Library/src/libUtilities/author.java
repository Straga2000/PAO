package libUtilities;

import java.util.List;

public class author {

    private static Integer counter = 0;
    private Integer id;

    private String name;
    private List<book> bookList;

    public author(String name, List<book> bookList) {
        this.id = counter;
        this.name = name;
        this.bookList = bookList;

        counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<book> getBooks() {
        return bookList;
    }

    public void setBooks(List<book> books) {
        this.bookList = books;
    }
}
