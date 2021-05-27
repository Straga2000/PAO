package IODB;

import libUtilities.book;

import javax.swing.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class CRUDBooks extends CRUDTemplate {

    private Field[] fields;
    private String className;

    public CRUDBooks() {
        this.fields = book.class.getDeclaredFields();
        this.className = book.class.getSimpleName();

        for (Field field : this.fields)
            System.out.println(field.getName());
        System.out.println(this.fields.length);
        System.out.println(this.className);
    }

    public void createTable() {
        super.createTable(book.class);
    }

    public void deleteTable(){
        super.deleteTable(book.class);
    }

    public List<book> getTable() throws SQLException {
        List<ResultSet> resultSetList = super.getTable(book.class);
        List<book> books = new ArrayList<>();

        for(ResultSet resultSet : resultSetList)
            books.add(createObjectFromDB(resultSet));

        return books;
    }

    //"INSERT INTO person(name, age) VALUES ('Irina', 22)";
    public void insertRow(book newBook) throws IllegalAccessException {

        StringBuilder insertRowCommand = new StringBuilder("INSERT INTO ");
        StringBuilder values = new StringBuilder(" VALUES(null, ");

        insertRowCommand.append(className).append("(");

        for(int i = 0; i < fields.length; i++)
        {
            fields[i].setAccessible(true);

            String name = fields[i].getName();
            if(!name.equals("counter") && !name.equals("id"))
            {
                insertRowCommand.append(name);
                values.append("\'").append(fields[i].get(newBook).toString()).append("\'");

                if (i != fields.length - 1) {
                    values.append(", ");
                    insertRowCommand.append(", ");
                }
            }
        }

        values.append(") ");
        insertRowCommand.append(")");

        insertRowCommand.append(values);
        System.out.println(insertRowCommand.toString());
        super.insertRow(insertRowCommand.toString());
    }

    //        UPDATE Customers
//        SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
//        WHERE CustomerID = 1;
    public void updateRow(book newBook) throws IllegalAccessException {
        StringBuilder updateRowCommand = new StringBuilder();
        updateRowCommand.append("UPDATE ").append(className).append(" SET ");

        for(Field field : fields)
        {
            String name = field.getName();
            String value = field.get(newBook).toString();
            if (!name.equals("id"))
                updateRowCommand.append(name).append("= ").append('\'').append(value).append('\'');
        }

        updateRowCommand.append("WHERE id= ").append(newBook.getId().toString());
        super.updateRow(updateRowCommand.toString());
    }

    ///Integer id, String title, String author, String publisher, String genre, Integer numberOfPages
    public book createObjectFromDB(ResultSet resultSet) throws SQLException {

        Integer id = Integer.parseInt(resultSet.getString(1));
        String title = resultSet.getString(2);
        String author = resultSet.getString(3);
        String publisher = resultSet.getString(4);
        String genre = resultSet.getString(5);
        Integer numberOfPages = Integer.parseInt(resultSet.getString(6));

        return new book(id, title, author, publisher, genre, numberOfPages);
    }
}
