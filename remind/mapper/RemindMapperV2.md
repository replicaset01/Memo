```java
import org.mapstruct.Mapper;
import remindproject.member.dto.MemberPatchDto;
import remindproject.member.dto.MemberPostDto;
import remindproject.member.dto.MemberResponseDto;
import remindproject.member.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostDtoToMember(MemberPostDto memberPostDto);

    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);

    MemberResponseDto memberToMemberResponseDto(Member member);
}
```