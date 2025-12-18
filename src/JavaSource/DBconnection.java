package JavaSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/minu_dish_db";
    private static final String USER = "mini_dish_db_manager";
    private static final String PASSWORD = "new185231";
    private static Connection connexion = null;

    public DBconnection() {

    }

    public static Connection getConnection() {
        try {
            connexion = DriverManager.getConnection(URL, USER, PASSWORD);
            connexion.setAutoCommit(false);
            System.out.println("Connexion established");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connexion;
    }
}
