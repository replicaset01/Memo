```java
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class LogExample01 {
    public static void main(String[] args) {
        Flux
            .fromStream(Stream.of(200, 300, 400, 500, 600))
            .log()
            .reduce((a, b) -> a + b)
            .log()
            .subscribe(System.out::println);
    }
    /*
            구독 시점에 onSubscribe Signal 이벤트가 발생합니다. 실행 결과에서는 onSubscribe Signal 이벤트가 두 번 발생했습니다.
데이터 요청 시, request Signal 이벤트가 발생합니다. 실행 결과에서는 request 이벤트가 두 번 발생했습니다.
Publisher가 데이터를 emit할 때 onNext Signal 이벤트가 발생합니다. 실행 결과에서는 총 다섯 개의 데이터가 emit 되었으므로 다섯 번의 onNext Signal 이벤트가 발생했습니다.
Publisher의 데이터 emit이 정상적으로 종료되면 onComplete Signal 이벤트가 발생합니다. 실행 결과에서는 총 두 번의 onComplete Signal 이벤트가 발생했습니다.
     */
}
```