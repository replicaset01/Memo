```java
import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import com.codestates.coffee.dto.CoffeeResponseDto;
import com.codestates.coffee.entity.Coffee;
import com.codestates.coffee.mapper.CoffeeMapper;
import com.codestates.coffee.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * <h3>API 계층과 서비스 계층 연동 실습 과제용 Solution 코드 포함</h3>
 *
 * CoffeeController는 실습 과제를 위한 다섯 개의 Solution 메서드를 포함하고 있습니다.
 * CoffeeController는 이번 과제를 수행함으로 인해서 서비스 클래스인 MemberService와 Mapper 인터페이스인 CoffeeMapper와 연동하게 됩니다.
 *
 * <h4>CoffeeController에서의 DI(Dependency Injection) 설명</h4>
 * <ul>
 *     <li>
 *         CoffeeService
 *         <ul>
 *             <li>
 *                 CoffeeService의 기능을 이용하여 서비스 계층에서 비즈니스 로직을 처리하기 위해 DI 받습니다.
 *             </li>
 *         </ul>
 *     </li>
 *     <li>
 *         CoffeeMapper
 *         <ul>
 *             <li>
 *                 API 계층은 클라이언트로부터 전달받은 request body에 해당하는 DTO 객체를 사용하지만 서비스 계층은 DTO 객체를 직접적으로 사용하는 것이 아니라
 *                 Entity 객체를 사용합니다.
 *                 따라서 DTO 객체를 Entity 객체로 변환해주는 과정이 필요하며, 이 변환을 Mapper가 담당하므로 CoffeeMapper를 DI 받습니다.
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html" target="_blank">@RestController</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html">@RequestMapping</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/annotation/Validated.html" target="_blank">@Validated</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html" target="_blank">@PostMapping</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PatchMapping.html" target="_blank">@PatchMapping</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/GetMapping.html" target="_blank">@GetMapping</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/DeleteMapping.html" target="_blank">@DeleteMapping</a>
 * @author 황정식
 * @version 1.0.0
 */
@RestController
@RequestMapping("/v5/coffees")
@Validated
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;

    /**
     * 서비스 계층과 연동하기 위해 필요한 객체를 생성자로 DI 받습니다.
     *
     * @param coffeeService 비즈니스 로직을 담당하는 CoffeeService 객체
     * @param coffeeMapper  DTO 객체를 Entity 객체로 변환하기 위한 CoffeeMapper 객체
     */
    public CoffeeController(CoffeeService coffeeService, CoffeeMapper coffeeMapper) {
        this.coffeeService = coffeeService;
        this.mapper = coffeeMapper;
    }

    /**
     * 커피 정보 등록을 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>CoffeeService의 커피 정보 등록용 기능 메서드를 호출하여 커피 정보를 전달합니다.</li>
     *     <li>
     *         클라이언트의 요청 정보가 담겨있는 DTO 객체 자체를 CoffeeService 클래스 쪽으로 전달하는 것이 아니라
     *         CoffeeMapper를 이용해 Entity 객체로 변환해서 전달합니다.
     *     </li>
     *     <li>
     *         CoffeeService 클래스로부터 리턴 받은 Coffee 객체 자체를 response body로 전달하는 것이 아니라
     *         CoffeeMapper를 이용해 response body 전용 DTO 객체로 변환해서 전달합니다.
     *     </li>
     * </ul>
     * @param coffeePostDto 커피 정보 등록을 위해 클라이언트 측으로부터 전달 받은 request body에 매핑되는 DTO 클래스
     * @return  클라이언트 측에 전송하는 CoffeeResponseDto를 포함한 ResponseEntity
     */
    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        Coffee coffee = coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDto));

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.CREATED);
    }

    /**
     * 커피 정보 수정을 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>CoffeeService의 커피 정보 수정용 기능 메서드를 호출하여 수정할 커피 정보를 전달합니다.</li>
     *     <li>
     *         URI path variable로 전달 받은 coffeeId 값을 CoffeeService 클래스 쪽에 Entity 객체로 한 번에
     *         전달하기 위해 setter를 이용해 DTO 객체의 필드에 추가해 줍니다.
     *     </li>
     *     <li>
     *         클라이언트의 요청 정보가 담겨있는 DTO 객체 자체를 CoffeeService 클래스 쪽으로 전달하는 것이 아니라
     *         CoffeeMapper를 이용해 Entity 객체로 변환해서 전달합니다.
     *     </li>
     *     <li>
     *         CoffeeService 클래스로부터 리턴 받은 Coffee 객체 자체를 response body로 전달하는 것이 아니라
     *         CoffeeMapper를 이용해 response body 전용 DTO 객체로 변환해서 전달합니다.
     *     </li>
     * </ul>
     * @param coffeeId  URL Path Variable에 매핑되는 커피 식별자. 커피 정보 수정의 대상이 된다.
     * @param coffeePatchDto    커피 정보 수정을 위해 클라이언트 측으로부터 전달 받은 request body에 매핑되는 DTO 클래스.
     * @return  수정된 커피 정보인 CoffeeResponseDto를 포함한 ResponseEntity
     */
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);
        Coffee coffee = coffeeService.updateCoffee(mapper.coffeePatchDtoToCoffee(coffeePatchDto));

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.OK);
    }

    /**
     * 특정 커피 정보 조회를 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>CoffeeService의 커피 정보 조회용 기능 메서드를 호출하여 조회할 커피 식별자(coffeeId)를 전달합니다.</li>
     *     <li>
     *         CoffeeService 클래스로부터 리턴 받은 Coffee 객체 자체를 response body로 전달하는 것이 아니라
     *         CoffeeMapper를 이용해 response body 전용 DTO 객체로 변환해서 전달합니다.
     *     </li>
     * </ul>
     * @param coffeeId  URL Path Variable에 매핑되는 커피 식별자. 해당 식별자를 통해 특정 커피 정보를 조회할 수 있다.
     * @return  클라이언트 측에 전송하는 CoffeeResponseDto를 포함한 ResponseEntity
     */
    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        Coffee coffee = coffeeService.findCoffee(coffeeId);

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.OK);
    }

    /**
     * 여러 건의 커피 정보 목록 조회를 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>CoffeeService의 커피 정보 목록 조회용 기능 메서드를 호출하여 커피 목록을 리턴 받습니다.</li>
     *     <li>
     *         CoffeeService 클래스로부터 리턴 받은 Coffee 목록(List)을 response body로 전달하는 것이 아니라
     *         CoffeeMapper를 이용해 response body 전용 DTO 객체를 원소로 가지는 List로 변환해서 전달합니다.
     *     </li>
     * </ul>
     * @return  클라이언트 측에 전송하는 List&lt;CoffeeResponseDto&gt;를 포함한 ResponseEntity
     */
    @GetMapping
    public ResponseEntity getCoffees() {
        List<Coffee> coffees = coffeeService.findCoffees();
        List<CoffeeResponseDto> response = mapper.coffeesToCoffeeResponseDtos(coffees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 회원 정보 삭제를 위한 핸들러 메서드를 구현한 Solution 코드입니다.
     * <p>
     * <b>Solution 키 포인트</b>
     * <ul>
     *     <li>CoffeeService의 커피 정보 삭제용 기능 메서드를 호출하여 삭제할 커피 식별자(coffeeId)를 전달합니다. </li>
     * </ul>
     * @param coffeeId  URL Path Variable에 매핑되는 커피 식별자. 삭제할 대상이 된다.
     * @return  response body를 포함하지 않는 ResponseEntity.
     *          <p>
     *          커피 정보를 삭제할 경우, 삭제되어 존재하지 않으므로 204 No Content를 지정한다.
     */
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        coffeeService.deleteCoffee(coffeeId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
```