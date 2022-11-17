```java
import org.springframework.stereotype.Service;
import remindproject.member.entity.Member;

import java.util.List;

@Service
public class MemberService {

    public Member createMember(Member member)
    {
        Member createdMember = member;
        return createdMember;
    }

    public Member updateMember(Member member)
    {
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId)
    {
        Member member = new Member(memberId, "aaa@aaa.com", "신건우", "010-1111-1111");
        return member;
    }

    public List<Member> findMembers()
    {
        List<Member> members = List.of(
                new Member(1, "aaa@aaa.com", "신건우", "010-1111-1111"),
                new Member(2, "bbb@bbb.com", "이경빈", "010-2222-2222"));
        return members;
    }

    public void deleteMember(long memberId) {}
}
```