package vn.edu.iuh.fit.backend.help;
import org.mindrot.jbcrypt.BCrypt;

public class AuthHelper {
    public static String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(plainPassword, salt);
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
