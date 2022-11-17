```text
---
//i v1
ErrorResponse 객체를 응답으로 전송
//i v2



---
//i MemberService
throw 예외
BusinessLogicException
(ExceptionCode.Custom_Status)

---
//i Global - Business 처리
공통 처리
handlerBusinessLogicException
(BusinessException e)
return new ResponseEntity

---
//i ExceptionCode
custom
멤버 - status, message

---
//i BusinessLogic
비즈니스 로직 선택 예외 처리
RuntimeException을 상속
멤버 - super exeptionCode.getMessage()
    - exceptionCode

---
//i ErrorResponse
멤버 - List<FieldError> (내부클래스)
    - List<ConstraintViolationError> (내부클래스)
    
  내부 클래스 FieldError
    멤버 
    field, rejectedValue, reason
    
  내부 클래스 ConstraintViolationError
    멤버 
    propertyPath, rejectedValue, reason

----------------------------------------
//i 과정

1. ErrorResponse에서 에러 응답의 프로퍼티(멤버) 설정

2. ErrorResponse 추가된 멤버의 생성자 private 생성

3. ErrorResponse of 추가 (ExceptionCode, HttpStatus)
    3-1 ExceptionCode = getstatus, getmessage
    
    3-2 HttpStatus = value, getreasonphrase
    
4. Global에서 예외를 처리할 메소드 설정
    4-1 handleBusinessLogic
        final ErrorResponse response = Error.of(e.getExceptionCode)
        
    4-2 handleHttpRequestMethodNotSupported
        final ErrorResponse response = Error.of(HttpStatus.METHOD_NOT_FOUND)
        
    4-3 handleException
    log.error
        final ErrorResponse response = Error.of(HttpStatus.INTERNAL_SERVER_ERROR)

```