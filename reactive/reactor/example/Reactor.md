```java
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Reactor {
    public static void main(String[] args) throws InterruptedException {
        //i Start Sequense
        Flux
                //i 이 Operator는 원본 소스로부터 데이터를 emit 하는 publisher 역할
                .just("Hello", "Reactor")
                //i 이 Operator는 Publisher로부터 받은 데이터 가공
                .map(message -> message.toUpperCase())
                //i 이 Operator는 쓰레드 관리자 역할을 하는 Scheduler 지정하는 역할
                //i Scheduler를 기준으로 쓰레드 실행, publishOn을 포함 2개의 downstream 쓰레드 실행
                .publishOn(Schedulers.parallel())
                //i emit 데이터 처리
                .subscribe(
                        //i emit된 데이터를 받아서 처리
                        System.out::println,
                        //i 에러 발생 시, 에러를 전달 받아서 처리
                        error -> System.out.println(error.getMessage()),
                        //i Reactor Sequence가 종료된 후, 후처리 역할
                        () -> System.out.println("$ onComplete"));
        /**
         * 이 쓰레드는 main 쓰레드의 데몬쓰레드 이므로 주 쓰레드인 main이 종료되면 종료됨
         * 그러므로 0.1초 정도 동작지연 시키면 0.1초 사이에
         * Scheduler로 지정한 데몬 쓰레드를 통해 Reactor Sequence가 정상 동작함
         */
        Thread.sleep(100L);
    }
}

```