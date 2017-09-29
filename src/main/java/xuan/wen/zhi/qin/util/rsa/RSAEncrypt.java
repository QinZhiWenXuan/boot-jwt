package xuan.wen.zhi.qin.util.rsa;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAEncrypt {
    /***
     * encrypt
     * @param publicKey RSAPublicKey
     * @param target target
     * @return Encrypt Byte Array
     * @throws Exception
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] target) throws Exception {
        byte[] results = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            results = cipher.doFinal(target);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new Exception("Encrypt error : " + e.getLocalizedMessage());
        }
        return results;
    }

    /****
     * Decrypt
     * @param privateKey RSAPrivateKey
     * @param target target
     * @return Decrypt Byte Array
     * @throws Exception
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] target) throws Exception {
        byte[] results = null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            results = cipher.doFinal(target);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new Exception("Decrypt error : " + e.getLocalizedMessage());
        }
        return results;
    }
}
