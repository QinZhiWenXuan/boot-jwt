package xuan.wen.zhi.qin.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import xuan.wen.zhi.qin.model.JWTModel;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtils {
    private static final String SECRET = "1234567890qwertyuiopasdfghjklzxcvbnm~!@#$%^&*()_+";
    private static final String ISS = "JWT";


    /***
     * 签名
     * @param model 签名对象
     * @param timeout 超时
     * @return token
     * @throws UnsupportedEncodingException
     */
    public static String sign(JWTModel model, long timeout) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis() + timeout);
        JWTCreator.Builder builder = JWT.create().withIssuer(ISS).withIssuedAt(new Date())
                .withSubject(model.getLoginName())
                .withNotBefore(date)
                .withExpiresAt(date);
        model.asMap().forEach((String key, String value) -> {
            builder.withClaim(key, value);
        });
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /***
     * 检测超时
     * @param token token
     * @return true : Timeout | false : not Timeout
     */
    public static boolean checkTimeout(String token) {
        boolean timeout = true;
        try {
            JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISS)
                    .acceptNotBefore(System.currentTimeMillis())
                    .build().verify(token);
            timeout = false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return timeout;
    }

    /****
     * Decoded
     * @param token token
     * @return DecodedJWT
     * @throws UnsupportedEncodingException
     */
    public static DecodedJWT decoded(String token) throws UnsupportedEncodingException {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .withIssuer(ISS).acceptNotBefore(System.currentTimeMillis()).build().verify(token);
    }

}
