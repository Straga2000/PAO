package ioUtilities;

import libUtilities.book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CSVIOBook extends CSVIO{
    private static CSVIOBook instance;

    private CSVIOBook(String filename) throws IOException {
        super(filename);
    }

    public static CSVIOBook getInstance(String filename) throws IOException {
        if(instance == null)    {
            instance = new CSVIOBook(filename);
        }
        return instance;
    }

    public ArrayList<book> getBooks() {

        ArrayList<book> bookList = new ArrayList<>();
        Map<String, String> bookProps = super.readLine();

        while (bookProps.size() != 0)
        {
            book newBook = new book();
            newBook.setTitle(bookProps.get("book name"));
            newBook.setAuthor(bookProps.get("author"));
            newBook.setGenre(bookProps.get("genre"));
            newBook.setNumberOfPages(Integer.parseInt(bookProps.get("pages")));
            newBook.setPublisher(bookProps.get("publisher"));

            bookList.add(newBook);
            bookProps = super.readLine();
        }

        return bookList;
    }

    public void putBooks(ArrayList<book> bookList) throws IOException {
        for(book newBook : bookList)
        {
            super.addLine(newBook.toCSVFormat());
        }

        super.writeLines();
    }
}
