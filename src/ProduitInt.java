import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProduitInt extends Remote{

    public String Calcule(Integer nbr1, Integer nbr2) throws RemoteException;

    // Méthode pour créer un nouveau produit
    public String createProduct(String nom, String categorie, int prix, int quantite) throws RemoteException;

    // Méthode pour lire les détails d'un produit par ID
    public String readProductById(int id) throws RemoteException;

    // Méthode pour lire tous les produits
    public String readAllProducts() throws RemoteException;

    // Méthode pour mettre à jour un produit par ID
    public String updateProduct(int id, String nom, String categorie, int prix, int quantite) throws RemoteException;

    // Méthode pour supprimer un produit par ID
    public String deleteProductById(int id) throws RemoteException;

    // Méthode pour chercher un produit par ID
    public String searchProductByName(String nom) throws RemoteException;


}
