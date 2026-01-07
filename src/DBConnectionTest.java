package JavaSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest {

    public static void main(String[] args) {

        System.out.println("=== TEST CONNEXION BASE DE DONNÉES ===");

        Connection connection = DBconnection.getDBConnection();

        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    System.out.println("Connexion ACTIVE et valide");
                } else {
                    System.out.println("Connexion fermée");
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la vérification de la connexion");
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    System.out.println("Connexion fermée proprement");
                } catch (SQLException e) {
                    System.out.println("Erreur lors de la fermeture");
                }
            }
        } else {
            System.out.println("Connexion échouée (connection == null)");
        }

        System.out.println("=== FIN DU TEST ===");
    }
}
