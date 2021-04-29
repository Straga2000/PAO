package ioUtilities;

import libUtilities.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class CSVIOAction extends CSVIO<action> {
    private static CSVIOAction instance;

    private CSVIOAction(String filename) throws IOException {
        super(filename);
    }

    public static CSVIOAction getInstance(String filename) throws IOException {
        if(instance == null)    {
            instance = new CSVIOAction(filename);
        }
        return instance;
    }

    public ArrayList<action> get() throws ParseException {

        ArrayList<action> actionList = new ArrayList<>();
        Map<String, String> actionProps = super.readLine();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        while (actionProps.size() != 0)
        {

            action newAction= new action();
            newAction.setOperation(actionProps.get("operation"));
            newAction.setTimestamp(dateFormat.parse(actionProps.get("timestamp")));

            actionList.add(newAction);
            actionProps = super.readLine();
        }

        return actionList;
    }

    public void put(ArrayList<action> actionList) throws IOException {

        for(action newAction : actionList)
            super.addLine(newAction.toCSVFormat());

        super.writeLines();
    }
}
