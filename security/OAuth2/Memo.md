```text
⭐
spring-boot-starter-oauth2-client 으로 추가한 후, 
별도의 설정을 하지 않아도 Spring Boot의 자동 구성을 통해 
OAuth 2 로그인 인증 기능이 활성화 된다.

⭐
ClientRegistration은 OAuth 2 시스템을 사용하는 
Client 등록 정보를 표현하는 객체이다.

⭐
Spring Security에서 제공하는 CommonOAuth2Provider enum은 내부적으로 Builder 패턴을 이용해 ClientRegistration 인스턴스를 제공하는 역할을 한다.

⭐
OAuth2AuthorizedClientService는 권한을 부여받은 Client인 OAuth2AuthorizedClient를 관리하는 역할을 한다.

⭐
OAuth2AuthorizedClientService를 이용해서 
OAuth2AuthorizedClient 가 보유하고 있는 Access Token에 접근할 수 있다.

⭐
OAuth2AuthorizedClientService의 
loadAuthorizedClient("google", authentication.getName())를 호출하면 OAuth2AuthorizedClientRepository를 통해 
OAuth2AuthorizedClient 객체를 로드 할 수 있다.

------------------------------------------------------
인증 처리 흐름 JWT + OAuth2, 6~11은 Spring이 내부적으로 처리해줌
------------------------------------------------------
(1) Resource Owner가 웹 브라우저에서 ‘Google 로그인 링크’를 클릭합니다.


(2) Frontend 애플리케이션에서 Backend 애플리케이션의 http://localhost:8080/oauth2/authorization/google로 request를 전송합니다. 이 URI의 requet는 OAuth2LoginAuthenticationFilter 가 처리합니다.


(3) Google의 로그인 화면을 요청하는 URI로 리다이렉트합니다. 이 때 Authorization Server가 Backend 애플리케이션 쪽으로 Authorization Code를 전송할 Redirect URI(http://localhost:8080/login/oauth2/code/google)를 쿼리 파라미터로 전달합니다. Redirect URI는 Spring Security가 내부적으로 제공합니다.


(4) Google 로그인 화면을 오픈합니다.


(5) Resource Owner가 Goole 로그인 인증 정보를 입력해서 로그인을 수행합니다.


(6) 로그인에 성공하면 (3)에서 전달한 Backend Redirect URI(http://localhost:8080/login/oauth2/code/google)로 Authorization Code를 요청합니다.


(7) Authorization Server가 Backend 애플리케이션에게 Authorization Code를 응답으로 전송합니다.


(8) Backend 애플리케이션이 Authorization Server에게 Access Token을 요청합니다.


(9) Authorization Server가 Backend 애플리케이션에게 Access Token을 응답으로 전송합니다.

여기서의 Access Token은 Google Resource Server에게 Resource를 요청하는 용도로 사용됩니다.


(10) Backend 애플리케이션이 Resource Server에게 User Info를 요청합니다.

여기서의 User Info는 Resource Owner에 대한 이메일 주소, 프로필 정보 등을 의미합니다.


(11) Resource Server가 Backend 애플리케이션에게 User Info를 응답으로 전송합니다.


(12) Backend 애플리케이션은 JWT로 구성된 Access Token과 Refresh Token을 생성한 후, Frondend 애플리케이션에게 JWT(Access Token과 Refresh Token)를 전달하기 위해 Frondend 애플리케이션(http://localhost?access_token={jwt-access-token}&refresh_token={jwt-refresh-token})으로 Redirect합니다.
```