package ioUtilities;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CSVIO {
    private Map<String, String> line = new HashMap<>();
    private ArrayList<String> header = new ArrayList<>();
    private ArrayList<String> addLines = new ArrayList<>();

    private String filename, headerString;
    private BufferedReader buff;

    public CSVIO(String filename) throws IOException {

        FileReader fileReader = new FileReader(filename);

        this.filename = filename;
        this.buff = new BufferedReader(fileReader);

        readHeader();
    }

    public void readHeader() throws IOException {
        headerString = buff.readLine();
        for(String prop : headerString.split(","))
        {
            line.put(prop, "");
            header.add(prop);
        }
    }

    public Map<String, String> readLine() {

        try{
            String newLine = buff.readLine();

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

    public void addLine(String props)
    {
        addLines.add(props);
    }

    public void writeLines() throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));

        bufferedWriter.write(headerString);
        for(String newLine : addLines)
            bufferedWriter.write(newLine);
    }
}
