```java
import java.util.List;

public class Imperative {
    public static void main(String[] args) {
        //i List에 있는 숫자들 중 4보다 큰 짝수의 합계 구하기
        List<Integer> num = List.of(1, 3, 6, 7, 8, 11);
        int sum = 0;

        for(int number : num) {
            if (number > 4 && (number % 2 == 0)) {
                sum = sum + number;
            }
        }
        System.out.println(sum);
    }
}
```