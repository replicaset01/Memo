```text
⭐ [H2 Database]
spring:
  h2:
    console:
      enabled: true
      path: /h2-console      // 콘솔 접속 URL
  datasource:
    url: jdbc:h2:mem:test     // JDBC 연결 URL
  sql:
    init:
      schema-locations: classpath*:db/h2/schema.sql  // 테이블 생성 파일
      data-locations: classpath*:db/h2/data.sql
logging:
  level:
    org:
      springframework:
        jdbc:
          core: TRACE
          
-------------------------------
⭐ [JPA]
spring:
  h2:
    console:
      enabled: true
      path: /h2     
  datasource:
    url: jdbc:h2:mem:test
  jpa:
    hibernate:
      ddl-auto: create  # (1) 스키마 자동 생성
    show-sql: true      # (2) SQL 쿼리 출력
---------------------------------
⭐ [JPA Log Level 설정]
logging:
  level:
    org:
      springframework:
        orm:
          jpa: DEBUG
```