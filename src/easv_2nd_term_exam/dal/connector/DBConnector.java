package easv_2nd_term_exam.dal.connector;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    SQLServerDataSource dataSource;

    public DBConnector() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/easv_2nd_term_exam/dal/connector/config.properties"));

            dataSource = new SQLServerDataSource();
            dataSource.setServerName(props.getProperty("db.server"));
            dataSource.setDatabaseName(props.getProperty("db.name"));
            dataSource.setUser(props.getProperty("db.user"));
            dataSource.setPassword(props.getProperty("db.password"));
            dataSource.setPortNumber(Integer.parseInt(props.getProperty("db.port")));
            dataSource.setTrustServerCertificate(true);

        } catch (IOException e) {
            throw new RuntimeException("Could not read configuration file", e);
        }
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) {
        DBConnector dbConnector = new DBConnector();
        try (Connection connection = dbConnector.getConnection()) {
            if (!connection.isClosed()) {
                System.out.println("Is it opened..?\n" + !connection.isClosed());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
