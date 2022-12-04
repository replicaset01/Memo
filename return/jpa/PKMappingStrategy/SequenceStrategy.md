```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import remindproject.member.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaIdSequenceMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;

    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory)
    {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args ->
        {
          tx.begin();
          em.persist(new Member());
          Member member = em.find(Member.class,1L);
          tx.commit();
        };
    }
}
```