```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 13, nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private LocalDateTime createAd = LocalDateTime.now();
    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();
    @Transient
    private String age;

    public Member(Long memberId, String email, String name, String phone) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
```