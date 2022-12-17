package me.sanchez.logging.Product;
import java.util.ArrayList;
public class ProductRepository {
    final String logprefix = "[ProductRepository] - ";

    private ArrayList<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
    }

    public void addProduct(Product u) throws Exception {
        if (products.contains(u)) {
            throw new Exception("Already known product");
        }
        products.add(u);
    }

    public Product getProductById(Long id) throws Exception {
        for (Product u : products) {
            if (u.getId() == id) {
                return u;
            }
        }
        throw new Exception("Product not found");
    }

    public ArrayList<Product> getProduct() {
        return products;
    }

    public void removeProduct(Long id) throws Exception {
        products.remove(getProductById(id));
    }

    public Product editProduct(Long id, Product u) throws Exception {
        getProductById(id).setNom(u.getNom());
        getProductById(id).setPrix(u.getPrix());
        getProductById(id).setExpDate(u.getExpDate());
        return u;
    }
}