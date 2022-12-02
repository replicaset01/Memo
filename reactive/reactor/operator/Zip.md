```java
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * zip() 기본 예제
 */
@Slf4j
public class ZipExample01 {
    public static void main(String[] args) throws InterruptedException {
        // (1)
        Flux<Long> source1 = Flux.interval(Duration.ofMillis(200L)).take(4);

        // (2)
        Flux<Long> source2 = Flux.interval(Duration.ofMillis(400L)).take(6);

        Flux
            .zip(source1, source2, (data1, data2) -> data1 + data2)   // (3)
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(3000L);
    }
    /*
            (1)과 (2)에서 사용한 interval() Operator는 파라미터로 전달한 시간(Duration.ofMillis(…))을 주기로 해서 0부터 1씩 증가한 숫자를 emit하는 Operator입니다.

interval() Operator가 끊임없이 숫자를 emit하는 Operator이기 때문에 take() Operator를 이용해서 take() Operator에 파라미터로 지정한 숫자만큼의 데이터만 emit하고 종료하도록 했습니다.


그런데 (1)의 Sequence는 0.2초에 한 번씩 데이터를 emit하고, (2)의 Sequence는 0.4초에 한 번씩 데이터를 emit하기 때문에 (1)의 Sequence가 매 번 조금 더 빨리 데이터를 emit합니다.


하지만 (1), (2)의 Sequence는 (3)에서 zip() Operator의 입력으로 전달되기 때문에 두 Sequence의 emit 시점이 매번 다르더라도 emit 시점이 늦은 데이터가 emit될 때까지 대기 했다가 두 개의 데이터를 전달 받게 됩니다
     */
}
```