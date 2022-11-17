```java
import lombok.Getter;

public class BusinessLogicException extends RuntimeException 
{
    
    @Getter
    private ExceptionCode exceptionCode;
    
    public BusinessLogicException(ExceptionCode exceptionCode)
    {
        //i super() = 부모클래스의 생성자 호출, 부모 생성자 호출은 항상 첫번째 줄이어야 함
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
```