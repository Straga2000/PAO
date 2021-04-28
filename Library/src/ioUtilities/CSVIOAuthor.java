package ioUtilities;

public class CSVIOAuthor {

    private CSVIOAuthor instance;
    private CSVIO io;

    private CSVIOAuthor()   {

    }

    public CSVIOAuthor getInstance() {
        if(instance == null)    {
            instance = new CSVIOAuthor();
        }
        return instance;
    }
}
