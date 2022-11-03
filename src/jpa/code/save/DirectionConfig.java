package jpa.code.save;

import com.codestates.entity.Member;
import com.codestates.entity.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class DirectionConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            mappingManyToOneUniDirection();
        };
    }

    private void mappingManyToOneUniDirection() {
        tx.begin();
        Member member = new Member("abc@abc.com",
                "Hong Gil Dong",
                "010-1111-1111");

        Order order = new Order();
        order.addMember(member);
        member.addOrder(order);
        em.persist(member);
        em.persist(order);

        tx.commit();

        Member findMember = em.find(Member.class, 1L);

        findMember.getOrders().stream()
                .forEach(findOrder -> {
                    System.out.println("findOrder = " + findOrder.getOrderId()
                            + ", " + findOrder.getOrderStatus());
                });
    }
}