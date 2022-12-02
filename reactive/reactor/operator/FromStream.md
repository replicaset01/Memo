```java
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

/**
 * fromStream() 기본 예제
 */
public class FromStreamExample01 {
    public static void main(String[] args) {
        Flux
            .fromStream(Stream.of(200, 300, 400, 500, 600))  // (1)
            .reduce((a, b) -> a + b)                         // (2)
            .subscribe(System.out::println);
    }

    /** 
     * //i (1)에서 fromStream()의 입력으로 Stream을 전달합니다. 전달 받은 Stream이 포함하고 있는 데이터를 차례대로 emit합니다.

     reduce() Operator는 Upstream에서 emit된 두 개의 데이터를 순차적으로 누적 처리할 수 있는 Operator입니다.

     //i (2)에서는 Upstream으로부터 전달 받은 두 개의 숫자를 합하는 과정을 반복해서 총합계를 구하고 있습니다.
     
     실행 결과 = 2000
     */
}
```