import IODB.CRUDBooks;
import libUtilities.book;

public class MainSQL {
    public static void main(String[] args) throws IllegalAccessException {
        book example = new book("exemplu 2", "tot eu", "editura mea", "", 3000);
        //book example1 = new book("exemplu 2", "tot eu", "editura mea", "", 20);

        example.setNumberOfPages(20);

        CRUDBooks crudBooks = new CRUDBooks();
        crudBooks.createTable();

        crudBooks.insertRow(example);
        crudBooks.updateRow(example);
    }
}
