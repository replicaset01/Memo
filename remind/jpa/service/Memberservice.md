```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import remindproject.advice.BusinessLogicException;
import remindproject.advice.ExceptionCode;
import remindproject.member.entity.Member;
import remindproject.member.repository.MemberRepository;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        return memberRepository.save(member);
    }
    
    public Member updateMember(Member member) {
        
    }
    
    public Member findMember(long memberId) {
        
    }
    
    public Page<Member> findMembers(int page, int size) {
        
    }
    
    public Member deleteMember(long memberId) {
        
    }
    
    public Member findVerifiedMember(long memberId) {
        //i memrepo에서 ID로 멤버를 찾음과 동시에 NullPointerException 방지 - Optional
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        
        return findMember;
    }
    
    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXIST);
    }
}
```