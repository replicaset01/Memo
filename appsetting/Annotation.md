⭐ @Target(ElementType.TYPE) , @Retention(RetentionPolicy.RUNTIME), @Documented
Custom Annotation 생성 시 사용

⭐ @BeforeEach	
테스트 메소드 실행 전 실행됨

⭐ @AfterEach	
테스트 메소드 이후에 실행됨

⭐ @DisplayName("")	
테스트 클래스or메소드의 이름 정의 가능

⭐ @Disabled("")	
테스트 클래스or메소드를 비활성화

⭐ @Autowired	
필요한 의존 객체의 '타입'에 해당하는 빈을 찾아 의존성 주입,  getBean(.class)와 동일하다고 보면 됨

⭐ @Component	
스프링 빈 등록, 이름을 지정 하려면 ("")로 지정하되, 첫글자는 무조건 소문자

⭐ @Configuration	
설정 정보를 스프링 컨테이너에 등록, 컴포넌트 스캔 대상임 (Why? Configuration 안에 @Compoent가 있음)

⭐ @ComponentScan	
@Component가 붙은 대상을 스프링 빈으로 등록, 보편적으로 탐색 시작위치는 프로젝트 최상단

@ComponentScan 추가 정보

↑ (excludeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, classes=Configuration.class) 제외 설정 
                                                                                                ↑필터 사용시 기본값의 type이 FilterType.ANNOTATION 이라서 type은 생략 가능
↑ basePackages = "hello.core" 컴포넌트 스캔 위치 지정
↑ basePackages = {"hello.core", "hello.service"} 여러곳 지정 가능
↑ basePackageClasses = 지정한 클래스의 패키지를 탐색 시작 위로 지정

[스캔 대상]
@Component
@Controller
@Service
@Repository
@Configuration

[필터 옵션]
ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
ex) org.example.SomeAnnotation
ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
ex) org.example.SomeClass
ASPECTJ: AspectJ 패턴 사용
ex) org.example..*Service+
REGEX: 정규 표현식
ex) org\.example\.Default.*
CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리
ex) org.example.MyTypeFilter

⭐ @GetMapping	
클라이언트가 서버의 리소스를 조회할때 사용

⭐ @RequestMapping	
클라이언트 요청 <-> 핸들러 메소드 매핑, 클래스 전체에 사용되는 공통 URL 설정
@RequestMapping 추가정보
produces = 응답 데이터의 타입 설정

⭐ @RestController	
이거 붙은 클래스가 Rest API의 리소스를 처리하기위한 API 엔드포인트로 동작함을 정의함, Bean으로 등록됨

⭐ @PostMapping	
클라이언트의 요청 데이터를 서버에 생성 (HTTP Method 타입 일치해야함)

⭐ @RequestParam	
핸들러 메소드의 파라미터 종류 중 하나, 클라이언트에서 전송하는 요청 데이터를
쿼리파라미터, 폼데이터 형식으로 전송하면 이를 서버 쪽에서 전달 받을때 사용
* 쿼리 파타미터 = 요청 URL에서 ?를 기준으로 붙은 키,값 쌍의 데이터 ex)

⭐ @PathVariable()	
핸들러 메소드의 파라미터 종류 중 하나, 괄호안의 문자값은 @GetMapping("/{member-id}" 의
중괄호 안의 문자열과 동일해야 매핑됨, 불일치시 MissingPathVariableException 발생

⭐ @RequiredArgsConstructor	
private final 멤버에 대한 생성자를 만들어줌

⭐ @NoArgsConstructor	
파라미터가 없는 생성자 생성

⭐ @AllArgsConstructor	
모든 필드값을 파라미터로 받는 생성자 생성

⭐ @Qualifier	
Lombok 관련 추가 구분자 지정, 빈이름 변경X

⭐ @Primary	
우선순위 설정

⭐ @RequestBody	
JSON 형식의 Request를 DTO 클래스 객체로 변환시켜줌

⭐ @ResponseBody	
JSON 형식의 Request를 클라이언트에게 전달하기 위해 DTO클래스 객체를 Response Body로 변환

⭐ @Email	
이메일 유효성 검증

⭐ @NotBlank("")	
값이 비어있다면 메시지 출력

⭐ @Pattern	
옵션: regexp(정규표현식), message(메세지)

⭐ @PostConstruct	
초기화 어노테이션

⭐ @PreDestroy	 
종료 어노테이션

⭐ @Scope	
옵션 : proxyMode = 빈 스코프를 프록시모드로 동작하게 함 ScopedProxyMode.TARGET_CLASS

⭐ @Mapper	
MapStruct의 Mapper Interface로 지정, componentModel = "spring"  <- 스프링 빈으로 등록

⭐ @ExceptionHandler	
예외 처리 어노테이션

⭐ @RestControllerAdvice	
예외처리 공통화, @ControllerAdvice + @ResponseBody = @RestControllerAdvice

⭐ @InitBinder	 
SSR 방식

⭐ @ModelAttribute	
SSR 방식

⭐ @ResponseStatus()	
Attribute에 HttpStatus.Msg 삽입,  한가지 유형의 예외만 처리할 경우에 사용

⭐ @MappedCollection	
attr= idColumn, keyColumn  /  엔티티 클래스 간 연관관계를 맺어주는 정보를 의미함
List일 경우 id,keyColumn 2개 다 필수로 입력해야 하지만
Set일경우 keyColimn은 필수가 아님
idColumn = 자식 테이블에 추가되는 외래키 컬럼명 지정,
keyColumn = 외래키를 포함하는 자식테이블의 기본키 컬럼명 지정
⭐ @Query("")	
개발자가 직접 쿼리문 작성, ex) @Query("SELECT * FROM USER WHERE USER_ID = :userId")
위의 예시 코드에서 :userId는 메소드의 변수값을 채워주는 동적 쿼리 파라미터임

------------JPA----------
⭐ @Id	
엔티티의 고유 식별자 역할, DB의 Primary Key로 지정한 컬럼에 해당함

⭐ @Entity	
JPA 관리대상 엔티티 클래스 지정, 
Attr = name
name 애트리뷰트를 설정하지 않으면 기본값으로 클래스 이름을 테이블 이름으로 사용

⭐ @Table	
테이블 지정, 미 지정시 클래시 이름이 테이블명이 됨, Optional, 
Attr = name
name 애트리뷰트를 설정하지 않으면 기본값으로 클래스 이름을 테이블 이름으로 사용
@Table 애너테이션은 옵션이며, 추가하지 않을 경우 클래스 이름을 테이블 이름으로 사용

⭐ @GeneratedValue	
멤버 변수에 추가하면 테이블에서 기본키가 되는 식별자를 자동 설정
Attr로 strategy = GenerationType.IDENTITY 사용 시 DB에 pk 생성 위임
Attr로 strategy = GenerationType.SEQUENCE 사용 시 DB 시퀀스 사용

⭐ @Column	
Field <-> Column 매핑
Attr = nullable (default = true) - Null값 허용 여부
Attr = updatable (default = true) - 컬럼 수정 여부
Attr = unique (default = false) - 고유 값 설정 여부(제약조건 설정)
Attr = length (default = 255) - 컬럼에 저장할 문자 길이

⭐ @Transient	
JPA 관리범위에서 제외

⭐ @Enumerated	
Enum 타입과 매핑
Attr = EnumType.STRING - enum의 이름을 테이블에 저장 (권장)
Attr = EnumType.ORDINAL - enum의 순서를 나타내는 숫자를 테이블에 저장
⭐ @JoinColumn	
Attr name 사용 시 해당 테이블의 외래키에 해당하는 컬럼명 지정

⭐ @OneToMany	
1:N 관계 매핑, Attr = mappedBy 을 이용한 매핑주체 설정 외래키의 역할을 하는 필드에 지정

⭐ @ManyToOne	
N:1 관계 매핑

⭐ @OneToOne	
1:1 매핑

⭐ @Transaction	
트랜잭션 적용,
Attr : readOnly = true 설정 시 읽기전용 트랜잭션 적용
Attr : propagation = Propagation.REQUIRED 설정 시 현재 진행중 트랜잭션이 있으면 해당 트랜잭션을 사용,
                                                                                                          존재하지 않으면 새 트랜잭션 생성
⭐ @EnableJpa
Repositories	DB를 사용하기 위한 JpaRepository가 위치한 패키지와 entityManaferFactory 빈에 대한 참조 설정
Attr Backpackages = 기존 Jpa Repo를 그대로 사용하도록 해당 Repo 패키지 경로 삽입
Attr entityManagerFactoryRef = Bean 생성 메소드 명 삽입

⭐ @EnableAsync	 
비동기 메소드 선언

⭐ @EventListener	 
이벤트 리스너 지정

⭐ @SpringBootTest	
어플리케이션 테스트를 위한 Application Context 생성
 
⭐ @AutoConfigure
MockMvc	Controller 테스트를 위한 어플리케이션 자동 구성 작업
 
⭐ @MockBean	
Application Context에 등록된 Bean에 대한 Mock 객체 생성 & 주입

⭐ @ExtendWith	
Spring을 사용하지않고 순수 Mockito의 기능만 사용하기 위해 클래스레벨에 추가

⭐ @Mock	
해당 필드의 객체를 Mock 객체로 만듬

⭐ @InjectMocks	
@Mock을 설정한 객체가 InjectMocks를 추가한 필드에 주입

⭐ @ElementCollection
속성 fetch = FetchType.EAGER
유저 등록시, 유저의 권한 테이블 생성

⭐ @AuthenticationPrincipal 
OAuth2User 타입으로 OAuth2User 객체를 얻음

⭐ @RegisteredOAuth2AuthorizedClient("google")
OAuth2AuthorizedClient 타입으로 파라미터로 OAuth2 AccessToken 객체를 얻음

⭐ @RequiredArgsConstructor
final 이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해줌

⭐ @Data
Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor 를 합친 어노테이션

⭐ @CrossOrigin
CORS 설정

⭐ @AutoConfigureRestDocs
RestDocs