
> http://plus4070.github.io/nhn%20entertainment%20devdays/2017/01/22/Exception/

<br>

---

> ### ⭐체크 예외 : 체크를 해야되는 예외
* 체크 예외는 RuntimeException을 상속하지 않는 예외들을 말하는데, 체크 예외가 발생할 수 있는 메소드를 사용할 경우, 
* 복구가 가능한 예외들이기 때문에 반드시 예외를 처리하는 코드를 함께 작성해야 한다. 
* catch문으로 예외를 잡든, throws로 예외를 자신을 호출한 클래스로 던지는 방법으로 해결해야 하는데, 
* 이를 해결하지 않으면 컴파일 에러 가 발생한다.
* 대표적으로는 IOException이나 SQLException 등이 존재한다.
* 체크예외는 @Transactional만 추가해서는 RollBack이 되지 않는다.
* 말그대로 체크(catch)를 한 후에 해당예외를 복구할지 회피할지 등의 적절한 예외 전략 고민
> 만일 별도의 예외 전략을 짤 필요가 없다면 @Transactional(rollbackFor = {SQLException.class,  DataFormatException.class})와 같이 해당 체크 예외를 직접 지정해주거나 언체크 예외(unchecked exception)로 감싸서 rollback이 동작하도록 할 수 있습니다.


---

> ### ⭐언체크예외
* RuntimeException을 상속한 예외들은 따로 언체크 예외라고 부르는데, 명시적으로 예외처리를 강제하지 않기 때문이다. 
* 언체크 예외는 따로 런타임 예외라고도 부른다. 
* 언체크 예외는 따로 catch문으로 잡거나, throws로 선언하지 않아도 된다. 
* 언체크 예외는 프로그램에 오류가 있을 때 발생하도록 의도된 것들이다.

* 대표적으로는 NullPointerException이나 IllegalArgumentException 등이 존재한다.

<br>

---

>  ### ⭐@Transactional
* import org.springframework.transaction.annotation.Transactional
* 클래스 레벨에 @Transactional 적용시, 해당 클래스에서 Repository의 기능을 이용하는 모든 메소드에 트랜잭션 적용


> ### ⭐Attribute

  * ### ReadOnly = true
    * 컨텍스트 flush & 스냅샷 생성을 하지 않는다 -> 성능 최적화
  * ### Propagation (트랜잭션 전파) 
    * [Propagation.*REQUIRED*]
      * 우리가 앞에서 @Transactional 애너테이션의 propagation 애트리뷰트에 지정한 Propagation.REQUIRED 는 일반적으로 가장 많이 사용되는 propagation 유형의 디폴트 값입니다.
        진행 중인 트랜잭션이 없으면 새로 시작하고, 진행 중인 트랜잭션이 있으면 해당 트랜잭션에 참여합니다. 
    * [Propagation.*REQUIRES_NEW*]
      * 이미 진행중인 트랜잭션과 무관하게 새로운 트랜잭션이 시작됩니다. 기존에 진행중이던 트랜잭션은 새로 시작된 트랜잭션이 종료할 때까지 중지됩니다. 
    * [Propagation.*MANDATORY*]
      * Propagation.REQUIRED는 진행 중인 트랜잭션이 없으면 새로운 트랜잭션이 시작되는 반면, Propagation.MANDATORY는 진행 중인 트랜잭션이 없으면 예외를 발생시킵니다. 
    * [Propagation.*NOT_SUPPORTED*]
      * 트랜잭션을 필요로 하지 않음을 의미합니다. 진행 중인 트랜잭션이 있으면 메서드 실행이 종료될 때 까지 진행중인 트랜잭션은 중지되며, 메서드 실행이 종료되면 트랜잭션을 계속 진행합니다. 
    * [Propagation.*NEVER*]
      * 트랜잭션을 필요로 하지 않음을 의미하며, 진행 중인 트랜잭션이 존재할 경우에는 예외를 발생시킵니다.
  * ### Isolation Level (트랜잭션 격리)
    * [Isolation.*DEFAULT*]
      * 데이터베이스에서 제공하는 기본 값입니다. 
    * [Isolation.*READ_UNCOMMITTED*]
      * 다른 트랜잭션에서 커밋하지 않은 데이터를 읽는 것을 허용합니다. 
    * [Isolation.*READ_COMMITTED*] 
      * 다른 트랜잭션에 의해 커밋된 데이터를 읽는 것을 허용합니다. 
    * [Isolation.*REPEATABLE_READ*] 
      * 트랜잭션 내에서 한 번 조회한 데이터를 반복해서 조회해도 같은 데이터가 조회되도록 합니다. 
    * [Isolation.*SERIALIZABLE*] 
      * 동일한 데이터에 대해서 동시에 두 개 이상의 트랜잭션이 수행되지 못하도록 합니다.


<br>

---

> ### ⭐ AOP 트랜잭션
* [TransactionInterceptor] 
  * 트랜잭션 어드바이스용






