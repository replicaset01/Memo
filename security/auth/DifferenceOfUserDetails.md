```text
//i v1 ⭐
loadUserByUsername() 의 리턴값 = new User

//i v2 ⭐
loadUserByUsername() 의 리턴값 = new HelloUserDetails
    내부 클래스 HelloUserDetails 생성
    HelloUserDetails 로 권한 생성 위임
    Member Entity 상속
    
//i v3 ⭐
User의 권한 정보를 저장하기 위한 테이블 생성

회원 가입 시, User의 권한 정보(Role)를 데이터베이스에 저장하는 작업

로그인 인증 시, User의 권한 정보를 데이터베이스에서 조회하는 작업
```