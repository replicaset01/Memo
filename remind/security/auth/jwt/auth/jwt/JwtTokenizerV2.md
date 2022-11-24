```java
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtTokenizer {

    //i⭐ Plain Text(평문) 형태의 Secret Key의 byte[]를 base64 Encoding해줌
    public String encodeBase64SecretKey(String secretKey)
    {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //i⭐ 인증된 사용자에게 JWT를 최초로 발급해주기 위한 JWT 생성 Method
    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey)
    {
        //i⭐ Base64 형식의 SecretKey 문자열을 이용해 Key 객체를 얻음
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims) //i⭐ JWT에 포함시킬 Custom Claims, 주로 인증된 유저정보 추가
                .setSubject(subject) //i⭐ JWT의 제목 추가
                .setIssuedAt(Calendar.getInstance().getTime()) //i⭐ JWT 발행일 설정, 파라미터 타입 = Date
                .setExpiration(expiration) //i⭐ JWT 만료일 지정, 파라미터 타입 = Date
                .signWith(key) //i⭐ 서명을 위한 Key 객체 지정
                .compact(); //i⭐ JWT 생성, 직렬화
    }

    //i⭐ JWT 만료시 Refresh Token을 생성하는 Method
    public String generateRefreshToken(String subject,
                                       Date expiration,
                                       String base64EncodedSecretKey)
    {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    //i⭐ JWT의 서명에 사용할 SecretKey 생성 Method
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey)
    {
        //i⭐ 인코딩된 SecretKey를 디코딩 후, byte Array 반환
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        //i⭐ key byte Array를 기반으로 적절한 HMAC 알고리즘을 적용한 Key 객체 생성
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

    //i⭐ JWT 검증 기능 구현
    public void verifySignature(String jws, String base64EncodedSecretKey)
    {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }
}
```