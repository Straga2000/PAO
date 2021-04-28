package ioUtilities;

public class CSVIOReader {
    private CSVIOReader instance;

    private CSVIOReader()   {
        System.out.println("First time the constructor is being called.");
    }

    public CSVIOReader getInstance() {
        if(instance == null)    {
            instance = new CSVIOReader();
        }
        return instance;
    }
}
