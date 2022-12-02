```java
import com.codestates.example.operators.sample_data.Coffee;
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;

/**
 * timeout(), retry() 기본 예제
 */
@Slf4j
public class TimeoutRetryExample01 {
    public static void main(String[] args) throws InterruptedException {
        getCoffees()
                .collect(Collectors.toSet())   // (5)
                .subscribe(bookSet -> bookSet
                                        .stream()
                                        .forEach(data ->
                                                log.info("{} : {}", data.getKorName(), data.getPrice())));

        Thread.sleep(12000);
    }

    private static Flux<Coffee> getCoffees() {
        final int[] count = {0};
        return Flux
                .fromIterable(SampleData.coffeeList)
                .delayElements(Duration.ofMillis(500)) // (1)
                .map(coffee -> {
                    try {
                        count[0]++;
                        if (count[0] == 3) {     // (2)
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {
                    }

                    return coffee;
                })
                .timeout(Duration.ofSeconds(2))   // (3)
                .retry(1)     // (4)
                .doOnNext(coffee -> log.info("# getCoffees > doOnNext: {}, {}",
                        coffee.getKorName(), coffee.getPrice()));
    }
    /*
            코드 4-27은 timeout()과 retry() Operator를 이용해서 일정 시간 내에 데이터가 emit 되지 않으면 다시 시도하는 예제입니다.

(1)의 delayElements() Operator는 입력으로 주어진 시간만큼 각각의 데이터 emit을 지연시키는 Operator입니다. 따라서 Coffee 객체는 0.5초에 한번씩 emit됩니다.

그런에 (2)에서 emit되는 세번째 커피 정보(coffee) 의도적으로 2초 더 지연시켰습니다.

(3)에서는 2초안에 데이터가 emit되지 않으면 onError Signal 이벤트가 발생하도록 지정했기 때문에 (2)에서 총 2.5초가 지연되어 세 번째 커피 정보(coffee)는 Downstream으로 emit되지 않습니다.

onError Signal 이벤트가 발생했기 때문에 모든 Sequence가 종료되어야하지만 (4)에서 retry() Operator를 추가했기 때문에 1회 재구독을 해서 Sequence를 다시 시작합니다.

이제 timeout이 발생할 이유가 없으므로 데이터는 정상적으로 emit 됩니다.

(5)에서 emit된 데이터를 Set<Coffee>으로 변환하는 이유는 timeout이 되기전에 이미 emit된 데이터가 있으므로 재구독 후, 다시 emit된 데이터에 동일한 데이터가 있으므로 중복을 제거하기 위함입니다.
     */
}
```