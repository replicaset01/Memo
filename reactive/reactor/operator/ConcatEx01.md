```java
import reactor.core.publisher.Flux;

/**
 * concat() 기본 예제 1
 */
public class ConcatExample01 {
    public static void main(String[] args) {
        Flux
            .concat(Flux.just("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                    Flux.just("Saturday", "Sunday"))
            .subscribe(System.out::println);
    }
}

/*
        concat() Operator의 입력으로 두 개의 Flux Sequence를 전달했기 때문에 두 개의 Sequence를 이어 붙여서 논리적으로 하나의 Sequence로 동작하게 됩니다.
 */
```