```java
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * flatMap() 기본 예제
 */
@Slf4j
public class FlatMapExample01 {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .range(2, 6)         // (1)
            .flatMap(dan -> Flux
                    .range(1, 9)  // (2)
                    .publishOn(Schedulers.parallel())   // (3)
                    .map(num -> dan + " x " + num + " = " + dan * num)) // (4)
            .subscribe(log::info);

        Thread.sleep(100L);
    }
    
    /*
flatMap() Operator를 이해하기 좋은 예제 중 하나가 바로 구구단 예제입니다.

(1)에서 range() Operator를 이용해서 구구단 2단부터 7단까지 출력하도록 지정합니다.

(2)와 같이 flatMap() 내부에서 range() Operator로 하나의 단을 출력하도록 1부터 9까지 숫자를 지정합니다.

(3)에서 flatMap() 내부의 Inner Sequence를 처리할 쓰레드를 할당합니다. 따라서 코드 4-20의 전체 Sequence는 여러 개의 쓰레드가 비동기적으로 동작합니다.

(4)에서는 구구단 형식으로 문자열을 구성합니다.

flatMap() Operator 예제 코드 실행 결과를 보면 2단부터 7단까지 구구단이 출력되는 것을 확인할 수 있습니다.


그런데 2단부터 7단까지 차례대로 출력되는 것이 아니라 실행 결과가 뒤섞여서 출력되는 것을 볼 수 있습니다.


이처럼 flatMap() Operator에서 추가 쓰레드를 할당할 경우, 작업의 처리 순서를 보장하지 않는다는 것을 알 수 있습니다.
     */
}
```