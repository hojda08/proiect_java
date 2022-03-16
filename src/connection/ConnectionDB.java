package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/terenuridb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private ConnectionDB() {}

    public static Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void close(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (Exception e) {};
        }
    }

    public static void close(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (Exception e) {};
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (Exception e) {};
        }
    }
}
