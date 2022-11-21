```text
⭐
@Entity
JPA 엔티티 지정

⭐
@Id, @GeneratedValue
식별자, 전략 지정

⭐
tx.begin()
트랜잭션 시작

⭐
persist()
영속성 컨텍스트에 저장

⭐
tx.commit()
DB 테이블에 저장, 내부적으로 em.flush()가 호출됨

⭐
find(타입, 식별자값)
영속성 컨텍스트에서 조회

⭐
em.remove()
영속성 컨텍스트의 1차캐시 엔티티 제거

⭐
em.flush()
영속성 컨텍스트의 변경사항을 테이블에 반영

⭐ Mapping
객체 - 테이블
필드 - 컬럼
엔티티 - 엔티티
기본키


```