```java
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloHomeController {

    //i Authorization Server로부터 전달받은 Access Token인지 확인
    @GetMapping("/hello-oauth2")
    public String home(@RegisteredOAuth2AuthorizedClient("google")OAuth2AuthorizedClient authorizedClient) {
        
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        System.out.println("accessToken Value = " + accessToken.getTokenValue());
        System.out.println("accessToken Type = " + accessToken.getTokenType().getValue());
        System.out.println("accessToken Scopes= " + accessToken.getScopes());
        System.out.println("accessToken Issued At = " + accessToken.getIssuedAt());
        System.out.println("accessToken Expires At = " + accessToken.getExpiresAt());
        return "hello-oauth2";
    }
}
```