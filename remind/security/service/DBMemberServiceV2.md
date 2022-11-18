```java
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DBMemberService implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public DBMemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        //i ⭐ 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        //i ⭐ 암호화된 비밀번호 password 필드에 다시 할당
        member.setPassword(encryptedPassword);

        Member savedMember = memberRepository.save(member);

        System.out.println("# Create Member in DB");
        return savedMember;
    }
}
```