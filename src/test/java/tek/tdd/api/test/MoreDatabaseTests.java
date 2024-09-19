package tek.tdd.api.test;

import org.openqa.selenium.devtools.v85.database.Database;
import org.testng.annotations.Test;
import tek.tdd.api.models.AccountResponse;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DatabaseUtility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreDatabaseTests extends ApiTestsBase {

    @Test
    public void convertResultSetToMap() throws SQLException {
        DatabaseUtility dbUtility = new DatabaseUtility();
        String query = "select * from plan_code where is_plan_expired = false ";

        ResultSet resultSet  = dbUtility.executeQuery(query);

        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map<String, Object>> data = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for(int col = 1; col <= metaData.getColumnCount(); col++) {
                row.put(metaData.getColumnName(col), resultSet.getObject(col));
            }
            data.add(row);
        }

        System.out.println(data);
    }

    @Test
    public void convertResultSetToObject() throws SQLException {
        DatabaseUtility dbUtility = new DatabaseUtility();
        String query = "select id, email, first_name from primary_person order by id desc limit 5; ";

        ResultSet resultSet  = dbUtility.executeQuery(query);

        ResultSetMetaData metaData = resultSet.getMetaData();

        List<AccountResponse> data = new ArrayList<>();

        while (resultSet.next()) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setId(resultSet.getInt("id"));
            accountResponse.setEmail(resultSet.getString("email"));
            accountResponse.setFirstName(resultSet.getString("first_name"));

            data.add(accountResponse);
        }

        System.out.println(data);
    }
}