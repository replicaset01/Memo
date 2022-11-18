```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfiguration {

    //i Inmemory User로 인증하기
    //i 사용자 인증을 위한 계정정보를 메모리에 고정
    @Bean
    public UserDetailsManager userDetailsService()
    {
        UserDetails userDetails =
                User.withDefaultPasswordEncoder() //i 암호화
                        .username("abc@abc.com")
                        .password("1234")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {

        /**
         * 1. csrf 공격 방어 disable
         * 2. 인증방법 지정 - form login
         * 3. 파라미터 -> AuthController의 loginForm()에 URL 요청 전송
         * 4. 로그인 인증 요청을 수행할 요청 URL 지정, login.html의 form 태그의 action의 URL과 동일
         * 5. 인증 실패 시 리다이렉트 할 URI 지정
         * 6. 보안 설정을 메소드 체인 형태로 구성 가능
         * 7. 접근 권한 체크 정의
         * 8-9. 클라이언트의 모든 요청에 대해 접근 허용
         */

        http
                .csrf().disable()
                .formLogin()
                .loginPage("/auths/login-form")
                .loginProcessingUrl("/process_login")
                .failureUrl("/auths/login-form?error")
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll();
        return http.build();
    }
}
```