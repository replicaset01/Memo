```java
import java.util.List;

public class Declarative {
    public static void main(String[] args) {
        List<Integer> num = List.of(1, 3, 6, 7, 8, 11);

        int sum = num.stream()
                .filter(number -> number > 4 && (number % 2 == 0))
                .mapToInt(number -> number)
                .sum();

        System.out.println("# 선언형 프로그래밍: " + sum);
    }
}
```