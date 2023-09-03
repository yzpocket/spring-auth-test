# Spring Boot Practice!
[Spring] Spring 환경에서 어노테이션 자동 Bean등록이 아닌 수동으로 Bean을 등록해보기!

## 🖥️ 저장소 소개
@Component 어노테이션이 아닌 수동으로 원하는 기능을 Bean으로 등록하는 기능을 테스트하기 위한 공간입니다.

## 🕰️ 학습 기간
* 23.09.03

### 🧑‍🤝‍🧑 맴버구성
- (팀장) 김인용 - 혼자!!

### ⚙️ 개발 환경
- **MainLanguage** : `Java` - JDK 17
- **IDE** : IntelliJ IDEA Ultimate
- **Framework** : SpringBoot
- **Database** : None
- **SERVER** : Spring Inner Server

## 📌 주요 기능
#### 학습한 기능
* @Configuration 어노테이션에도 @Component 어노테이션이 숨어있음
* 해당 클래스의 메소드 위에     @Bean 을 작성하면 해당 메소드는 IoC Container에 등록되면서 Bean으로 등록됨
* BCrypt를 통한 비밀번호 암호화 기능을 Bean으로 수동 등록해보기 실습 테스트
- PasswordEncoder 클래스에는 encode(varName) 메소드를 통해서 해당 변수(password)를 해싱함수를 통해 쉽게 암호화 시켜줌
- 또한 위 클래스의 matches(원본, 암화화된암호) 메소드를 통해서 암호하된것과 비교를 true or false로 비교 할 수 있음