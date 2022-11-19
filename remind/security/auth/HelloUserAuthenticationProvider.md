```java
import com.codestates.auth.utils.HelloAuthorityUtils;
import com.codestates.member.Member;
import com.codestates.member.MemberService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * (1)과 같이 AuthenticationProvider 인터페이스의 구현 클래스로 정의합니다.
 *
 * 따라서 우리는 AuthenticationProvider의 구현 클래스로써의 HelloUserAuthenticationProvider를 구현해야 합니다. ^^
 *
 * Spring Security는 코드 4-34와 같이 AuthenticationProvider를 구현한 구현 클래스가 Spring Bean으로 등록되어 있다면 해당 AuthenticationProvider를 이용해서 인증을 진행합니다.
 *
 * 따라서 클라이언트쪽에서 로그인 인증을 시도하면 우리가 구현한 HelloUserAuthenticationProvider가 직접 인증을 처리하게 됩니다.
 *
 *
 * AuthenticationProvider 인터페이스의 구현 클래스는 authenticate(Authentication authentication) 메서드와 supports(Class<?> authentication) 메서드를 구현해야 합니다.
 *
 * 그 중에서 (2)의 supports(Class<?> authentication) 메서드는 우리가 구현하는 Custom AuthenticationProvider(HelloUserAuthenticationProvider)가 Username/Password 방식의 인증을 지원한다는 것을 Spring Security에게 알려주는 역할을 합니다.
 *
 * supports() 메서드의 리턴값이 true일 경우, Spring Security는 해당 AuthenticationProvider의 authenticate() 메서드를 호출해서 인증을 진행합니다.
 *
 *
 * (3)의 authenticate(Authentication authentication)에서 우리가 직접 작성한 인증 처리 로직을 이용해 사용자의 인증 여부를 결정합니다.
 *
 * (3-1)에서 authentication을 캐스팅하여 UsernamePasswordAuthenticationToken을 얻습니다.
 *
 * 이 UsernamePasswordAuthenticationToken 객체에서 (3-2)와 같이 해당 사용자의 Username을 얻은 후, 존재하는지 체크합니다.
 *
 * Username이 존재한다면 (3-3)과 같이 데이터베이스에서 해당 사용자를 조회합니다.
 *
 * (3-4)에서 로그인 정보에 포함된 패스워드(authToken.getCredentials())와 데이터베이스에 저장된 사용자의 패스워드 정보가 일치하는지를 검증합니다.
 *
 * (3-4)의 검증 과정을 통과했다면 로그인 인증에 성공한 사용자이므로 (3-5)와 같이 해당 사용자의 권한을 생성합니다.
 *
 * 마지막으로 (3-6)과 같이 인증된 사용자의 인증 정보를 리턴값으로 전달합니다.
 *
 * 이 인증 정보는 내부적으로 Spring Security에서 관리하게 됩니다.
 */

//@Component
public class HelloUserAuthenticationProvider implements AuthenticationProvider {
    private final MemberService memberService;
    private final HelloAuthorityUtils authorityUtils;
    private final PasswordEncoder passwordEncoder;

    public HelloUserAuthenticationProvider(MemberService memberService,
                                           HelloAuthorityUtils authorityUtils,
                                           PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.authorityUtils = authorityUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

        String username = authToken.getName();
        Optional.ofNullable(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User name or User Password"));
        Member member = memberService.findMember(username);

        String password = member.getPassword();
        verifyCredentials(authToken.getCredentials(), password);

        Collection<? extends GrantedAuthority> authorities = authorityUtils.createAuthorities(member.getRoles());

        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    // HelloUserAuthenticationProvider가 Username/Password 방식의 인증을 지원한다는 것을 Spring Security에게 알려준다.
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    private void verifyCredentials(Object credentials, String password) {
        if (!passwordEncoder.matches((String)credentials, password)) {
            throw new BadCredentialsException("Invalid User name or User Password");
        }
    }
}
```