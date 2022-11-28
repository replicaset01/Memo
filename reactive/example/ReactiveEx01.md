```java
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Reactive {
    public static void main(String[] args) {

        /* example 1 */
        //i Publisher의 역할 - 데이터 emit
        Mono<String> mono = Mono.just("Hello, Reactive");
        //i Subscriber의 역할 - emit 데이터 소비
        mono.subscribe(message -> System.out.println(message));

        /* example 2 */
        //i 메소드 체인 구성
        Mono
                .just("Hello, Reactive")
                .subscribe(message -> System.out.println(message));

        /* example 3 */
        //i flux
        Flux //i publisher
                .fromIterable(List.of(1, 3, 6, 7, 8, 11))
                .filter(number -> number > 4 && (number % 2 == 0))
                .reduce((n1, n2) -> n1 + n2)
                .subscribe(System.out::println); //i subscribe, subscriber
    }
}
```