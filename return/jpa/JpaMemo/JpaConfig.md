```text
//i 1. 영속성 컨텍스트에만 엔티티 저장

    private void example01() { //i ex01
        Member member = new Member("hgd@gmail.com");
        em.persist(member);

        Member resultMember = em.find(Member.class, 1L);
        System.out.println("id: " + resultMember.getMemberId()
                + ", email: " +resultMember.getEmail());
    }

//i 2. 영속성컨텍스트에 저장된 엔티티 DB 테이블에 저장

    private void example02() {
        tx.begin();
        Member member = new Member("hgd@gmail.com");
        em.persist(member);
        tx.commit();

        Member resultMember1 = em.find(Member.class, 1L);
        System.out.println("Id: " + resultMember1.getMemberId()
        + ", email: " + resultMember1.getEmail());

        Member resultMember2 = em.find(Member.class, 2L);
        System.out.println(resultMember2 == null);
    }

//i 3. DB에 모두 저장

    private void example03() {
        tx.begin();

        Member member1 = new Member("a@a.com");
        Member member2 = new Member("b@b.com");

        em.persist(member1);
        em.persist(member2);
        tx.commit();
    }

//i 4. setter를 이용한 객체 업데이트

    private void example04() {
        tx.begin();
        em.persist(new Member("abc@abc.com"));
        tx.commit();

        tx.begin();
        Member member1 = em.find(Member.class, 1L);
        member1.setEmail("bcd@bcd.com");
        tx.commit();
    }

//i 5. 객체 삭제

    private void example05() {
        tx.begin();
        em.persist(new Member("abc@abc.com"));
        tx.commit();

        tx.begin();
        Member member = em.find(Member.class, 1L);
        em.remove(member);
        tx.commit();
    }
```