```java
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import remindproject.advice.ExceptionCode;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    /**
     * 유효성 검증에 실패한 필드가 1개 이상일 수 있기 때문에 리스트로 담아야함
     * 필드 에러 정보는 FieldError라는 별도의 static class를
     * ErrorResponse 클래스의 static 멤버 클래스로 정의함
     *
     * ErrorResponse의 static 멤버 클래스로 정의 하는 이유는
     * 둘의 관심사가 에러라는 공통의 관심사를 가지고 있으므로 멤버로 표현하는게 적절함
     */


    private int status;
    private String message;
     //i MethodArgumentNotValidException에서 발생하는 에러 정보를 담는 변수
    private List<FieldError> fieldErrors;
    //i ConstranitViolationException에서 발생하는 에러 정보를 담는 변수
    private List<ConstraintViolationError> violationErrors;

    //i private인 이유 = 객체생성 방식이 아닌 of() 메소드를 이용해서 ErrorResponse의 객체 생성
    //i 객체 생성과 동시에 ErrorResponse의 역할을 명확하게 나눠줌
    private ErrorResponse(final List<FieldError> fieldErrors,
                          final List<ConstraintViolationError> violationErrors)
    {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    //i BindingResult에 대한 ErrorResponse 객체 생성
    //i MethodArgException에서 에러 정보를 얻기위해 필요한것이 BindingResult 객체이므로
    //i 이 of() 메소드를 호출하는 쪽에서 BindingResult 객체를 파라미터로 넘겨주면 됨
    //i 그런데, 이 Binding 객체를 가지고 에러정보를 추출하는 과정은 FieldError 클래스에게 위임
    public static ErrorResponse of(BindingResult bindingResult)
    {
        return new ErrorResponse(FieldError.of(bindingResult), null);
    }

    //i Set<ConstraintViolation<?>> 객체에 대한 ErrorResponse 객체 생성
    //i 위와 동일
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations)
    {
        return new ErrorResponse(null, ConstraintViolationError.of(violations));
    }

    public static ErrorResponse of(ExceptionCode exceptionCode)
    {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    public static ErrorResponse of(HttpStatus httpStatus)
    {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    @Getter
    public static class FieldError
    {
        private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason)
        {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult)
        {
            final List<org.springframework.validation.FieldError> fieldErrors =
                    bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class ConstraintViolationError
    {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue, String reason)
        {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolationError> of(Set<ConstraintViolation<?>> constraintViolations)
        {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()))
                    .collect(Collectors.toList());
        }
    }
}
```