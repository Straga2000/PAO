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

    private Map<String, String> translateToSQL = new HashMap<>();

    public CRUDTemplate() {
        this.translateToSQL.put("class java.lang.Integer", "int");
        this.translateToSQL.put("class java.lang.String", "varchar(255)");
        this.translateToSQL.put("interface java.util.List", "ARRAY");
    }


    public Map<String, String> toSQLFormat(Class cls)
    {
        Map<String, String> bindingMap = new HashMap<>();

        Field[] fieldsName = cls.getFields();

        for (Field field : fieldsName)
        {
            bindingMap.put(field.getName(), translateToSQL.get(field.getType().toString()));
        }

        return bindingMap;
    }

    public void createTable(Class cls)
    {
        StringBuilder createCommand = new StringBuilder("CREATE TABLE IF NOT EXISTS " + cls.getSimpleName() + "(id int PRIMARY KEY");

        for(Map.Entry<String, String> entry : toSQLFormat(cls).entrySet())
        {
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();

            if(!mapKey.equals("id"))
                createCommand.append(", ").append(mapKey).append(" ").append(mapValue);
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
