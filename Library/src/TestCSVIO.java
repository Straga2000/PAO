import ioUtilities.CSVIOBook;

import java.io.IOException;

public class TestCSVIO {
    public static void main(String[] args) throws IOException {
        CSVIOBook readerBook = CSVIOBook.getInstance("Database/booksDB.csv");
        System.out.println(readerBook.getBooks().toString());
    }
}
