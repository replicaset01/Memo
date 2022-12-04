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
            example02();
            example03();
            example04();
            example05();
        };
    }

    private void example01()
    {
        Member member = new Member(1L, "abc@abc.com", "홍길동", "010-1111-1111");

        em.persist(member);

        Member resultMember1 = em.find(Member.class, 1L);
        System.out.println("Id = " +
                resultMember1.getMemberId() +
                ", email: " +
                resultMember1.getEmail());
    }

    private void example02()
    {
        tx.begin();
        Member member = new Member(1L, "abc@abc.com", "홍길동", "010-1111-1111");
        em.persist(member);
        tx.commit();
        Member resultMember1 = em.find(Member.class, 1L);
        Member resultMember2 = em.find(Member.class, 2L);

    }

    private void example03()
    {
        tx.begin();
        Member member1 = new Member(1L, "abc@abc.com", "신건우", "010-1111-1111");
        Member member2 = new Member(1L, "abc@ab.com", "신건", "010-1111-111");

        em.persist(member1);
        em.persist(member2);
        tx.commit();
    }

    private void example04()
    {
        tx.begin();
        em.persist(new Member(1L, "abc@abc.com", "신건우", "010-1111-1111"));
        tx.commit();

        tx.begin();
        Member member = em.find(Member.class, 1L);
        member.setEmail("aaa@aaa.com");
        tx.commit();
    }

    private void example05()
    {
        tx.begin();
        em.persist(new Member(1L, "skw@skw.com", "신건우", "010-1111-1111"));
        tx.commit();

        tx.begin();
        Member member = em.find(Member.class, 1L);
        em.remove(member);
        tx.commit();
    }
}
```