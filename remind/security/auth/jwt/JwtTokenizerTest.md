```java
import io.jsonwebtoken.io.Decoders;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 학습이 핵심이 아니니 구글에서 검색해서 학습하세요.
public class JwtTokenizerTest {
    private static JwtTokenizer jwtTokenizer;
    private String secretKey;
    private String base64EncodedSecretKey;

    //i⭐ SecretKey -> Base64 Encoding
    @BeforeAll
    public void init() {
        jwtTokenizer = new JwtTokenizer();
        secretKey = "kevin1234123412341234123412341234";  // encoded "a2V2aW4xMjM0MTIzNDEyMzQxMjM0MTIzNDEyMzQxMjM0"

        base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(secretKey);
    }

    //i⭐ Encoding Test, Decoding한 값과 일치하는지 AssertThat 수행
    @Test
    public void encodeBase64SecretKeyTest() {
        System.out.println(base64EncodedSecretKey);

        assertThat(secretKey, is(new String(Decoders.BASE64.decode(base64EncodedSecretKey))));
    }

    //i⭐ AccessToken 생성 테스트, 생성할떄마다 값 변경, Null AssertThat 테스트
    @Test
    public void generateAccessTokenTest() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", 1);
        claims.put("roles", List.of("USER"));

        String subject = "test access token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        Date expiration = calendar.getTime();

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        System.out.println(accessToken);

        assertThat(accessToken, notNullValue());
    }

    //i⭐ RefreshToken Test, CustomClaims 불필요
    @Test
    public void generateRefreshTokenTest() {
        String subject = "test refresh token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        Date expiration = calendar.getTime();

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        System.out.println(refreshToken);

        assertThat(refreshToken, notNullValue());
    }

    @DisplayName("dose not throw any Exception when jws verify")
    @Test
    public void verifySignatureTest()
    {
        String accessToken = getAccessToken(Calendar.MINUTE, 10);
        assertDoesNotThrow(() -> jwtTokenizer.verifySignature(accessToken, base64EncodedSecretKey));
    }

    @DisplayName("throw ExpiredJwtException when jws verify")
    @Test
    public void verifyExpirationTest() throws InterruptedException
    {
        String accessToken = getAccessToken(Calendar.SECOND, 1);
        assertDoesNotThrow(() -> jwtTokenizer.verifySignature(accessToken, base64EncodedSecretKey));
        TimeUnit.MILLISECONDS.sleep(1500);
        assertThrows(ExpiredJwtException.class, () -> jwtTokenizer.verifySignature(accessToken,base64EncodedSecretKey));
    }

    private String getAccessToken(int timeUnit, int timeAmount)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", 1);
        claims.put("roles", List.of("USERS");

        String subject = "test access tokken";
        Calendar calendar = Calendar.getInstance();
        calendar.add(timeUnit, timeAmount);
        Date expiration = calendar.getTime();
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }
}
```