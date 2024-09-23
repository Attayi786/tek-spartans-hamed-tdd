package tek.tdd.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tek.tdd.base.BaseSetup;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtility extends BaseSetup {
    private static final Logger LOGGER = LogManager.getLogger(DatabaseUtility.class);

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

    public List<Map<String, Object>> getResultFromQuery(String query) {
        LOGGER.debug("Executing Query {}" , query);
        String url = getProperty("db.url");
        String username = getProperty("db.username");
        String password = getProperty("db.password");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<Map<String, Object>> data = new ArrayList<>();
            while(resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for(int col = 1; col <= metaData.getColumnCount(); col++) {
                    row.put(metaData.getColumnName(col), resultSet.getObject(col));
                }
                data.add(row);
            }
            LOGGER.debug("Query result {}", data);
            return data;
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}