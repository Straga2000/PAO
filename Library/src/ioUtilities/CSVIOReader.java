package ioUtilities;

import libUtilities.reader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CSVIOReader extends CSVIO<reader>{
    private static CSVIOReader instance;

    private CSVIOReader(String filename) throws IOException {
        super(filename);
    }

    public static CSVIOReader getInstance(String filename) throws IOException {
        if(instance == null)    {
            instance = new CSVIOReader(filename);
        }
        return instance;
    }

    public ArrayList<reader> get() throws ParseException, IOException {

        ArrayList<reader> readerList = new ArrayList<>();
        Map<String, String> readerProps = super.readLine();

        while (readerProps.size() != 0)
        {
            reader newReader = new reader();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");


            ///name,max borrowed,start membership,finish membership,price,is VIP,pay date
            newReader.setFullName(readerProps.get("name"));

            newReader.setAll(dateFormat.parse(readerProps.get("start membership")),
                             dateFormat.parse(readerProps.get("finish membership")),
                             Integer.parseInt(readerProps.get("max borrowed")),
                             Integer.parseInt(readerProps.get("price")),
                             Boolean.parseBoolean(readerProps.get("is VIP")),
                             dateFormat.parse(readerProps.get("pay date")));

            readerList.add(newReader);
            readerProps = super.readLine();
        }

        return readerList;
    }

    public void put(ArrayList<reader> readerList) throws IOException {
        for(reader newBook : readerList)
            super.addLine(newBook.toCSVFormat());

        super.writeLines();
    }

}
