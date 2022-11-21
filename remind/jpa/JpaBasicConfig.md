```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import remindproject.member.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner (EntityManagerFactory emFactory)
    {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args ->
        {
            example01();
        }
    }

    private void example01()
    {
        Member member = new Member(1L, "abc@abc.com", "신건우", "010-1111-1111");

        em.persist(member);

        Member resultMember = em.find(Member.class, 1L);
        System.out.println("Id = " +
                resultMember.getMemberId() +
                ", email: " +
                resultMember.getEmail());
    }
}

```