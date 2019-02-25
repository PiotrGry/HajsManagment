import tools.PsqlConn;

import java.sql.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {

        PsqlConn dbConn = PsqlConn.getInstance();
        Connection connection = dbConn.getConnection();


        PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1+1 AS result");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet.getAsciiStream("result"));
    }
}
