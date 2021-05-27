package IODB;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CRUDTemplate {

    public CRUDTemplate() {}

    public void createTable(Class cls)
    {
        StringBuilder createCommand = new StringBuilder("CREATE TABLE IF NOT EXISTS " + cls.getSimpleName() + "(id int PRIMARY KEY");

        Field[] fields = cls.getDeclaredFields();

        for(Field field : fields)
        {
            String name = field.getName();
            if(!name.equals("id"))
                createCommand.append(", ").append(name).append(" varchar(100)");
        }

        createCommand.append(")");

        Connection connection = DBConfig.getDBConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createCommand.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteTable(Class cls)
    {
        StringBuilder deleteCommand = new StringBuilder("DROP TABLE ").append(cls.getSimpleName());
        Connection connection = DBConfig.getDBConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteCommand.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<ResultSet> getTable(Class cls)
    {
        String selectCommand = "SELECT * FROM " + cls.getSimpleName();
        Connection connection = DBConfig.getDBConnection();
        List<ResultSet> result = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectCommand);

            while (resultSet.next())
                result.add(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public void insertRow(String insertRowSQL)
    {
        Connection connection = DBConfig.getDBConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertRowSQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateRow(String updateRowSQL)
    {
        Connection connection = DBConfig.getDBConnection();

        try
        {
            Statement statement = connection.createStatement();
            statement.executeQuery(updateRowSQL);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteRow(String deleteRowSQL)
    {
        Connection connection = DBConfig.getDBConnection();

        try
        {
            Statement statement = connection.createStatement();
            statement.executeQuery(deleteRowSQL);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
