package JavaSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
        private static final String URL = "jdbc:postgresql://localhost:5432/mini_dish_db";
        private static final String USER = "mini_dish_db_manager";
        private static final String PASSWORD = "new185231";

    public static Connection getDBConnection() {
        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie à PostgreSQL!");

        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL non trouvé");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion SQL");
            e.printStackTrace();
        }
        return connection;
    }
}
