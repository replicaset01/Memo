```java
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMemberService implements MemberService
{

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    /**
     *
     * @param ⭐ userDetailsManager
     * UserDetailsManager는 Spring Security의 User를 관리하는 관리자 역할을 합니다.
     * 우리가 SecurityConfiguration 에서 Bean으로 등록한 UserDetailsManager는
     * InMemoryUserDetailsManager 이므로 여기서 DI 받은 UserDetailsManager 인터페이스의
     * 하위 타입은InMemoryUserDetailsManager 라는 사실을 기억하기 바랍니다
     *
     * @param ⭐ passwordEncoder
     * PasswordEncoder 는 Spring Security User를 등록할 때 패스워드를 암호화 해주는 클래스입니다.
     * Spring Security 5 에서는 InMemory User의 경우에도 패스워드의 암호화가 필수입니다.
     * 따라서 DI 받은 PasswordEncoder 를 이용해 User의 패스워드를 암호호 해 주어야 합니다
     *
     */

    public InMemoryMemberService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder)
    {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member createMember(Member member)
    {

        /**
         * ⭐ List
         * Spring Security에서 User를 등록하기 위해서는 해당 User의 권한(Authority)을 지정해 주어야 합니다.
         * 따라서 createAuthorities(Member.MemberRole.ROLE_USER.name());를 이용해 User의 권한 목록을 List<GrantedAuthority>로 생성하고 있습니다.
         *
         *  ⭐ String
         *  PasswordEncoder 를 이용해 등록할 User의 패스워드를 암호화 하고 있습니다
         *  만약, 패스워드를 암호화 하지 않고 User를 등록한다면 User 등록은 되지만 로그인 인증 시,
         *  다음과 같은 에러를 만나게 되므로 User의 패스워드는 반드시 암호화 해야 합니다
         *
         *  ⭐ UserDetails
         *  Spring Security User로 등록하기 위해 UserDetails 를 생성합니다
         *  Spring Security에서는 Spring Security에서 관리하는 User 정보를 UserDetails로 관리한다는 사실을 꼭 기억하기 바랍니다
         */

        List<GrantedAuthority> authorities = createAuthorities(Member.MemberRole.ROLE_USER.name());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());

        UserDetails userDetails = new User(member.getEmail(), encryptedPassword, authorities);


        //i ⭐ UserDetailsManager의 createUser() 메서드를 이용해서 User를 등록합니다.
        userDetailsManager.createUser(userDetails);
        return member;
    }


    /**
     *
     * @param roles
     * @return
     * ⭐ Spring Security에서는 SimpleGrantedAuthority 를 사용해 Role 베이스 형태의 권한을 지정할 때 ‘Roll_’ + 권한명 형태로 지정해 주어야 합니다.
     * 그렇지 않을 경우 적절한 권한 매핑이 이루어지지 않는다는 사실을 기억하기 바랍니다.
     *
     * ⭐ Java의 Stream API를 이용해 생성자 파라미터로 해당 User의 Role을 전달하면서
     * SimpleGrantedAuthority 객체를 생성한 후, List<SimpleGrantedAuthority> 형태로 리턴해 줍니다
     */

    private List<GrantedAuthority> createAuthorities(String... roles)
    {
        return Arrays.stream(roles)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}

```