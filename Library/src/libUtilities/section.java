package libUtilities;

import java.util.ArrayList;
import java.util.List;

public class section {

    private static Integer counter = 0;

    private Integer id;
    private String name;
    private List<book> bookList = new ArrayList<>();

    public section(String name, List<book> bookList) {
        this.id = counter;
        this.name = name;
        this.bookList = bookList;

        counter++;
    }

    public section(Integer id, String name) {
        this.id = counter;
        this.name = name;

        counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<book> getBookList() {
        return bookList;
    }

    public void setBookList(List<book> bookList) {
        this.bookList = bookList;
    }

    public void addBook(book addedBook)
    {
        bookList.add(addedBook);
    }
}
