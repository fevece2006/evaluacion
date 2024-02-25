package com.fvelasquez.evaluacion.utils;



import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JwtUtil {

    private static final String SECRET_KEY = "AAA--BBB";
    private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    private JSONObject payload = new JSONObject();

    private String signature;
    private String encodedHeader;

    private JwtUtil(){
        encodedHeader = encode(new JSONObject(JWT_HEADER));
    }

    public JwtUtil(String sub,String email, long expires) {
        this();
        payload.put("sub", sub);
        payload.put("exp", expires);
        payload.put("iat", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        payload.put("iss", email);
        payload.put("jti", UUID.randomUUID().toString());
        signature = hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY);
    }

    @Override
    public String toString() {
        return encodedHeader + "." + encode(payload) + "." + signature;
    }


    private static String encode(JSONObject obj) {
        return encode(obj.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String hmacSha256(String data, String secret) {
        try {
            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);

            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return encode(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(JwtUtil.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
