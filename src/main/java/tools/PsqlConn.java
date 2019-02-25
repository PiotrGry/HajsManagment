package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public final class PsqlConn {

    private DbCredentialsProvider data = new DbCredentialsProvider();
    private Map<String,String> credentials = data.getDbCredential();


    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = credentials.get("dbURL") ;
    private final String DB_USER = credentials.get("userName");
    private final String DB_PASS = credentials.get("dbPass");

    private static Connection connection;
    private static PsqlConn instance;

    private PsqlConn() {
        getConnectionToDataBase();
    }

    private void getConnectionToDataBase() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager
                    .getConnection(DB_URL,DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+" "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }


    public static PsqlConn getInstance(){
        if(instance == null) instance = new PsqlConn();

        return instance;
    }

    public  Connection getConnection() {
        return connection;
    }
}