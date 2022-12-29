> ⭐ [인텔리제이 로그 한글 깨질때]

server:
  servlet:
    encoding:
      force-response: true
      charset: UTF-8

> ⭐ [H2 Database]

  h2:
    console:
      enabled: true
      path: /h2
  datasource:
    url: jdbc:h2:mem:test

> ⭐ [JPA]

  jpa:
    hibernate:
      ddl-auto: create  # (1) 스키마 자동 생성
    show-sql: true      # (2) SQL 쿼리 출력
    properties:
      hibernate:
        format_sql: true  # (3) SQL pretty print
//  sql:
//    init:
//      data-locations: classpath*:db/h2/data.sql

> ⭐ [JPA Log Level 설정]

logging:
  level:
    org:
      springframework:
        orm:
          jpa: DEBUG
            
> ⭐ [HTTP Encoding CharSet 설정]

server:
  servlet:
    encoding:
      charset: UTF-8
      force-response: true
        
> ⭐ [Mail 설정]

mail:
  address:
    admin: admin@gmail.com
      
> ⭐ [OAuth2 Client 설정]

  security:
    oauth2:
      client:
        registration:
          google:
            clientId: xxxxx
            client-secret: xxxxx