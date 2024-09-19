package tek.tdd.utility;

import tek.tdd.base.BaseSetup;

import java.sql.*;

public class DatabaseUtility extends BaseSetup {

    public ResultSet executeQuery(String query) {
        try {
            String url = getProperty("db.url");
            String username = getProperty("db.username");
            String password = getProperty("db.password");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            return  statement.executeQuery(query);
        }catch (SQLException ex) {
            throw new RuntimeException( ex);
        }
    }
}