package gr.aueb.cf.schoolapp.service.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static BasicDataSource ds = new BasicDataSource();
    private static Connection connection;

    static {
        ds.setUrl("jdbc:mysql://localhost:3306/schooldb6?serverTimeZone=UTC");
        ds.setUsername("userdb6");
        ds.setPassword(System.getenv("PASS_DB6"));
        ds.setInitialSize(10);
        ds.setMaxTotal(50);
        ds.setMinIdle(10);
        ds.setMaxIdle(10);
    }

    /**
     * No instances of this class should be available
     */
    private DBUtil() {}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = ds.getConnection();
            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
