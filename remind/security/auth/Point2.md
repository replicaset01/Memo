```text
UsernamePasswordAuthenticationFilter를 이용해서 JWT 발급 전의 로그인 인증 기능을 구현할 수 있다.

Spring Security에서는 개발자가 직접 Custom Configurer를 구성해 Spring Security의 Configuration을 커스터마이징(customizations) 할 수 있다.

Username/Password 기반의 로그인 인증은 OncePerRequestFilter 같은 Spring Security에서 지원하는 다른 Filter를 이용해서 구현할 수 있으며, Controller에서 REST API 엔드포인트로 구현하는 것도 가능하다.

Spring Security에서는 Username/Password 기반의 로그인 인증에 성공했을 때, 로그를 기록하거나 로그인에 성공한 사용자 정보를 response로 전송하는 등의 추가 처리를 할 수 있는 AuthenticationSuccessHandler를 지원하며, 로그인 인증 실패 시에도 마찬가지로 인증 실패에 대해 추가 처리를 할 수 있는 AuthenticationFailureHandler를 지원한다.
```