```text

//i v1 ⭐

--- 주입
None
--- 입력
QueryParameter, 수작업 Json
--- 변환
Dto
--- 리턴
Dto

//i v2 ⭐

--- 주입
None
--- 입력
RequestBody -> Dto
--- 변환
Handler Method
--- 리턴
Entity

//i v3 ⭐

--- 주입
MemberService
--- 입력
RequestBody -> Dto
--- 변환
Handler Method
--- 리턴
Entity

//i v4  (mapper Class 사용) ⭐

--- 주입
MemberService
MemberMapper
--- 입력
RequestBody -> Dto
--- 변환
Mapper
--- 리턴
Converted ResponseDto

//i v5 (MapStruct 적용) ⭐

--- 주입
MemberService
MemberMapper
--- 입력
RequestBody -> Dto
--- 변환
Mapstruct
--- 리턴
Converted ResponseDto

//i v6 (@ExceptionHandler 적용) ⭐

--- 주입
MemberService
MemberMapper
--- 입력
RequestBody -> Dto
--- 변환
Mapstruct
--- 리턴
Converted ResponseDto

//i v7 (ErrorResponse 역할 분리) ⭐
//i 새로운 Exception 마다 
//i ExceptionHandler를 추가로 작성해야함

--- 주입
MemberService
MemberMapper
--- 입력
RequestBody -> Dto
--- 변환
Mapstruct
--- 리턴
Converted ResponseDto

//i v8 (ErrorResponse 역할 분리) ⭐
//i 새로운 Exception 마다 
//i @ExceptionHandler 추가

--- 주입
MemberService
MemberMapper
--- 입력
RequestBody -> Dto
--- 변환
Mapstruct
--- 리턴
Converted ResponseDto

//i v9 (ErrorResponse 역할 분리) ⭐
//i 새로운 Exception 마다 
//i @ExceptionHandler 추가

--- 주입
MemberService
MemberMapper
--- 입력
RequestBody -> Dto
--- 변환
Mapstruct
--- 리턴
Converted ResponseDto

```