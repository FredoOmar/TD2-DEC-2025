package JavaSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class DBconnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/minu_dish_db";
    private static final String USER = "mini_dish_db_manager";
    private static final String PASSWORD = "new185231";
    private static Connection connexion = null;

    public DBconnection() {

    }

    public static Connection getConnection() throws ClassNotFoundException {
        if(connexion == null){
            try {

                connexion = DriverManager.getConnection(URL, USER, PASSWORD);
                connexion.setAutoCommit(false);
                System.out.println("Connexion established");

            } catch (SQLException e) {
                System.out.println("Connection failed" );
            }
        }


        return connexion;
    }
    public static void closeConnexion() throws SQLException {
        if(connexion != null){
            connexion.close();
            System.out.println("Connexion closed");
        }
    }

}
