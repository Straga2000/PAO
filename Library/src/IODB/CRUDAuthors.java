package IODB;

import libUtilities.author;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDAuthors extends CRUDTemplate {

    private Field[] fields;
    private String className;

    public CRUDAuthors(author newBook) {
        this.fields = newBook.getClass().getFields();
        this.className = newBook.getClass().getSimpleName();
    }

    public void createTable() {
        super.createTable(author.class);
    }

    public void deleteTable(){
        super.deleteTable(author.class);
    }

    public List<author> getTable() throws SQLException {
        List<ResultSet> resultSetList = super.getTable(author.class);
        List<author> authors = new ArrayList<>();

//        for(ResultSet resultSet : resultSetList)
//            authors.add(createObjectFromDB(resultSet));

        return authors;
    }

//    //"INSERT INTO person(name, age) VALUES ('Irina', 22)";
//    public void insertRow(author newAuthor) throws IllegalAccessException {
//
//        StringBuilder insertRowCommand = new StringBuilder("INSERT INTO ");
//
//        insertRowCommand.append(className).append("(id");
//
//        for(Field field : fields)
//        {
//            String name = field.getName();
//            if(!name.equals("id"))
//                insertRowCommand.append(", ").append(name);
//        }
//
//        insertRowCommand.append(") VALUES (");
//
//        for(int i = 0; i < fields.length; i++)
//        {
//            fields[i].setAccessible(true);
//            insertRowCommand.append("\'").append(fields[i].get(newAuthor).toString()).append("\'");
//
//            if (i != fields.length - 1)
//                insertRowCommand.append(", ");
//        }
//
//        insertRowCommand.append(")");
//
//        super.insertRow(insertRowCommand.toString());
//    }
//
//    //        UPDATE Customers
////        SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
////        WHERE CustomerID = 1;
//    public void updateRow(book newBook) throws IllegalAccessException {
//        StringBuilder updateRowCommand = new StringBuilder();
//        updateRowCommand.append("UPDATE ").append(className).append(" SET ");
//
//        for(Field field : fields)
//        {
//            String name = field.getName();
//            String value = field.get(newBook).toString();
//            if (!name.equals("id"))
//                updateRowCommand.append(name).append("= ").append('\'').append(value).append('\'');
//        }
//
//        updateRowCommand.append("WHERE id= ").append(newBook.getId().toString());
//        super.updateRow(updateRowCommand.toString());
//    }
//
//    ///Integer id, String title, String author, String publisher, String genre, Integer numberOfPages
//    public book createObjectFromDB(ResultSet resultSet) throws SQLException {
//
//        Integer id = Integer.parseInt(resultSet.getString(1));
//        String title = resultSet.getString(2);
//        String author = resultSet.getString(3);
//        String publisher = resultSet.getString(4);
//        String genre = resultSet.getString(5);
//        Integer numberOfPages = Integer.parseInt(resultSet.getString(6));
//
//        return new book(id, title, author, publisher, genre, numberOfPages);
//    }
}
