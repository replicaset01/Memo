```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    //i Inmemory User로 인증하기
    //i 사용자 인증을 위한 계정정보를 메모리에 고정
    @Bean
    public UserDetailsManager userDetailsService()
    {
        UserDetails user =
                User.withDefaultPasswordEncoder() //i 암호화
                        .username("abc@abc.com")
                        .password("1234")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder() //i 암호화
                        .username("admin@abc.com")
                        .password("1234")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {

        /** .exceptionHandling 부터 1번
         * 1. 권한없는 사용자가 특정 requestURL에 접근시 표시할 에러 페이지
         * 2. 람다로 request URI에 대한 접근 권한 부여
         * 3. antMatchers 순서 주의
         */

        http
                .csrf().disable()
                .formLogin()
                .loginPage("/auths/login-form")
                .loginProcessingUrl("/process_login")
                .failureUrl("/auths/login-form?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/auths/access-denied")
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/orders/**").hasRole("ADMIN")
                        .antMatchers("/members/my-page").hasRole("USER")
                        .antMatchers("/**").permitAll());
        return http.build();
    }
}
```