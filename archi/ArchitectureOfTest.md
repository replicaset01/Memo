> ⭐ ### [테스트]


* ### Packages
  * import static org.assertj.core.api.Assertions.*; [assertj]
  * import static org.junit.jupiter.api.Assertions.*; [junit]
  * import static org.junit.jupiter.api.Assumptions.*; [Assumptions]
  * import static org.hamcrest.MatcherAssert.*; [Hamcrest]
  * import static org.hamcrest.Matchers.*; [Hamcrest Matcher]
  * import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; [Controller 테스트]
  * import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; [Controller 테스트]
  * import org.mockito.Mockito; [Mockito]
  * import static org.mockito.BDDMockito.given; [Mockito_given]


* ### Class, Method
  * Junit 
    * assertEquals(a,b) - *값비교*
    * assertNotNull(대상, 실패시 메시지) - *Null 여부 체크*
    * assertThrows(Exception.class, () -> 테스트대상 메소드) -  *예외 발생 테스트*
    * assertDoesNotThrow(() -> Do) - *예외 발생 X 테스트*

  * AsserJ
    * 

  * Assumption
    * assumeTrue() - 파라미터의 값이 true이면, 아래 로직 실행

  * Hamcrest
    * asserThat(a, is(equalTo(b))) - 비교
    * asserThat(a, is(notNullValue())) - Null 검증
    * asserThat(대상.class, is(예상Exception.class)) - 예외 검증

  * URI
    * UriComponentBuilder.newInstance().path().buildAndExpand().toUri - Build Request URI

  * ResultActions - 기대 HTTP Status, Content 검증
    * mockMvc.perform(get & post 등등)

  * MvcResult - Response Body의 HTTP Status, 프로퍼티 검증
    * ResultActions의 객체를 이용 


* ### Anotations 
  * @BeforeEach - *init()* 사용, 테스트 실행 전 전처리
  * @BeforeAll -  *initAll()* 사용, 테스트 케이스가 실행되기전 1번만 실행
  * @DisplayName - 테스트의 이름 지정
  ### Controller
  * @SpringBootTest - Spring Boot 기반 Application Context 생성
  * @AutoConfigureMockMvc - Controller를 위한 앱의 자동 구성 작업, MockMvc를 이용하려면 필수로 추가해야함
  ### Data
  * @DataJpaTest - @Transactional을 포함하고 있어서, 하나의 테스트케이스 종료시 저장된 데이터 RollBack
  ### Mockito
  * @MockBean - Application Context에 있던 Bean을 Mockito Mock 객체를 생성 & 주입
  * @ExtendWith - Spring을 사용하지않고 Junit에서 Mockito의 기능을 사용하기 위해 추가
  * @Mock - 해당 필드의 객체를 Mock 객체로 생성
  * @InjectMocks - @InjectMocks를 설정한 필드에 @Mock으로 생성한 객체를 주입

<br>
---

> ⭐ ### [슬라이스 테스트] API & Data 계층 테스트


* ### given,when,then

  * given - 테스트용 Request Body 설정
  * when - MockMvc 객체로 테스트 대상 Controller 호출(요청URI, HTTP메소드 등 지정)
  * then - Controller 핸들러 메소드에서 응답으로 수신한 HTTP Status 및 response body 검증

 
    * Post - Request Body 생성
    * Gson - Json 변환
    * ResultActions - URI(post), MediaType, content 지정
    * actions.andExpect() - Resonse Body <-> 응답의 프로퍼티와 일치하는지 검증


<br>
---

> ⭐ ### [컨트롤러 테스트]

  * Post - Request Body 생성
  * Gson - Json 변환
  * ResultActions - URI(post), MediaType, content 지정
  * actions.andExpect() - Resonse Body <-> 응답의 프로퍼티와 일치하는지 검증


<br>
---

> ⭐ ### [데이터계층 테스트]

  * @DataJpaTest 사용
  * Entity 객체 생성 - 데이터 생성
  * 저장 & 조회 등 동작 수행
  * 검증






























