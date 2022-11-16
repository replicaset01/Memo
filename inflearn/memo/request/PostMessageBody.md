//i MessageBody로 Post요청 urlencoding, json

//i 클래스
ServletInputStream
StreamUtils

//i 메소드
getInputStream()
copyToString(inputStream, StandardCharsets.UTF-8)

//i json 데이터 요청 시 json 응답을 객체로 변환

파싱할 객체 생성

서블릿에
Entity entity = objectMapper.readValue(K, Entity.class);