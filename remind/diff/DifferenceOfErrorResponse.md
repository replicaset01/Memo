```text
//i v1 ⭐

--- ErrorResponse
유효성 검증에 실패한 DTO의 예외만 처리

--- GlobalExceptionAdvice
예외처리 공통화 + 예외처리 메소드를 여기서 작성 & 처리

//i v2 ⭐

--- ErrorResponse
공통화된 예외처리 메소드를 여기서 작성 (of메소드)

--- GlobalExceptionAdvice
ErrorResponse에 작성된 공통화된 예외처리 메소드(of)를 사용

//i v3 ⭐

--- ErrorResponse


--- GlobalExceptionAdvice


```