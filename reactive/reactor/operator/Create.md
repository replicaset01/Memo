```java
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Arrays;
import java.util.List;

/**
 * create() Operator 기본 예제
 */
@Slf4j
public class CreateExample {
    private static List<Integer> source= Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19);

    public static void main(String[] args) {
        Flux.create((FluxSink<Integer> sink) -> {   // (1)
            // (2)
            sink.onRequest(n -> {
                for (int i = 0; i < source.size(); i++) {
                    sink.next(source.get(i));   // (3)
                }
                sink.complete();    // (4)
            });

            // (5)
            sink.onDispose(() -> log.info("# clean up"));
        }).subscribe(data -> log.info("# onNext: {}", data));
    }
    
    /*
            create() Operator의 파라미터는 FluxSink라는 람다 파라미터를 가지는 람다 표현식입니다.

(1)의 FluxSink는 Flux나 Mono에서 just(), fromIterable() 같은 데이터 생성 Operator에 데이터소스를 전달하면 내부에서 알아서 데이터를 emit 하는 등의 Sequence를 진행하는 것이 아니라 **프로그래밍 방식으로 직접 Signal 이벤트를 발생 시켜서 Sequence를 진행하도록 해주는 기능을 합니다.

따라서 create() Operator 내부에서 FluxSink의 객체를 통해 모든 작업을 진행합니다.**

(2)의 onRequest()는 Subscriber에서 데이터를 요청하면 onRequest()의 파라미터인 람다 표현식이 실행됩니다.

(3)에서 for문을 순회하면서 next() 메서드로 List source의 원소를 emit하고 있습니다.

(4)에서는 List source 원소를 모두 emit했으므로, Sequence를 종료하기 위해 complete() 을 호출합니다.

(5)의 onDispose()는 Sequence가 완전히 종료되기 직전에 호출되며, sequence 종료 직전 후처리 작업을 할 수 있습니다.


실행 결과

1
2
3
4
5
6
7
8
9
10
11
10:26:22.231 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 1
10:26:22.232 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 3
10:26:22.232 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 5
10:26:22.232 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 7
10:26:22.232 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 9
10:26:22.232 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 11
10:26:22.232 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 13
10:26:22.232 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 15
10:26:22.233 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 17
10:26:22.233 [main] INFO com.codestates.example.operators.create.CreateExample - # onNext: 19
10:26:22.233 [main] INFO com.codestates.example.operators.create.CreateExample - # clean up
     */
}
```