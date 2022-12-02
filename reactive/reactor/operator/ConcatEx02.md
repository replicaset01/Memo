```java
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * concat() 기본 예제 2
 */
@Slf4j
public class ConcatExample02 {
    public static void main(String[] args) {
        Flux
            .concat(Flux.fromIterable(SampleData.salesOfCafeA),
                    Flux.fromIterable(SampleData.salesOfCafeB),
                    Flux.fromIterable(SampleData.salesOfCafeC))
                .reduce((a, b) -> a + b)
            .subscribe(data -> log.info("# total sales: {}", data));
    }
    
    /* 
    concat() Operator를 이용해서 3개 카페 지점의 월별 매출액을 모두 하나의 Sequence로 연결 한 다음 카페의 전체 매출액을 계산하는 예제입니다.

3개 카페의 월별 매출액은 reduce() Operator로 누적해서 전체 매출액을 계산했습니다.


실행 결과

1
13:31:20.314 [main] INFO com.codestates.example.operators.transformation.ConcatExample02 - # total sales: 153200000

     */
}
```