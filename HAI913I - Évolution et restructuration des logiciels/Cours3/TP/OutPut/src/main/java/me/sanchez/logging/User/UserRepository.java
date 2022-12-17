package me.sanchez.logging.User;
import java.util.ArrayList;
import java.util.Objects;
public class UserRepository {
    // final Logger logger =  LoggerFactory.getLogger(UserRepository.class);
    final String logprefix = "[UserRepository] - ";

    private ArrayList<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    public User login(String email, String psw) {
        for (User user : getUsers()) {
            if (email.equals(user.getEmail()) && psw.equals(user.getPassword())) {
                // logger.info(logprefix + user.getId() +" logged in");
                return user;
            }
        }
        return null;
    }

    public void addUser(User u) throws Exception {
        if (users.contains(u)) {
            throw new Exception("Already known user");
        }
        users.add(u);
        // logger.info(logprefix  + "User ID = " + u.getId() + " added");
    }

    public User getUserById(Long id) {
        for (User u : users) {
            if (Objects.equals(u.getId(), id)) {
                // logger.info(logprefix + "User ID = " + u.getId() + " found");
                return u;
            }
        }
        // logger.info(logprefix  + "User ID = " + id + " not  found");
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void removeUser(Long id) {
        // logger.info(logprefix  + "User ID = " + id + " deleted");
        users.removeIf(( u) -> Objects.equals(u.getId(), id));
    }

    public void editUser(Long id, User u) throws Exception {
        // logger.info(logprefix  + "User ID = " + id + " edited");
        removeUser(id);
        addUser(u);
    }
}