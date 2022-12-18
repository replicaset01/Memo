//i 1. N:1에서 N쪽 연관관계 매핑

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addMember(Member member) {
        this.member = member;
    }

//i 2. N:1 매핑을 이용한 회원과 주문 정보 저장

@Configuration
public class JpaManyToOneUniDirectionConfig {
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
        em.persist(member);

        Order order = new Order();
        order.addMember(member);
        em.persist(order);

        tx.commit();

        Order findOrder = em.find(Order.class, 1L);

        System.out.println("findOrder = " + findOrder.getMember().getMemberId()
        + ", " + findOrder.getMember().getEmail());
    }
}

//i 3. 1쪽에서 양방향 관계 매핑

    //i Member Entity에 추가
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


//i 4. 양방향 관계를 추가한 주문 정보 조회 클래스 수정

    private void mappingManyToOneUniDirection() {
        tx.begin();
        Member member = new Member("abc@abc.com",
                "Hong Gil Dong",
                "010-1111-1111");

        Order order = new Order();
        //Do 1. order객체에 member객체 추가
        order.addMember(member);
        //Do 2. member객체에 order객체 추가
        member.addOrder(order);
        //Do 3. 영속성 컨테이너테  회원,주문 정보 저장
        em.persist(member);
        em.persist(order);
        //Do 4. DB에 저장
        tx.commit();
        //Do 5. 영속성 컨텍스트의 저장된 회원정보를 1차 캐시 조회
        Member findMember = em.find(Member.class, 1L);
        //Do 6. 회원객체에서 주문 객체를 불러와서 stream으로 주문 List 정보에 접근 가능
        findMember.getOrders().stream()
                .forEach(findOrder -> {
                    System.out.println("findOrder = " + findOrder.getOrderId()
                            + ", " + findOrder.getOrderStatus());
                });
    }