package ioUtilities;

import libUtilities.author;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CSVIOAuthor extends CSVIO<author> {

    private static CSVIOAuthor instance;

    private CSVIOAuthor(String filename) throws IOException {
        super(filename);
    }

    public static CSVIOAuthor getInstance(String filename) throws IOException {
        if(instance == null)    {
            instance = new CSVIOAuthor(filename);
        }
        return instance;
    }

    public ArrayList<author> get() {

        ArrayList<author> authorList = new ArrayList<>();
        Map<String, String> authorProps = super.readLine();

        while (authorProps.size() != 0)
        {
            author newAuthor = new author();

            newAuthor.setName(authorProps.get("name"));
            newAuthor.setNumberOfBooks(Integer.parseInt(authorProps.get("number of books")));

            authorList.add(newAuthor);
            authorProps = super.readLine();
        }

        return authorList;
    }

    public void put(ArrayList<author> authorList) throws IOException {
        for(author newAuthor : authorList)
        {
            super.addLine(newAuthor.toCSVFormat());
        }

        super.writeLines();
    }
}
