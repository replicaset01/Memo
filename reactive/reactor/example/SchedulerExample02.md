```java
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * subscribeOn() Operator를 이용해서 Scheduler를 추가할 경우
 */
@Slf4j
public class SchedulersExample02 {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .range(1, 10)
            .subscribeOn(Schedulers.boundedElastic())     // (1)
            .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))   // (2)
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
```