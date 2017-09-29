package xuan.wen.zhi.qin.util.rsa;

import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    /***
     * Generator RSAPublicKey
     * @param path RSAPublicKey  path
     * @return RSAPublicKey
     * @throws Exception
     */
    public static RSAPublicKey generatorRSAPublicKey(String path) throws Exception {
        RSAPublicKey key = null;
        try {
            String builder = readKey(path);
            if (builder.length() > 0) {
                key = (RSAPublicKey) generatorPublicKey(builder.toString(), "RSA");
            }
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new Exception("Generator RSAPublicKey error : " + e.getLocalizedMessage());
        }
        return key;
    }

    /***
     * Generator RSAPrivateKey
     * @param path RSAPrivateKey  path
     * @return RSAPrivateKey
     * @throws Exception
     */
    public static RSAPrivateKey generatorRSAPrivateKey(String path) throws Exception {
        RSAPrivateKey key = null;
        try {
            String builder = readKey(path);
            if (builder.length() > 0) {
                key = (RSAPrivateKey) generatorPrivateKey(builder, "RSA");
            }
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new Exception("Generator RSAPublicKey error : " + e.getLocalizedMessage());
        }
        return key;
    }

    /***
     *  Generator PublicKey
     * @param buffer PublicKey  Buffer
     * @param algorithm the name of the requested key algorithm.
     * @return PublicKey
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey generatorPublicKey(String buffer, String algorithm) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(algorithm).generatePublic(new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(buffer)));
    }

    /***
     * Generator PrivateKey
     * @param buffer PrivateKey  Buffer
     * @param algorithm the name of the requested key algorithm.
     * @return PrivateKey
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey generatorPrivateKey(String buffer, String algorithm) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(algorithm).generatePrivate(new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(buffer)));
    }

    /***
     * ReadKey
     * @param path Key Path
     * @return String
     * @throws IOException
     */
    private static String readKey(String path) throws IOException {
        String line = null;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));) {
            while ((line = reader.readLine()) != null) {
                if (line.charAt(0) != '-') {
                    builder.append(line).append('\r');
                }
            }
        } catch (IOException e) {
            throw new IOException("ReadKey error : " + e.getLocalizedMessage());
        }
        return builder.toString();
    }
}
