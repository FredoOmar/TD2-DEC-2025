import JavaSource.DBconnection;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBconnection.getConnection();
            System.out.println("🎉 CONNEXION RÉUSSIE !");
            System.out.println("Base de données connectée avec succès.");
            DBconnection.closeConnexion();
        } catch (Exception e) {
            System.out.println("💥 ÉCHEC DE CONNEXION !");
            System.out.println("Erreur: " + e.getMessage());
        }

    }
}