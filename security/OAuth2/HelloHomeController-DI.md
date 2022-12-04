```java
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloHomeController {

    //i OAuth2 인증이된(OAuth2AuthorizedClient) 클라이언트를 관리하는 인터페이스
    private final OAuth2AuthorizedClientService authorizedClientService;

    public HelloHomeController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    //i Authorization Server로부터 전달받은 Access Token인지 확인
    @GetMapping("/hello-oauth2")
    public String home(Authentication authentication) {
        var authorizedClient = authorizedClientService.loadAuthorizedClient("google", authentication.getName());

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