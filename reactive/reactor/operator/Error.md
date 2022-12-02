```java
import com.codestates.example.operators.sample_data.Coffee;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 에러 기본 예제
 */
@Slf4j
public class ErrorExample01 {
    public static void main(String[] args) {
        Mono.justOrEmpty(findVerifiedCoffee())  // (1)
                .switchIfEmpty(Mono.error(new RuntimeException("Not found coffee")))  // (2)
                .subscribe(
                        data -> log.info("{} : {}", data.getKorName(), data.getPrice()),
                        error -> log.error("# onError: {}", error.getMessage()));  // (3)
    }

    private static Coffee findVerifiedCoffee() {
        // TODO 데이터베이스에서 Coffee 정보를 조회할 수 있습니다.

        return null;
    }
    /*
            (1)의 justOrEmpty() Operator는 파라미터로 전달되는 데이터소스가 null 이어도 에러가 발생하지 않습니다. just() Operator는 null 데이터를 emit하면 에러가 발생합니다.


(2)의 switchIfEmpty() Operator는 Upstream에서 전달되는 데이터가 null이면 대체 동작을 수행할 수 있습니다.

여기서는 유효하지 않은 커피 객체(예를 들어 null)가 전달되면 error() Operator를 사용해서 onError Signal 이벤트를 발생하도록 했습니다.


findVerifiedCoffee() 메서드가 null을 리턴하기 때문에 (2)에서 onError Signal 이벤트가 전송되고, (3)에서는 error 객체를 전달 받아서 에러 메시지를 출력하고 있습니다.


코드 4-26에서는 error() Operator의 사용법을 이해하기 위해 findVerifiedCoffee() 메서드에서 의도적으로 null을 리턴하도록 했다는 사실을 참고하세요.
     */
}
```