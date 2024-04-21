//package com.mytravel.authservice.util.jwt;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Li Gengrun
// * @date 2024/4/20 10:24
// */
//@Component
//public class JwtTokenUtil {
//
//    private static final String JWT_CACHE_KEY = "jwt:userId:";
//    private static final String USER_ID = "userId";
//    private static final String USER_NAME = "username";
//    private static final String ACCESS_TOKEN = "access_token";
//    private static final String REFRESH_TOKEN = "refresh_token";
//    private static final String EXPIRE_IN = "expire_in";
//
//    private String generateToken(Map<String, Object> claims) {
//        Date expirationDate = new Date(System.currentTimeMillis() + 3600000 );
//        return Jwts.builder().setClaims(claims)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS512, "MyTravel") //不推荐将密钥硬编码，为了简便就先这样了
//                .compact();
//    }
//
//    public String generateToken(String userId, String username, Map<String,String> payloads) {
//        Map<String, Object> claims = buildClaims(userId, username, payloads);;
//        return generateToken(claims);
//    }
//
//    private Map<String, Object> buildClaims(String userId, String username, Map<String, String> payloads) {
//        int payloadSizes = payloads == null? 0 : payloads.size();
//
//        Map<String, Object> claims = new HashMap<>(payloadSizes + 2);
//        claims.put("sub", userId);
//        claims.put("username", username);
//        claims.put("created", new Date());
//
//        if(payloadSizes > 0){
//            claims.putAll(payloads);
//        }
//
//        return claims;
//    }
//
//    private Map<String, Object> buildToken(String userId, String username) {
//        String accessToken = generateToken(userId, username, null);
////        String refreshToken = generateRefreshToken(userId, username, null);
//        HashMap<String, Object> tokenMap = new HashMap<>(2);
//        tokenMap.put(ACCESS_TOKEN, accessToken);
////        tokenMap.put(REFRESH_TOKEN, refreshToken);
//        tokenMap.put(EXPIRE_IN, 3600000); // expiration time
//        return tokenMap;
//    }
//
//    public Map<String, Object> generateTokenAndRefreshToken(String userId, String username) {
//        Map<String, Object> tokenMap = buildToken(userId, username);
//        // cacheToken(userId, tokenMap);
//        return tokenMap;
//    }
//
//    public static void main(String[] args) {
//        JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();
//        Map<String, Object> token = jwtTokenUtil.generateTokenAndRefreshToken("1", "user");
//        System.out.println("Generated Token: " + token);
//    }
//}
