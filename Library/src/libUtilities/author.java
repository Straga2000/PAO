package libUtilities;

import java.util.List;

public class author {

    private static Integer counter = 0;
    private Integer id;

    private String name;
    private List<book> bookList;
    private Integer numberOfBooks;

    public author(String name, List<book> bookList) {
        this.id = counter;
        this.name = name;

        if(bookList.size() != 0)
            this.bookList = bookList;

        counter++;
    }

    public author() {}

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

    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Integer numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public String toCSVFormat()
    {
        ///name,number of books
        return "" + name + "," + numberOfBooks + "\n";
    }
}
