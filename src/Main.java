import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        System.out.println("-----    => BIENVENUE DANS APPLICATION DE GESTION D'INVENTAIRE <=     -----");

        try {
            // Recherche du service distant
            ProduitInt service = (ProduitInt) Naming.lookup("rmi://localhost:1099/EchoMsg");

            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            while (choice != 6) {
                // Affichage du menu
                System.out.println("   ----- MENU -----   ");

                System.out.println("0 - Ajouter un produit");
                System.out.println("1 - Récupérer la liste des produits");
                System.out.println("2 - Récupérer un produit par son ID");
                System.out.println("3 - Mettre à jour un produit");
                System.out.println("4 - Supprimer un produit par son ID");
                System.out.println("5 - Chercher un produit par son NOM");
                System.out.println("6 - Quitter");

                System.out.print("Veuillez choisir une option (0-6) : ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        // Ajouter un produit
                        System.out.println("Veuillez entrer les informations du nouveau produit :");

                        System.out.print("Nom du produit : ");
                        String name = scanner.next();

                        System.out.print("Catégorie du produit : ");
                        String category = scanner.next();

                        System.out.print("Prix du produit : ");
                        int price = scanner.nextInt();

                        System.out.print("Quantité du produit : ");
                        int quantity = scanner.nextInt();

                        // Appel de la méthode createProduct pour ajouter le produit
                        System.out.println(service.createProduct(name, category, price, quantity));
                        break;

                    case 1:
                        // Récupérer la liste des produits
                        System.out.println("Liste des produits :");
                        System.out.println(service.readAllProducts());
                        break;

                    case 2:
                        // Récupérer un produit par son ID
                        System.out.print("Entrez l'ID du produit : ");
                        int productId = scanner.nextInt();
                        System.out.println("Produit avec ID " + productId + ":");
                        System.out.println(service.readProductById(productId));
                        break;

                    case 3:
                        // Mettre à jour un produit
                        System.out.print("Entrez l'ID du produit à mettre à jour : ");
                        int updateId = scanner.nextInt();
                        System.out.print("Entrez le nouveau nom du produit : ");
                        String newName = scanner.next();
                        System.out.print("Entrez la nouvelle catégorie du produit : ");
                        String newCategory = scanner.next();
                        System.out.print("Entrez le nouveau prix du produit : ");
                        int newPrice = scanner.nextInt();
                        System.out.print("Entrez la nouvelle quantité du produit : ");
                        int newQuantity = scanner.nextInt();
                        System.out.println(service.updateProduct(updateId, newName, newCategory, newPrice, newQuantity));
                        break;

                    case 4:
                        // Supprimer un produit par son ID
                        System.out.print("Entrez l'ID du produit à supprimer : ");
                        int deleteId = scanner.nextInt();
                        System.out.println(service.deleteProductById(deleteId));
                        break;

                    case 5:
                        // Chercher un produit par son nom
                        System.out.print("Entrez le nom du produit à chercher : ");
                        String searchName = scanner.next(); // Lire le nom du produit
                        System.out.println(service.searchProductByName(searchName)); // Appeler la méthode pour chercher le produit par nom
                        break;

                    case 6:
                        // Quitter le programme
                        System.out.println("Au revoir !");
                        break;

                    default:
                        // Option invalide
                        System.out.println("Option invalide. Veuillez choisir une option entre 0 et 6.");
                        break;
                }
            }

            scanner.close(); // Fermeture du scanner
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Erreur de communication avec le serveur RMI : " + e.getMessage());
        }
    }
}