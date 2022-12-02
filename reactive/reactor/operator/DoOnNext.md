```java
import com.codestates.example.operators.sample_data.Coffee;
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * doOnNext() 기본 예제
 */
@Slf4j
public class DoOnNextExample01 {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coffeeList)
                .doOnNext(coffee -> validateCoffee(coffee))    // (1)
                .subscribe(data -> log.info("{} : {}", data.getKorName(), data.getPrice()));
    }

    private static void validateCoffee(Coffee coffee) {
        if (coffee == null) {
            throw new RuntimeException("Not found coffee");
        }
        // TODO 유효성 검증에 필요한 로직을 필요한 만큼 추가할 수 있습니다.
    }
    /*
            doOnNext()를 이용해서 emit되는 데이터의 유효성 검증을 진행하는 예제 코드입니다.


여러분들이 Spring MVC에서 DTO 클래스의 유효성 검증을 학습했던 부분을 떠올려 보세요.

DTO 클래스의 필드에 유효성 검증을 위한 애너테이션을 추가하여 Controller의 핸들러 메서드에서 DTO 클래스를 전달 받기 전에 유효성 검증을 수행했던 것 기억날거라 생각합니다.


Spring MVC에서의 DTO 클래스에 대한 유효성 검증과 유사하게 Reactor Sequence 상에서 emit된 데이터의 유효성 검증을 진행하고자 한다면 (1)과 같이 doOnNext() Operator에서 수행할 수 있습니다
     */
}
```