```java
import org.springframework.stereotype.Component;
import remindproject.member.dto.MemberPatchDto;
import remindproject.member.dto.MemberPostDto;
import remindproject.member.dto.MemberResponseDto;
import remindproject.member.entity.Member;

@Component
public class MemberMapper {

    //i Request Body로 들어온 데이터가 DTO로 들어옴 -> Mapper에서 Entity로 변환
    
    //i PostDto -> Member 변환
    public Member memberPostDtoToMember(MemberPostDto memberPostDto)
    {
        return new Member(
                0L,
                memberPostDto.getEmail(),
                memberPostDto.getName(),
                memberPostDto.getPhone()
        );
    }

    //i PatchDto -> Member 변환
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto)
    {
        return new Member(
                memberPatchDto.getMemberId(),
                null,
                memberPatchDto.getName(),
                memberPatchDto.getPhone()
        );
    }

    //i Member -> ResponseDto 변환
    public MemberResponseDto memberToMemberResponseDto(Member member)
    {
        return new MemberResponseDto(
                member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone()
        );
    }
}
```