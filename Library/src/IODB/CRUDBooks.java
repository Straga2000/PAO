package IODB;

import libUtilities.book;

import javax.swing.table.TableRowSorter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CRUDBooks extends CRUDTemplate {


    public void insertRow(book newBook) throws IllegalAccessException {
        Class usedClass = book.class;

//        "INSERT INTO person(name, age) VALUES ('Irina', 22)";
        StringBuilder insertRowCommand = new StringBuilder("INSERT INTO ");

        insertRowCommand.append(usedClass.getSimpleName()).append("(id");

        for(Map.Entry<String, String> entry : toSQLFormat(usedClass).entrySet())
        {
            String mapKey = entry.getKey();

            if(!mapKey.equals("id"))
                insertRowCommand.append(", ").append(mapKey);
        }

        insertRowCommand.append(")").append(" VALUES (");

        Field []fieldArray = usedClass.getFields();
        for(int i = 0; i < fieldArray.length; i++)
        {
            fieldArray[i].setAccessible(true);
            insertRowCommand.append(fieldArray[i].get(newBook).toString());

            if (i != fieldArray.length - 1)
                insertRowCommand.append(", ");
        }

        insertRowCommand.append(")");

        super.insertRow(insertRowCommand.toString());
    }

    public void updateRow(book newBook)
    {
        Class usedClass = book.class;
//        UPDATE Customers
//        SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
//        WHERE CustomerID = 1;

    }

}
