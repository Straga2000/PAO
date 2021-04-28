import ioUtilities.CSVIOBook;
import libUtilities.book;

import java.io.IOException;
import java.util.ArrayList;

public class TestCSVIO {
    public static void main(String[] args) throws IOException {
        CSVIOBook csvBook = CSVIOBook.getInstance("Database/booksDB.csv");
        ArrayList<book> bookList = csvBook.getBooks();

        for(book newBook : bookList)
            System.out.println(newBook.toString());

        //public book(String title, String author, String publisher, String genre, Integer numberOfPages)
        book example = new book("exemplu 2", "tot eu", "editura mea", "", 3000);
        bookList.add(example);

        csvBook.putBooks(bookList);
    }
}
