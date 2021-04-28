package ioUtilities;

public class CSVIOSectionByAuthor {
    private CSVIOSectionByAuthor instance;

    private CSVIOSectionByAuthor()   {
        System.out.println("First time the constructor is being called.");
    }

    public CSVIOSectionByAuthor getInstance() {
        if(instance == null)    {
            instance = new CSVIOSectionByAuthor();
        }
        return instance;
    }
}
