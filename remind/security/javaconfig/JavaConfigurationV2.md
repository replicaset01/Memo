```java
import com.codestates.member.DBMemberService;
import com.codestates.member.InMemoryMemberService;
import com.codestates.member.MemberRepository;
import com.codestates.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class JavaConfiguration {
    @Bean
    public MemberService dbMemoryMemberService(MemberRepository memberRepository,
                                               PasswordEncoder passwordEncoder)
    {
        return new DBMemberService(memberRepository, passwordEncoder);
    }
}
```