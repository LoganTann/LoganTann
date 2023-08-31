package fr.efrei;

import java.sql.*;

public class Exo1 {
    private Connection conn;

    public void connexion() throws SQLException {
        // Non nécessaire : Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/BDCOMMANDES";
        String username = "root";
        String password = "";
        this.conn = DriverManager.getConnection(url, username, password);
        this.loadDB();
    }

    public void closeConnexion() throws SQLException {
        this.conn.close();
    }

    public void listerTable() throws SQLException {
        System.out.println(" ------------------------ ");
        System.out.println("Lister la table Fournisseurs");
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Fournisseurs");
        while (rs.next()) {
            int fno = rs.getInt("fno");
            String name = rs.getString("nom");
            System.out.println(fno + " " + name);
        }
        rs.close();
        stmt.close();
    }

    public void afficherProduitsNonCommences() throws SQLException {
        System.out.println(" ------------------------ ");
        System.out.println("Afficher les produits qui ne sont pas commencés");
        String sql = "SELECT Pno, Design, Prix " +
                "FROM Produits " +
                "WHERE Pno NOT IN ( " +
                "SELECT Pno FROM Commandes" +
                ")";
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int pno = rs.getInt("pno");
            String design = rs.getString("design");
            int prix = rs.getInt("prix");
            System.out.printf("%d\t %8s\t %d %n", pno, design, prix);
        }
        rs.close();
        stmt.close();
    }

    public void ajouterProduit() throws SQLException {
        System.out.println(" ------------------------ ");
        System.out.println("Ajouter le produit (111,\"chaise\",100,4,\"noir\")");
        String sql = "INSERT INTO Produits (Pno,Design,Prix,Poids,Couleur)"
                + "VALUES (111,\"chaise\",100,4,\"noir\")";
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public void ajouterCommande() throws SQLException {
        System.out.println(" ------------------------ ");
        System.out.println("Ajouter la commande de ce produit fournit par le fournisseur 10");
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate("INSERT INTO Commandes (Cno,Fno,Pno,Qute) VALUES (1111,10,111,1)");
        stmt.close();
    }

    public void modifierProduit() throws SQLException {
        System.out.println(" ------------------------ ");
        System.out.println(
                "Modifier le prix du produit 101 avec la valeur 1111 et le poids du produit 102 avec la valeur 11");
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate("UPDATE Produits SET Prix = 1111 WHERE Pno = 101;");
        stmt.executeUpdate("UPDATE Produits SET Poids = 11 WHERE Pno = 102;");
        stmt.close();
    }

    public void supprimerCommande() throws SQLException {
        System.out.println(" ------------------------ ");
        System.out.println("Supprimer la commande 1029.");
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate("DELETE FROM Commandes WHERE Cno = 1029");
        stmt.close();
    }

    private void loadDB() throws SQLException {
        String setup[] = {
                "DROP TABLE IF EXISTS Commandes;",
                "DROP TABLE IF EXISTS Fournisseurs;",
                "DROP TABLE IF EXISTS Produits;",
                "CREATE TABLE Fournisseurs ("
                        + "fno Numeric(6) NOT NULL primary key ,"
                        + "nom VARCHAR(25) NOT NULL ,"
                        + "adresse VARCHAR(25) ,"
                        + "ville VARCHAR(25) NOT NULL"
                        + ");",
                "CREATE TABLE Produits ("
                        + "pno Numeric(6) NOT NULL primary key ,"
                        + "design VARCHAR(25) NOT NULL ,"
                        + "prix Numeric(6,2) NOT NULL ,"
                        + "poids Numeric(6,2) NOT NULL ,"
                        + "couleur VARCHAR(25) NOT NULL"
                        + ");",
                "CREATE TABLE Commandes ("
                        + "cno Numeric(6) NOT NULL primary key ,"
                        + "fno Numeric(6) NOT NULL ,"
                        + "pno Numeric(6) NOT NULL,"
                        + "qute INT(5) NOT NULL ,"
                        + "constraint FK1 foreign key (fno) references Fournisseurs(fno),"
                        + "constraint FK2 foreign key (pno) references Produits(pno)"
                        + ");",
                "INSERT INTO Fournisseurs (Fno,Nom,Adresse,Ville) VALUES "
                        + "(10,\"Dupont\",NULL,\"Lille\"),(15,\"Durand\",NULL,\"Lille\"),"
                        + "(17,\"Lefebvre\",NULL,\"Lille\"),(12,\"Jacquet\",NULL,\"Lyon\"),"
                        + "(14,\"Martin\",NULL,\"Nice\"),(13,\"Durand\",NULL,\"Lyon\"),"
                        + "(11,\"Martin\",NULL,\"Amiens\"),(19,\"Maurice\",NULL,\"Paris\"),"
                        + "(16,\"Dupont\",NULL,\"Paris\");",
                "INSERT INTO Produits (Pno,Design,Prix,Poids,Couleur) VALUES "
                        + "(102,\"fauteuil\",1500,9,\"rouge\"),(103,\"bureau\",3500,30,\"vert\"),"
                        + "(101,\"fauteuil\",2000,7,\"gris\"),(105,\"armoire\", 2500,35,\"rouge\"),"
                        + "(104,\"bureau\",4000,40,\"gris\"),(107,\"caisson\",1000,12,\"jaune\"),"
                        + "(106,\"caisson\",1000,12,\"gris\"),(108,\"classeur\",1500,20,\"bleu\");",
                "INSERT INTO Commandes VALUES "
                        + "(1001,17,103,10),(1003,15,103,2),(1005,17,102,1),"
                        + "(1007,15,108,1),(1011,19,107,12),(1013,13,107,5),"
                        + "(1017,19,105,3),(1019,14,103,10),(1023,10,102,8),"
                        + "(1029,17,108,15);"
        };
        Statement stmt = this.conn.createStatement();
        for (String line : setup) {
            System.out.println(line);
            stmt.execute(line);
        }
        stmt.close();
    }
}
