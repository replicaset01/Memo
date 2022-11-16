```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import remindproject.member.dto.MemberPatchDto;
import remindproject.member.dto.MemberPostDto;
import remindproject.member.dto.MemberResponseDto;
import remindproject.member.entity.Member;
import remindproject.member.mapper.MemberMapper;
import remindproject.member.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/v5/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@RequestBody @Valid MemberPostDto memberPostDto)
    {
        //i 핸들러메소드는 DTO -> Entity 변환 작업까지 하면 안됨
        //i 변환작업을 mapper 에게 넘김
        Member member = mapper.memberPostDtoToMember(memberPostDto);

        //i 회원 정보 등록을 위해 createMember 메소드 호출, 서비스와 연결 지점
        //i 응답 변환 작업을 mapper에게 넘김
        Member response = memberService.createMember(member);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.CREATED);
    }


    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @RequestBody @Valid MemberPatchDto memberPatchDto)
    {
        memberPatchDto.setMemberId(memberId);

        Member response = memberService.updateMember
                (mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId)
    {
        Member response = memberService.findMember(memberId);
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers()
    {
        List<Member> members = memberService.findMembers();

        //i mapper를 이용해 List<Member>를 ResponseDto로 변환
        List<MemberResponseDto> response = members.stream()
                .map(member -> mapper.memberToMemberResponseDto(member))
                .collect(Collectors.toList());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId)
    {
        memberService.deleteMember(memberId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
```