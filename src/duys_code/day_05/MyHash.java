package duys_code.day_05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MyHash
 * @Author Duys
 * @Description
 * @Date 2021/9/24 13:11
 **/
public class MyHash {

    private MessageDigest messageDigest;

    public MyHash(String hash) {
        try {
            messageDigest = MessageDigest.getInstance(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hashCode(String input) {
       // return DatatypeConverter.printHexBinary(messageDigest.digest(input.getBytes())).toUpperCase();
        return null;
    }
}
