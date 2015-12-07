package day4;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Predicate;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws NoSuchAlgorithmException {
        final String prefix = "iwrupvqb";

        Predicate<byte[]> hashMatcher = new HashMatcherPart2();

        MessageDigest digest = MessageDigest.getInstance("MD5");
        //        Charset charset = Charset.forName("US-ASCII");
        Charset charset = Charset.defaultCharset();

        String firstMatchingKey = null;
        for (byte[] key : KeyGenerator.generateWithPrefix(prefix.getBytes(charset))) {
            //TODO: optimize this.
            byte[] md5HashBytes = digest.digest(key);
            boolean matches = hashMatcher.test(md5HashBytes);

            if (logger.isDebugEnabled()) {
                logger.debug("generated key: {}", new String(key, charset));
                logger.debug("corresponding hash: {}", Hex.encodeHexString(md5HashBytes));
                logger.debug("is matching? {}", matches);
            }

            if (matches) {
                firstMatchingKey = new String(key, charset);
                break;
            }
        }

        System.out.println("first matching key: " + firstMatchingKey);
    }
}
