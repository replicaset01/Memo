```java
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

/**
 * fromIterable() 기본 예제
 */
@Slf4j
public class FromIterableExample01 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.coffeeList)
            .subscribe(coffee -> log.info("{} : {}", coffee.getKorName(), coffee.getPrice()));
    }
}
```