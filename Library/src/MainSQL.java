import IODB.CRUDBooks;
import libUtilities.book;

public class MainSQL {
    public static void main(String[] args) throws IllegalAccessException {
        book example = new book("exemplu 2", "tot eu", "editura mea", "", 3000);

        CRUDBooks crudBooks = new CRUDBooks();
        crudBooks.createTable();

        crudBooks.insertRow(example);
    }
}
