```text
//i 라이브러리
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	implementation 'org.mapstruct:mapstruct:1.5.2.Final'
	annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.2.Final'
	implementation 'org.springframework.boot:spring-boot-starter-mail'
	implementation 'com.google.code.gson:gson'

	implementation 'org.springframework.boot:spring-boot-starter-security' // (1)

  // (2) JWT 기능을 위한 jjwt 라이브러리
	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
	runtimeOnly	'io.jsonwebtoken:jjwt-jackson:0.11.5'
	
//i 사전작업
SecurityConfiguration 클래스 
MemberDto에 password Field 추가
Member에 password Field 추가, roles(권한 테이블 생성) 추가

//i 구현
⭐MemberDetailsService 구현, 내부 private final 클래스로 custom MemberDetails 존재

⭐로그인 인증정보 역직렬화를 위한 LoginDto 구현

⭐JWT를 생성하는 KwtTokenizer 구현

⭐로그인 인증을 처리하는 Custom Security Filter 구현
Annotation = @SneakyThrows, @Override
상속 = UsernamePasswordAuthenticationFilter
DI = AuthenticationManager, JwtTokenizer

인증 시도 로직 = public Authentication  
param = HttpServletRequest & Response {
    1. 로그인 정보 -> DTO 역직렬화를 위한 ObjectMapper 인스턴스 생성
    2. ServletInputStream -> LoginDto 클래스의 객체로 역직렬화
    3. 로그인 정보를 포함한 UsernamePasswordAuthenticationToken 생성
    4. 위에서 만든 토큰을 AuthenticationManager 에게 인증 처리 위임
}

인증 성공 시 호출 로직 protected void
param = HttpServletRequest & Response, FilterChain, Authentication {
    1. Authentiocation을 이용해 Member 객체를 얻음
    2. delegateAccessToken 메소드를 이용해 AccessToken 생성
    3. delegateAccessToken 메소드를 이용해 RefreshToken 생성
}

Access 토큰 생성 로직 String

⭐Custom Filter 추가를 위한 SecurityConfiguration에 설정 추가

⭐로그인 인증성공, 실패에 따른 추가 처리 클래스인  AuthenticationSuccessHandler, AuthenticationFailureHandler 구현
```