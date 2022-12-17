package me.sanchez.logging;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import java.util.List;
import me.sanchez.logging.Product.Product;
import me.sanchez.logging.Product.ProductRepository;
import me.sanchez.logging.User.User;
import me.sanchez.logging.User.UserRepository;
public class Applicationrepository {
    private static Logger price = com.savoirtech.logging.slf4j.json.LoggerFactory.getLogger("Price");

    private static Logger read = com.savoirtech.logging.slf4j.json.LoggerFactory.getLogger("Read");

    private static Logger write = com.savoirtech.logging.slf4j.json.LoggerFactory.getLogger("Write");

    private UserRepository userRepository = new UserRepository();

    private User user = null;

    private ProductRepository productRepository = new ProductRepository();

    public void connexion(String email, String password) {
        if(this.user != null){read.trace().message("connexion")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
        .log(); 
        };
        this.user = userRepository.login(email, password);
    }

    public void register(User user) throws Exception {
        if(this.user != null){write.trace().message("register")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
        .log(); 
        };
        userRepository.addUser(user);
    }

    public List<Product> getProducts() throws Exception {
        if(this.user != null){read.trace().message("getProducts")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
        .log(); 
        };
        return productRepository.getProduct();
    }

    public Product getProductById(long id) throws Exception {
        if(this.user != null){read.trace().message("getProductById")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
        .log(); 
        }if(this.user != null && productRepository.getProductById(id) != null){price.trace().message("getProductById")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
         .field("Product", productRepository.getProductById(id).getNom())
         .field("ProductID", productRepository.getProductById(id).getId())
         .field("Price", productRepository.getProductById(id).getPrix())
        .log(); 
        };
        return productRepository.getProductById(id);
    }

    public void addProduct(Product product) throws Exception {
        if(this.user != null){write.trace().message("addProduct")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
        .log(); 
        };
        productRepository.addProduct(product);
    }

    public void removeProduct(long id) throws Exception {
        if(this.user != null){write.trace().message("removeProduct")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
        .log(); 
        };
        productRepository.removeProduct(id);
    }

    public void editProduct(Long id, Product product) throws Exception {
        if(this.user != null){write.trace().message("editProduct")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
        .log(); 
        };
        productRepository.editProduct(id, product);
    }

    public User getUser() {
        if(this.user != null){read.trace().message("getUser")
         .field("User",this.user.getNom())
         .field("UserID", this.user.getId())
        .log(); 
        };
        return user;
    }

    public void deconnexion() {
        ;
        this.user = null;
    }
}