package me.sanchez.logging;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import java.util.List;
import me.sanchez.logging.Product.Product;
import me.sanchez.logging.Product.ProductRepository;
import me.sanchez.logging.User.User;
import me.sanchez.logging.User.UserRepository;
public class Applicationrepository {
    private UserRepository userRepository = new UserRepository();

    private User user = null;

    private ProductRepository productRepository = new ProductRepository();

    public void connexion(String email, String password) {
        this.user = userRepository.login(email, password);
    }

    public void register(User user) throws Exception {
        userRepository.addUser(user);
    }

    public List<Product> getProducts() throws Exception {

        return productRepository.getProduct();
    }

    public Product getProductById(long id) throws Exception {
        return productRepository.getProductById(id);
    }

    public void addProduct(Product product) throws Exception {
        productRepository.addProduct(product);
    }

    public void removeProduct(long id) throws Exception {
        productRepository.removeProduct(id);
    }

    public void editProduct(Long id, Product product) throws Exception {
        productRepository.editProduct(id, product);
    }

    public User getUser() {
        return user;
    }

    public void deconnexion() {
        this.user = null;
    }
}