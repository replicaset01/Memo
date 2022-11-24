```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() //i⭐H2 를 정상적으로 사용하기 위해 설정
                .and()
                .csrf().disable()        //i⭐csrf 공격 방지 옵션 Disable
                .cors(withDefaults())    //i⭐ corsConfiguraationSource Bean을 추가
                .formLogin().disable()   //i⭐ SSR 방식의 form-login Disable
                .httpBasic().disable()   //i⭐ HTTP Header에 ID,PW를 전송하지 않을것이므로 Disable
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() //i⭐ 모든 HTTP Request에 대해 허용
                );
        return http.build();
    }

    //i⭐ PasswordEncoder Bean 생성
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //i⭐CorsConfigurationSource Bean을 통해 구체적인 CORS정책 설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //i⭐ 모든 출처(Origin)에 대해 스크립트 기반의 HTTP 통신 허용
        configuration.setAllowedOrigins(Arrays.asList("*"));
        //i⭐ 파라미터로 지정한 HTTP Method에 대해 HTTP 통신 허용
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        //i⭐ CorsConfigurationSource의 구현클래스인 UrlBasedCorsConfigurationSource 객체 생성
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //i⭐ 모든 URL에 앞에서 구성한 CORS정책(CordConfiguration) 적용
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
```