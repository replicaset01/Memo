```text
[Dependencies]

⭐ [Lombok]
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
testCompileOnly 'org.projectlombok:lombok'
testAnnotationProcessor 'org.projectlombok:lombok'

⭐ [JSR-330 Provider]
implementation 'javax.inject:javax.inject:1'

⭐ [Web] Scope 추가
implementation 'org.springframework.boot:spring-boot-starter-web'

⭐ [MapStruct]
implementation 'org.mapstruct:mapstruct:1.5.3.Final'
annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.3.Final'

⭐ [Validator]
implementation 'org.springframework.boot:spring-boot-starter-validation'

⭐ [Thymeleaf]
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf
implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'

⭐ [Spring Data JDBC]
implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
runtimeOnly 'com.h2database:h2'

⭐ [Spring Data JPA]
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

⭐ [MySql]
implementation 'mysql:mysql-connector-java'
implementation 'org.springframework.boot:spring-boot-starter-jta-atomikos'

⭐ [Gson]
implementation group: 'com.google.code.gson', name: 'gson', version: '2.8.5'

⭐ [Apache HttpComponents]
implementation 'org.apache.httpcomponents:httpclient'

⭐ [Spring Rest Docs]   
// Rest Docs 의존 라이브러리
testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'  
asciidoctorExtensions 'org.springframework.restdocs:spring-restdocs-asciidoctor'

// :test task 실행 시, 스니핏 디렉토리 경로 지정
tasks.named('test') {
outputs.dir snippetsDir
useJUnitPlatform()
}

// :asciidoctor 실행 시, 기능 사용을 위해 task에 asccidoctorExtensions 설정
tasks.named('asciidoctor') {
configurations "asciidoctorExtensions"
inputs.dir snippetsDir
dependsOn test
}

// :build 실행 전 실행되는 task,  :copyDocument 가 실행 되면 index.html이 static 경로에 copy되며,
    그 파일은 API Docs를 파일로 외부 제공을 위한 용도로 사용 가능
task copyDocument(type: Copy) {
dependsOn asciidoctor            // :asciidoctor 실행 후 task 실행되도록 의존 설정
from file("${asciidoctor.outputDir}")   // asciidoc 경로에 생성되는 index.html copy
into file("src/main/resources/static/docs")   // static 경로로 index.html 추가
}

build {
dependsOn copyDocument  // :build 가 실행되기 전, :copyDocument 가 선행되도록 설정
}

// App 실행 파일이 생성하는 :bootJar 설정
bootJar {
dependsOn copyDocument    // :bootJar 실행 전, :copyDocument 가 선행되도록 의존설정
from ("${asciidoctor.outputDir}") {  // Asciidoctor로 생성되는 index.html을 Jar에 추가
into 'static/docs'    
}

⭐ [Spring Security]
implementation 'org.springframework.boot:spring-boot-starter-security'

⭐ [JJWT]
implementation 'org.springframework.boot:spring-boot-starter-web'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'org.springframework.security:spring-security-test'

implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
runtimeOnly	'io.jsonwebtoken:jjwt-jackson:0.11.5'

⭐ [Mail]
implementation 'org.springframework.boot:spring-boot-starter-mail'

⭐ [JUnit5]
 testImplementation 'org.junit.jupiter:junit-jupiter-api:5.3.1'
 testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.3.1'
 
⭐ [OAuth2]
implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'

⭐ [WebFlux]
implementation 'org.springframework.boot:spring-boot-starter-webflux'

⭐ [r2dbc]
	implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
	//i H2 Non-Blocking Driver
	runtimeOnly 'io.r2dbc:r2dbc-h2'
```