package src;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public final class PasswordGenerator {
    private static final int REQUIRED_LENGTH = 32;
    private static final String PLAIN_TEXT_HEX = "00000000000000000000000000000000";


    private final String myKey;
    private final String myAlgorithm;
    private final String myTransformation;

    PasswordGenerator(final String key, final String algorithm, final String transformation) {
        myKey = key;
        myAlgorithm = algorithm;
        myTransformation = transformation;
    }


    public String generatePassword() {
        String paddedKey = myKey;
        if(paddedKey.length() < REQUIRED_LENGTH) {
            paddedKey = addPadding(paddedKey);
        }
        SecretKey secretKey = new SecretKeySpec(paddedKey.getBytes(), myAlgorithm);

        try {
            Cipher cipher = Cipher.getInstance(myTransformation);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(PLAIN_TEXT_HEX.getBytes());
            return new String(result);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String addPadding(final String key) {
        StringBuilder sb = new StringBuilder(key);
        while(sb.length() < REQUIRED_LENGTH) {
            sb.append("0");
        }
        return sb.toString();
    }
}
