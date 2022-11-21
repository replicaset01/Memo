```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import remindproject.member.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaDirectMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory)
    {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args ->
        {
            tx.begin();
            em.persist(new Member(1L, "abc@abc.com", "신건우", "010-1111-1111"));
            tx.commit();
            Member member = em.find(Member.class, 1L);
        };
    }
}
```