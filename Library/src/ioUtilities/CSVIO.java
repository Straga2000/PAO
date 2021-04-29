package ioUtilities;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class CSVIO<T> {
    private Map<String, String> line = new HashMap<>();
    private ArrayList<String> header = new ArrayList<>();
    private ArrayList<String> addLines = new ArrayList<>();

    private String filename, headerString;
    private BufferedReader buff;

    CSVIO(String filename) throws IOException {

        this.filename = filename;

        readHeader();
    }

    private void readHeader() throws IOException {
        this.buff = new BufferedReader(new FileReader(filename));

        headerString = buff.readLine().replace("\n", "");
        for(String prop : headerString.split(","))
        {
            line.put(prop, "");
            header.add(prop);
        }
        //System.out.println(headerString);
    }

    Map<String, String> readLine() {

        try{
            String newLine = buff.readLine().replace("\n","");

            String[] propList = newLine.split(",");
            for(int i = 0; i < propList.length; i++)
                line.put(header.get(i), propList[i]);
        }
        catch (Exception e)
        {
            line = new HashMap<>();
            return line;
        }

        return line;
    }

    void addLine(String props)
    {
        addLines.add(props);
    }

    void writeLines() throws IOException {

        buff.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
        //System.out.println(headerString);
        bufferedWriter.write(headerString + "\n");
        for(String newLine : addLines)
            bufferedWriter.write(newLine);
        bufferedWriter.close();
    }

    public abstract ArrayList<T> get() throws ParseException, IOException;
    public abstract void put(ArrayList<T> list) throws IOException;
}
