package web.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HashUtil {
    private final String ENCRYPTOR = "SHA-512";
    private Logger logger = Logger.getLogger(HashUtil.class);

    public byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public String hashPassword(String password, byte[] salt) {
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTOR);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());

            for (byte b: digest) {
                hashPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Can`t hashing password", e);
        }

        return hashPassword.toString();
    }
}
