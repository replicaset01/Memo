```java
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.Member;
import com.codestates.member.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * - Custom UserDetails 사용 안함
 * - HelloAuthorityUtils를 바로 사용(정적인 방식)하여 Spring Security에 Role 정보 제공
 */

    @Component
    public class HelloUserDetailsService implements UserDetailsService {   // (1)

        /**
         * ⭐ HelloUserDetailsService와 같은 Custom UserDetailsService를
         * 구현하기 위해서는 UserDetailsService 인터페이스를 구현해야 합니다.
         *
         * ⭐ HelloUserDetailsService는 데이터베이스에서 User를 조회하고,
         * 조회한 User의 권한(Role) 정보를 생성하기 위해 MemberRepository와
         * HelloAuthorityUtils 클래스를 DI 받습니다
         */

        private final MemberRepository memberRepository;
        private final HelloAuthorityUtils authorityUtils;

        public HelloUserDetailsService(MemberRepository memberRepository, HelloAuthorityUtils authorityUtils) {
            this.memberRepository = memberRepository;
            this.authorityUtils = authorityUtils;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Optional<Member> optionalMember = memberRepository.findByEmail(username);
            
            Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
            //i ⭐ HelloAuthorityUtils를 이용해 DB에서 조회한 회원의 이메일을 이용해
            //i ⭐ Role 기반의 권한 정보 컬렉션 생성
            Collection<? extends GrantedAuthority> authorities = authorityUtils.createAuthorities(findMember.getEmail());
            //i ⭐ Spring Security는 조회한 유저 인증정보와 생성된 권한의 정보를 모르기때문에
            //i ⭐ UserDetails 인터페이스의 구현체인 User 객체를 통해 제공
            return new User(findMember.getEmail(), findMember.getPassword(), authorities);
        }
        

        /**
         * ⭐ UserDetails
         *
         * UserDetails는 UserDetailsService에 의해 로드(load)되어 인증을 위해 사용되는 핵심 User 정보를 표현하는 인터페이스입니다.
         *
         * UserDetails 인터페이스의 구현체는 Spring Security에서 보안 정보 제공을 목적으로 직접 사용되지는 않고, Authentication 객체로 캡슐화 되어 제공됩니다.
         */
    }

```