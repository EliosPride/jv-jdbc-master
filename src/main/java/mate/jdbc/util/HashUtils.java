package mate.jdbc.util;

import mate.jdbc.exception.DataProcessingException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static String encrypt(String string) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(string.getBytes(StandardCharsets.UTF_8));
            byte[] byteBuilder = sha256.digest();
            StringBuilder builder = new StringBuilder();
            for (byte symbol : byteBuilder) {
                builder.append(symbol);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new DataProcessingException("Can't encrypt data", e);
        }
    }
}
