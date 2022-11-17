```java
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    /**
     * 유효성 검증에 실패한 필드가 1개 이상일 수 있기 때문에 리스트로 담아야함
     * 필드 에러 정보는 FieldError라는 별도의 static class를
     * ErrorResponse 클래스의 static 멤버 클래스로 정의함
     *
     * ErrorResponse의 static 멤버 클래스로 정의 하는 이유는
     * 둘의 관심사가 에러라는 공통의 관심사를 가지고 있으므로 멤버로 표현하는게 적절함
     */
    
    private List<FieldError> fieldErrors;

    @Getter
    @AllArgsConstructor
    public static class FieldError
    {

        /**
         * DTO의 유효성 검증에 실패한 필드(멤버 변수)에 대한 에러 정보만 담아서 응답
         */
        
        private String field;
        private Object rejectedValue;
        private String reason;
    }
}
```