package JavaSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    public static Connection getDBConnection() throws SQLException {

        String url = System.getenv("JDBC_URL");
        String user = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new RuntimeException("Variables d'environnement manquantes");
        }

        return DriverManager.getConnection(url, user, password);
    }
}
